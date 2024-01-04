import React from 'react';
import {
  Button,
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';

import {Colors} from 'react-native/Libraries/NewAppScreen';

import RTNCenteredText from 'rtn-centered-text/js/RTNCenteredTextNativeComponent';

function App(): React.JSX.Element {
  const isDarkMode = useColorScheme() === 'dark';
  const [logs, setLogs] = React.useState<string[]>([]);
  const scrollViewRef = React.useRef<ScrollView | null>(null);
  const [centeredText, setCenteredText] = React.useState('Hello!');
  const [otherText, setOtherText] = React.useState('Other');

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  function handleUpdateProp1() {
    setLogs([...logs, 'Calling setCenteredText()...']);
    setCenteredText(Math.random().toString());
  }

  function handleUpdateProp2() {
    setLogs([...logs, 'Calling setOtherText()...']);
    setOtherText(Math.random().toString());
  }

  function handleClearLogs() {
    setLogs([]);
  }

  function handleTestDirectEvent(event: {nativeEvent: {message: string}}) {
    setLogs([...logs, event.nativeEvent?.message]);
  }

  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar
        barStyle={isDarkMode ? 'light-content' : 'dark-content'}
        backgroundColor={backgroundStyle.backgroundColor}
      />
      <View
        style={{
          backgroundColor: isDarkMode ? Colors.black : Colors.white,
        }}>
        <RTNCenteredText
          text={centeredText}
          other={otherText}
          onTestDirectEvent={handleTestDirectEvent}
          // onTestBubblingEvent={handleTestBubblingEvent}
          style={styles.centeredTextComponent}
        />
        <Button title="Update prop 1" onPress={handleUpdateProp1} />
        <Button title="Update prop 2" onPress={handleUpdateProp2} />
        <Button title="Clear logs" onPress={handleClearLogs} />
      </View>
      <ScrollView
        ref={scrollViewRef}
        contentInsetAdjustmentBehavior="automatic"
        onContentSizeChange={() =>
          scrollViewRef.current?.scrollToEnd({animated: true})
        }
        style={styles.logs}>
        <View>
          {logs.map((log, index) => (
            <Text key={index}>{log}</Text>
          ))}
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  centeredTextComponent: {
    height: 20,
    marginTop: 16,
    width: '100%',
  },
  logs: {
    height: '100%',
    marginTop: 16,
    padding: 16,
  },
});

export default App;
