import java.awt.*;                                                              //MINESWEEPER PROJECT-MineFrame.java    BY: BEN SAILOR
import java.awt.event.*;
import javax.swing.*;

public class MineFrame extends JFrame implements ActionListener {               //MineSweeper Game Frame that extends JFrame 
    private JPanel window;                                                      
    private JPanel panel;                                   
    private JPanel sidePanel;                                                   //initializing all necissary class variables
    private JTextField displayText;                                             
    private MineButton[][] buttons;                                             //array of MineButton objects that extends JButton
    private MineButton button;                                                  //temperary variable for testing
    private JToggleButton flagButton;                                           //toggle for flag
    private JButton check;                                                      //chceck button WINS: all mines are flagged, all nonmines are not flagged
    private JButton reset;                                                      //exit and go back to control frame
    private int length = 10;                                                    //length, default to 10
    private int won;                                               

    MineFrame() {                                                               //constructor (with no input), uses initialize method only
        initialize();
    }

    MineFrame(Integer s) {                                                      //constructor (with string input)
        length = Integer.valueOf(s);                                            //changes text to int and sets length to that (test in control panel is essential
        System.out.println(length);                                             //to not throw errors) 
        initialize();                                                           //calls initialize method after changing the length
    }

    private void initialize() {                                                 //initializes (from constructor ) and sets all values
        window = new JPanel();                                                  //stacked panels for easier management
        panel = new JPanel();                                                   //sub panel for MineButton array
        sidePanel = new JPanel();                                               //sub panel for side options (flag, check, reset)
        displayText = new JTextField(30);                                       //text field for display at the top
        flagButton = new JToggleButton("         FLAG          ");
        check = new JButton("SUBMIT/CHECK");                                    //the three side buttons
        reset = new JButton("        RESET        ");
        won = 1;                                                                //won variable for later logic

        this.initializeList();                                                  //seperate method to initialize MineButton array to decrease clutter

        window.setLayout(new BorderLayout());                                   //setting the main window layout
        panel.setLayout(new GridLayout(length, length));                        //setting the button array layout to grid of size lengthxlength (default: 10x10)
        sidePanel.setPreferredSize(new Dimension(150,100));                

        check.addActionListener(this);                                          //adding actionListener for two side buttons
        reset.addActionListener(this);

        displayText.setText("MINESWEEPER");                                     //setting DisplayText on top

        this.setValues();                                                       //calling setvalues method to add button array to grid panel, done to reduce clutter

        sidePanel.add(flagButton);                                              
        sidePanel.add(check);                                                   //adding the side buttons to the side panel
        sidePanel.add(reset);

        window.add("North", displayText);                                       //setting up and adding the 
        window.add("Center", panel);                                            //subpanels to the the main panel
        window.add("East", sidePanel);

        this.setContentPane(window);                                            //setting up main panel 
        this.pack();                                                            //packing
        this.setSize(600,600);                                                  //default settings
        this.setLocationRelativeTo(null);                                       
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);                 //dispose on close so that the Control Panel will remain running, and with it the program
    }

    private  void initializeList() {                                            //initializeList method to declare and initialize the MineButtons
        buttons = new MineButton[length][length];                               //declaring array of minebuttons
        for (int i = 0; i < length; i++) {                                      //nested loops to cycle through and declare each
            for (int j = 0; j < length; j++) {                                  //inner loop
                buttons[i][j] = new MineButton();                               //initialization of each button
            }
        }
    }

    private void setValues() {                                                  //setValues method to add each button to the panel, add its action listener 
        for (int i = 0; i < length; i++) {                                      //and set number of near mines
            for (int j = 0; j < length; j++) {                                  //loop to initialize buttons and add action listener
                panel.add(buttons[i][j]);
                buttons[i][j].addActionListener(this);
            }
        }

        for (int i = 0; i < length; i++) {                                      //probably overcomplicated, but basically cycles through each tile and determines its 
            for (int j = 0; j < length; j++) {                                  //Number of Touching Mines
                if (buttons[i][j].isMine() == 0) {                         
                    if (i == 0) {
                        if (j == 0) {
                            if (buttons[i + 1][j].isMine() == 1) { buttons[i][j].incNum(); }
                            if (buttons[i + 1][j + 1].isMine() == 1) { buttons[i][j].incNum(); }
                            if (buttons[i][j + 1].isMine() == 1) { buttons[i][j].incNum(); }
                        }   else if (j == length - 1) {
                            if (buttons[i + 1][j].isMine() == 1) { buttons[i][j].incNum(); }
                            if (buttons[i + 1][j - 1].isMine() == 1) { buttons[i][j].incNum(); }
                            if (buttons[i][j - 1].isMine() == 1) { buttons[i][j].incNum(); }
                        }   else {
                            if (buttons[i][j + 1].isMine() == 1) { buttons[i][j].incNum(); }
                            if (buttons[i + 1][j + 1].isMine() == 1) { buttons[i][j].incNum(); }
                            if (buttons[i + 1][j].isMine() == 1) { buttons[i][j].incNum(); }
                            if (buttons[i + 1][j - 1].isMine() == 1) { buttons[i][j].incNum(); }
                            if (buttons[i][j - 1].isMine() == 1) { buttons[i][j].incNum(); }
                        }
                    }   else if (i == length - 1) {
                        if (j == 0) {
                            if (buttons[i - 1][j].isMine() == 1) { buttons[i][j].incNum(); }
                            if (buttons[i - 1][j + 1].isMine() == 1) { buttons[i][j].incNum(); }
                            if (buttons[i][j + 1].isMine() == 1) { buttons[i][j].incNum(); }
                        }   else if (j == length - 1) {
                            if (buttons[i - 1][j].isMine() == 1) { buttons[i][j].incNum(); }
                            if (buttons[i - 1][j - 1].isMine() == 1) { buttons[i][j].incNum(); }
                            if (buttons[i][j - 1].isMine() == 1) { buttons[i][j].incNum(); }
                        }   else {
                            if (buttons[i][j + 1].isMine() == 1) { buttons[i][j].incNum(); }
                            if (buttons[i - 1][j + 1].isMine() == 1) { buttons[i][j].incNum(); }
                            if (buttons[i - 1][j].isMine() == 1) { buttons[i][j].incNum(); }
                            if (buttons[i - 1][j - 1].isMine() == 1) { buttons[i][j].incNum(); }
                            if (buttons[i][j - 1].isMine() == 1) { buttons[i][j].incNum(); }
                        }
                    }   else if (j == 0) {
                        if (buttons[i + 1][j].isMine() == 1) { buttons[i][j].incNum(); }
                        if (buttons[i + 1][j + 1].isMine() == 1) { buttons[i][j].incNum(); }
                        if (buttons[i][j + 1].isMine() == 1) { buttons[i][j].incNum(); }
                        if (buttons[i - 1][j + 1].isMine() == 1) { buttons[i][j].incNum(); }
                        if (buttons[i - 1][j].isMine() == 1) { buttons[i][j].incNum(); }
                    }   else if (j == length - 1) {
                        if (buttons[i + 1][j].isMine() == 1) { buttons[i][j].incNum(); }
                        if (buttons[i + 1][j - 1].isMine() == 1) { buttons[i][j].incNum(); }
                        if (buttons[i][j - 1].isMine() == 1) { buttons[i][j].incNum(); }
                        if (buttons[i - 1][j - 1].isMine() == 1) { buttons[i][j].incNum(); }
                        if (buttons[i - 1][j].isMine() == 1) { buttons[i][j].incNum(); }
                    }   else {
                        if (buttons[i][j + 1].isMine() == 1) { buttons[i][j].incNum(); }
                        if (buttons[i + 1][j + 1].isMine() == 1) { buttons[i][j].incNum(); }
                        if (buttons[i + 1][j].isMine() == 1) { buttons[i][j].incNum(); }
                        if (buttons[i + 1][j - 1].isMine() == 1) { buttons[i][j].incNum(); }
                        if (buttons[i][j - 1].isMine() == 1) { buttons[i][j].incNum(); }
                        if (buttons[i - 1][j - 1].isMine() == 1) { buttons[i][j].incNum(); }
                        if (buttons[i - 1][j].isMine() == 1) { buttons[i][j].incNum(); }
                        if (buttons[i - 1][j + 1].isMine() == 1) { buttons[i][j].incNum(); }
                    }
                }
            }
        }
    }



    @Override                                                                       //override for action listener
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reset) {                                               //case for if reset button is pressed
            this.dispose();                                                         //disposes of JFrame, thereby returning us to control frame
        }   else if (e.getSource() == check) {                                      //case for if the check button is pressed
            for (int i = 0; i < length; i++) {                                      //loops through each square
                for (int j = 0; j < length; j++) {
                    if (((buttons[i][j].isMine() == 0) && (buttons[i][j].isFlagged() == 1)) || ((buttons[i][j].isMine() == 1) && (buttons[i][j].isFlagged() == 0))) {
                        lost(i, j);                                                 //and tests for any incorrect tiles (ones that are mines and are not flagged)
                        won = 0;                                                    //and uses lost method and sets won to 0 if ever happens
                    }   else { 
                        buttons[i][j].setBackground(Color.GREEN);                   //each square that is correct it sets green (flagged bombs)
                    }   if (won == 1) {                                             //if no square was incorrect, won value should still be 1 and it returns victory message
                        displayText.setText("  WINNER       WINNER        WINNER        WINNER        WINNER        WINNER        WINNER        WINNER");   //victory message
                    }
                    won = 1;                                                        
                }
            }
        }   else if (flagButton.isSelected()) {                                     //checks if flag button is selected
            button = (MineButton) e.getSource();
            if (!(button.isPressed())) { button.toggleFlagged(); }                  //and toggles flagged status from method in MineButton                                     
        }   else {
            button = (MineButton) e.getSource();                                    //and finally, if not flagButton is not selected
            if (button.isFlagged() == 0) {                                          //and if the button isnt already flagged (must deflag first)
                if (button.isMine() == 1) {                                         // if the button is a mine 
                    lost(button);                                                   //user loses and returns lost method

                }   else if (button.isMine() == 0){                                 //if not a mine then it cycles through to find the index of that button
                    int index1 = 0;
                    int index2 = 0;

                    for (int i = 0; i < length; i++) {                              //uses two loops to find the same button (works because pass by reference)
                        for (int j = 0; j < length; j++) {      
                            if (button == buttons[i][j]) {
                                index1 = i;                                         //sets index
                                index2 = j;
                            }
                        }
                    }
                    this.clearTile(index1, index2);                                 //starts recursive clearTile method at the index of the button
                }
            }   
        }
    }

    public void clearTile(int ind1, int ind2) {                                     //recursive ClearTile method, also probably overcomplicated
        if (!(buttons[ind1][ind2].isPressed())) {                                   //checks if the button is already pressed (this fixed one of my biggest problems with
            buttons[ind1][ind2].setBackground(Color.GRAY);                          //endless recursion         on this line, setting the background color for pressed squares
            buttons[ind1][ind2].setText(String.valueOf(buttons[ind1][ind2].getNum()));
            buttons[ind1][ind2].togglePressed();                                    //sets the text value of the button to its number and sets that it has been pressed
            if (buttons[ind1][ind2].getNum() == 0) {                                // if the number on it is 0 then the recursion continues 
                if(ind1 == 0) {                                                     //had to make a lot of special cases to not go out of bounds on the corners and sides
                    if (ind2 == 0) {
                        clearTile(ind1 + 1, ind2);
                        clearTile(ind1, ind2 + 1);
                    }   else if (ind2 == length - 1) {
                        clearTile(ind1 + 1, ind2);
                        clearTile(ind1, ind2 - 1);
                    }   else {
                        clearTile(ind1, ind2 + 1);
                        clearTile(ind1 + 1, ind2);
                        clearTile(ind1, ind2 - 1);
                    }
                }   else if (ind1 == length - 1) {
                    if (ind2 == 0) {
                        clearTile(ind1 - 1, ind2);
                        clearTile(ind1, ind2 + 1);
                    }   else if (ind2 == length - 1) {
                        clearTile(ind1 - 1, ind2);
                        clearTile(ind1, ind2 - 1);
                    }   else {
                        clearTile(ind1, ind2 + 1);
                        clearTile(ind1 - 1, ind2);
                        clearTile(ind1, ind2 - 1);
                    }
                }   else if (ind2 == 0) {
                    clearTile(ind1 + 1, ind2);
                    clearTile(ind1, ind2 + 1);
                    clearTile(ind1 - 1, ind2);
                }   else if (ind2 == length - 1) {
                    clearTile(ind1 + 1, ind2);
                    clearTile(ind1, ind2 - 1);
                    clearTile(ind1 - 1, ind2);
                }   else {
                    clearTile(ind1, ind2 + 1);                                          //these 4 lines handle the entire middle. 
                    clearTile(ind1 - 1, ind2);
                    clearTile(ind1, ind2 - 1);
                    clearTile(ind1 + 1, ind2);
                }
            }    
        }   
    }


    public void lost(int a, int b) {                                                    //first loss case for loss by failed check
        for (int i = 0; i < length; i++) {                                              //nested loops to go through each button
            for (int j = 0; j < length; j++) {
                if (!(buttons[i][j].getBackground().equals(Color.GREEN)) && !(buttons[i][j].getBackground().equals(Color.GRAY))) {             
                    buttons[i][j].setBackground(Color.RED);                             //sets the ones that arent correct or clicked to red 
                    buttons[i][j].setEnabled(false);                                    //and disables the buttons for use, forces user to restart
                }
            }
        }
        buttons[a][b].setBackground(Color.RED);                                         //sets the incorrect button to red
        check.setEnabled(false);                                                        //disables check button for future use, also to force restart
        flagButton.setEnabled(false);                                                   //disables flagButton
    }

    public void lost(MineButton b) {                                                    //case for loss by clicking mine
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (!(buttons[i][j].getBackground().equals(Color.DARK_GRAY)) && !(buttons[i][j].getBackground().equals(Color.GRAY))) {             
                    buttons[i][j].setBackground(Color.RED);                             //sets the ones that arent correct or clicked to red 
                    buttons[i][j].setEnabled(false);                                    //and disables the buttons for use, forces user to restart
                }
            }
        }
        displayText.setText("YOU LOSE    YOU LOSE    YOU LOSE    YOU LOSE    YOU LOSE    YOU LOSE    YOU LOSE    YOU LOSE");
        b.setBackground(Color.BLACK);
        check.setEnabled(false);
        flagButton.setEnabled(false);
    }
    


    public void view() {                                                                //view method
        this.setVisible(true);
    }
}
