package ligacao.ligacao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ligacao.ligacao.model.Marca;
import ligacao.ligacao.service.Intermarca;

@RestController
public class Conmarca {

	@Autowired
	Intermarca intermarca;
	
	
	@RequestMapping(value="/addmarca", method=RequestMethod.POST)
	 public Marca addmarca(@RequestBody Marca ma) {
		
		
		String logo=ma.getLogo();
		String nome=ma.getNome();
		String username=ma.getUsername();
		intermarca.save(ma); 	
		return ma;
	}
	
	
	@RequestMapping(value="/addmarca/user", method=RequestMethod.POST)
	 public Marca addmarcauser(@RequestBody Marca ma) {
		
		
		String logo=ma.getLogo();
		String nome=ma.getNome();
		String username=ma.getUsername();
		
	
		intermarca.save(ma); 	
		return ma;
	}
	
	
/*	@RequestMapping(value="/lista/marca", method=RequestMethod.GET)
	public ResponseEntity<List<Marca>> listamarca() {
		ArrayList<Marca> armarca = new ArrayList<>();
		for(Marca m:intermarca.findAll()) {
			armarca.add(m);
		}
		
		return new ResponseEntity<>(armarca, HttpStatus.OK);
	}*/
	
	
	@RequestMapping(value="/lista/marca", method=RequestMethod.GET)
	public ResponseEntity<List<Marca>> listamarca() {
		ArrayList<Marca> armarca = new ArrayList<>();
		String oi="nada";
		
			for(Marca m:intermarca.findAll()) {
				if( m.getUsername().contains(oi)) {
					armarca.add(m);
				}
				
			}
		
		return new ResponseEntity<>(armarca, HttpStatus.OK);
	}
	
	@RequestMapping(value="/lista/marca/user", method=RequestMethod.GET)
	public ResponseEntity<List<Marca>> listamarcauser(@RequestParam("username") String username) {
		ArrayList<Marca> armarca = new ArrayList<>();
		
			for(Marca m:intermarca.findAll()) {
				/*if( m.getUsername()=="Hugo") {
					armarca.add(m);
				}*/
				if(m.getUsername().contains(username)) {
					armarca.add(m);
				}
				
			}
		
		return new ResponseEntity<>(armarca, HttpStatus.OK);
	}
	
	
	
}
