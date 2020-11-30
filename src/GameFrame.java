import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class GameFrame extends Frame {

    GamePanel panel;

    GameFrame() {
        setTitle("Eat Coin");
        setBounds(400, 200, 500, 500);
        setResizable(false);

        panel = new GamePanel();
        add(panel, BorderLayout.CENTER);

        setVisible(true);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

    }
}
