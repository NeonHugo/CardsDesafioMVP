package com.nm.commons.net.data

import com.nm.commons.net.ApiError
import com.nm.commons.net.ErrorResults
import com.nm.commons.net.Results
import com.nm.commons.net.SuccessResults

//import retrofit2.Response

//// Retrofit
//fun <T, O> Response<T>.create(mapper: Mapper<T, O>): Results<O> {
//    return transformResponse(this).converter(mapper)
//}
//
//fun <T> transformResponse(response: Response<T>): ApiResponse<T> {
//    return if (response.isSuccessful) {
//        val body = response.body()
//        if (body == null || response.code() == 204 || (body is List<*> && body.size == 0)) ApiErrorResponse(
//            ApiError(
//                response.code().toString(),
//                response.message()
//            )
//        )
//        else ApiSuccessResponse(body = body)
//    } else {
//        ApiErrorResponse(
//            ApiError(
//                response.code().toString(),
//                response.message()
//            )
//        )
//    }
//}

//sealed class ApiResponse<T>
//data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()
//data class ApiErrorResponse<T>(val error: ApiError) : ApiResponse<T>()
//
//private fun <T, O> ApiResponse<T>.converter(mapper: Mapper<T, O>): Results<O> {
//    return when (this) {
//        is ApiSuccessResponse -> {
//            val itemConverted = mapper.transform(body)
//            SuccessResults(itemConverted)
//        }
//        is ApiErrorResponse -> ErrorResults(
//            error
//        )
//    }
//}