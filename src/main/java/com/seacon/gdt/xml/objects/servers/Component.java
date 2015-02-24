<<<<<<< HEAD
package com.seacon.gdt.xml.objects.servers;

import com.seacon.gdt.utility.Utility;
import com.seacon.gdt.xml.Constants;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Peter
 */
@XmlType(name = Constants.application, namespace = "org.moooz.servers")
public class Component implements Serializable {
    public static final long serialVersionUID = 2015013113L;

    private String id;
    private String skip;
    private String action;
    
    public Component() {
        this.id = "";
        this.skip = "";
        this.action = "";
    }

    public Boolean isSkip() {
        return Utility.convertStrToBoolean(this.skip);
    }
    
    public Boolean isDeploy() {
        return (this.action != null && "deploy".equals(this.action.toLowerCase()));
    }

    public Boolean isReload() {
        return (this.action != null && "reload".equals(this.action.toLowerCase()));
    }
    
    public Boolean isUndeploy() {
        return (this.action != null && "undeploy".equals(this.action.toLowerCase()));
    }
    
    public Boolean isRedeploy() {
        return (this.action != null && "redeploy".equals(this.action.toLowerCase()));
    }

    public String getId() {
        return id;
    }

    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    public String getSkip() {
        return skip;
    }

    @XmlAttribute
    public void setSkip(String skip) {
        this.skip = skip;
    }

    public String getAction() {
        return action;
    }

    @XmlAttribute
    public void setAction(String action) {
        this.action = action;
    }

}
=======
package com.seacon.gdt.xml.objects.servers;

import com.seacon.gdt.utility.Utility;
import com.seacon.gdt.xml.Constants;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Peter
 */
@XmlType(name = Constants.component, namespace = "org.moooz.servers")
public class Component implements Serializable {
    public static final long serialVersionUID = 2015013113L;

    private String id;
    private String skip;
    private String action;
    
    public Component() {
        this.id = "";
        this.skip = "";
        this.action = "";
    }

    public Boolean isSkip() {
        return Utility.convertStrToBoolean(this.skip);
    }
    
    public Boolean isDeploy() {
        return (this.action != null && "deploy".equals(this.action.toLowerCase()));
    }

    public Boolean isReload() {
        return (this.action != null && "reload".equals(this.action.toLowerCase()));
    }
    
    public Boolean isUndeploy() {
        return (this.action != null && "undeploy".equals(this.action.toLowerCase()));
    }
    
    public Boolean isRedeploy() {
        return (this.action != null && "redeploy".equals(this.action.toLowerCase()));
    }

    public String getId() {
        return id;
    }

    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    public String getSkip() {
        return skip;
    }

    @XmlAttribute
    public void setSkip(String skip) {
        this.skip = skip;
    }

    public String getAction() {
        return action;
    }

    @XmlAttribute
    public void setAction(String action) {
        this.action = action;
    }

}
>>>>>>> origin/master
