package com.example.aamezencev.handbook.domain.common

import io.reactivex.Observable

interface SessionInitializer<T : Any> {
    fun initSesseion(): Observable<T>
}