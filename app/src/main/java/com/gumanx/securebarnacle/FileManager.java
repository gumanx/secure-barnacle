package com.gumanx.securebarnacle;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileManager {

    public static boolean save(Context context, String filename, String text) {
        // Saves text to the specified filename in the App's internal storage.
        // Gives a toast showing either success or failure in saving.
        // Returns true if successful, or false if not successful.
        try {
            OutputStreamWriter out =
                    new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            BufferedWriter writer = new BufferedWriter(out);
            writer.write(text);
            writer.close();
            Toast.makeText(context, "Note Saved!", Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e) {
            Toast.makeText(context, "Error Saving:" + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static String open(Context context, String filename) {
        // Opens the specified file, if it exists.
        // Returns the contents of the file.
        try {
            InputStreamReader in = new InputStreamReader(context.openFileInput(filename));
            BufferedReader reader = new BufferedReader(in);
            String str;
            StringBuilder buffer = new StringBuilder();
            while ((str = reader.readLine()) != null) {
                buffer.append(str);
            }
            reader.close();
            return buffer.toString();
        } catch (Exception e) {
            Toast.makeText(context, "Error Opening:" + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public static boolean delete(Context context, String filename) {
        // Deletes the specified filename in the given context, if it exists.
        // Returns true upon successful deletion, or false if not successful.
        try {
            return context.getFileStreamPath(filename).delete();
        } catch (Exception e) {
            Toast.makeText(context, "Error Deleting:" + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
