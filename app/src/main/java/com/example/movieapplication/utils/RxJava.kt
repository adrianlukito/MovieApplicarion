package com.example.movieapplication.utils

import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

fun <T> Single<T>.customSubscribe(success: (T) -> Unit, error: (Throwable) -> Unit): Disposable {
    return this.subscribe(Consumer {
        success(it)
    }, {
        error(it)
    })
}