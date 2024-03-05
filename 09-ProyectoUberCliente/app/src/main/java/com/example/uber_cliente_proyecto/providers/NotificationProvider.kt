package com.example.uber_cliente_proyecto.providers


import com.example.uber_cliente_proyecto.api.IFCMApi
import com.example.uber_cliente_proyecto.api.RetrofitClient
import com.example.uber_cliente_proyecto.models.FCMBody
import com.example.uber_cliente_proyecto.models.FCMResponse

import retrofit2.Call

class NotificationProvider {

    private val URL = "https://fcm.googleapis.com"

    fun sendNotification(body: FCMBody): Call<FCMResponse> {
        return RetrofitClient.getClient(URL).create(IFCMApi::class.java).send(body)
    }

}