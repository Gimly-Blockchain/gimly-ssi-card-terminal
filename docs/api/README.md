# Documentation for Gimly SSI Card Terminal

<a name="documentation-for-api-endpoints"></a>
## Documentation for API Endpoints

All URIs are relative to *http://localhost:8080/gimly-ssi-card-terminal/0.1*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*CardApi* | [**createWallet**](Apis/CardApi.md#createwallet) | **POST** /wallets | Create a new wallet
*CardApi* | [**getWalletByIndex**](Apis/CardApi.md#getwalletbyindex) | **GET** /wallets/{walletId} | Get a wallet by Index or public key
*CardApi* | [**getWallets**](Apis/CardApi.md#getwallets) | **GET** /wallets | Get all wallets
*CardApi* | [**scanCard**](Apis/CardApi.md#scancard) | **GET** / | Scan the card


<a name="documentation-for-models"></a>
## Documentation for Models

 - [CardInfo](./io.gimly.generated.card.model/CardInfo.md)
 - [CardInfoResult](./io.gimly.generated.card.model/CardInfoResult.md)
 - [Curve](./io.gimly.generated.card.model/Curve.md)
 - [ErrorResponse](./io.gimly.generated.card.model/ErrorResponse.md)
 - [FirmwareVersion](./io.gimly.generated.card.model/FirmwareVersion.md)
 - [LinkedTerminal](./io.gimly.generated.card.model/LinkedTerminal.md)
 - [WalletInfo](./io.gimly.generated.card.model/WalletInfo.md)
 - [WalletResults](./io.gimly.generated.card.model/WalletResults.md)


<a name="documentation-for-authorization"></a>
## Documentation for Authorization

<a name="oauth2schema"></a>
### oauth2schema

- **Type**: OAuth
- **Flow**: application
- **Authorization URL**: 
- **Scopes**: 
  - global: accessEverything

