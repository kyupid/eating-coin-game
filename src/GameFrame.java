import java.awt.*;
import java.awt.event.*;

class GameFrame extends Frame {

    GamePanel panel;

    GameFrame() {
        setTitle("Eat Coin");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

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
