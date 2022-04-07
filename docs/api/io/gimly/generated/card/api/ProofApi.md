# ProofApi

All URIs are relative to _http://localhost:8080/gimly-ssi-card-terminal/0.1_

| Method                                               | HTTP request                               | Description                         |
| ---------------------------------------------------- | ------------------------------------------ | ----------------------------------- |
| [**signCredential**](ProofApi.md#signCredential)     | **PUT** /keys/{keyId}/proofs/credentials   | Signs a credential (adds a proof)   |
| [**signPresentation**](ProofApi.md#signPresentation) | **PUT** /keys/{keyId}/proofs/presentations | Signs a presentation (adds a proof) |
| [**signUsingKey**](ProofApi.md#signUsingKey)         | **PUT** /keys/{keyId}/proofs/signatures    | Sign one or more inputs             |

<a name="signCredential"></a>

# **signCredential**

> SignCredentialResponse signCredential(keyId, SignCredentialRequest, cardId)

Signs a credential (adds a proof)

    Sign a credential, adding a proof and making it a verifiable credential

### Parameters

| Name                      | Type                                                                                          | Description                                                 | Notes                        |
| ------------------------- | --------------------------------------------------------------------------------------------- | ----------------------------------------------------------- | ---------------------------- |
| **keyId**                 | **String**                                                                                    | The Key index, public key or DID/Verification method key id | [default to null]            |
| **SignCredentialRequest** | [**SignCredentialRequest**](/docs/api/io/gimly/generated/card/model/SignCredentialRequest.md) | Signs one or more inputs, typically hashes in hex format    |
| **cardId**                | **String**                                                                                    | The Id of a card                                            | [optional] [default to null] |

### Return type

[**SignCredentialResponse**](/docs/api/io/gimly/generated/card/model/SignCredentialResponse.md)

### Authorization

[oauth2schema](/docs/api/README.md#oauth2schema)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

<a name="signPresentation"></a>

# **signPresentation**

> SignPresentationResponse signPresentation(keyId, SignPresentationRequest, cardId)

Signs a presentation (adds a proof)

    Sign a presentation, adding a proof and making it a verifiable presentation

### Parameters

| Name                        | Type                                                                                              | Description                                                 | Notes                        |
| --------------------------- | ------------------------------------------------------------------------------------------------- | ----------------------------------------------------------- | ---------------------------- |
| **keyId**                   | **String**                                                                                        | The Key index, public key or DID/Verification method key id | [default to null]            |
| **SignPresentationRequest** | [**SignPresentationRequest**](/docs/api/io/gimly/generated/card/model/SignPresentationRequest.md) | Signs a presentation                                        |
| **cardId**                  | **String**                                                                                        | The Id of a card                                            | [optional] [default to null] |

### Return type

[**SignPresentationResponse**](/docs/api/io/gimly/generated/card/model/SignPresentationResponse.md)

### Authorization

[oauth2schema](/docs/api/README.md#oauth2schema)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

<a name="signUsingKey"></a>

# **signUsingKey**

> SignResponse signUsingKey(keyId, SignRequest, cardId)

Sign one or more inputs

    Sign one or more inputs using the provided key

### Parameters

| Name            | Type                                                                      | Description                                                 | Notes                        |
| --------------- | ------------------------------------------------------------------------- | ----------------------------------------------------------- | ---------------------------- |
| **keyId**       | **String**                                                                | The Key index, public key or DID/Verification method key id | [default to null]            |
| **SignRequest** | [**SignRequest**](/docs/api/io/gimly/generated/card/model/SignRequest.md) | Signs one or more inputs, typically hashes in hex format    |
| **cardId**      | **String**                                                                | The Id of a card                                            | [optional] [default to null] |

### Return type

[**SignResponse**](/docs/api/io/gimly/generated/card/model/SignResponse.md)

### Authorization

[oauth2schema](/docs/api/README.md#oauth2schema)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json
