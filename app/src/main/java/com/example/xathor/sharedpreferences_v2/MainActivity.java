package com.example.xathor.sharedpreferences_v2;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnSave;
    private TextView txtChange;
    private RadioButton rbRojo, rbAzul, rbDefault;

    private SharedPreferences preferences;

    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        declaraciones();

        color = preferences.getInt("color", R.color.negro);
        txtChange.setTextColor(getResources().getColor(color));
        if(color == R.color.azul) {
            rbAzul.setChecked(true);
        } else if(color == R.color.rojo) {
            rbRojo.setChecked(true);
        } else {
            rbDefault.setChecked(true);
        }

        btnSave.setOnClickListener(saveColor);
    }

    public void declaraciones() {
        btnSave = (Button)findViewById(R.id.btnSave);
        txtChange = (TextView)findViewById(R.id.txtChange);
        rbAzul = (RadioButton)findViewById(R.id.rbAzul);
        rbRojo = (RadioButton)findViewById(R.id.rbRojo);
        rbDefault = (RadioButton)findViewById(R.id.rbDefault);

        preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
    }

    public View.OnClickListener saveColor = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(rbAzul.isChecked()) {
                color = R.color.azul;
            } else if(rbRojo.isChecked()) {
                color = R.color.rojo;
            } else {
                color = R.color.negro;
            }

            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("color", color);
            editor.commit();

            txtChange.setTextColor(getResources().getColor(color));
        }
    };

}