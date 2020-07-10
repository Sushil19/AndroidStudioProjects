package com.example.playhub;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;


class VideoHolder extends RecyclerView.ViewHolder {

    TextView text_filename;
    ImageView image_thumbnail;
    CardView mcardview;

    VideoHolder(View view) {
        super(view);

        text_filename = view.findViewById(R.id.text_videofilename);
        image_thumbnail = view.findViewById(R.id.viewThumbnail);
        mcardview = view.findViewById(R.id.mycardview);

    }
}

public class MyAdapter extends RecyclerView.Adapter <VideoHolder> {

    private Context context ;
    ArrayList<File>  videoArrayList ;

    public MyAdapter(Context context, ArrayList<File> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View mview = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item , parent , false);
        return new VideoHolder(mview);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoHolder holder, int position) {

        holder.text_filename.setText(MainActivity.fileArrayList.get(position).getName());
        Bitmap bitmapthumbnail = ThumbnailUtils.createVideoThumbnail(videoArrayList.get(position).getPath(), MediaStore.Images.Thumbnails.MINI_KIND);
        holder.image_thumbnail.setImageBitmap(bitmapthumbnail);

        holder.mcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , videoPlayer.class);
                intent.putExtra("position",holder.getAdapterPosition());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (videoArrayList.size() > 1)
            return videoArrayList.size() ;
        else
                return 1;
    }
}

