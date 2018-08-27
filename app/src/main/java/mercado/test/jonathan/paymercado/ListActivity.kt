package mercado.test.jonathan.paymercado

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity(), FeedbackRequest {

    private var paymentType: String? = null
    private var amount: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        paymentType = intent.extras["meio_pagamento"] as String
        amount = intent.extras["quantidade"] as String

        if (paymentType != null) {
            ServiceBO.getOptionsCards(this, paymentType!!)
        }
    }

    private fun loadData(list: List<PaymentType>?) {
        list?.let {

            val layoutManager = LinearLayoutManager(this@ListActivity)
            recyclerView.layoutManager = layoutManager

            val recyclerViewAdapter = RecyclerAdapter(applicationContext, it) {
                Log.d("Main", "item clicado $it")

                val intent = Intent()
                intent.putExtra("meio_pagamento", paymentType)
                intent.putExtra("quantidade", amount)
                intent.putExtra("id", it.id.toString())
                this@ListActivity.setResult(Activity.RESULT_OK, intent)
                this@ListActivity.finish()
            }
            recyclerView.adapter = recyclerViewAdapter
        }
    }

    override fun onSuccessCards(list: List<PaymentType>?) {
        loadData(list)
    }

    override fun onSuccessPaymentType(list: List<PaymentType>?) {}

    override fun onSuccessInstallments(body: List<Installments>?) {}
}
