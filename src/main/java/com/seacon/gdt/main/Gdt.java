package com.seacon.gdt.main;

import com.seacon.gdt.runtime.GdtCommandPreparator;
import com.seacon.gdt.runtime.GdtCommand;
import com.seacon.gdt.runtime.server.Version;
import com.seacon.gdt.utility.GdtLog;
import com.seacon.gdt.utility.Jaxb;
import com.seacon.gdt.utility.PasswordFileHandler;
import com.seacon.gdt.utility.comparators.CommandComparator;
import com.seacon.gdt.utility.comparators.GdtCommandComparator;
import com.seacon.gdt.utility.comparators.ServerComparator;
import com.seacon.gdt.xml.objects.servers.Command;
import com.seacon.gdt.xml.objects.servers.Server;
import com.seacon.gdt.xml.objects.servers.Target;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

/**
 *
 *
 * @author varsanyi.peter
 */
public class Gdt {

    private String xmlFilePath;
    private com.seacon.gdt.xml.objects.Gdt gdt;

    public Gdt(String xmlFilePath) throws Exception {
        this.xmlFilePath = xmlFilePath;
        this.gdt = null;
    }

    public void processXml() throws Exception {
        if (this.isValidXml()) {
            this.execute();
        }
    }

    /**
     * Check validity, print errors (if any) and then return.
     *
     * http://www.freeformatter.com/xsd-generator.html
     *
     * @return Boolean.
     */
    private Boolean isValidXml() {
        Boolean retVal = true;
        try {
            InputStream xsd = getClass().getResourceAsStream("/gdt.xsd");
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(this.xmlFilePath)));
        } catch (Exception ex) {
            GdtLog.error("VALIDATION ERROR! The given parameter xml is not valid!!!");
            GdtLog.error(ex);
            retVal = false;
        }
        return retVal;
    }

    private void execute() throws Exception {
        this.gdt = Jaxb.readXml(this.xmlFilePath);
        if (this.gdt != null) {
            GdtLog.info("----- GDT execute begin. -----");
            printBeginInfo();

            Collections.sort(this.gdt.getServers(), new ServerComparator());
            for (Server server : this.gdt.getServers()) {
                printServerInfo(server);
                if (isServerRunning(gdt.getParameters().getAsadminpath(), server.getTarget())) {
                    if (!server.isSkip()) {
                        PasswordFileHandler.createPasswordFile(server.getTarget().getPassword());
                        Collections.sort(server.getCommands(), new CommandComparator());
                        List<GdtCommand> commandCollection = new ArrayList<GdtCommand>();

                        GdtLog.info("--- C O M M A N D    P R E P A R A T O R    B E G I N. ---");

                        for (Command command : server.getCommands()) {
                            if (command.isSkip()) {
                                GdtLog.info("'" + command.getId() + "' command is SKIPPED!");
                            } else {
                                GdtLog.info("'" + command.getId() + "' command is preparing...");
                                GdtCommandPreparator cpre = new GdtCommandPreparator(gdt.getParameters().getAsadminpath(), server.getTarget());
                                commandCollection.addAll(cpre.prepare(command, this.gdt.getData()));
                            }
                        }
                        
                        GdtLog.info("--- C O M M A N D    P R E P A R A T O R    E N D. ---");

                        if (commandCollection.size() != 0) {
                            Collections.sort(commandCollection, new GdtCommandComparator());
                            
                            GdtLog.info("--- C O M M A N D    E X E C U T I O N S    B E G I N. ---");
                            for (GdtCommand gdtCommand : commandCollection) {
                                gdtCommand.execute();
                            }
                            GdtLog.info("--- C O M M A N D    E X E C U T I O N S    E N D. ---");
                        }
                    }
                }
            }

            GdtLog.info("----- GDT execute end. -----");
        }
    }

    private void printServerInfo(Server server) {
        GdtLog.info("Server");
        GdtLog.info("  Host: " + server.getTarget().getHost());
        GdtLog.info("  Port: " + server.getTarget().getPort());
        GdtLog.info("  User: " + server.getTarget().getUser());
        if (server.isSkip()) {
            GdtLog.info("This server is SKIPPED!");
        }
    }

    private void printBeginInfo() {
        GdtLog.info("Xml file: " + this.xmlFilePath);
        GdtLog.info("Name: " + this.gdt.getParameters().getName());
        GdtLog.info("Last modified: " + this.gdt.getParameters().getLastmodified());
        GdtLog.info("ASADMIN path: " + this.gdt.getParameters().getAsadminpath());
    }

    private boolean isServerRunning(String asadminPath, Target targetServer) throws Exception {
        com.seacon.gdt.runtime.server.Version cmdVer = new Version(asadminPath, targetServer);
        cmdVer.execute();
        Boolean retVal = cmdVer.isServerRunning();
        if (retVal) {
            GdtLog.info("Server is running.");
        } else {
            GdtLog.error("Unreachable server! Is not running?");
        }
        return retVal;
    }

}
