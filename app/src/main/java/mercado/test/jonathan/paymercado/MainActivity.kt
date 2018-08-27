package mercado.test.jonathan.paymercado

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast

class MainActivity : AppCompatActivity(), FeedbackRequest {

    private var itemSelected: PaymentType? = null

    companion object {
        const val REQUEST_CODE = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ServiceBO.getOptionsPayment(this)

        buttonContinue.setOnClickListener {
            if (itemSelected != null) {
                val intent = Intent(this, ListActivity::class.java)
                intent.putExtra("meio_pagamento", itemSelected?.id)
                intent.putExtra("quantidade", amountView.text.toString())

                startActivityForResult(intent, REQUEST_CODE)
            }
        }
    }

    private fun verifyToEnableContinue() {
        buttonContinue.isEnabled = itemSelected != null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            REQUEST_CODE -> if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    val paymentType = data.extras["meio_pagamento"] as String
                    val id = data.extras["id"] as String
                    val amount = data.extras["quantidade"] as String

                    ServiceBO.getInstallments(this, paymentType, id, amount)
                }
            }
        }
    }

    private fun loadData(list: List<PaymentType>?) {
        list?.let {
            val layoutManager = LinearLayoutManager(this@MainActivity)
            listView.layoutManager = layoutManager

            val recyclerViewAdapter = RecyclerAdapter(applicationContext, it) {
                Log.d("Main", "item clicado $it")
                itemSelected = it
                verifyToEnableContinue()
            }
            listView.adapter = recyclerViewAdapter
        }
    }

    private fun showResult(body: List<Installments>?) {
        val list = body?.get(0)?.payer_costs

        list?.forEach {
            Toast.makeText(this@MainActivity, "Resultado: ${it.recommended_message}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onSuccessPaymentType(list: List<PaymentType>?) {
        loadData(list)
    }

    override fun onSuccessInstallments(body: List<Installments>?) {
        showResult(body)
    }

    override fun onSuccessCards(list: List<PaymentType>?) {}
}

