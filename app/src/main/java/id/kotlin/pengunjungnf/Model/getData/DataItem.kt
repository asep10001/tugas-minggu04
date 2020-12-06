package id.kotlin.pengunjungnf.Model.getData

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataItem(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("jenis")
	val jenis: String? = null,

	@field:SerializedName("nohp")
	val nohp: String? = null,

	@field:SerializedName("riwayat_perjalanan")
	val riwayatPerjalanan: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
) : Parcelable