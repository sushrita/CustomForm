package plumpypanda.com.dhwaniristask.view.custom;

import java.util.List;

/**
 * Created by lenovo on 9/23/2018.
 */

public interface ValidationErrorDisplay {
    /**
     * Display the validation errors.
     *
     * @param errors The errors to show.
     */
    void showErrors(List<ValidationError> errors);

    /**
     * Dismiss the validation errors.
     */
    void resetErrors();
}


