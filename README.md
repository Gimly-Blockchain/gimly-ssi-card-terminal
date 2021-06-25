<h2 style="text-align: center; vertical-align: middle">
    <center><a href="https://www.gimly.io/"><img src="https://avatars.githubusercontent.com/u/64525639?s=200&v=4" alt="Gimly" width="120" style="vertical-align: middle"></a></center></h2>

# Gimly SSI Card Terminal

Gimly SSI Card Terminal is a REST API for Self Sovereign Identity interactions between apps, servers, terminals having an NFC reader and NFC cards.

The API is used as an easy integration means for Desktop, Terminal and Server environments equipped with a NFC reader. The API is targeted at Self Sovereign Identity and Authentication use cases, meaning it can be used to create asymmetric keys for Decentralized Identifiers, as well as store and present Verifiable Credentials and Verifiable Presentations. Given the private key is securely stored in the NFC cards protected environment, it means the solution provides security for SSI use cases on desktop and terminal environments which typically would not be possible otherwise.

# API Documentation
The below examples are the most common use cases.

The full [API documentation](./docs/api/README.md) including all endpoints can be found [here](./docs/api/README.md)

To view the API definition online open [editor.swagger.io](https://editor.swagger.io/?url=https://raw.githubusercontent.com/Gimly-Blockchain/gimly-ssi-card-terminal/main/src/main/resources/api.yaml)



# Scanning a card
To start working with the NFC card, you typically have to scan the card first

Simply doing a `GET` call to the root `/` will return basic NFC card information once you tapped the NFC card to the reader.

<a name="scanCard"></a>
### **scanCard**
> CardInfoResult scanCard()

### Parameters
This endpoint does not need any parameter.

### Return type

[**CardInfoResult**](./docs/api/io.gimly.generated.card.model/CardInfoResult.md)


# Creating a key(pair) on the NFC card
This creates an asymmetric keypair on the NFC card. The private key will never be disclosed and is safely stored in the card. The public key is disclosed. The key can be used as a regular keypair, not using DIDs at all if desired. To access and use the key later you can use the public key value, its card index or the DID Key id value 


### **createKey**
> KeyResults createKey(cardId, CreateKeyRequest)

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**cardId** | **String**| The Id of a card | [optional] [default to null]
**CreateKeyRequest** | [**CreateKeyRequest**](./docs/api/io.gimly.generated.card.model/CreateKeyRequest.md)|  | [optional]

### Return type

[**KeyResults**](./docs/api/io.gimly.generated.card.model/KeyResults.md)


# Sign using the key on the NFC card

This method allows you to sign one or more inputs using the private key stored on the NFC card. 

<a name="signUsingKey"></a>
### **signUsingKey**
> SignResponse signUsingKey(keyId, SignRequest, cardId)

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**keyId** | **String**| The Key index, public key or DID/Verification method key id | [default to null]
**SignRequest** | [**SignRequest**](../io.gimly.generated.card.model/SignRequest.md)| Signs one or more inputs, typically hashes in hex format |
**cardId** | **String**| The Id of a card | [optional] [default to null]

### Return type

[**SignResponse**](../io.gimly.generated.card.model/SignResponse.md)

# TODO
- Auth support on the endpoints itself
- Integration with GraalVM for native executables
- Windows service integration
- Docker support
- Do we want direct DID creation support, instead of external application created DIDs and only KID resolution?
- SDK generation for popular languages

