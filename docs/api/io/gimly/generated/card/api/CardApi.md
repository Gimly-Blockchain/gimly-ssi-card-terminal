# CardApi

All URIs are relative to *http://localhost:8080/gimly-ssi-card-terminal/0.1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createWallet**](CardApi.md#createWallet) | **POST** /wallets | Create a new wallet
[**getWalletByIndex**](CardApi.md#getWalletByIndex) | **GET** /wallets/{walletId} | Get a wallet by Index or public key
[**getWallets**](CardApi.md#getWallets) | **GET** /wallets | Get all wallets
[**scanCard**](CardApi.md#scanCard) | **GET** / | Scan the card


<a name="createWallet"></a>
# **createWallet**
> WalletResults createWallet(body, cardId)

Create a new wallet

    Create a new wallet.

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | **Object**| Create a new wallet |
 **cardId** | **String**| The Id of a card | [optional] [default to null]

### Return type

[**WalletResults**](../io.gimly.generated.card.model/WalletResults.md)

### Authorization

[oauth2schema](../README.md#oauth2schema)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

<a name="getWalletByIndex"></a>
# **getWalletByIndex**
> WalletInfo getWalletByIndex(walletId, cardId)

Get a wallet by Index or public key

    Get a wallet by index or publickey

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **walletId** | **String**| The Wallet index or public key | [default to null]
 **cardId** | **String**| The Id of a card | [optional] [default to null]

### Return type

[**WalletInfo**](../io.gimly.generated.card.model/WalletInfo.md)

### Authorization

[oauth2schema](../README.md#oauth2schema)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

<a name="getWallets"></a>
# **getWallets**
> WalletResults getWallets(cardId)

Get all wallets

    Get existing wallets

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **cardId** | **String**| The Id of a card | [optional] [default to null]

### Return type

[**WalletResults**](../io.gimly.generated.card.model/WalletResults.md)

### Authorization

[oauth2schema](../README.md#oauth2schema)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

<a name="scanCard"></a>
# **scanCard**
> CardInfoResult scanCard()

Scan the card

    Scan the Card

### Parameters
This endpoint does not need any parameter.

### Return type

[**CardInfoResult**](../io.gimly.generated.card.model/CardInfoResult.md)

### Authorization

[oauth2schema](../README.md#oauth2schema)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

