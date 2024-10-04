package com.example.json;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailActivity extends AppCompatActivity {

    private TextView textViewMahang, textViewTenhang, textViewChungloai, textViewSoluong, textViewXuatxu;
    private ImageView imageViewFlag;
    private Button buttonEdit, buttonDelete,buttonreturn;
    private DatabaseHelper dbHelper;
    private String mahang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        dbHelper = new DatabaseHelper(this);

        textViewMahang = findViewById(R.id.textViewMahang);
        textViewTenhang = findViewById(R.id.textViewTenhang);
        textViewChungloai = findViewById(R.id.textViewChungloai);
        textViewSoluong = findViewById(R.id.textViewSoluong);
        textViewXuatxu = findViewById(R.id.textViewXuatxu);
        imageViewFlag = findViewById(R.id.imageViewFlag);
        buttonEdit = findViewById(R.id.buttonEdit);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonreturn = findViewById(R.id.buttonReturn);

        // Nhận mã hàng từ Intent
        Intent intent = getIntent();
        mahang = intent.getStringExtra("Mahang");

        // Tải thông tin chi tiết của mặt hàng
        loadItemDetails(mahang);

        // Xử lý sự kiện nhấn nút "Sửa"
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(ItemDetailActivity.this, EditItemActivity.class);
                editIntent.putExtra("Mahang", mahang);
                startActivity(editIntent);
            }
        });

        // Xử lý sự kiện nhấn nút "Xóa"
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteConfirmationDialog();
            }
        });
        buttonreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xử lý sự kiện khi nhấn nút Return
                finish();
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
            textViewTenhang.setText(tenhang);
            textViewChungloai.setText(chungloai);
            textViewSoluong.setText(String.valueOf(soluong));
            textViewXuatxu.setText(xuatxu);

            // Hiển thị cờ của quốc gia
            int flagResId = getFlagResourceId(xuatxu);
            if (flagResId != 0) {
                imageViewFlag.setImageResource(flagResId);
            }

            cursor.close();
        }
    }

    private int getFlagResourceId(String xuatxu) {
        switch (xuatxu.toLowerCase()) {
            case "vietnam":
                return R.drawable.flag_vietnam;
            case "france":
                return R.drawable.flag_france;
            case "russia":
                return R.drawable.flag_russia;
            case "japan":
                return R.drawable.flag_japan;
            case "thailand":
                return R.drawable.flag_thailand;
            default:
                return 0;  // Default placeholder or no image
        }
    }

    private void showDeleteConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Bạn có chắc chắn muốn xóa mặt hàng này?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteItem(mahang);
                    }
                })
                .setNegativeButton("Không", null)
                .show();
    }

    private void deleteItem(String mahang) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsDeleted = db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COLUMN_ID + " = ?", new String[]{mahang});

        if (rowsDeleted > 0) {
            Toast.makeText(this, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
            finish(); // Quay lại màn hình trước đó
        } else {
            Toast.makeText(this, "Lỗi khi xóa sản phẩm", Toast.LENGTH_SHORT).show();
        }
    }
}
