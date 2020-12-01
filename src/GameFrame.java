import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class GameFrame extends JFrame implements KeyListener, Runnable {

    private boolean isKeyUp, isKeyDown, isKeyLeft, isKeyRight = false;
    private boolean playerMove = false;

    private final int FRAME_WIDTH = 600;
    private final int FRAME_HEIGHT = 600;

    private int xPlayer, yPlayer, xCoin, yCoin;

    private int score = 0;

    private int count = 0;

    Toolkit tk = Toolkit.getDefaultToolkit();
    Image imagePlayer = tk.getImage("0.png");
    Image imageCoin = tk.getImage("1.png");

    Image bufferImage;
    Graphics gp;

    Thread t;

    Random rd;


    GameFrame() {
        init();
        addKeyListener(this);

        t = new Thread(this);
        t.start();

        setVisible(true);
    }

    public void run() {
    while(true) {
        try{
            movePlayer(); // isKey가 true인지 계속 확인
            repaint(); //계속 다시그려준다
            count++;


            Thread.sleep(20);

        } catch (Exception e) {}
    }
    }

    private void init() {
        setTitle("Eat Coin");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        //xPlayer, yPlayer를 프레임의 가운데에 배치하는 값을 저장한다.
        xPlayer = (FRAME_WIDTH - imagePlayer.getWidth(this)) / 2;
        yPlayer = (FRAME_HEIGHT - imagePlayer.getHeight(this)) / 2;

        //xCoin, yCoin 설정
        rd = new Random();
        xCoin = rd.nextInt(FRAME_WIDTH);
        yCoin = rd.nextInt(FRAME_HEIGHT);
        while(yCoin < 30) { //창의 좌표까지 고려해서
            yCoin = rd.nextInt(FRAME_HEIGHT);
        }
    }


    public void update(Graphics g) {
        g.drawImage(bufferImage, 0, 0, this);
    }

    public void paint(Graphics g) {
        //가상(back buffer)에 그려준다
        bufferImage = createImage(FRAME_WIDTH, FRAME_HEIGHT);
        gp = bufferImage.getGraphics();
        gp.drawImage(imagePlayer, xPlayer, yPlayer, this);
        gp.drawString(("repaint() 카운트: " + Integer.toString(count)), 50, 50);

        gp.drawImage(imageCoin, xCoin, yCoin, this);

        update(g);
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

