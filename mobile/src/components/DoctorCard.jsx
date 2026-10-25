import React from 'react';
import { Pressable, StyleSheet, Text, View } from 'react-native';
import { Feather } from '@expo/vector-icons';
import Avatar from './Avatar';
import { colors, radius, shadow, spacing, statusColors } from '../theme';

const statusOkBg = statusColors.confirmed.bg;
const statusOkText = statusColors.confirmed.text;

export default function DoctorCard({
  doctor,
  variant = 'featured',
  onPress,
  onPressAction,
}) {
  if (variant === 'featured') {
    return (
      <Pressable
        onPress={onPress}
        style={({ pressed }) => [styles.card, styles.featured, pressed && styles.pressed]}
      >
        <View style={styles.avatarRing}>
          <Avatar size={50} />
        </View>
        <View style={styles.featuredInfo}>
          <Text style={styles.name}>{doctor.name}</Text>
          <Text style={styles.specialty}>{doctor.specialty}</Text>
          <View style={styles.ratingRow}>
            <Feather name="star" size={13} color={colors.star} />
            <Text style={styles.ratingText}>
              {doctor.rating.toFixed(1)} ({doctor.reviews})
            </Text>
          </View>
        </View>
        <Feather name="chevron-right" size={20} color={colors.textTertiary} />
      </Pressable>
    );
  }

  return (
    <View style={[styles.card, styles.list]}>
      <View style={styles.listHeader}>
        <View style={styles.avatarRing}>
          <Avatar size={54} />
        </View>
        <View style={styles.listInfo}>
          <Text style={styles.name}>{doctor.name}</Text>
          <Text style={styles.specialty}>
            {doctor.title} · {doctor.yearsExperience} años exp.
          </Text>
          <View style={styles.ratingRow}>
            <Feather name="star" size={13} color={colors.star} />
            <Text style={styles.ratingText}>
              {doctor.rating.toFixed(1)} ({doctor.reviews})
            </Text>
          </View>
        </View>
        <View style={styles.availableBadge}>
          <View style={styles.availableDot} />
          <Text style={styles.availableText}>Disponible</Text>
        </View>
      </View>

      <View style={styles.locationRow}>
        <Feather name="map-pin" size={13} color={colors.primary} />
        <Text style={styles.locationText}>
          {doctor.location}, {doctor.clinic}
        </Text>
      </View>

      <Pressable
        onPress={onPressAction}
        style={({ pressed }) => [styles.actionButton, pressed && styles.pressed]}
      >
        <Text style={styles.actionText}>Ver perfil</Text>
        <Feather name="arrow-right" size={15} color={colors.white} />
      </Pressable>
    </View>
  );
}

const styles = StyleSheet.create({
  card: {
    backgroundColor: colors.card,
    borderRadius: radius.card,
    borderWidth: 1,
    borderColor: colors.border,
    marginBottom: spacing.xl,
    ...shadow.subtle,
  },
  featured: {
    flexDirection: 'row',
    alignItems: 'center',
    padding: spacing.lg,
  },
  list: {
    padding: spacing.xl,
  },
  listHeader: {
    flexDirection: 'row',
    alignItems: 'flex-start',
  },
  avatarRing: {
    padding: 2,
    borderRadius: 999,
    borderWidth: 2,
    borderColor: colors.primarySoft,
  },
  featuredInfo: {
    flex: 1,
    marginLeft: spacing.md,
  },
  listInfo: {
    flex: 1,
    marginLeft: spacing.md,
  },
  name: {
    fontSize: 15,
    fontWeight: '700',
    color: colors.text,
    lineHeight: 20,
  },
  specialty: {
    fontSize: 12,
    color: colors.textMuted,
    marginTop: 4,
    lineHeight: 18,
  },
  ratingRow: {
    flexDirection: 'row',
    alignItems: 'center',
    marginTop: 6,
  },
  ratingText: {
    marginLeft: 4,
    fontSize: 12,
    fontWeight: '600',
    color: colors.text,
  },
  availableBadge: {
    flexDirection: 'row',
    alignItems: 'center',
    alignSelf: 'flex-start',
    backgroundColor: statusOkBg,
    paddingHorizontal: spacing.sm,
    paddingVertical: 4,
    borderRadius: radius.pill,
  },
  availableDot: {
    width: 6,
    height: 6,
    borderRadius: 3,
    backgroundColor: statusOkText,
    marginRight: 5,
  },
  availableText: {
    fontSize: 10,
    fontWeight: '700',
    color: statusOkText,
  },
  locationRow: {
    flexDirection: 'row',
    alignItems: 'center',
    marginTop: spacing.md,
  },
  locationText: {
    marginLeft: 6,
    fontSize: 12,
    color: colors.textMuted,
    flexShrink: 1,
  },
  actionButton: {
    marginTop: spacing.lg,
    height: 44,
    borderRadius: radius.button,
    backgroundColor: colors.primary,
    alignItems: 'center',
    justifyContent: 'center',
    flexDirection: 'row',
    gap: 6,
  },
  pressed: {
    opacity: 0.94,
    transform: [{ scale: 0.995 }],
  },
  actionText: {
    color: colors.white,
    fontWeight: '600',
    fontSize: 13,
  },
});
