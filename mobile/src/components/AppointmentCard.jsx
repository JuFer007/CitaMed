import React from 'react';
import { Pressable, StyleSheet, Text, View } from 'react-native';
import { Feather } from '@expo/vector-icons';
import Avatar from './Avatar';
import StatusBadge from './StatusBadge';
import { colors, radius, shadow, spacing } from '../theme';
import { formatShortDate, formatTime12h } from '../utils/date';

export default function AppointmentCard({ appointment, onPress }) {
  return (
    <View style={[styles.card]}>
      <View style={styles.top}>
        <View style={styles.avatarRing}>
          <Avatar size={44} />
        </View>
        <View style={styles.info}>
          <Text style={styles.name}>{appointment.doctorName}</Text>
          <Text style={styles.specialty}>{appointment.specialty}</Text>
        </View>
        <StatusBadge status={appointment.status} />
      </View>

      <View style={styles.dateRow}>
        <View style={styles.dateIcon}>
          <Feather name="clock" size={14} color={colors.primary} />
        </View>
        <Text style={styles.dateText}>
          {formatShortDate(appointment.date)} · {formatTime12h(appointment.time)}
        </Text>
      </View>

      {onPress ? (
        <Pressable
          onPress={onPress}
          style={({ pressed }) => [styles.action, pressed && styles.pressed]}
        >
          <Text style={styles.actionText}>Ver detalle</Text>
          <Feather name="chevron-right" size={16} color={colors.primary} />
        </Pressable>
      ) : null}
    </View>
  );
}

const styles = StyleSheet.create({
  card: {
    backgroundColor: colors.card,
    borderRadius: radius.card,
    borderWidth: 1,
    borderColor: colors.border,
    padding: spacing.xl,
    marginBottom: spacing.xl,
    ...shadow.subtle,
  },
  top: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  avatarRing: {
    padding: 2,
    borderRadius: 999,
    borderWidth: 2,
    borderColor: colors.primarySoft,
  },
  info: {
    flex: 1,
    marginLeft: spacing.md,
  },
  name: {
    fontSize: 15,
    fontWeight: '700',
    color: colors.text,
  },
  specialty: {
    fontSize: 12,
    color: colors.textMuted,
    marginTop: 3,
  },
  dateRow: {
    flexDirection: 'row',
    alignItems: 'center',
    marginTop: spacing.lg,
    paddingTop: spacing.lg,
    borderTopWidth: 1,
    borderTopColor: colors.border,
  },
  dateIcon: {
    width: 28,
    height: 28,
    borderRadius: 14,
    backgroundColor: colors.primarySoft,
    alignItems: 'center',
    justifyContent: 'center',
  },
  dateText: {
    marginLeft: spacing.sm,
    fontSize: 13,
    color: colors.text,
    fontWeight: '600',
  },
  action: {
    marginTop: spacing.lg,
    height: 44,
    borderRadius: radius.button,
    backgroundColor: colors.primarySoft,
    alignItems: 'center',
    justifyContent: 'center',
    flexDirection: 'row',
    gap: 4,
  },
  pressed: {
    opacity: 0.94,
    transform: [{ scale: 0.995 }],
  },
  actionText: {
    color: colors.primary,
    fontWeight: '600',
    fontSize: 13,
  },
});
