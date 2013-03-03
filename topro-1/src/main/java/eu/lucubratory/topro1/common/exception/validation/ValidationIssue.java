package eu.lucubratory.topro1.common.exception.validation;


/**
 * Describes a validation issues, including code and additional parameters.
 */
public class ValidationIssue {

    private IssueCode issueCode;

    public ValidationIssue(IssueCode issueCode) {
        this.issueCode=issueCode;
    }

    public IssueCode getIssueCode() {
        return issueCode;
    }

}
