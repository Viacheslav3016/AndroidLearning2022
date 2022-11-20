package com.example.mylovefilms;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{

    private static final String TEG_POSITIVE = "Позитивный";
    private static final String TEG_NEGATIVE = "Негативный";
    private static final String TEG_NEITRALNIY = "Нейтральный";

    private List<Review> reviewList = new ArrayList<>();

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviewList.get(position);
        holder.tv_review_author.setText(review.getAuthorOfReviw());
        holder.tv_review_text.setText(review.getTextOfreview());
        String type = review.getTypeOfReview();
        int colorResId = android.R.color.holo_red_light;
        switch (type){
            case TEG_POSITIVE:
                colorResId = android.R.color.holo_green_light;
                    break;
            case TEG_NEITRALNIY:
                colorResId = android.R.color.holo_orange_light;
                    break;
        }
        int color = ContextCompat.getColor(holder.itemView.getContext(), colorResId);
        holder.LinearLReview.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout LinearLReview;
        private TextView tv_review_author;
        private TextView tv_review_text;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_review_author = itemView.findViewById(R.id.tv_review_author);
            tv_review_text = itemView.findViewById(R.id.tv_review_text);
            LinearLReview = itemView.findViewById(R.id.LinearLReview);
        }
    }

}
