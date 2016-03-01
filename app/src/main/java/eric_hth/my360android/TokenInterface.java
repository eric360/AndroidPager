package eric_hth.my360android;

import eric_hth.my360android.TokenResponse;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by eric_360 on 01/03/16.
 */
public interface TokenInterface {
    @FormUrlEncoded
    @POST("/api/tokens")
    Call<TokenResponse> postAuth(@Field("login") String login, @Field("password") String md5Pwd, @Field("mobile") boolean mobile);
}