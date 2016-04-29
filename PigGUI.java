import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class PigGUI extends JFrame implements ActionListener 
{
JPanel mainPanel;
JPanel optionPanel;
JPanel dicePanel;
private JPanel dicePanel_1;
JButton rollDice;
JButton hold;
JMenu options;
JMenuItem quit;
JMenuItem seeStats;
JMenuItem howtoPlay;
JMenuBar menuBar;
JLabel diceLabel;
JLabel diceLabel2;
Dice dice;
Graphics die1;
Graphics die2;
private boolean rolling;
private JTextArea textArea;
private JLabel lblPlayerSettings;
private JLabel lblPlayer;
private JLabel lblComputerPlayer;
private JLabel lblPlayerScore;
private JLabel label;
private JLabel lblNewLabel_1;
private JLabel lblPlayerName;
private JTextField txtEnterNumber;
private JLabel lblDiceSides;
private JTextField txtEnterYourName;
private JTextField textField;

public PigGUI()
{
    setTitle("Play Pig!");
    this.setPreferredSize(new Dimension(1200,600));

    mainPanel = new JPanel();
    mainPanel.setBounds(0, 0, 1200, 578);
    optionPanel = new JPanel();
    optionPanel.setBackground(Color.WHITE);
    optionPanel.setBounds(386, 471, 446, 37);
    dicePanel = new JPanel();
    options = new JMenu("Options");
    quit = new JMenuItem("Quit");
    seeStats = new JMenuItem("See Stats");
    howtoPlay = new JMenuItem("How to Play");
    menuBar = new JMenuBar();
    dice = new Dice();

    options.add(quit);
    options.add(seeStats);
    options.add(howtoPlay);

    menuBar.add(options);

    optionPanel.add(menuBar);
    optionPanel.setPreferredSize(new Dimension(600,100));
    
    dicePanel_1 = new JPanel(new GridLayout(2,1));
    dicePanel_1.setBounds(530, 251, 200, 132);

    mainPanel.setPreferredSize(new Dimension(600,600));
    mainPanel.setLayout(null);
    
    textArea = new JTextArea();
    textArea.setBackground(Color.WHITE);
    textArea.setBounds(184, 251, 319, 173);
    mainPanel.add(textArea);
    
    lblPlayerSettings = new JLabel("Console:");
    lblPlayerSettings.setBounds(184, 230, 141, 16);
    mainPanel.add(lblPlayerSettings);
    
    lblPlayer = new JLabel("PLAYER 1:");
    lblPlayer.setBounds(184, 184, 85, 16);
    mainPanel.add(lblPlayer);
    
    lblPlayerScore = new JLabel("PLAYER 1 SCORE");
    lblPlayerScore.setBounds(255, 184, 141, 16);
    mainPanel.add(lblPlayerScore);
    
    lblComputerPlayer = new JLabel("COMPUTER PLAYER:");
    lblComputerPlayer.setBounds(820, 184, 141, 16);
    mainPanel.add(lblComputerPlayer);
    
    label = new JLabel("COMPUTER SCORE");
    label.setBounds(957, 184, 122, 16);
    mainPanel.add(label);
    
    lblNewLabel_1 = new JLabel("Player Settings:");
    lblNewLabel_1.setBounds(771, 230, 122, 16);
    mainPanel.add(lblNewLabel_1);
    
    lblPlayerName = new JLabel("Player Name:");
    lblPlayerName.setBounds(781, 258, 85, 16);
    mainPanel.add(lblPlayerName);
    
    txtEnterYourName = new JTextField();
    txtEnterYourName.setText("Enter your name");
    txtEnterYourName.setColumns(10);
    txtEnterYourName.setBounds(903, 246, 130, 26);
    mainPanel.add(txtEnterYourName);
    
    lblDiceSides = new JLabel("Set dice sides:");
    lblDiceSides.setBounds(781, 294, 122, 16);
    mainPanel.add(lblDiceSides);
    
    txtEnterNumber = new JTextField();
    txtEnterNumber.setText("enter number 1-20");
    txtEnterNumber.setBounds(903, 289, 130, 26);
    mainPanel.add(txtEnterNumber);
    txtEnterNumber.setColumns(10);
    
    JLabel label_1 = new JLabel("Set difficulty:");
    label_1.setBounds(781, 338, 122, 16);
    mainPanel.add(label_1);
    
    textField = new JTextField();
    textField.setText("enter number 1-3");
    textField.setColumns(10);
    textField.setBounds(903, 333, 130, 26);
    mainPanel.add(textField);
    
    JLabel lblNewLabel_2 = new JLabel("*Easy = 1, Medium = 2, Hard = 3*");
    lblNewLabel_2.setBounds(781, 366, 252, 16);
    mainPanel.add(lblNewLabel_2);
    
    JButton btnNewButton = new JButton("Save Player Settings");
    btnNewButton.setBounds(840, 395, 149, 29);
    
    
    mainPanel.add(btnNewButton);
    mainPanel.add(optionPanel);
    mainPanel.add(dicePanel_1);
    diceLabel2 = new JLabel();
    dicePanel_1.add(diceLabel2);
    diceLabel = new JLabel();
    dicePanel_1.add(diceLabel);


    quit.addActionListener(this);
    seeStats.addActionListener(this);
    howtoPlay.addActionListener(this);
    getContentPane().setLayout(null);
    

    this.getContentPane().add(mainPanel);
    rollDice = new JButton("Roll Dice");
    rollDice.setBounds(528, 395, 100, 29);
    mainPanel.add(rollDice);
    hold = new JButton("Hold");
    hold.setBounds(630, 395, 100, 29);
    mainPanel.add(hold);
    
    //Background image
    Image img = new ImageIcon(this.getClass().getResource("/background.jpg")).getImage();
    JLabel lblNewLabel = new JLabel("");
    lblNewLabel.setIcon(new ImageIcon(img));
    lblNewLabel.setBounds(6, 6, 1188, 567);
    mainPanel.add(lblNewLabel);
    

    rollDice.addActionListener(this);

    this.pack();
    //Closes program on exit
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
}


public void actionPerformed(ActionEvent e) {
    if (e.getSource()== quit)
        System.exit(0);

    if (e.getSource() == seeStats)
    {
        JOptionPane.showMessageDialog(mainPanel,
                "Your stats are: "); 
    }
    //Message dialog under options how to play & set up
    if (e.getSource() == howtoPlay)
    {
        JOptionPane.showMessageDialog(mainPanel,
                "=============================HOW TO SET UP=============================\n"
                + "Begin by entering player settings.\n"
                + "Ener your first name and how many sides you'd like on each die ex: 1-20.\n"
                + "Then select a difficulty level by entering the corresponding value.\n"
                + "EASY = 1, MEDIUM = 2, and HARD = 3\n"
                + "Finish by clicking 'Save Player Settings'\n"
                + "=============================HOW TO PLAY=============================\n"
                + "The object of the game is to be the first player to reach 100 points.\n"
                + "You may choose to keep rolling the die or hold while it is your turn.\n"
                + "Your turn ends once you decide to hold or if you roll a 1, or snake eyes.\n"
                + "The game will end once you or the computer reaches 100 or more points.\n"); 
    }

    if (e.getSource() == rollDice)
    {


    	rolling = true;
    }
}
}
