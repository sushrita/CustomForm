package plumpypanda.com.dhwaniristask.view.custom;

import android.content.Context;
import android.content.res.Resources;

import java.util.List;

/**
 * Created by lenovo on 9/23/2018.
 */

public class PerFieldValidationErrorDisplay implements ValidationErrorDisplay  {
    private final Context context;
    private final FormController controller;

    public PerFieldValidationErrorDisplay(Context context, FormController controller) {
        this.context = context;
        this.controller = controller;
    }

    @Override
    public void resetErrors() {
        for (FormSectionController section: controller.getSections()) {
            for (FormElementController elementController: section.getElements()) {
                elementController.setError(null);
            }
        }
    }

    @Override
    public void showErrors(List<ValidationError> errors) {
        Resources res = context.getResources();
        FormElementController element;
        for (ValidationError error : errors) {
            element = controller.getElement(error.getFieldName());
            element.setError(error.getMessage(res));
        }
    }
}
