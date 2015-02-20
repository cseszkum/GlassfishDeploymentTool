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
@XmlType(name = Constants.application, namespace = "org.moooz.data")
public class Application implements Serializable {
    public static final long serialVersionUID = 20150131223L;
    
    private String id;
    
    private String name;
    private Deploy deploy;
    private String restart;

    public Application() {
        this.id = "";

        this.name = "";
        this.deploy = new Deploy();
        this.restart = "";
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

    public Deploy getDeploy() {
        return deploy;
    }

    @XmlElement
    public void setDeploy(Deploy deploy) {
        this.deploy = deploy;
    }

    public String getRestart() {
        return restart;
    }

    @XmlElement
    public void setRestart(String restart) {
        this.restart = restart;
    }
    
    
}
