package com.example.debermoviles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CreateVehicleActivity.class));
        });

    }
}
