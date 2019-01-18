package ui.views;

import ui.Window;   

import ui.views.View;

import javax.swing.JButton;

import java.io.File;

import controller.views.HomeController;

/**
  * The class <code>HomeView</code> represents the view for the home
  * @version 1.0
  * @author Dorian Terbah 
**/

public class HomeView extends View {

    /**
      * Button to access to the pvp mode for the game 
    **/
    private JButton pvpButton;

    /**
      * Button to access to the pve mode for the game 
    **/
    private JButton pveButton;

    /**
      * Button to access to the credit menu 
    **/
    private JButton creditButton;

    /**
      * Button to load game existing 
    **/
    private JButton loadGameButton;

    public HomeView(Window window) {
        super(window);

        //creation of the buttons
        this.pvpButton = new JButton("PVP Mode");
        this.pveButton = new JButton("PVE Mode");
        this.creditButton = new JButton("Credit");
        this.loadGameButton = null;

        //add the buttons to the current view
        this.add(this.pvpButton);
        this.add(this.pveButton);   
        this.add(this.creditButton);

        //create the controller
        File file = new File(new File("export"), "game.txt");
        if(file.exists()) {
            this.loadGameButton = new JButton("Load game");
            this.add(this.loadGameButton);
        } 
        
        this.controller = new HomeController(window, this.pvpButton, this.pveButton, this.creditButton, this.loadGameButton);
    }
}