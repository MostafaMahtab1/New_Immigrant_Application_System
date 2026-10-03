// Contributor - Mostafa Mahtab
// Helps with throwing error codes.
package edu.gmu.cs321;

public class ImmigrantException extends RuntimeException {
    private final ErrorCode code;

    public ImmigrantException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}
