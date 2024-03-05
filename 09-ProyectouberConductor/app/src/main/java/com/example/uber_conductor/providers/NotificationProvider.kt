package com.example.uber_conductor.providers

import com.example.uber_conductor.api.IFCMApi
import com.example.uber_conductor.api.RetrofitClient
import com.example.uber_conductor.models.FCMBody
import com.example.uber_conductor.models.FCMResponse

import retrofit2.Call

class NotificationProvider {

    private val URL = "https://fcm.googleapis.com"

    fun sendNotification(body: FCMBody): Call<FCMResponse> {
        return RetrofitClient.getClient(URL).create(IFCMApi::class.java).send(body)
    }

}