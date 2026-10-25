import React, { useMemo, useState } from 'react';
import { FlatList, Modal, Pressable, StyleSheet, Text, View } from 'react-native';
import { Feather } from '@expo/vector-icons';
import { SafeAreaView } from 'react-native-safe-area-context';
import Header from '../../components/Header';
import DoctorCard from '../../components/DoctorCard';
import { colors, radius, spacing } from '../../theme';
import { doctors } from '../../data/doctors';
import { specialties } from '../../data/specialties';

const sortOptions = ['Mejor valorados', 'Más cercanos', 'Más experiencia', 'Nombre (A-Z)'];

export default function DoctorsListScreen({ route, navigation }) {
  const initialSpecialty = route.params?.specialty ?? 'Todas';
  const [specialty, setSpecialty] = useState(initialSpecialty);
  const [sort, setSort] = useState('Mejor valorados');
  const [openMenu, setOpenMenu] = useState(null);

  const specialtyOptions = ['Todas', ...specialties.map((item) => item.name)];

  const filtered = useMemo(() => {
    const list = doctors.filter(
      (doctor) => specialty === 'Todas' || doctor.specialty === specialty,
    );
    const sorted = [...list];
    if (sort === 'Mejor valorados') sorted.sort((a, b) => b.rating - a.rating);
    if (sort === 'Más experiencia') sorted.sort((a, b) => b.yearsExperience - a.yearsExperience);
    if (sort === 'Nombre (A-Z)') sorted.sort((a, b) => a.name.localeCompare(b.name));
    return sorted;
  }, [specialty, sort]);

  const renderDropdown = (
    kind,
    options,
    selected,
    onSelect,
  ) => (
    <Modal visible={openMenu === kind} transparent animationType="fade">
      <Pressable style={styles.backdrop} onPress={() => setOpenMenu(null)}>
        <View style={styles.menu}>
          <Text style={styles.menuTitle}>
            {kind === 'specialty' ? 'Especialidad' : 'Ordenar por'}
          </Text>
          {options.map((option) => (
            <Pressable
              key={option}
              style={styles.menuItem}
              onPress={() => {
                onSelect(option);
                setOpenMenu(null);
              }}
            >
              <Text
                style={[styles.menuItemText, option === selected && styles.menuItemSelected]}
              >
                {option}
              </Text>
              {option === selected ? (
                <Feather name="check" size={18} color={colors.primary} />
              ) : null}
            </Pressable>
          ))}
        </View>
      </Pressable>
    </Modal>
  );

  return (
    <SafeAreaView style={styles.container} edges={['top']}>
      <Header
        title={specialty === 'Todas' ? 'Médicos' : specialty}
        onBack={() => navigation.goBack()}
        rightIcon="sliders"
        onRightPress={() => setOpenMenu('sort')}
      />

      <View style={styles.dropdowns}>
        <Pressable style={styles.dropdown} onPress={() => setOpenMenu('specialty')}>
          <Text style={styles.dropdownLabel}>Especialidad</Text>
          <View style={styles.dropdownValue}>
            <Text style={styles.dropdownText} numberOfLines={1}>
              {specialty}
            </Text>
            <Feather name="chevron-down" size={16} color={colors.textMuted} />
          </View>
        </Pressable>

        <Pressable style={styles.dropdown} onPress={() => setOpenMenu('sort')}>
          <Text style={styles.dropdownLabel}>Ordenar</Text>
          <View style={styles.dropdownValue}>
            <Text style={styles.dropdownText} numberOfLines={1}>
              {sort}
            </Text>
            <Feather name="chevron-down" size={16} color={colors.textMuted} />
          </View>
        </Pressable>
      </View>

      <FlatList
        data={filtered}
        keyExtractor={(item) => item.id}
        contentContainerStyle={styles.list}
        showsVerticalScrollIndicator={false}
        ListEmptyComponent={
          <View style={styles.empty}>
            <Feather name="user" size={36} color={colors.textMuted} />
            <Text style={styles.emptyText}>No hay médicos disponibles</Text>
          </View>
        }
        renderItem={({ item }) => (
          <DoctorCard
            doctor={item}
            variant="list"
            onPress={() => navigation.navigate('DoctorProfile', { doctorId: item.id })}
            onPressAction={() => navigation.navigate('DoctorProfile', { doctorId: item.id })}
          />
        )}
      />

      {renderDropdown('specialty', specialtyOptions, specialty, setSpecialty)}
      {renderDropdown('sort', [...sortOptions], sort, (value) => setSort(value))}
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.background,
  },
  dropdowns: {
    flexDirection: 'row',
    paddingHorizontal: spacing.xl,
    paddingTop: spacing.sm,
    gap: spacing.md,
  },
  dropdown: {
    flex: 1,
    backgroundColor: colors.white,
    borderRadius: radius.button,
    borderWidth: 1,
    borderColor: colors.border,
    paddingHorizontal: spacing.lg,
    paddingVertical: spacing.md,
    ...require('../../theme').shadow.subtle,
  },
  dropdownLabel: {
    fontSize: 11,
    color: colors.textTertiary,
    marginBottom: 3,
    fontWeight: '600',
  },
  dropdownValue: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
  },
  dropdownText: {
    flex: 1,
    fontSize: 13,
    fontWeight: '700',
    color: colors.text,
    marginRight: 4,
  },
  list: {
    paddingHorizontal: spacing.xl,
    paddingTop: spacing.lg,
    paddingBottom: spacing.xl5,
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
  backdrop: {
    flex: 1,
    backgroundColor: colors.overlay,
    justifyContent: 'center',
    paddingHorizontal: spacing.xxl,
  },
  menu: {
    backgroundColor: colors.white,
    borderRadius: radius.card,
    paddingVertical: spacing.md,
    ...require('../../theme').shadow.card,
  },
  menuTitle: {
    fontSize: 13,
    fontWeight: '700',
    color: colors.textMuted,
    paddingHorizontal: spacing.xl,
    paddingVertical: spacing.sm,
    textTransform: 'uppercase',
    letterSpacing: 0.4,
  },
  menuItem: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    paddingHorizontal: spacing.xl,
    paddingVertical: spacing.lg,
    minHeight: 52,
  },
  menuItemText: {
    fontSize: 15,
    color: colors.text,
  },
  menuItemSelected: {
    color: colors.primary,
    fontWeight: '700',
  },
});
