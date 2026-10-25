import React from 'react';
import { ActivityIndicator, Pressable, StyleSheet, Text } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { colors, radius, typography } from '../theme';

export default function Button({
  title,
  onPress,
  variant = 'primary',
  size = 'normal',
  icon,
  loading = false,
  disabled = false,
  style,
  fullWidth = true,
}) {
  const isPrimary = variant === 'primary';
  const isSecondary = variant === 'secondary';
  const isGhost = variant === 'ghost';
  const isDanger = variant === 'danger';
  const isSmall = size === 'small';
  const isDisabled = disabled || loading;

  let backgroundColor = colors.primary;
  let borderColor = colors.primary;
  let borderWidth = 0;
  let contentColor = colors.white;

  if (isSecondary) {
    backgroundColor = colors.white;
    borderColor = colors.border;
    borderWidth = 1;
    contentColor = colors.text;
  } else if (isGhost) {
    backgroundColor = 'transparent';
    borderColor = 'transparent';
    borderWidth = 0;
    contentColor = colors.primary;
  } else if (isDanger) {
    backgroundColor = colors.danger;
    borderColor = colors.danger;
    borderWidth = 0;
    contentColor = colors.white;
  }

  const rippleColor = isPrimary
    ? colors.primaryDark
    : isSecondary
    ? colors.primarySoft
    : isGhost
    ? colors.primarySoft
    : colors.danger;

  return (
    <Pressable
      onPress={onPress}
      disabled={isDisabled}
      android_ripple={{ color: rippleColor, borderless: false }}
      style={({ pressed }) => [
        styles.base,
        isSmall ? styles.small : styles.normal,
        { backgroundColor, borderColor, borderWidth },
        pressed && !isDisabled && styles.pressed,
        isDisabled && styles.disabled,
        !fullWidth && styles.inline,
        style,
      ]}
    >
      {loading ? (
        <ActivityIndicator color={contentColor} size="small" />
      ) : (
        <>
          {icon ? (
            <Ionicons
              name={icon}
              size={isSmall ? 16 : 18}
              color={contentColor}
              style={styles.icon}
            />
          ) : null}
          <Text style={[typography.buttonText, isSmall && styles.textSmall, { color: contentColor }]}>
            {title}
          </Text>
        </>
      )}
    </Pressable>
  );
}

const styles = StyleSheet.create({
  base: {
    borderRadius: radius.button,
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
    alignSelf: 'stretch',
  },
  normal: {
    height: 54,
    paddingHorizontal: 24,
  },
  small: {
    height: 40,
    paddingHorizontal: 18,
    borderRadius: radius.sm,
  },
  pressed: {
    opacity: 0.94,
    transform: [{ scale: 0.995 }],
  },
  disabled: {
    opacity: 0.5,
  },
  inline: {
    alignSelf: 'flex-start',
  },
  icon: {
    marginRight: 8,
  },
  textSmall: {
    fontSize: 13,
  },
});
