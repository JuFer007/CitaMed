import React, { createContext, useCallback, useContext, useMemo, useState } from 'react';
import { initialAppointments } from '../data/appointments';

const AppointmentsContext = createContext(undefined);

export function AppointmentsProvider({ children }) {
  const [appointments, setAppointments] = useState(initialAppointments);

  const addAppointment = useCallback((data) => {
    const appointment = {
      ...data,
      id: `appt-${Date.now()}`,
      status: 'confirmed',
    };
    setAppointments((prev) => [appointment, ...prev]);
    return appointment;
  }, []);

  const cancelAppointment = useCallback((id) => {
    setAppointments((prev) =>
      prev.map((appointment) =>
        appointment.id === id ? { ...appointment, status: 'cancelled' } : appointment,
      ),
    );
  }, []);

  const getAppointmentById = useCallback(
    (id) => appointments.find((appointment) => appointment.id === id),
    [appointments],
  );

  const { upcoming, completed, cancelled } = useMemo(() => {
    return {
      upcoming: appointments.filter(
        (appointment) => appointment.status === 'confirmed' || appointment.status === 'pending',
      ),
      completed: appointments.filter((appointment) => appointment.status === 'completed'),
      cancelled: appointments.filter((appointment) => appointment.status === 'cancelled'),
    };
  }, [appointments]);

  const value = useMemo(
    () => ({
      appointments,
      upcoming,
      completed,
      cancelled,
      addAppointment,
      cancelAppointment,
      getAppointmentById,
    }),
    [appointments, upcoming, completed, cancelled, addAppointment, cancelAppointment, getAppointmentById],
  );

  return <AppointmentsContext.Provider value={value}>{children}</AppointmentsContext.Provider>;
}

export function useAppointments() {
  const context = useContext(AppointmentsContext);
  if (!context) {
    throw new Error('useAppointments debe usarse dentro de un AppointmentsProvider');
  }
  return context;
}
