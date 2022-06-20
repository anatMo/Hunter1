package com.example.hunter1.utils;

import com.example.hunter1.objects.Player;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {
    @Override
    public int compare(Player playerA, Player playerB) {
        return playerA.getScore() < playerB.getScore() ? 1 : playerA.getScore() == playerB.getScore() ? 0 : -1;
    }
}
