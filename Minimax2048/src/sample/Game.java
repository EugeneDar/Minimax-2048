package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {

    int[][] field;

    public Game() {
        field = new int[4][4];

        for (int i = 0;i < 3;i++) {
            int x = (int) ((Math.random() * 1000) % 4);
            int y = (int) ((Math.random() * 1000) % 4);
            field[x][y] = 2;
        }
    }

    public Game (int[][] field) {
        this.field = new int[4][4];
        for (int i = 0;i < 4;i++) {
            for (int j = 0;j < 4;j++) {
                this.field[i][j] = field[i][j];
            }
        }
    }

    public Game swipeLeft () {
        int[][] clone = cloneMatrix(field);
        for (int i = 0;i < 4;i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0;j < 4;j++) {
                if (clone[i][j] != 0) {
                    list.add(clone[i][j]);
                }
                clone[i][j] = 0;
            }
            boolean good = true;
            while (good) {
                good = false;
                for (int k = 0;k < list.size() - 1;k++) {
                    if (list.get(k).equals(list.get(k + 1))) {
                        list.set(k, list.get(k) * 2);
                        list.remove(k + 1);
                        good = true;
                        break;
                    }
                }
            }

            for (int j = 0;j < list.size();j++) {
                clone[i][j] = list.get(j);
            }
        }
        return new Game(clone);
    }

    public Game swipeRight () {
        int[][] clone = cloneMatrix(field);
        for (int i = 0;i < 4;i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 3;j >= 0;j--) {
                if (clone[i][j] != 0) {
                    list.add(clone[i][j]);
                }
                clone[i][j] = 0;
            }
            boolean good = true;
            while (good) {
                good = false;
                for (int k = 0;k < list.size() - 1;k++) {
                    if (list.get(k).equals(list.get(k + 1))) {
                        list.set(k, list.get(k) * 2);
                        list.remove(k + 1);
                        good = true;
                        break;
                    }
                }
            }
            for (int j = 0;j < list.size();j++) {
                clone[i][3 - j] = list.get(j);
            }
        }
        return new Game(clone);
    }

    public Game swipeUp () {
        int[][] clone = cloneMatrix(field);
        for (int j = 0;j < 4;j++) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0;i < 4;i++) {
                if (clone[i][j] != 0) {
                    list.add(clone[i][j]);
                }
                clone[i][j] = 0;
            }
            boolean good = true;
            while (good) {
                good = false;
                for (int k = 0;k < list.size() - 1;k++) {
                    if (list.get(k).equals(list.get(k + 1))) {
                        list.set(k, list.get(k) * 2);
                        list.remove(k + 1);
                        good = true;
                        break;
                    }
                }
            }
            for (int i = 0;i < list.size();i++) {
                clone[i][j] = list.get(i);
            }
        }
        return new Game(clone);
    }

    public Game swipeDown () {
        int[][] clone = cloneMatrix(field);
        for (int j = 0;j < 4;j++) {
            List<Integer> list = new ArrayList<>();
            for (int i = 3;i >= 0;i--) {
                if (clone[i][j] != 0) {
                    list.add(clone[i][j]);
                }
                clone[i][j] = 0;
            }
            boolean good = true;
            while (good) {
                good = false;
                for (int k = 0;k < list.size() - 1;k++) {
                    if (list.get(k).equals(list.get(k + 1))) {
                        list.set(k, list.get(k) * 2);
                        list.remove(k + 1);
                        good = true;
                        break;
                    }
                }
            }
            for (int i = 0;i < list.size();i++) {
                clone[3 - i][j] = list.get(i);
            }
        }
        return new Game(clone);
    }

    public boolean isGameOver () {
        boolean good = true;
        for (int i = 0;i < 4;i++) {
            for (int j = 0;j < 4;j++) {
                if (field[i][j] == 0) {
                    good = false;
                    i = 5;
                    j = 5;
                }
            }
        }
        if (!good) {
            return good;
        }
        for (int i = 0;i < 4;i++) {
            for (int j = 0;j < 3;j++) {
                if (field[i][j] == field[i][j + 1]) {
                    good = false;
                    i = 5;
                    j = 5;
                }
            }
        }
        if (!good) {
            return good;
        }
        for (int i = 0;i < 3;i++) {
            for (int j = 0;j < 4;j++) {
                if (field[i][j] == field[i + 1][j]) {
                    good = false;
                    i = 5;
                    j = 5;
                }
            }
        }
        return good;
    }

    public Game makeRandomBlock () {
        int[][] clone = cloneMatrix(field);
        int counter = 50;
        while (counter-- > 0) {
            int x = (int) ((Math.random() * 1000) % 4);
            int y = (int) ((Math.random() * 1000) % 4);
            if (clone[x][y] == 0) {
                if (Math.random() < 0.8) {
                    clone[x][y] = 2;
                } else {
                    clone[x][y] = 4;
                }
                break;
            }
        }
        return new Game(clone);
    }

    public int getCell (int i, int j) {
        if (i < 0 || j < 0 || i >= 4 || j >= 4) {
            return -1;
        }
        return field[i][j];
    }

    public static int[][] cloneMatrix (int[][] root) {
        int[][] clone = new int[4][4];
        for (int i = 0;i < 4;i++) {
            for (int j = 0;j < 4;j++) {
                clone[i][j] = root[i][j];
            }
        }
        return clone;
    }

}
