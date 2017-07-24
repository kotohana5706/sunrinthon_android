package kr.edcan.sunrinton;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import kr.edcan.sunrinton.databinding.ActivityCardPrintBinding;
import kr.edcan.sunrinton.utils.CredentialsManager;

public class CardPrintActivity extends BaseActivity {

    ActivityCardPrintBinding binding;

    @Override
    protected void setDefault() {
        binding = (ActivityCardPrintBinding) baseBinding;
        setToolbarTitle("카드 인쇄");
        binding.nameInput.setText(CredentialsManager.getInstance().getActiveUser().second.getName());
        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNotEmpty(binding.nameInput, binding.numberInput, binding.addressInput)) {
                    new MaterialDialog.Builder(CardPrintActivity.this)
                            .title("카드 인쇄")
                            .content("이름 : " + binding.nameInput.getText().toString().trim() + "\n받을 주소 : " + binding.addressInput.getText().toString().trim() + "\n전화번호 : " + binding.numberInput.getText().toString() + "\n입력한 정보가 맞나요?")
                            .positiveText("확인")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    Toast.makeText(CardPrintActivity.this, binding.addressInput.getText().toString().trim() + "로 3일 내로 배송될 예정입니다.", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                    finish();
                } else
                    Toast.makeText(CardPrintActivity.this, "빈칸 없이 입력해주세요!", Toast.LENGTH_SHORT).show();
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
        return R.layout.activity_card_print;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }
}
