import React, { useMemo, useState } from 'react';
import {
  KeyboardAvoidingView,
  Platform,
  ScrollView,
  StyleSheet,
  Text,
  TextInput,
  View,
} from 'react-native';
import { Feather } from '@expo/vector-icons';
import { SafeAreaView } from 'react-native-safe-area-context';
import { Calendar, LocaleConfig } from 'react-native-calendars';
import Button from '../../components/Button';
import Header from '../../components/Header';
import Stepper from '../../components/Stepper';
import Avatar from '../../components/Avatar';
import TimeChip from '../../components/TimeChip';
import { colors, radius, shadow, spacing } from '../../theme';
import { getDoctorById } from '../../data/doctors';
import { useAppointments } from '../../context/AppointmentsContext';
import { formatDayAndWeekday, formatPrice, formatTime12h, todayISO } from '../../utils/date';

LocaleConfig.locales.es = {
  monthNames: [
    'Enero',
    'Febrero',
    'Marzo',
    'Abril',
    'Mayo',
    'Junio',
    'Julio',
    'Agosto',
    'Septiembre',
    'Octubre',
    'Noviembre',
    'Diciembre',
  ],
  monthNamesShort: [
    'Ene',
    'Feb',
    'Mar',
    'Abr',
    'May',
    'Jun',
    'Jul',
    'Ago',
    'Sep',
    'Oct',
    'Nov',
    'Dic',
  ],
  dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
  dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mié', 'Jue', 'Vie', 'Sáb'],
  today: 'Hoy',
};
LocaleConfig.defaultLocale = 'es';

const steps = ['Fecha', 'Hora', 'Motivo'];
const PRICE = 80;

function chunk(items, size) {
  const rows = [];
  for (let i = 0; i < items.length; i += size) {
    rows.push(items.slice(i, i + size));
  }
  return rows;
}

export default function BookAppointmentScreen({ route, navigation }) {
  const doctor = getDoctorById(route.params.doctorId);
  const { addAppointment } = useAppointments();
  const [step, setStep] = useState(0);
  const [date, setDate] = useState(null);
  const [time, setTime] = useState(null);
  const [reason, setReason] = useState('');

  const markedDates = useMemo(() => {
    if (!date) return {};
    return {
      [date]: {
        selected: true,
        selectedColor: colors.primary,
        selectedTextColor: colors.white,
      },
    };
  }, [date]);

  if (!doctor) {
    return (
      <SafeAreaView style={styles.container} edges={['top']}>
        <Header title="Agendar cita" onBack={() => navigation.goBack()} />
        <View style={styles.empty}>
          <Text style={styles.emptyText}>Médico no encontrado</Text>
        </View>
      </SafeAreaView>
    );
  }

  const canContinue =
    (step === 0 && !!date) || (step === 1 && !!time) || (step === 2 && reason.trim().length > 0);

  const handleBack = () => {
    if (step === 0) navigation.goBack();
    else setStep((prev) => prev - 1);
  };

  const handleConfirm = () => {
    if (!date || !time) return;
    const appointment = addAppointment({
      doctorId: doctor.id,
      doctorName: doctor.name,
      specialty: doctor.specialty,
      clinic: doctor.clinic,
      address: doctor.address,
      phone: doctor.phone,
      date,
      time,
      reason: reason.trim(),
      price: PRICE,
    });
    navigation.replace('AppointmentConfirmation', { appointmentId: appointment.id });
  };

  const timeRows = chunk(doctor.availableTimes, 3);

  const renderStep = () => {
    if (step === 0) {
      return (
        <View style={styles.calendarWrapper}>
          <Calendar
            current={date ?? todayISO()}
            minDate={todayISO()}
            onDayPress={(day) => setDate(day.dateString)}
            markedDates={markedDates}
            firstDay={1}
            enableSwipeMonths
            theme={{
              backgroundColor: colors.background,
              calendarBackground: colors.card,
              textSectionTitleColor: colors.textMuted,
              selectedDayBackgroundColor: colors.primary,
              selectedDayTextColor: colors.white,
              todayTextColor: colors.primary,
              dayTextColor: colors.text,
              textDisabledColor: colors.dotInactive,
              arrowColor: colors.primary,
              monthTextColor: colors.text,
              textMonthFontWeight: '700',
              textDayFontWeight: '500',
              textDayHeaderFontWeight: '600',
            }}
            style={styles.calendar}
          />
        </View>
      );
    }

    if (step === 1) {
      return (
        <View>
          <Text style={styles.stepHint}>Horarios disponibles</Text>
          {timeRows.map((row, index) => (
            <View key={index} style={styles.timeRow}>
              {row.map((item) => (
                <TimeChip
                  key={item}
                  label={item}
                  selected={time === item}
                  onPress={() => setTime(item)}
                  style={styles.timeChip}
                />
              ))}
            </View>
          ))}
        </View>
      );
    }

    return (
      <View>
        <Text style={styles.stepHint}>Motivo de la cita</Text>
        <TextInput
          style={styles.textarea}
          placeholder="Describe brevemente el motivo de tu consulta..."
          placeholderTextColor={colors.placeholder}
          multiline
          textAlignVertical="top"
          value={reason}
          onChangeText={setReason}
        />

        <View style={styles.summary}>
          <Text style={styles.summaryTitle}>Resumen de la cita</Text>
          <View style={styles.summaryRow}>
            <Text style={styles.summaryLabel}>Servicio</Text>
            <Text style={styles.summaryValue}>Consulta general</Text>
          </View>
          <View style={styles.summaryRow}>
            <Text style={styles.summaryLabel}>Médico</Text>
            <Text style={styles.summaryValue}>{doctor.name}</Text>
          </View>
          {date ? (
            <View style={styles.summaryRow}>
              <Text style={styles.summaryLabel}>Fecha</Text>
              <Text style={styles.summaryValue}>{formatDayAndWeekday(date)}</Text>
            </View>
          ) : null}
          {time ? (
            <View style={styles.summaryRow}>
              <Text style={styles.summaryLabel}>Hora</Text>
              <Text style={styles.summaryValue}>{formatTime12h(time)}</Text>
            </View>
          ) : null}
          <View style={[styles.summaryRow, styles.summaryTotal]}>
            <Text style={styles.summaryTotalLabel}>Total</Text>
            <Text style={styles.summaryTotalValue}>{formatPrice(PRICE)}</Text>
          </View>
        </View>
      </View>
    );
  };

  return (
    <SafeAreaView style={styles.container} edges={['top']}>
      <Header title="Agendar cita" onBack={handleBack} />

      <View style={styles.stepperWrapper}>
        <Stepper steps={steps} current={step} />
      </View>

      <KeyboardAvoidingView
        style={styles.flex}
        behavior={Platform.OS === 'ios' ? 'padding' : undefined}
      >
        <ScrollView
          contentContainerStyle={styles.scroll}
          keyboardShouldPersistTaps="handled"
          showsVerticalScrollIndicator={false}
        >
          <View style={styles.doctorRow}>
            <Avatar size={44} />
            <View style={styles.doctorInfo}>
              <Text style={styles.doctorName}>{doctor.name}</Text>
              <Text style={styles.doctorSpecialty}>{doctor.title}</Text>
            </View>
          </View>

          {renderStep()}
        </ScrollView>

        <View style={styles.footer}>
          <Button
            title={step === 2 ? 'Confirmar cita' : 'Siguiente'}
            onPress={() => {
              if (step === 2) handleConfirm();
              else setStep((prev) => prev + 1);
            }}
            disabled={!canContinue}
          />
        </View>
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.background,
  },
  flex: {
    flex: 1,
  },
  empty: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  emptyText: {
    color: colors.textMuted,
  },
  stepperWrapper: {
    paddingVertical: spacing.md,
  },
  scroll: {
    paddingHorizontal: spacing.xl,
    paddingBottom: spacing.xxl,
  },
  doctorRow: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: colors.white,
    borderRadius: radius.card,
    borderWidth: 1,
    borderColor: colors.border,
    padding: spacing.md,
    marginBottom: spacing.xl,
    ...shadow.card,
  },
  doctorInfo: {
    marginLeft: spacing.md,
  },
  doctorName: {
    fontSize: 15,
    fontWeight: '700',
    color: colors.text,
  },
  doctorSpecialty: {
    fontSize: 13,
    color: colors.textMuted,
  },
  calendarWrapper: {
    borderRadius: radius.card,
    overflow: 'hidden',
    borderWidth: 1,
    borderColor: colors.border,
  },
  calendar: {
    paddingBottom: spacing.sm,
  },
  stepHint: {
    fontSize: 14,
    fontWeight: '600',
    color: colors.text,
    marginBottom: spacing.md,
  },
  timeRow: {
    flexDirection: 'row',
    gap: spacing.sm,
    marginBottom: spacing.sm,
  },
  timeChip: {
    flex: 1,
  },
  textarea: {
    height: 120,
    borderRadius: radius.control,
    borderWidth: 1,
    borderColor: colors.border,
    backgroundColor: colors.white,
    padding: spacing.md,
    fontSize: 14,
    color: colors.text,
    marginBottom: spacing.xl,
  },
  summary: {
    backgroundColor: colors.card,
    borderRadius: radius.card,
    borderWidth: 1,
    borderColor: colors.border,
    padding: spacing.lg,
    ...shadow.card,
  },
  summaryTitle: {
    fontSize: 15,
    fontWeight: '700',
    color: colors.text,
    marginBottom: spacing.md,
  },
  summaryRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: spacing.sm,
  },
  summaryLabel: {
    fontSize: 13,
    color: colors.textMuted,
  },
  summaryValue: {
    fontSize: 13,
    fontWeight: '600',
    color: colors.text,
    flexShrink: 1,
    textAlign: 'right',
    marginLeft: spacing.md,
  },
  summaryTotal: {
    marginTop: spacing.sm,
    paddingTop: spacing.md,
    borderTopWidth: 1,
    borderTopColor: colors.border,
    marginBottom: 0,
  },
  summaryTotalLabel: {
    fontSize: 15,
    fontWeight: '700',
    color: colors.text,
  },
  summaryTotalValue: {
    fontSize: 16,
    fontWeight: '800',
    color: colors.primaryDark,
  },
  footer: {
    padding: spacing.xl,
    borderTopWidth: 1,
    borderTopColor: colors.border,
  },
});
