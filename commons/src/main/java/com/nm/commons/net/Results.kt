package com.nm.commons.net

sealed class Results<T>
data class SuccessResults<T>(val body: T) : Results<T>()
data class ErrorResults<T>(val error: ApiError) : Results<T>()