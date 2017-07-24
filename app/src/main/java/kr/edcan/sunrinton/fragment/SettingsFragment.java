package kr.edcan.sunrinton.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nitrico.lastadapter.Holder;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;

import java.util.ArrayList;
import java.util.Collections;

import kr.edcan.sunrinton.BR;
import kr.edcan.sunrinton.R;
import kr.edcan.sunrinton.databinding.ContentSettingsBinding;
import kr.edcan.sunrinton.databinding.ContentSettingsHeaderBinding;
import kr.edcan.sunrinton.databinding.FragmentSettingsBinding;
import kr.edcan.sunrinton.models.Settings;

/**
 * Created by Junseok Oh on 2017-07-24.
 */

public class SettingsFragment extends Fragment {
    FragmentSettingsBinding binding;
    LastAdapter adapter;
    ArrayList<Object> arrayList = new ArrayList<>();
    RecyclerView settingRecyclerView;

    public SettingsFragment() {
        Collections.addAll(arrayList,
                "내 프로필",
                new Settings("닉네임 변경", R.drawable.ic_settings_name, "1000GUK에서 사용할 닉네임을 변경합니다."),
                "1000GUK 카드",
                new Settings("카드 디자인 변경", R.drawable.ic_settings_change, "사용하고 있는 카드의 디자인을 변경합니다."),
                new Settings("카드 인쇄", R.drawable.ic_settings_print, "오프라인으로 사용가능한 카드 인쇄를 신청합니다."));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
        settingRecyclerView = binding.settingsRecyclerView;
        settingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LastAdapter(arrayList, BR.content)
                .map(String.class, new ItemType<ContentSettingsHeaderBinding>(R.layout.content_settings_header))
                .map(Settings.class, new ItemType<ContentSettingsBinding>(R.layout.content_settings) {
                    @Override
                    public void onBind(Holder<ContentSettingsBinding> holder) {
                        super.onBind(holder);
                        holder.getBinding().setActivity(SettingsFragment.this);
                    }
                }).into(settingRecyclerView);
        return binding.getRoot();
    }

    public void onListClicked(Settings item) {
        switch (item.getTitle()) {
            case "닉네임 변경":
                break;
            case "카드 디자인 변경":
                break;
            case "카드 인쇄":
                break;
        }
    }
}
