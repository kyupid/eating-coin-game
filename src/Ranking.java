import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class Ranking extends JFrame implements ActionListener {

    private final int X_RANK = 150;
    private final int X_NAME = 250;
    private final int X_SCORE = 350;
    private final int X_SCORE_R = 450;
    private final int Y = 75;
    private final int WIDTH_LABEL = 100;
    private final int HEIGHT_LABEL = 50;


    private JButton bt_AnotherGame;
    private JLabel labelRank, labelName, labelScore, labelScoreR;

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
        labelRank = new JLabel("순위");
        labelRank.setBounds(X_RANK, Y, WIDTH_LABEL, HEIGHT_LABEL);
        add(labelRank);

        labelName = new JLabel("이름");
        labelName.setBounds(X_NAME, Y, WIDTH_LABEL, HEIGHT_LABEL);
        add(labelName);

        labelScore = new JLabel("점수");
        labelScore.setBounds(X_SCORE, Y, WIDTH_LABEL, HEIGHT_LABEL);
        add(labelScore);

        labelScoreR = new JLabel("Repaint()");
        labelScoreR.setBounds(X_SCORE_R, Y, WIDTH_LABEL, HEIGHT_LABEL);
        add(labelScoreR);

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


        ArrayList<Integer> lsScore = new ArrayList<Integer>();
        for (int i = 1; i <= ls.size() / 3; i++) {
            lsScore.add(Integer.valueOf((String) ls.get(3 * i - 2)));
        }
        Collections.sort(lsScore); //정렬

        ArrayList<String> lsScore2 = new ArrayList<String>();
        for (int i = 0; i < lsScore.size(); i++) { //int타입인 isScore를 다시 String으로
            lsScore2.add(String.valueOf(lsScore.get(i)));
        }

        int rank = 0;
        for (int i = lsScore2.size(); i >= 1; i--) { //
            int x = ls.indexOf(lsScore2.get(i - 1)); //ls의 인덱스값 1, 4,7..찾기
            rank++;
            callAllGen(x, rank);
        }

    }

    private void callAllGen(int x, int rank) { // x는 스코어가 높은 점수대로 넣어줘야함
        // y는 x가 몇개들어가는지에따라서 1~n까지 for문으로 넣어줌 -> callAllGen메소드출력갯수만큼 똑같겠다
        genName(x - 1, rank);
        genScore(x, rank);
        genScoreR(x + 1, rank);
    }

    private void genName(int index, int rank) {
        labelName = new JLabel((String) ls.get(index));
        labelName.setBounds(X_NAME, Y + 25 * rank, WIDTH_LABEL, HEIGHT_LABEL);
        add(labelName);
    }

    private void genScore(int index, int rank) {
        labelScore = new JLabel((String) ls.get(index));
        labelScore.setBounds(X_SCORE, Y + 25 * rank, WIDTH_LABEL, HEIGHT_LABEL);
        add(labelScore);
    }

    private void genScoreR(int index, int rank) {
        labelScoreR = new JLabel((String) ls.get(index));
        labelScoreR.setBounds(X_SCORE_R, Y + 25 * rank, WIDTH_LABEL, HEIGHT_LABEL);
        add(labelScoreR);
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