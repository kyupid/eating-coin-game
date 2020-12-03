import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Ranking extends JFrame implements ActionListener {

    private final int X_RANK = 150;
    private final int X_NAME = 250;
    private final int X_SCORE = 350;
    private final int X_SCORE_R = 450;
    private final int Y = 75;
    private final int WIDTH_LABEL = 100;
    private final int HEIGHT_LABEL = 50;


    private JButton bt_AnotherGame;
    private JLabel rank, name, score, scoreR;

    private ArrayList ls = new ArrayList();

    Ranking() {
        setTitle("Eat Coin - Ranking");
        setUndecorated(true);
        setResizable(false);
        setLayout(null);
        setBounds(100, 100, GameFrame.FRAME_WIDTH, GameFrame.FRAME_HEIGHT);

        bt_AnotherGame = new JButton("한판더?");
        bt_AnotherGame.setFont(new Font(null, 0, 20));
        bt_AnotherGame.setBounds(250, 350, 100, 100);
        bt_AnotherGame.addActionListener(this);

        printRanking();
        add(bt_AnotherGame);
        setVisible(true);
    }


    private void printRanking() {
        printRankingTitle();
        printActualRanking();
    }

    private void printRankingTitle() {
        rank = new JLabel("순위");
        rank.setBounds(X_RANK, Y, WIDTH_LABEL, HEIGHT_LABEL);
        add(rank);

        name = new JLabel("이름");
        name.setBounds(X_NAME, Y, WIDTH_LABEL, HEIGHT_LABEL);
        add(name);

        score = new JLabel("점수");
        score.setBounds(X_SCORE, Y, WIDTH_LABEL, HEIGHT_LABEL);
        add(score);

        scoreR = new JLabel("Repaint()");
        scoreR.setBounds(X_SCORE_R, Y, WIDTH_LABEL, HEIGHT_LABEL);
        add(scoreR);

    }

    private void printActualRanking() {
        try (
                FileReader fr = new FileReader("test.txt");
                BufferedReader br = new BufferedReader(fr);
        ) {
            String readLine = null;
            while ((readLine = br.readLine()) != null) { //　왜이렇게하지?
                ls.add(readLine);
            }
        } catch (IOException e) {
        }

        genName(0, 1);
        genName(3, 2);
        genName(6, 3);
        genScore(1, 1);
        genScore(4, 2);
        genScore(7, 3);
        genScoreR(2, 1);
        genScoreR(5, 2);
        genScoreR(8, 3);
    }

    private void genName(int index, int y) {
        name = new JLabel((String) ls.get(index));
        name.setBounds(X_NAME, Y + 25 * y, WIDTH_LABEL, HEIGHT_LABEL);
        add(name);
    }

    private void genScore(int index, int y) {
        score = new JLabel((String) ls.get(index));
        score.setBounds(X_SCORE, Y + 25 * y, WIDTH_LABEL, HEIGHT_LABEL);
        add(score);
    }

    private void genScoreR(int index, int y) {
        scoreR = new JLabel((String) ls.get(index));
        scoreR.setBounds(X_SCORE_R, Y + 25 * y, WIDTH_LABEL, HEIGHT_LABEL);
        add(scoreR);
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