import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GameFrame extends JFrame implements KeyListener {

    private boolean isKeyUp, isKeyDown, isKeyLeft, isKeyRight= false;

    private final int FRAME_WIDTH = 600;
    private final int FRAME_HEIGHT = 600;

    private int xPlayer, yPlayer;

    Toolkit tk = Toolkit.getDefaultToolkit();
    Image imagePlayer = tk.getImage("0.png");

    Image bufferImage;
    Graphics gp;

    GameFrame() {
        init();

        addKeyListener(this);

        setVisible(true);

    }

    private void init() {
        setTitle("Eat Coin");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public void paint (Graphics g) {

        g.drawImage(imagePlayer, 50, 50, this);
    }



    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()) { //입럭된 키보드 값을 가져온다
            case KeyEvent.VK_UP:
                isKeyUp = true;
                yPlayer -= 5;
                break;

            case KeyEvent.VK_DOWN:
                isKeyDown = true;
                yPlayer += 5;
                break;

            case KeyEvent.VK_LEFT:
                isKeyLeft = true;
                xPlayer -= 5;
                break;

            case KeyEvent.VK_RIGHT:
                isKeyRight = true;
                xPlayer += 5;
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}

