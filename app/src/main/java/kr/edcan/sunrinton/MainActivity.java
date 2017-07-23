package kr.edcan.sunrinton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import kr.edcan.sunrinton.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;
    @Override
    protected void setDefault() {

        binding = (ActivityMainBinding) baseBinding;
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AuthActivity.class));
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }
}
