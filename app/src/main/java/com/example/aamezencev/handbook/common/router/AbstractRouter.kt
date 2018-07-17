package com.example.aamezencev.handbook.common.router

abstract class AbstractRouter<L : MvpRouter.Listener> : MvpRouter<L> {
    var routerListener: L? = null

    override fun setListener(listener: L) {
        this.routerListener = listener
    }
}