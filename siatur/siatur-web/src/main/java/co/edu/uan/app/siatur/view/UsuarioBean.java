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

import co.edu.uan.app.siatur.model.entity.Usuario;
import co.edu.uan.app.siatur.model.pojo.Constantes;
import co.edu.uan.app.siatur.model.service.UsuarioService;
import co.edu.uan.app.siatur.util.FacesUtils;


@ManagedBean(name = UsuarioBean.BEAN_NAME, eager = true)
@SessionScoped
public class UsuarioBean   implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String BEAN_NAME = "usuarioBean";
	private static final Logger logger = LoggerFactory.getLogger(UsuarioBean.class);
	

	@EJB
	UsuarioService usuarioService;
	private Usuario usuario;
	private List<Usuario> listUsuario;
	private boolean visiblePopup;

	private String headerDialog;
	
	
	@PostConstruct
	public void init() {
		this.listUsuario = null;
		this.usuario = null;
		this.visiblePopup = false;
		this.headerDialog = "";
	}
	
	private void openPopup() {
		this.visiblePopup = true;
	}
	
	
	public List<Usuario> getUsuarioAll() {
		this.listUsuario = usuarioService.getAll();
		return this.listUsuario;
	}
	
	
	

	public void addUsuario(ActionEvent event) {
		logger.info("Entro a addUsuario(event:" + event + ")");

		this.usuario = new Usuario();
		this.usuario.setClave("");
		this.usuario.setUsuario("");
		this.usuario.setNombre("");
		this.usuario.setApellido("");;
		this.usuario.setDireccion("");
		this.usuario.setIdentificacion("");
		this.usuario.setCorreo("");
		this.usuario.setEstado(Constantes.ESTADO_ACTIVO);

		this.headerDialog = "Nuevo Usuario";
		this.openPopup();

		logger.info("Saliendo de addUsuario(usuario:" + usuario + ")");

	}
	
	
	
	public void saveAction() {
		logger.info("Entró a saveAction(ActionEvent event)");

		if (validateSaveAction()) {

			try {
				usuarioService.save(this.usuario);
				this.getUsuarioAll();

			} catch (Exception e) {
				FacesUtils.addMessageError("Guardar Usuario", "Error al guardar el Usuario", e.getMessage());
				logger.error("Error al guardar Usuario. "+e.getMessage());
			}
		}

		logger.info("Saliendo de saveAction()");
	}
	
	
	
	
	private boolean validateSaveAction() {
		logger.info("Entró a validateSaveAction()");

		boolean valid = true;
		String detail = "";

		if (this.usuario == null) {
			detail = "No existe un objeto USUARIO inicializado";
			valid = false;
		} else if (StringUtils.isBlank(this.usuario.getNombre())) {
			detail = "Se debe ingresar el nombre del usuario";
			valid = false;
		}

		if (!valid) {
			FacesUtils.addMessageError("Guardar Usuario", "Error al guardar el Usuario", detail);
			logger.error("Error validando el usuario a guardar. "+detail);
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
	
	
	
	public Usuario getUsuario() {

		logger.info("this.usuario = " + this.usuario);
		if (this.usuario != null)
			logger.info("this.usuario.getNombre() = " + this.usuario.getNombre());

		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	public boolean isVisiblePopup() {
		return visiblePopup;
	}

	public void setVisiblePopup(boolean visiblePopup) {
		this.visiblePopup = visiblePopup;
	}

}

