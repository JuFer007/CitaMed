import React, { useState } from 'react';
import {
  KeyboardAvoidingView,
  Platform,
  Pressable,
  ScrollView,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import Button from '../../components/Button';
import Input from '../../components/Input';
import Header from '../../components/Header';
import Logo from '../../components/Logo';
import { colors, spacing } from '../../theme';
import { useAuth } from '../../context/AuthContext';

export default function RegisterScreen({ navigation }) {
  const { register } = useAuth();
  const [form, setForm] = useState({
    name: '',
    email: '',
    phone: '',
    password: '',
    confirm: '',
  });
  const [errors, setErrors] = useState({});
  const [loading, setLoading] = useState(false);

  const update = (key) => (value) =>
    setForm((prev) => ({ ...prev, [key]: value }));

  const handleRegister = async () => {
    const nextErrors = {};
    if (!form.name.trim()) nextErrors.name = 'Ingresa tu nombre completo';
    if (!form.email.trim()) nextErrors.email = 'Ingresa tu correo';
    else if (!/^\S+@\S+\.\S+$/.test(form.email.trim())) nextErrors.email = 'Correo no válido';
    if (!form.phone.trim()) nextErrors.phone = 'Ingresa tu teléfono';
    else if (form.phone.trim().length < 9) nextErrors.phone = 'Teléfono no válido';
    if (!form.password) nextErrors.password = 'Ingresa una contraseña';
    else if (form.password.length < 6) nextErrors.password = 'Mínimo 6 caracteres';
    if (form.confirm !== form.password) nextErrors.confirm = 'Las contraseñas no coinciden';

    setErrors(nextErrors);
    if (Object.keys(nextErrors).length > 0) return;

    setLoading(true);
    try {
      await register({
        name: form.name.trim(),
        email: form.email.trim(),
        phone: form.phone.trim(),
        password: form.password,
      });
    } finally {
      setLoading(false);
    }
  };

  return (
    <SafeAreaView style={styles.container}>
      <Header title="Crear cuenta" onBack={() => navigation.goBack()} />
      <KeyboardAvoidingView
        style={styles.flex}
        behavior={Platform.OS === 'ios' ? 'padding' : undefined}
      >
        <ScrollView
          contentContainerStyle={styles.scroll}
          keyboardShouldPersistTaps="handled"
          showsVerticalScrollIndicator={false}
        >
          <Logo height={80} style={styles.logo} />
          <Text style={styles.heading}>Crear cuenta</Text>
          <Text style={styles.subtitle}>Completa tus datos para continuar</Text>

          <Input
            label="Nombre completo"
            icon="user"
            placeholder="Nombre y apellidos"
            value={form.name}
            onChangeText={update('name')}
            error={errors.name}
          />
          <Input
            label="Correo electrónico"
            icon="mail"
            placeholder="correo@ejemplo.com"
            keyboardType="email-address"
            autoCapitalize="none"
            value={form.email}
            onChangeText={update('email')}
            error={errors.email}
          />
          <Input
            label="Teléfono"
            icon="phone"
            placeholder="+51 999 999 999"
            keyboardType="phone-pad"
            value={form.phone}
            onChangeText={update('phone')}
            error={errors.phone}
          />
          <Input
            label="Contraseña"
            icon="lock"
            placeholder="Mínimo 6 caracteres"
            secureTextEntry
            value={form.password}
            onChangeText={update('password')}
            error={errors.password}
          />
          <Input
            label="Confirmar contraseña"
            icon="lock"
            placeholder="Repite tu contraseña"
            secureTextEntry
            value={form.confirm}
            onChangeText={update('confirm')}
            error={errors.confirm}
          />

          <Button title="Crear cuenta" onPress={handleRegister} loading={loading} />

          <Pressable onPress={() => navigation.navigate('Login')} style={styles.loginRow}>
            <Text style={styles.loginText}>¿Ya tienes cuenta? </Text>
            <Text style={styles.loginLink}>Inicia sesión</Text>
          </Pressable>
        </ScrollView>
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
    flexGrow: 1,
    paddingHorizontal: spacing.xl,
    paddingTop: spacing.sm,
    paddingBottom: spacing.xxl,
  },
  heading: {
    fontSize: 24,
    fontWeight: '700',
    color: colors.text,
  },
  logo: {
    alignSelf: 'center',
    marginBottom: spacing.lg,
  },
  subtitle: {
    marginTop: spacing.xs,
    marginBottom: spacing.xxl,
    fontSize: 15,
    color: colors.textMuted,
  },
  loginRow: {
    flexDirection: 'row',
    justifyContent: 'center',
    marginTop: spacing.xxl,
  },
  loginText: {
    fontSize: 14,
    color: colors.textMuted,
  },
  loginLink: {
    fontSize: 14,
    fontWeight: '700',
    color: colors.primary,
  },
});
