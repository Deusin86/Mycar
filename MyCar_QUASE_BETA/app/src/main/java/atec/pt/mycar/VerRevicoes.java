package atec.pt.mycar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import net.eunainter.r2std2oid.RequestR2D2;
import net.eunainter.r2std2oid.ResponseR2D2;
import net.eunainter.r2std2oid.RestObserver;
import net.eunainter.r2std2oid.Skyrunner;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import atec.pt.mycar.model.Modelos;
import atec.pt.mycar.util.Webservice;

public class VerRevicoes extends AppCompatActivity implements RestObserver {

    RadioGroup group1,group2,group3,group4,group5,group6,group7,group8,group9,group10;


    EditText marca1,modelo1,matricula1,km1,custos1,descricao_prob1,ano,mes,dia;

    Button introduzir_revisao;

    Skyrunner ask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_revicoes);


        ask=new Skyrunner(this);


        ano=findViewById(R.id.ano_revisao);
        mes=findViewById(R.id.mes_revicao);
        dia=findViewById(R.id.dia_revicao);

        introduzir_revisao=findViewById(R.id.introduzir_revisao);

       // introduzir_revisao.setVisibility(View.GONE);





        group1 = findViewById(R.id.grupo_gp1);
        group2= findViewById(R.id.grupo_gp2);
        group3=findViewById(R.id.grupo_gp3);
        group4=findViewById(R.id.grupo_gp4);
        group5=findViewById(R.id.grupo_gp5);
        group6=findViewById(R.id.grupo_gp6);
        group7=findViewById(R.id.grupo_gp7);
        group8=findViewById(R.id.grupo_gp8);
        group9=findViewById(R.id.grupo_gp9);
        group10=findViewById(R.id.grupo_gp10);


        marca1=findViewById(R.id.marca_revicao);
        modelo1=findViewById(R.id.modelo_revicao);
        matricula1=findViewById(R.id.Matricula_revicao);
        km1=findViewById(R.id.kilometros_revicao);
        custos1=findViewById(R.id.custos_revicoes);
        descricao_prob1=findViewById(R.id.descricao_prob);


        final Modelos modelo=((Appobjecto)getApplication()).getMod();

        marca1.setText(modelo.getNome_marca());
        modelo1.setText(modelo.getNome_modelo());
        matricula1.setText(modelo.getMatricula());



        group1.clearCheck();

        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb_agua = group.findViewById(checkedId);
               /* if (null != rb_agua) {
                    Toast.makeText(VerRevicoes.this, rb_agua.getText(), Toast.LENGTH_SHORT).show();
                }
                */
            }
        });

        group2.clearCheck();

        group2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb_oleo = group.findViewById(checkedId);


            }
        });


        group3.clearCheck();

        group3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb_combustivel = group.findViewById(checkedId);

            }
        });


        group4.clearCheck();

        group4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb_ar = group.findViewById(checkedId);

            }
        });


        group5.clearCheck();

        group5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb_ac = group.findViewById(checkedId);

            }
        });


        group6.clearCheck();

        group6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb_luzes = group.findViewById(checkedId);

            }
        });

        group7.clearCheck();

        group7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb_pneus = group.findViewById(checkedId);

            }
        });

        group8.clearCheck();

        group8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb_travoes = group.findViewById(checkedId);

            }
        });

        group9.clearCheck();

        group9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb_alinhamento = group.findViewById(checkedId);

            }
        });

        group10.clearCheck();

        group10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb_eletrica = group.findViewById(checkedId);

            }
        });

        introduzir_revisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String matricula22="";
                String marca22="";
                String modelo22="";
                String username22="";
                String kilometros22="";
                String custos22="";
                String descricao22="";
                String agua69="";
                String oleo69="";
                String combustivel69="";
                String ar69="";
                String ac69="";
                String luzes69="";
                String pneus69="";
                String travoes69="";
                String alinhamento69="";
                String eletrica69="";
                String ano69="";
                String mes69="";
                String dia69="";

                String imagem69="";

            /*    int ano;
                int mes;
                int dia;*/

                RadioButton rb_agua =  group1.findViewById(group1.getCheckedRadioButtonId());
                RadioButton rb_oleo =  group2.findViewById(group2.getCheckedRadioButtonId());
                RadioButton rb_combustivel =  group3.findViewById(group3.getCheckedRadioButtonId());
                RadioButton rb_ar =  group4.findViewById(group4.getCheckedRadioButtonId());
                RadioButton rb_ac =  group5.findViewById(group5.getCheckedRadioButtonId());
                RadioButton rb_luzes =  group6.findViewById(group6.getCheckedRadioButtonId());
                RadioButton rb_pneus =  group7.findViewById(group7.getCheckedRadioButtonId());
                RadioButton rb_travoes =  group8.findViewById(group8.getCheckedRadioButtonId());
                RadioButton rb_alinhamento =  group9.findViewById(group9.getCheckedRadioButtonId());
                RadioButton rb_eletrica =  group10.findViewById(group10.getCheckedRadioButtonId());

                matricula22=modelo.getMatricula().toString();
                marca22=modelo.getNome_marca().toString();
                modelo22=modelo.getNome_modelo().toString();
                username22=modelo.getUsername().toString();
                kilometros22=km1.getText().toString();
                custos22=custos1.getText().toString();
                descricao22=descricao_prob1.getText().toString();
                agua69=rb_agua.getText().toString();
                oleo69=rb_oleo.getText().toString();
                combustivel69=rb_combustivel.getText().toString();
                ar69=rb_ar.getText().toString();
                ac69=rb_ac.getText().toString();
                luzes69=rb_luzes.getText().toString();
                pneus69=rb_pneus.getText().toString();
                travoes69=rb_travoes.getText().toString();
                alinhamento69=rb_alinhamento.getText().toString();
                eletrica69=rb_eletrica.getText().toString();
                ano69=ano.getText().toString();
                mes69=mes.getText().toString();
                dia69=dia.getText().toString();

                imagem69=modelo.getImagem_modelo().toString();



             /*  int dia = data.getDayOfMonth();
               int mes = data.getMonth()+1;
               int ano = data.getYear();

               data.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                   @Override
                   public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {



                       data69 = String.valueOf(new StringBuilder().append(String.valueOf(i1 + 1))
                               .append("-").append(String.valueOf(i2)).append("-").append(String.valueOf(i))
                               .append(" "));


                   }
               });*/




                RequestR2D2 req = new RequestR2D2(Webservice.revisoes,null,RequestR2D2.POST);

                req.addParValue("matricula",matricula22);
                req.addParValue("nome_marca",marca22);
                req.addParValue("nome_modelo",modelo22);
                req.addParValue("username",username22);
                req.addParValue("custos",custos22);
                req.addParValue("km",kilometros22);
                req.addParValue("agua",agua69);
                req.addParValue("oleo",oleo69);
                req.addParValue("filtro_combustivel",combustivel69);
                req.addParValue("filtro_ar",ar69);
                req.addParValue("filtro_arcondicionado",ac69);
                req.addParValue("luzes",luzes69);
                req.addParValue("pneus",pneus69);
                req.addParValue("travoes",travoes69);
                req.addParValue("alenhamento",alinhamento69);
                req.addParValue("eletrica",eletrica69);
                req.addParValue("descricao_prob",descricao22);
                req.addParValue("ano",ano69);
                req.addParValue("mes",mes69);
                req.addParValue("dia",dia69);
                req.addParValue("imagem",imagem69);

                ask.sendRequest(req,Skyrunner.RequestTag.KPOSONE);

                        /*
                        * String matricula=re.getMatricula();
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
		Date proxima=re.getProxima();
                        *
                        * */

             /*   DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String formattedDate = dateFormat.format(date);*/

                Intent activity_utilizador = new Intent(getApplicationContext(),Utilizador.class);
                startActivity(activity_utilizador);
            }
        });

    }



   /*
    public void onSubmit(View v) {
        RadioButton rb = group.findViewById(group.getCheckedRadioButtonId());
        Toast.makeText(VerRevicoes.this, rb.getText(), Toast.LENGTH_SHORT).show();
    }*/

    @Override
    public void receivedResponse(ResponseR2D2 response) {
        switch (response.getId()) {
            case Skyrunner.RequestTag.KPOSONE: {
                JSONObject job = response.getJSONObj();

                String id="", matricula = "", marca = "", modelo = "", username = "", custos = "", km = "", agua = "", oleo = "", combustivel = "", ar = "", ac = "",luzes="", pneus = "", travoes = "",
                alinhamento = "", eletrica = "", descricao="", ano="",mes="",dia="",imagem="";

                try {

                    matricula = job.getString("matricula");
                    marca = job.getString("nome_marca");
                    modelo = job.getString("nome_modelo");
                    id = job.getString("id");
                    username = job.getString("username");
                    custos = job.getString("custos");
                    km = job.getString("km");
                    agua = job.getString("agua");
                    combustivel = job.getString("filtro_combustivel");
                    ar = job.getString("filtro_ar");
                    ac = job.getString("filtro_arcondicionado");
                    luzes = job.getString("luzes");
                    pneus = job.getString("pneus");
                    travoes = job.getString("travoes");
                    alinhamento = job.getString("alenhamento");
                    eletrica = job.getString("eletrica");
                    descricao=job.getString("descricao_prob");
                    ano=job.getString("ano");
                    mes=job.getString("mes");
                    dia=job.getString("dia");

                    imagem=job.getString("imagem");

                    //  marca.add(new Marcas(id, logo, nome));

                    // System.out.println("ola"+ marca.get(0).getNome());


                    if(id.contains(id)){
                        Toast.makeText(this,"Revição Inserida",Toast.LENGTH_LONG).show();
                       /* Intent activity_utilizador = new Intent(getApplicationContext(),Utilizador.class);
                        startActivity(activity_utilizador);*/
                    }

                } catch (JSONException je) {
                    Log.e("passing", je.getMessage());

                }


            }
        }

    }

    @Override
    public void startConnecting() {

    }

    @Override
    public void endConnecting() {

    }

    @Override
    public void requestTimeout() {

    }
}
