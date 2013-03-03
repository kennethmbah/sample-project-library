package eu.lucubratory.topro1.domain.topic;

import eu.lucubratory.topro1.common.exception.persistence.DatastoreFailureException;
import eu.lucubratory.topro1.common.exception.validation.ValidationException;
import eu.lucubratory.topro1.domain.persistence.Datastore;
import eu.lucubratory.topro1.domain.persistence.entity.ProposalEntity;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service EJB handling operations on Topic proposal data.
 * <p/>
 * Services are business logic EJBs that are executed inside of a
 * transaction open by a Facade. Services handle business logic that is too
 * broad to be placed in concerned Entities directly.
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
//
public class TopicService {

    private static Logger log=LoggerFactory.getLogger(TopicService.class);

    @Inject Datastore datastore;


    /**
     * Returns a sorted immutable copy of the Proposals list.
     */
    public List<ProposalEntity> getProposals() {
        return datastore.getProposalsContainer().getProposals();
    }


    /**
     * Validates and adds a new topic proposal record to the datastore.
     */
    public void newTopic(ProposalEntity topic) throws ValidationException, DatastoreFailureException {

        topic.setProposalDate(new Date());

        // Validate the parts of the data that can be validated by the Entity
        // without reference to other things.
        topic.validate();

        // Validate the parts of the data that needs more complex interactions.
        validate(topic);

        // Create new topic and update the data store (data file).
        datastore.getProposalsContainer().addProposal(topic);
        datastore.writeDatafile();
    }


    /**
     * Performs complex validations on ProposalEntities.
     */
    public void validate(ProposalEntity topic) throws ValidationException {
        // Later
    }
}
