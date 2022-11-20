package com.example.mylovefilms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailersViewHolder> {
    private List<Trailers> trailersL = new ArrayList<>();
    private onClickListenerTrailer onClickListenerTrailer
            ;
    public void setTrailersL(List<Trailers> trailersL) {
        this.trailersL = trailersL;
        notifyDataSetChanged();
    }

    public void setOnClickListenerTrailer(TrailersAdapter.onClickListenerTrailer onClickListenerTrailer) {
        this.onClickListenerTrailer = onClickListenerTrailer;
    }

    @NonNull
    @Override
    public TrailersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item, parent,
                false);
        return new TrailersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailersViewHolder holder, int position) {
        Trailers trailers = trailersL.get(position);
        holder.tvTrailerName.setText(trailers.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListenerTrailer!=null){
                    onClickListenerTrailer.onTrailerClick(trailers);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return trailersL.size();
    }

    interface onClickListenerTrailer{
        void onTrailerClick(Trailers trailers);
    }

    static class TrailersViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvTrailerName;

        public TrailersViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTrailerName = itemView.findViewById(R.id.tvTrailerName);
        }
    }
}
