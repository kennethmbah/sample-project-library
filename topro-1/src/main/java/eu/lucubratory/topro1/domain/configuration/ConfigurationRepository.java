package eu.lucubratory.topro1.domain.configuration;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton configuration management facility.
 */
@Singleton
@Startup
public class ConfigurationRepository {

    static public final String BEANNAME="ConfigurationRepository";

    private final static Logger log=LoggerFactory.getLogger(ConfigurationRepository.class);

    static private final String DATASTORE_FILENAME="topro-ds.xml";

    private String datastoreFile=null;

    /**
     *
     */
    @PostConstruct
    public void postConstruct() {

    	log.info("Start-up ConfigurationRepository");

    	String dir=System.getProperty("user.dir");
        // In GF3, same as: (new File(".").getAbsolutePath())

        if (!dir.endsWith(String.valueOf(File.separatorChar))) {
            dir+=String.valueOf(File.separatorChar);
        }
        dir+="datastore";

        if ( ((new File(dir)).mkdir())==true) {
            log.info("Created new data store directory: {}",dir);
        }

        log.info("Data store directory is: {}",dir);

        setDatastoreDirectory(dir);
    }

    /**
     *
     */
    public void setDatastoreDirectory(String directory) {
        setDatastoreFile(directory);
    }

    /**
     *
     */
    public void setDatastoreFile(String directory) {

        datastoreFile=directory+File.separatorChar+DATASTORE_FILENAME;
    }


    public String getDatastoreFile() {
        return datastoreFile;
    }

}
