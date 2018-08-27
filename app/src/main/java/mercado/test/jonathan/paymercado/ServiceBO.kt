package mercado.test.jonathan.paymercado

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceBO {

    companion object {

        private fun service(): ServiceMercado {
            val fit = Retrofit.Builder()
                    .baseUrl(Constants.SERVER_V1)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return fit.create(ServiceMercado::class.java)
        }

        fun getOptionsPayment(listener: FeedbackRequest) {
            val paymentOptions = service().getPaymentOptions()
            paymentOptions.enqueue(object : Callback<List<PaymentType>> {
                override fun onResponse(call: Call<List<PaymentType>>?, response: Response<List<PaymentType>>?) {
                    val list = response?.body()
                    Log.d("Main", "pay: $list")
                    Log.d("Main", "pay url: ${call?.request()?.url()}")

                    listener.onSuccessPaymentType(list)
                }

                override fun onFailure(call: Call<List<PaymentType>>?, t: Throwable?) {
                    Log.e("Main", "Error PaymentType: $t")
                }
            })
        }

        fun getOptionsCards(listener: FeedbackRequest, paymentType: String) {
            val paymentOptions = service().getCardIssues(paymentType)
            paymentOptions.enqueue(object : Callback<List<PaymentType>> {
                override fun onResponse(call: Call<List<PaymentType>>?, response: Response<List<PaymentType>>?) {
                    val list = response?.body()

                    Log.d("Main", "card: $list")
                    Log.d("Main", "card url: ${call?.request()?.url()}")

                    listener.onSuccessCards(list)
                }

                override fun onFailure(call: Call<List<PaymentType>>?, t: Throwable?) {
                    Log.e("Main", "Error Bank: $t")
                }
            })
        }

        fun getInstallments(listener: FeedbackRequest, paymentType: String, id: String, amount: String) {
            val paymentOptions = service().getInstallments(paymentType, id, amount)
            paymentOptions.enqueue(object : Callback<List<Installments>> {
                override fun onResponse(call: Call<List<Installments>>?, response: Response<List<Installments>>?) {
                    val body = response?.body()
                    Log.d("Main", "Installments: $body")
                    Log.d("Main", "Installments url: ${call?.request()?.url()}")

                    listener.onSuccessInstallments(body)
                }

                override fun onFailure(call: Call<List<Installments>>?, t: Throwable?) {
                    Log.e("Main", "Error Installments: $t")
                }

            })
        }
    }
}

interface FeedbackRequest {
    fun onSuccessPaymentType(list: List<PaymentType>?)
    fun onSuccessCards(list: List<PaymentType>?)
    fun onSuccessInstallments(body: List<Installments>?)
}