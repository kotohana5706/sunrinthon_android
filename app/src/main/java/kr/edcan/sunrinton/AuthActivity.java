package kr.edcan.sunrinton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import kr.edcan.sunrinton.databinding.ActivityAuthBinding;
import kr.edcan.sunrinton.models.User;
import kr.edcan.sunrinton.utils.CredentialsManager;
import kr.edcan.sunrinton.utils.NetworkHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends BaseActivity {
    LoginButton loginButton;
    CallbackManager callbackManager;
    ActivityAuthBinding binding;

    @Override
    protected void setDefault() {
        binding = (ActivityAuthBinding) baseBinding;
        loginButton = binding.realFBButton;
        disableToggle();
        setToolbarTitle("로그인");
        binding.fbLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.performClick();
            }
        });

        String permissions[] = new String[]{"email", "user_about_me"};
        loginButton.setReadPermissions(permissions);
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("asdf", loginResult.getAccessToken().getToken());
                NetworkHelper.getNetworkInstance().loginByFacebook(loginResult.getAccessToken().getToken()).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        switch (response.code()) {
                            case 200:
                                CredentialsManager.getInstance().saveUserInfo(response.body(), 0);
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                Toast.makeText(AuthActivity.this, response.body().getName() + "님 환영합니다!", Toast.LENGTH_SHORT).show();
                                finish();
                                break;
                            default:
                                Toast.makeText(AuthActivity.this, "로그인 할 수 없습니다.", Toast.LENGTH_SHORT).show();
                                Log.e("asdf", response.code() + "");
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("asdf", t.getLocalizedMessage());
                    }
                });
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        binding.loginBtnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.emailInput.getText().toString().equals("") || binding.passwordInput.getText().toString().equals(""))
                    Toast.makeText(AuthActivity.this, "빈칸 없이 입력해주세요", Toast.LENGTH_SHORT).show();
                else {
                    NetworkHelper.getNetworkInstance().loginLocal(
                            binding.emailInput.getText().toString().trim(),
                            binding.passwordInput.getText().toString().trim()
                    ).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            switch (response.code()) {
                                case 200:
                                    CredentialsManager.getInstance().saveUserInfo(response.body(), 1);
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    finish();
                                    Toast.makeText(AuthActivity.this, response.body().getName() + "님 환영합니다!", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(AuthActivity.this, "아이디 혹은 비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_auth;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }
}
