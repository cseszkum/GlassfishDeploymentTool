package com.seacon.gdt.xml.objects.data;

import com.seacon.gdt.utility.GdtLog;
import com.seacon.gdt.xml.Constants;
import com.seacon.gdt.xml.objects.servers.Target;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Peter
 */
@XmlType(name = Constants.domain, namespace = "org.moooz.data")
public class Domain implements Serializable {
    public static final long serialVersionUID = 20150131222L;
    
    private String id;
    
    private String name;
    
    public Domain() {
        this.id = "";
        
        this.name = "";
    }
    
    public Boolean isExists(String asadminPath, Target targetServer) throws IOException, URISyntaxException {
        Boolean retVal = false;
        
        com.seacon.gdt.runtime.domain.List listCmd = new com.seacon.gdt.runtime.domain.List(asadminPath, targetServer);
        listCmd.execute();
        
        for (int i = 0; i < listCmd.getOutputLines().size() && retVal == false; i++) {
            if (listCmd.getOutputLines().get(i).equals(this.name)) {
                retVal = true;
            }
        }
        GdtLog.info("'" + this.name + "' is exists: " + retVal);
        return retVal;
    }  
    
    public String getId() {
        return id;
    }

    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }
    
    
}
