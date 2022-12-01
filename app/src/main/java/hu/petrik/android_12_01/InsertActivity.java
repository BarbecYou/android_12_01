package hu.petrik.android_12_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import hu.petrik.android_12_01.databinding.ActivityInsertBinding;
import hu.petrik.android_12_01.databinding.ActivityMainBinding;

public class InsertActivity extends AppCompatActivity {

    ActivityInsertBinding binding;

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEventListeners();
        db = new DBHelper(InsertActivity.this);
    }

    private void addEventListeners() {
        binding.insertInsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vezetekNev = binding.veznev.getText().toString().trim();
                String keresztNev = binding.kernev.getText().toString().trim();
                String jegyString = binding.jegy.getText().toString().trim();
                if (vezetekNev.isEmpty() || keresztNev.isEmpty() || jegyString.isEmpty()){
                    Toast.makeText(InsertActivity.this, "Minden mező kitöltése kötelező", Toast.LENGTH_SHORT).show();
                } else {
                    int jegy = Integer.parseInt(jegyString);
                    // TODO: jegy validation
                    if (db.rogzites(vezetekNev, keresztNev, jegy)){
                        Toast.makeText(InsertActivity.this, "Sikeres adatrögzítés", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(InsertActivity.this, "Sikertelen adatrögzítés", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.insertBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}