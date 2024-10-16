package com.arseniy.blogapp.network.domain.dto

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable


@Serializable
data class Pageable(
    val sort: Sort,
    val offset: Long,
    val pageNumber: Int,
    val pageSize: Int,
    val paged: Boolean,
    val unpaged: Boolean
)

@Serializable
data class Sort(
    val sorted: Boolean,
    val unsorted: Boolean,
    val empty: Boolean
)


@Serializable
data class PageResponse<T>(

    val content : List<T>,

    val pageable : Pageable,
    val totalPages : Int,
    val totalElements: Int,
    val last: Boolean,
    val size: Int,
    val number: Int,
    val sort: Sort,
    val numberOfElements: Int,
    val first: Boolean,
    val empty: Boolean

)
