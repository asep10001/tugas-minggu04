package id.kotlin.pengunjungnf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import id.kotlin.anggota.Adapter.DataAdapter
import id.kotlin.anggota.Config.NetworkModule
import id.kotlin.pengunjungnf.Model.action.ResponseAction
import id.kotlin.pengunjungnf.Model.getData.DataItem
import id.kotlin.pengunjungnf.Model.getData.ResponseGetData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener{
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }

        showData()
    }

    private fun showData(){
        val listPengunjung = NetworkModule.service().getData()
        listPengunjung.enqueue(object : Callback<ResponseGetData> {
            override fun onResponse(
                call: Call<ResponseGetData>,
                response: Response<ResponseGetData>
            ) {
                if(response.isSuccessful){

                    val item = response.body()?.data
                    val adapter = DataAdapter(item, object : DataAdapter.OnClickListener{
                        override fun detail(item: DataItem?) {
                            val intent = Intent(this@MainActivity, InputActivity::class.java)
                            intent.putExtra("data", item)
                            startActivity(intent)

                        }

                        override fun hapusData(item: DataItem?) {

                            AlertDialog.Builder(this@MainActivity).apply {
                                setTitle("Hapus Data")
                                setMessage("Apakah anda yakin ingin menghapus data ini?")
                                setPositiveButton("Delete"){ dialog, _ ->
                                    hapusItem(item?.id)
                                    dialog.dismiss()
                                }

                                setNegativeButton("Cancel"){ dialog, _ ->
                                    dialog.dismiss()
                                }
                            }.show()

                        }

                    })

                    list.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ResponseGetData>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun hapusItem(id: String?) {
        val hapus = NetworkModule.service().deleteData(id ?: "")
        hapus.enqueue(object : Callback<ResponseAction> {
            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Toast.makeText(applicationContext, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                showData()
            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }

    override fun onResume() {
        super.onResume()
        showData()
    }
}
