package hu.petrik.android_12_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Toast;

import hu.petrik.android_12_01.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEventListeners();
        db = new DBHelper(MainActivity.this);
    }

    private void addEventListeners() {
        binding.resultTextView.setMovementMethod(new ScrollingMovementMethod());

        binding.dataReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor adatok = db.listaz();
                if (adatok.getCount() == 0){
                    Toast.makeText(MainActivity.this, "Nincs az adatbázisban bejegyzés", Toast.LENGTH_SHORT).show();
                } else {
                    StringBuilder bobTheBuilder = new StringBuilder();
                    while (adatok.moveToNext()){
                        bobTheBuilder.append("ID: ").append(adatok.getInt(0));
                        bobTheBuilder.append(System.lineSeparator());
                        bobTheBuilder.append("Vezetéknév: ").append(adatok.getString(1));
                        bobTheBuilder.append(System.lineSeparator());
                        bobTheBuilder.append("Keresztnév: ").append(adatok.getString(2));
                        bobTheBuilder.append(System.lineSeparator());
                        bobTheBuilder.append("Jegy: ").append(adatok.getInt(3));
                        bobTheBuilder.append(System.lineSeparator());
                        bobTheBuilder.append(System.lineSeparator());
                    }
                    binding.resultTextView.setText(bobTheBuilder.toString());
                }
            }
        });
        binding.dataInsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intent);
                finish();
            }
        });
        binding.dataModifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                startActivity(intent);
                finish();
            }
        });
        binding.dataDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}