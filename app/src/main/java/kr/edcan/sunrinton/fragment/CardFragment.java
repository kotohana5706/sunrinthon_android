package kr.edcan.sunrinton.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;

import kr.edcan.sunrinton.HWPayInfoActivity;
import kr.edcan.sunrinton.R;
import kr.edcan.sunrinton.databinding.FragmentCardBinding;
import kr.edcan.sunrinton.databinding.FragmentSettingsBinding;
import kr.edcan.sunrinton.utils.CredentialsManager;

/**
 * Created by Junseok Oh on 2017-07-24.
 */

public class CardFragment extends Fragment {
    FragmentCardBinding binding;

    public CardFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_card, container, false);
        binding.cardNum.setText(CredentialsManager.getInstance().getActiveUser().second.get_id());
        binding.cardContainer.setBackground(new ColorDrawable(Color.parseColor(
                CredentialsManager.getInstance().getColorBackground()
        )));
        binding.moneyLeft.setText(CredentialsManager.getInstance().getActiveUser().second.getMoney() + "원");
        binding.charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getActivity())
                        .title("결제할 금액을 선택하세요")
                        .items("1000원", "5000원", "10000원", "50000원")
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                startActivity(new Intent(getActivity(), HWPayInfoActivity.class)
                                        .putExtra("money", text.toString()));
                            }
                        }).show();
            }
        });
        binding.giveSomeone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getActivity())
                        .title("선물할 금액을 선택하세요")
                        .items("1000원", "5000원", "10000원", "50000원")
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                startActivity(new Intent(getActivity(), HWPayInfoActivity.class)
                                        .putExtra("money", text.toString()));
                            }
                        }).show();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (binding != null) {
            binding.moneyLeft.setText(CredentialsManager.getInstance().getActiveUser().second.getMoney() + "원");
            binding.cardContainer.setBackground(new ColorDrawable(Color.parseColor(
                    CredentialsManager.getInstance().getColorBackground()
            )));
        }
    }
}
