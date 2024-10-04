package com.example.json;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class AddItemActivity extends AppCompatActivity {

    private EditText editTextMahang, editTextTenhang, editTextChungloai, editTextSoluong, editTextXuatxu;
    private Button buttonSave;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.additem);

        dbHelper = new DatabaseHelper(this);

        editTextMahang = findViewById(R.id.editTextMahang);
        editTextTenhang = findViewById(R.id.editTextTenhang);
        editTextChungloai = findViewById(R.id.editTextChungloai);
        editTextSoluong = findViewById(R.id.editTextSoluong);
        editTextXuatxu = findViewById(R.id.editTextXuatxu);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemToDatabase();
            }
        });
    }

    private void addItemToDatabase() {
        String mahang = editTextMahang.getText().toString().trim();
        String tenhang = editTextTenhang.getText().toString().trim();
        String chungloai = editTextChungloai.getText().toString().trim();
        String soluong = editTextSoluong.getText().toString().trim();
        String xuatxu = editTextXuatxu.getText().toString().trim();

        if (mahang.isEmpty() || tenhang.isEmpty() || chungloai.isEmpty() || soluong.isEmpty() || xuatxu.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_ID, mahang);
        values.put(DatabaseHelper.COLUMN_NAME, tenhang);
        values.put(DatabaseHelper.COLUMN_CATEGORY, chungloai);
        values.put(DatabaseHelper.COLUMN_QUANTITY, Integer.parseInt(soluong));
        values.put(DatabaseHelper.COLUMN_ORIGIN, xuatxu);

        long newRowId = db.insert(DatabaseHelper.TABLE_NAME, null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
            finish(); // Quay lại màn hình trước đó
        } else {
            Toast.makeText(this, "Lỗi khi thêm sản phẩm", Toast.LENGTH_SHORT).show();
        }
    }
}
