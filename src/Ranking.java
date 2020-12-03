import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ranking extends JFrame implements ActionListener {
    JButton bt_AnotherGame;

    Ranking() {
        setTitle("Eat Coin - Ranking");
        setUndecorated(true);
        setResizable(false);
        setLayout(null);
        setBounds(100, 100, GameFrame.FRAME_WIDTH, GameFrame.FRAME_HEIGHT);

        bt_AnotherGame = new JButton("한판더?");
        bt_AnotherGame.setFont(new Font(null, 0, 20));
        bt_AnotherGame.setBounds(250, 350, 100, 100);
        bt_AnotherGame.addActionListener(this) ;

        add(bt_AnotherGame);
        setVisible(true);
    }

    private void reset() {
        this.setVisible(false);
        this.dispose(); //해당프레임만종료
        new GameFrame(); //새게임
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        reset();
    }
}
