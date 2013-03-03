package eu.lucubratory.topro1.common.exception.validation;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Container exception to transport validation issues between actors.
 */
public class ValidationException extends Exception {

    private static final long serialVersionUID = -763562872139733063L;

    List<ValidationIssue> issues=new ArrayList<>();

    public ValidationException() {
        super();
    }

    public ValidationException(ValidationIssue i) {
        super();
        issues.add(i);
    }

    public List<ValidationIssue> getIssues() {
        return  ImmutableList.copyOf(issues);
    }
}
