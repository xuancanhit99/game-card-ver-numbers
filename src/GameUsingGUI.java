import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class GameUsingGUI extends JFrame implements ActionListener {
    Stack<Integer> player1 = new Stack<Integer>();
    Stack<Integer> player2 = new Stack<Integer>();
    Stack<Integer> randomArr = new Stack<Integer>();

    int moves;
    int ac = 0;
    int bc = 0;

    public static Random rd = new Random();
    public static Scanner sc = new Scanner(System.in);

    public static void printStack(Stack<Integer> player) {
        Iterator<Integer> iter = player.iterator();
        while (iter.hasNext()) {
            Integer val = iter.next();
            System.out.print(val + " ");
        }
        System.out.println();
    }
    public static void randomStack0to9(Stack<Integer> randomArr) {
        int random = rd.nextInt(10);
        while (randomArr.size() < 10) {
            if (randomArr.search(random) >= 0 && randomArr.search(random) < 10)
                random = rd.nextInt(10);
            else {
                randomArr.push(random);
            }
        }
    }

    public void newGame() {
        randomStack0to9(randomArr);
        player1.clear();
        player2.clear();
        for(int i = 0; i < 10/2; i++)
            player1.push(randomArr.pop());
        for(int i = 0; i < 10/2; i++)
            player2.push(randomArr.pop());
        moves = 0;
    }


    JButton jbCards1, jbCards2, reset;
    JLabel  jlWinner, jlPlayer1, jlPlayer2, jlLastPlayer, empty1, empty2, jlRm, empty4, empty5, jlLastCard;
    Label Info, copyRight;
    JTextField jtRemainingCards1, jtRemainingCards2, jtLP, jtW, jtLastCard1, jtLastCard2;
    JMenuBar menu;

    String TheWinner = "DRAW";
    String LastPlayer = "N/A";
    int startCard1 = 5;
    int startCard2 = 5;
    String lastCard1 = "X";
    String lastCard2 = "X";
    Container cont;
    JPanel header, body, footer;

    public GameUsingGUI(String nameProgram) {
        super(nameProgram);
        newGame();
        cont = this.getContentPane(); /*Lấy lớp ContentPane để đặt các đối tượng hiển thị*/

        // Menu
        menu = new JMenuBar();
        menu.add(optional());
        setJMenuBar(menu);
        // Header
        Info = new Label("Drunkard card game");

        header = new JPanel();
        header.add(Info);

        // Body
        //1
        jlPlayer1 = new JLabel("Player 1", SwingConstants.CENTER);
        //2
        empty1 = new JLabel("", SwingConstants.CENTER);
        //3
        jlPlayer2 = new JLabel("Player 2", SwingConstants.CENTER);
        //4
        jtRemainingCards1 = new JTextField();
        jtRemainingCards1.setEditable(false);
        //5
        jlRm = new JLabel("--Remaining Cards--", SwingConstants.CENTER);
        //6
        jtRemainingCards2 = new JTextField();
        jtRemainingCards2.setEditable(false);
        //7
        jbCards1 = new JButton("X ");
        //8
        empty2 = new JLabel("", SwingConstants.CENTER);
        //9
        jbCards2 = new JButton(" X");
        //10
        jtLastCard1 = new JTextField();
        jtLastCard1.setEditable(false);
        //11
        jlLastCard = new JLabel("--Last Cards--", SwingConstants.CENTER);
        //12
        jtLastCard2 = new JTextField();
        jtLastCard2.setEditable(false);
        //13
        empty4 = new JLabel("", SwingConstants.CENTER);
        //14
        reset = new JButton("Reset");
        reset.setBackground(Color.CYAN);
        //15
        empty5 = new JLabel("", SwingConstants.CENTER);

        jtLastCard1.setText(lastCard1);
        jtLastCard2.setText(lastCard2);
        jtRemainingCards1.setText(String.valueOf(startCard1));
        jtRemainingCards2.setText(String.valueOf(startCard2));

        body = new JPanel(new GridLayout(5, 3));
        body.add(jlPlayer1);
        body.add(empty1);
        body.add(jlPlayer2);
        body.add(jtRemainingCards1);
        body.add(jlRm);
        body.add(jtRemainingCards2);
        body.add(jbCards1);
        body.add(empty2);
        body.add(jbCards2);
        body.add(jtLastCard1);
        body.add(jlLastCard);
        body.add(jtLastCard2);
        body.add(empty4);
        body.add(reset);
        body.add(empty5);

        // Footer
        jlLastPlayer = new JLabel("Last Player: ", SwingConstants.CENTER);
        jtLP = new JTextField();
        jtLP.setEditable(false);
        jlWinner = new JLabel("The current player wins: ", SwingConstants.CENTER);
        jtW = new JTextField();
        jtW.setEditable(false);
        copyRight = new Label("@XuanCanh-ИКБО-07-19");
        copyRight.setForeground(Color.GRAY);

        jtLP.setText(LastPlayer);
        jtW.setText(TheWinner);

        footer = new JPanel(new GridLayout(3, 2));
        footer.add(jlLastPlayer);
        footer.add(jtLP);
        footer.add(jlWinner);
        footer.add(jtW);
        footer.add(copyRight);

        cont.add(header, "North");
        cont.add(body, "Center");
        cont.add(footer, "South");

        jbCards1.addActionListener(this);
        jbCards2.addActionListener(this);
        reset.addActionListener(this);

        //setSize(480, 320);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JMenu optional() {
        JMenu opt = new JMenu("Optional");
        JMenuItem exit = new JMenuItem(new ExitAction());
        exit.setIcon(new ImageIcon("src/images/icon_exit.png"));
        opt.add(exit);
        return opt;
    }

    public void checkGame() {
        if(ac == 0 && bc == 10 - 1) {
            player1.push(ac);
            player1.push(bc);
        }
        else if(bc == 0 && ac == 10 - 1) {
            player2.push(ac);
            player2.push(bc);
        }
        else if(ac > bc) {
            player1.push(ac);
            player1.push(bc);
        }
        else if(bc > ac) {
            player2.push(ac);
            player2.push(bc);
        }
        moves++;
    }

    public void butPlayer1() {
        startCard1--;
        ac = player1.remove(0);
        jtLastCard1.setText(String.valueOf(ac));
        //checkGame();
        System.out.print("Player 1: ");
        printStack(player1);
        jtRemainingCards1.setText(String.valueOf(player1.size()));
        currentWins();
        winGame();
    }

    public void butPlayer2() {
        startCard2--;
        bc = player2.remove(0);
        jtLastCard2.setText(String.valueOf(bc));
        checkGame();
        System.out.print("Player 2: ");
        printStack(player2);
        jtRemainingCards2.setText(String.valueOf(player2.size()));
        currentWins();
        winGame();
    }


    public void resetAll() {
        this.LastPlayer = "N/A";
        this.TheWinner = "DRAW";
        this.lastCard1 = "X";
        this.lastCard2 = "X";
        this.startCard1 = 5;
        this.startCard2 = 5;
        jtRemainingCards1.setText(String.valueOf(startCard1));
        jtRemainingCards2.setText(String.valueOf(startCard2));
        jtLastCard1.setText(lastCard1);
        jtLastCard2.setText(lastCard2);
        jtLP.setText(LastPlayer);
        jtW.setText(TheWinner);
        JOptionPane.showMessageDialog(null, "Reset Successful!!!", "Alert", JOptionPane.INFORMATION_MESSAGE);
    }

    public void currentWins() {
        if(player1.size() > player2.size())
            jtW.setText("Player 1");
        else if(player1.size() < player2.size())
            jtW.setText("Player 2");
        else
            jtW.setText("DRAW");
    }

    public void winGame() {
        if (player1.empty()) {
            JOptionPane.showMessageDialog(null, "Player 2 won ", "Finish the game", JOptionPane.INFORMATION_MESSAGE);
            newGame();
            resetAll();
        }

        else if(player2.empty()) {
            JOptionPane.showMessageDialog(null, "Player 1 won ", "Finish the game", JOptionPane.INFORMATION_MESSAGE);
            newGame();
            resetAll();
        }

        else if(moves > 200000) {
            JOptionPane.showMessageDialog(null, "botva", "Finish the game", JOptionPane.INFORMATION_MESSAGE);
            newGame();
            resetAll();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "Reset") {
            resetAll();
            newGame();
        }

        if(e.getActionCommand() == "X ") {
            butPlayer1();
            jtLP.setText("Player 1, next -> Player 2");
        }

        if(e.getActionCommand() == " X") {
            butPlayer2();
            jtLP.setText("Player 2, next -> Player 1");
        }
    }
}
