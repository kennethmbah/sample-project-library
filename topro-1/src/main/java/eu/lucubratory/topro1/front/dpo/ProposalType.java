package eu.lucubratory.topro1.front.dpo;

import eu.lucubratory.topro1.domain.persistence.entity.ProposalTypes;
import eu.lucubratory.topro1.front.common.i18n.Translator;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Presentation Object (DPO) containing a domain proposal type transformed for display.
 */
public class ProposalType {

    private String id;
    private String label;


    /**
     * Static initialiser populating a list with the domain enumeration values
     * and their screen values.
     */
    public static List<ProposalType> init() {
        List<ProposalType> ptypes=new ArrayList<>();
        for (ProposalTypes pt:ProposalTypes.values()) {
            ptypes.add(new ProposalType(pt.name(),Translator.translate(pt)));
        }
        return ptypes;
    }


    /**
     * Initialising constructor.
     */
    public ProposalType(String id,String label) {
        this.id=id;
        this.label=label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


}
