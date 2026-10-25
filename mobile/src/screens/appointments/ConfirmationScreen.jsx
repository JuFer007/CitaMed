import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { Feather } from '@expo/vector-icons';
import { SafeAreaView } from 'react-native-safe-area-context';
import Button from '../../components/Button';
import { colors, radius, shadow, spacing } from '../../theme';
import { useAppointments } from '../../context/AppointmentsContext';
import { formatDayAndWeekday, formatTime12h } from '../../utils/date';

function Row({ icon, label, value }) {
  return (
    <View style={styles.row}>
      <Feather name={icon} size={18} color={colors.primary} />
      <View style={styles.rowText}>
        <Text style={styles.rowLabel}>{label}</Text>
        <Text style={styles.rowValue}>{value}</Text>
      </View>
    </View>
  );
}

export default function ConfirmationScreen({ route, navigation }) {
  const { getAppointmentById } = useAppointments();
  const appointment = getAppointmentById(route.params.appointmentId);

  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.content}>
        <View style={styles.check}>
          <Feather name="check" size={60} color={colors.white} />
        </View>
        <Text style={styles.title}>¡Cita agendada con éxito!</Text>
        <Text style={styles.subtitle}>
          Tu cita ha sido confirmada correctamente
        </Text>

        {appointment ? (
          <View style={styles.card}>
            <Row icon="user" label="Médico" value={appointment.doctorName} />
            <View style={styles.divider} />
            <Row
              icon="calendar"
              label="Fecha"
              value={formatDayAndWeekday(appointment.date)}
            />
            <View style={styles.divider} />
            <Row icon="clock" label="Hora" value={formatTime12h(appointment.time)} />
            <View style={styles.divider} />
            <Row icon="map-pin" label="Clínica" value={appointment.clinic} />
          </View>
        ) : null}
      </View>

      <View style={styles.footer}>
        <Button
          title="Ver mis citas"
          onPress={() => navigation.navigate('Tabs', { screen: 'AppointmentsTab' })}
        />
        <Button
          title="Ir al inicio"
          variant="secondary"
          style={styles.secondary}
          onPress={() => navigation.navigate('Tabs', { screen: 'HomeTab' })}
        />
      </View>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.background,
  },
  content: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    paddingHorizontal: spacing.xl,
  },
  check: {
    width: 108,
    height: 108,
    borderRadius: 54,
    backgroundColor: colors.primary,
    alignItems: 'center',
    justifyContent: 'center',
    ...shadow.card,
  },
  title: {
    marginTop: spacing.xl,
    fontSize: 26,
    fontWeight: '700',
    color: colors.text,
    textAlign: 'center',
  },
  subtitle: {
    marginTop: spacing.sm,
    fontSize: 15,
    lineHeight: 24,
    color: colors.textMuted,
    textAlign: 'center',
    paddingHorizontal: spacing.sm,
  },
  card: {
    width: '100%',
    marginTop: spacing.xxxl,
    backgroundColor: colors.card,
    borderRadius: radius.card,
    padding: spacing.xl,
    ...shadow.card,
  },
  row: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingVertical: spacing.sm,
  },
  rowText: {
    marginLeft: spacing.md,
    flex: 1,
  },
  rowLabel: {
    fontSize: 12,
    color: colors.textMuted,
    fontWeight: '600',
  },
  rowValue: {
    fontSize: 15,
    fontWeight: '700',
    color: colors.text,
    marginTop: 2,
  },
  divider: {
    height: 1,
    backgroundColor: colors.border,
    marginVertical: spacing.sm,
    opacity: 0.6,
  },
  footer: {
    padding: spacing.xl,
    paddingBottom: spacing.xxxl,
    gap: spacing.md,
  },
  secondary: {
    marginTop: 0,
  },
});
