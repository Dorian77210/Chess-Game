package ui.side;

import ui.Window;

import controller.side.ExportLogController;

import log.Log;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.BorderLayout;

/**
  * The class <code>LogView</code> represents the display of the log
  * @version 1.0
  * @author Dorian Terbah 
**/

public class LogView extends JPanel {

    /**
      * To display the log 
    **/
    private JTextArea log;

    /**
      * To export the log 
    **/
    private JButton exportLog;

    /**
      * The controller to controls the export of the log 
    **/
    private ExportLogController controller;

    public LogView() {
        super(new BorderLayout());
        this.log = new JTextArea("");

        JScrollPane pane = new JScrollPane(this.log);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //display log
        this.setPreferredSize(new Dimension(200, Window.HEIGHT));
        this.log.setLineWrap(true);
        this.log.setEditable(false);

        this.add(pane, BorderLayout.CENTER);

        //export log
        this.exportLog = new JButton("Export log");
        this.add(this.exportLog, BorderLayout.SOUTH);

        this.controller = new ExportLogController(this.exportLog);
    }

    /**
      * Display the log 
    **/
    public void refreshDisplayLog() {
        this.log.setText(Log.instance().getLog().toString());
    }
}