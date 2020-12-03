import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class GameOverFrame extends JFrame implements KeyListener {

    GameOverFrame() {
        setTitle("Eat Coin - Game Over");
        setUndecorated(true);
        setResizable(false);
        setBounds(100, 100, GameFrame.FRAME_WIDTH, GameFrame.FRAME_HEIGHT);
        setVisible(true);
    }

    private void reset() {
        this.setVisible(false);
        this.dispose(); //해당프레임만종료
        new GameFrame(); //새게임
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
