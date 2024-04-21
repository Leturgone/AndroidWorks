package com.example.yasnecovfi10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter  extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private final List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView titleTextview;
        private final TextView directorTextview;
        private final TextView yearTextview;
        private final ImageView moviPosterImageview;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextview = itemView.findViewById(R.id.movie_title_txt);
            directorTextview = itemView.findViewById(R.id.movie_director_txt);
            yearTextview = itemView.findViewById(R.id.movie_year_txt);
            moviPosterImageview = itemView.findViewById(R.id.movie_poster_img);

        }
        public  void blind(Movie movie){
            titleTextview.setText(movie.getMovie_title());
            directorTextview.setText(movie.getMovie_director());
            yearTextview.setText(movie.getMovie_year());
            moviPosterImageview.setImageBitmap(movie.getMovie_poster());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        holder.blind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
