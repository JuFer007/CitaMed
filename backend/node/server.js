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
      monto
    } = req.body;

    const templatePath = path.join(__dirname, "templates", "ticket-cita.html");
    let template = fs.readFileSync(templatePath, "utf8");

    template = template
      .replace("{{cliente}}", cliente)
      .replace("{{dni}}", dni)
      .replace("{{fecha}}", fecha)
      .replace("{{hora}}", hora)
      .replace("{{numeroCita}}", numeroCita)
      .replace("{{medico}}", medico)
      .replace("{{especialidad}}", especialidad)
      .replace("{{metodoPago}}", metodoPago)
      .replace("{{monto}}", monto.toFixed(2));

    const browser = await puppeteer.launch({
      args: ["--no-sandbox"],
      headless: "new"
    });

    const page = await browser.newPage();
    await page.setContent(template, { waitUntil: "networkidle0" });

    const pdf = await page.pdf({
      format: "A4",
      printBackground: true
    });

    await browser.close();

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
      receta,
      indicaciones,
      fecha
    } = req.body;

    const templatePath = path.join(__dirname, "templates", "receta.html");
    let template = fs.readFileSync(templatePath, "utf8");

    template = template
      .replace("{{paciente}}", paciente)
      .replace("{{dni}}", dni)
      .replace("{{edad}}", edad)
      .replace("{{medico}}", medico)
      .replace("{{especialidad}}", especialidad)
      .replace("{{diagnostico}}", diagnostico)
      .replace("{{receta}}", receta)
      .replace("{{indicaciones}}", indicaciones)
      .replace("{{fecha}}", fecha);

    const browser = await puppeteer.launch({
      args: ["--no-sandbox"],
      headless: "new"
    });

    const page = await browser.newPage();
    await page.setContent(template, { waitUntil: "networkidle0" });

    const pdf = await page.pdf({
      format: "A4",
      printBackground: true
    });

    await browser.close();

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

    const browser = await puppeteer.launch({
      args: ["--no-sandbox"],
      headless: "new"
    });

    const page = await browser.newPage();
    await page.setContent(template, { waitUntil: "networkidle0" });

    const pdf = await page.pdf({
      format: "A4",
      printBackground: true
    });

    await browser.close();

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
