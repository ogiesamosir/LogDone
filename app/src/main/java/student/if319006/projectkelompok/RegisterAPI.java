package student.if319006.projectkelompok;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface RegisterAPI {

    @FormUrlEncoded
    @POST("insert.php")
    Call<Value> daftar(@Field("judul") String judul,
                       @Field("isi") String isi);

    @GET("view.php")
    Call<Value> view();


    @FormUrlEncoded
    @POST("update.php")
    Call<Value> ubah(@Field("id") String id,
                     @Field("judul") String judul,
                     @Field("isi") String isi);

    @FormUrlEncoded
    @POST("delete.php")
    Call<Value> hapus(@Field("id") String id);





}
