package eu.lucubratory.topro1.domain.topic;

import eu.lucubratory.topro1.common.exception.persistence.DatastoreFailureException;
import eu.lucubratory.topro1.common.exception.validation.ValidationException;
import eu.lucubratory.topro1.domain.persistence.entity.ProposalEntity;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.inject.Inject;

/**
 * Service Facade EJB for Topics related operations.
 * <p/>
 * Facades are thin transaction boundary beans and always start a TX propagated
 * to one or more services. The real power of this architecture becomes clear
 * with more complex business operations, distributed over multiple services.
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//
public class TopicFacade {

    @Inject TopicService topicService;


    public List<ProposalEntity> getProposals() {
        return topicService.getProposals();
    }

    public void newTopic(ProposalEntity topic) throws ValidationException, DatastoreFailureException {
        topicService.newTopic(topic);
    }

}
