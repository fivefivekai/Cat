package au.edu.cat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private Context context;
    private List<Data> cats;
    public ListAdapter(Context context, List<Data> cats) {
        this.context = context;
        this.cats = cats;
    }



    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener ){
        this.onItemClickListener = listener;
    }
    @Override
    public int getItemCount() {
        return cats.size();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_cat, parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Data cat = cats.get(position);
        holder.text2.setText(cat.getName());
        holder.text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener!=null){
                    onItemClickListener.setOnItemClickListener(position);
                }
            }
        });

    }



    class ViewHolder extends RecyclerView.ViewHolder{

        TextView text2;
        public ViewHolder(View itemView) {
            super(itemView);
            text2 = itemView.findViewById(R.id.text2);
        }
    }

}
