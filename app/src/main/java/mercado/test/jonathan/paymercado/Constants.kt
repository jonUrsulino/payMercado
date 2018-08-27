package mercado.test.jonathan.paymercado

class Constants {

    companion object {
        const val SERVER_V1 = "https://api.mercadopago.com/v1/"

        const val PAYMENT_METHODS = "payment_methods"
        const val CARD_ISSUES = "payment_methods/card_issuers"
        const val INSTALLMENTS = "payment_methods/installments"

        const val PUBLIC_KEY_PARAM = "public_key"
        const val AMOUNT_PARAM = "amount"
        const val PAYMENT_METHOD_ID_PARAM = "payment_method_id"
        const val ISSUER_ID_PARAM = "issuer.id"

        const val PUBLIC_KEY_VALUE = "444a9ef5-8a6b-429f-abdf-587639155d88"
    }
}