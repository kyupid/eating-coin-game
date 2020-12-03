import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GameOverFrame extends JFrame implements ActionListener {

    JButton bt_OK;
    JTextField tf;

    GameOverFrame() {
        setTitle("Eat Coin - Game Over");
        setUndecorated(true);
        setResizable(false);
        setLayout(null);
        setBounds(100, 100, GameFrame.FRAME_WIDTH, GameFrame.FRAME_HEIGHT);

        tf = new JTextField("김인호", 20);
        tf.setBounds(GameFrame.FRAME_WIDTH/2 - 180/2, GameFrame.FRAME_HEIGHT/2 - 25/2, 180, 25);

        bt_OK = new JButton("확인");
        bt_OK.setBounds(390, GameFrame.FRAME_HEIGHT/2 - 25/2, 30, 25);
        bt_OK.addActionListener(this);

        add(tf);
        add(bt_OK);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Ranking();
    }

}
