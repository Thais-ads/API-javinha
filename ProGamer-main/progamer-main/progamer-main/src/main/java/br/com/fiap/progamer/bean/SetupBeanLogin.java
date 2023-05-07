package br.com.fiap.progamer.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.fiap.progamer.dao.LoginDao;
import br.com.fiap.progamer.model.LoginModel;

@Named
@RequestScoped
public class SetupBeanLogin {
	LoginModel LoginModel = new LoginModel();
	
	@Inject
	private LoginDao LoginDao;
	
	private LoginModel selectedLogin;
	
	
	public LoginModel getLoginModel() {
		return LoginModel;
	}

	public void setLoginModel(LoginModel LoginModel) {
		this.LoginModel = LoginModel;
	}

	@Transactional
	public void save() {
		if(this.LoginModel.getName()!="" && this.LoginModel.getEmail()!="") {
			LoginDao.save(this.LoginModel);
			this.LoginModel = new LoginModel();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"As informações foram salvas com sucesso!","INFO"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro ao tentar salvar!","ERROR"));
		}
	}
	
	public List<LoginModel> findAll(){
		return this.LoginDao.findAll();
	}

	public LoginModel getSelectedSetup() {
		return selectedLogin;
	}

	public void selectedLogin(LoginModel selectedLogin) {
		this.selectedLogin = selectedLogin;
	}
	
	
	public static void main(String[] args) {
		List<LoginModel> logins = new ArrayList<>();
		
		for(LoginModel login : logins) {
			System.out.println(login.getName());
		}
	}
}
