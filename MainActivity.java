package com.example.rohit.apicalling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//HGsonConverterFactory is used to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);//making instance of Api interface.
        Call<List<Astute>> call = api.getQuestions();

        call.enqueue(new Callback<List<Astute>>() {
            @Override
            public void onResponse(Call<List<Astute>> call, Response<List<Astute>> response) {

                List<Astute> quelist = response.body(); //gives question list

                for(Astute a:quelist)
                {
                    Log.d("userId",a.getUserId());
                    Log.d("id",a.getId());
                    Log.d("title",a.getTitle());
                    Log.d("body",a.getBody());
                }
            }

            @Override
            public void onFailure(Call<List<Astute>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }


        });
    }

}
