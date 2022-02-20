package sample;

public class AppThread extends Thread {
    Controller controller;
    public AppThread(Controller controller) {
        this.controller = controller;
        start();
    }

    public void run () {
        while (!controller.game.isGameOver()) {
            try {
                Simulation.Orientation orientation = new Simulation(controller.game.field).getWay();
                if (orientation == Simulation.Orientation.DOWN) {
                    controller.swipeDown();
                } else if (orientation == Simulation.Orientation.LEFT) {
                    controller.swipeLeft();
                } else if (orientation == Simulation.Orientation.UP) {
                    controller.swipeUp();
                } else if (orientation == Simulation.Orientation.RIGHT) {
                    controller.swipeRight();
                }
                //int res = Simulation.countRes(controller.game);

//                int res_left = new Simulation(controller.game.field).rec(0, controller.game.swipeLeft(), false);
//                int res_right = new Simulation(controller.game.field).rec(0, controller.game.swipeRight(), false);
//                int res_up = new Simulation(controller.game.field).rec(0, controller.game.swipeUp(), false);
//                int res_down = new Simulation(controller.game.field).rec(0, controller.game.swipeDown(), false);
//
//                controller.textView.setText("Up = " + res_up + "\n" + "Left = " + res_left + "\n" + "Down = " + res_down + "\n" + "Right = " + res_right);
                Thread.sleep(0);

                controller.draw();
            } catch (Exception ignore) {}
            controller.draw();
        }
        System.out.println("GAME IS OVER!!!");
    }
}
