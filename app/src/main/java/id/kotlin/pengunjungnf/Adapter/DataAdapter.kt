package id.kotlin.anggota.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kotlin.pengunjungnf.Model.getData.DataItem
import id.kotlin.pengunjungnf.R
import kotlinx.android.synthetic.main.list_item.view.*

class DataAdapter(val data: List<DataItem>?, val itemCLick: OnClickListener) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.nama.text = item?.nama
        holder.nohp.text = item?.nohp
        holder.alamat.text = item?.alamat
        holder.jenis.text = item?.jenis
        holder.riwayatPerjalanan.text = item?.riwayatPerjalanan

        holder.view.setOnClickListener{
            itemCLick.detail(item)
        }

        holder.btnHapus.setOnClickListener{
            itemCLick.hapusData(item)
        }

    }

    override fun getItemCount(): Int = data?.size ?: 0

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val nama = view.textNama
        val nohp = view.textNohp
        val alamat = view.textAlamat
        val jenis = view.textJenis
        val riwayatPerjalanan = view.textRiwayat
        val btnHapus = view.btnHapus
    }

    interface OnClickListener{
        fun detail(item: DataItem?)

        fun hapusData(item: DataItem?)
    }
}