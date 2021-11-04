package com.mota.pebblebee.data.dto

import com.google.gson.annotations.SerializedName
import com.mota.pebblebee.domain.model.User

data class UserDto (
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

fun UserDto.toUser(): User {
    return User(
        email = email,
        uuid = uuid,
        id = id,
        lastName = lastName,
        firstName = firstName
    )
}