package ligacao.ligacao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ligacao.ligacao.model.User;
import ligacao.ligacao.service.Interuser;


@RestController
public class Conuser {

	
	@Autowired
	Interuser interuser;
	

	
	
	@RequestMapping(value="/registo", method=RequestMethod.POST)
	 public String registo(@RequestBody User log) {
		String nome=log.getNome();
		String contacto=log.getContacto();
		String email=log.getEmail();
		String idade=log.getIdade();
		String username=log.getUsername();
		String password=log.getPassword();
		
		
		int estado=0;
		
		for(User l: interuser.findAll()) {	
			if(l.getUsername().compareTo(username)==0) {
				estado++;	
				return l.getUsername().toString();
			}
			if( l.getEmail().compareTo(email)==0) {
				estado++;	
				return l.getEmail().toString();
			}
		}
		if(estado==0) {
			interuser.save(log);		
		}
		
		return "Inserido com sucesso ";
	}
	
	
	
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ResponseEntity<List<User>> log(@RequestParam("username") String username,@RequestParam("password") String password) {
		ArrayList<User> arlogin = new ArrayList<>();
		for(User l:interuser.findAll()) {	
			if(l.getUsername().equals(username) && l.getPassword().equals(password))
			{
				arlogin.add(l);
			}
		}
		return new ResponseEntity<List<User>>(arlogin, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/lista", method=RequestMethod.GET)
	public ResponseEntity<List<User>> lista() {
		ArrayList<User> arlogin = new ArrayList<>();
		for(User l:interuser.findAll()) {
			arlogin.add(l);
		}
		
		return new ResponseEntity<>(arlogin, HttpStatus.OK);
	}
	
	//TIPO EDITE
/*	@RequestMapping(value="/adduser", method=RequestMethod.POST)
	 public ResponseEntity<User> addusermrca( @RequestBody User log) {
		String id=log.getId();
		String nome=log.getNome();
		String password=log.getPassword();
		String marca=log.getMarca();
			
		for(User l:interuser.findAll()) {
			if(l.getId().compareTo(id)==0) {
				interuser.save(log);	
			}
		}
		//interlogin.save(log); 
		
		return null;
	}*/
	
	
	//TIPO BUSCA POR MARCA
/*	@RequestMapping(value="/busca", method=RequestMethod.GET)
	public ResponseEntity<List<User>> listamarca(@RequestParam("id") String id, @RequestParam("marca") String marca ) {
		ArrayList<User> arlogin = new ArrayList<>();
		
		
		//Dinamico algo mal estatico ok
		for(User lo:interuser.findAll()) {
			if(lo.getId().compareTo(id)==0 && lo.getMarca().startsWith(marca)) {
				
						arlogin.add(lo);
								
			}
		}
		

		return new ResponseEntity<List<User>>(arlogin, HttpStatus.OK);
	}*/
	
	
	
	//ESTA BOM
	/*@RequestMapping(value="/add", method=RequestMethod.POST)
	public Login adduser(@RequestParam String nome, @RequestParam String password) {
		
		Login log=new Login();
		
		log.setNome(nome);
		log.setPassword(password);
		interlogin.save(log);
		
		return log;
		
	}*/
	//ESTA BOM MUDAR ID no MODULO PARA OBEJECTID
	/* @RequestMapping(value = "/", method = RequestMethod.POST)
	  public Login createlogin(@Valid @RequestBody Login log) {
	    log.set_id(ObjectId.get());
	    interlogin.save(log);
	    return log;
	  }*/
	
	  

	
}
