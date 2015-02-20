package com.seacon.gdt.xml.objects.data;

import com.seacon.gdt.xml.Constants;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Peter
 */
@XmlType(name = Constants.deploy)
public class Deploy implements Serializable {
 
    public static final long serialVersionUID = 20150131331L;
    
    private String contextname;
    private String deployobject;
    
    public Deploy() {
        this.contextname = "";
        this.deployobject = "";
    }

    public String getContextname() {
        return contextname;
    }

    @XmlElement
    public void setContextname(String contextname) {
        this.contextname = contextname;
    }

    public String getDeployobject() {
        return deployobject;
    }

    @XmlElement
    public void setDeployobject(String deployobject) {
        this.deployobject = deployobject;
    }
    
    
}
