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
public class Component implements Serializable {
    public static final long serialVersionUID = 20150131223L;
    
    private String id;
    private String ctype;
    private String scappid;
    
    private String name;
    private String type;
    private Deploy deploy;

    public Component() {
        this.id = "";
        this.ctype = "";
        this.scappid = "";

        this.name = "";
        this.type = "";
        this.deploy = new Deploy();
    }
    
    public Boolean isExists(String asadminPath, Target targetServer) throws IOException, URISyntaxException {
        Boolean retVal = false;
        
        com.seacon.gdt.runtime.application.List listCmd = new com.seacon.gdt.runtime.application.List(asadminPath, targetServer);
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

    public String getCtype() {
        return ctype;
    }

    @XmlAttribute
    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getScappid() {
        return scappid;
    }
    
    @XmlAttribute
    public void setScappid(String scappid) {
        this.scappid = scappid;
    }

    public String getType() {
        return type;
    }

    @XmlElement
    public void setType(String type) {
        this.type = type;
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
