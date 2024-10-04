package com.example.json;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class KhohangActivity extends AppCompatActivity {
    private Button them, rturn;
    private ListView listhh;
    private ArrayList<String> hanghoa;
    private ArrayAdapter<String> adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hienthi_layout);
        khaibao();
        loadData();
        listhh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selecthh = hanghoa.get(i);
                String mahang = selecthh.split(":")[0].trim();
                String message = "Bạn đã chọn: " + selecthh;
                Toast.makeText(KhohangActivity.this, message, Toast.LENGTH_SHORT).show();
                Intent chitiethh= new Intent(KhohangActivity.this, ItemDetailActivity.class);
                chitiethh.putExtra("Mahang", mahang);
                startActivity(chitiethh);
            }
        });

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xử lý sự kiện khi nhấn nút Thêm
                Intent intent = new Intent(KhohangActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });

        rturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xử lý sự kiện khi nhấn nút Return
                finish();
            }
        });
    }

    private void khaibao() {
        them = findViewById(R.id.btnthem);
        rturn = findViewById(R.id.btnreturn);
        listhh = findViewById(R.id.listhanghoa);
        dbHelper = new DatabaseHelper(this);
        hanghoa = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, hanghoa);
        listhh.setAdapter(adapter);
    }

    private void loadData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String mahang = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                String tenhang = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                String chungloai = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CATEGORY));
                int soluong = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_QUANTITY));
                String xuatxu = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ORIGIN));
                String item = mahang + ": " + tenhang + " - " + chungloai + " - " + soluong + " - " + xuatxu;
                hanghoa.add(item);
            }
            cursor.close();
        }
        adapter.notifyDataSetChanged();
    }
}
