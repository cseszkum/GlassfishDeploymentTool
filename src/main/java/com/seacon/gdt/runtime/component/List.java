package com.seacon.gdt.runtime.component;

import com.seacon.gdt.runtime.domain.*;
import com.seacon.gdt.runtime.GdtCommand;
import com.seacon.gdt.utility.PasswordFileHandler;
import com.seacon.gdt.xml.objects.servers.Target;
import java.net.URISyntaxException;

/**
 * http://docs.oracle.com/cd/E19798-01/821-1750/giulr/index.html
 *
 * @author varsanyi.peter
 */
public class List extends GdtCommand {

    public List(String asadminPath, Target targetServer) throws URISyntaxException {
        super(asadminPath, targetServer);
        setProcessInfo("List components");
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

        if (componentData.isTypeApplication()) {
            getParameters().add("list-applications");
        }
        
        if (componentData.isTypeComponent()) {
            getParameters().add("list-components");
        }

        if (componentData.isTypeSubcomponent()) {
            getParameters().add("list-sub-components");
            
            if (parentAppData == null) {
                throw new Exception("The 'parentApData' parameter is null, but it's need for the SUBCOMPONENT LIST command!");
            }
            getParameters().add("--appname");
            getParameters().add(parentAppData.getName());
        }
        
        if (componentData.getType() != null && !componentData.getType().isEmpty()) {
            getParameters().add("--type");
            getParameters().add(componentData.getType());
        }
    }

}
