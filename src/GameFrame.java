import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.*;

class GameFrame extends JFrame {

    private final int FRAME_WIDTH = 600;
    private final int FRAME_HEIGHT = 600;

    Toolkit tk = Toolkit.getDefaultToolkit();
    Image imagePlayer = tk.getImage("0.png");



    GameFrame() {
        setTitle("Eat Coin");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        setVisible(true);

    }

    public void paint (Graphics g) {
        g.drawImage(imagePlayer, 50, 50, this);
    }

}

