package com.gmm.www.mvvmnews.otherfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmm.www.mvvmnews.R;
import com.gmm.www.mvvmnews.databinding.FragmentServiceBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

/**
 * @author:gmm
 * @date:2020/4/26
 * @类说明:
 */
public class ServiceFragment extends Fragment {
    private FragmentServiceBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_service,container,false);
        mBinding.text.setText(getString(R.string.menu_services));
        return mBinding.getRoot();
    }
}
