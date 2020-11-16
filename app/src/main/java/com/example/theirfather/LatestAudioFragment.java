package com.example.theirfather;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LatestAudioFragment # newInstance} factory method to
 * create an instance of this fragment.
 */
public class LatestAudioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private RecyclerView audioList;
//    final AudioLink audioLink = new AudioLink();

    private static final String URL_DATA = "https://firebasestorage.googleapis.com/v0/b/theirfather-cdab8.appspot.com/o/";
    String result = "";
    String url = "https://firebasestorage.googleapis.com/v0/b/theirfather-cdab8.appspot.com/o/";

    // firebaseStorage and StorageReference
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    final ArrayList<DataItem> items = new ArrayList<>();


    public LatestAudioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //jsonTask myTask = (jsonTask) new jsonTask().execute();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_latest_audio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // initialising data list/item
        // firebase isshh
        audioList = view.findViewById(R.id.audioListRecycler);
//        final ArrayList<FirebaseItem> FirebaseTemp = new ArrayList<FirebaseItem>();
//        ArrayList<DataItem> itemList = new ArrayList<DataItem>();
//        ArrayList<FirebaseItem> firebaseItems = new ArrayList<FirebaseItem>();
//        AudioListAdapter audioListAdapter = new AudioListAdapter(R.layout.audio_single, itemList);
//        audioList = view.findViewById(R.id.audioListRecycler);
//        audioList.setLayoutManager(new LinearLayoutManager(getContext()));
//        audioList.setAdapter(audioListAdapter);

        loadAudioData();
        // populating data list/items
//        for (int index = 1; index <= 20; index++) {
//            itemList.add(new DataItem("Audio " + index));
//        }
    }

    private void loadAudioData() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Audio . . . ");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("items");

                            for (int i=0; i<array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                                items.add(new DataItem(object.getString("name")));
                            }
//                            for (int i=0; i<items.size(); i++){
//                                Log.d("checkM", items.get(i).getAudioName());
//                            }
                            AudioListAdapter audioListAdapter = new AudioListAdapter(R.layout.audio_single, items);
                            audioList.setLayoutManager(new LinearLayoutManager(getContext()));
                            audioList.setAdapter(audioListAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//                        Log.d("firebaseError->", error.getMessage());
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

//    class jsonTask extends AsyncTask<Void, Void, String> {
//        public ArrayList<DataItem> jsonItem;
//        @Override
//        protected void onPreExecute(){
//            super.onPreExecute();
//        }
//        @Override
//        protected String doInBackground(Void... voids) {
//            try {
//                URL audioLink = new URL(url);
//                HttpURLConnection urlConnection = (HttpURLConnection) audioLink.openConnection();
//                InputStreamReader streamReader = new InputStreamReader(urlConnection.getInputStream());
//                BufferedReader reader = new BufferedReader(streamReader);
//                StringBuilder builder = new StringBuilder();
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    builder.append(line);
//                }
//                result = builder.toString();
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return result;
//        }
//        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            try {
//                JSONObject jsonObject = new JSONObject(s);
//                JSONArray array = jsonObject.getJSONArray("items");
//                for (int i = 0; i < array.length(); i++) {
//                    itemList.add(new DataItem(array.getString(i)));
//                }
//                for (int i = 0; i < itemList.size(); i++) {
//                    String temp = itemList.get(i).getAudioName();
//                    temp = temp.replace("\",\"bucket\":\"theirfather-cdab8.appspot.com\"}", "");
//                    temp = temp.replace("{\"name\":\"", "");
//                    itemList.get(i).setAudioName(temp);
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            Log.d("testD", itemList.get(1).getAudioName());
//            jsonItem = itemList;
//        }
//    }
}
