package ui.views;

import ui.Window;

import controller.views.Controller;

import javax.swing.JPanel;

import java.awt.FlowLayout;

/**
  * The class <code>View</code> represents the base for the main views of the application
  * @version 1.0
  * @author Dorian Terbah 
**/
public class View extends JPanel {

    protected Controller controller;

    public View(Window window) {
        super(new FlowLayout(FlowLayout.CENTER));
    }
}