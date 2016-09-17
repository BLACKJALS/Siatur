package co.edu.uan.app.siatur.util;

import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;

public class FacesUtils {

    private static final Logger logger = Logger.getLogger(FacesUtils.class.toString());

	
    public static Principal getPrincipal() {

    	logger.log(Level.INFO, "FacesContext.getCurrentInstance() = "+FacesContext.getCurrentInstance());
    	logger.log(Level.INFO, "FacesContext.getCurrentInstance().getExternalContext() = "+FacesContext.getCurrentInstance().getExternalContext());
    	logger.log(Level.INFO, "FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() = "+FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal());
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
    }
	
}
