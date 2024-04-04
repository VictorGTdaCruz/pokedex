package com.victor.network

sealed class PokedexException(message: String) : RuntimeException(message) {
    class ConnectionException : PokedexException("Parece que há algum problema com a conexão.")
    class FormatException : PokedexException("O dev foi burro :( Por favor, entre em contato!")
    class UnexpectedException : PokedexException("Ops, algo deu errado. Tente novamente!")
}