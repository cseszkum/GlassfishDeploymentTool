package com.seacon.gdt.xml.objects;

import com.seacon.gdt.xml.Constants;
import com.seacon.gdt.xml.objects.data.Application;
import com.seacon.gdt.xml.objects.data.Jdbcresource;
import com.seacon.gdt.xml.objects.data.Domain;
import com.seacon.gdt.xml.objects.data.Pool;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Peter
 */
@XmlType(name = Constants.data)
public class Data implements Serializable {
    
    public static final long serialVersionUID = 20150129422L;
    
    private List<Domain> domains;
    private List<Application> applications;
    private List<Jdbcresource> jdbcresources;
    private List<Pool> pools;
    
    public Data() {
        this.domains = new ArrayList<Domain>();
        this.applications = new ArrayList<Application>();
        this.jdbcresources = new ArrayList<Jdbcresource>();
        this.pools = new ArrayList<Pool>();
    }

    public List<Domain> getDomains() {
        return domains;
    }

    @XmlElementWrapper
    @XmlElement(name = "domain")
    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }

    public List<Application> getApplications() {
        return applications;
    }

    @XmlElementWrapper
    @XmlElement(name = "application")
    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public List<Jdbcresource> getJdbcresources() {
        return jdbcresources;
    }

    @XmlElementWrapper
    @XmlElement(name = "jdbcresource")
    public void setJdbcresources(List<Jdbcresource> jdbcresources) {
        this.jdbcresources = jdbcresources;
    }

    public List<Pool> getPools() {
        return pools;
    }

    @XmlElementWrapper
    @XmlElement(name = "pool")
    public void setPools(List<Pool> pools) {
        this.pools = pools;
    }
    
    
}
