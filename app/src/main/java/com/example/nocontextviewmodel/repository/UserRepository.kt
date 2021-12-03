package com.example.nocontextviewmodel.repository

import com.example.nocontextviewmodel.utils.Resource
import com.example.nocontextviewmodel.utils.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class UserRepository(private val responseHandler: ResponseHandler) {

    suspend fun stimulateSocketTimeout() = flow {

        //stimulate a network call
        kotlinx.coroutines.delay(100)
        emit(
            //stimulate a failed network response
            if (false) {


                responseHandler
                    .handleSuccess("This should be api response")


            } else {
                Resource.Error(
                    responseHandler
                        .handleException<Int>(-1)
                )


            }
        )
    }.flowOn(Dispatchers.IO).catch { e ->
        e.printStackTrace()
        emit(

            Resource.Error(
                responseHandler
                    .handleException<Throwable>(e)
            )

        )


    }

    suspend fun stimulateUnAuthorizedError() = flow {

        //stimulate a network call
        kotlinx.coroutines.delay(100)
        emit(
            //stimulate a failed network response
            if (false) {


                responseHandler
                    .handleSuccess("This should be api response")


            } else {
                Resource.Error(
                    responseHandler
                        .handleException<Int>(401)
                )


            }
        )
    }.flowOn(Dispatchers.IO).catch { e ->
        e.printStackTrace()
        emit(

            Resource.Error(
                responseHandler
                    .handleException<Throwable>(e)
            )

        )


    }
    suspend fun stimulateSuccessfulNetworkCall()= flow {

        //stimulate a network call
        kotlinx.coroutines.delay(100)

        throw UnknownHostException()
        emit(
            //stimulate a failed network response
            if (true) {


                responseHandler
                    .handleSuccess("Sealed classes are awesome")


            } else {
                Resource.Error(
                    responseHandler
                        .handleException<Int>(401)
                )


            }
        )
    }.flowOn(Dispatchers.IO).catch { e ->
        e.printStackTrace()
        emit(

            Resource.Error(
                responseHandler
                    .handleException<Throwable>(e)
            )
        )


    }
}