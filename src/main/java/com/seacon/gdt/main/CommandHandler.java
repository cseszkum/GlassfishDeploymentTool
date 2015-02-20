package com.seacon.gdt.main;

import com.seacon.gdt.runtime.pool.Create;
import com.seacon.gdt.runtime.pool.Drop;
import com.seacon.gdt.utility.GdtLog;
import com.seacon.gdt.xml.objects.Data;
import com.seacon.gdt.xml.objects.servers.Application;
import com.seacon.gdt.xml.objects.servers.Command;
import com.seacon.gdt.xml.objects.servers.Jdbcresource;
import com.seacon.gdt.xml.objects.servers.Domain;
import com.seacon.gdt.xml.objects.servers.Pool;
import com.seacon.gdt.xml.objects.servers.Target;
import java.util.List;

/**
 *
 * @author varsanyi.peter
 */
class CommandHandler {

    private String asadminPath;
    private Target targetServer;

    public CommandHandler(String asadminPath, Target targetServer) {
        this.asadminPath = asadminPath;
        this.targetServer = targetServer;
    }

    /**
     * Command handler.
     *
     * Order: - pools - jdbcresources - domains - domain apps (in handleDomains)
     *
     * @param command
     * @param data
     */
    public void handle(Command command, Data data) throws Exception {
        handlePools(command.getPools(), data);
        handleJdbcresources(command.getJdbcresources(), data);
        handleDomains(command.getDomains(), data);
    }

    private void handlePools(List<com.seacon.gdt.xml.objects.servers.Pool> poolsInCommand, Data data) throws Exception {
        for (com.seacon.gdt.xml.objects.servers.Pool poolCmd : poolsInCommand) {
            if (poolCmd.isSkip()) {
                GdtLog.info("SKIP pool command. id: '" + poolCmd.getId() + "'");
            } else {
                com.seacon.gdt.xml.objects.data.Pool poolData = getPoolDataById(poolCmd.getId(), data);

                GdtLog.info("Handle pool command. id: '" + poolData.getId() + "' - jndiName: " + poolData.getJndiName());

                Boolean poolExists = poolData.isExists(asadminPath, targetServer);
                
                if ((poolCmd.isDrop() && poolExists) || poolCmd.isRecreate()) {
                    com.seacon.gdt.runtime.pool.Drop poolDrop = new com.seacon.gdt.runtime.pool.Drop(this.asadminPath, this.targetServer);
                    poolDrop.setParameters(poolData);
                    poolDrop.execute();
                } else {
                    GdtLog.info("drop: SKIPPED.");
                }

                if ((poolCmd.isCreate() && !poolExists) || poolCmd.isRecreate()) {
                    com.seacon.gdt.runtime.pool.Create poolCreate = new com.seacon.gdt.runtime.pool.Create(this.asadminPath, this.targetServer);
                    poolCreate.setParameters(poolData);
                    poolCreate.execute();
                } else {
                    GdtLog.info("create: SKIPPED.");
                }
            }
        }
    }

    private void handleJdbcresources(List<Jdbcresource> jdbcresoucesInCommand, Data data) throws Exception {
        for (com.seacon.gdt.xml.objects.servers.Jdbcresource jdbcrCmd : jdbcresoucesInCommand) {
            if (jdbcrCmd.isSkip()) {
                GdtLog.info("SKIP JDBC Resource command. id: '" + jdbcrCmd.getId() + "'");
            } else {
                com.seacon.gdt.xml.objects.data.Jdbcresource jdbcrData = getJdbcresourceDataById(jdbcrCmd.getId(), data);

                GdtLog.info("Handle jdbc resource command. id: '" + jdbcrData.getId() + "' - name: " + jdbcrData.getName());

                Boolean jdbcrExists = jdbcrData.isExists(asadminPath, targetServer);
                
                if ((jdbcrCmd.isDrop() && jdbcrExists) || jdbcrCmd.isRecreate()) {
                    com.seacon.gdt.runtime.jdbcresource.Drop jdbcrDrop = new com.seacon.gdt.runtime.jdbcresource.Drop(this.asadminPath, this.targetServer);
                    jdbcrDrop.setParameters(jdbcrData);
                    jdbcrDrop.execute();
                } else {
                    GdtLog.info("drop: SKIPPED.");
                }

                if ((jdbcrCmd.isCreate() && !jdbcrExists) || jdbcrCmd.isRecreate()) {
                    com.seacon.gdt.xml.objects.data.Pool poolData = getPoolDataById(jdbcrData.getPoolid(), data);
                    com.seacon.gdt.runtime.jdbcresource.Create jdbcrCreate = new com.seacon.gdt.runtime.jdbcresource.Create(this.asadminPath, this.targetServer);
                    jdbcrCreate.setParameters(jdbcrData, poolData);
                    jdbcrCreate.execute();
                } else {
                    GdtLog.info("create: SKIPPED.");
                }
            }
        }
    }

    private void handleDomains(List<Domain> domainsInCommand, Data data) throws Exception {
        for (com.seacon.gdt.xml.objects.servers.Domain domainCmd : domainsInCommand) {
            if (domainCmd.isSkip()) {
                GdtLog.info("SKIP domain command. id: '" + domainCmd.getId() + "'");
            } else {
                com.seacon.gdt.xml.objects.data.Domain domainData = getDomainDataById(domainCmd.getId(), data);

                GdtLog.info("Handle domain command. id: '" + domainData.getId() + "' - name: " + domainData.getName());
                
                Boolean domainExists = domainData.isExists(asadminPath, targetServer);
                
                if ((domainCmd.isDrop() && domainExists)) {
                    com.seacon.gdt.runtime.domain.Drop domainDrop = new com.seacon.gdt.runtime.domain.Drop(this.asadminPath, this.targetServer);
                    domainDrop.setParameters(domainData);
                    domainDrop.execute();
                } else {
                    GdtLog.info("drop: SKIPPED.");
                }

                if (domainCmd.isCreate() && !domainExists) {
                    com.seacon.gdt.runtime.domain.Create domainCreate = new com.seacon.gdt.runtime.domain.Create(this.asadminPath, this.targetServer);
                    domainCreate.setParameters(domainData);
                    domainCreate.execute();
                } else {
                    GdtLog.info("create: SKIPPED.");
                }

                if (domainCmd.isStop() && domainExists) {
                    com.seacon.gdt.runtime.domain.Stop domainStop = new com.seacon.gdt.runtime.domain.Stop(this.asadminPath, this.targetServer);
                    domainStop.setParameters(domainData);
                    domainStop.execute();
                } else {
                    GdtLog.info("stop: SKIPPED.");
                }

                if (domainCmd.isStart() && domainExists) {
                    com.seacon.gdt.runtime.domain.Start domainStart = new com.seacon.gdt.runtime.domain.Start(this.asadminPath, this.targetServer);
                    domainStart.setParameters(domainData);
                    domainStart.execute();
                } else {
                    GdtLog.info("start: SKIPPED.");
                }

                if (domainCmd.isRestart() && domainExists) {
                    com.seacon.gdt.runtime.domain.Restart domainRestart = new com.seacon.gdt.runtime.domain.Restart(this.asadminPath, this.targetServer);
                    domainRestart.setParameters(domainData);
                    domainRestart.execute();
                } else {
                    GdtLog.info("restart: SKIPPED.");
                }

                if (domainExists) {
                    handleApplications(domainCmd.getApplications(), domainData, data);
                }
            }
        }
    }
    
    private void handleApplications(List<Application> appssInCommand, com.seacon.gdt.xml.objects.data.Domain domainData, Data data) throws Exception {
        for (com.seacon.gdt.xml.objects.servers.Application appCmd : appssInCommand) {
            if (appCmd.isSkip()) {
                GdtLog.info("SKIP application command. id: '" + appCmd.getId() + "'");
            } else {
                com.seacon.gdt.xml.objects.data.Application appData = getApplicationDataById(appCmd.getId(), data);

                GdtLog.info("Handle application command. id: '" + appData.getId() + "' - name: " + appData.getName());
                
                Boolean appExists = appData.isExists(asadminPath, targetServer);
            }
        }
    }

    private com.seacon.gdt.xml.objects.data.Pool getPoolDataById(String id, Data data) throws Exception {
        com.seacon.gdt.xml.objects.data.Pool retVal = null;

        for (int i = 0; i < data.getPools().size() && retVal == null; i++) {
            if (data.getPools().get(i).getId().equals(id)) {
                retVal = data.getPools().get(i);
            }
        }

        if (retVal == null) {
            throw new Exception("Not found pool data by id! (id: '" + id + "')");
        }
        return retVal;
    }

    private com.seacon.gdt.xml.objects.data.Jdbcresource getJdbcresourceDataById(String id, Data data) throws Exception {
        com.seacon.gdt.xml.objects.data.Jdbcresource retVal = null;

        for (int i = 0; i < data.getJdbcresources().size() && retVal == null; i++) {
            if (data.getJdbcresources().get(i).getId().equals(id)) {
                retVal = data.getJdbcresources().get(i);
            }
        }

        if (retVal == null) {
            throw new Exception("Not found jdbc resource data by id! (id: '" + id + "')");
        }
        return retVal;
    }

    private com.seacon.gdt.xml.objects.data.Domain getDomainDataById(String id, Data data) throws Exception {
        com.seacon.gdt.xml.objects.data.Domain retVal = null;

        for (int i = 0; i < data.getDomains().size() && retVal == null; i++) {
            if (data.getDomains().get(i).getId().equals(id)) {
                retVal = data.getDomains().get(i);
            }
        }

        if (retVal == null) {
            throw new Exception("Not found domain data by id! (id: '" + id + "')");
        }
        return retVal;
    }

    private com.seacon.gdt.xml.objects.data.Application getApplicationDataById(String id, Data data) throws Exception {
        com.seacon.gdt.xml.objects.data.Application retVal = null;

        for (int i = 0; i < data.getApplications().size() && retVal == null; i++) {
            if (data.getApplications().get(i).getId().equals(id)) {
                retVal = data.getApplications().get(i);
            }
        }

        if (retVal == null) {
            throw new Exception("Not found application data by id! (id: '" + id + "')");
        }
        return retVal;
    }

}
