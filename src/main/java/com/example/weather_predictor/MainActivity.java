package com.example.weather_predictor;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Dictionary;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {

    private final String[] CITIES = new String[]{"Москва", "Нижний Новгород", "Казань", "Санкт-Петербург", "Калининград"};
    private AutoCompleteTextView textView;
    private RadioButton b1, b2, b3, b4;
    private RadioGroup rg;
    private Button b;

    private Dictionary<String, String> codes = new Hashtable<>();
    private Dictionary<String , String> periods = new Hashtable<>();

    private static final int R1 = 1;
    private static final int R2 = 2;
    private static final int R3 = 3;
    private static final int R4 = 4;

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

        codes.put("Санкт-Петербург", "saint-petersburg");
        codes.put("Нижний Новгород", "nizhny-novgorod");
        codes.put("Москва", "moscow");
        codes.put("Казань", "kazan");
        codes.put("Калининград", "kaliningrad");

        textView = findViewById(R.id.inputEmail);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        rg = findViewById(R.id.group);

        b1.setId(R1);
        b2.setId(R2);
        b3.setId(R3);
        b4.setId(R4);
        b = findViewById(R.id.get_forecast_button);
        periods.put("1", "details/today");
        periods.put("2", "details/tomorrow");
        periods.put("3", "details");
        periods.put("4", "details/weekend");
    }

    public void search(View view){
        if (rg.getCheckedRadioButtonId() == -1) {
        } else {
            int periodt = rg.getCheckedRadioButtonId();
            String period = periods.get(Integer.toString(periodt));

            String city = codes.get(textView.getText().toString());
            String url = "https://yandex.ru/pogoda/ru/" + city + "/" + period;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Log.d("Error", e.toString());
                Toast.makeText(getApplicationContext(), "Could not proceed", Toast.LENGTH_LONG).show();
            }


        }
    }
}