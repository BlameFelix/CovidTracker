package de.fh.albsig0.resources;

import org.springframework.web.servlet.ModelAndView;

public class CovidExceptions extends ModelAndView {
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
