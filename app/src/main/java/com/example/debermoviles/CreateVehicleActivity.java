package com.example.debermoviles;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class CreateVehicleActivity extends AppCompatActivity {
    Button btn_add2;
    EditText brand, model, color;
    private FirebaseFirestore mifirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_vehicle); // Cambia el layout



        // Configura la ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Inicializa Firestore
        mifirestore = FirebaseFirestore.getInstance();

        // Obtén las referencias a los elementos del layout
        brand = findViewById(R.id.brand);
        model = findViewById(R.id.model);
        color = findViewById(R.id.color);
        btn_add2 = findViewById(R.id.btn_add2);

        btn_add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String brandVehicle = brand.getText().toString().trim();
                String modelVehicle = model.getText().toString().trim();
                String colorVehicle = color.getText().toString().trim();

                if (brandVehicle.isEmpty() || modelVehicle.isEmpty() || colorVehicle.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                } else {
                    postVehicle(brandVehicle, modelVehicle, colorVehicle);
                }
            }
        });
    }

    private void postVehicle(String brandVehicle, String modelVehicle, String colorVehicle) {
        Map<String, Object> map = new HashMap<>();
        map.put("brand", brandVehicle);
        map.put("model", modelVehicle);
        map.put("color", colorVehicle);

        mifirestore.collection("vehicles").add(map)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Vehículo creado exitosamente", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error al ingresar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

