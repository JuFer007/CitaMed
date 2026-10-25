import React from 'react';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import MainTabs from './MainTabs';
import DoctorsListScreen from '../screens/doctors/DoctorsListScreen';
import DoctorProfileScreen from '../screens/doctors/DoctorProfileScreen';
import BookAppointmentScreen from '../screens/appointments/BookAppointmentScreen';
import ConfirmationScreen from '../screens/appointments/ConfirmationScreen';
import AppointmentDetailScreen from '../screens/appointments/AppointmentDetailScreen';
import EditProfileScreen from '../screens/profile/EditProfileScreen';

const Stack = createNativeStackNavigator();

export default function AppStack() {
  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
      <Stack.Screen name="Tabs" component={MainTabs} />
      <Stack.Screen name="DoctorsList" component={DoctorsListScreen} />
      <Stack.Screen name="DoctorProfile" component={DoctorProfileScreen} />
      <Stack.Screen name="BookAppointment" component={BookAppointmentScreen} />
      <Stack.Screen name="AppointmentConfirmation" component={ConfirmationScreen} />
      <Stack.Screen name="AppointmentDetail" component={AppointmentDetailScreen} />
      <Stack.Screen name="EditProfile" component={EditProfileScreen} />
    </Stack.Navigator>
  );
}
