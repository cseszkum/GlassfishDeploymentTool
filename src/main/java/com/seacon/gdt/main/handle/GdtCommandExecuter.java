package com.seacon.gdt.main.handle;

import java.util.List;

/**
 *
 * @author varsanyi.peter
 */
public class GdtCommandExecuter {
    
    public GdtCommandExecuter() {
    }
    
    public void execute(List<GdtCommand> commandCollection) throws Exception {
        if (commandCollection.size() == 0) {
            return;
        }
        setRightOrder(commandCollection);
        executeCommands(commandCollection);
    }

    private void setRightOrder(List<GdtCommand> commandCollection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void executeCommands(List<GdtCommand> commandCollection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
