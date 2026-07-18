const express = require("express");
const puppeteer = require("puppeteer");
const fs = require("fs");
const path = require("path");
const cors = require("cors");
const app = express();

app.use(cors());
app.use(express.json({ limit: '10mb' }));
app.use(express.urlencoded({ limit: '10mb', extended: true }));

app.use('/recursos', express.static(path.join(__dirname, 'recursos')));
app.use('/', express.static(path.join(__dirname, 'templates')));

async function renderPdf(html, viewportWidth) {
  const browser = await puppeteer.launch({
    args: ["--no-sandbox"],
    headless: "new"
  });

  try {
    const page = await browser.newPage();

    await page.setViewport({ width: viewportWidth, height: 800 });
    await page.setContent(html, { waitUntil: "networkidle0" });

    await page.evaluate(() => {
      document.body.style.margin = "0";
      document.body.style.padding = "0";
      document.body.style.background = "transparent";
      document.body.style.display = "block";

      const root = document.body.firstElementChild;
      if (root) {
        root.style.margin = "0";
      }
    });

    const rootHandle = await page.evaluateHandle(() => document.body.firstElementChild);
    const rootElement = rootHandle.asElement();
    const box = await rootElement.boundingBox();
    await rootHandle.dispose();

    const width = Math.ceil(box.width);
    const height = Math.ceil(box.height);

    const pdf = await page.pdf({
      width: `${width}px`,
      height: `${height}px`,
      printBackground: true,
      margin: { top: 0, right: 0, bottom: 0, left: 0 },
      pageRanges: "1"
    });

    return pdf;
  } finally {
    await browser.close();
  }
}

app.post("/generar-ticket-cita", async (req, res) => {
  try {
    const {
      cliente,
      dni,
      fecha,
      hora,
      numeroCita,
      medico,
      especialidad,
      metodoPago,
      fechaPago,
      monto,
      subtotal,
      descuento
    } = req.body;

    const templatePath = path.join(__dirname, "templates", "ticket-cita.html");
    let template = fs.readFileSync(templatePath, "utf8");

    const montoStr = Number(monto).toFixed(2);
    const subtotalStr = subtotal !== undefined ? Number(subtotal).toFixed(2) : montoStr;
    const descuentoStr = descuento !== undefined ? Number(descuento).toFixed(2) : '0.00';
    const serviciosHtml = `<div class="fila-total" style="display:flex;justify-content:space-between;font-size:8.5px;color:#444;margin-bottom:3px;">
  <div>Consulta de ${especialidad}</div>
  <div><span class="moneda">S/.</span>${montoStr}</div>
</div>`;

    template = template
        .replace("{{cliente}}", cliente)
        .replace("{{dni}}", dni)
        .replace("{{fecha}}", fecha)
        .replace("{{hora}}", hora)
        .replace("{{numeroCita}}", numeroCita)
        .replace("{{medico}}", medico)
        .replace("{{especialidad}}", especialidad)
        .replace("{{metodoPago}}", metodoPago)
        .replace("{{fechaPago}}", fechaPago || '—')
        .replace("{{monto}}", montoStr)
        .replace("{{servicios}}", serviciosHtml)
        .replace("{{subtotal}}", subtotalStr)
        .replace("{{descuento}}", descuentoStr);

    const pdf = await renderPdf(template, 300);

    res.setHeader("Content-Type", "application/pdf");
    res.send(pdf);

  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

app.post("/generar-receta", async (req, res) => {
  try {
    const {
      paciente,
      dni,
      edad,
      medico,
      especialidad,
      diagnostico,
      enfermedad,
      descripcion,
      receta,
      indicaciones,
      fecha
    } = req.body;

    function toBullets(text) {
      if (!text) return '';
      return text.split(/\.\s+(?=[A-ZÁÉÍÓÚ])/).map(s => s.trim()).filter(s => s.length > 0).map(s => `<li>${s.endsWith('.') ? s : s + '.'}</li>`).join('');
    }

    function buildDiagnosticoHtml(enfermedad, descripcion) {
      let html = `<p style="color:#126565;border-color:#126565" class="text-sm font-semibold border-l-2 pl-3">${enfermedad || ''}</p>`;
      if (descripcion) {
        html += `<p class="text-gray-600 text-sm mt-1 border-l-2 pl-3" style="border-color:#126565">${descripcion}</p>`;
      }
      return html;
    }

    const templatePath = path.join(__dirname, "templates", "receta.html");
    let template = fs.readFileSync(templatePath, "utf8");

    template = template
        .replace(/\{\{paciente\}\}/g, paciente)
        .replace(/\{\{dni\}\}/g, dni)
        .replace(/\{\{edad\}\}/g, edad)
        .replace(/\{\{medico\}\}/g, medico)
        .replace(/\{\{especialidad\}\}/g, especialidad)
        .replace(/\{\{diagnosticoHtml\}\}/g, buildDiagnosticoHtml(enfermedad, descripcion))
        .replace(/\{\{receta\}\}/g, toBullets(receta))
        .replace(/\{\{indicaciones\}\}/g, toBullets(indicaciones))
        .replace(/\{\{fecha\}\}/g, fecha);

    const pdf = await renderPdf(template, 560);

    res.setHeader("Content-Type", "application/pdf");
    res.send(pdf);

  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

app.post("/generar-historial", async (req, res) => {
  try {
    let data = req.body;
    if (data.data) {
      data = JSON.parse(data.data);
    }
    const {
      paciente,
      dni,
      edad,
      genero,
      grupoSanguineo,
      telefono,
      email,
      totalCitas,
      citas,
      diagnosticos
    } = data;

    const templatePath = path.join(__dirname, "templates", "historial-medico.html");
    let template = fs.readFileSync(templatePath, "utf8");
    const fecha = new Date().toLocaleDateString("es-PE", {
      year: "numeric", month: "long", day: "numeric"
    });

    const citasHtml = citas.map(c => `
      <tr class="border-b border-gray-100">
        <td class="px-3 py-2.5 text-gray-900 text-xs">${c.fecha}</td>
        <td class="px-3 py-2.5 text-gray-900 text-xs font-medium">${c.medico}</td>
        <td class="px-3 py-2.5 text-gray-500 text-xs">${c.especialidad}</td>
        <td class="px-3 py-2.5 text-gray-500 text-xs">${c.motivo}</td>
        <td class="px-3 py-2.5 text-center">
          <span class="inline-block text-xs font-semibold px-2 py-0.5 rounded-full ${c.estado === 'ATENDIDA' ? 'bg-green-100 text-green-700' : c.estado === 'PROGRAMADA' ? 'bg-blue-100 text-blue-700' : c.estado === 'CANCELADA' ? 'bg-red-100 text-red-700' : 'bg-yellow-100 text-yellow-700'}">${c.estado}</span>
        </td>
      </tr>
    `).join('');

    const diagnosticosHtml = diagnosticos.length > 0 ? diagnosticos.map(d => `
      <div class="mb-4 pb-4 border-b border-gray-100 last:border-b-0 last:pb-0">
        <p class="text-sm font-bold text-brand">${d.enfermedad}</p>
        ${d.descripcion ? `<p class="text-xs text-gray-600 mt-1">${d.descripcion}</p>` : ''}
        ${d.receta ? `<p class="text-xs text-gray-500 mt-2"><span class="font-semibold">Receta:</span> ${d.receta}</p>` : ''}
        ${d.indicaciones ? `<p class="text-xs text-gray-500 mt-1"><span class="font-semibold">Indicaciones:</span> ${d.indicaciones}</p>` : ''}
      </div>
    `).join('') : '<p class="text-xs text-gray-400 italic">No hay diagnósticos registrados</p>';

    template = template
        .replace("{{paciente}}", paciente)
        .replace("{{dni}}", dni)
        .replace("{{edad}}", edad)
        .replace("{{genero}}", genero)
        .replace("{{grupoSanguineo}}", grupoSanguineo)
        .replace("{{telefono}}", telefono)
        .replace("{{email}}", email)
        .replace("{{fecha}}", fecha)
        .replace("{{totalCitas}}", totalCitas)
        .replace("{{citas}}", citasHtml)
        .replace("{{diagnosticos}}", diagnosticosHtml);

    const pdf = await renderPdf(template, 750);

    res.setHeader("Content-Type", "application/pdf");
    res.setHeader("Content-Disposition", `inline; filename="HISTORIAL_MEDICO_${dni}.pdf"`);
    res.send(pdf);

  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

app.listen(3005, () => {
  console.log("CitaMed PDF Service running on port 3005");
});
