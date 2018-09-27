package plumpypanda.com.dhwaniristask.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import plumpypanda.com.dhwaniristask.R;
import plumpypanda.com.dhwaniristask.model.RootObject;
import plumpypanda.com.dhwaniristask.presenter.RootObjectPresenter;
import plumpypanda.com.dhwaniristask.view.CustomFieldController.CheckBoxController;
import plumpypanda.com.dhwaniristask.view.CustomFieldController.DatePickerController;
import plumpypanda.com.dhwaniristask.view.CustomFieldController.EditTextController;
import plumpypanda.com.dhwaniristask.view.custom.FormController;
import plumpypanda.com.dhwaniristask.view.custom.FormSectionController;
import plumpypanda.com.dhwaniristask.view.custom.FormWithActivity;
import plumpypanda.com.dhwaniristask.view.custom.InputValidator;

/**
 * Created by lenovo on 9/13/2018.
 */

public class FormActivity  extends FormWithActivity implements RootObjectView{

    List<RootObject> rootObjects;
    EditTextController editTextController;


    @Override
    public void initForm(FormController controller) {
        setTitle("Custom Form");
        RootObjectPresenter rootObjectPresenter = new RootObjectPresenter(this);
        rootObjectPresenter.getObjects();

        final FormSectionController section = new FormSectionController(this, "Data");
        section.addElement(new EditTextController(this, "FirstName", "First name", "FIRST NAME", true,"1. "));
        section.addElement(new EditTextController(this, "LastName", "Last name","LAST NAME",true,"2. "));
        section.addElement(new EditTextController(this, "MobileNumber", "Mobile number","MOBILE NUMBER",new HashSet<InputValidator>(),InputType.TYPE_CLASS_PHONE,"3. "));
        //editTextController.getEditText().setInputType(InputType.TYPE_CLASS_PHONE);
        section.addElement(new DatePickerController(this,"DOB","Date of Birth","4. "));
        section.addElement(new DatePickerController(this,"DOJ","Date of Joining","5. "));
        section.addElement(new CheckBoxController(this,"CITY","Choose City",true,Arrays.asList("Delhi","Mumbai"),true,"6. " ));
        section.addElement(new CheckBoxController(this,"Education","Choose Education",true, Arrays.asList("Graduation","Post Graduation"),true,"7. "));
        section.addElement(new CheckBoxController(this,"INTRESTS","Choose Intrests",true, Arrays.asList("Cricket","Base Ball","Foot Ball"),true,"8. "));

        controller.addSection(section);
    }

    //for demo only
    @Override
    public void objectsReady(List<RootObject> rootObjects) {
        for(RootObject object:rootObjects){
            Log.e("OBJECTS:",object.getName());

        }
    }
}
