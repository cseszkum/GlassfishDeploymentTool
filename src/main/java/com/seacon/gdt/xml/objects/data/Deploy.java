package com.seacon.gdt.xml.objects.data;

import com.seacon.gdt.xml.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Peter
 */
@XmlType(name = Constants.deploy)
public class Deploy implements Serializable {
 
    public static final long serialVersionUID = 20150131331L;
    
    private String name;
    private String type;
    private String contextroot;
    private String path;
    private List<Property> properties;
    
    public Deploy() {
        this.name = "";
        this.type = "";
        this.contextroot = "";
        this.path = "";
        this.properties = new ArrayList<Property>();
    }

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    @XmlAttribute
    public void setType(String type) {
        this.type = type;
    }

    public String getContextroot() {
        return contextroot;
    }

    @XmlAttribute
    public void setContextroot(String contextroot) {
        this.contextroot = contextroot;
    }

    public String getPath() {
        return path;
    }

    @XmlAttribute
    public void setPath(String path) {
        this.path = path;
    }

    public List<Property> getProperties() {
        return properties;
    }

    @XmlElementWrapper
    @XmlElement(name="property")
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }


    
    
}
