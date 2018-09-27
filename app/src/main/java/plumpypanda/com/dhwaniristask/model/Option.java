package plumpypanda.com.dhwaniristask.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovo on 9/19/2018.
 */

public class Option {
    @SerializedName("id")
    private String id;

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

    @SerializedName("value")
    private String value;

    public String getValue() { return this.value; }

    public void setValue(String value) { this.value = value; }
}
