package com.example.ktra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;

public class moviesActivity extends AppCompatActivity {
    private int age = 0;
    private String[] moviesUnder13 = {"Doreamon", "Tom and Jerry"};
    private String[] movies14to18 = {"Doreamon", "Tom and Jerry", "Đào phở Piano"};
    private String[] moviesAbove18 = {"Doreamon", "Tom and Jerry", "Đào phở Piano", "Em chưa 18"};
    private EditText etName;
    private TextView tvAge;
    private ListView listViewMovies;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dkiphim_layout);

        etName = findViewById(R.id.Name);
        tvAge = findViewById(R.id.showAge);
        listViewMovies = findViewById(R.id.listmoive);
        Button buttonIncreaseAge = findViewById(R.id.btnAge1);
        Button buttonDecreaseAge = findViewById(R.id.btnAge2);
        Button buttonRegister = findViewById(R.id.btnDangki);

        buttonIncreaseAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age++;
                updateAgeDisplay();
            }
        });

        buttonDecreaseAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (age > 0) {
                    age--;
                    updateAgeDisplay();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(moviesActivity.this);
                    builder.setMessage("Không thể giảm tuổi hơn 0!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    builder.create().show();
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMovieList();
            }
        });

        listViewMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String movie = (String) parent.getItemAtPosition(position);
                showDialog(movie);
            }
        });
    }

    private void updateAgeDisplay() {
        tvAge.setText("Tuổi: " + age);
    }

    private void updateMovieList() {
        if (age < 13) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, moviesUnder13);
        } else if (age <= 18) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movies14to18);
        } else {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, moviesAbove18);
        }
        listViewMovies.setAdapter(adapter);
    }

    private void showDialog(String movie) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin đặt phim");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog, null);
        builder.setView(dialogView);

        TextView tvDialogName = dialogView.findViewById(R.id.tvDialogName);
        TextView tvDialogAge = dialogView.findViewById(R.id.tvDialogAge);
        TextView tvDialogMovie = dialogView.findViewById(R.id.tvDialogMovie);
        Button buttonConfirm = dialogView.findViewById(R.id.buttonConfirm);

        tvDialogName.setText("Họ và tên: " + etName.getText().toString());
        tvDialogAge.setText("Tuổi: " + age);
        tvDialogMovie.setText("Phim: " + movie);

        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false); // Không cho phép thoát khi click bên ngoài
        dialog.setCancelable(false); // Không cho phép thoát khi bấm nút back trên thiết bị

        dialog.show();

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
