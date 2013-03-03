package eu.lucubratory.topro1.domain.persistence;

import eu.lucubratory.topro1.domain.persistence.entity.ContainerEntity;
import eu.lucubratory.topro1.domain.persistence.entity.ProposalTypes;
import eu.lucubratory.topro1.domain.configuration.ConfigurationRepository;
import eu.lucubratory.topro1.domain.persistence.entity.ProposalEntity;
import eu.lucubratory.topro1.common.exception.persistence.DatastoreFailureException;
import eu.lucubratory.topro1.common.exception.persistence.system.SystemException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Managing the file data store containing application data.
 * <p/>
 * Note:
 * File access is prohibited in Java EE 6 application (for obvious reasons).
 * However, a DB is simply overkill for this application and a filestore
 * is the simplest solution. The data storage directory is set by the
 * front-up at application start-up. Not the most clean solution, but simple.
 * <p/>
 * LockType.WRITE - simple way to handle the DB serialisation at each data
 * update, knowing that we are in a total-ultra-low user/performance scenario.
 */
@Singleton
@Startup
@Lock(LockType.WRITE)
public class Datastore {

    static public final String BEANNAME="Datastore";

    private final static Logger log=LoggerFactory.getLogger(Datastore.class);

    private @Inject ConfigurationRepository configurationRepository;

    /** Main data container */
    private ContainerEntity container=null;


    /**
     * Starts-up the data store, initialises and loads the file into memory.
     */
    public void kickStart() {

        String datafile=configurationRepository.getDatastoreFile();

        // Load data XML
        Path path=Paths.get(datafile);
        List<String> lines;
        try {
            lines = Files.readAllLines(path,StandardCharsets.UTF_8);
        }
        catch (IOException ex) {
            createAndWriteExampleDatabase();
            XStream xstream=new XStream();
            log.error("Unable to load datastore file ["+datafile+"]. Created a new for you in a minimal version:\n"+
                      "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+xstream.toXML(container),ex instanceof NoSuchFileException ? null:ex);
            return;
        }

        StringBuilder xml=new StringBuilder();
        for (String l:lines) {
            xml.append(l);
        }

        // Unmarshal data XML into POJO
        try {
            XStream xstream=new XStream();
            container=(ContainerEntity)xstream.fromXML(xml.toString());
        }
        catch(Exception e) {
            log.error("Unable to load and parse XML file ["+datafile+"]",e);
            container=null;
        }

        if (container!=null) {
            container.sortProposals();
        }
    }


    /**
     * Work done in...
     * @see Datastore#init(java.lang.String)
     */
    @PostConstruct
    public void postConstruct() {
    	log.info("Start-up Datastore");
    	kickStart();
        // NOP
    }


    /**
     * Store shutdown.
     */
    @PreDestroy
    public void preDestroy() {
    }


    /**
     * Creates and writes an example database file into the data store directory.
     * In case of error, we just continue hoping that the next write operation succeeds.
     * Invoked to create a demo file when no database is found.
     */
    private void createAndWriteExampleDatabase() {

        container=new ContainerEntity();

        ProposalEntity proposal=new ProposalEntity();
        proposal.setComment("Example Comment");
        proposal.setProposalDate(new Date());
        proposal.setSummary("Example Summary");
        proposal.setTitle("Example Title");
        proposal.setType(ProposalTypes.LIGHTNING_TALK);

        container.addProposal(proposal);

        try {
            writeDatafile();
        }
        catch (SystemException e) {
            log.error("Unable to create fresh database file. Nothing more I can do. Sorry pal!",e);
        }
    }


    /**
     * Updates the data file, which means just re-write a new version to disk.
     */
    public void writeDatafile() {

    	String datafile=configurationRepository.getDatastoreFile();
    	XStream xstream=new XStream();

    	try {
            String xml=xstream.toXML(container);
            Path path=Paths.get(datafile);
            Files.write(path,xml.getBytes(),StandardOpenOption.TRUNCATE_EXISTING,StandardOpenOption.CREATE);
        }
        catch(IOException e) {
             log.error("Trouble writing the database file ["+datafile+"]",e);
             throw new DatastoreFailureException("",e);
        }
    	catch(XStreamException xe) {
            log.error("Trouble serialising the database ["+datafile+"]",xe);
            throw new DatastoreFailureException("Trouble serialising the database",xe);
    	}
    }


    /**
     * Returns a the Proposals data container. Returns null when the container
     * is requested before the data store has been kick started or the data
     * store has not been opened.
     */
    public ContainerEntity getProposalsContainer() {
        return container;
    }
}
