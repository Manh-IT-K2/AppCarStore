package qvm.m.appcuahang.Retrofit;
import com.google.gson.stream.JsonReader;

import io.reactivex.rxjava3.core.Observable;
import qvm.m.appcuahang.Model.LoaiSpModel;
import qvm.m.appcuahang.Model.UserModel;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiBanHang {
    @POST("getsp.php")
    @FormUrlEncoded
    Observable<LoaiSpModel> getSanPham(
            @Field("page") int page,
            @Field("loai") int loai
    );
    @POST("dangky.php")
    @FormUrlEncoded
    Observable<UserModel> dangky(
        @Field("fullname") String fullname,
        @Field("username") String username,
        @Field("password") String password,
        @Field("email") String email
    );

    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<UserModel> dangnhap(
            @Field("username") String username,
            @Field("password") String password
    );
}