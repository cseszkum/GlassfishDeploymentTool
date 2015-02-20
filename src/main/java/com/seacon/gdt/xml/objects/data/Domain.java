package com.seacon.gdt.xml.objects.data;

import com.seacon.gdt.xml.Constants;
import java.io.Serializable;
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
