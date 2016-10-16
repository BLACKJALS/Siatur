package co.edu.uan.app.siatur.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.uan.app.siatur.model.entity.Req;
import co.edu.uan.app.siatur.model.pojo.Constantes;
import co.edu.uan.app.siatur.model.service.ReqService;
import co.edu.uan.app.siatur.util.FacesUtils;

@ManagedBean(name = ReqBean.BEAN_NAME, eager = true)
@SessionScoped
public class ReqBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String BEAN_NAME = "reqBean";
	public static final String PAGE_NAME = "gestionar_requisitos";
	private static final Logger logger = LoggerFactory.getLogger(ReqBean.class);

	@EJB
	ReqService reqService;

	private Req req;
	private List<Req> listReq;
	private boolean visiblePopup;

	private String headerDialog;

	@PostConstruct
	public void init() {
		this.listReq = null;
		this.req = null;
		this.visiblePopup = false;
		this.headerDialog = "";
	}

	private void openPopup() {
		this.visiblePopup = true;
	}
	private void closedPopup() {
		this.visiblePopup = false;
	}

	public List<Req> getReqAll() {

		this.listReq = reqService.getAll();
		return this.listReq;
	}

	public void addReq(ActionEvent event) {
		logger.info("Entro a addReq(event:" + event + ")");

		this.req = new Req();
		this.req.setVersion(1);
		this.req.setEditable(Boolean.TRUE);
		this.req.setDescripcion("");
		this.req.setNombre("");

		this.headerDialog = "Nuevo Requisito";
		this.openPopup();

		logger.info("Saliendo de addReq(req:" + req + ")");

	}
/*
	public String saveAction() {
		logger.info("Entró a saveAction(ActionEvent event)");

		if (validateSaveAction()) {

			try {
				reqService.save(this.req);
				this.getReqAll();
				this.closedPopup();

			} catch (Exception e) {
				FacesUtils.addMessageError("Guardar Req", "Error al guardar el Req", e.getMessage());
				logger.error("Error al guardar req. "+e.getMessage());
			}
		}

		logger.info("Saliendo de saveAction()");
		return PAGE_NAME;
	}
*/

	private boolean validateSaveAction() {
		logger.info("Entró a validateSaveAction()");

		boolean valid = true;
		String detail = "";

		if (this.req == null) {
			detail = "No existe un objeto Req inicializado";
			valid = false;
		} else if (StringUtils.isBlank(this.req.getNombre())) {
			detail = "Se debe ingresar el nombre del req";
			valid = false;
		}

		if (!valid) {
			FacesUtils.addMessageError("Guardar Req", "Error al guardar el Req", detail);
			logger.error("Error validando el req a guardar. "+detail);
		}

		logger.info("Saliendo de validateSaveAction()");
		return valid;
	}

	public String getHeaderDialog() {
		return this.headerDialog;
	}

	public void setHeaderDialog(String headerDialog) {
		this.headerDialog = headerDialog;
	}

	public Req getReq() {

		logger.info("this.req = " + this.req);
		if (this.req != null)
			logger.info("this.req.getNombre() = " + this.req.getNombre());

		return this.req;
	}

	public void setReq(Req req) {
		this.req = req;
	}

	public boolean isVisiblePopup() {
		return visiblePopup;
	}

	public void setVisiblePopup(boolean visiblePopup) {
		this.visiblePopup = visiblePopup;
	}

}
