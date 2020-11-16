//package com.example.theirfather;
//
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.ListResult;
//import com.google.firebase.storage.StorageReference;
//
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//public class AudioLink {
//    // firebaseStorage and StorageReference
//    FirebaseStorage firebaseStorage;
//    StorageReference storageReference;
//
//    public final ArrayList<FirebaseItem> firebaseUrl = new ArrayList<FirebaseItem>();
//    public final AudioModel audioModel = new AudioModel();
//
//    public AudioLink () {
//        this.getAudio(new FirebaseCallback() {
//            @Override
//            public void onCallBack(ArrayList<FirebaseItem> list) {
//                //firebaseUrl.add()
//                Log.d("Invoke", "callBackSuccessful");
//                Log.d("Check", firebaseUrl.get(1).getFirebaseItem());
//                Log.d("Check", list.get(1).getFirebaseItem());
//                for (int i = 0; i < list.size(); i++) {
//                    //audioModel.setLink(list.get(i).getFirebaseItem());
//                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                            Request.Method.GET,
//                             list.get(i).getFirebaseItem(),
//                            null,
//                            new Response.Listener<JSONObject>() {
//                                @Override
//                                public void onResponse(JSONObject response) {
//                                    Log.d("JSONs", String.valueOf(response));
//                                    // firebaseCallback.onJsonRequest(items);
//                                }
//                            },
//                            new Response.ErrorListener() {
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//                                    Log.d("JSON", "response" + error);
//                                }
//                            }
//                    );
//                }
//                for (int i = 0; i < audioModel.link.size(); i++) {
//                    Log.d("GettingThere", audioModel.getLink(i));
//                }
//            }
//        });
//    }
//
////    public ArrayList<FirebaseItem> getFirebaseUrl () {
////        return this.firebaseUrl;
////    }
//    private void getAudio (final FirebaseCallback firebaseCallback) {
//        storageReference = firebaseStorage.getInstance().getReference().child("audio");
//        storageReference.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
//            @Override
//            public void onSuccess(ListResult listResult) {
//                String baseLink = "https://firebasestorage.googleapis.com/v0/b/theirfather-cdab8.appspot.com/o/audio%2F";
//                for (StorageReference item : listResult.getItems()) {
//                    //items.add(new FirebaseItem((item).toString()));
//                    //String[] newUrl = items.toString().split("audio/");
//                    //String newItem = item.toString().replace("gs://theirfather-cdab8.appspot.com/audio/", "");
//                    firebaseUrl.add(new FirebaseItem(baseLink + item.toString().replace("gs://theirfather-cdab8.appspot.com/audio/", "")));
////                     Log.d("firebaseItem", "this " + item.toString().replace("gs://theirfather-cdab8.appspot.com/audio/", "") );
//                }
//                firebaseCallback.onCallBack(firebaseUrl);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d("firebaseError", "this " + e );
//            }
//        });
//    }
//
//    public void checkLink () {
//        for (int i = 0; i < audioModel.link.size(); i++) {
//            Log.d("GettingThere", audioModel.getLink(i));
//        }
//    }
//
//    public void getRequest (String url) {
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                Request.Method.GET,
//                url,
//                null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d("JSONs", String.valueOf(response));
//                        // firebaseCallback.onJsonRequest(items);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("JSON", "response" + error);
//                    }
//                }
//        );
//    }
//}
