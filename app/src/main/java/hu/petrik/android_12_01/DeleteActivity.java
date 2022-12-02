package hu.petrik.android_12_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import hu.petrik.android_12_01.databinding.ActivityDeleteBinding;

public class DeleteActivity extends AppCompatActivity {
    
    ActivityDeleteBinding binding;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeleteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        db = new DBHelper(DeleteActivity.this);
        
        binding.buttonDeleteDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = binding.editTextIdDelete.getText().toString().trim();
                if (id.isEmpty()){
                    Toast.makeText(DeleteActivity.this, "A mező kitöltése kötelező!", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.torles(id) == 0){
                        Toast.makeText(DeleteActivity.this, "Sikertelen törlés, nincs ilyen id", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DeleteActivity.this, "Sikeres törlés!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.buttonBackDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeleteActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}