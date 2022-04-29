# KeyApi

All URIs are relative to _http://localhost:8080/gimly-ssi-card-terminal/0.1_

| Method                                   | HTTP request             | Description                                           |
| ---------------------------------------- | ------------------------ | ----------------------------------------------------- |
| [**createKey**](KeyApi.md#createKey)     | **POST** /keys           | Create a new key                                      |
| [**deactivateKey**](KeyApi.md#deactivateKey) | **DELETE** /keys/{keyId} | Deactivate a key by card Index, public key or DID key |
| [**getKey**](KeyApi.md#getKey)           | **GET** /keys/{keyId}    | Get a key by card Index, public key or DID key        |
| [**getKeys**](KeyApi.md#getKeys)         | **GET** /keys            | Get all keys                                          |

<a name="createKey"></a>

# **createKey**

> KeyResults createKey(cardId, CreateKeyRequest)

Create a new key

    Create a new key.

### Parameters

| Name                 | Type                                                                                | Description      | Notes                        |
| -------------------- | ----------------------------------------------------------------------------------- | ---------------- | ---------------------------- |
| **cardId**           | **String**                                                                          | The Id of a card | [optional] [default to null] |
| **CreateKeyRequest** | [**CreateKeyRequest**](/docs/api/io/gimly/generated/card/model/CreateKeyRequest.md) |                  | [optional]                   |

### Return type

[**KeyResults**](/docs/api/io/gimly/generated/card/model/KeyResults.md)

### Authorization

[oauth2schema](/docs/api/README.md#oauth2schema)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

<a name="deactivateKey"></a>

# **deactivateKey**

> KeyInfo deactivateKey(keyId, cardId)

Deactivate a key by card Index, public key or DID key

    Deactivate a key by card index, publickey or DID key

### Parameters

| Name       | Type       | Description                                                 | Notes                        |
| ---------- | ---------- | ----------------------------------------------------------- | ---------------------------- |
| **keyId**  | **String** | The Key index, public key or DID/Verification method key id | [default to null]            |
| **cardId** | **String** | The Id of a card                                            | [optional] [default to null] |

### Return type

[**KeyInfo**](/docs/api/io/gimly/generated/card/model/KeyInfo.md)

### Authorization

[oauth2schema](/docs/api/README.md#oauth2schema)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

<a name="getKey"></a>

# **getKey**

> KeyInfo getKey(keyId, cardId)

Get a key by card Index, public key or DID key

    Get a key by card index, publickey or DID key

### Parameters

| Name       | Type       | Description                                                 | Notes                        |
| ---------- | ---------- | ----------------------------------------------------------- | ---------------------------- |
| **keyId**  | **String** | The Key index, public key or DID/Verification method key id | [default to null]            |
| **cardId** | **String** | The Id of a card                                            | [optional] [default to null] |

### Return type

[**KeyInfo**](/docs/api/io/gimly/generated/card/model/KeyInfo.md)

### Authorization

[oauth2schema](/docs/api/README.md#oauth2schema)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

<a name="getKeys"></a>

# **getKeys**

> KeyResults getKeys(cardId)

Get all keys

    Get existing keys

### Parameters

| Name       | Type       | Description      | Notes                        |
| ---------- | ---------- | ---------------- | ---------------------------- |
| **cardId** | **String** | The Id of a card | [optional] [default to null] |

### Return type

[**KeyResults**](/docs/api/io/gimly/generated/card/model/KeyResults.md)

### Authorization

[oauth2schema](/docs/api/README.md#oauth2schema)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json
