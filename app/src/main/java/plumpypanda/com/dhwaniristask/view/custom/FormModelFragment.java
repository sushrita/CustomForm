package plumpypanda.com.dhwaniristask.view.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by lenovo on 9/21/2018.
 */

public class FormModelFragment extends Fragment {

    public static final String TAG = "nd_model";

    private FormModel model;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public FormModel getModel() {
        return model;
    }

    public void setModel(FormModel model) {
        this.model = model;
    }
}
