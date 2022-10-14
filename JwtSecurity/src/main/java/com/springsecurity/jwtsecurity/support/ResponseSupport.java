package com.springsecurity.jwtsecurity.support;

import com.springsecurity.jwtsecurity.dto.response.data.DataRes;

public class ResponseSupport {

    public static DataRes build(final Object data) {
        return new DataRes(data);
    }
}
