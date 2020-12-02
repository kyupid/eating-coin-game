import java.util.Random;

class Ball {
    int x = 0;
    int y = 0;
    static final int SIZE = 15;
    int[] speed = {1, 2, 3, 4, 5};
    int xStep, yStep;

    Random rd; // 무작위 speed를 위한


    Ball(int x, int y) {
        this.x = x;
        this.y = y;

        rd = new Random();

        int s = rd.nextInt(5);
        xStep = speed[s];
        yStep = speed[s];
    }
}