package au.edu.cat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import au.edu.cat.Data;
import au.edu.cat.ListAdapter;
import au.edu.cat.IntroActivity;
import au.edu.cat.OnItemClickListener;
import au.edu.cat.R;

public class HomeFragment extends Fragment {




    private EditText edit_query;
    View view;
    private List<Data> list;
    private RecyclerView recyclerviw;
    private ListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        edit_query = view.findViewById(R.id.edit_query);
        recyclerviw = view.findViewById(R.id.RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerviw.setLayoutManager(linearLayoutManager);
        adapter = new ListAdapter(getActivity(),list);
        recyclerviw.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), IntroActivity.class);
                intent.putExtra("cat",list.get(position));
                startActivity(intent);
            }
        });
        view.findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Search();
            }
        });
    }

    public void Search() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("CAT",response);
                Gson gson = new Gson();
                Data[] objectsArray = gson.fromJson(response, Data[].class);
                List<Data> objectsList = Arrays.asList(objectsArray);
                list.clear();
                list.addAll(objectsList);
                adapter.notifyDataSetChanged();
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
        } };
        String param = edit_query.getText().toString().trim();
        String url = null;

            url = "https://api.thecatapi.com/v1/breeds/search?q="+param;

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest =
                new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
    }


}