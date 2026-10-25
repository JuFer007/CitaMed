const MONTHS = [
  'enero',
  'febrero',
  'marzo',
  'abril',
  'mayo',
  'junio',
  'julio',
  'agosto',
  'septiembre',
  'octubre',
  'noviembre',
  'diciembre',
];

const MONTHS_SHORT = [
  'ene',
  'feb',
  'mar',
  'abr',
  'may',
  'jun',
  'jul',
  'ago',
  'sep',
  'oct',
  'nov',
  'dic',
];

const WEEKDAYS = ['domingo', 'lunes', 'martes', 'miércoles', 'jueves', 'viernes', 'sábado'];

function parse(iso) {
  const [year, month, day] = iso.split('-').map(Number);
  if (!year || !month || !day) return null;
  return { year, month, day };
}

export function formatLongDate(iso) {
  const p = parse(iso);
  if (!p) return iso;
  return `${p.day} de ${MONTHS[p.month - 1]} de ${p.year}`;
}

export function formatShortDate(iso) {
  const p = parse(iso);
  if (!p) return iso;
  return `${p.day} ${MONTHS_SHORT[p.month - 1]}. ${p.year}`;
}

export function formatDayAndWeekday(iso) {
  const p = parse(iso);
  if (!p) return iso;
  const date = new Date(p.year, p.month - 1, p.day);
  const weekday = WEEKDAYS[date.getDay()];
  return `${weekday.charAt(0).toUpperCase()}${weekday.slice(1)}, ${p.day} de ${
    MONTHS[p.month - 1]
  }`;
}

export function formatTime12h(time) {
  const [hourStr, minute] = time.split(':');
  const hour = Number(hourStr);
  const suffix = hour < 12 ? 'a. m.' : 'p. m.';
  const hour12 = hour % 12 === 0 ? 12 : hour % 12;
  return `${`${hour12}`.padStart(2, '0')}:${minute} ${suffix}`;
}

export function todayISO() {
  const now = new Date();
  const month = `${now.getMonth() + 1}`.padStart(2, '0');
  const day = `${now.getDate()}`.padStart(2, '0');
  return `${now.getFullYear()}-${month}-${day}`;
}

export function formatPrice(value) {
  return `S/ ${value.toFixed(2)}`;
}

export function monthYearLabel(iso) {
  const p = parse(iso);
  if (!p) return iso;
  const name = MONTHS[p.month - 1];
  return `${name.charAt(0).toUpperCase()}${name.slice(1)} ${p.year}`;
}
