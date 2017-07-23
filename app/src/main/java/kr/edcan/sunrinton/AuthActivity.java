package kr.edcan.sunrinton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import kr.edcan.sunrinton.databinding.ActivityAuthBinding;
import kr.edcan.sunrinton.models.User;
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
        loginButton = binding.fbLoginButton;
        binding.realFbLoginButton.setOnClickListener(new View.OnClickListener() {
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
                        Log.e("asdf", response.code() + "");
                        if (response.code() == 200) Log.e("asdf", response.body().getName());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

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
        return 0;
    }
}
