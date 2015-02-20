package com.seacon.gdt.main;

import com.seacon.gdt.runtime.pool.Create;
import com.seacon.gdt.runtime.pool.Drop;
import com.seacon.gdt.utility.GdtLog;
import com.seacon.gdt.xml.objects.Data;
import com.seacon.gdt.xml.objects.servers.Command;
import com.seacon.gdt.xml.objects.servers.Connection;
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
     * Order:
     * - pools
     * - connections
     * - domains
     * - domain apps (in handleDomains)
     *
     * @param command
     * @param data
     */
    public void handle(Command command, Data data) throws Exception {
        handlePools(command.getPools(), data);
        handleConnections(command.getConnections(), data);
        handleDomains(command.getDomains(), data);
    }

    private void handlePools(List<com.seacon.gdt.xml.objects.servers.Pool> pools, Data data) throws Exception {
        for (com.seacon.gdt.xml.objects.servers.Pool poolCmd : pools) {

            if (poolCmd.isSkip()) {
                GdtLog.info("SKIP pool command. id: '" + poolCmd.getId() + "'");
            } else {
                com.seacon.gdt.xml.objects.data.Pool poolData = getPoolDatabyId(poolCmd.getId(), data);

                GdtLog.info("Handle pool command. id: '" + poolData.getId() + "' - jndiName: " + poolData.getJndiName());

                if ((poolCmd.isDrop() && poolData.isExists(asadminPath, targetServer)) || poolCmd.isRecreate()) {
                    com.seacon.gdt.runtime.pool.Drop dropPool = new Drop(this.asadminPath, this.targetServer);
                    dropPool.setParameters(poolData);
                    dropPool.execute();
                } else {
                    GdtLog.info("drop: SKIPPED.");
                }

                if ((poolCmd.isCreate() && !poolData.isExists(asadminPath, targetServer)) || poolCmd.isRecreate()) {
                    com.seacon.gdt.runtime.pool.Create createPool = new Create(this.asadminPath, this.targetServer);
                    createPool.setParameters(poolData);
                    createPool.execute();
                } else {
                    GdtLog.info("create: SKIPPED.");
                }
            }
        }
    }

    private void handleConnections(List<Connection> connections, Data data) {
        GdtLog.error("Not supported yet.");
    }

    private void handleDomains(List<Domain> domains, Data data) {
        GdtLog.error("Not supported yet.");
    }

    private com.seacon.gdt.xml.objects.data.Pool getPoolDatabyId(String id, Data data) throws Exception {
        com.seacon.gdt.xml.objects.data.Pool retVal = null;

        for (int i = 0; i < data.getPools().size() && retVal == null; i++) {
            if (data.getPools().get(i).getId().equals(id)) {
                retVal = data.getPools().get(i);
            }
        }

        if (retVal == null) {
            throw new Exception("Not found data pool by id! (id: '" + id + "')");
        }
        return retVal;
    }

}
