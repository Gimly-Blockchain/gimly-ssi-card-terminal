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
*ProofApi* | [**signCredential**](Apis/ProofApi.md#signcredential) | **PUT** /keys/{keyId}/proofs/credentials | Signs a credential (adds a proof)
*ProofApi* | [**signPresentation**](Apis/ProofApi.md#signpresentation) | **PUT** /keys/{keyId}/proofs/presentations | Signs a presentation (adds a proof)
*ProofApi* | [**signUsingKey**](Apis/ProofApi.md#signusingkey) | **PUT** /keys/{keyId}/proofs/signatures | Sign one or more inputs
*StorageApi* | [**deleteStoredCredential**](Apis/StorageApi.md#deletestoredcredential) | **DELETE** /storage/credentials/{credentialId} | Deletes a stored Verifiable credential
*StorageApi* | [**getStoredCredential**](Apis/StorageApi.md#getstoredcredential) | **GET** /storage/credentials/{credentialId} | Gets a stored Verifiable credential
*StorageApi* | [**getStoredCredentials**](Apis/StorageApi.md#getstoredcredentials) | **GET** /storage/credentials | Gets all stored Verifiable Credentials


<a name="documentation-for-models"></a>
## Documentation for Models

 - [CardInfo](./io.gimly.generated.card.model/CardInfo.md)
 - [CardInfoResult](./io.gimly.generated.card.model/CardInfoResult.md)
 - [CreateKeyRequest](./io.gimly.generated.card.model/CreateKeyRequest.md)
 - [Credential](./io.gimly.generated.card.model/Credential.md)
 - [CredentialStatus](./io.gimly.generated.card.model/CredentialStatus.md)
 - [CredentialSubject](./io.gimly.generated.card.model/CredentialSubject.md)
 - [Curve](./io.gimly.generated.card.model/Curve.md)
 - [ErrorResponse](./io.gimly.generated.card.model/ErrorResponse.md)
 - [FirmwareVersion](./io.gimly.generated.card.model/FirmwareVersion.md)
 - [InputEncoding](./io.gimly.generated.card.model/InputEncoding.md)
 - [KeyInfo](./io.gimly.generated.card.model/KeyInfo.md)
 - [KeyResults](./io.gimly.generated.card.model/KeyResults.md)
 - [KeyStatus](./io.gimly.generated.card.model/KeyStatus.md)
 - [LinkedTerminal](./io.gimly.generated.card.model/LinkedTerminal.md)
 - [OutputEncoding](./io.gimly.generated.card.model/OutputEncoding.md)
 - [Presentation](./io.gimly.generated.card.model/Presentation.md)
 - [Proof](./io.gimly.generated.card.model/Proof.md)
 - [SignCredentialRequest](./io.gimly.generated.card.model/SignCredentialRequest.md)
 - [SignCredentialResponse](./io.gimly.generated.card.model/SignCredentialResponse.md)
 - [SignInput](./io.gimly.generated.card.model/SignInput.md)
 - [SignMode](./io.gimly.generated.card.model/SignMode.md)
 - [SignOutput](./io.gimly.generated.card.model/SignOutput.md)
 - [SignOutputFromInput](./io.gimly.generated.card.model/SignOutputFromInput.md)
 - [SignPresentationRequest](./io.gimly.generated.card.model/SignPresentationRequest.md)
 - [SignPresentationResponse](./io.gimly.generated.card.model/SignPresentationResponse.md)
 - [SignRequest](./io.gimly.generated.card.model/SignRequest.md)
 - [SignResponse](./io.gimly.generated.card.model/SignResponse.md)
 - [StoredCredentialsResponse](./io.gimly.generated.card.model/StoredCredentialsResponse.md)
 - [VerifiableCredential](./io.gimly.generated.card.model/VerifiableCredential.md)
 - [VerifiablePresentation](./io.gimly.generated.card.model/VerifiablePresentation.md)


<a name="documentation-for-authorization"></a>
## Documentation for Authorization

<a name="oauth2schema"></a>
### oauth2schema

- **Type**: OAuth
- **Flow**: application
- **Authorization URL**: 
- **Scopes**: 
  - global: accessEverything

