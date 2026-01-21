package io.github.tuanpq.utility

interface CustomMapper<in E, T> {
    fun map(from: List<E>): List<T>
}