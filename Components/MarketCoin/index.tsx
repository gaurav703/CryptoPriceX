/* eslint-disable prettier/prettier */
import React from 'react';
import {View, Text, Image, StyleSheet} from 'react-native';

export default function Index(data: any) {
  // Extracting data
  const {name, symbol} = data?.data || {};
  const price = data?.data?.quotes[0]?.price || 0; // Assuming default price is 0 if not available

  // Formatting price to two decimal places
  const formattedPrice = parseFloat(price).toFixed(2);

  const id = data?.data?.id || 0;
  const uri = `https://s2.coinmarketcap.com/static/img/coins/64x64/${id}.png`;

  const percentChange24h =
    data?.data?.quotes[0]?.percentChange24h.toFixed(2) || 0;
  return (
    <View style={styles.container}>
      <View style={styles.iconContainer}>
        <Image
          // source={require('../../Images/bitcoin.png')}
          source={{
            uri,
          }}
          style={styles.icon}
        />
      </View>
      <View style={styles.textContainer}>
        <Text style={styles.title}>{name}</Text>
        <Text style={styles.subtitle}>{symbol}</Text>
      </View>
      <View style={styles.priceContainer}>
        <Text style={styles.price}>${formattedPrice}</Text>
        <View style={styles.changeContainer}>
          <Text
            style={
              percentChange24h > 0
                ? styles.positiveChange
                : styles.negativeChange
            }>
            {percentChange24h}%
          </Text>
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#fff',
    borderRadius: 10,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 2,
    },
    shadowOpacity: 0.25,
    shadowRadius: 3.84,
    elevation: 5,
    paddingVertical: 10,
    paddingHorizontal: 20,
    width: '100%',
    marginVertical: 10,
    alignSelf: 'center',
  },
  iconContainer: {
    marginRight: 15,
  },
  icon: {
    width: 45,
    height: 45,
  },
  textContainer: {
    flex: 1,
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
    color: 'black',
  },
  subtitle: {
    fontSize: 16,
    color: '#666',
  },
  priceContainer: {
    alignItems: 'flex-end',
  },
  label: {
    fontSize: 16,
    color: '#666',
  },
  price: {
    fontSize: 20,
    fontWeight: 'bold',
    color: 'black',
  },
  changeContainer: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  changeIcon: {
    width: 14,
    height: 14,
    marginRight: 4,
  },
  positiveChange: {
    color: '#4CAF50', // Green for positive change
    fontWeight: 'bold',
  },
  negativeChange: {
    color: '#F44336', // Red for negative change
    fontWeight: 'bold',
  },
});
