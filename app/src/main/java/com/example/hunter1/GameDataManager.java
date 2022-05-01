package com.example.hunter1;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GameDataManager extends AppCompatActivity {

    public static ArrayList<Square> buildData(Activity_Main activity_main) {

        ArrayList<Square> squares = new ArrayList<>();

        squares.add(new Square().setIndex(0).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear0)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter0)));
        squares.add(new Square().setIndex(1).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear1)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter1)));
        squares.add(new Square().setIndex(2).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear2)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter2)));
        squares.add(new Square().setIndex(3).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear3)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter3)));
        squares.add(new Square().setIndex(4).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear4)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter4)));
        squares.add(new Square().setIndex(5).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear5)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter5)));
        squares.add(new Square().setIndex(6).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear6)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter6)));
        squares.add(new Square().setIndex(7).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear7)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter7)));
        squares.add(new Square().setIndex(8).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear8)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter8)));
        squares.add(new Square().setIndex(9).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear9)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter9)));
        squares.add(new Square().setIndex(10).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear10)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter10)));
        squares.add(new Square().setIndex(11).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear11)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter11)));
        squares.add(new Square().setIndex(12).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear12)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter12)));
        squares.add(new Square().setIndex(13).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear13)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter13)));
        squares.add(new Square().setIndex(14).setBearIMG(activity_main.findViewById(R.id.main_IMG_bear14)).setHunterIMG(activity_main.findViewById(R.id.main_IMG_hunter14)));

        //initiate all hunters and bears invisible at first
        setAllInvisible(squares);
        
        return squares;
    }

    private static void setAllInvisible( ArrayList<Square> squares) {

        for (int i = 0; i < squares.size(); i++) {
            squares.get(i).getBearIMG().setVisibility(View.INVISIBLE);
            squares.get(i).getHunterIMG().setVisibility(View.INVISIBLE);
        }
    }
}
