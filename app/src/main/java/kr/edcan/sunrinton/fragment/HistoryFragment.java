package kr.edcan.sunrinton.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.github.nitrico.lastadapter.Holder;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;

import java.util.ArrayList;
import java.util.Collections;

import kr.edcan.sunrinton.R;
import kr.edcan.sunrinton.databinding.ContentHistoryBinding;
import kr.edcan.sunrinton.databinding.FragmentHistoryBinding;
import kr.edcan.sunrinton.databinding.FragmentSettingsBinding;
import kr.edcan.sunrinton.models.History;

/**
 * Created by Junseok Oh on 2017-07-24.
 */

public class HistoryFragment extends Fragment {
    FragmentHistoryBinding binding;
    RecyclerView historyRecyclerview;
    ArrayList<History> arrayList = new ArrayList<>();

    LastAdapter adapter;

    public HistoryFragment() {
        Collections.addAll(arrayList,
                new History("asdf", "asdf", 3000),
                new History("asdf", "asdf", 3000),
                new History("asdf", "asdf", 3000),
                new History("asdf", "asdf", 3000),
                new History("asdf", "asdf", 3000),
                new History("asdf", "asdf", 3000)
        );
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false);
        historyRecyclerview = binding.historyRecyclerView;
        adapter = new LastAdapter(arrayList, BR.content)
                .map(Object.class, new ItemType<ContentHistoryBinding>(R.layout.content_history) {
                    @Override
                    public void onBind(Holder<ContentHistoryBinding> holder) {
                        super.onBind(holder);
                        holder.getBinding().setActivity(HistoryFragment.this);
                    }
                })
                .into(historyRecyclerview);
        return binding.getRoot();
    }

    public void onListClicked(History item) {
        switch (item.getTitle()) {

        }
    }
}
