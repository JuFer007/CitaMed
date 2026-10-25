import React, { useState } from 'react';
import { Pressable, StyleSheet, Text, TextInput, View } from 'react-native';
import { Feather } from '@expo/vector-icons';
import { colors, radius, spacing } from '../theme';

export default function Input({
  label,
  icon,
  secureTextEntry,
  error,
  containerStyle,
  inputStyle,
  onFocus,
  onBlur,
  multiline,
  ...rest
}) {
  const [hidden, setHidden] = useState(!!secureTextEntry);
  const [focused, setFocused] = useState(false);

  return (
    <View style={[styles.wrapper, containerStyle]}>
      {label ? <Text style={styles.label}>{label}</Text> : null}
      <View
        style={[
          styles.inputRow,
          multiline && styles.multilineRow,
          focused && styles.focused,
          !!error && styles.errorBorder,
        ]}
      >
        {icon ? (
          <Feather name={icon} size={20} color={focused ? colors.primary : colors.inputIcon} style={styles.leftIcon} />
        ) : null}
        <TextInput
          {...rest}
          multiline={multiline}
          style={[styles.input, multiline && styles.multilineInput, inputStyle]}
          placeholderTextColor={colors.placeholder}
          secureTextEntry={secureTextEntry ? hidden : false}
          onFocus={(event) => {
            setFocused(true);
            onFocus?.(event);
          }}
          onBlur={(event) => {
            setFocused(false);
            onBlur?.(event);
          }}
        />
        {secureTextEntry ? (
          <Pressable onPress={() => setHidden((prev) => !prev)} hitSlop={10}>
            <Feather
              name={hidden ? 'eye-off' : 'eye'}
              size={20}
              color={colors.inputIcon}
            />
          </Pressable>
        ) : null}
      </View>
      {error ? <Text style={styles.error}>{error}</Text> : null}
    </View>
  );
}

const styles = StyleSheet.create({
  wrapper: {
    marginBottom: spacing.lg,
  },
  label: {
    fontSize: 13,
    fontWeight: '600',
    color: colors.text,
    marginBottom: spacing.sm,
  },
  inputRow: {
    flexDirection: 'row',
    alignItems: 'center',
    height: 54,
    borderRadius: radius.control,
    borderWidth: 1,
    borderColor: colors.border,
    backgroundColor: colors.white,
    paddingHorizontal: spacing.xl,
  },
  multilineRow: {
    height: 90,
    alignItems: 'flex-start',
    paddingVertical: spacing.md,
  },
  focused: {
    borderColor: colors.primary,
    backgroundColor: '#FAFDFD',
  },
  errorBorder: {
    borderColor: colors.danger,
  },
  leftIcon: {
    marginRight: spacing.sm,
  },
  input: {
    flex: 1,
    fontSize: 14,
    color: colors.text,
    paddingVertical: 0,
  },
  multilineInput: {
    height: '100%',
    textAlignVertical: 'top',
    paddingVertical: 4,
  },
  error: {
    marginTop: spacing.xs,
    fontSize: 12,
    color: colors.danger,
  },
});
