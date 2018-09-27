package plumpypanda.com.dhwaniristask.view.CustomFieldController;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import plumpypanda.com.dhwaniristask.view.custom.FormController;
import plumpypanda.com.dhwaniristask.view.custom.InputValidator;
import plumpypanda.com.dhwaniristask.view.custom.LabeledFieldController;

/**
 * Created by lenovo on 9/24/2018.
 */

public class DatePickerController extends LabeledFieldController {
    private final int editTextId = FormController.generateViewId();

    private DatePickerDialog datePickerDialog = null;
    private final SimpleDateFormat displayFormat;
    private final TimeZone timeZone;

    /**
     * Constructs a new instance of a date picker field.
     *
     * @param ctx           the Android context
     * @param name          the name of the field
     * @param labelText     the label to display beside the field. Set to {@code null} to not show a label.
     * @param validators    contains the validations to process on the field
     * @param displayFormat the format of the date to show in the text box when a date is set
     */
    public DatePickerController(Context ctx, String name, String labelText, Set<InputValidator> validators, SimpleDateFormat displayFormat,String labelNo) {
        super(ctx, name, labelText, validators,labelNo);
        this.displayFormat = displayFormat;
        this.timeZone = displayFormat.getTimeZone();
    }

    /**
     * Constructs a new instance of a date picker field.
     *
     * @param ctx           the Android context
     * @param name          the name of the field
     * @param labelText     the label to display beside the field. Set to {@code null} to not show a label.
     * @param isRequired    indicates if the field is required or not
     * @param displayFormat the format of the date to show in the text box when a date is set
     */
    public DatePickerController(Context ctx, String name, String labelText, boolean isRequired, SimpleDateFormat displayFormat,String labelNo) {
        super(ctx, name, labelText, isRequired,labelNo);
        this.displayFormat = displayFormat;
        this.timeZone = displayFormat.getTimeZone();
    }

    /**
     * Constructs a new instance of a date picker field, with the selected date displayed in "MMM d, yyyy" format.
     *
     * @param name      the name of the field
     * @param labelText the label to display beside the field
     */
    public DatePickerController(Context context, String name, String labelText,String labelNo) {
        this(context, name, labelText, false, new SimpleDateFormat("MMM d, yyyy", Locale.getDefault()),labelNo);
    }

    @Override
    protected View createFieldView() {
        final EditText editText = new EditText(getContext());
        editText.setId(editTextId);

        editText.setSingleLine(true);
        editText.setInputType(InputType.TYPE_CLASS_DATETIME);
        editText.setKeyListener(null);
        refresh(editText);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(getContext(), editText);
            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickerDialog(getContext(), editText);
                }
            }
        });

        return editText;
    }

    private void showDatePickerDialog(final Context context, final EditText editText) {
        // don't show dialog again if it's already being shown
        if (datePickerDialog == null) {
            Date date = (Date) getModel().getValue(getName());
            if (date == null) {
                date = new Date();
            }
            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            calendar.setTimeZone(timeZone);
            calendar.setTime(date);

            datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar calendar = Calendar.getInstance(Locale.getDefault());
                    calendar.setTimeZone(timeZone);
                    calendar.set(year, monthOfYear, dayOfMonth);
                    getModel().setValue(getName(), calendar.getTime());
                    editText.setText(displayFormat.format(calendar.getTime()));

                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

            datePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    datePickerDialog = null;
                }
            });

            datePickerDialog.show();
        }
    }

    private EditText getEditText() {
        return (EditText) getView().findViewById(editTextId);
    }

    private void refresh(EditText editText) {
        Date value = (Date) getModel().getValue(getName());
        editText.setText(value != null ? displayFormat.format(value) : "");
    }

    public void refresh() {
        refresh(getEditText());
    }
}