package co.edu.uan.app.siatur.util;

import java.security.Principal;

import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FacesUtils {

    private static final Logger logger = LoggerFactory.getLogger(FacesUtils.class);

	
    public static Principal getPrincipal() {

        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
    }
	
}
