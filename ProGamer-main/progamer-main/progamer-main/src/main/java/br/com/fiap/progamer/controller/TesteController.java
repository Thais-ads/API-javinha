package br.com.fiap.progamer.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.progamer.dao.SetupDao;
import br.com.fiap.progamer.model.SetupModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;



	
	@RestController
	@RequestMapping("/api/setups")
	@Api(value = "Progamer API")
public class TesteController {

		@Inject
		private SetupDao SetupDao;
		
	
		@GetMapping()
		@ApiOperation("Obter todos os setups")
		public ResponseEntity<List<SetupModel>> index() {
			return ResponseEntity.ok(SetupDao.findAll());
		}
	
@PostMapping
public ResponseEntity <String> create (@RequestBody SetupModel setupRequest) {
		
	
	try {
		SetupModel setup = new SetupModel();
		//fazendo as validaçoes para saber se o que vem do front esta correto para o servidor
		if(setupRequest.getName() == null || setupRequest.getPrice() < 0 || setupRequest.getDescription() == null ||  setupRequest.getId() ==0 ) {
			
			System.out.print("");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}
		
		setup.setName(setupRequest.getName());
		setup.setPrice(setupRequest.getPrice());
		setup.setDescription(setupRequest.getDescription());
		setup.setId(setupRequest.getId());
		
		
		
		//atualizando o objeto instanciado setup e os atributos dentro do setupmodel
		SetupDao.save(setup);
		return ResponseEntity.status(HttpStatus.CREATED).build();
		
	} catch (Exception e){
		e.printStackTrace();
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
	}
		
		
		
		
}		
	@GetMapping("{id}")
	@ApiOperation("Buscar por id ")
	public ResponseEntity<SetupModel> show (@PathVariable ("id") long id ) {
			
		SetupModel setup = SetupDao.buscarPorId(id);
		
		if(setup==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
			
			return ResponseEntity.ok(setup);
	
		
	}

	@PutMapping("{id}")
	@ApiOperation("Atualizaçao de setup")
	public ResponseEntity<String> update (@PathVariable ("id") long id, @RequestBody SetupModel setupRequest) {
		
		
		try {
			
			
			SetupModel setup = SetupDao.buscarPorId(id);
			
			if(setup == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			setup.setName(setupRequest.getName());
			setup.setDescription(setupRequest.getDescription());
			setup.setPrice(setupRequest.getPrice());
			setup.setId(setupRequest.getId());
			
			SetupDao.save(setup);
			
			return ResponseEntity.ok("Atualizado com sucesso!");
		
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@DeleteMapping("{id}")
	@ApiOperation("excluir setup ")
	public ResponseEntity <String> delete (@PathVariable ("id") long id){
		
		try {
			SetupModel setup =SetupDao.buscarPorId(id);
			
			if(setup == null ) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				
			}
			SetupDao.deletar(setup);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	



}
