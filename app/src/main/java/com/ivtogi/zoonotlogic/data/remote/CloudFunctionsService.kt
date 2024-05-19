package com.ivtogi.zoonotlogic.data.remote

import retrofit2.http.POST

interface CloudFunctionsService {
    @POST("paymentSheet")
    fun getPaymentSheet(): Result<Any>
    // TODO: CREAR EL DTO PARA EL PAYMENTSHEET Y ACABAR EL PAGO
}