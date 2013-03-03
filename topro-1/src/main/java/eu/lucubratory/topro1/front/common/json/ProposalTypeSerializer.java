package eu.lucubratory.topro1.front.common.json;

import eu.lucubratory.topro1.domain.persistence.entity.ProposalTypes;
import eu.lucubratory.topro1.front.common.i18n.Translator;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.std.SerializerBase;


/**
 * Custom type specific Jackson serialiser.
 * @see ProposalTypes
 */
public class ProposalTypeSerializer extends SerializerBase<ProposalTypes> {

    /**
     *
     */
    public ProposalTypeSerializer() {
        super(ProposalTypes.class, true);
    }

    /**
     *
     */
    @Override
    public void serialize(ProposalTypes value, JsonGenerator jgen, SerializerProvider provider)
                                                 throws IOException, JsonGenerationException {
        jgen.writeString(Translator.translate(value));
    }
}
