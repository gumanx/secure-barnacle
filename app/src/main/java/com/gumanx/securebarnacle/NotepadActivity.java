package com.gumanx.securebarnacle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

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
            noteText.setText(FileManager.open(getBaseContext(), title));
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
        String filename = noteTitle.getText().toString();
        switch (item.getItemId()) {
            case R.id.save:
                // User chose the "save" option, so save the file.
                if (filename.equals("")) {
                    Toast.makeText(this, "Pick a Title!", Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    return FileManager.save(this, filename,
                            noteText.getText().toString());
                }

            case R.id.delete:
                // User chose the "delete" option, so delete the file.
                if (filename.equals("")) {
                    Toast.makeText(this, "Cannot Delete an Empty File!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (FileManager.delete(this, filename)) {
                        finish();
                        return true;
                    }
                }
                return false;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

}
