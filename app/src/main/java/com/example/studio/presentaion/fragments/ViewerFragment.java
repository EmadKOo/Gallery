package com.example.studio.presentaion.fragments;

import static android.view.View.VISIBLE;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.MediaController;

import com.example.studio.R;
import com.example.studio.databinding.FragmentViewerBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ViewerFragment extends Fragment {
    private Uri mediaUri;
    private boolean isPhoto;
    private FragmentViewerBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentViewerBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         if (getArguments() != null) {
            isPhoto = getArguments().getBoolean(getString(R.string.isPhoto));
            mBinding.setIsPhoto(isPhoto);
            mBinding.setTitle(getArguments().getString(getString(R.string.title)));
            mBinding.setSize(String.valueOf(getArguments().getInt(getString(R.string.size))));
            mediaUri = Uri.parse(getArguments().getString(getString(R.string.mediaPath)));
            if (isPhoto)
                mBinding.selectedImage.setImageURI(mediaUri);
            else {
                MediaController mediaController = new MediaController(getActivity());
                mediaController.setAnchorView( mBinding.selectedVideo);
                mBinding.selectedVideo.setMediaController(mediaController);
                mBinding.selectedVideo.setVideoURI(mediaUri);
                mBinding.selectedVideo.start();
            }
        }
    }
}