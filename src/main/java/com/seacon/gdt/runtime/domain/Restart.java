package com.seacon.gdt.runtime.domain;

import com.seacon.gdt.runtime.GdtCommand;
import com.seacon.gdt.utility.PasswordFileHandler;
import com.seacon.gdt.xml.objects.data.Domain;
import com.seacon.gdt.xml.objects.servers.Target;
import java.net.URISyntaxException;

/**
 * http://docs.oracle.com/cd/E19798-01/821-1751/ginqj/index.html
 * 
 * @author varsanyi.peter
 */
public class Restart extends GdtCommand {
    
    public Restart(String asadminPath, Target targetServer) {
        super(asadminPath, targetServer);
        setProcessInfo("Restart domain");
        setCommandExecuteIndex(com.seacon.gdt.xml.Constants.CI_DOMAIN_RESTART);
    }

    public void setParameters(Domain domainData) throws URISyntaxException {
        getParameters().add("-H");
        getParameters().add(getTargetServer().getHost());
        getParameters().add("-p");
        getParameters().add(getTargetServer().getPort());
        getParameters().add("-u");
        getParameters().add(getTargetServer().getUser());
        getParameters().add("--passwordfile");
        getParameters().add("\"" + PasswordFileHandler.getPasswordFilePath() + "\"");

        getParameters().add("restart-domain");

        getParameters().add(domainData.getName());
    }
    
}
