package id.kotlin.anggota.Config

import id.kotlin.pengunjungnf.Model.action.ResponseAction
import id.kotlin.pengunjungnf.Model.getData.ResponseGetData
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {

    //getData
    @GET("getData.php")
    fun getData(): Call<ResponseGetData>

    //getData
    @GET("getData.php")
    fun getDataById(@Query("id") id: String): Call<ResponseGetData>

    //insert
    @FormUrlEncoded
    @POST("insertData.php")
    fun insertData(
        @Field("nama") nama: String,
        @Field("nohp") nohp: String,
        @Field("alamat") alamat: String,
        @Field("jenis") jenis: String,
        @Field("riwayat_perjalanan") riwayat_perjalanan: String
    ): Call<ResponseAction>

    //insert
    @FormUrlEncoded
    @POST("updateData.php")
    fun updateData(
        @Field("id") id: String,
        @Field("nama") nama: String,
        @Field("nohp") nohp: String,
        @Field("alamat") alamat: String,
        @Field("jenis") jenis: String,
        @Field("riwayat_perjalanan") riwayat_perjalanan: String
    ): Call<ResponseAction>

    //insert
    @FormUrlEncoded
    @POST("deleteData.php")
    fun deleteData(@Field("id") id: String): Call<ResponseAction>

}