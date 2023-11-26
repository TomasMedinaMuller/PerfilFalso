package com.example.perfilfalso

data class Info (
    val seed: String,
    val results: Long,
    val page: Long,
    val version: String
)

data class RandomUserResponse(
    val results: List<Result>
)

data class Result (
    val gender: String,
    val name: Name,
    val location: Location,
    val picture: Picture,
)

data class Location (
    val city: String,
    val state: String,
    val country: String,
    val postcode: Long,
)


data class Name (
    val title: String,
    val first: String,
    val last: String
)

data class Picture (
    val large: String,
    val medium: String,
    val thumbnail: String
)

