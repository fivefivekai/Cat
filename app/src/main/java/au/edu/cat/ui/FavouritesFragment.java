package au.edu.cat.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import au.edu.cat.Data;
import au.edu.cat.ListAdapter;
import au.edu.cat.OnItemClickListener;
import au.edu.cat.IntroActivity;
import au.edu.cat.db.AppDatabase;
import au.edu.cat.R;

public class FavouritesFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<Data> list = new ArrayList<>();
    private ListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favourites, container, false);
        mRecyclerView = rootView.findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
         adapter = new ListAdapter(getActivity(), list);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), IntroActivity.class);
                intent.putExtra("cat",list.get(position));
                startActivity(intent);
            }
        });
        rootView.findViewById(R.id.tv_re).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FavouritesTask().execute();
            }
        });
        new FavouritesTask().execute();
        return rootView;
    }



    private  class FavouritesTask extends AsyncTask<Void, Void,  List<Data>> {
        @Override
        protected void onPostExecute(List<Data> cats) {
            super.onPostExecute(cats);
            list.clear();
            list.addAll(cats);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected  List<Data> doInBackground(Void... voids) {
            return AppDatabase.getDatabase(getActivity()).dao().getAll();
        }


    }
}
