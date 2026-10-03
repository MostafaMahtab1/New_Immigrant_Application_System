// Contributor - Shane Birckhead
// Helps with throwing error codes for unit tests.
package edu.gmu.cs321;

public class ImmigrationException extends RuntimeException {
    private final ErrorCode code;

    public ImmigrationException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}
