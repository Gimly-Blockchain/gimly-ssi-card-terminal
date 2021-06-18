<h2 style="text-align: center; vertical-align: middle">
    <center><a href="https://www.gimly.io/"><img src="https://avatars.githubusercontent.com/u/64525639?s=200&v=4" alt="Gimly" width="120" style="vertical-align: middle"></a></center></h2>

# Gimly SSI Card Terminal

Gimly SSI Card Terminal is a REST API for Self Sovereign Identity interactions between apps, servers, terminals having an NFC reader and NFC cards.

The API is used as an easy integration means for Desktop, Terminal and Server environments equipped with a NFC reader. The API is targeted at Self Sovereign Identity and Authentication use cases, meaning it can be used to create asymmetric keys for Decentralized Identifiers, as well as store and present Verifiable Credentials and Verifiable Presentations. Given the private key is securely stored in the NFC cards protected environment, it means the solution provides security for SSI use cases on desktop and terminal environments which typically would not be possible otherwise.

# Scanning a card
To start working with the NFC card, you typically have to scan the card first

Simply doing a `GET` call to the root `/` will return basic NFC card information once you tapped the NFC card to the reader.

# TODO
- Auth support on the endpoints itself
- Integration with GraalVM for native executables
- Windows service integration
- Docker support
- SDK generation for popular languages

