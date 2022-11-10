package com.example.truongtannha_buoi0506;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NoteAppAdapter extends RecyclerView.Adapter<NoteAppAdapter.NoteAppVH> implements Filterable {
    ArrayList<NoteApp>noteApps;
    Listener listener;
    ArrayList<NoteApp> noteAppsFillter;
    public NoteAppAdapter(ArrayList<NoteApp> noteApps, Listener listener) {
        this.noteApps = noteApps;
        this.listener = listener;
        this.noteAppsFillter = noteApps;
    }

    @NonNull
    @Override
    public NoteAppVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_row,parent,false);
        return new NoteAppVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAppVH holder, int position) {
        NoteApp noteApp =noteAppsFillter.get(position);
        holder.txDetail.setText(noteApp.getDetaile());
        holder.txTitle.setText(noteApp.getTitle());
        holder.txDate.setText(noteApp.getDate());
        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Ghi chú ");
                builder.setMessage("Bạn có muốn xóa ".concat(holder.txTitle.getText().toString().trim()).concat("?"));
                builder.setNegativeButton("No",(dialog, i) ->{
                    dialog.cancel();
                } );
                builder.setPositiveButton("Yes",(dialog, i) ->{
                    DbHelper dbHelper = new DbHelper(v.getContext());
                    dbHelper.deleteNote(noteApp.getId());
                    dialog.dismiss();
                    deleteNote(noteApp);
                } );
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemListener(position,noteApp);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteAppsFillter.size();
    }

    @Override
    public Filter getFilter() {

        return new NoteFilter();
    }
    private class NoteFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String charString = constraint.toString();
            if (charString.isEmpty()){
                noteAppsFillter=noteApps;
            }
            else {
                ArrayList<NoteApp> filterList = new ArrayList<>();
                for (NoteApp row : noteApps){
                    if (row.getTitle().toLowerCase().contains(charString.toLowerCase())
                            || row.getDetaile().toLowerCase().contains(charString.toLowerCase())){
                        filterList.add(row);
                    }
                }
                noteAppsFillter = filterList;
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values=noteAppsFillter;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            noteAppsFillter = (ArrayList<NoteApp>) results.values;
            notifyDataSetChanged();
        }
    }
    class NoteAppVH extends RecyclerView.ViewHolder{
        TextView txTitle, txDate, txDetail;
        ImageView imgDel;

        public NoteAppVH(@NonNull View itemView) {
            super(itemView);
            txTitle=itemView.findViewById(R.id.txTitle);
            txDate = itemView.findViewById(R.id.txDate);
            txDetail=itemView.findViewById(R.id.txDetail);
            imgDel = itemView.findViewById(R.id.imgDel);
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
