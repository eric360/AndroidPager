package eric_hth.my360android;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by eric_360 on 01/03/16.
 */
public class TokenResponse implements Serializable {

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("error")
    @Expose
    private LoggingError error;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoggingError getError() {
        return error;
    }

    public void setError(LoggingError error) {
        this.error = error;
    }
}