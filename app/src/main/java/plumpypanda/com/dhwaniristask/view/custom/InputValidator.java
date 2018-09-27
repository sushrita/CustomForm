package plumpypanda.com.dhwaniristask.view.custom;

/**
 * Created by lenovo on 9/23/2018.
 */

public interface InputValidator {
    ValidationError validate(Object value, String fieldName, String fieldLabel);

}
