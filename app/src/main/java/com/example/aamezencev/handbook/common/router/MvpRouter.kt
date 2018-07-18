package com.example.aamezencev.handbook.common.router

interface MvpRouter<L : MvpRouter.Listener> {
    fun setListener(listener: L?)

    interface Listener
}