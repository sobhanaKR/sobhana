package com.example.sobhana.first;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sobhana on 26/6/17.
 */

public interface Request_Interface {
    @GET("bins/mnqmn")
    Call<JSON_Response> getJSON();
}
