package com.example.listenit;

import android.content.Context;
import android.media.MediaMetadata;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {

    Context context;
    ArrayList<MusicFile> musicFiles;

    MusicAdapter(Context context, ArrayList<MusicFile> arr){
        this.context = context;
        this.musicFiles = arr;
    }


    @NonNull
    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.music_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapter.ViewHolder holder, int position) {

        holder.song_title.setText(musicFiles.get(position).getTitle());
        byte[] image = getImage(musicFiles.get(position).getPath());
        if(image != null){

            Glide.with(context).asBitmap()
                    .load(image)
                    .into(holder.song_image);
        }

    }

    @Override
    public int getItemCount() {
        return musicFiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView song_title;
        ImageView song_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            song_image = itemView.findViewById(R.id.song_image);
            song_title = itemView.findViewById(R.id.song_title);
        }
    }

    public byte[] getImage(String uri){

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] result = retriever.getEmbeddedPicture();
        retriever.release();
        return result;
    }
}


