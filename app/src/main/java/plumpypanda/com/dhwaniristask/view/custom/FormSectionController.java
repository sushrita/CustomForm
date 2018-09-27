package plumpypanda.com.dhwaniristask.view.custom;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import plumpypanda.com.dhwaniristask.R;

/**
 * Created by lenovo on 9/21/2018.
 */

public class FormSectionController extends  FormElementController{
    private final String title;
    private final Map<String,FormElementController> elements = new HashMap<String,FormElementController>();
    private final List<FormElementController> orderedElements = new ArrayList<FormElementController>();

   // Context ctx;
   // String name ;

    /**
     * Creates a new instance of a form section with a specified name and title.
     *
     * @param ctx   the Android context
     * @param name  the name of the section
     * @param title the title of the section to display
     */
    public FormSectionController(Context ctx, String name, String title) {
        super(ctx, name);
    //    this.name = name;
        this.title = title;
    }

    /**
     * Creates a new instance of a form section with a specified title. The name of the section is generated randomly.
     * This method can be used when you do not need to refer to a section by name.
     *
     * @param title
     */
    public FormSectionController(Context context, String title) {
        this(context, UUID.randomUUID().toString(), title);
    }

    /**
     * Creates a new instance of a form section with no title or name.
     */
    public FormSectionController(Context context) {
        this(context, null);
    }

    /**
     * Returns the display title of this section.
     *
     * @return the display title of this section
     */
    public String getTitle() {
        return title;
    }

    /**
     * Adds a form element to this section. Note that sub-sections are not supported.
     *
     * @param element   the form element to add
     * @param position  the position at which to insert the element
     * @return          the same instance of the form element that was added to support method chaining
     */
    public FormElementController addElement(FormElementController element, int position) {
        /*if (element instanceof FormSectionController) {
            throw new IllegalArgumentException("Sub-sections are not supported");
        }
*/
        if (elements.containsKey(element.getName())) {
            throw new IllegalArgumentException("Element with that name already exists");
        } else {
            elements.put(element.getName(), element);
            orderedElements.add(position, element);
            return element;
        }
    }

    /**
     * Adds a form element to the end of this section. Note that sub-sections are not supported.
     *
     * @param element   the form element to add
     * @return          the same instance of the form element that was added to support method chaining
     */
    public FormElementController addElement(FormElementController element) {
        return addElement(element, orderedElements.size());
    }

    /**
     * Adds a collection of form elements to this section.
     *
     * @param values    the form elements to add
     */
    public void addElements(Collection<FormElementController> values) {
        for (FormElementController element : values) {
            addElement(element);
        }
    }

    /**
     * Removes the form element with the specified name from this section.
     *
     * @param name  the name of the form element to remove
     * @return      the removed form element instance, or null of no such element was found.
     */
    public FormElementController removeElement(String name) {
        FormElementController element = elements.remove(name);
        orderedElements.remove(element);
        return element;
    }

    /**
     * Removes the specified form element from this section.
     *
     * @param element   the form element to remove
     * @return          the removed form element instance, or null of no such element was found.
     */
    public FormElementController removeElement(FormElementController element) {
        return removeElement(element.getName());
    }

    /**
     * Returns the form element with the specified name from this section.
     *
     * @param name  the name of the form element to get
     * @return      the form element with the specified name, or null if no such element was found
     */
    public FormElementController getElement(String name) {
        return elements.get(name);
    }

    /**
     * Returns the form element indexed at {@code i} in this section.
     *
     * @param i the index of the form element to return
     * @return  the form element at the specified index
     */
    public FormElementController getElement(int i) {
        return orderedElements.get(i);
    }

    /**
     * Returns the list of elements that are in this section.
     *
     * @return a list of the section's elements
     */
    public List<FormElementController> getElements() {
        return orderedElements;
    }

    /**
     * Returns the number of elements that are in this section.
     *
     * @return the number of elements in this section
     */
    public int getNumberOfElements() {
        return elements.size();
    }

    @Override
    protected View createView() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        View view;
        if (!TextUtils.isEmpty(getTitle())) {
            view = layoutInflater.inflate(R.layout.form_section, null);
            view.setOnClickListener(null);
            view.setOnLongClickListener(null);
            view.setLongClickable(false);

            final TextView sectionView = (TextView) view.findViewById(R.id.list_item_section_text);
            sectionView.setText(title);
        } else {
            view = layoutInflater.inflate(R.layout.separator, null);
        }

        return view;
    }

    @Override
    public void refresh() {
        for (FormElementController element : orderedElements) {
            element.refresh();
        }
    }

    @Override
    public void setError(String message) {
        // No error are possible on a section.
    }
}
