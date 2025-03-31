package fh.at.servlcetestapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ✅ Dieser Code ist jetzt korrekt in onCreate
        EditText editTextMessage = findViewById(R.id.Textinput);
        Button buttonStartService = findViewById(R.id.button);

        buttonStartService.setOnClickListener(v -> {
            String message = editTextMessage.getText().toString();

            Intent serviceIntent = new Intent(MainActivity.this, MyService.class);
            serviceIntent.putExtra("message", message);

            startService(serviceIntent);
        });
    }
}
