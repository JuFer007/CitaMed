import React, { useState } from 'react';
import { FlatList, Pressable, StyleSheet, Text, View } from 'react-native';
import { Feather } from '@expo/vector-icons';
import { SafeAreaView } from 'react-native-safe-area-context';
import AppointmentCard from '../../components/AppointmentCard';
import { colors, radius, spacing } from '../../theme';
import { useAppointments } from '../../context/AppointmentsContext';

const tabs = ['Próximas', 'Realizadas', 'Canceladas'];

export default function MyAppointmentsScreen({ navigation }) {
  const { upcoming, completed, cancelled } = useAppointments();
  const [tab, setTab] = useState('Próximas');

  const data = tab === 'Próximas' ? upcoming : tab === 'Realizadas' ? completed : cancelled;

  return (
    <SafeAreaView style={styles.container} edges={['top']}>
      <View style={styles.header}>
        <Text style={styles.title}>Mis citas</Text>
      </View>

      <View style={styles.tabs}>
        {tabs.map((item) => {
          const isActive = item === tab;
          return (
            <Pressable
              key={item}
              onPress={() => setTab(item)}
              style={[styles.tab, isActive && styles.tabActive]}
            >
              <Text style={[styles.tabText, isActive && styles.tabTextActive]}>{item}</Text>
            </Pressable>
          );
        })}
      </View>

      <FlatList
        data={data}
        keyExtractor={(item) => item.id}
        contentContainerStyle={styles.list}
        showsVerticalScrollIndicator={false}
        ListEmptyComponent={
          <View style={styles.empty}>
            <Feather name="calendar" size={40} color={colors.textMuted} />
            <Text style={styles.emptyTitle}>Sin citas {tab.toLowerCase()}</Text>
            <Text style={styles.emptyText}>Cuando tengas citas aparecerán aquí.</Text>
          </View>
        }
        renderItem={({ item }) => (
          <AppointmentCard
            appointment={item}
            onPress={() => navigation.navigate('AppointmentDetail', { appointmentId: item.id })}
          />
        )}
      />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.background,
  },
  header: {
    paddingHorizontal: spacing.xl,
    paddingTop: spacing.md,
    paddingBottom: spacing.lg,
  },
  title: {
    fontSize: 24,
    fontWeight: '700',
    color: colors.text,
  },
  tabs: {
    flexDirection: 'row',
    backgroundColor: colors.white,
    borderRadius: radius.control,
    borderWidth: 1,
    borderColor: colors.border,
    marginHorizontal: spacing.xl,
    padding: 4,
    height: 48,
    ...require('../../theme').shadow.subtle,
  },
  tab: {
    flex: 1,
    paddingVertical: spacing.sm,
    borderRadius: radius.control,
    alignItems: 'center',
    justifyContent: 'center',
  },
  tabActive: {
    backgroundColor: colors.primary,
  },
  tabText: {
    fontSize: 13,
    fontWeight: '700',
    color: colors.textMuted,
  },
  tabTextActive: {
    color: colors.white,
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
  emptyTitle: {
    marginTop: spacing.md,
    fontSize: 18,
    fontWeight: '700',
    color: colors.text,
  },
  emptyText: {
    marginTop: spacing.xs,
    color: colors.textMuted,
    textAlign: 'center',
    paddingHorizontal: spacing.xl,
    fontSize: 14,
    lineHeight: 22,
  },
});
