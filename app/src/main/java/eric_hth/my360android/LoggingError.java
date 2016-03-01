package eric_hth.my360android;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eric_360 on 01/03/16.
 */
public enum LoggingError {
    @SerializedName("param_password_needed")
    PARAM_PASSWORD_NEEDED,

    @SerializedName("wrong_password")
    WRONG_PASSWORD,

    @SerializedName("param_login_needed")
    PARAM_LOGIN_NEEDED,

    @SerializedName("not_authorized_ip")
    NOT_AUTHORIZED_IP,

    @SerializedName("wrong_company_id")
    WRONG_COMPANY_ID,

    @SerializedName("wrong_ip")
    WRONG_IP,

    @SerializedName("wrong_signature")
    WRONG_SIGNATURE,
}