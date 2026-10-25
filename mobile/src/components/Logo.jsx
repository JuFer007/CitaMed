import React from 'react';
import { Image, StyleSheet, View } from 'react-native';

const LOGO_SOURCE = require('../../assets/logoCitaMed.png');
const LOGO_ASPECT = 721 / 820;

export default function Logo({ height = 88, style }) {
  return (
    <View style={style}>
      <Image
        source={LOGO_SOURCE}
        style={[styles.image, { height, width: height * LOGO_ASPECT }]}
        resizeMode="contain"
      />
    </View>
  );
}

export function LogoMark({ size = 96 }) {
  return <Logo height={size} />;
}

const styles = StyleSheet.create({
  image: {
    alignSelf: 'center',
  },
});
