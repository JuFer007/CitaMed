import React, { createContext, useCallback, useContext, useMemo, useState } from 'react';

const AuthContext = createContext(undefined);

const defaultUser = {
  name: 'María López',
  email: 'maria.lopez@citamed.pe',
  phone: '+51 987 654 321',
};

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);

  const login = useCallback(async (email, _password) => {
    await new Promise((resolve) => setTimeout(resolve, 600));
    setUser({ ...defaultUser, email: email || defaultUser.email });
  }, []);

  const register = useCallback(async (data) => {
    await new Promise((resolve) => setTimeout(resolve, 600));
    setUser({ name: data.name, email: data.email, phone: data.phone });
  }, []);

  const logout = useCallback(() => {
    setUser(null);
  }, []);

  const updateUser = useCallback((data) => {
    setUser((prev) => (prev ? { ...prev, ...data } : prev));
  }, []);

  const value = useMemo(
    () => ({
      user,
      isAuthenticated: user !== null,
      login,
      register,
      logout,
      updateUser,
    }),
    [user, login, register, logout, updateUser],
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth debe usarse dentro de un AuthProvider');
  }
  return context;
}
