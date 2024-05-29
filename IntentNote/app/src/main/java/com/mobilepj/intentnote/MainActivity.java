package com.mobilepj.intentnote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public static final String ACTION_CREATE_NOTE = "com.google.android.gms.actions.CREATE_NOTE";
    public static final String EXTRA_NAME = "com.google.android.gms.actions.EXTRA_NAME";
    public static final String EXTRA_TEXT = "com.google.android.gms.actions.EXTRA_TEXT";

    private EditText titleEditText;
    private EditText contentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleEditText = findViewById(R.id.titleEditText);
        contentEditText = findViewById(R.id.contentEditText);
        Button saveButton = findViewById(R.id.saveButton);

        Intent intent = getIntent();
        if (ACTION_CREATE_NOTE.equals(intent.getAction())) {
            String title = intent.getStringExtra(EXTRA_NAME);
            String text = intent.getStringExtra(EXTRA_TEXT);

            if (title != null) {
                titleEditText.setText(title);
            }
            if (text != null) {
                contentEditText.setText(text);
            }
        }
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String content = contentEditText.getText().toString();

                if (!title.isEmpty() && !content.isEmpty()) {
                    // Save the note (you can customize this part to save the note as you want)
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("title", title);
                    resultIntent.putExtra("content", content);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void createNoteIntent(String subject, String text) {
        Intent intent = new Intent("com.google.android.gms.actions.CREATE_NOTE")
                .putExtra("com.google.android.gms.actions.EXTRA_NAME", subject)
                .putExtra("com.google.android.gms.actions.EXTRA_TEXT", text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}

