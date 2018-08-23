package com.gumanx.securebarnacle;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class NotepadActivity extends AppCompatActivity {

    EditText noteText, noteTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        noteText = findViewById(R.id.noteText);
        noteTitle = findViewById(R.id.noteTitle);

        String title = getIntent().getStringExtra("title");
        if (title != null) {
            noteTitle.setText(title);
            noteText.setText(open(title));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notepad, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.save:
                // User chose the "save" option, so save the file.
                String filename = noteTitle.getText().toString();
                if (filename.equals("")) {
                    Toast.makeText(this, "Pick a Title!", Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    return save(filename);
                }

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean save(String filename) {
        // Saves text to the specified filename in the App's internal storage.
        // Gives a toast showing either success or failure in saving.
        try {
            OutputStreamWriter out =
                    new OutputStreamWriter(openFileOutput(filename, Context.MODE_PRIVATE));
            BufferedWriter writer = new BufferedWriter(out);
            writer.write(noteText.getText().toString());
            writer.close();
            Toast.makeText(this, "Note Saved!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error Saving:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private String open(String filename) {
        // Opens the specified file, if it exists.
        // Returns the contents of the file.
        try {
            InputStreamReader in = new InputStreamReader(openFileInput(filename));
            BufferedReader reader = new BufferedReader(in);
            String str;
            StringBuilder buffer = new StringBuilder();
            while ((str = reader.readLine()) != null) {
                buffer.append(str);
            }
            reader.close();
            return buffer.toString();
        } catch (Exception e) {
            Toast.makeText(this, "Error Opening:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}
