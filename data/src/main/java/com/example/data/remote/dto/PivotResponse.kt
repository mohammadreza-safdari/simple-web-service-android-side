package com.example.data.remote.dto

import com.example.domain.model.Pivot
import com.google.gson.annotations.SerializedName

data class PivotResponse(
    @SerializedName("sport_id")
    val sportId: Int,
    @SerializedName("user_id")
    val userId: Int
)
fun PivotResponse.toPivot(): Pivot {
    return Pivot(
        sportId = sportId,
        userId = userId
    )
}
