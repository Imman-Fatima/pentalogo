package com.example.pentalogo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etName, etEmail, etAddress, etPassword;
    CheckBox cbAgree;
    Button btnWelcome, btnSubmit;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        etPassword = findViewById(R.id.etPassword);
        cbAgree = findViewById(R.id.cbAgree);
        btnWelcome = findViewById(R.id.btnWelcome);
        btnSubmit = findViewById(R.id.btnSubmit);

        prefs = getSharedPreferences("PentagonPrefs", MODE_PRIVATE);

        // Saved data wapis load karo
        etName.setText(prefs.getString("name", ""));
        etEmail.setText(prefs.getString("email", ""));
        etAddress.setText(prefs.getString("address", ""));
        etPassword.setText(prefs.getString("password", ""));
        cbAgree.setChecked(prefs.getBoolean("agree", false));

        btnWelcome.setOnClickListener(v ->
                Toast.makeText(this, "Welcome to Pentagon!", Toast.LENGTH_SHORT).show());

        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || address.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.setError("Invalid email");
                return;
            }
            if (password.length() < 6) {
                etPassword.setError("Minimum 6 characters");
                return;
            }
            if (!cbAgree.isChecked()) {
                Toast.makeText(this, "Please check the box", Toast.LENGTH_SHORT).show();
                return;
            }

            saveData();

            Intent i = new Intent(MainActivity.this, DetailActivity.class);
            i.putExtra("name", name);
            i.putExtra("email", email);
            i.putExtra("address", address);
            i.putExtra("password", password);
            startActivity(i);
        });
    }

    private void saveData() {
        SharedPreferences.Editor e = prefs.edit();
        e.putString("name", etName.getText().toString());
        e.putString("email", etEmail.getText().toString());
        e.putString("address", etAddress.getText().toString());
        e.putString("password", etPassword.getText().toString());
        e.putBoolean("agree", cbAgree.isChecked());
        e.apply();
    }

    // Lifecycle: activity background mein jaye to data save
    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }
}