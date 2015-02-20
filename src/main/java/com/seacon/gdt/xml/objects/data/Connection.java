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
@XmlType(name = Constants.connection, namespace = "org.moooz.data")
public class Connection implements Serializable {
    public static final long serialVersionUID = 20150131224L;
    
    private String id;
    
    private String name;
    private String poolid;

    public Connection() {
        this.id = "";

        this.name = "";
        this.poolid = "";
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

    public String getPoolid() {
        return poolid;
    }

    @XmlElement
    public void setPoolid(String poolid) {
        this.poolid = poolid;
    }
    
    
    
}
