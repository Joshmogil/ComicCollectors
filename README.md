#Comic Collectors

Frontend built using Vuex, backend API with Java Spring. Integrated third-party comic resources from external Marvel Comics API into backend.

My contributions to this project were styling and design of front-end, database structure and schemas, backend API endpoints, and the access to the external Marvel Comics API.

Access to external comic/character data is done through an instance of the MarvelComicService class, code for that found in this file:

https://github.com/Joshmogil/ComicCollectors/blob/main/java/src/main/java/com/techelevator/services/MarvelComicService.java

It is not super clean, but the basic pieces are:

1. It uses rest templates to grab raw JSON (individual comic details, lists of comic details, lists of characters).
2. It converts the raw JSON from the response body to a string then uses an algorithim to parse and work through the string relying on regular patterns.
3. It creates objects (comic, character, list<comic/character>) from the parsed data then returns them.

Finally, our own API's service layers can call useful functions from their own instances of a MarvelComicService.

