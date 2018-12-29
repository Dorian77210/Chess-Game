package controller.window;

import ui.Window;

import java.awt.Dimension;

import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
/**
  * The class <code>WindowSizeController</code> controls the resize of the window
  * @version 1.0
  * @author Dorian Terbah 
**/

public class WindowSizeController implements ComponentListener {

    public WindowSizeController() {

    }

    @Override 
    public void componentHidden(ComponentEvent event) {

    }

    @Override 
    public void componentMoved(ComponentEvent event) {

    }

    @Override
    public void componentResized(ComponentEvent event) {
        Window window = (Window)event.getComponent();
        Dimension dimension = window.getSize();
        if((dimension.width < Window.WINDOW_WIDTH) || (dimension.height < Window.WINDOW_HEIGHT)) {
            window.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        } 
    }

    @Override
    public void componentShown(ComponentEvent event) {

    }
}