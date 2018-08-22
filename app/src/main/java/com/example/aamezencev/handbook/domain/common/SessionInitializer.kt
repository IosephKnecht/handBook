package com.example.aamezencev.handbook.domain.common

import io.reactivex.Observable

interface SessionInitializer<T> {
    fun initSesseion(): Observable<T>
}