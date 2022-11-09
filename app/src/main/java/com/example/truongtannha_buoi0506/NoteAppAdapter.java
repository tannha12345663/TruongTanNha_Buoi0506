package com.example.truongtannha_buoi0506;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class NoteAppAdapter extends RecyclerView.Adapter<NoteAppAdapter.NoteAppVH> {
    ArrayList<NoteApp>noteApps;
    Listener listener;

    public NoteAppAdapter(ArrayList<NoteApp> noteApps, Listener listener) {
        this.noteApps = noteApps;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteAppVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_row,parent,false);
        return new NoteAppVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAppVH holder, int position) {
        NoteApp noteApp =noteApps.get(position);
        holder.txDetail.setText(noteApp.getDetaile());
        holder.txTitle.setText(noteApp.getTitle());
        holder.txDate.setText(noteApp.getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemListener(position,noteApp);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteApps.size();
    }

    class NoteAppVH extends RecyclerView.ViewHolder{
        TextView txTitle, txDate, txDetail;


        public NoteAppVH(@NonNull View itemView) {
            super(itemView);
            txTitle=itemView.findViewById(R.id.txTitle);
            txDate = itemView.findViewById(R.id.txDate);
            txDetail=itemView.findViewById(R.id.txDetail);

        }

    }
    interface Listener{
        void onItemListener(int pos,NoteApp noteApp);
    }
    public void addNote(NoteApp noteApp){
        noteApps.add(noteApp);
        notifyDataSetChanged();
    }
    public void editNote(NoteApp noteApp,int pos){
        noteApps.set(pos, noteApp);
        notifyDataSetChanged();
    }
    public void deleteNote(int pos){
        noteApps.remove(pos);
        notifyDataSetChanged();
    }
    public void deleteNote(NoteApp noteApp){
        noteApps.remove(noteApp);
        notifyDataSetChanged();
    }
}
