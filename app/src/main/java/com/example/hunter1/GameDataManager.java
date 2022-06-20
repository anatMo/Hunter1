package com.example.hunter1;

import android.app.Activity;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hunter1.objects.Square;

import java.util.ArrayList;

public class GameDataManager extends AppCompatActivity {

    public static ArrayList<Square> buildDataSquaresArray(Activity activity_main) {

        ArrayList<Square> squares = new ArrayList<>();

        squares.add(new Square().setIndex(0).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear0)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter0)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token0)));
        squares.add(new Square().setIndex(1).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear1)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter1)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token1)));
        squares.add(new Square().setIndex(2).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear2)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter2)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token2)));
        squares.add(new Square().setIndex(3).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear3)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter3)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token3)));
        squares.add(new Square().setIndex(4).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear4)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter4)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token4)));
        squares.add(new Square().setIndex(5).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear5)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter5)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token5)));
        squares.add(new Square().setIndex(6).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear6)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter6)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token6)));
        squares.add(new Square().setIndex(7).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear7)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter7)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token7)));
        squares.add(new Square().setIndex(8).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear8)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter8)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token8)));
        squares.add(new Square().setIndex(9).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear9)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter9)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token9)));
        squares.add(new Square().setIndex(10).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear10)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter10)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token10)));
        squares.add(new Square().setIndex(11).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear11)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter11)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token11)));
        squares.add(new Square().setIndex(12).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear12)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter12)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token12)));
        squares.add(new Square().setIndex(13).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear13)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter13)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token13)));
        squares.add(new Square().setIndex(14).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear14)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter14)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token14)));
        squares.add(new Square().setIndex(15).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear15)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter15)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token15)));
        squares.add(new Square().setIndex(16).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear16)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter16)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token16)));
        squares.add(new Square().setIndex(17).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear17)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter17)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token17)));
        squares.add(new Square().setIndex(18).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear18)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter18)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token18)));
        squares.add(new Square().setIndex(19).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear19)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter19)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token19)));
        squares.add(new Square().setIndex(20).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear20)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter20)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token20)));
        squares.add(new Square().setIndex(21).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear21)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter21)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token21)));
        squares.add(new Square().setIndex(22).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear22)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter22)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token22)));
        squares.add(new Square().setIndex(23).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear23)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter23)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token23)));
        squares.add(new Square().setIndex(24).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear24)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter24)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token24)));
        squares.add(new Square().setIndex(25).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear25)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter25)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token25)));
        squares.add(new Square().setIndex(26).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear26)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter26)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token26)));
        squares.add(new Square().setIndex(27).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear27)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter27)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token27)));
        squares.add(new Square().setIndex(28).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear28)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter28)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token28)));
        squares.add(new Square().setIndex(29).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear29)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter29)).setTokenIMG(activity_main.findViewById(R.id.main_IMG_token29)));

        //initiate all hunters and bears invisible at first
        setAllInvisible(activity_main, squares);
        
        return squares;
    }

    private static void setAllInvisible(Activity activity_main, ArrayList<Square> squares) {

        for (int i = 0; i < squares.size(); i++) {
            squares.get(i).getBearIMG().setVisibility(View.INVISIBLE);
            squares.get(i).getHunterIMG().setVisibility(View.INVISIBLE);
            squares.get(i).getTokenIMG().setVisibility(View.INVISIBLE);
        }

        activity_main.findViewById(R.id.mainSensor_IMG_left).setVisibility(View.INVISIBLE);
        activity_main.findViewById(R.id.mainSensor_IMG_up).setVisibility(View.INVISIBLE);
        activity_main.findViewById(R.id.mainSensor_IMG_right).setVisibility(View.INVISIBLE);
        activity_main.findViewById(R.id.mainSensor_IMG_down).setVisibility(View.INVISIBLE);
    }

}
