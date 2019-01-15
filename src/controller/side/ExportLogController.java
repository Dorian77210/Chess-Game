package controller.side;

import log.LogExport;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
  * The class <code>ExportLogController</code> controls the exprt of the log
  * @version 1.0
  * @author Dorian Terbah 
**/

public class ExportLogController implements ActionListener {

    /**
      * Constant used to identify the export log button 
    **/
    private static final String EXPORT_LOG_ACTION_COMMAND = "EXPORT_LOG_ACTION_COMMAND";

    public ExportLogController(JButton exportLog) {
        exportLog.setActionCommand(EXPORT_LOG_ACTION_COMMAND);
        exportLog.addActionListener(this);
    }

    /**
      * React to an button's event
      * @param event The actual event
    **/
    @Override 
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if(actionCommand.equals(EXPORT_LOG_ACTION_COMMAND)) {
            LogExport.exportLog();
        }
    }
}