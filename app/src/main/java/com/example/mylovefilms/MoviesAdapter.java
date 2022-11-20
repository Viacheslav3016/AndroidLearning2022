package com.example.mylovefilms;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviewViewHolder> {
    private List<Movie> movies = new ArrayList<>();
    private onReachEndListener onReachEndListener;
    private OnClickMovieListener onClickMovieListener;

    public void setOnClickMovieListener(OnClickMovieListener onClickMovieListener) {
        this.onClickMovieListener = onClickMovieListener;
    }

    public void setOnReachEndListener(MoviesAdapter.onReachEndListener onReachEndListener) {
        this.onReachEndListener = onReachEndListener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MoviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviewViewHolder holder, int position) {
        Movie movie = movies.get(position);
        Glide.with(holder.itemView)
                .load(movie.getPoster().getUrl())
                .into(holder.ivPoster);

        double rating = movie.getRating().getKp();
        int bacgroundId;
        if (rating>=8){
            bacgroundId = R.drawable.circle_green;
        }else if (rating >= 5){
            bacgroundId = R.drawable.circle_yellow;
        }else {
            bacgroundId = R.drawable.circle_red;
        }
        Drawable backround = ContextCompat.getDrawable(holder.itemView.getContext(), bacgroundId);
        holder.tv_Raiting.setBackground(backround);
        holder.tv_Raiting.setText(String.valueOf(rating));

        if (position >= movies.size() - 10 && onReachEndListener!=null){
            onReachEndListener.onReachEnd();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickMovieListener!=null){
                    onClickMovieListener.onClickMovie(movie);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    interface onReachEndListener{
        void onReachEnd();
    }

    interface OnClickMovieListener{
        void onClickMovie(Movie movie);
    }

    static class MoviewViewHolder extends RecyclerView.ViewHolder{

        private final ImageView ivPoster;
        private final TextView tv_Raiting;
        public MoviewViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            tv_Raiting = itemView.findViewById(R.id.tv_Raiting);
        }
    }
}
