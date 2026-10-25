import React from 'react';
import { Alert, ScrollView, StyleSheet, Text, View } from 'react-native';
import { Feather } from '@expo/vector-icons';
import { SafeAreaView } from 'react-native-safe-area-context';
import Avatar from '../../components/Avatar';
import Button from '../../components/Button';
import Header from '../../components/Header';
import StatusBadge from '../../components/StatusBadge';
import { colors, radius, shadow, spacing } from '../../theme';
import { useAppointments } from '../../context/AppointmentsContext';
import { formatDayAndWeekday, formatPrice, formatTime12h } from '../../utils/date';

function InfoRow({ icon, label, value }) {
  return (
    <View style={styles.infoRow}>
      <View style={styles.infoIcon}>
        <Feather name={icon} size={18} color={colors.primary} />
      </View>
      <View style={styles.infoText}>
        <Text style={styles.infoLabel}>{label}</Text>
        <Text style={styles.infoValue}>{value}</Text>
      </View>
    </View>
  );
}

export default function AppointmentDetailScreen({ route, navigation }) {
  const { getAppointmentById, cancelAppointment } = useAppointments();
  const appointment = getAppointmentById(route.params.appointmentId);

  if (!appointment) {
    return (
      <SafeAreaView style={styles.container} edges={['top']}>
        <Header title="Detalle de cita" onBack={() => navigation.goBack()} />
        <View style={styles.empty}>
          <Text style={styles.emptyText}>Cita no encontrada</Text>
        </View>
      </SafeAreaView>
    );
  }

  const isCancelled = appointment.status === 'cancelled';

  const handleCancel = () => {
    Alert.alert(
      'Cancelar cita',
      '¿Seguro que deseas cancelar esta cita? Esta acción no se puede deshacer.',
      [
        { text: 'No', style: 'cancel' },
        {
          text: 'Sí, cancelar',
          style: 'destructive',
          onPress: () => cancelAppointment(appointment.id),
        },
      ],
    );
  };

  return (
    <SafeAreaView style={styles.container} edges={['top']}>
      <Header title="Detalle de cita" onBack={() => navigation.goBack()} />

      <ScrollView contentContainerStyle={styles.scroll} showsVerticalScrollIndicator={false}>
        <View style={styles.doctorCard}>
          <Avatar size={64} />
          <View style={styles.doctorInfo}>
            <Text style={styles.doctorName}>{appointment.doctorName}</Text>
            <Text style={styles.doctorSpecialty}>{appointment.specialty}</Text>
          </View>
          <StatusBadge status={appointment.status} />
        </View>

        <View style={styles.card}>
          <InfoRow
            icon="calendar"
            label="Fecha"
            value={formatDayAndWeekday(appointment.date)}
          />
          <View style={styles.divider} />
          <InfoRow icon="clock" label="Hora" value={formatTime12h(appointment.time)} />
          <View style={styles.divider} />
          <InfoRow icon="file-text" label="Motivo" value={appointment.reason} />
        </View>

        <Text style={styles.sectionTitle}>Clínica</Text>
        <View style={styles.card}>
          <InfoRow icon="home" label="Centro médico" value={appointment.clinic} />
          <View style={styles.divider} />
          <InfoRow icon="map-pin" label="Dirección" value={appointment.address} />
          <View style={styles.divider} />
          <InfoRow icon="phone" label="Contacto" value={appointment.phone} />
        </View>

        <View style={styles.priceRow}>
          <Text style={styles.priceLabel}>Precio de la consulta</Text>
          <Text style={styles.price}>{formatPrice(appointment.price)}</Text>
        </View>
      </ScrollView>

      <View style={styles.footer}>
        {!isCancelled ? (
          <Button
            title="Cancelar cita"
            variant="danger"
            icon="close-circle-outline"
            onPress={handleCancel}
          />
        ) : (
          <View style={styles.cancelledBanner}>
            <Feather name="info" size={18} color={colors.danger} />
            <Text style={styles.cancelledText}>Esta cita fue cancelada</Text>
          </View>
        )}
        <Button title="Volver" variant="secondary" onPress={() => navigation.goBack()} style={styles.volver} />
      </View>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.background,
  },
  scroll: {
    paddingHorizontal: spacing.xl,
    paddingBottom: spacing.xxl,
  },
  empty: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  emptyText: {
    color: colors.textMuted,
  },
  doctorCard: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: colors.card,
    borderRadius: radius.card,
    borderWidth: 1,
    borderColor: colors.border,
    padding: spacing.lg,
    marginBottom: spacing.lg,
    ...shadow.card,
  },
  doctorInfo: {
    flex: 1,
    marginLeft: spacing.md,
  },
  doctorName: {
    fontSize: 16,
    fontWeight: '700',
    color: colors.text,
  },
  doctorSpecialty: {
    fontSize: 13,
    color: colors.textMuted,
    marginTop: 2,
  },
  sectionTitle: {
    marginTop: spacing.lg,
    marginBottom: spacing.sm,
    fontSize: 15,
    fontWeight: '700',
    color: colors.text,
  },
  card: {
    backgroundColor: colors.card,
    borderRadius: radius.card,
    borderWidth: 1,
    borderColor: colors.border,
    padding: spacing.lg,
    ...shadow.card,
  },
  infoRow: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  infoIcon: {
    width: 40,
    height: 40,
    borderRadius: 20,
    backgroundColor: colors.primarySoft,
    alignItems: 'center',
    justifyContent: 'center',
  },
  infoText: {
    flex: 1,
    marginLeft: spacing.md,
  },
  infoLabel: {
    fontSize: 12,
    color: colors.textMuted,
  },
  infoValue: {
    fontSize: 14,
    fontWeight: '600',
    color: colors.text,
    marginTop: 1,
  },
  divider: {
    height: 1,
    backgroundColor: colors.border,
    marginVertical: spacing.md,
  },
  priceRow: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    marginTop: spacing.lg,
    padding: spacing.lg,
    borderRadius: radius.card,
    backgroundColor: colors.primarySoft,
  },
  priceLabel: {
    fontSize: 14,
    fontWeight: '600',
    color: colors.text,
  },
  price: {
    fontSize: 18,
    fontWeight: '800',
    color: colors.primaryDark,
  },
  footer: {
    padding: spacing.xl,
    gap: spacing.md,
    borderTopWidth: 1,
    borderTopColor: colors.border,
  },
  volver: {
    marginTop: 0,
  },
  cancelledBanner: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#FFDADA',
    borderRadius: radius.control,
    paddingVertical: spacing.md,
  },
  cancelledText: {
    marginLeft: spacing.sm,
    color: colors.danger,
    fontWeight: '600',
    fontSize: 13,
  },
});
