package com.appsbrook.nicerss.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appsbrook.nicerss.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public class IntroductionFragment extends Fragment {

    private static final String ARG_PAGE_NUMBER = "ARG_PAGE_NUMBER";
    @BindView(R.id.text_view_splash_title)
    TextView textViewSplashTitle;
    @BindView(R.id.image_view_splash)
    ImageView imageViewSplash;
    @BindView(R.id.text_view_splash_desc)
    TextView textViewSplashDesc;
    @BindView(R.id.image_view_empty_dot_1)
    ImageView imageViewEmptyDot1;
    @BindView(R.id.image_view_empty_dot_2)
    ImageView imageViewEmptyDot2;
    @BindView(R.id.image_view_empty_dot_3)
    ImageView imageViewEmptyDot3;
    @BindView(R.id.image_view_selected_dot_1)
    ImageView imageViewSelectedDot1;
    @BindView(R.id.image_view_selected_dot_2)
    ImageView imageViewSelectedDot2;
    @BindView(R.id.image_view_selected_dot_3)
    ImageView imageViewSelectedDot3;
    @BindView(R.id.relative_layout_fragment_splash)
    RelativeLayout relativeLayoutFragmentSplash;
    Unbinder unbinder;


    public static Fragment newInstance(int position) {

        IntroductionFragment fragment = new IntroductionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_introduction, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int pageNumber = getArguments().getInt(ARG_PAGE_NUMBER);
        Timber.d("pageNumber: " + pageNumber);

        switch (pageNumber) {

            case 1:
                textViewSplashTitle.setText("Page 1");
                textViewSplashDesc.setText("Page 1 description");
                imageViewSplash.setImageResource(R.drawable.ic_intro_one);
                relativeLayoutFragmentSplash.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.md_blue_grey_500));
                imageViewSelectedDot1.setVisibility(View.VISIBLE);
                imageViewSelectedDot2.setVisibility(View.INVISIBLE);
                imageViewSelectedDot3.setVisibility(View.INVISIBLE);
                break;
            case 2:
                textViewSplashTitle.setText("Page 2");
                textViewSplashDesc.setText("Page 2 description");
                imageViewSplash.setImageResource(R.drawable.ic_intro_two);
                relativeLayoutFragmentSplash.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
                imageViewSelectedDot1.setVisibility(View.INVISIBLE);
                imageViewSelectedDot2.setVisibility(View.VISIBLE);
                imageViewSelectedDot3.setVisibility(View.INVISIBLE);
                break;
            case 3:
                textViewSplashTitle.setText("Page 3");
                textViewSplashDesc.setText("Page 3 description");
                imageViewSplash.setImageResource(R.drawable.ic_intro_three);
                relativeLayoutFragmentSplash.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                imageViewSelectedDot1.setVisibility(View.INVISIBLE);
                imageViewSelectedDot2.setVisibility(View.INVISIBLE);
                imageViewSelectedDot3.setVisibility(View.VISIBLE);
                break;
            default:
                throw new RuntimeException("Wrong page number");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
