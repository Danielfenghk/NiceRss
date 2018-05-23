package com.appsbrook.nicerss.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appsbrook.nicerss.R;
import com.appsbrook.nicerss.models.RssSource;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RssSourcesAdapter extends RecyclerView.Adapter<RssSourcesAdapter.RssSourceViewHolder> {

    private RssSourcesAdapterHost rssSourcesAdapterHost;
    private List<RssSource> rssSources;

    public interface RssSourcesAdapterHost {
        void onRssSourceClick(RssSource rssSource);
    }

    public RssSourcesAdapter(RssSourcesAdapterHost rssSourcesAdapterHost) {
        this.rssSourcesAdapterHost = rssSourcesAdapterHost;
    }

    public RssSourcesAdapter(RssSourcesAdapterHost rssSourcesAdapterHost,
                             List<RssSource> rssSources) {

        this.rssSourcesAdapterHost = rssSourcesAdapterHost;
        this.rssSources = rssSources;
    }

    @NonNull
    @Override
    public RssSourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rss_source, parent, false);

        return new RssSourceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RssSourceViewHolder holder, int position) {

        RssSource rssSource = rssSources.get(position);

        holder.bind(rssSource);
    }

    @Override
    public int getItemCount() {

        return rssSources != null ? rssSources.size() : 0;
    }

    class RssSourceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RssSource rssSource;

        @BindView(R.id.rss_source_name_text_view)
        TextView rssSourceNameTextView;

        public RssSourceViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        public void bind(RssSource rssSource) {
            this.rssSource = rssSource;

            rssSourceNameTextView.setText(rssSource.getName());
        }

        @Override
        public void onClick(View v) {

            rssSourcesAdapterHost.onRssSourceClick(rssSource);
        }
    }
}
