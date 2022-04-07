# StorageApi

All URIs are relative to _http://localhost:8080/gimly-ssi-card-terminal/0.1_

| Method                                                             | HTTP request                                   | Description                            |
| ------------------------------------------------------------------ | ---------------------------------------------- | -------------------------------------- |
| [**deleteStoredCredential**](StorageApi.md#deleteStoredCredential) | **DELETE** /storage/credentials/{credentialId} | Deletes a stored Verifiable credential |
| [**getStoredCredential**](StorageApi.md#getStoredCredential)       | **GET** /storage/credentials/{credentialId}    | Gets a stored Verifiable credential    |
| [**getStoredCredentials**](StorageApi.md#getStoredCredentials)     | **GET** /storage/credentials                   | Gets all stored Verifiable Credentials |

<a name="deleteStoredCredential"></a>

# **deleteStoredCredential**

> VerifiableCredential deleteStoredCredential(credentialId, cardId)

Deletes a stored Verifiable credential

    Deletes the Verifiable Credentials from the NFC card

### Parameters

| Name             | Type       | Description       | Notes                        |
| ---------------- | ---------- | ----------------- | ---------------------------- |
| **credentialId** | **String** | The Credential Id | [default to null]            |
| **cardId**       | **String** | The Id of a card  | [optional] [default to null] |

### Return type

[**VerifiableCredential**](/docs/api/io/gimly/generated/card/model/VerifiableCredential.md)

### Authorization

[oauth2schema](/docs/api/README.md#oauth2schema)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

<a name="getStoredCredential"></a>

# **getStoredCredential**

> VerifiableCredential getStoredCredential(credentialId, cardId)

Gets a stored Verifiable credential

    Gets the Verifiable Credentials by id from the NFC card

### Parameters

| Name             | Type       | Description       | Notes                        |
| ---------------- | ---------- | ----------------- | ---------------------------- |
| **credentialId** | **String** | The Credential Id | [default to null]            |
| **cardId**       | **String** | The Id of a card  | [optional] [default to null] |

### Return type

[**VerifiableCredential**](/docs/api/io/gimly/generated/card/model/VerifiableCredential.md)

### Authorization

[oauth2schema](/docs/api/README.md#oauth2schema)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

<a name="getStoredCredentials"></a>

# **getStoredCredentials**

> StoredCredentialsResponse getStoredCredentials(cardId)

Gets all stored Verifiable Credentials

    Gets all stored Verifiable Credentials from the NFC card

### Parameters

| Name       | Type       | Description      | Notes                        |
| ---------- | ---------- | ---------------- | ---------------------------- |
| **cardId** | **String** | The Id of a card | [optional] [default to null] |

### Return type

[**StoredCredentialsResponse**](/docs/api/io/gimly/generated/card/model/StoredCredentialsResponse.md)

### Authorization

[oauth2schema](/docs/api/README.md#oauth2schema)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json
