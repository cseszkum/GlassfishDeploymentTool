package com.seacon.gdt.runtime.jdbcresource;

import com.seacon.gdt.runtime.AsadminCommandExecuter;
import com.seacon.gdt.utility.PasswordFileHandler;
import com.seacon.gdt.xml.objects.data.Jdbcresource;
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
        setProcessInfo("Drop JDBC resource");
    }

    public void setParameters(Jdbcresource jdbcrData) throws URISyntaxException {
        getParameters().add("-H");
        getParameters().add(getTargetServer().getHost());
        getParameters().add("-p");
        getParameters().add(getTargetServer().getPort());
        getParameters().add("-u");
        getParameters().add(getTargetServer().getUser());
        getParameters().add("--passwordfile");
        getParameters().add("\"" + PasswordFileHandler.getPasswordFilePath() + "\"");

        getParameters().add("delete-jdbc-resource");

        getParameters().add(jdbcrData.getName());
    }
    
}
