package plumpypanda.com.dhwaniristask.view.custom;

import android.content.res.Resources;

import plumpypanda.com.dhwaniristask.R;

/**
 * Created by lenovo on 9/23/2018.
 */

public class RequiredField extends ValidationError {

    /**
     * Creates a new instance with the specified field name.
     *
     * @param fieldName     the field name
     * @param fieldLabel    the field label
     */
    public RequiredField(String fieldName, String fieldLabel) {
        super(fieldName, fieldLabel);
    }

    @Override
    public String getMessage(Resources resources) {
        return String.format(resources.getString(R.string.required_field_error_msg), getFieldLabel());
    }
}