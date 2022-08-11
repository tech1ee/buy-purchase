package dev.techie.buy_purchases.data.api

import dev.techie.buy_purchases.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor: Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (chain.request().header(AUTH_HEADER) == null) {
            val newRequest = chain.request().newBuilder()
                .addHeader(AUTH_HEADER, BuildConfig.API_KEY)
                .build()
            return chain.proceed(newRequest)
        }
        return chain.proceed(chain.request())
    }

    companion object {
        private const val AUTH_HEADER = "apikey"
    }
}