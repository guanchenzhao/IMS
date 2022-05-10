package com.gc.ims.core.consts;

import java.util.Arrays;
import java.util.List;

public interface Constant {

    String AUTH_HEADER = "Authorization";

    String TOKEN = "TOKEN";

    List<String> NONE_PERMISSION_RES = Arrays.asList(
            "/login/login",
            "/login/register"
    );

}
