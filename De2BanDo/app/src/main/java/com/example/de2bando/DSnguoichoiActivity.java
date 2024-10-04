package com.example.de2bando;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import androidx.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import androidx.appcompat.app.AppCompatActivity;

public class DSnguoichoiActivity extends AppCompatActivity {
    private ListView dsnguoi;
    private Button back,show;

    private String jsonData ="[{\"Ho ten\":\"Nguyen Van Dang\",\"Tuoi\":\"21\"},{\"Ho ten\":\"Pham Tuan Anh\",\"Tuoi\":\"20\"}]";
//    private String json ="[{\"Ho ten\":\"Nguyen Van Dang\",\"Tuoi\":21}]";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nguoichoi_layout);
        dsnguoi=findViewById(R.id.listnguoi);
        show=findViewById(R.id.btnshow);
        back=findViewById(R.id.btnbback);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> nguoiList = new ArrayList<>();

                try {
                    JSONArray jsonArray = new JSONArray(jsonData);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String ten = jsonObject.getString("Ho ten");
                        String tuoi = jsonObject.getString("Tuoi");
                        nguoiList.add(ten + " - Tuoi: " + tuoi);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(DSnguoichoiActivity.this, android.R.layout.simple_list_item_1, nguoiList);
                dsnguoi.setAdapter(adapter);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
