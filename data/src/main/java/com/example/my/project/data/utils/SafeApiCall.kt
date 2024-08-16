package com.example.my.project.data.utils


import com.example.my.project.domain.util.Result

suspend fun <T> safeApiCall(apiCall : suspend ()->T): Result<T> = try {
    Result.Success(apiCall.invoke())
}catch (e: Exception){
    Result.Error(e)
}