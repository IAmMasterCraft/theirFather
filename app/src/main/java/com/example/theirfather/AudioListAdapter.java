package com.example.theirfather;

import android.app.DownloadManager;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.util.Strings;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class AudioListAdapter extends RecyclerView.Adapter<AudioListAdapter.ViewHolder> {

    private int listItemLayout;
    public ArrayList<DataItem> itemList;
    private Context context;

    // constructor
    public AudioListAdapter (int layoutId, ArrayList<DataItem> itemList) {
        this.listItemLayout = layoutId;
        this.itemList = itemList;
        this.context = context;
    }

    // size of the data/list items
    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    // row layout file and click for each
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view, this.itemList);
        return myViewHolder;
    }


    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        String audio = itemList.get(listPosition).getAudioName();
        audio = audio.replace("audio/", "");
        audio = audio.replace(".mp3", "");
        audio = audio.replace("_", " ");
//        audio = audio.substring(0, 11) + "...";
        holder.audioName.setText(audio);
//        holder.playBtn.setText("PLAY");
//        holder.dlBtn.setText("DOWNLOAD");
    }

    // static inner class to initialise
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView audioName;
        Button playBtn;
        Button dlBtn;
        ArrayList<DataItem> audioFiles;
        Context context;
        MediaPlayer mediaPlayer = new MediaPlayer();
        String nowPlaying;

        public ViewHolder (View itemView, ArrayList<DataItem> myAudioList) {
            super(itemView);

            // find audioName, and buttons
            audioName = itemView.findViewById(R.id.audioName);
            playBtn = itemView.findViewById(R.id.playBtn);
            dlBtn = itemView.findViewById(R.id.dlBtn);

            audioFiles = myAudioList;

            this.context = context;

            // setOnClickListener for the 2 buttons
            playBtn.setOnClickListener(this);
            dlBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View button) {
            if (button.getId() == R.id.playBtn) {
                /*String baseLink = "https://firebasestorage.googleapis.com/v0/b/theirfather-cdab8.appspot.com/o/audio%2F";
                String finalLink = "?alt=media";
                String completeUrl = audioFiles.get(getLayoutPosition()).getAudioName();
                completeUrl = completeUrl.replace("audio/", "");
                completeUrl = completeUrl.replace(" ", "%20");
                completeUrl = baseLink + completeUrl + finalLink;
                try {
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setDataSource(completeUrl);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                //get complete url
                String completeUrl = buildUrl(audioFiles, getLayoutPosition());
                // get file name
                String fileName = fileName(audioFiles, getLayoutPosition());
                // get file extension
                String fileExtension = fileExtension();
                //playAudio
                playAudio(completeUrl, fileName, fileExtension);
                //Log.d("onclick", "ready to play " + completeUrl + " " + "Play Button !");
            }
            if (button.getId() == R.id.dlBtn) {
                // get context
                Context downloadContext = this.audioName.getContext();
                // get file name
                String fileName = fileName(audioFiles, getLayoutPosition());
                // get file extension
                String fileExtension = fileExtension();
                // get url
                String completeUrl = buildUrl(audioFiles, getLayoutPosition());
                // download audio
                downloadAudio(downloadContext, fileName, fileExtension, DIRECTORY_DOWNLOADS, completeUrl);
//                Log.d("onclick", "onClick " + completeUrl + " " + "Download Button !");
            }
        }

        public void downloadAudio (Context context, String fileName, String fileExtension, String destinationDirectory, String url) {
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            Uri uri = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);
            downloadManager.enqueue(request);
        }

        public String fileName (ArrayList<DataItem> allItems, int position) {
            String fileName = allItems.get(position).getAudioName();
            fileName = fileName.replace("audio/", "");
            fileName = fileName.replace("%20", " ");
            fileName = fileName.replace(".mp3", "");
            return fileName;
        }
        public String fileExtension () {
            return ".mp3";
        }

        public String buildUrl (ArrayList<DataItem> allItems, int position) {
            String baseLink = "https://firebasestorage.googleapis.com/v0/b/theirfather-cdab8.appspot.com/o/audio%2F";
            String finalLink = "?alt=media";
            String completeUrl = allItems.get(position).getAudioName();
            completeUrl = completeUrl.replace("audio/", "");
            completeUrl = completeUrl.replace(" ", "%20");
            completeUrl = baseLink + completeUrl + finalLink;
            return completeUrl;
        }

        public void playAudio (String completeUrl, String fileName, String fileExtension) {
            try {
                if (mediaPlayer.isPlaying()) {
                    Toast.makeText(this.audioName.getContext(), nowPlaying + " is STILL playing . . . ", Toast.LENGTH_LONG).show();
                } else {
                    mediaPlayer.reset();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setDataSource(completeUrl);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    nowPlaying = fileName + fileExtension;
                    Toast.makeText(this.audioName.getContext(), nowPlaying + " is NOW playing . . . ", Toast.LENGTH_LONG).show();
                }
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}