package plumpypanda.com.dhwaniristask.presenter;

import java.util.List;

import plumpypanda.com.dhwaniristask.model.RestResponse;
import plumpypanda.com.dhwaniristask.model.RootObject;
import plumpypanda.com.dhwaniristask.service.RootObjectService;
import plumpypanda.com.dhwaniristask.view.RootObjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lenovo on 9/20/2018.
 */

public class RootObjectPresenter {

    private RootObjectView rootObjectView;
    private RootObjectService rootObjectService;

    public RootObjectPresenter(RootObjectView view) {
        this.rootObjectView = view;

        if (this.rootObjectService == null) {
            this.rootObjectService = new RootObjectService();
        }
    }

    public void getObjects() {
        rootObjectService
                .getApi().getResults()
                .enqueue(new Callback<RestResponse>() {
                    @Override
                    public void onResponse(Call<RestResponse> call, Response<RestResponse> response) {
                        RestResponse data = response.body();

                        if (data != null && data.getResult() != null) {
                            List<RootObject> result = data.getResult();
                            rootObjectView.objectsReady(result);
                        }
                    }

                    @Override
                    public void onFailure(Call<RestResponse> call, Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong!"+t.getMessage());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
