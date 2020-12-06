package id.kotlin.pengunjungnf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import id.kotlin.anggota.Config.NetworkModule
import id.kotlin.pengunjungnf.Model.action.ResponseAction
import id.kotlin.pengunjungnf.Model.getData.DataItem
import kotlinx.android.synthetic.main.activity_input.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val getData = intent.getParcelableExtra<DataItem>("data")

        if(getData != null){
            etNama.setText(getData.nama)
            etNohp.setText(getData.nohp)
            etAlamat.setText(getData.alamat)
            etJenis.setText(getData.jenis)
            etRiwayat.setText(getData.riwayatPerjalanan)

            button1.text = "Update"
        }

        when(button1.text){
            "Update" ->{
                button1.setOnClickListener{
                    updateData(getData?.id, etNama.text.toString(), etNohp.text.toString(), etAlamat.text.toString(), etJenis.text.toString(), etRiwayat.text.toString())
                }
            }else ->{
            button1.setOnClickListener{
                inputData(etNama.text.toString(), etNohp.text.toString(), etAlamat.text.toString(), etJenis.text.toString(), etRiwayat.text.toString())
            }
        }
        }



        button2.setOnClickListener{
            finish()
        }
    }

    private fun inputData(nama : String?, nohp : String?, alamat: String?, jenis: String?, riwayatPerjalanan: String?){
        val input = NetworkModule.service().insertData(nama ?: "", nohp ?: "", alamat ?: "", jenis ?: "", riwayatPerjalanan ?: "")
        input.enqueue(object : Callback<ResponseAction> {
            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Toast.makeText(applicationContext, "Data berhasil ditambah", Toast.LENGTH_LONG).show()
                finish()
            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun updateData(id: String?, nama : String?, nohp : String?, alamat: String?, jenis: String?, riwayatPerjalanan: String?){
        val input = NetworkModule.service().updateData(id?: "",nama ?: "", nohp ?: "", alamat ?: "", jenis ?: "", riwayatPerjalanan ?: "")
        input.enqueue(object : Callback<ResponseAction> {
            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Toast.makeText(applicationContext, "Data berhasil diupdate", Toast.LENGTH_LONG).show()
                finish()
            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
