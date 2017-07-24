package kr.edcan.sunrinton;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import kr.edcan.sunrinton.databinding.ActivityHwpayInfoBinding;

public class HWPayInfoActivity extends BaseActivity {

    ActivityHwpayInfoBinding binding;
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void setDefault() {
        binding = (ActivityHwpayInfoBinding) baseBinding;
        binding.costText.setText(getIntent().getStringExtra("money"));
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(ContextCompat.getColor(getApplicationContext(), R.color.hwColor)));
        setToolbarTitle("HW Pay");
        toolbar.setTitleTextColor(Color.WHITE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.hwColor));
        }
        Collections.addAll(arrayList,
                "현대카드", "비씨카드", "KB국민카드", "삼성카드", "신한카드", "롯데카드", "NH농협카드", "하나카드", "씨티카드", "우리카드");
        ArrayAdapter adapter = new ArrayAdapter(
                getApplicationContext(),
                R.layout.hwpay_list,
                R.id.text,
                arrayList);
        binding.payTypeSpinner.setAdapter(adapter);
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.checkBox.isChecked()) {
                    startActivity(new Intent(getApplicationContext(), HWPayPasswordActivity.class)
                            .putExtra("money", getIntent().getStringExtra("money"))
                            .putExtra("bankType", binding.payTypeSpinner.getSelectedItemPosition()));
                    finish();
                } else
                    Toast.makeText(HWPayInfoActivity.this, "약관에 동의하셔야 결제가 가능합니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_hwpay_info;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }
}
