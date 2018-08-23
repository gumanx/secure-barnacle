package com.gumanx.securebarnacle;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NotesAdapter extends RecyclerView.Adapter <NotesAdapter.NoteHolder> {

    private String[] notesList;

    public static class NoteHolder extends RecyclerView.ViewHolder {
        public TextView title;
        NoteHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
        }
    }

    NotesAdapter(String[] arr) {
        notesList = arr;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_row, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        holder.title.setText(notesList[position]);
    }

    @Override
    public int getItemCount() {
        return notesList.length;
    }
}
