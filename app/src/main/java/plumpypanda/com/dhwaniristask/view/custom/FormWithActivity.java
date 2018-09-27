package plumpypanda.com.dhwaniristask.view.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.WindowManager;
import plumpypanda.com.dhwaniristask.R;

/**
 * Created by lenovo on 9/21/2018.
 */

public abstract class FormWithActivity extends AppCompatActivity {

    private FormModelFragment formModelFragment;
    private FormController formController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formwith);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        this.formModelFragment = FragmentActivityHelper.getFormModelFragment(this);
        this.formController = new FormController(this, formModelFragment.getModel());

        initForm(formController);
        recreateViews();
    }

    /**
     * An abstract method that must be overridden by subclasses where the form fields are initialized.
     */
     public abstract void initForm(FormController controller);

    /**
     * Returns the associated <code>FormController</code> that manages the form fields.
     */
    public FormController getFormController() {
        return formController;
    }

    /**
     * Returns the associated model of this form.
     */
    public FormModel getModel() {
        return formModelFragment.getModel();
    }

    /**
     * Sets the model to use for this form
     *
     * @param formModel the model to use
     */
    public void setModel(FormModel formModel) {
        this.formModelFragment.setModel(formModel);
        formController.setModel(formModel);
    }

    /**
     * Recreates the views for all the elements that are in the form. This method needs to be called when field are dynamically added or
     * removed
     */
    protected void recreateViews() {
        ViewGroup containerView =  this.findViewById(R.id.form_elements_container);
        formController.recreateViews(containerView);
    }
}
