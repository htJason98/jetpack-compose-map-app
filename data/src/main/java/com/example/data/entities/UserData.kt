package com.example.data.entities

import com.google.gson.annotations.SerializedName
import com.example.domain.entities.User

data class UserData (
    val email: String,
    val uuid: String,
    val id: String,
    val lastName: String,
    val firstName: String,
    @SerializedName("join_date")
    val joinDate: String,
    @SerializedName("cached_at")
    val cachedAt: String,
    @SerializedName("image_url")
    val imageUrl: String
)

fun UserData.toUser(): User {
    return User(
        email = email,
        uuid = uuid,
        id = id,
        lastName = lastName,
        firstName = firstName,
        joinDate = joinDate,
        cachedAt = cachedAt,
        imageUrl = imageUrl
    )
}
