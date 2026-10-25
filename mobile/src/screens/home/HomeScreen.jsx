import React, { useMemo, useState } from 'react';
import { Pressable, ScrollView, StyleSheet, Text, TextInput, View } from 'react-native';
import { Feather } from '@expo/vector-icons';
import { SafeAreaView } from 'react-native-safe-area-context';
import DoctorCard from '../../components/DoctorCard';
import SpecialtyChip from '../../components/SpecialtyChip';
import { colors, radius, spacing } from '../../theme';
import { specialties } from '../../data/specialties';
import { doctors } from '../../data/doctors';
import { useAuth } from '../../context/AuthContext';

export default function HomeScreen({ navigation }) {
  const { user } = useAuth();
  const [query, setQuery] = useState('');

  const firstName = (user?.name ?? 'Paciente').split(' ')[0];
  const featured = useMemo(() => doctors.slice(0, 4), []);

  const filtered = useMemo(() => {
    const term = query.trim().toLowerCase();
    if (!term) return featured;
    return doctors.filter(
      (doctor) =>
        doctor.name.toLowerCase().includes(term) ||
        doctor.specialty.toLowerCase().includes(term),
    );
  }, [query, featured]);

  return (
    <SafeAreaView style={styles.container} edges={['top']}>
      <ScrollView
        contentContainerStyle={styles.scroll}
        showsVerticalScrollIndicator={false}
        keyboardShouldPersistTaps="handled"
      >
        <View style={styles.header}>
          <View style={styles.headerText}>
            <Text style={styles.greeting}>Hola, {firstName}</Text>
            <Text style={styles.subGreeting}>¿En qué te podemos ayudar hoy?</Text>
          </View>
          <Pressable
            style={styles.bell}
            onPress={() => navigation.navigate('Tabs', { screen: 'NotificationsTab' })}
          >
            <Feather name="bell" size={20} color={colors.text} />
            <View style={styles.bellDot} />
          </Pressable>
        </View>

        <View style={styles.search}>
          <Feather name="search" size={18} color={colors.inputIcon} />
          <TextInput
            placeholder="Buscar especialidad, médico o síntoma"
            placeholderTextColor={colors.placeholder}
            style={styles.searchInput}
            value={query}
            onChangeText={setQuery}
            returnKeyType="search"
          />
        </View>

        <View style={styles.sectionHeader}>
          <Text style={styles.sectionTitle}>Especialidades</Text>
          <Pressable onPress={() => navigation.navigate('DoctorsList', {})}>
            <Text style={styles.link}>Ver todo</Text>
          </Pressable>
        </View>

        <ScrollView
          horizontal
          showsHorizontalScrollIndicator={false}
          style={styles.specialtiesScroll}
          contentContainerStyle={styles.specialties}
        >
          {specialties.map((specialty) => (
            <SpecialtyChip
              key={specialty.id}
              label={specialty.name}
              icon={specialty.icon}
              onPress={() => navigation.navigate('DoctorsList', { specialty: specialty.name })}
            />
          ))}
        </ScrollView>

        <View style={styles.sectionHeader}>
          <Text style={styles.sectionTitle}>Médicos destacados</Text>
          <Pressable onPress={() => navigation.navigate('DoctorsList', {})}>
            <Text style={styles.link}>Ver todo</Text>
          </Pressable>
        </View>

        {filtered.map((doctor) => (
          <DoctorCard
            key={doctor.id}
            doctor={doctor}
            variant="featured"
            onPress={() => navigation.navigate('DoctorProfile', { doctorId: doctor.id })}
          />
        ))}

        {filtered.length === 0 ? (
          <View style={styles.empty}>
            <Feather name="search" size={36} color={colors.textMuted} />
            <Text style={styles.emptyText}>No encontramos resultados</Text>
          </View>
        ) : null}
      </ScrollView>
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
  header: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    marginTop: spacing.md,
    marginBottom: spacing.xl,
  },
  headerText: {
    flex: 1,
  },
  greeting: {
    fontSize: 24,
    fontWeight: '700',
    color: colors.text,
  },
  subGreeting: {
    marginTop: 4,
    fontSize: 14,
    color: colors.textMuted,
  },
  bell: {
    width: 48,
    height: 48,
    borderRadius: 16,
    backgroundColor: colors.white,
    borderWidth: 1,
    borderColor: colors.border,
    alignItems: 'center',
    justifyContent: 'center',
    ...require('../../theme').shadow.subtle,
  },
  bellDot: {
    position: 'absolute',
    top: 12,
    right: 13,
    width: 9,
    height: 9,
    borderRadius: 5,
    backgroundColor: colors.danger,
    borderWidth: 1.5,
    borderColor: colors.white,
  },
  search: {
    flexDirection: 'row',
    alignItems: 'center',
    height: 54,
    borderRadius: radius.control,
    backgroundColor: colors.white,
    borderWidth: 1,
    borderColor: colors.border,
    paddingHorizontal: spacing.lg,
    ...require('../../theme').shadow.subtle,
  },
  searchInput: {
    flex: 1,
    marginLeft: spacing.sm,
    fontSize: 14,
    color: colors.text,
  },
  sectionHeader: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    marginTop: spacing.xxl,
    marginBottom: spacing.lg,
  },
  sectionTitle: {
    fontSize: 18,
    fontWeight: '700',
    color: colors.text,
  },
  link: {
    fontSize: 13,
    fontWeight: '700',
    color: colors.primary,
  },
  specialtiesScroll: {
    marginHorizontal: -spacing.xl,
  },
  specialties: {
    flexDirection: 'row',
    gap: spacing.lg,
    paddingHorizontal: spacing.xl,
  },
  empty: {
    alignItems: 'center',
    paddingVertical: spacing.xl4,
  },
  emptyText: {
    marginTop: spacing.md,
    color: colors.textMuted,
    fontSize: 14,
  },
});
