package com.rontikeky.mycampus.otpblucampus.RestApi;

import com.rontikeky.mycampus.otpblucampus.ResponseError.RegisterErrorResponse;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by Anggit on 05/12/2017.
 */

public class ErrorRegisterUtil {

    public static RegisterErrorResponse.Errors parseError(Response<?> response) {
        Converter<ResponseBody, RegisterErrorResponse.Errors> converter =
                ServiceGenerator.retrofit()
                        .responseBodyConverter(RegisterErrorResponse.Errors.class, new Annotation[0]);

        RegisterErrorResponse.Errors error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new RegisterErrorResponse().errors;
        }

        return error;

    }

}
