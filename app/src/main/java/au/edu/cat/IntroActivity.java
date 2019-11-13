package au.edu.cat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import au.edu.cat.db.AppDatabase;

public class IntroActivity extends AppCompatActivity {
    private Data data;
    private ImageView ivImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
         data = (Data) getIntent().getSerializableExtra("cat");
        TextView tvName = findViewById(R.id.tv_name);
        TextView tvDes = findViewById(R.id.tv_des);
         ivImg = findViewById(R.id.iv_img);
        TextView tvOrigin = findViewById(R.id.tv_origin);
        TextView tv_life = findViewById(R.id.tv_life);
        TextView tv_link = findViewById(R.id.tv_link);
        TextView tv_friend = findViewById(R.id.tv_friend);
        TextView tvTemperament = findViewById(R.id.tv_Temperament);
        TextView tvWeight = findViewById(R.id.tv_Weight);

        tvName.setText(data.getName());
        tvDes.setText(data.getDescription());
        tvOrigin.setText("Origin:"+data.getOrigin());
        tv_life.setText("Life span:"+data.getLife_span());
        tv_link.setText("Wikipedia link:"+data.getWikipedia_url());
        tv_friend.setText("Dog friendliness level:"+data.getDog_friendly());
        tvTemperament.setText("Temperament:"+data.getTemperament());
        tvWeight.setText("Weight(metric):"+data.getWeight().getMetric());
        loadImg(data.getId());

    }
    public void loadImg(String param) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("CAT",response);
                try {
                    JSONArray jsonArray =  new JSONArray(response);
                    if (jsonArray!=null&&jsonArray.length()>0){
                        JSONObject object = jsonArray.optJSONObject(0);
                        if (object!=null){
                            String url = object.optString("url");
                            Glide.with(IntroActivity.this).load(url).into(ivImg);
                        }
                    }
                } catch (JSONException e) {


                }

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            } };
        String url = null;
            url = "https://api.thecatapi.com/v1/images/search?breed_ids="+param;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest =
                new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
    }

    public void Coll(View view) {
        new  CollAsyncTask().execute();
    }


    private  class CollAsyncTask extends AsyncTask<Void, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Void... voids) {
            AppDatabase.getDatabase(IntroActivity.this).dao().coll(data);
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            Toast.makeText(IntroActivity.this,"ok",Toast.LENGTH_SHORT).show();
        }
    }
}
