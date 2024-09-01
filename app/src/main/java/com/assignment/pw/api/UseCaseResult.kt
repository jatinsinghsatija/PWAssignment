package com.headway.mybzb.api

sealed class UseCaseResult<out T:Any>{
    class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    class Error(val exception: Exception) : UseCaseResult<Nothing>()
    object Empty : UseCaseResult<Nothing>()
}