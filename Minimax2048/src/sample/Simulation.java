package sample;

import java.util.HashMap;
import java.util.Map;

public class Simulation {

    public enum Orientation {
        UP, DOWN, LEFT, RIGHT
    }

    Game game;
    private final int depth = 5;

    public Simulation(int[][] field) {
        game = new Game();

        for (int i = 0;i < 4;i++) {
            for (int j = 0;j < 4;j++) {
                game.field[i][j] = field[i][j];
            }
        }
    }

    public Orientation getWay () {

        int res_left = rec(0, game.swipeLeft(), true);
        int res_right = rec(0, game.swipeRight(), true);
        int res_up = rec(0, game.swipeUp(), true);
        int res_down = rec(0, game.swipeDown(), true);

        int max = Math.max(Math.max(res_up, res_down), Math.max(res_left, res_right));

        if (max == res_up) {
            return Orientation.UP;
        }
        if (max == res_down) {
            return Orientation.DOWN;
        }
        if (max == res_right) {
            return Orientation.RIGHT;
        }
        return Orientation.LEFT;
    }

    int rec (int counter, Game game, boolean need_min) {
        if (counter >= depth && need_min) {
            int min1 = Math.min(countRes(new Simulation(game.makeRandomBlock().field).game),
                            countRes(new Simulation(game.makeRandomBlock().field).game));
            int min2 = Math.min(countRes(new Simulation(game.makeRandomBlock().field).game),
                    countRes(new Simulation(game.makeRandomBlock().field).game));
            int min3 = Math.min(countRes(new Simulation(game.makeRandomBlock().field).game),
                    countRes(new Simulation(game.makeRandomBlock().field).game));
            int min4 = Math.min(countRes(new Simulation(game.makeRandomBlock().field).game),
                    countRes(new Simulation(game.makeRandomBlock().field).game));
            return Math.min(Math.min(min1, min2), Math.min(min3, min4));
        }

        if (need_min) {
            Simulation simulation1 = new Simulation(game.makeRandomBlock().field);
            int res1 = rec(counter + 1, simulation1.game, false);
            Simulation simulation2 = new Simulation(game.makeRandomBlock().field);
            int res2 = rec(counter + 1, simulation2.game, false);
            Simulation simulation3 = new Simulation(game.makeRandomBlock().field);
            int res3 = rec(counter + 1, simulation3.game, false);
            Simulation simulation4 = new Simulation(game.makeRandomBlock().field);
            int res4 = rec(counter + 1, simulation4.game, false);
            return Math.min(Math.min(res1, res2), Math.min(res3, res4));
        } else {
            Simulation simulation1 = new Simulation(game.swipeRight().field);
            int res1 = rec(counter + 1, simulation1.game, true);
            Simulation simulation2 = new Simulation(game.swipeDown().field);
            int res2 = rec(counter + 1, simulation2.game, true);
            Simulation simulation3 = new Simulation(game.swipeLeft().field);
            int res3 = rec(counter + 1, simulation3.game, true);
            Simulation simulation4 = new Simulation(game.swipeUp().field);
            int res4 = rec(counter + 1, simulation4.game, true);
            return Math.max(Math.max(res1, res2), Math.max(res3, res4));
        }
    }



    public static int countRes (Game game) {
        if (game.isGameOver()) {
            return -9999;
        }


        int score = 0;
        for (int i = 0;i < 4;i++) {
            for (int j = 0;j < 4;j++) {
                if (game.field[i][j] == 0) {
                    score += 60;
                    continue;
                } else {
                    score -= 50;
                }
//                if (game.field[i][j] == game.getCell(i, j + 1)) {
//                    score += 30;
//                }
//                if (game.field[i][j] == game.getCell(i, j - 1)) {
//                    score += 30;
//                }
//                if (game.field[i][j] == game.getCell(i + 1, j)) {
//                    score += 30;
//                }
//                if (game.field[i][j] == game.getCell(i - 1, j)) {
//                    score += 30;
//                }
            }
        }

        int max = 0;
        for (int i = 0;i < 4;i++) {
            for (int j = 0;j < 4;j++) {
                max = Math.max(max, game.field[i][j]);
            }
        }
        if (game.field[0][0] == max) {
            score += 50;
        }
        if (game.field[1][0] == max) {
            score += 40;
        }
        if (game.field[0][1] == max) {
            score += 40;
        }

        return score;
    }

}
