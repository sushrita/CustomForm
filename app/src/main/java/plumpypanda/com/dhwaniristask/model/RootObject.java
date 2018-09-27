package plumpypanda.com.dhwaniristask.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by lenovo on 9/19/2018.
 */

public class RootObject {


    @SerializedName("name")
    private String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    @SerializedName("id")
    private String id;

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

    @SerializedName("type")
    private String type;

    public String getType() { return this.type; }

    public void setType(String type) { this.type = type; }

    @SerializedName("label")
    private String label;

    public String getLabel() { return this.label; }

    public void setLabel(String label) { this.label = label; }

    private ArrayList<Option> options;

    public ArrayList<Option> getOptions() { return this.options; }

    public void setOptions(ArrayList<Option> options) { this.options = options; }
}
