package eu.lucubratory.topro1.common.exception.persistence;

import eu.lucubratory.topro1.common.exception.persistence.system.SystemException;

/**
 * Failed to write data store to disk.
 */
public class DatastoreFailureException extends SystemException {

    private static final long serialVersionUID = -765628721397333916L;

    public DatastoreFailureException() {
        super();
    }

    public DatastoreFailureException(String msg,Throwable t) {
        super(msg,t);
    }
}
