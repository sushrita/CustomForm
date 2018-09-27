package plumpypanda.com.dhwaniristask.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lenovo on 9/20/2018.
 */

public class RestResponse {

    @SerializedName("status")
    private String status;
    public String getStatus(){return status;}

    @SerializedName("data")
    private List<RootObject> result;

    public List<RootObject> getResult() {
        return result;
    }

}
