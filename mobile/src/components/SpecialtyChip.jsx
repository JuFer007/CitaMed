import React from 'react';
import { Pressable, StyleSheet, Text, View } from 'react-native';
import { Feather } from '@expo/vector-icons';
import { colors, shadow, spacing } from '../theme';

export default function SpecialtyChip({
  label,
  icon,
  selected = false,
  onPress,
}) {
  return (
    <Pressable
      onPress={onPress}
      style={({ pressed }) => [styles.container, pressed && styles.pressed]}
    >
      <View style={[styles.circle, selected && styles.circleSelected]}>
        <Feather
          name={icon}
          size={24}
          color={selected ? colors.white : colors.primary}
        />
      </View>
      <Text style={styles.label} numberOfLines={2}>
        {label}
      </Text>
    </Pressable>
  );
}

const styles = StyleSheet.create({
  container: {
    width: 82,
    alignItems: 'center',
  },
  pressed: {
    opacity: 0.85,
    transform: [{ scale: 0.97 }],
  },
  circle: {
    width: 60,
    height: 60,
    borderRadius: 30,
    backgroundColor: colors.primarySoft,
    borderWidth: 1,
    borderColor: colors.border,
    alignItems: 'center',
    justifyContent: 'center',
  },
  circleSelected: {
    backgroundColor: colors.primary,
    borderColor: colors.primary,
    ...shadow.subtle,
  },
  label: {
    marginTop: spacing.sm,
    fontSize: 12,
    fontWeight: '600',
    lineHeight: 16,
    color: colors.text,
    textAlign: 'center',
    paddingHorizontal: 2,
  },
});
