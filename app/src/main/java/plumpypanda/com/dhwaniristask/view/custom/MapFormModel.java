package plumpypanda.com.dhwaniristask.view.custom;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 9/24/2018.
 */

public final class MapFormModel extends FormModel {
    private final Map<String,Object> data = new HashMap<>();

    @Override
    public Object getBackingValue(String name) {
        return data.get(name);
    }

    @Override
    public void setBackingValue(String name, Object value) {
        data.put(name, value);
    }
}
