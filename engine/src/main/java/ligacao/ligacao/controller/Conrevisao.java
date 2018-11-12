package ligacao.ligacao.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.pattern.parser.Parser;
import ligacao.ligacao.model.Modelos;
import ligacao.ligacao.model.Revisao;
import ligacao.ligacao.service.Interrevisoes;

@RestController
public class Conrevisao {
	
	@Autowired
	Interrevisoes interrevisoes;
	
	
	
	@RequestMapping(value="/addrevisao", method=RequestMethod.POST)
	 public Revisao addrevisao(@RequestBody Revisao re) {
		
		String matricula=re.getMatricula();
		String nome_marca=re.getNome_marca();
		String nome_modelo=re.getNome_modelo();
		String username=re.getUsername();
		String custos=re.getCustos();
		String km=re.getKm();
		String agua=re.getAgua();
		String oleo=re.getOleo();
		String filtro_combustivel=re.getFiltro_combustivel();
		String filtro_ar=re.getFiltro_ar();
		String filtro_arcondicionado=re.getFiltro_arcondicionado();
		String luzes=re.getLuzes();
		String pneus=re.getPneus();
		String travoes=re.getTravoes();
		String alenhamento=re.getAlenhamento();
		String eletrica=re.getEletrica();
		String descricao_prob=re.getDescricao_prob();
		String ano=re.getAno();
		String mes=re.getMes();
		String dia=re.getDia();
		String imagem=re.getImagem();
		
	
		interrevisoes.save(re); 	
		return re;
	}
	
	
	@RequestMapping(value="/lista/revisoes", method=RequestMethod.GET)
	public ResponseEntity<List<Revisao>> listarevisoes(@RequestParam("matricula") String matricula) {
		ArrayList<Revisao> arrevisao = new ArrayList<>();
		
		
			for(Revisao r:interrevisoes.findAll()) {
				if( r.getMatricula().contains(matricula)) {
					arrevisao.add(r);
				}
			/*	else {
					arrevisao.add(new Revisao(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null));
				}*/
			}
		
		return new ResponseEntity<>(arrevisao, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/lista/revisoes/user", method=RequestMethod.GET)
	public ResponseEntity<List<Revisao>> listarevisaouser(@RequestParam("username") String username) {
		ArrayList<Revisao> arrevisao = new ArrayList<>();
		
			for(Revisao r:interrevisoes.findAll()) {
				if( r.getUsername().contains(username)) {
					arrevisao.add(r);
				}
				
			}
		
		return new ResponseEntity<>(arrevisao, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/time", method=RequestMethod.GET)
	public ResponseEntity<List<Revisao>> play(@RequestParam("username") String username) {
		ArrayList<Revisao> arre=new ArrayList<>();
		
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		//System.out.println(dateFormat.format(date)); 
		System.out.println(date);
		String ola="";
		
		
		for(Revisao r :interrevisoes.findAll()) {
			if(r.getUsername().equals(username)) {
				
			
			
	
					
				Calendar calendar1 = new GregorianCalendar();
		
				calendar1.setTime(date);
		
				int year1 = calendar1.get(Calendar.YEAR);
			
				int month1 = calendar1.get(Calendar.MONTH)+1;
				int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
			
				
		
			
					System.out.println("MES1 " + month1);
				
					String ano2 =r.getAno();
					String mes2=r.getMes();
					String dia2 = r.getDia();
					
					int ano11= Integer.valueOf(ano2);
					int mes11= Integer.valueOf(mes2);
					int dia11= Integer.valueOf(dia2);
					
					if(ano11>=year1) {
					

						if(month1<mes11 || month1>mes11) {
							System.out.println("MES1 " + month1);
							System.out.println("MES2 " + mes11);
							if(mes11-month1==1 || month1-mes11==11) {
								
								
								
								
								if(dia11==day1 ) {
								
									System.out.println("day1 " + day1);
									System.out.println("day2 " + dia11);
									System.out.println("FAZER");
									arre.add(r);
								}
							}
							else {
								ola="NAO";
							}
							
						}
						
					}
			}

			}	
		
		return new ResponseEntity<>(arre, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/delete/revisoes", method=RequestMethod.GET) 
	public String delete(@RequestParam("matricula") String matricula) {
		String ola="";
		
			for(Revisao r:interrevisoes.findAll()) {
				
				if(r.getMatricula().compareTo(matricula)==0) {
					
					interrevisoes.delete(r);
					ola="eliminado";
				}
				else {
					ola="fora";
				}
				
			
			}
		
		return ola;
	}

}
