import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

class GameFrame extends JFrame implements KeyListener, Runnable {

    private boolean isKeyUp, isKeyDown, isKeyLeft, isKeyRight = false;

    private final int FRAME_WIDTH = 600;
    private final int FRAME_HEIGHT = 600;

    private int xPlayer, yPlayer, xCoin, yCoin;

    private int score = 0;

    private int count = 0;


    Image imagePlayer = new ImageIcon("0.png").getImage();
    Image imageCoin = new ImageIcon("1.png").getImage();

    int widthPlayer = imagePlayer.getWidth(this);
    int heightPlayer = imagePlayer.getHeight(this);
    int widthCoin = imageCoin.getWidth(this);
    int heightCoin = imageCoin.getHeight(this);

    Image bufferImage;
    Graphics gp;

    Thread t;

    Random rd = new Random();

    //벽에 부딪치는 것을 구현하기위해
    Insets insets = getInsets();
    int TOP = insets.top;
    int BOTTOM = FRAME_HEIGHT - insets.bottom;
    int LEFT = insets.left;
    int RIGHT = FRAME_WIDTH - insets.right;

    // Ball들을 담을 ArrayList
    ArrayList balls = new ArrayList();

    GameFrame() {

        init();
        addKeyListener(this);


        t = new Thread(this);
        t.start();

        setVisible(true);

    }

    public void run() {
        new BallGenerator().start();
        while (true) {
            try {
                Thread.sleep(20);
            } catch (Exception e) {
            }
            takeBallsOutFromArray();
            movePlayer(); // isKey가 true인지 계속 확인
            checkPlayerNWallBumped();
            checkPlayerNCoinBumped();
            repaint(); //계속 다시그려준다
            count++;
        }
    }

    private void init() {
        setTitle("Eat Coin");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);


        //xPlayer, yPlayer를 프레임의 가운데에 배치하는 값을 저장한다.
        xPlayer = (FRAME_WIDTH - widthPlayer) / 2;
        yPlayer = (FRAME_HEIGHT - heightPlayer) / 2;

        genCoin();

    }


    public void update(Graphics g) {
        g.drawImage(bufferImage, 0, 0, this);
    }

    public void paint(Graphics g) {
        //가상(back buffer)에 그려준다
        bufferImage = createImage(FRAME_WIDTH, FRAME_HEIGHT);
        gp = bufferImage.getGraphics();
        gp.drawImage(imagePlayer, xPlayer, yPlayer, this);
        gp.drawImage(imageCoin, xCoin, yCoin, this);

        gp.drawString(("repaint() 카운트: " + Integer.toString(count)), 50, 50);
        gp.drawString(("SCORE: " + Integer.toString(score)), 300, 50);


        gp.setColor(Color.RED);
        int size = balls.size();
        for (int i = 0; i < size; i++) {
            Ball b = (Ball) balls.get(i); // (Ball) 부분을 모르겠다
            gp.fillOval(b.x, b.y, b.SIZE, b.SIZE); // ballgenerator에서 값을 3초마다하나씩 넣어서 생성한다
        }


        update(g);
    }



    class BallGenerator extends Thread { //공 생성해주는 쓰레드를 만들어준다 무한반복할거니까
        public void run() {
            int x, y;
            while (true) {
                x = (int) (Math.random() * (RIGHT - Ball.SIZE));
                y = (int) (Math.random() * (BOTTOM - Ball.SIZE));

                balls.add(new Ball(x, y));

                try {
                    Thread.sleep(3 * 1000);
                } catch (Exception e) {

                }
            }
        }
    }

    private void takeBallsOutFromArray() {
        int size = balls.size();
        for (int i = 0; i < size; i++) { //생성된 ball들을 for문에 넣고 조건을 줘서 움직여준다
            Ball b = (Ball) balls.get(i); // ball들의 x와 y의 값을 가져오는거긴한데 이 코드는 이해를 못하겠음

            b.x += b.xStep;
            b.y += b.yStep;

            if (b.y <= TOP) {
                b.y = TOP;
                b.yStep = -b.yStep; //속도를 -로 바꿈
            }

            if (b.y >= BOTTOM - b.SIZE) {
                b.y = BOTTOM - b.SIZE;
                b.yStep = -b.yStep;
            }

            if (b.x <= LEFT) {
                b.x = LEFT;
                b.xStep = -b.xStep;
            }

            if (b.x >= RIGHT - b.SIZE) {
                b.x = RIGHT - b.SIZE;
                b.xStep = -b.xStep;
            }
        }
    }


    private void movePlayer() {
        if (isKeyUp) {
            yPlayer -= 5;
        }

        if (isKeyDown) {
            yPlayer += 5;
        }

        if (isKeyLeft) {
            xPlayer -= 5;
        }

        if (isKeyRight) {
            xPlayer += 5;
        }
    }


    private void genCoin() {
        //xCoin, yCoin 설정
        xCoin = rd.nextInt(FRAME_WIDTH);
        yCoin = rd.nextInt(FRAME_HEIGHT);

        //화면을 넘기지 않도록 코인을 생성한다.
        while (xCoin > FRAME_WIDTH - widthCoin) {
            xCoin = rd.nextInt(FRAME_WIDTH);
        }
        while (yCoin < 30 || yCoin > FRAME_HEIGHT - heightCoin) {
            yCoin = rd.nextInt(FRAME_HEIGHT);
        }
    }


    private void checkPlayerNCoinBumped() {

        if (xPlayer + widthPlayer > xCoin && yPlayer + heightPlayer > yCoin && xCoin + widthCoin > xPlayer && yCoin + heightCoin > yPlayer) {

            score++;

            genCoin();
        }
    }

    private void checkPlayerNWallBumped() {

        if (yPlayer <= TOP) {
            yPlayer = TOP;
        }

        if (yPlayer >= BOTTOM - heightPlayer) {
            yPlayer = BOTTOM - heightPlayer;
        }

        if (xPlayer <= LEFT) {
            xPlayer = LEFT;
        }

        if (xPlayer >= RIGHT - widthPlayer) {
            xPlayer = RIGHT - widthPlayer;
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) { //입럭된 키보드 값을 가져온다
            case KeyEvent.VK_UP:
                isKeyUp = true;
                break;

            case KeyEvent.VK_DOWN:
                isKeyDown = true;
                break;

            case KeyEvent.VK_LEFT:
                isKeyLeft = true;
                break;

            case KeyEvent.VK_RIGHT:
                isKeyRight = true;
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) { //입럭된 키보드 값을 가져온다
            case KeyEvent.VK_UP:
                isKeyUp = false;
                break;

            case KeyEvent.VK_DOWN:
                isKeyDown = false;
                break;

            case KeyEvent.VK_LEFT:
                isKeyLeft = false;
                break;

            case KeyEvent.VK_RIGHT:
                isKeyRight = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}

