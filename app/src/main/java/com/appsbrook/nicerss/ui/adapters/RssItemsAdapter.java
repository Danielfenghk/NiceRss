package com.appsbrook.nicerss.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appsbrook.nicerss.R;
import com.appsbrook.nicerss.data.RssItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RssItemsAdapter extends RecyclerView.Adapter<RssItemsAdapter.RssItemViewHolder> {

    private List<RssItem> items;
    private HostingComponent hostingComponent;

    public interface HostingComponent {

        void onRssItemClick(RssItem item);
    }

    public RssItemsAdapter(HostingComponent hostingComponent) {
        this.hostingComponent = hostingComponent;
    }

    public RssItemsAdapter(List<RssItem> items, HostingComponent hostingComponent) {
        this.items = items;
        this.hostingComponent = hostingComponent;
    }

    @NonNull
    @Override
    public RssItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);

        return new RssItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RssItemViewHolder holder, int position) {

        RssItem item = items.get(position);

        holder.bind(item);
    }

    @Override
    public int getItemCount() {

        return items != null ? items.size() : 0;
    }

    public void updateNewsItems(List<RssItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addNewsItems(List<RssItem> newItems) {

        if (items == null) {
            items = new ArrayList<>();
        }

        items.addAll(newItems);
        notifyDataSetChanged();
    }

    class RssItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RssItem item;

        @BindView(R.id.title_text_view)
        TextView titleTextView;

        @BindView(R.id.publication_date_text_view)
        TextView publicationDateTextView;

        RssItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(this);
        }

        void bind(RssItem item) {

            this.item = item;

            titleTextView.setText(item.getTitle());
        }

        @Override
        public void onClick(View v) {

            hostingComponent.onRssItemClick(item);
        }
    }
}