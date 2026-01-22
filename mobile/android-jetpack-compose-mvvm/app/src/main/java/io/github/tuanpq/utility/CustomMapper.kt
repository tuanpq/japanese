package io.github.tuanpq.utility

interface CustomMapper<in E, T> {
    fun mapList(from: List<E>): List<T>
    fun mapObject(from: E): T
}