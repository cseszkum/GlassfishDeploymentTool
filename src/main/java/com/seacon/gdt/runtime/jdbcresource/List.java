package com.seacon.gdt.runtime.jdbcresource;

import com.seacon.gdt.runtime.AsadminCommandExecuter;
import com.seacon.gdt.utility.PasswordFileHandler;
import com.seacon.gdt.xml.objects.servers.Target;
import java.net.URISyntaxException;

/**
 * http://docs.oracle.com/cd/E19776-01/820-4497/list-jdbc-resources-1/index.html
 *
 * @author varsanyi.peter
 */
public class List extends AsadminCommandExecuter {

    public List(String asadminPath, Target targetServer) throws URISyntaxException {
        super(asadminPath, targetServer);
        setProcessInfo("List JDBC resources");
        setParameters();
    }

    private void setParameters() throws URISyntaxException {
        getParameters().add("-H");
        getParameters().add(getTargetServer().getHost());
        getParameters().add("-p");
        getParameters().add(getTargetServer().getPort());
        getParameters().add("-u");
        getParameters().add(getTargetServer().getUser());
        getParameters().add("--passwordfile");
        getParameters().add("\"" + PasswordFileHandler.getPasswordFilePath() + "\"");

        getParameters().add("list-jdbc-resources");
    }

}
