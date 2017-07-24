package kr.edcan.sunrinton;

import android.view.View;
import android.widget.Toast;


import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import kr.edcan.sunrinton.databinding.ActivityReserveBinding;
import kr.edcan.sunrinton.models.Map;

public class ReserveActivity extends BaseActivity {

    Map map;
    ActivityReserveBinding binding;

    @Override
    protected void setDefault() {
        map = (Map) getIntent().getSerializableExtra("map");
        binding = (ActivityReserveBinding) baseBinding;
        binding.selectedMapText.setText(map.getTitle());
        binding.selectedMapAddressText.setText(map.getAddress());
        binding.datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                binding.datePicker.setText(year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일");
                            }
                        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                );
                dialog.show(getFragmentManager(), "DatePickerDialog");
            }
        });
        binding.timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog = TimePickerDialog.newInstance(
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
                                binding.timePicker.setText(hourOfDay + "시 " + minute + "분");

                            }
                        }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true
                );
                dialog.show(getFragmentManager(), "TimePickerDialog");
            }
        });

        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ReserveActivity.this, "예약이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_reserve;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }
}
