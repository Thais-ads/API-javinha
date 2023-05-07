package br.com.fiap.progamer.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.fiap.progamer.dao.SetupDao;
import br.com.fiap.progamer.model.SetupModel;

@Named
@RequestScoped
public class SetupBean {

	SetupModel setupModel = new SetupModel();
	
	@Inject
	private SetupDao setupDao;
	
	private SetupModel selectedSetup;
	
	
	public SetupModel getSetupModel() {
		return setupModel;
	}

	public void setSetupModel(SetupModel setupModel) {
		this.setupModel = setupModel;
	}

	@Transactional
	public void save() {
		if(this.setupModel.getName()!="" && this.setupModel.getDescription()!="") {
			setupDao.save(this.setupModel);
			this.setupModel = new SetupModel();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"As informações foram salvas com sucesso!","INFO"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro ao tentar salvar!","ERROR"));
		}
	}
	
	public List<SetupModel> findAll(){
		return this.setupDao.findAll();
	}

	public SetupModel getSelectedSetup() {
		return selectedSetup;
	}

	public void setSelectedSetup(SetupModel selectedSetup) {
		this.selectedSetup = selectedSetup;
	}
	
	
	public static void main(String[] args) {
		List<SetupModel> setups = new ArrayList<>();
		
		for(SetupModel setup : setups) {
			System.out.println(setup.getName());
		}
	}
	
}
