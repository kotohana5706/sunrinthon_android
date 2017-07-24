package kr.edcan.sunrinton;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import kr.edcan.sunrinton.databinding.ActivityHwpayPasswordBinding;
import kr.edcan.sunrinton.models.User;
import kr.edcan.sunrinton.utils.CredentialsManager;
import kr.edcan.sunrinton.utils.NetworkHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HWPayPasswordActivity extends BaseActivity {

    ActivityHwpayPasswordBinding binding;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> colorArray = new ArrayList<>();
    ArrayList<Integer> resArray = new ArrayList<>();

    @Override
    protected void setDefault() {
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(ContextCompat.getColor(getApplicationContext(), R.color.hwColor)));
        setToolbarTitle("HW Pay");
        toolbar.setTitleTextColor(Color.WHITE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.hwColor));
        }
        Collections.addAll(arrayList,
                "현대카드", "비씨카드", "KB국민카드", "삼성카드", "신한카드", "롯데카드", "NH농협카드", "하나카드", "씨티카드", "우리카드");
        Collections.addAll(colorArray,
                "#242424", "#e56c6c", "#532a28", "#9b9b9b", "#66ACFF", "#9F281E", "#119E28", "#6A940C", "#022491", "#2A6680");
        Collections.addAll(resArray,
                R.drawable.pic_pay_hyundai, R.drawable.pic_pay_bakacard, R.drawable.pic_pay_kb, R.drawable.pic_pay_samsung, R.drawable.pic_pay_sinhan, R.drawable.pic_pay_lotte, R.drawable.pic_pay_nh, R.drawable.pic_pay_hana, R.drawable.pic_pay_citi, R.drawable.pic_pay_woori);
        binding = (ActivityHwpayPasswordBinding) baseBinding;
        binding.cardType.setText(arrayList.get(getIntent().getIntExtra("bankType", 0)));
        binding.cardUser.setText(
                CredentialsManager.getInstance().getActiveUser().second.getName() + " " + 9990
        );
        binding.cardContainer.setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorArray.get(getIntent().getIntExtra("bankType", 0)))));
        binding.cardTypeImage.setImageResource(resArray.get(getIntent().getIntExtra("bankType", 0)));
        binding.money.setText(getIntent().getStringExtra("money"));
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.passwordInput.getText().toString().length() < 6)
                    Toast.makeText(HWPayPasswordActivity.this, "비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                else {
                    NetworkHelper.getNetworkInstance().chargeSelf(
                            CredentialsManager.getInstance().getActiveUser().second.getToken(),
                            Integer.parseInt(getIntent().getStringExtra("money").replace("원", ""))
                    ).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            switch (response.code()) {
                                case 200:
                                    CredentialsManager.getInstance().updateUserInfo(response.body());
                                    startActivity(new Intent(getApplicationContext(), HWPayCompleteActivity.class));
                                    finish();
                                    break;
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.e("asdf", t.getLocalizedMessage());
                        }
                    });
                }
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_hwpay_password;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }
}
