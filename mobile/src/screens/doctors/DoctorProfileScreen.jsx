import React, { useState } from 'react';
import { ScrollView, StyleSheet, Text, View } from 'react-native';
import { Feather } from '@expo/vector-icons';
import { SafeAreaView } from 'react-native-safe-area-context';
import Avatar from '../../components/Avatar';
import Button from '../../components/Button';
import Header from '../../components/Header';
import TimeChip from '../../components/TimeChip';
import { colors, radius, shadow, spacing } from '../../theme';
import { getDoctorById } from '../../data/doctors';
import { formatPrice } from '../../utils/date';

function chunk(items, size) {
  const rows = [];
  for (let i = 0; i < items.length; i += size) {
    rows.push(items.slice(i, i + size));
  }
  return rows;
}

export default function DoctorProfileScreen({ route, navigation }) {
  const doctor = getDoctorById(route.params.doctorId);
  const [favorite, setFavorite] = useState(false);
  const [selectedTime, setSelectedTime] = useState(null);

  if (!doctor) {
    return (
      <SafeAreaView style={styles.container} edges={['top']}>
        <Header title="Perfil del médico" onBack={() => navigation.goBack()} />
        <View style={styles.empty}>
          <Text style={styles.emptyText}>Médico no encontrado</Text>
        </View>
      </SafeAreaView>
    );
  }

  const timeRows = chunk(doctor.availableTimes, 4);

  return (
    <SafeAreaView style={styles.container} edges={['top']}>
      <Header
        title="Perfil del médico"
        onBack={() => navigation.goBack()}
        rightIcon="heart"
        rightIconColor={favorite ? colors.danger : colors.text}
        onRightPress={() => setFavorite((prev) => !prev)}
      />

      <ScrollView contentContainerStyle={styles.scroll} showsVerticalScrollIndicator={false}>
        <View style={styles.profile}>
          <Avatar size={96} />
          <Text style={styles.name}>{doctor.name}</Text>
          <Text style={styles.specialty}>{doctor.title}</Text>
          <View style={styles.ratingRow}>
            <Feather name="star" size={15} color={colors.star} />
            <Text style={styles.ratingText}>{doctor.rating.toFixed(1)}</Text>
            <Text style={styles.reviews}>· {doctor.reviews} reseñas</Text>
          </View>
        </View>

        <View style={styles.statsRow}>
          <View style={styles.statCard}>
            <Feather name="briefcase" size={20} color={colors.primary} />
            <Text style={styles.statValue}>{doctor.yearsExperience} años</Text>
            <Text style={styles.statLabel}>Experiencia</Text>
          </View>
          <View style={styles.statCard}>
            <Feather name="map-pin" size={20} color={colors.primary} />
            <Text style={styles.statValue} numberOfLines={1}>
              {doctor.clinic}
            </Text>
            <Text style={styles.statLabel}>Clínica</Text>
          </View>
        </View>

        <Text style={styles.sectionTitle}>Sobre el médico</Text>
        <Text style={styles.bio}>{doctor.bio}</Text>

        <Text style={styles.sectionTitle}>Horarios disponibles</Text>
        {timeRows.map((row, index) => (
          <View key={index} style={styles.timeRow}>
            {row.map((time) => (
              <TimeChip
                key={time}
                label={time}
                selected={selectedTime === time}
                onPress={() => setSelectedTime(time)}
                style={styles.timeChip}
              />
            ))}
          </View>
        ))}

        <View style={styles.priceRow}>
          <Text style={styles.priceLabel}>Consulta general</Text>
          <Text style={styles.price}>{formatPrice(80)}</Text>
        </View>
      </ScrollView>

      <View style={styles.footer}>
        <Button
          title="Agendar cita"
          icon="calendar-outline"
          onPress={() => navigation.navigate('BookAppointment', { doctorId: doctor.id })}
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
  profile: {
    alignItems: 'center',
    marginTop: spacing.md,
  },
  name: {
    marginTop: spacing.md,
    fontSize: 21,
    fontWeight: '700',
    color: colors.text,
  },
  specialty: {
    marginTop: 2,
    fontSize: 14,
    color: colors.textMuted,
  },
  ratingRow: {
    flexDirection: 'row',
    alignItems: 'center',
    marginTop: spacing.sm,
  },
  ratingText: {
    marginLeft: 4,
    fontSize: 14,
    fontWeight: '700',
    color: colors.text,
  },
  reviews: {
    marginLeft: 4,
    fontSize: 13,
    color: colors.textMuted,
  },
  statsRow: {
    flexDirection: 'row',
    gap: spacing.md,
    marginTop: spacing.xl,
  },
  statCard: {
    flex: 1,
    backgroundColor: colors.card,
    borderRadius: radius.card,
    borderWidth: 1,
    borderColor: colors.border,
    padding: spacing.lg,
    alignItems: 'center',
    ...shadow.card,
  },
  statValue: {
    marginTop: spacing.sm,
    fontSize: 14,
    fontWeight: '700',
    color: colors.text,
    textAlign: 'center',
  },
  statLabel: {
    marginTop: 2,
    fontSize: 12,
    color: colors.textMuted,
  },
  sectionTitle: {
    marginTop: spacing.xxl,
    marginBottom: spacing.md,
    fontSize: 17,
    fontWeight: '700',
    color: colors.text,
  },
  bio: {
    fontSize: 14,
    lineHeight: 22,
    color: colors.textMuted,
  },
  timeRow: {
    flexDirection: 'row',
    gap: spacing.sm,
    marginBottom: spacing.sm,
  },
  timeChip: {
    flex: 1,
  },
  priceRow: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    marginTop: spacing.xxl,
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
    borderTopWidth: 1,
    borderTopColor: colors.border,
    backgroundColor: colors.background,
  },
});
