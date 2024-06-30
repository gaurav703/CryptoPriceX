/* eslint-disable prettier/prettier */
/* eslint-disable react-native/no-inline-styles */
/* eslint-disable prettier/prettier */
/* eslint-disable prettier/prettier */
import axios from 'axios';
import React, {useEffect, useState} from 'react';
import {
  SafeAreaView,
  Text,
  StyleSheet,
  TouchableOpacity,
  View,
  ScrollView,
  ActivityIndicator,
  FlatList,
  Dimensions,
  Image,
} from 'react-native';
import MarketCoin from '../../Components/MarketCoin';

function HomeScreen(): React.JSX.Element {
  const [activeTab, setActiveTab] = useState('gainers');
  const [coins, setCoins] = useState([]);
  const [filteredCoins, setFilteredCoins] = useState([]);
  const [famousCoins, setFamousCoins] = useState([]);
  const [loading, setLoading] = useState(true);

  const famousCoinNames = [
    'Bitcoin',
    'Ethereum',
    'Cardano',
    'Binance Coin',
    'Solana',
  ];

  const handleTabPress = (tab: string) => {
    setActiveTab(tab);
    filterCoins(tab, coins);
  };

  const filterCoins = (tab: string, coinList: any[]) => {
    const sortedCoins: any = [...coinList].sort((a, b) => {
      const changeA = a.quotes[0].percentChange24h;
      const changeB = b.quotes[0].percentChange24h;
      return tab === 'gainers' ? changeB - changeA : changeA - changeB;
    });
    setFilteredCoins(sortedCoins.slice(0, 10));
  };

  const filterFamousCoins = (coinList: any[]) => {
    const famous: any = coinList.filter((coin: any) =>
      famousCoinNames.includes(coin.name),
    );
    setFamousCoins(famous);
  };

  useEffect(() => {
    axios
      .get(
        'https://api.coinmarketcap.com/data-api/v3/cryptocurrency/listing?start=1&limit=100',
      )
      .then(res => {
        const coinList = res.data.data.cryptoCurrencyList;
        setCoins(coinList);
        setFilteredCoins(coinList);
        filterCoins('gainers', coinList);
        filterFamousCoins(coinList);
        setLoading(false);
        // console.log('coins', coinList);
      })
      .catch((error: any) => console.error(error));
  });

  const renderFamousCoin = ({item}: any) => {
    const id = item?.id || 0;
    const uri = `https://s2.coinmarketcap.com/static/img/coins/64x64/${id}.png`;
    return (
      <View style={styles.card}>
        <View style={styles.cardRow}>
          <Image
            // source={require('../../Images/bitcoin.png')}
            source={{
              uri,
            }}
            style={styles.icon}
          />

          <View style={styles.textContainer}>
            <Text style={styles.title_name}>{item?.name}</Text>
            <Text style={styles.subtitle}>{item?.symbol}</Text>
          </View>
          {/* <Text style={styles.cardTitle}>{item.name}</Text>
          <Text style={styles.cardPrice}>
            ${item.quotes[0].price.toFixed(2)}
          </Text>
          <Text
            style={[
              styles.cardPercent,
              {color: item.quotes[0].percentChange24h >= 0 ? 'green' : 'red'},
            ]}>
            {item.quotes[0].percentChange24h.toFixed(2)}%
          </Text> */}
        </View>
        <View
          style={{
            display: 'flex',
            flexDirection: 'row',
            justifyContent: 'space-between',
            width: '100%',
            alignItems: 'center',
          }}>
          <Text style={styles.cardPrice}>
            ${item.quotes[0].price.toFixed(2)}
          </Text>
          <Text
            style={[
              styles.cardPercent,
              {color: item.quotes[0].percentChange24h >= 0 ? 'green' : 'red'},
            ]}>
            {item.quotes[0].percentChange24h.toFixed(2)}%
          </Text>
        </View>
      </View>
    );
  };

  if (loading) {
    return (
      <SafeAreaView style={styles.loadingContainer}>
        <ActivityIndicator size="large" color="#0000ff" />
      </SafeAreaView>
    );
  }

  return (
    <SafeAreaView style={styles.main_cont}>
      <Text style={styles.title}>CryptoPriceX</Text>
      <Text style={styles.sectionTitle}>Most Famous Coins</Text>
      <View>
        <FlatList
          data={famousCoins}
          renderItem={renderFamousCoin}
          keyExtractor={item => item.id}
          horizontal
          showsHorizontalScrollIndicator={false}
          contentContainerStyle={styles.horizontalScroll}
        />
      </View>

      <View style={styles.container}>
        <TouchableOpacity
          style={[
            styles.tabButton,
            activeTab === 'gainers' && styles.activeTabButton,
          ]}
          onPress={() => handleTabPress('gainers')}>
          <Text
            style={[
              styles.tabButtonText,
              activeTab === 'gainers' && styles.activeTabButtonText,
            ]}>
            Top Gainers
          </Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={[
            styles.tabButton,
            activeTab === 'losers' && styles.activeTabButton,
          ]}
          onPress={() => handleTabPress('losers')}>
          <Text
            style={[
              styles.tabButtonText,
              activeTab === 'losers' && styles.activeTabButtonText,
            ]}>
            Top Losers
          </Text>
        </TouchableOpacity>
      </View>

      <ScrollView contentContainerStyle={styles.scrollView}>
        <Text style={styles.sectionTitle}>
          Top {activeTab === 'gainers' ? 'Gainers' : 'Losers'}
        </Text>
        <View style={styles.coinsContainer}>
          {filteredCoins.map((coin: any, index: number) => (
            <MarketCoin key={index} data={coin} />
          ))}
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  main_cont: {
    flex: 1,
    backgroundColor: '#f0f0f0',
    paddingTop: 20,
  },
  text: {
    fontSize: 20,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 20,
    paddingHorizontal: 20,
    color: 'grey',
  },
  sectionTitle: {
    fontSize: 20,
    fontWeight: 'bold',
    marginTop: 20,
    paddingHorizontal: 20,
  },
  container: {
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: 10,
    marginBottom: 10,
    backgroundColor: '#f0f0f0',
    // borderRadius: 8,
    overflow: 'hidden',
  },
  tabButton: {
    flex: 1,
    paddingVertical: 10,
    alignItems: 'center',
    backgroundColor: '#fff',
  },
  activeTabButton: {
    backgroundColor: '#5EDE99',
  },
  tabButtonText: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#666',
  },
  activeTabButtonText: {
    color: '#fff',
  },
  scrollView: {
    flexGrow: 1,
    width: '100%',
    paddingHorizontal: 20,
  },
  coinsContainer: {
    flexDirection: 'column',
    justifyContent: 'flex-start',
    alignItems: 'stretch',
    gap: 10,
  },
  loadingContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  card: {
    backgroundColor: '#fff',
    borderRadius: 6,
    paddingHorizontal: 20,
    marginRight: 15,
    width: 250,
    height: 110,
    alignItems: 'center',
    shadowColor: '#000',
    shadowOffset: {width: 0, height: 2},
    shadowOpacity: 0.8,
    shadowRadius: 2,
    elevation: 5,
  },
  cardRow: {
    flexDirection: 'row',
    justifyContent: 'flex-start',
    alignItems: 'center',
    width: '100%',
  },
  cardContent: {
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
  },
  cardTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 5,
    marginLeft: 30,
  },
  cardPrice: {
    fontSize: 16,
    color: 'green',
  },
  cardPercent: {
    fontSize: 14,
    marginTop: 5,
  },
  icon: {
    width: 50,
    height: 50,
    marginVertical: 10,
  },
  horizontalScroll: {
    paddingLeft: 20,
    paddingVertical: 10,
  },
  textContainer: {
    marginLeft: 30,
  },
  title_name: {
    fontSize: 20,
    fontWeight: 'bold',
    color: 'black',
  },
  subtitle: {
    fontSize: 16,
    color: '#666',
  },
});

export default HomeScreen;
