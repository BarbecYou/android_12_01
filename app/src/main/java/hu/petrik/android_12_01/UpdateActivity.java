package hu.petrik.android_12_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import hu.petrik.android_12_01.databinding.ActivityUpdateBinding;

public class UpdateActivity extends AppCompatActivity {

    ActivityUpdateBinding binding;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new DBHelper(UpdateActivity.this);

        binding.buttonBackUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.buttonModifyUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = binding.editTextIdUpdate.getText().toString().trim();
                String veznev = binding.editTextVezNevUpdate.getText().toString().trim();
                String kernev = binding.editTextKerNevUpdate.getText().toString().trim();
                String jegy = binding.editTextJegyUpdate.getText().toString().trim();
                if (id.isEmpty() ||
                veznev.isEmpty() ||
                kernev.isEmpty() ||
                jegy.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Minden mező kitöltése kötelező", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.modositas(id, veznev, kernev, jegy) == 0){
                        Toast.makeText(UpdateActivity.this, "Sikertelen módosítás, az adott id nem létezik", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdateActivity.this, "Sikeres módosítás", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}