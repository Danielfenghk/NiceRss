package com.appsbrook.nicerss.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.appsbrook.nicerss.events.RssSourceConfirmDeleteEvent;
import com.appsbrook.nicerss.models.RssSource;

import org.greenrobot.eventbus.EventBus;

public class ConfirmDeleteRssSourceDialog extends DialogFragment {

    private static final String KEY_RSS_SOURCE = "KEY_RSS_SOURCE";
    private long rssSourceId;

    public static ConfirmDeleteRssSourceDialog newInstance(long rssSource) {

        ConfirmDeleteRssSourceDialog fragment = new ConfirmDeleteRssSourceDialog();
        Bundle args = new Bundle();
        args.putLong(KEY_RSS_SOURCE, rssSource);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            rssSourceId = getArguments().getLong(KEY_RSS_SOURCE);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Delete Rss Source")
                .setMessage("Do you really want to delete this RSS source?")
                .setCancelable(true)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        EventBus.getDefault().post(new RssSourceConfirmDeleteEvent(rssSourceId));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        return builder.create();
    }
}
