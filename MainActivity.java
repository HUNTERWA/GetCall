package com.example.rohit.apicalling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//HGsonConverterFactory is used to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);//making instance of Api interface.
        Call<List<Astute>> call = api.getQuestions();

        call.enqueue(new Callback<List<Astute>>() {
            @Override
            public void onResponse(Call<List<Astute>> call, Response<List<Astute>> response)
            {

                List<Astute> quelist = response.body(); //gives question list


                /*for(Astute a:quelist)
                {
                    Log.d("userId",a.getUserId());
                    Log.d("id",a.getId());
                    Log.d("title",a.getTitle());
                    Log.d("body",a.getBody());
                }*/

                String[] questions = new String[quelist.size()]; //creating a array of string for list view
                for (int i = 0; i < quelist.size(); i++)
                {
                    questions[i] = quelist.get(i).getId();
                }


                //displaying the string array into listview
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, questions));
            }

            @Override
            public void onFailure(Call<List<Astute>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }


        });
    }

}
