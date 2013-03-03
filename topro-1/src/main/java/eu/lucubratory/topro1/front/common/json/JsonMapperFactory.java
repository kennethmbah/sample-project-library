package eu.lucubratory.topro1.front.common.json;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Factory bound to the @JsonMapper qualifier to inject a customised Jackson
 * Object Mapper having application specific serialisers installed.
 * <p/>
 * According to the Jackson doc, the object mapper is omnipotent and thread-safe,
 * so we keep just one instance.
 */
public class JsonMapperFactory implements Serializable {

    private static Logger log=LoggerFactory.getLogger(JsonMapperFactory.class);

    private static ObjectMapper customisedJsonMapper=null;
    private static ObjectMapper rawJsonMapper=null;

    /**
     *
     */
    public @Produces @JsonMapper ObjectMapper getJsonMapper(InjectionPoint ip) {

        if (customisedJsonMapper==null) {
            synchronized(JsonMapperFactory.class) {
                if (customisedJsonMapper==null) {

                    SimpleModule myModule = new SimpleModule("BRUJUG-TOVO",new Version(1,0,0,null));
                    myModule.addSerializer(new ProposalTypeSerializer()); // serializer declares correct class to bind to

                    customisedJsonMapper=new ObjectMapper();
                    customisedJsonMapper.registerModule(myModule);
                }
            }
        }

        log.debug("Inject JSON mapper:"+customisedJsonMapper);

        return customisedJsonMapper;
    }


    /**
     *
     */
    public @Produces @RawJsonMapper ObjectMapper getRawJasonMapper(InjectionPoint ip) {

        if (rawJsonMapper==null) {
            synchronized(JsonMapperFactory.class) {
                if (rawJsonMapper==null) {
                    rawJsonMapper=new ObjectMapper();
                }
            }
        }

        log.debug("Inject RAW JSON mapper:"+rawJsonMapper);

        return rawJsonMapper;
    }
}