package eu.lucubratory.topro1.domain.persistence.entity;

import com.google.common.collect.ImmutableList;

import eu.lucubratory.topro1.common.exception.validation.ValidationException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Container pojo surrounding the application data structure.
 */
public class ContainerEntity implements Serializable {

    private static final long serialVersionUID = 3940647793960762303L;

    /** SORTED list of proposals. */
    private List<ProposalEntity> proposals=new ArrayList<>();

    /**
     * 
     */
    public void validate() throws ValidationException {
    }


    /**
     * Returns a sorted immutable copy of the Proposals list.
     */
    public List<ProposalEntity> getProposals() {
        return ImmutableList.copyOf(proposals);
    }


    protected void setProposals(List<ProposalEntity> proposals) {
        this.proposals = proposals;
        sortProposals();
    }


    public void addProposal(ProposalEntity proposal) {
        this.proposals.add(proposal);
        sortProposals();
    }


    public void sortProposals() {
        Collections.sort(proposals,
                 new Comparator<ProposalEntity>() {
                     @Override
                     public int compare(ProposalEntity e1, ProposalEntity e2) {
                         return e1.getTitle().compareTo(e2.getTitle());
                     }
                 });
    }
}
