package com.seacon.gdt.runtime.connection;

import com.seacon.gdt.runtime.pool.*;
import com.seacon.gdt.runtime.AsadminCommandExecuter;
import com.seacon.gdt.utility.PasswordFileHandler;
import com.seacon.gdt.xml.objects.data.Pool;
import com.seacon.gdt.xml.objects.servers.Target;
import java.net.URISyntaxException;

/**
 * http://docs.oracle.com/cd/E19776-01/820-4497/6nfv6jlj0/index.html
 *
 * @author varsanyi.peter
 */
public class Create extends AsadminCommandExecuter {

    public Create(String asadminPath, Target targetServer) {
        super(asadminPath, targetServer);
        setProcessInfo("Create connection (JDBC resource)");
    }

    public void setParameters(Pool poolData) throws URISyntaxException {
        getParameters().add("-H");
        getParameters().add(getTargetServer().getHost());
        getParameters().add("-p");
        getParameters().add(getTargetServer().getPort());
        getParameters().add("-u");
        getParameters().add(getTargetServer().getUser());
        getParameters().add("--passwordfile");
        getParameters().add("\"" + PasswordFileHandler.getPasswordFilePath() + "\"");

        getParameters().add("create-jdbc-resource");

        getParameters().add("--connectionpoolid");
                
        getParameters().add(poolData.getJndiName());

        if (poolData.getProperties().size() != 0) {
            getParameters().add("--property");
            
            String propStr = "";
            for (com.seacon.gdt.xml.objects.data.Property prop : poolData.getProperties()) {
                if (!"".equals(propStr)) {
                    propStr += ":";
                }
                propStr += prop.getName() + "=" + prop.getValue();
            }
            getParameters().add("\"" + propStr + "\"");
        }

    }

}
