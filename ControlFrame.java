import java.awt.*;                                                                  //MINESWEEPER PROJECT-ControlFrame.java s    BY: BEN SAILOR
import java.awt.event.*;
import javax.swing.*;

public class ControlFrame extends JFrame implements ActionListener {                //extended JFrame class to act as a control panel
    private JPanel panel;                                                           //to select size of game and to start game
    private JButton enter;
    private JTextField text;                                                        //declaring object variables, JPanel, JButton, and JTextField

    ControlFrame() {                                                                //main constructor
        panel = new JPanel();                                                       
        enter = new JButton("  START  ");
        text = new JTextField(20);

        text.setText("ENTER GAME SIZE");                                            //Text field to enter numerical game size into (default is 10)

        enter.addActionListener(this);                                              //action listener for the start game button, references the text box
        
        panel.add(text);                                                            //building panel
        panel.add(enter);
        this.setContentPane(panel);                                                 //building ControlFrame
        this.pack();
        this.setSize(300,100);
        this.setLocationRelativeTo(null);                                           //standard options
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                        //exit on close because this is the main background program behind the game
    }

    private boolean isNumeric(String str) throws NumberFormatException{             //method to check if TextField is a number
        try {
            Integer.valueOf(str);                                                   //trys to take integer value of string, catches exception and allows us to use errors
            return true;                                                            //to our advantage while having a concrete testing method (Not origional, of course)
        }   catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {                                    //overriding actionPerformed method for our needs, is previously defined somewhere 
        if (e.getSource() == enter &&  isNumeric(text.getText())) {                 //checks if the enter button was clicked AND if it passes the previous isNumeric method
            MineFrame f = new MineFrame(Integer.valueOf(text.getText()));           //if so, initializes a game of that size
            f.view();                                                               
        }   else if (e.getSource() == enter) {      
            MineFrame f = new MineFrame();                                          //if not, starts a standard 10x10 game
            f.view();
        }
    }

    public void view() {                                                            //view class
        this.setVisible(true);
    }

    public void viewOff() {
        this.setVisible(false);
    }
}
