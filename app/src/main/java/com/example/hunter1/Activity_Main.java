package com.example.hunter1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textview.MaterialTextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Activity_Main extends AppCompatActivity {

    private ImageView[] main_IMG_hurts;

    private MaterialTextView main_LBL_score;
    private final int startingPositionHunter = 1;
    private final int startingPositionBear = 13;
    private int currentHunterIndex = startingPositionHunter;
    private int currentBearIndex = startingPositionBear;
    private final int numOfRows = 5;
    private final int numOfColumns= 3;
    private ImageButton main_BTN_left;
    private ImageButton main_BTN_up;
    private ImageButton main_BTN_right;
    private ImageButton main_BTN_down;

    private enum BEAR_DIRECTION {
        HIBERNATION,
        LEFT,
        UP,
        RIGHT,
        DOWN
    }

    private BEAR_DIRECTION bearDirection = BEAR_DIRECTION.HIBERNATION;

    private GameManager gameManager;


    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set all views ID
        findViews();

        main_BTN_left.setOnClickListener(view -> {
            bearDirection = BEAR_DIRECTION.LEFT;
        });

        main_BTN_up.setOnClickListener(view -> {
            bearDirection = BEAR_DIRECTION.UP;
        });

        main_BTN_right.setOnClickListener(view -> {
            bearDirection = BEAR_DIRECTION.RIGHT;
        });

        main_BTN_down.setOnClickListener(view -> {
            bearDirection = BEAR_DIRECTION.DOWN;
        });


        gameManager = new GameManager(this);

        updateUI();
        startTimer();
    }


    private void updateUI() {
        gameManager.getSquares().get(currentHunterIndex).getHunterIMG().setVisibility(View.VISIBLE);
        gameManager.getSquares().get(currentBearIndex).getBearIMG().setVisibility(View.VISIBLE);

        for (int i = 0; i < main_IMG_hurts.length; i++) {
            main_IMG_hurts[i].setVisibility(gameManager.getLives() > i ? View.VISIBLE: View.INVISIBLE);
        }

    }

    private void findViews() {

        main_BTN_left = findViewById(R.id.main_BTN_left);
        main_BTN_up = findViewById(R.id.main_BTN_up);
        main_BTN_right = findViewById(R.id.main_BTN_right);
        main_BTN_down = findViewById(R.id.main_BTN_down);
        main_LBL_score = findViewById(R.id.game_LBL_score);

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


        if(bearDirection == BEAR_DIRECTION.LEFT && !conditionMoveLeft){
            moveBearLeft();
            return;
        }else if(bearDirection == BEAR_DIRECTION.UP && conditionMoveUp){
            moveBearUp();
            return;
        }else if(bearDirection == BEAR_DIRECTION.RIGHT && !conditionMoveRight){
            moveBearRight();
            return;
        }else if(bearDirection == BEAR_DIRECTION.DOWN && conditionMoveDown){
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
        if(currentHunterIndex == currentBearIndex){
            gameManager.reduceLives();
            if (gameManager.isDead()) {
                Toast.makeText(this, "You Lose",
                        Toast.LENGTH_LONG).show();
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

    private void countAndUpdateScore() {
        ++counter;
        main_LBL_score.setText("" + counter);
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
    private int counter = 0;


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
                countAndUpdateScore();
                checkIfHunterAteBear();
                moveBear();
                moveHunter();
            }
        });
    }
}