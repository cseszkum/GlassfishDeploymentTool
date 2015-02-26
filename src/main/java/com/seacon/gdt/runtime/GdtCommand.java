package com.seacon.gdt.runtime;

import com.seacon.gdt.utility.GdtLog;
import com.seacon.gdt.xml.objects.servers.Target;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Peter
 */
public class GdtCommand {

    private Target targetServer;
    private String processInfo;
    private String asadminPath;
    private Process process;
    private List<String> parameters;
    private List<String> outputLines;
    private int commandExecuteIndex;

    public GdtCommand(String asadminPath, Target targetServer) {
        this.asadminPath = asadminPath;
        this.targetServer = targetServer;
        this.parameters = new ArrayList<String>();
        this.outputLines = new ArrayList<String>();
        this.parameters.add(this.asadminPath);
        this.commandExecuteIndex = -1;
    }

    public void execute() throws Exception {
        executeBeforeCommand();
        GdtLog.info("-==  " + processInfo + " command execute begin.  ==-");
        GdtLog.info(getCommandAsString());
        this.outputLines.clear();
        this.process = new ProcessBuilder(getParameters()).start();
        
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            this.outputLines.add(line);
            GdtLog.info(line);
        }
        
        this.process.destroy();
        GdtLog.info("-==  " + processInfo + " command execute end.  ==-");
        executeAfterCommand();
    }

    public void executeBeforeCommand() throws Exception {
        // Override for execte something before command
    }
    
    public void executeAfterCommand() throws Exception {
        // Override for execte something before command
    }
    
    public Target getTargetServer() {
        return targetServer;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public String getProcessInfo() {
        return processInfo;
    }

    public void setProcessInfo(String processInfo) {
        this.processInfo = processInfo;
    }

    private String getCommandAsString() {
        String retVal = "";
        for (String s : this.parameters) {
            retVal = retVal + s + " ";
        }
        return retVal;
    }

    public List<String> getOutputLines() {
        return outputLines;
    }

    public int getCommandExecuteIndex() {
        return commandExecuteIndex;
    }

    public void setCommandExecuteIndex(int commandExecuteIndex) {
        this.commandExecuteIndex = commandExecuteIndex;
    }
}
