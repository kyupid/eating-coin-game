import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

        printRanking();
        add(bt_AnotherGame);
        setVisible(true);
    }

    private void reset() {
        this.setVisible(false);
        this.dispose(); //해당프레임만종료
        new GameFrame(); //새게임
    }

    private void printRanking() {
        rank = new JLabel("순위");
        rank.setBounds(150, 75, 50, 50);
        add(rank);

        name = new JLabel("이름");
        name.setBounds(250, 75, 50, 50);
        add(name);

        score = new JLabel("점수");
        score.setBounds(350, 75, 50, 50);
        add(score);

        scoreR = new JLabel("Repaint()");
        scoreR.setBounds(450, 75, 100, 50);
        add(scoreR);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        reset();
    }
}
