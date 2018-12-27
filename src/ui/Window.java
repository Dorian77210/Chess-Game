package ui;

import ui.views.View;
import ui.views.CreditView;
import ui.views.HomeView;

import enums.WindowState;
import enums.GameMode;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

/**
  * The class <code>Window</code> is the graphics interface of the application
  * @version 1.0
  * @author Dorian Terbah 
**/
public class Window extends JFrame {

    /**
      * Constant used to set the width to the window 
    **/
    private static final int WINDOW_WIDTH = 500;

    /**
      * Constant used to set the height to the window 
    **/
    private static final int WINDOW_HEIGHT = 500;

    /**
      * The current view displayed on the window 
    **/
    private View currentView;

    /**
      * The current state to set the view associated 
    **/
    private WindowState state;

    public Window() {
        super("Chess Game");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //initial value
        this.currentView = new View(this);
        this.add(this.currentView, BorderLayout.CENTER);

        this.changeView(WindowState.HOME_VIEW_STATE);
    }

    /**
      * Change the current view of the window
      * @param state The state associated with the view 
    **/
    public void changeView(WindowState state) {
        //change the state
        this.state = state;

        this.remove(this.currentView);
        if(this.state.equals(WindowState.HOME_VIEW_STATE)) {
            this.currentView = new HomeView(this);
        } else if(this.state.equals(WindowState.CREDIT_VIEW_STATE)) {
            this.currentView = new CreditView(this);
        }

        this.add(this.currentView, BorderLayout.CENTER);
        this.revalidate();
    }

    /**
      * Change the current view of the window
      * @param state The state associated with the view
      * @param mode The mode of the game 
    **/
    public void changeView(WindowState state, GameMode mode) {
        this.state = state;
        this.remove(this.currentView);

        //this.currentView = new BoardView(mode);

        this.add(this.currentView, BorderLayout.CENTER);
        this.revalidate();
    } 
}