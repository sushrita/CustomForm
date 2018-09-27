package plumpypanda.com.dhwaniristask.service;

import java.util.List;

import plumpypanda.com.dhwaniristask.model.RestResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lenovo on 9/20/2018.
 */

public interface RootObjectApi {

    @GET("v1/task/new-task")
    Call<RestResponse> getResults();

}
