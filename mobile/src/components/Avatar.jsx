import React from 'react';
import { StyleSheet, View } from 'react-native';
import Svg, { Circle, ClipPath, Defs, G, Path } from 'react-native-svg';
import { colors } from '../theme';

export default function Avatar({ size = 48, color = colors.avatar }) {
  return (
    <View style={[styles.container, { width: size, height: size, borderRadius: size / 2 }]}>
      <Svg width={size} height={size} viewBox="0 0 100 100">
        <Defs>
          <ClipPath id="avatarClip">
            <Circle cx={50} cy={50} r={50} />
          </ClipPath>
        </Defs>
        <G clipPath="url(#avatarClip)">
          <Circle cx={50} cy={50} r={50} fill={color} />
          <Circle cx={50} cy={40} r={17} fill={colors.white} />
          <Path d="M20,92 C20,70 33,60 50,60 C67,60 80,70 80,92 Z" fill={colors.white} />
        </G>
      </Svg>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    overflow: 'hidden',
  },
});
