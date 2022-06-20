package com.example.hunter1.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.hunter1.GameManager;
import com.example.hunter1.R;
import com.example.hunter1.TopTenManager;
import com.example.hunter1.objects.Player;
import com.example.hunter1.utils.LocationProvider;
import com.google.android.material.textview.MaterialTextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Activity_Main_Sensors extends AppCompatActivity {

    private ImageView[] main_IMG_hurts;

    private MaterialTextView main_LBL_score;
    private final int startingPositionHunter = 2;
    private final int startingPositionBear = 27;
    private int currentHunterIndex = startingPositionHunter;
    private int currentBearIndex = startingPositionBear;
    private int lastTokenIndex = -1;
    private int currentTokenIndex = -1;
    private final int numOfRows = 6;
    private final int numOfColumns= 5;
    float x = 0;
    float y = 0;

    private SensorManager sensorManager;
    private Sensor accSensor;

    private ImageView mainSensor_IMG_left;
    private ImageView mainSensor_IMG_right;
    private ImageView mainSensor_IMG_up;
    private ImageView mainSensor_IMG_down;

    private enum BEAR_DIRECTION {
        HIBERNATION,
        LEFT,
        UP,
        RIGHT,
        DOWN
    }

    private BEAR_DIRECTION bearDirection = BEAR_DIRECTION.HIBERNATION;

    private Player currentPlayer;
    private TopTenManager topTenManager;
    private GameManager gameManager;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sesors);

        //set all views ID
        findViews();

        Location loc = getLocationWithCheckNetworkAndGPS(this);
//        Log.d("LOCATION: ", loc+"");

        topTenManager = new TopTenManager();

        gameManager = new GameManager(this);

        updateUI();
        startTimer();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public static Location getLocationWithCheckNetworkAndGPS(Context mContext) {
        LocationManager lm = (LocationManager)
                mContext.getSystemService(Context.LOCATION_SERVICE);
        assert lm != null;
        boolean isGpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkLocationEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        Location networkLoacation = null, gpsLocation = null, finalLoc = null;
        if (isGpsEnabled)
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return null;
            }gpsLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (isNetworkLocationEnabled)
            networkLoacation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if (gpsLocation != null && networkLoacation != null) {

            //smaller the number more accurate result will
            if (gpsLocation.getAccuracy() > networkLoacation.getAccuracy())
                return finalLoc = networkLoacation;
            else
                return finalLoc = gpsLocation;

        } else {

            if (gpsLocation != null) {
                return finalLoc = gpsLocation;
            } else if (networkLoacation != null) {
                return finalLoc = networkLoacation;
            }
        }
        return finalLoc;
    }


    private void updateUI() {
        gameManager.getSquares().get(currentHunterIndex).getHunterIMG().setVisibility(View.VISIBLE);
        gameManager.getSquares().get(currentBearIndex).getBearIMG().setVisibility(View.VISIBLE);

        for (int i = 0; i < main_IMG_hurts.length; i++) {
            main_IMG_hurts[i].setVisibility(gameManager.getLives() > i ? View.VISIBLE: View.INVISIBLE);
        }

    }

    private void findViews() {
        main_LBL_score = findViewById(R.id.game_LBL_score);
        mainSensor_IMG_left = findViewById(R.id.mainSensor_IMG_left);
        mainSensor_IMG_right= findViewById(R.id.mainSensor_IMG_right);
        mainSensor_IMG_up = findViewById(R.id.mainSensor_IMG_up);
        mainSensor_IMG_down = findViewById(R.id.mainSensor_IMG_down);


        main_IMG_hurts = new ImageView[]{
                findViewById(R.id.main_IMG_heart1),
                findViewById(R.id.main_IMG_heart2),
                findViewById(R.id.main_IMG_heart3)
        };
    }

    private void moveBear() {

        boolean conditionMoveLeft = currentBearIndex % numOfColumns == 0;
        boolean conditionMoveUp = (currentBearIndex > numOfColumns - 1);
        boolean conditionMoveRight = (currentBearIndex + 1) % numOfColumns == 0;
        boolean conditionMoveDown = (currentBearIndex < gameManager.getSquares().size() - numOfColumns);


        if(x > 1.5 && !conditionMoveLeft){
            moveBearLeft();
            return;
        }else if(y >= 6 && conditionMoveUp){
            moveBearUp();
            return;
        }else if(x < -1.5 && !conditionMoveRight){
            moveBearRight();
            return;
        }else if(y < 6 && conditionMoveDown){
            moveBearDown();
            return;
        }
    }

    private void moveBearDown() {
        setCurrentBearInvisible();
        currentBearIndex += numOfColumns;
        updateUI();
    }

    private void moveBearUp() {
        setCurrentBearInvisible();
        currentBearIndex -= numOfColumns;
        updateUI();
    }

    private void moveBearRight() {
        setCurrentBearInvisible();
        currentBearIndex++;
        updateUI();
    }

    private void moveBearLeft() {
        setCurrentBearInvisible();
        currentBearIndex--;
        updateUI();
    }

    private void moveHunter() {
        int random = new Random().nextInt(4);

        //move down, random == 0
        boolean conditionMoveDown = (currentHunterIndex < gameManager.getSquares().size() - numOfColumns);
        if((random == 0) && conditionMoveDown){
            moveHunterDown();
            return;
        }else if(random == 0){
            returnHunterToStart();
            return;
        }

        //move up, random == 1
        boolean conditionMoveUp = (currentHunterIndex > numOfColumns - 1);
        if((random == 1) && conditionMoveUp){
            moveHunterUp();
            return;
        }else if (random == 1){
            moveHunterDown();
            return;
        }

        //move right, random == 2
        boolean conditionMoveRight = (currentHunterIndex + 1) % numOfColumns == 0;
        if((random == 2) && !conditionMoveRight){
            moveHunterRight();
            return;
        }else if (random == 2){
            moveHunterLeft();
            return;
        }

        //move left, random == 3
        boolean conditionMoveLeft = currentHunterIndex % numOfColumns == 0;
        if((random == 3) && !conditionMoveLeft){
            moveHunterLeft();
            return;
        }else if (random == 3){
            moveHunterRight();
            return;
        }

    }

    private void returnHunterToStart() {
        setCurrentHunterInvisible();
        currentHunterIndex = startingPositionHunter;
        updateUI();
    }

    private void moveHunterDown() {
        setCurrentHunterInvisible();
        currentHunterIndex += numOfColumns;
        updateUI();
    }

    private void moveHunterUp() {
        setCurrentHunterInvisible();
        currentHunterIndex -= numOfColumns;
        updateUI();
    }

    private void moveHunterLeft() {
        setCurrentHunterInvisible();
        currentHunterIndex--;
        updateUI();
    }

    private void moveHunterRight() {
        setCurrentHunterInvisible();
        currentHunterIndex++;
        updateUI();
    }

    private void setCurrentBearInvisible() {
        gameManager.getSquares().get(currentBearIndex).getBearIMG().setVisibility(View.INVISIBLE);
    }

    private void setCurrentHunterInvisible() {
        gameManager.getSquares().get(currentHunterIndex).getHunterIMG().setVisibility(View.INVISIBLE);
    }


    private void checkIfHunterAteBear() {

//        if(secCounter == 5){
//            updatePlayer();
//            if(topTenManager.isNewScore(score)){
//                Log.d("YES- NEW SCORE", "SCORE: " + score);
//                topTenManager.updateTopTenScores(currentPlayer);
//            }
//            LocationProvider.getMe().fetchLastLocation(this);
//            finishGame();
//        }


        if(currentHunterIndex == currentBearIndex){
            gameManager.reduceLives();
            if (gameManager.isDead()) {
                Log.d("DEADDDD", "INNNNN");
                Toast.makeText(this, "You Lose",
                        Toast.LENGTH_LONG).show();
                updatePlayer();
                if(topTenManager.isNewScore(score)){
                    Log.d("YES- NEW SCORE", "SCORE: " + score);
                    topTenManager.updateTopTenScores(currentPlayer);
                }
                LocationProvider.getMe().fetchLastLocation(this);
                finishGame();
                return;
            }else {
                Toast.makeText(this, "Be Careful!",
                        Toast.LENGTH_LONG).show();
                setCurrentHunterInvisible();
                setCurrentBearInvisible();
                currentHunterIndex = startingPositionHunter;
                currentBearIndex = startingPositionBear;
                bearDirection = BEAR_DIRECTION.HIBERNATION;
                updateUI();
            }
        }
    }

    private void updatePlayer() {
        currentPlayer = new Player()
                .setScore(score);
        Log.d("UPDATE PLAYER", "SCORE: " + score);
    }


    private void countAndUpdateScore() {
        ++score;
        main_LBL_score.setText("" + score);
    }


    private void finishGame() {
        finish();
    }

    //================================= Timer =============================================
    private Timer timer;
    private final int DELAY = 1000;
    private enum TIMER_STATUS {
        OFF,
        RUNNING,
        PAUSE
    }
    private TIMER_STATUS timerStatus = TIMER_STATUS.OFF;
    private int score = 0;
    private int secCounter = 0;


    @Override
    protected void onStart() {
        super.onStart();
        if (timerStatus == TIMER_STATUS.PAUSE) {
            startTimer();
        }
    }


    private void startTimer() {
        timerStatus = TIMER_STATUS.RUNNING;

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tick();
            }
        }, 0, DELAY);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (timerStatus == TIMER_STATUS.RUNNING) {
            stopTimer();
            timerStatus = TIMER_STATUS.PAUSE;
        }
    }

    private void stopTimer() {
        timer.cancel();
    }

    private void tick() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ++secCounter;
                countAndUpdateScore();
                checkIfHunterAteBear();
                checkIfBearTookToken();
                showToken();
                moveBear();
                moveHunter();
            }
        });
    }

    private void checkIfBearTookToken() {
        if(currentBearIndex == currentTokenIndex){
            gameManager.getSquares().get(currentTokenIndex).getTokenIMG().setVisibility(View.INVISIBLE);
            score += 10;
        }
    }

    private void showToken() {
        if(secCounter != 0 && secCounter % 10 == 0){
            changeTokenLocation();
        }
    }

    private void changeTokenLocation() {
        if(lastTokenIndex != -1){
            gameManager.getSquares().get(lastTokenIndex).getTokenIMG().setVisibility(View.INVISIBLE);
        }
        int random = new Random().nextInt(30);
        if(random == currentBearIndex || random == currentHunterIndex){
            if(random < 28){
                random++;
            }else {
                random--;
            }
        }
        currentTokenIndex = random;
        gameManager.getSquares().get(currentTokenIndex).getTokenIMG().setVisibility(View.VISIBLE);
        lastTokenIndex = random;
    }
//=================================Acc Sensor =============================================

    private SensorEventListener accSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            //sensor RIGHT/LEFT
            x = sensorEvent.values[0];
            //sensor UP/DOWN
            y = sensorEvent.values[2];

            updateDirection();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
            Log.d("sensor","onAccuracyChanged\n" + "x: " + x + ", y: " + y);
        }
    };



    private void updateDirection() {
        //Left
        if(x > 1.5){
            mainSensor_IMG_left.setVisibility(View.VISIBLE);
            mainSensor_IMG_up.setVisibility(View.INVISIBLE);
            mainSensor_IMG_right.setVisibility(View.INVISIBLE);
            mainSensor_IMG_down.setVisibility(View.INVISIBLE);
            return;
        }
        //Up
        if(y >= 6){
            mainSensor_IMG_up.setVisibility(View.VISIBLE);
            mainSensor_IMG_left.setVisibility(View.INVISIBLE);
            mainSensor_IMG_right.setVisibility(View.INVISIBLE);
            mainSensor_IMG_down.setVisibility(View.INVISIBLE);
            return;
        }
        //Right
        if(x < -1.5){
            mainSensor_IMG_right.setVisibility(View.VISIBLE);
            mainSensor_IMG_left.setVisibility(View.INVISIBLE);
            mainSensor_IMG_up.setVisibility(View.INVISIBLE);
            mainSensor_IMG_down.setVisibility(View.INVISIBLE);
            return;
        }
        //Down
        if(y < 6){
            mainSensor_IMG_down.setVisibility(View.VISIBLE);
            mainSensor_IMG_left.setVisibility(View.INVISIBLE);
            mainSensor_IMG_up.setVisibility(View.INVISIBLE);
            mainSensor_IMG_right.setVisibility(View.INVISIBLE);
            return;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(accSensorEventListener, accSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(accSensorEventListener);
    }

}
