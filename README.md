<h2 style="text-align: center; vertical-align: middle">
    <center><a href="https://www.gimly.io/"><img src="https://avatars.githubusercontent.com/u/64525639?s=200&v=4" alt="Gimly" width="120" style="vertical-align: middle"></a></center></h2>

# Gimly SSI Card Terminal

Gimly SSI Card Terminal is a REST API for Self Sovereign Identity interactions between apps, servers, terminals having an NFC reader and NFC cards.

The API is used as an easy integration means for Desktop, Terminal and Server environments equipped with a NFC reader. The API is targeted at Self Sovereign Identity and Authentication use cases, meaning it can be used to create asymmetric keys for Decentralized Identifiers, as well as store and present Verifiable Credentials and Verifiable Presentations. Given the private key is securely stored in the NFC cards protected environment, it means the solution provides security for SSI use cases on desktop and terminal environments which typically would not be possible otherwise.

# API Documentation

The below examples are the most common use cases.

The full [API documentation](./docs/api/README.md) including all endpoints can be found [here](./docs/api/README.md)

To view the API definition online open [editor.swagger.io](https://gimly-blockchain.github.io/gimly-ssi-card-terminal)

# Scanning a card

To start working with the NFC card, you typically have to scan the card first

Simply doing a `GET` call to the root `/` will return basic NFC card information once you tapped the NFC card to the reader.

<a name="scanCard"></a>

### **scanCard**

> CardInfoResult scanCard()

### Parameters

This endpoint does not need any parameter.

### Return type

[**CardInfoResult**](docs/api/io/gimly/generated/card/model/CardInfoResult.md)

# Creating a key(pair) on the NFC card

This creates an asymmetric keypair on the NFC card. The private key will never be disclosed and is safely stored in the card. The public key is disclosed. The key can be used as a regular keypair, not using DIDs at all if desired. To access and use the key later you can use the public key value, its card index or the DID Key id value

### **createKey**

> KeyResults createKey(cardId, CreateKeyRequest)

### Parameters

| Name                 | Type                                                                               | Description      | Notes                        |
| -------------------- | ---------------------------------------------------------------------------------- | ---------------- | ---------------------------- |
| **cardId**           | **String**                                                                         | The Id of a card | [optional] [default to null] |
| **CreateKeyRequest** | [**CreateKeyRequest**](docs/api/io/gimly/generated/card/model/CreateKeyRequest.md) |                  | [optional]                   |

### Return type

[**KeyResults**](docs/api/io/gimly/generated/card/model/KeyResults.md)

# Sign using the key on the NFC card

This method allows you to sign one or more inputs using the private key stored on the NFC card.

<a name="signUsingKey"></a>

### **signUsingKey**

> SignResponse signUsingKey(keyId, SignRequest, cardId)

### Parameters

| Name            | Type                                                                     | Description                                                 | Notes                        |
| --------------- | ------------------------------------------------------------------------ | ----------------------------------------------------------- | ---------------------------- |
| **keyId**       | **String**                                                               | The Key index, public key or DID/Verification method key id | [default to null]            |
| **SignRequest** | [**SignRequest**](docs/api/io/gimly/generated/card/model/SignRequest.md) | Signs one or more inputs, typically hashes in hex format    |
| **cardId**      | **String**                                                               | The Id of a card                                            | [optional] [default to null] |

### Return type

[**SignResponse**](docs/api/io/gimly/generated/card/model/SignResponse.md)

# Add a proof to a credential

This method adds a proof to the supplied credential, using the private key on the NFC card and thus making it a Verifiable Credential. It allows for optional storage of the VC on the NFC card.

### **signCredential**

> SignCredentialResponse signCredential(keyId, SignCredentialRequest, cardId)

### Parameters

| Name                      | Type                                                                                         | Description                                                 | Notes                        |
| ------------------------- | -------------------------------------------------------------------------------------------- | ----------------------------------------------------------- | ---------------------------- |
| **keyId**                 | **String**                                                                                   | The Key index, public key or DID/Verification method key id | [default to null]            |
| **SignCredentialRequest** | [**SignCredentialRequest**](docs/api/io/gimly/generated/card/model/SignCredentialRequest.md) | Signs one or more inputs, typically hashes in hex format    |
| **cardId**                | **String**                                                                                   | The Id of a card                                            | [optional] [default to null] |

### Return type

[**SignCredentialResponse**](docs/api/io/gimly/generated/card/model/SignCredentialResponse.md)

# Add a proof to a presentation

Sign ths supplied presentation using the key on the NFC card, adding a proof and making it a verifiable presentation. Please note that verifiable presentations cannot be stored, as the nature of Verifiable Presentations is to use them on singular invocations only

<a name="signPresentation"></a>

### **signPresentation**

> SignPresentationResponse signPresentation(keyId, SignPresentationRequest, cardId)

Signs a presentation (adds a proof)

### Parameters

| Name                        | Type                                                                                             | Description                                                 | Notes                        |
| --------------------------- | ------------------------------------------------------------------------------------------------ | ----------------------------------------------------------- | ---------------------------- |
| **keyId**                   | **String**                                                                                       | The Key index, public key or DID/Verification method key id | [default to null]            |
| **SignPresentationRequest** | [**SignPresentationRequest**](docs/api/io/gimly/generated/card/model/SignPresentationRequest.md) | Signs a presentation                                        |
| **cardId**                  | **String**                                                                                       | The Id of a card                                            | [optional] [default to null] |

### Return type

[**SignPresentationResponse**](docs/api/io/gimly/generated/card/model/SignPresentationResponse.md)

# Get all Verifiable Credentials stored on the NFC card

Verified Credentials that are self-issued as well as externally issued with a subject that related to the NFC card, can be stored on the NFC card. This method returns all stored Verifiable Credentials.

<a name="getStoredCredentials"></a>

### **getStoredCredentials**

> StoredCredentialsResponse getStoredCredentials(cardId)

### Parameters

| Name       | Type       | Description      | Notes                        |
| ---------- | ---------- | ---------------- | ---------------------------- |
| **cardId** | **String** | The Id of a card | [optional] [default to null] |

### Return type

[**StoredCredentialsResponse**](docs/api/io/gimly/generated/card/model/StoredCredentialsResponse.md)

# TODO

- Mock testing once we have the latest cards
- Auth support on the endpoints itself
- Integration with GraalVM for native executables
- Windows service integration
- Docker support with USB?
- Do we want direct DID creation support, instead of external application created DIDs and only KID resolution?
- SDK generation for popular languages
