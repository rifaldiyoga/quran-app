package com.rifaldi.yoga.quranapp.presentation.utils

/**
 * Created by aldi on 05/04/22.
 */
class Resource<out T>(val status: Status, val data: T?, val message: String?, val code: Int?) {

    enum class Status {
        LOADING, SUCCESS, ERROR
    }

    companion object {
        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null, null, )
        }

        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null,)
        }

        fun <T> error(data: T?, msg: String, code: Int): Resource<T> {
            return Resource(Status.ERROR, data, msg, code,)
        }
    }

}