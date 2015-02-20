package com.seacon.gdt.runtime.domain;

import com.seacon.gdt.runtime.AsadminCommandExecuter;
import com.seacon.gdt.utility.PasswordFileHandler;
import com.seacon.gdt.xml.objects.data.Domain;
import com.seacon.gdt.xml.objects.servers.Target;
import java.net.URISyntaxException;

/**
 * http://docs.oracle.com/cd/E19776-01/820-4497/6nfv6jljd/index.html
 * 
 * @author varsanyi.peter
 */
public class Drop extends AsadminCommandExecuter {
    
    public Drop(String asadminPath, Target targetServer) {
        super(asadminPath, targetServer);
        setProcessInfo("Drop domain");
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

        getParameters().add("delete-domain");

        getParameters().add(domainData.getName());
    }
    
}
