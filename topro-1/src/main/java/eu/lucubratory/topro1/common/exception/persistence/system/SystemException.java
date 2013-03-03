package eu.lucubratory.topro1.common.exception.persistence.system;


/**
 * General (unexpected) system exception.
 */
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -7656221333916L;

    public SystemException() {
        super();
    }

    public SystemException(String msg,Throwable t) {
        super(msg,t);
    }
}
