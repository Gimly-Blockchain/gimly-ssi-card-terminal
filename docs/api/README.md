# Documentation for Gimly SSI Card Terminal

<a name="documentation-for-api-endpoints"></a>
## Documentation for API Endpoints

All URIs are relative to *http://localhost:8080/gimly-ssi-card-terminal/0.1*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*CardApi* | [**scanCard**](Apis/CardApi.md#scancard) | **GET** / | Scan the card
*KeyApi* | [**createKey**](Apis/KeyApi.md#createkey) | **POST** /keys | Create a new key
*KeyApi* | [**deactiveKey**](Apis/KeyApi.md#deactivekey) | **DELETE** /keys/{keyId} | Deactivate a key by card Index, public key or DID key
*KeyApi* | [**getKey**](Apis/KeyApi.md#getkey) | **GET** /keys/{keyId} | Get a key by card Index, public key or DID key
*KeyApi* | [**getKeys**](Apis/KeyApi.md#getkeys) | **GET** /keys | Get all keys
*KeyApi* | [**signUsingKey**](Apis/KeyApi.md#signusingkey) | **PUT** /keys/{keyId}/signatures | Sign one or more inputs


<a name="documentation-for-models"></a>
## Documentation for Models

 - [CardInfo](./io.gimly.generated.card.model/CardInfo.md)
 - [CardInfoResult](./io.gimly.generated.card.model/CardInfoResult.md)
 - [CreateKeyRequest](./io.gimly.generated.card.model/CreateKeyRequest.md)
 - [Curve](./io.gimly.generated.card.model/Curve.md)
 - [ErrorResponse](./io.gimly.generated.card.model/ErrorResponse.md)
 - [FirmwareVersion](./io.gimly.generated.card.model/FirmwareVersion.md)
 - [InputEncoding](./io.gimly.generated.card.model/InputEncoding.md)
 - [KeyInfo](./io.gimly.generated.card.model/KeyInfo.md)
 - [KeyResults](./io.gimly.generated.card.model/KeyResults.md)
 - [KeyStatus](./io.gimly.generated.card.model/KeyStatus.md)
 - [LinkedTerminal](./io.gimly.generated.card.model/LinkedTerminal.md)
 - [OutputEncoding](./io.gimly.generated.card.model/OutputEncoding.md)
 - [SignInput](./io.gimly.generated.card.model/SignInput.md)
 - [SignMode](./io.gimly.generated.card.model/SignMode.md)
 - [SignOutput](./io.gimly.generated.card.model/SignOutput.md)
 - [SignOutputFromInput](./io.gimly.generated.card.model/SignOutputFromInput.md)
 - [SignRequest](./io.gimly.generated.card.model/SignRequest.md)
 - [SignResponse](./io.gimly.generated.card.model/SignResponse.md)


<a name="documentation-for-authorization"></a>
## Documentation for Authorization

<a name="oauth2schema"></a>
### oauth2schema

- **Type**: OAuth
- **Flow**: application
- **Authorization URL**: 
- **Scopes**: 
  - global: accessEverything

