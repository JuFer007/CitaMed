import React from 'react';
import { Alert, Pressable, ScrollView, StyleSheet, Text, View } from 'react-native';
import { Feather } from '@expo/vector-icons';
import { SafeAreaView } from 'react-native-safe-area-context';
import Avatar from '../../components/Avatar';
import { colors, radius, shadow, spacing } from '../../theme';
import { useAuth } from '../../context/AuthContext';

export default function ProfileScreen({ navigation }) {
  const { user, logout } = useAuth();

  const handleLogout = () => {
    Alert.alert('Cerrar sesión', '¿Deseas cerrar sesión en CitaMed?', [
      { text: 'Cancelar', style: 'cancel' },
      { text: 'Cerrar sesión', style: 'destructive', onPress: logout },
    ]);
  };

  const options = [
    {
      label: 'Editar perfil',
      icon: 'edit-2',
      onPress: () => navigation.navigate('EditProfile'),
    },
    {
      label: 'Historial médico',
      icon: 'file-text',
      onPress: () =>
        Alert.alert('Historial médico', 'Próximamente podrás ver aquí tu historial clínico.'),
    },
    {
      label: 'Notificaciones',
      icon: 'bell',
      onPress: () => navigation.navigate('Tabs', { screen: 'NotificationsTab' }),
    },
    {
      label: 'Ayuda y soporte',
      icon: 'help-circle',
      onPress: () => Alert.alert('Ayuda', 'Escríbenos a soporte@citamed.pe y te ayudaremos.'),
    },
    {
      label: 'Términos y privacidad',
      icon: 'shield',
      onPress: () => Alert.alert('Términos y privacidad', 'Consulta nuestros términos y política de privacidad.'),
    },
    {
      label: 'Cerrar sesión',
      icon: 'log-out',
      onPress: handleLogout,
      danger: true,
    },
  ];

  return (
    <SafeAreaView style={styles.container} edges={['top']}>
      <ScrollView contentContainerStyle={styles.scroll} showsVerticalScrollIndicator={false}>
        <Text style={styles.title}>Mi perfil</Text>

        <View style={styles.card}>
          <View style={styles.avatarWrapper}>
            <Avatar size={88} />
            <Pressable style={styles.camera} onPress={() => navigation.navigate('EditProfile')}>
              <Feather name="camera" size={16} color={colors.white} />
            </Pressable>
          </View>
          <Text style={styles.name}>{user?.name ?? 'Paciente'}</Text>
          <Text style={styles.email}>{user?.email ?? ''}</Text>
          <Text style={styles.phone}>{user?.phone ?? ''}</Text>
        </View>

        <View style={styles.options}>
          {options.map((option, index) => (
            <Pressable
              key={option.label}
              onPress={option.onPress}
              style={[styles.option, index === options.length - 1 && styles.optionLast]}
            >
              <View style={[styles.optionIcon, option.danger && styles.optionIconDanger]}>
                <Feather
                  name={option.icon}
                  size={18}
                  color={option.danger ? colors.danger : colors.primary}
                />
              </View>
              <Text style={[styles.optionLabel, option.danger && styles.optionLabelDanger]}>
                {option.label}
              </Text>
              <Feather name="chevron-right" size={18} color={colors.textMuted} />
            </Pressable>
          ))}
        </View>
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
    paddingTop: spacing.md,
    paddingBottom: spacing.xxl,
  },
  title: {
    fontSize: 24,
    fontWeight: '700',
    color: colors.text,
    marginBottom: spacing.lg,
  },
  card: {
    alignItems: 'center',
    backgroundColor: colors.card,
    borderRadius: radius.card,
    paddingVertical: spacing.xxxl,
    ...shadow.card,
  },
  avatarWrapper: {
    position: 'relative',
  },
  camera: {
    position: 'absolute',
    right: 0,
    bottom: 0,
    width: 40,
    height: 40,
    borderRadius: 20,
    backgroundColor: colors.primary,
    alignItems: 'center',
    justifyContent: 'center',
    borderWidth: 3,
    borderColor: colors.white,
  },
  name: {
    marginTop: spacing.lg,
    fontSize: 20,
    fontWeight: '700',
    color: colors.text,
  },
  email: {
    marginTop: spacing.xs,
    fontSize: 14,
    color: colors.textMuted,
  },
  phone: {
    marginTop: 2,
    fontSize: 14,
    color: colors.textMuted,
  },
  options: {
    marginTop: spacing.xl,
    backgroundColor: colors.card,
    borderRadius: radius.card,
    borderWidth: 1,
    borderColor: colors.border,
    overflow: 'hidden',
    ...shadow.subtle,
  },
  option: {
    flexDirection: 'row',
    alignItems: 'center',
    padding: spacing.lg,
    borderBottomWidth: 0.5,
    borderBottomColor: colors.border,
    minHeight: 56,
  },
  optionLast: {
    borderBottomWidth: 0,
  },
  optionIcon: {
    width: 42,
    height: 42,
    borderRadius: 21,
    backgroundColor: colors.primarySoft,
    alignItems: 'center',
    justifyContent: 'center',
  },
  optionIconDanger: {
    backgroundColor: '#FFDADA',
  },
  optionLabel: {
    flex: 1,
    marginLeft: spacing.md,
    fontSize: 15,
    fontWeight: '600',
    color: colors.text,
  },
  optionLabelDanger: {
    color: colors.danger,
  },
});
