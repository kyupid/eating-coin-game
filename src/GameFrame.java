import java.awt.*;

class GameFrame extends Frame {

    GamePanel panel;

    GameFrame() {
        setTitle("Eat Coin");
        setBounds(400, 200, 500, 500);
        setResizable(false);

        panel = new GamePanel();
        add(panel, BorderLayout.CENTER);


        setVisible(true);
    }
 }
