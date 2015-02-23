package com.seacon.gdt.runtime.component;

import com.seacon.gdt.runtime.AsadminCommandExecuter;
import com.seacon.gdt.utility.PasswordFileHandler;
import com.seacon.gdt.xml.objects.servers.Target;
import java.net.URISyntaxException;

/**
 * http://docs.oracle.com/cd/E19798-01/821-1750/giulr/index.html
 *
 * @author varsanyi.peter
 */
public class Deploy extends AsadminCommandExecuter {

    public Deploy(String asadminPath, Target targetServer) throws URISyntaxException {
        super(asadminPath, targetServer);
        setProcessInfo("Deploy component");
        setCommandExecuteIndex(com.seacon.gdt.xml.Constants.CI_COMPONENT_DEPLOY);
    }

    public void setParameters(com.seacon.gdt.xml.objects.data.Component componentData, com.seacon.gdt.xml.objects.data.Component parentAppData) throws URISyntaxException, Exception {
        getParameters().add("-H");
        getParameters().add(getTargetServer().getHost());
        getParameters().add("-p");
        getParameters().add(getTargetServer().getPort());
        getParameters().add("-u");
        getParameters().add(getTargetServer().getUser());
        getParameters().add("--passwordfile");
        getParameters().add("\"" + PasswordFileHandler.getPasswordFilePath() + "\"");

        getParameters().add("deploy");
        
        if (componentData.getType() != null && !componentData.getType().isEmpty()) {
            getParameters().add("--type");
            getParameters().add(componentData.getType());
        }
        
        if (componentData.getContextroot() != null && !componentData.getContextroot().isEmpty()) {
            getParameters().add("--contextroot");
            getParameters().add(componentData.getContextroot());
        }

        if (componentData.getName()!= null && !componentData.getName().isEmpty()) {
            getParameters().add("--name");
            getParameters().add(componentData.getName());
        }
        
        if (componentData.getProperties().size() != 0) {
            getParameters().add("--property");
            
            String propStr = "";
            for (com.seacon.gdt.xml.objects.data.Property prop : componentData.getProperties()) {
                if (!"".equals(propStr)) {
                    propStr += ":";
                }
                propStr += prop.getName() + "=" + prop.getValue();
            }
            getParameters().add("\"" + propStr + "\"");
        }     
        
        getParameters().add(componentData.getPath());
    }

}
