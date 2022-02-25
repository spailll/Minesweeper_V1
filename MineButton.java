import java.awt.*;                                                      //MINESWEEPER PROJECT-MineButton.java       BY: BEN SAILOR
import javax.swing.*;

public class MineButton extends JButton {                               //MineButton object class extends JButton to function as such while also holding values
    private int isMine = (int) (Math.random() * 6) % 5 % 4 % 3 % 2;     //randomly selects the mines
    private int flagged = 0;                                            
    private int num;                                                    //object variables for game logic
    private boolean pressed = false;
    public JButton button;


    MineButton() {                                                      //constructor for MineButton
        button = new JButton("");                                       //forwards the method to a normal JButton
    }

    public boolean isPressed() {                                        //returns if button has been pressed or not
        return pressed;
    }

    public void togglePressed() {                                       //toggles weather the button has been pressed or not
        if (pressed) { pressed = false; }                               //logic in MineFrame ensures single direciton
        else { pressed = true; }
    }

    public void toggleFlagged() {                                       //toggles weather the space is flagged or not
        if (flagged == 1) { 
            flagged = 0;
            this.setBackground(new JButton().getBackground());          //changes value and background of tile accordingly
        }
        else { 
            flagged = 1;
            this.setBackground(Color.DARK_GRAY); 
        }
    }
    public int isFlagged() {                                            //returns int value for wether or not the space is flagged, also for game logic
        return flagged;
    }

    public int isMine() {                                               //returns wether or not the space is a mine
        return isMine;
    }

    public void incNum() {                                              //increments the number of nearby bombs, essential for game logic 
        num += 1;
    }

    public int getNum() {                                               //returns the number of touching mines
        return num;
    }
}