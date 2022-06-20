package com.example.hunter1;

import android.location.Location;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hunter1.objects.Player;
import com.example.hunter1.utils.MSP;
import com.example.hunter1.utils.PlayerComparator;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class TopTenManager extends AppCompatActivity
        implements OnMapReadyCallback {

    private ArrayList<Button> scoresBTNArray;
    private ArrayList<Player> playersArray = new ArrayList<>();
    private PlayerComparator playerComparator;
    public static final String SP_KEY_TOP_TEN = "TopTen";

    //ADDDDDD
    private static final String TAG = TopTenManager.class.getSimpleName();
    private GoogleMap map;
    private CameraPosition cameraPosition;

    // The entry point to the Places API.
    private PlacesClient placesClient;

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient fusedLocationProviderClient;

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng defaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location lastKnownLocation;

    // Keys for storing activity state.
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    // Used for selecting the current place.
    private static final int M_MAX_ENTRIES = 5;
    private String[] likelyPlaceNames;
    private String[] likelyPlaceAddresses;
    private List[] likelyPlaceAttributions;
    private LatLng[] likelyPlaceLatLngs;

    public TopTenManager(){
        playerComparator = new PlayerComparator();
        playersArray = getPlayersArrayFromMSP();
    }

    public void findViews( View view) {
        scoresBTNArray = new ArrayList<>();
        scoresBTNArray.add(view.findViewById(R.id.toTen_BTN_score0));
        scoresBTNArray.add(view.findViewById(R.id.toTen_BTN_score1));
        scoresBTNArray.add(view.findViewById(R.id.toTen_BTN_score2));
        scoresBTNArray.add(view.findViewById(R.id.toTen_BTN_score3));
        scoresBTNArray.add(view.findViewById(R.id.toTen_BTN_score4));
        scoresBTNArray.add(view.findViewById(R.id.toTen_BTN_score5));
        scoresBTNArray.add(view.findViewById(R.id.toTen_BTN_score6));
        scoresBTNArray.add(view.findViewById(R.id.toTen_BTN_score7));
        scoresBTNArray.add(view.findViewById(R.id.toTen_BTN_score8));
        scoresBTNArray.add(view.findViewById(R.id.toTen_BTN_score9));
    }

    public void initViews() {

//        playersArray = getPlayersArrayFromMSP();

        int score = 0;
        for (int i = 0; i < scoresBTNArray.size(); i++) {
            score = playersArray.get(i).getScore();
            scoresBTNArray.get(i).setText(String.valueOf(score));
        }


//        list_BTN_lion.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                lionClicked();
//            }
//        });
    }

    public ArrayList<Player> getPlayersArrayFromMSP() {

        String playersJson = MSP.getMe().getStringFromSP(SP_KEY_TOP_TEN,null);
        Log.d("initViews", "playersJson: " + playersJson);
        if(playersJson == null){
            initNullPlayersArray();
        }else {
            try {
                playersArray = new Gson().fromJson(playersJson, new TypeToken<ArrayList<Player>>(){}.getType());
            } catch (Exception ex) {
            }
        }
        return playersArray;
    }

    public void updatePlayersToMSP(ArrayList<Player> playersArray) {
        String playersJson = new Gson().toJson(playersArray);
        MSP.getMe().putStringToSP(SP_KEY_TOP_TEN,playersJson);
        String j = MSP.getMe().getStringFromSP(SP_KEY_TOP_TEN,"");
        playersArray = new Gson().fromJson(playersJson, new TypeToken<ArrayList<Player>>(){}.getType());
        Log.d("Anat", playersArray.get(0).getScore() +"");
    }

    private void initNullPlayersArray() {
        playersArray.add(new Player().setScore(0));
        playersArray.add(new Player().setScore(0));
        playersArray.add(new Player().setScore(0));
        playersArray.add(new Player().setScore(0));
        playersArray.add(new Player().setScore(0));
        playersArray.add(new Player().setScore(0));
        playersArray.add(new Player().setScore(0));
        playersArray.add(new Player().setScore(0));
        playersArray.add(new Player().setScore(0));
        playersArray.add(new Player().setScore(0));

        updatePlayersToMSP(playersArray);
    }



    public void updateTopTenScores(Player player){
        int lowestScore = playersArray.size() - 1;
        Log.d("updateTopTenScores", "lowest score: " + playersArray.get(lowestScore).getScore());

        if(playersArray.size() != 0){
            playersArray.remove(lowestScore);
            Log.d("playersArray.size()", ": " + playersArray.size());
        }
        playersArray.add(player);
        Log.d("playersArray.add(player)", "last player score: " + playersArray.get(playersArray.size() - 1).getScore());
        playersArray.sort(playerComparator);

        for (int i = 0; i < playersArray.size(); i++) {
            Log.d("playersArray.size()", ": " + playersArray.get(i).getScore());

        }

        updatePlayersToMSP(playersArray);
    }


    public boolean isNewScore(int score) {
        Log.d("isNewScore", "INNNNN");
        boolean b = playersArray != null;
        int arraySize = playersArray.size();
        Log.d("playersArray != null", ": " + b);
        Log.d("playersArray-SIZE:", ": " + arraySize);

        if (playersArray != null){
            for (int i = 0; i < playersArray.size(); i++) {

                Log.d("playersArray_", i+": " + playersArray.get(i).getScore());

                if(score > playersArray.get(i).getScore()){
                    Log.d("SCORE > OLD SCORE", "OLD SCORE: " + playersArray.get(i).getScore());

                    return true;
                }
            }
        }
        Log.d("SCORE < OLD SCORE", "SCORE: " + score);
        return false;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }
}
