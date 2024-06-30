/* eslint-disable prettier/prettier */
/* eslint-disable no-alert */
/* eslint-disable prettier/prettier */
/* eslint-disable react-native/no-inline-styles */
/* eslint-disable prettier/prettier */
import React, {useState, useEffect} from 'react';
import {
  SafeAreaView,
  Text,
  StyleSheet,
  View,
  ScrollView,
  ActivityIndicator,
  TextInput,
} from 'react-native';
import MarketCoin from '../../Components/MarketCoin';
import axios from 'axios';

function MarketScreen(): React.JSX.Element {
  const [coins, setCoins] = useState([]);
  const [filteredCoins, setFilteredCoins] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchQuery, setSearchQuery] = useState('');

  useEffect(() => {
    axios
      .get(
        'https://api.coinmarketcap.com/data-api/v3/cryptocurrency/listing?start=1&limit=100',
      )
      .then(res => {
        setCoins(res.data.data.cryptoCurrencyList);
        setFilteredCoins(res.data.data.cryptoCurrencyList);
        setLoading(false);
        console.log('coins', res.data.data.cryptoCurrencyList);
      })
      .catch((error: any) => console.error(error));
  }, []);

  if (loading) {
    return (
      <SafeAreaView style={styles.loadingContainer}>
        <ActivityIndicator size="large" color="#0000ff" />
      </SafeAreaView>
    );
  }

  const handleSearch = (query: string) => {
    setSearchQuery(query);
    if (query.trim() === '') {
      setFilteredCoins(coins); // Reset to all coins if search query is empty
    } else {
      const filtered = filteredCoins.filter(
        (coin: any) =>
          coin?.name.toLowerCase().includes(query.toLowerCase()) ||
          coin?.symbol.toLowerCase().includes(query.toLowerCase()),
      );
      setFilteredCoins(filtered);
    }
  };

  return (
    <SafeAreaView style={styles.container}>
      <Text style={styles.title}>Market Screen</Text>
      <TextInput
        style={styles.input}
        placeholder="Search cryptocurrencies..."
        onChangeText={handleSearch}
        value={searchQuery}
      />
      <ScrollView contentContainerStyle={styles.scrollView}>
        <View style={styles.coinsContainer}>
          {coins.map((coin: any, index: number) => (
            <MarketCoin key={index} data={coin} />
          ))}
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f0f0f0',
    paddingHorizontal: 20,
    paddingTop: 20,
  },
  loadingContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  input: {
    height: 40,
    borderColor: '#ccc',
    borderWidth: 1,
    borderRadius: 8,
    paddingHorizontal: 10,
    marginBottom: 10,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 20,
  },
  scrollView: {
    flexGrow: 1,
    width: '100%',
  },
  coinsContainer: {
    flexDirection: 'column',
    justifyContent: 'flex-start',
    alignItems: 'stretch',
    gap: 10,
  },
});

export default MarketScreen;
