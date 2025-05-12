package com.example.data.model

import com.google.gson.annotations.SerializedName

data class SourceDTO(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String = "",
)
