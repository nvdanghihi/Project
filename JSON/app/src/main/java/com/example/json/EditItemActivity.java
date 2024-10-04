package com.example.json;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditItemActivity extends AppCompatActivity {

    private TextView textViewMahang;
    private EditText editTextTenhang, editTextChungloai, editTextSoluong, editTextXuatxu;
    private Button buttonSave, buttonCancel;
    private DatabaseHelper dbHelper;
    private String mahang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edititem);

        dbHelper = new DatabaseHelper(this);

        textViewMahang = findViewById(R.id.textViewMahang);
        editTextTenhang = findViewById(R.id.editTextTenhang);
        editTextChungloai = findViewById(R.id.editTextChungloai);
        editTextSoluong = findViewById(R.id.editTextSoluong);
        editTextXuatxu = findViewById(R.id.editTextXuatxu);
        buttonSave = findViewById(R.id.buttonSave);
        buttonCancel = findViewById(R.id.buttonCancel);

        // Nhận mã hàng từ Intent
        Intent intent = getIntent();
        mahang = intent.getStringExtra("Mahang");

        // Tải thông tin chi tiết của mặt hàng
        loadItemDetails(mahang);

        // Xử lý sự kiện nhấn nút "Lưu"
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveItemDetails(mahang);
            }
        });

        // Xử lý sự kiện nhấn nút "Hủy"
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Quay lại màn hình trước đó
            }
        });
    }

    private void loadItemDetails(String mahang) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_NAME,  // Tên bảng
                null,                       // Các cột cần chọn
                DatabaseHelper.COLUMN_ID + " = ?",  // Điều kiện
                new String[]{mahang},       // Tham số điều kiện
                null,                       // Group by
                null,                       // Having
                null                        // Order by
        );

        if (cursor != null && cursor.moveToFirst()) {
            String tenhang = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            String chungloai = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CATEGORY));
            int soluong = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_QUANTITY));
            String xuatxu = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ORIGIN));

            textViewMahang.setText(mahang);
            editTextTenhang.setText(tenhang);
            editTextChungloai.setText(chungloai);
            editTextSoluong.setText(String.valueOf(soluong));
            editTextXuatxu.setText(xuatxu);

            cursor.close();
        }
    }

    private void saveItemDetails(String mahang) {
        String tenhang = editTextTenhang.getText().toString();
        String chungloai = editTextChungloai.getText().toString();
        int soluong = Integer.parseInt(editTextSoluong.getText().toString());
        String xuatxu = editTextXuatxu.getText().toString();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, tenhang);
        values.put(DatabaseHelper.COLUMN_CATEGORY, chungloai);
        values.put(DatabaseHelper.COLUMN_QUANTITY, soluong);
        values.put(DatabaseHelper.COLUMN_ORIGIN, xuatxu);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsUpdated = db.update(DatabaseHelper.TABLE_NAME, values, DatabaseHelper.COLUMN_ID + " = ?", new String[]{mahang});

        if (rowsUpdated > 0) {
            Toast.makeText(this, "Cập nhật sản phẩm thành công", Toast.LENGTH_SHORT).show();
            finish(); // Quay lại màn hình trước đó
        } else {
            Toast.makeText(this, "Lỗi khi cập nhật sản phẩm", Toast.LENGTH_SHORT).show();
        }
    }
}
