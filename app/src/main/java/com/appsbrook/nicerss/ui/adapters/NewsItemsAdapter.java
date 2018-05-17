package com.appsbrook.nicerss.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appsbrook.nicerss.R;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsItemsAdapter extends RecyclerView.Adapter<NewsItemsAdapter.NewsItemViewHolder> {

    private List<String> items;

    public NewsItemsAdapter() {
    }

    public NewsItemsAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public NewsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);

        return new NewsItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsItemViewHolder holder, int position) {

        String item = items.get(position);

        holder.bind(item);
    }

    @Override
    public int getItemCount() {

        return items != null ? items.size() : 0;
    }

    public void updateNewsItems(List<String> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    static class NewsItemViewHolder extends RecyclerView.ViewHolder {

        private String item;

        @BindView(R.id.title_text_view)
        TextView titleTextView;

        NewsItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(String item) {

            this.item = item;

            titleTextView.setText(item);
        }
    }
}
