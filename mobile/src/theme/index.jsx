export const colors = {
  primary: '#0F8B8D',
  primaryDark: '#0B6E70',
  primarySoft: '#E3F3F3',
  background: '#F4FBFB',
  card: '#FFFFFF',
  border: '#CDE8E8',
  text: '#1F2D3D',
  textMuted: '#6B7C8C',
  textTertiary: '#9AA9B5',
  placeholder: '#9AA9B5',
  inputIcon: '#7A8B99',
  star: '#F5A623',
  avatar: '#E3F3F3',
  dotInactive: '#D5DEE3',
  white: '#FFFFFF',
  danger: '#C0392B',
  warning: '#F59E0B',
  success: '#3FB984',
  overlay: 'rgba(11, 110, 112, 0.45)',
};

export const statusColors = {
  confirmed: { bg: '#D6F5E3', text: '#1E8E4F', label: 'Confirmada' },
  pending: { bg: '#FFE9CC', text: '#C77700', label: 'Pendiente' },
  cancelled: { bg: '#FFDADA', text: '#C0392B', label: 'Cancelada' },
  completed: { bg: '#E3F0FF', text: '#2C6FBB', label: 'Realizada' },
};

export const spacing = {
  xs: 4,
  sm: 8,
  md: 12,
  lg: 16,
  xl: 20,
  xxl: 24,
  xxxl: 32,
  xl4: 40,
  xl5: 48,
};

export const radius = {
  card: 24,
  control: 16,
  button: 16,
  pill: 999,
  sm: 10,
  xs: 8,
};

export const shadow = {
  card: {
    shadowColor: '#0F8B8D',
    shadowOffset: { width: 0, height: 6 },
    shadowOpacity: 0.08,
    shadowRadius: 20,
    elevation: 5,
  },
  subtle: {
    shadowColor: '#0F8B8D',
    shadowOffset: { width: 0, height: 1 },
    shadowOpacity: 0.06,
    shadowRadius: 10,
    elevation: 2,
  },
};

export const typography = {
  h1: { fontSize: 26, fontWeight: '700', color: '#1F2D3D', lineHeight: 32 },
  h2: { fontSize: 22, fontWeight: '700', color: '#1F2D3D', lineHeight: 28 },
  h3: { fontSize: 18, fontWeight: '600', color: '#1F2D3D', lineHeight: 24 },
  screenTitle: { fontSize: 20, fontWeight: '700', color: '#1F2D3D', lineHeight: 26 },
  sectionTitle: { fontSize: 16, fontWeight: '600', color: '#1F2D3D', lineHeight: 22 },
  doctorName: { fontSize: 15, fontWeight: '600', color: '#1F2D3D', lineHeight: 20 },
  body: { fontSize: 14, color: '#1F2D3D', lineHeight: 22 },
  bodySm: { fontSize: 13, color: '#6B7C8C', lineHeight: 20 },
  caption: { fontSize: 12, color: '#6B7C8C', lineHeight: 18 },
  buttonText: { fontSize: 15, fontWeight: '600', color: '#FFFFFF', lineHeight: 20 },
  label: { fontSize: 13, fontWeight: '500', color: '#6B7C8C', lineHeight: 18 },
  inputText: { fontSize: 14, color: '#1F2D3D', lineHeight: 20 },
};
