package com.example.tugaspraktikum1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText input = findViewById(R.id.input);
        Button okBtn = findViewById(R.id.okBtn);
        TextView output = findViewById(R.id.output);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = input.getText().toString();

                if (!TextUtils.isEmpty(inputText)){
                    String existingText = output.getText().toString();
                    if(!TextUtils.isEmpty(existingText)){
                        inputText += "\n";
                    }
                    String newText = inputText + existingText ;

                    output.setText(newText);
                    output.setVisibility(View.VISIBLE);
                    input.setText("");
                }
            }
        });
    }
}