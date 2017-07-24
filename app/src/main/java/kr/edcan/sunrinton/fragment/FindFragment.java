package kr.edcan.sunrinton.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import kr.edcan.sunrinton.R;
import kr.edcan.sunrinton.databinding.FragmentFindBinding;
import kr.edcan.sunrinton.databinding.FragmentSettingsBinding;
import kr.edcan.sunrinton.models.Map;
import kr.edcan.sunrinton.utils.GpsInfo;
import kr.edcan.sunrinton.utils.NetworkHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Junseok Oh on 2017-07-24.
 */

public class FindFragment extends Fragment implements OnMapReadyCallback {
    FragmentFindBinding binding;
    GpsInfo gpsInfo;
    GoogleMap map;
    ArrayList<Map> arrayList = new ArrayList<>();

    public FindFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_find, container, false);
        gpsInfo = new GpsInfo(getContext());
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return binding.getRoot();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        if (gpsInfo.isGetLocation()) {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(gpsInfo.getLatitude(), gpsInfo.getLongitude()), 16.0f));
            NetworkHelper.getNetworkInstance().getMaps(gpsInfo.getLatitude(), gpsInfo.getLongitude()).enqueue(new Callback<ArrayList<Map>>() {
                @Override
                public void onResponse(Call<ArrayList<Map>> call, Response<ArrayList<Map>> response) {
                    switch (response.code()) {
                        case 200:
                            arrayList.addAll(response.body());
                            for (Map m : arrayList) {
                                map.addMarker(new MarkerOptions().position(new LatLng(m.getLatitude(), m.getLongitude())).title(m.getTitle()));
                            }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Map>> call, Throwable t) {

                }
            });
            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Map dest = null;
                    for (Map m : arrayList) {
                        if (m.getTitle().equals(marker.getTitle())) {
                            dest = m;
                            break;
                        }
                    }
                    binding.cardViewContainer.setVisibility(View.VISIBLE);
                    binding.title.setText(dest.getTitle());
                    binding.content.setText(dest.getAddress());
                    return false;
                }
            });
        }

    }
}
