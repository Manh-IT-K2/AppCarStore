package qvm.m.appcuahang.Retrofit;
import io.reactivex.rxjava3.core.Observable;
import qvm.m.appcuahang.Model.LoaiSpModel;
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
}