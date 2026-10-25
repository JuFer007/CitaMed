import React, { useState } from 'react';
import { Pressable, StyleSheet, Text, View } from 'react-native';
import { Feather } from '@expo/vector-icons';
import { SafeAreaView } from 'react-native-safe-area-context';
import Button from '../../components/Button';
import { colors, radius, spacing } from '../../theme';

const slides = [
  {
    icon: 'search',
    title: 'Encuentra al especialista ideal',
    description: 'Busca médicos por especialidad de forma rápida y sencilla.',
  },
  {
    icon: 'calendar',
    title: 'Agenda tu cita en segundos',
    description: 'Elige fecha, hora y confirma tu cita cuando más te convenga.',
  },
  {
    icon: 'check-circle',
    title: 'Tu cita lista y confirmada',
    description: 'Recibe recordatorios para no perder ninguna cita.',
  },
];

export default function OnboardingScreen({ navigation }) {
  const [index, setIndex] = useState(0);
  const isLast = index === slides.length - 1;
  const slide = slides[index];

  const handleNext = () => {
    if (isLast) {
      navigation.replace('Login');
    } else {
      setIndex((prev) => prev + 1);
    }
  };

  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.header}>
        <Pressable onPress={() => navigation.replace('Login')} hitSlop={10}>
          <Text style={styles.skip}>Saltar</Text>
        </Pressable>
      </View>

      <View style={styles.content}>
        <View style={styles.illustration}>
          <View style={styles.blob} />
          <View style={styles.blobSmall} />
          <View style={styles.circle}>
            <Feather name={slide.icon} size={76} color={colors.primary} />
          </View>
        </View>

        <Text style={styles.title}>{slide.title}</Text>
        <Text style={styles.description}>{slide.description}</Text>
      </View>

      <View style={styles.footer}>
        <View style={styles.dots}>
          {slides.map((item, dotIndex) => (
            <View
              key={item.title}
              style={[styles.dot, dotIndex === index && styles.dotActive]}
            />
          ))}
        </View>
        <Button title={isLast ? 'Comenzar' : 'Siguiente'} onPress={handleNext} />
      </View>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.background,
  },
  header: {
    alignItems: 'flex-end',
    paddingHorizontal: spacing.xl,
    paddingTop: spacing.md,
  },
  skip: {
    fontSize: 14,
    fontWeight: '600',
    color: colors.textMuted,
  },
  content: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    paddingHorizontal: spacing.xxl,
  },
  illustration: {
    width: 220,
    height: 220,
    alignItems: 'center',
    justifyContent: 'center',
    marginBottom: spacing.xxxl,
  },
  blob: {
    position: 'absolute',
    width: 200,
    height: 200,
    borderRadius: 100,
    backgroundColor: colors.primarySoft,
  },
  blobSmall: {
    position: 'absolute',
    width: 96,
    height: 96,
    borderRadius: 48,
    backgroundColor: colors.primarySoft,
    top: -10,
    right: -6,
    opacity: 0.6,
  },
  circle: {
    width: 156,
    height: 156,
    borderRadius: 78,
    backgroundColor: colors.white,
    borderWidth: 1,
    borderColor: colors.border,
    alignItems: 'center',
    justifyContent: 'center',
    ...require('../../theme').shadow.subtle,
  },
  title: {
    fontSize: 25,
    fontWeight: '700',
    color: colors.text,
    textAlign: 'center',
    lineHeight: 32,
    paddingHorizontal: spacing.sm,
  },
  description: {
    marginTop: spacing.md,
    fontSize: 15,
    lineHeight: 24,
    color: colors.textMuted,
    textAlign: 'center',
    paddingHorizontal: spacing.md,
  },
  footer: {
    paddingHorizontal: spacing.xl,
    paddingBottom: spacing.xxl,
  },
  dots: {
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: spacing.xl,
    gap: 6,
  },
  dot: {
    width: 8,
    height: 8,
    borderRadius: 4,
    backgroundColor: colors.dotInactive,
  },
  dotActive: {
    width: 26,
    backgroundColor: colors.primary,
  },
});
