package com.krshmbb.archsamples;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Lifecycle aware class for loading data.
 */
public class JsonLiveData extends LiveData<List<String>> {

    private final Context context;

    public JsonLiveData(@NonNull Context context) {
        this.context = context;
        loadData();
    }

    private void loadData() {
        new AsyncTask<Void, Void, List<String>>() {
            @Override
            protected List<String> doInBackground(Void... voids) {
                try {
                    URL url = new URL("https://api.github.com/");
                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                    if (connection.getResponseCode() == 200) {
                        InputStreamReader responseReader =
                                new InputStreamReader(connection.getInputStream(), "UTF-8");
                        JsonReader jsonReader = new JsonReader(responseReader);
                        List<String> output = new ArrayList<>();
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            output.add(jsonReader.nextName() + ":" + jsonReader.nextString());
                        }
                        return output;
                    } else {
                        return Arrays.asList(new String[]{context.getString(R.string.default_error)});
                    }
                } catch (MalformedURLException e) {
                    // This should never happen
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    return Arrays.asList(new String[]{context.getString(R.string.default_error)});
                }
            }

            @Override
            protected void onPostExecute(List<String> strings) {
                setValue(strings);
            }
        }.execute();
    }
}
