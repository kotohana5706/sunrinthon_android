package kr.edcan.sunrinton.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.edcan.sunrinton.R;
import kr.edcan.sunrinton.databinding.FragmentFindBinding;
import kr.edcan.sunrinton.databinding.FragmentSettingsBinding;

/**
 * Created by Junseok Oh on 2017-07-24.
 */

public class FindFragment extends Fragment {
    FragmentFindBinding binding;

    public FindFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_find, container, false);
        return binding.getRoot();
    }
}
