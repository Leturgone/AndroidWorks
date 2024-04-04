package com.example.yasnecovfi5;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {

    //Класс нужный для обработки RecucleView
    private List<String> items;
    private  List<Integer> images;
    public SimpleAdapter(List<String> items,List<Integer> images) {
        this.items = items;
        this.images = images;
    }
    @NonNull
    @Override
    public SimpleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull SimpleAdapter.ViewHolder holder, int position) {
        String item = items.get(position);
        Integer image = images.get(position);
        holder.textView.setText(item);
        holder.imageView.setImageResource(image);
    }
    @Override
    public int getItemCount() {
        return items.size();
    }



    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.text_item);
            imageView = view.findViewById(R.id.image_item);
        }
    }
}
