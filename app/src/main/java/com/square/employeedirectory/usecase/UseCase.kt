package com.square.employeedirectory.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

abstract class UseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            withContext(coroutineDispatcher) {
                Result.success(execute(parameters))
            }
        } catch (e: Exception) {
            Timber.e(e)
            Result.failure(e)
        }
    }

    abstract suspend fun execute(parameters: P): R
}