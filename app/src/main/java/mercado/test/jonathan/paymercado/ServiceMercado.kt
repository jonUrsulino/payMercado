package mercado.test.jonathan.paymercado

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceMercado {

    @GET(Constants.PAYMENT_METHODS)
    fun getPaymentOptions(
            @Query(Constants.PUBLIC_KEY_PARAM) key: String = Constants.PUBLIC_KEY_VALUE): Call<List<PaymentType>>

    @GET(Constants.CARD_ISSUES)
    fun getCardIssues(
            @Query(Constants.PAYMENT_METHOD_ID_PARAM) paymentType: String,
            @Query(Constants.PUBLIC_KEY_PARAM) key: String = Constants.PUBLIC_KEY_VALUE
    ): Call<List<PaymentType>>

    @GET(Constants.INSTALLMENTS)
    fun getInstallments(
            @Query(Constants.PAYMENT_METHOD_ID_PARAM) paymentType: String,
            @Query(Constants.ISSUER_ID_PARAM) issuerId: String,
            @Query(Constants.AMOUNT_PARAM) amount: String,
            @Query(Constants.PUBLIC_KEY_PARAM) key: String = Constants.PUBLIC_KEY_VALUE
    ): Call<List<Installments>>

}