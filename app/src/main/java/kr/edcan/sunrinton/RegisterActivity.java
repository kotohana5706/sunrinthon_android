package kr.edcan.sunrinton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import kr.edcan.sunrinton.databinding.ActivityRegisterBinding;
import kr.edcan.sunrinton.utils.NetworkHelper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity {


    ActivityRegisterBinding binding;

    @Override
    protected void setDefault() {
        binding = (ActivityRegisterBinding) baseBinding;
        disableToggle();
        setToolbarTitle("회원가입");
        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNotEmpty(binding.emailInput, binding.nicknameInput, binding.passwordInput, binding.passwordReInput)) {
                    if (binding.passwordInput.getText().toString().trim().equals(binding.passwordReInput.getText().toString().trim())) {
                        NetworkHelper.getNetworkInstance().registerLocal(
                                binding.emailInput.getText().toString().trim(),
                                binding.nicknameInput.getText().toString().trim(),
                                binding.passwordInput.getText().toString().trim()
                        ).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                switch (response.code()) {
                                    case 200:
                                        Toast.makeText(RegisterActivity.this, "회원가입이 정상적으로 처리되었습니다.", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), AuthActivity.class));
                                        finish();
                                        break;
                                    case 400:
                                        Toast.makeText(RegisterActivity.this, "이미 사용자가 존재합니다.", Toast.LENGTH_SHORT).show();
                                        break;
                                }

                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.e("asdf", t.getLocalizedMessage());
                            }
                        });
                    } else
                        Toast.makeText(RegisterActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(RegisterActivity.this, "빈칸 없이 입력해주세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean isNotEmpty(EditText... editTexts) {
        for (EditText e : editTexts) {
            if (e.getText().toString().trim().equals("")) return false;
        }
        return true;
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }
}
