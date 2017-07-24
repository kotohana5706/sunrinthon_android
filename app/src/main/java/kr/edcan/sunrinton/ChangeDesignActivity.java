package kr.edcan.sunrinton;

import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.github.nitrico.lastadapter.Holder;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;

import java.util.ArrayList;
import java.util.Collections;

import kr.edcan.sunrinton.databinding.ActivityChangeDesignBinding;
import kr.edcan.sunrinton.databinding.ChangelayoutContentBinding;
import kr.edcan.sunrinton.utils.CredentialsManager;

public class ChangeDesignActivity extends BaseActivity {

    ActivityChangeDesignBinding binding;
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void setDefault() {
        Collections.addAll(arrayList,
                "#FF7D70",
                "#344B7D",
                "#F09AD6",
                "#FF9C9C");
        setToolbarTitle("카드 디자인 변경하기");
        binding = (ActivityChangeDesignBinding) baseBinding;
        binding.changeDesignRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        new LastAdapter(arrayList, BR.content)
                .map(String.class, new ItemType<ChangelayoutContentBinding>(R.layout.changelayout_content) {
                    @Override
                    public void onBind(Holder<ChangelayoutContentBinding> holder) {
                        super.onBind(holder);
                        holder.getBinding().setActivity(ChangeDesignActivity.this);
                        holder.getBinding().setUser(
                                CredentialsManager.getInstance().getActiveUser().second
                        );
                    }
                })
                .into(binding.changeDesignRecyclerView);


    }

    public void updateDesign(String color) {
        CredentialsManager.getInstance().save("color", color);
        Toast.makeText(this, "카드 디자인이 변경되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_change_design;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }
}
