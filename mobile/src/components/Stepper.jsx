import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { colors, spacing } from '../theme';

export default function Stepper({ steps, current }) {
  return (
    <View style={styles.container}>
      {steps.map((step, index) => {
        const isDone = index < current;
        const isActive = index === current;
        const filled = isDone || isActive;
        return (
          <React.Fragment key={step}>
            <View style={styles.group}>
              <View style={[styles.circle, filled ? styles.circleFilled : styles.circleIdle]}>
                <Text style={[styles.number, filled && styles.numberFilled]}>{index + 1}</Text>
              </View>
              <Text style={[styles.label, filled && styles.labelActive]}>{step}</Text>
            </View>
            {index < steps.length - 1 ? (
              <View style={[styles.line, isDone && styles.lineDone]} />
            ) : null}
          </React.Fragment>
        );
      })}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: spacing.sm,
  },
  group: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  circle: {
    width: 26,
    height: 26,
    borderRadius: 13,
    alignItems: 'center',
    justifyContent: 'center',
    borderWidth: 1.5,
  },
  circleFilled: {
    backgroundColor: colors.primary,
    borderColor: colors.primary,
  },
  circleIdle: {
    backgroundColor: colors.white,
    borderColor: colors.border,
  },
  number: {
    fontSize: 12,
    fontWeight: '700',
    color: colors.textMuted,
  },
  numberFilled: {
    color: colors.white,
  },
  label: {
    marginLeft: 6,
    fontSize: 12,
    fontWeight: '500',
    color: colors.textMuted,
  },
  labelActive: {
    color: colors.primary,
    fontWeight: '700',
  },
  line: {
    flex: 1,
    height: 2,
    backgroundColor: colors.border,
    marginHorizontal: 8,
  },
  lineDone: {
    backgroundColor: colors.primary,
  },
});
