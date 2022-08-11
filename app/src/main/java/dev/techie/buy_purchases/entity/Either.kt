package dev.techie.buy_purchases.entity

sealed class Either<A, B> {
    data class Left<A, B>(val a: A) : Either<A, B>()
    data class Right<A, B>(val b: B) : Either<A, B>()
}

typealias Result<T> = Either<T, Exception>
fun <T> Success(t: T): Result<T> = Either.Left(t)
fun <T> Failure(e: Exception): Result<T> = Either.Right(e)
