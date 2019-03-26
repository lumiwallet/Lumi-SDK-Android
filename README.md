# Lumi-SDK-Android

SDK for signing messages and transactions via Lumi Collect app.

## Add dependency

Add dependency in your root build.gradle:

```
allprojects {
  repositories {
   ...
   maven { url 'https://jitpack.io' }
  }
}
```

Add dependency into your module build.gradle:

```
dependencies {
    implementation 'com.github.lumitechnologies:Lumi-SDK-Android:1.0.0'
}
```

Explanation: For normal operation you will also need the commons-android library

```
    implementation 'com.github.lumitechnologies:commons-android:1.0.1' 
```

## Init Lumi SDK
   
Initialization of the SDK will not require much effort.

```
private val lumi = Lumi()
```

## How to Create and execute an operation with Lumi Collect

### Sign message

To sign a message, you need to follow these steps:

1. Create SignMessageRequest builder

```
val requestBuilder = SignMessageRequest.builder()
```

2. Specify the message as hex value

```
requestBuilder.message (hexMessage)
``` 

3. Get a request and execute it

```
val request = requestBuilder.getRequest()
lumi.execute (this, request)
``` 

### Sign personal message

To sign a personal message, you need to follow these steps:

1. Create SignPersonalMessageRequest builder

```
val requestBuilder = SignPersonalMessageRequest.builder()
```

2. Specify the message as hex value

```
requestBuilder.message (hexMessage)
``` 

3. Get a request and execute it
```
val request = requestBuilder.getRequest()
lumi.execute (this, request)
``` 

### Sign transaction

To sign a transaction, you need to follow these steps:

1. Create SignTransactionRequest builder

```
val requestBuilder = SignTransactionRequest.builder()
```

2. Specify the parameters for the transaction

```
requestBuilder.address(address)
       .chainId(chainId)
       .nonce(nonce)
       .amount(amount) 
       .gasPrice(gasPrice)
       .gasLimit(gasLimit) 
       .data(data)
       .send(false)
```

Explanations: 
The Send parameter indicates whether the transaction is about to be signed (false) or has already been sent (true). The default value is False.
An error will occur if you specify both chainId and send(true) parameters and Lumi Collect has a different chainID (from what you specified).
The Amount, gasPrice and gasLimit parameters must be specified in weis.

3. Get a request and execute it

```
val request = requestBuilder.getRequest()
lumi.execute (this, request)
```

## Handle Lumi callbacks

In order to receive responses from Lumi you need to implement BroadcastReceiver.
```

private val signReceiver: BroadcastReceiver = object : BroadcastReceiver() {
     override fun onReceive(context: Context?, intent: Intent?) {
       val signResponse: SignResponse? = lumi.getResponse(response)
      }
    }
``` 

Register the receiver by specifying an intent filter with the SIGN_ACTION parameter.

```
override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       registerReceiver(signReceiver, IntentFilter(SIGN_ACTION))
    }

```

Do not forget to cancel the registration.

```
 override fun onDestroy() {
       super.onDestroy()
       unregisterReceiver(signReceiver)
    }
```

## Response structure

Response is an object with three fields: 
SignInfo - the object that contains the response status and its detailed explanation.
Message - the object that contains the signed message, the signed transaction or the hex of the sent transaction.
WalletInfo - the object that contains the information about the wallet (address, balance, network (chainId).

## Example

LumiSDK includes an example project with the abovementioned code. To run the example project clone the repo. 
Open project in Android Studio and run a sample module. 
Make sure that you have LumiCollect installed on your device or a simulator to test the full callback flow.

## License

Lumi-SDK-Android is available under the MIT license. See the [LICENSE](LICENSE) file for more info.
