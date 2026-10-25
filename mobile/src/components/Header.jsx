import React from 'react';
import { Pressable, StyleSheet, Text, View } from 'react-native';
import { Feather } from '@expo/vector-icons';
import { colors, spacing } from '../theme';

export default function Header({
  title,
  onBack,
  rightIcon,
  onRightPress,
  rightIconColor = colors.text,
}) {
  return (
    <View style={styles.container}>
      <View style={styles.side}>
        {onBack ? (
          <Pressable onPress={onBack} hitSlop={12} style={styles.iconButton}>
            <Feather name="chevron-left" size={26} color={colors.text} />
          </Pressable>
        ) : null}
      </View>
      <Text style={styles.title} numberOfLines={1}>
        {title}
      </Text>
      <View style={[styles.side, styles.right]}>
        {rightIcon ? (
          <Pressable onPress={onRightPress} hitSlop={12} style={styles.iconButton}>
            <Feather name={rightIcon} size={21} color={rightIconColor} />
          </Pressable>
        ) : null}
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    alignItems: 'center',
    height: 60,
    paddingHorizontal: spacing.lg,
  },
  side: {
    width: 48,
  },
  right: {
    alignItems: 'flex-end',
  },
  iconButton: {
    width: 48,
    height: 48,
    alignItems: 'center',
    justifyContent: 'center',
    borderRadius: 12,
  },
  title: {
    flex: 1,
    textAlign: 'center',
    fontSize: 18,
    fontWeight: '700',
    color: colors.text,
    paddingHorizontal: 4,
  },
});
