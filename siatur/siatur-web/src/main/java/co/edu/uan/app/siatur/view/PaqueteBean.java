package co.edu.uan.app.siatur.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.uan.app.siatur.model.entity.Paquete;
import co.edu.uan.app.siatur.model.service.PaqueteService;
import co.edu.uan.app.siatur.util.FacesUtils;

@ManagedBean(name = PaqueteBean.BEAN_NAME, eager = true)
@CustomScoped(value = "#{window}")
public class PaqueteBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final String BEAN_NAME = "paqueteBean";
	public static final String PAGE_NAME = "gestionar_paquetes";
	private static final Logger logger = LoggerFactory.getLogger(PaqueteBean.class);
	
	
	@EJB
	PaqueteService paqueteService;
	private Paquete paquete;
	private List<Paquete> listPaquete;
	private boolean visiblePopup;

	private String headerDialog;
	
	
	@PostConstruct
	public void init() {
		this.listPaquete = null;
		this.paquete = null;
		this.visiblePopup = false;
		this.headerDialog = "";
	}
	
	private void openPopup() {
		this.visiblePopup = true;
	}
	
	private void closedPopup() {
		this.visiblePopup = false;
	}
	
	
	public List<Paquete> getPaqueteAll() {
		this.listPaquete = paqueteService.getAll();
		return this.listPaquete;
	}
	
	
	

	public void addPaquete(ActionEvent event) {
		logger.info("Entro a addPaquete(event:" + event + ")");

		this.paquete = new Paquete();
		this.paquete.setEditable(Boolean.TRUE);
	    this.paquete.setVersion(1);
		this.paquete.setCiudad("");
		this.paquete.setHotel("");
		this.paquete.setComidas("");
		this.paquete.setPrecio(0.0);
		this.paquete.setSitios("");
		

		this.headerDialog = "Nuevo Paquete";
		this.openPopup();

		logger.info("Saliendo de addPaquete(paquete:" + paquete + ")");

	}
	
	
	
	public String saveAction() {
		logger.info("Entró a saveAction(ActionEvent event)");

		if (validateSaveAction()) {

			try {
				paqueteService.save(this.paquete);
				this.getPaqueteAll();
				this.closedPopup();
				
			} catch (Exception e) {
				FacesUtils.addMessageError("Guardar Paquete", "Error al guardar el Paquete", e.getMessage());
				logger.error("Error al guardar Paquete. "+e.getMessage());
			}
		}

		logger.info("Saliendo de saveAction()");
		return PAGE_NAME;
	}
	
	
	
	
	private boolean validateSaveAction() {
		logger.info("Entró a validateSaveAction()");

		boolean valid = true;
		String detail = "";

		if (this.paquete == null) {
			detail = "No existe un objeto Paquete inicializado";
			valid = false;
			
		} else if (StringUtils.isBlank(this.paquete.getCiudad())) {
			detail = "Se debe ingresar la ciudad del paquete";
			valid = false;
		}

		if (!valid) {
			FacesUtils.addMessageError("Guardar paquete", "Error al guardar el paquete", detail);
			logger.error("Error validando el paquete a guardar. "+detail);
		}

		logger.info("Saliendo de validateSaveAction()");
		return valid;
	}
	
	
	
	public void editPaquete(ActionEvent event) {
		logger.info("Entro a editPaquete(event:" + event + ")");
		
        UIComponent tmpComponent = event.getComponent();
        while (null != tmpComponent && !(tmpComponent instanceof UIData)) {
            tmpComponent = tmpComponent.getParent();
        }
        if (tmpComponent != null && (tmpComponent instanceof UIData)) {
            Object tmpRowData = ((UIData) tmpComponent).getRowData();
            if (tmpRowData instanceof Paquete) {
            	this.paquete = (Paquete) tmpRowData;
            }
        }

		this.headerDialog = "Editar Paquete";
		this.openPopup();

		logger.info("Saliendo de editUsuario(Usuario:" + paquete + ")");

	}
	
	public String cancelAction() {
		logger.info("Entró a cancelAction()");

		this.closedPopup();

		logger.info("Saliendo de cancelAction()");
		return PAGE_NAME;
	}
	
	
	
	public String getHeaderDialog() {
		return this.headerDialog;
	}

	public void setHeaderDialog(String headerDialog) {
		this.headerDialog = headerDialog;
	}
	
	
	public Paquete getPaquete() {

		logger.info("this.paquete = " + this.paquete);
		if (this.paquete != null)
			logger.info("this.usuario.getCiudad() = " + this.paquete.getCiudad());

		return this.paquete;
	}

	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}
	
	
	
	public boolean isVisiblePopup() {
		return visiblePopup;
	}

	public void setVisiblePopup(boolean visiblePopup) {
		this.visiblePopup = visiblePopup;
	}
	
	
	public void setPkPaquete(long id){
		if(this.paquete != null){
			this.paquete.setId(id);
		}
	}
	
	public long getPkUsuario(){
		long id = 0;
		if(this.paquete != null){
			id = this.paquete.getId();
		}
		
		return id;
	}
	
	
	public void setCiudadPaquete(String nombre){
		if(this.paquete != null){
			this.paquete.setCiudad(nombre);
		}
	}
	
	public String getCiudadPaquete(){
		String nombre = "";
		if(this.paquete != null){
			nombre = this.paquete.getCiudad();
		}
		
		return nombre;
	}
	
	
	public void setHotelPaquete(String hotel){
		if(this.paquete != null){
			this.paquete.setHotel(hotel);
		}
	}
	
	public String getHotelPaquete(){
		String hotel = "";
		if(this.paquete != null){
			hotel = this.paquete.getHotel();
		}
		
		return hotel;
	}
	
	public void setComidasPaquete(String comidas){
		if(this.paquete != null){
			this.paquete.setComidas(comidas);
		}
	}
	
	public String getComidasPaquete(){
		String comidas = "";
		if(this.paquete != null){
			comidas = this.paquete.getComidas();
		}
		
		return comidas;
	}
	
	
	public void setPrecioPaquete(double precio){
		if(this.paquete != null){
			this.paquete.setPrecio(precio);
		}
	}
	
	public double getPrecioPaquete(){
		double paquete = 0.0;
		if(this.paquete != null){
			paquete = this.paquete.getPrecio();
		}
		
		return paquete;
	}
	
	
	public void setSitiosPaquete(String sitios){
		if(this.paquete != null){
			this.paquete.setSitios(sitios);
		}
	}
	
	public String getSitiosPaquete(){
		String sitios = "";
		if(this.paquete != null){
			sitios = this.paquete.getSitios();
		}
		
		return sitios;
	}
	
	
	
	public void closeFAjax(AjaxBehaviorEvent event){
        this.visiblePopup=false;
    }



}
