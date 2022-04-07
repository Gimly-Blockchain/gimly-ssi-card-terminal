# Documentation for Gimly SSI Card Terminal

<a name="documentation-for-api-endpoints"></a>

## Documentation for API Endpoints

All URIs are relative to _http://localhost:8080/gimly-ssi-card-terminal/0.1_

| Class        | Method                                                                                         | HTTP request                                   | Description                                           |
| ------------ | ---------------------------------------------------------------------------------------------- | ---------------------------------------------- | ----------------------------------------------------- |
| _CardApi_    | [**scanCard**](io/gimly/generated/card/api/CardApi.md#scancard)                                | **GET** /                                      | Scan the card                                         |
| _KeyApi_     | [**createKey**](io/gimly/generated/card/api/KeyApi.md#createkey)                               | **POST** /keys                                 | Create a new key                                      |
| _KeyApi_     | [**deactiveKey**](io/gimly/generated/card/api/KeyApi.md#deactivekey)                           | **DELETE** /keys/{keyId}                       | Deactivate a key by card Index, public key or DID key |
| _KeyApi_     | [**getKey**](io/gimly/generated/card/api/KeyApi.md#getkey)                                     | **GET** /keys/{keyId}                          | Get a key by card Index, public key or DID key        |
| _KeyApi_     | [**getKeys**](io/gimly/generated/card/api/KeyApi.md#getkeys)                                   | **GET** /keys                                  | Get all keys                                          |
| _ProofApi_   | [**signCredential**](io/gimly/generated/card/api/ProofApi.md#signcredential)                   | **PUT** /keys/{keyId}/proofs/credentials       | Signs a credential (adds a proof)                     |
| _ProofApi_   | [**signPresentation**](io/gimly/generated/card/api/ProofApi.md#signpresentation)               | **PUT** /keys/{keyId}/proofs/presentations     | Signs a presentation (adds a proof)                   |
| _ProofApi_   | [**signUsingKey**](io/gimly/generated/card/api/ProofApi.md#signusingkey)                       | **PUT** /keys/{keyId}/proofs/signatures        | Sign one or more inputs                               |
| _StorageApi_ | [**deleteStoredCredential**](io/gimly/generated/card/api/StorageApi.md#deletestoredcredential) | **DELETE** /storage/credentials/{credentialId} | Deletes a stored Verifiable credential                |
| _StorageApi_ | [**getStoredCredential**](io/gimly/generated/card/api/StorageApi.md#getstoredcredential)       | **GET** /storage/credentials/{credentialId}    | Gets a stored Verifiable credential                   |
| _StorageApi_ | [**getStoredCredentials**](io/gimly/generated/card/api/StorageApi.md#getstoredcredentials)     | **GET** /storage/credentials                   | Gets all stored Verifiable Credentials                |

<a name="documentation-for-models"></a>

## Documentation for Models

- [CardInfo](/docs/api/io/gimly/generated/card/model/CardInfo.md)
- [CardInfoResult](/docs/api/io/gimly/generated/card/model/CardInfoResult.md)
- [CreateKeyRequest](/docs/api/io/gimly/generated/card/model/CreateKeyRequest.md)
- [Credential](/docs/api/io/gimly/generated/card/model/Credential.md)
- [CredentialStatus](/docs/api/io/gimly/generated/card/model/CredentialStatus.md)
- [CredentialSubject](/docs/api/io/gimly/generated/card/model/CredentialSubject.md)
- [Curve](/docs/api/io/gimly/generated/card/model/Curve.md)
- [ErrorResponse](/docs/api/io/gimly/generated/card/model/ErrorResponse.md)
- [FirmwareVersion](/docs/api/io/gimly/generated/card/model/FirmwareVersion.md)
- [InputEncoding](/docs/api/io/gimly/generated/card/model/InputEncoding.md)
- [KeyInfo](/docs/api/io/gimly/generated/card/model/KeyInfo.md)
- [KeyResults](/docs/api/io/gimly/generated/card/model/KeyResults.md)
- [KeyStatus](/docs/api/io/gimly/generated/card/model/KeyStatus.md)
- [LinkedTerminal](/docs/api/io/gimly/generated/card/model/LinkedTerminal.md)
- [OutputEncoding](/docs/api/io/gimly/generated/card/model/OutputEncoding.md)
- [Presentation](/docs/api/io/gimly/generated/card/model/Presentation.md)
- [Proof](/docs/api/io/gimly/generated/card/model/Proof.md)
- [SignCredentialRequest](/docs/api/io/gimly/generated/card/model/SignCredentialRequest.md)
- [SignCredentialResponse](/docs/api/io/gimly/generated/card/model/SignCredentialResponse.md)
- [SignInput](/docs/api/io/gimly/generated/card/model/SignInput.md)
- [SignMode](/docs/api/io/gimly/generated/card/model/SignMode.md)
- [SignOutput](/docs/api/io/gimly/generated/card/model/SignOutput.md)
- [SignOutputFromInput](/docs/api/io/gimly/generated/card/model/SignOutputFromInput.md)
- [SignPresentationRequest](/docs/api/io/gimly/generated/card/model/SignPresentationRequest.md)
- [SignPresentationResponse](/docs/api/io/gimly/generated/card/model/SignPresentationResponse.md)
- [SignRequest](/docs/api/io/gimly/generated/card/model/SignRequest.md)
- [SignResponse](/docs/api/io/gimly/generated/card/model/SignResponse.md)
- [StoredCredentialsResponse](/docs/api/io/gimly/generated/card/model/StoredCredentialsResponse.md)
- [VerifiableCredential](/docs/api/io/gimly/generated/card/model/VerifiableCredential.md)
- [VerifiablePresentation](/docs/api/io/gimly/generated/card/model/VerifiablePresentation.md)

<a name="documentation-for-authorization"></a>

## Documentation for Authorization

<a name="oauth2schema"></a>

### oauth2schema

- **Type**: OAuth
- **Flow**: application
- **Authorization URL**:
- **Scopes**:
  - global: accessEverything
