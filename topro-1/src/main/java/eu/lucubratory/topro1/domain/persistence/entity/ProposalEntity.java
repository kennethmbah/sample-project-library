package eu.lucubratory.topro1.domain.persistence.entity;

import eu.lucubratory.topro1.common.exception.validation.ValidationException;

import java.util.Date;


/**
 * Holds one single topic proposal record.
 */
public class ProposalEntity {

    private String title;
    private String summary;
    private String comment;
    private ProposalTypes type;
    private Date proposalDate;

    public void validate() throws ValidationException {
        // Validate the parts of the data that can be validated by the Entity
        // without reference to other things.
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ProposalTypes getType() {
        return type;
    }

    public void setType(ProposalTypes type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getProposalDate() {
        return proposalDate;
    }

    public void setProposalDate(Date proposalDate) {
        this.proposalDate = proposalDate;
    }


}
