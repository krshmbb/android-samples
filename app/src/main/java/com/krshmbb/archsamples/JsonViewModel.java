package com.krshmbb.archsamples;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Class that holds the data model.
 */
public class JsonViewModel extends AndroidViewModel {

    private final JsonLiveData data;

    /**
     * Constructor.
     * @param application Application context
     */
    public JsonViewModel(@NonNull Application application) {
        super(application);
        data = new JsonLiveData(application);
    }

    /**
     * Returns the data in the model.
     */
    @NonNull
    public LiveData<List<String>> getData() {
        return data;
    }
}
