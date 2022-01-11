# Pokédex App
The Pokédex presents the user with all the Pokémon types. Upon choosing one, the user sees a list with all the Pokémon of the selected type. If one is clicked, a few more detailed information is shown. The app uses the PokéApi (https://pokeapi.co/) to get all of displayed information. This project was created in a 7 day window as a technical challenge. 

# Stack
- Kotlin
- Coroutines for async
- Retrofit2 and okhttp3 for the PokéApi bridge
- Kodein for DI
- Jetpack Compose for UI
- Coil for loading images
- Junit and Mockk for unit test
- Kotlinter as lint

# Next Steps / Improvements
- Persintence (Room)
- Would be nice to have a logger (Timber)
- Thought about making a domain endpoint myself (like in Firebase or Apiary) to check for Pokémon type colors and icons, since I didn't find this in the PokéApi. I implemented these data directly in the app using the `PokemonTypeEnum.kt` class as a faster solution. The app is going to be outdated if a new Pokémon type is created, so implementing a remote solution is a easy way to avoid this problem.

# Screenshots

<p align="middle">
  <img src="https://user-images.githubusercontent.com/21258742/149018771-a815232f-ce4a-452f-8158-5b780c962df0.png" width="30%"/>
  <img src="https://user-images.githubusercontent.com/21258742/149018767-7b45a3e4-8567-47c6-b7aa-32a4efb3b714.png" width="30%"/>
  <img src="https://user-images.githubusercontent.com/21258742/149018773-683c2181-e06c-43c5-bc86-5065db33b22b.png" width="30%"/>
</p>
