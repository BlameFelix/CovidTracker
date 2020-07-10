package de.fh.albsig0.resources;

public class CovidExceptions extends CovidParent {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public CovidExceptions(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
