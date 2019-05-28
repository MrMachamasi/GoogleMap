package com.example.googlemap;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;

import model.LatitudeLongitude;

public class SearchActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private AutoCompleteTextView etCity;
    private Button btnSearch;
    private List<LatitudeLongitude> latitudeLongitudeList;
    Marker markerName;
    CameraUpdate center, zoom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        etCity = findViewById(R.id.etCity);
        btnSearch = findViewById(R.id.btnSearch);

        private void fillArrayListAndSetAdapter ();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etCity.getText().toString())) {
                    etCity.setError("Please enter a place name");
                    return;
                }
                int position = SearchArrayList(etCity.getText().toString());
                if (position > -1)
                    loadMap(position);
                else
                    Toast.makeText(SearchActivity.this, "Location not found by name :" + etCity.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void fillArrayListAndSetAdapter()

         {
             latitudeLongitudeList = new ArrayList<>();
            latitudeLongitudeList.add(new LatitudeLongitude(27.7134481, 85.3241922, "Naagpokhari"));
            latitudeLongitudeList.add(new LatitudeLongitude(27.7181749, 85.3173212, "Narayanhiti Palace"));
            latitudeLongitudeList.add(new LatitudeLongitude(27.7127827, 85.3265391, "Hotel Brihaspati"));
            String[] data = new String[latitudeLongitudeList.size()];

            for (int i = 0; i < data.length; i++) {
                data[i] = latitudeLongitudeList.get(i).getmarker();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    SearchActivity.this, android.R.layout.simple_list_item_1, data
            );
            etCity.setAdapter(adapter);
            etCity.setThreshold(1);
        }

        public int SearchArrayList (String name){
            for (int i = 0; i < latitudeLongitudeList.size(); i++) {
                if (latitudeLongitudeList.get(i).getMarker().contains(name)) {
                    return i;
                }
            }
            return -1;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
