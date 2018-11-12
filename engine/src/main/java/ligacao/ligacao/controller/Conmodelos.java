package ligacao.ligacao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation.EmptyArraysBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ligacao.ligacao.model.Marca;
import ligacao.ligacao.model.Modelos;
import ligacao.ligacao.service.Intermodelos;

@RestController
public class Conmodelos {

	
	@Autowired
	Intermodelos intermodelos;
	
	@RequestMapping(value="/addmodelo", method=RequestMethod.POST)
	 public Modelos addmodelo(@RequestBody Modelos ma) {
		
		String id=ma.getId();
		String imagem_modelo=ma.getImagem_modelo();
		String nome_marca=ma.getNome_marca();
		String nome_modelo=ma.getNome_modelo();
		String username=ma.getUsername();
		String nr_portas=ma.getNr_portas();
		String combustivel=ma.getCombustivel();
		String consumo=ma.getConsumo();
		String potencia=ma.getPotencia();
		String matricula=ma.getMatricula();
		String motor=ma.getMotor();
		
		int estado=0;
		for(Modelos mm:intermodelos.findAll()) {
			if(mm.getMatricula().compareTo(matricula)==0) {
				estado++;
			}
		}
		if(estado==0) {
			intermodelos.save(ma); 
		}
		
		//intermodelos.save(ma); 	
		return ma;
	}
	
	@RequestMapping(value="/lista/modelos", method=RequestMethod.GET)
	public ResponseEntity<List<Modelos>> listamodelo(@RequestParam("nome_marca") String nome_marca) {
		ArrayList<Modelos> armodelos = new ArrayList<>();
		String oi="nada";
		
			for(Modelos m:intermodelos.findAll()) {
				if( m.getUsername().contains(oi) && m.getNome_marca().contains(nome_marca)) {
					armodelos.add(m);
				}
				
			}
		
		return new ResponseEntity<>(armodelos, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/addmodelo/update", method=RequestMethod.POST)
	 public Modelos updatemodelo(@RequestBody Modelos ma) {
		
		String id=ma.getId();
		String imagem_modelo=ma.getImagem_modelo();
		String nome_marca=ma.getNome_marca();
		String nome_modelo=ma.getNome_modelo();
		String username=ma.getUsername();
		String nr_portas=ma.getNr_portas();
		String combustivel=ma.getCombustivel();
		String consumo=ma.getConsumo();
		String potencia=ma.getPotencia();
		String matricula=ma.getMatricula();
		String motor=ma.getMotor();
		
		
		intermodelos.save(ma); 	
		return ma;
	}
	
	
	@RequestMapping(value="/lista/modelos/user", method=RequestMethod.GET)
	public ResponseEntity<List<Modelos>> listamodelouser(@RequestParam("username") String username) {
		ArrayList<Modelos> armodelos = new ArrayList<>();
			String oi="nada";
		
			for(Modelos m:intermodelos.findAll()) {
				
				if(m.getUsername().contains(username) && (m.getMatricula().compareTo(oi)!=0) ){
					armodelos.add(m);
				}
				
				/*if(m.getUsername().contains(username) ){
					if(m.getMatricula().compareTo(oi)!=0)
					armodelos.add(m);
				}*/
				
			}
		
		return new ResponseEntity<>(armodelos, HttpStatus.OK);
	}
	
	@RequestMapping(value="/busca/matricula", method=RequestMethod.GET) //lista modelos
	public ResponseEntity<List<Modelos>> busca(@RequestParam("matricula") String matricula) {
		ArrayList<Modelos> armodelos = new ArrayList<>();
			Modelos fake=new Modelos(null, null, null, null, null, null, null, null, null, null, null);
			
			boolean estado=false;
			for(Modelos m:intermodelos.findAll()) {
				
				if(m.getMatricula().compareTo(matricula)==0) {
					armodelos.add(m);
					estado = true;
					break;
					
				}
			
			}
			
			if(estado == false) {
				armodelos.add(fake);
			}
			else {
				estado = false;
			}
		
		return new ResponseEntity<>(armodelos, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/delete/modelos", method=RequestMethod.GET) 
	public ResponseEntity<List<Modelos>> delete(@RequestParam("matricula") String matricula) {
		ArrayList<Modelos> armodelos = new ArrayList<>();
		
			for(Modelos m:intermodelos.findAll()) {
				
				if(m.getMatricula().compareTo(matricula)==0) {
					//armodelos.add(m);
					intermodelos.delete(m);
				}
				else {
					armodelos.add(m);
				}
			
			}
		
		return new ResponseEntity<>(armodelos, HttpStatus.OK);
	}
	
	
}
