package mercado.test.jonathan.paymercado

class PaymentType(val id: String, val name: String, val thumbnail: String)

class Installments(val payer_costs: List<PayerCosts>)
class PayerCosts(val installments: Int, val recommended_message: String)