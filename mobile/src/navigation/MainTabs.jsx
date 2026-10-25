import React from 'react';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import HomeScreen from '../screens/home/HomeScreen';
import MyAppointmentsScreen from '../screens/appointments/MyAppointmentsScreen';
import NotificationsScreen from '../screens/profile/NotificationsScreen';
import ProfileScreen from '../screens/profile/ProfileScreen';
import TabBar from '../components/TabBar';

const Tab = createBottomTabNavigator();

export default function MainTabs() {
  return (
    <Tab.Navigator screenOptions={{ headerShown: false }} tabBar={(props) => <TabBar {...props} />}>
      <Tab.Screen name="HomeTab" component={HomeScreen} options={{ title: 'Inicio' }} />
      <Tab.Screen
        name="AppointmentsTab"
        component={MyAppointmentsScreen}
        options={{ title: 'Mis citas' }}
      />
      <Tab.Screen
        name="NotificationsTab"
        component={NotificationsScreen}
        options={{ title: 'Notificaciones' }}
      />
      <Tab.Screen name="ProfileTab" component={ProfileScreen} options={{ title: 'Perfil' }} />
    </Tab.Navigator>
  );
}
