import React, { useState } from 'react';
import {
  KeyboardAvoidingView,
  Platform,
  ScrollView,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import Button from '../../components/Button';
import Header from '../../components/Header';
import Input from '../../components/Input';
import { colors, spacing } from '../../theme';
import { useAuth } from '../../context/AuthContext';

export default function EditProfileScreen({ navigation }) {
  const { user, updateUser } = useAuth();
  const [name, setName] = useState(user?.name ?? '');
  const [email, setEmail] = useState(user?.email ?? '');
  const [phone, setPhone] = useState(user?.phone ?? '');
  const [errors, setErrors] = useState({});

  const handleSave = () => {
    const nextErrors = {};
    if (!name.trim()) nextErrors.name = 'Ingresa tu nombre';
    if (!email.trim()) nextErrors.email = 'Ingresa tu correo';
    else if (!/^\S+@\S+\.\S+$/.test(email.trim())) nextErrors.email = 'Correo no válido';
    if (!phone.trim()) nextErrors.phone = 'Ingresa tu teléfono';
    setErrors(nextErrors);
    if (Object.keys(nextErrors).length > 0) return;

    updateUser({ name: name.trim(), email: email.trim(), phone: phone.trim() });
    navigation.goBack();
  };

  return (
    <SafeAreaView style={styles.container} edges={['top']}>
      <Header title="Editar perfil" onBack={() => navigation.goBack()} />
      <KeyboardAvoidingView
        style={styles.flex}
        behavior={Platform.OS === 'ios' ? 'padding' : undefined}
      >
        <ScrollView
          contentContainerStyle={styles.scroll}
          keyboardShouldPersistTaps="handled"
          showsVerticalScrollIndicator={false}
        >
          <Text style={styles.hint}>
            Actualiza tus datos personales
          </Text>

          <Input
            label="Nombre completo"
            icon="user"
            value={name}
            onChangeText={setName}
            error={errors.name}
          />
          <Input
            label="Correo electrónico"
            icon="mail"
            keyboardType="email-address"
            autoCapitalize="none"
            value={email}
            onChangeText={setEmail}
            error={errors.email}
          />
          <Input
            label="Teléfono"
            icon="phone"
            keyboardType="phone-pad"
            value={phone}
            onChangeText={setPhone}
            error={errors.phone}
          />
        </ScrollView>

        <View style={styles.footer}>
          <Button title="Guardar cambios" onPress={handleSave} />
        </View>
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.background,
  },
  flex: {
    flex: 1,
  },
  scroll: {
    paddingHorizontal: spacing.xl,
    paddingTop: spacing.lg,
    paddingBottom: spacing.xxl,
  },
  hint: {
    fontSize: 14,
    color: colors.textMuted,
    marginBottom: spacing.xl,
    lineHeight: 20,
  },
  footer: {
    padding: spacing.xl,
    borderTopWidth: 1,
    borderTopColor: colors.border,
  },
});
