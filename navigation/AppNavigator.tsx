/* eslint-disable prettier/prettier */
/* eslint-disable react-native/no-inline-styles */
/* eslint-disable react/no-unstable-nested-components */
/* eslint-disable prettier/prettier */
import React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import {Image} from 'react-native';
import HomeScreen from '../Screens/HomeScreen';
import MarketScreen from '../Screens/MarketScreen';

const Tab = createBottomTabNavigator();

function AppNavigator(): React.JSX.Element {
  return (
    <NavigationContainer>
      <Tab.Navigator
        screenOptions={({route}) => ({
          tabBarActiveTintColor: '#5EDE99',
          tabBarInactiveTintColor: '#7B7B7B',
          tabBarLabelStyle: {fontSize: 12},
          style: {
            borderTopWidth: 0, // remove top border of tab bar
            elevation: 0, // remove shadow on Android
            shadowOpacity: 0, // remove shadow on iOS
            backgroundColor: '#FFFFFF', // background color of tab bar,
            paddingVertical: 20,
          },
          tabBarIcon: ({focused}) => {
            let iconName;
            if (route.name === 'Home') {
              iconName = focused
                ? require('../Images/Home1.png')
                : require('../Images/Home2.png');
            } else if (route.name === 'Market') {
              iconName = focused
                ? require('../Images/Market1.png')
                : require('../Images/Market2.png');
            }
            return <Image source={iconName} style={{width: 24, height: 24}} />;
          },
        })}>
        <Tab.Screen
          name="Home"
          component={HomeScreen}
          options={{
            headerShown: false,
          }}
        />
        <Tab.Screen
          name="Market"
          component={MarketScreen}
          options={{
            headerShown: false,
          }}
        />
      </Tab.Navigator>
    </NavigationContainer>
  );
}

export default AppNavigator;
