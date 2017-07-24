package kr.edcan.sunrinton.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import kr.edcan.sunrinton.utils.CredentialsManager;
import kr.edcan.sunrinton.utils.NetworkHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Junseok Oh on 2017-07-24.
 */

public class HistoryFragment extends Fragment {
    FragmentHistoryBinding binding;
    RecyclerView historyRecyclerview;
    ArrayList<History> arrayList = new ArrayList<>();

    LastAdapter adapter;

    public HistoryFragment() {
        NetworkHelper.getNetworkInstance().getHistory(
                CredentialsManager.getInstance().getActiveUser().second.getToken()
        ).enqueue(new Callback<ArrayList<History>>() {
            @Override
            public void onResponse(Call<ArrayList<History>> call, Response<ArrayList<History>> response) {
                switch (response.code()) {
                    case 200:
                        arrayList.addAll(response.body());
                        setLayout();
                        break;
                    default:
                        Log.e("asdf", response.code() + "");
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<History>> call, Throwable t) {
                Log.e("asdf", t.getLocalizedMessage());
            }
        });

    }

    private void setLayout() {
        if(arrayList.size() ==0){
            binding.errorText.setVisibility(View.VISIBLE);
            binding.historyRecyclerView.setVisibility(View.GONE);
        } else {
            binding.errorText.setVisibility(View.GONE);
            binding.historyRecyclerView.setVisibility(View.VISIBLE);
            adapter = new LastAdapter(arrayList, BR.content)
                    .map(History.class, new ItemType<ContentHistoryBinding>(R.layout.content_history) {
                        @Override
                        public void onBind(Holder<ContentHistoryBinding> holder) {
                            super.onBind(holder);
                            holder.getBinding().setActivity(HistoryFragment.this);
                        }
                    })
                    .into(historyRecyclerview);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false);
        historyRecyclerview = binding.historyRecyclerView;
        historyRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        return binding.getRoot();
    }

    public void onListClicked(History item) {
        switch (item.getTitle()) {

        }
    }
}
