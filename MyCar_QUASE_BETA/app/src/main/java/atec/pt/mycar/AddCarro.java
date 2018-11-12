package atec.pt.mycar;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.eunainter.r2std2oid.RequestR2D2;
import net.eunainter.r2std2oid.ResponseR2D2;
import net.eunainter.r2std2oid.RestObserver;
import net.eunainter.r2std2oid.Skyrunner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import atec.pt.mycar.model.Modelos;
import atec.pt.mycar.model.User;
import atec.pt.mycar.util.Webservice;

public class AddCarro extends AppCompatActivity implements RestObserver {


    EditText nome_marca,
    nome_modelo,
    portas,combustivel,
    consumo,potencia,
    motor,matricula23;

    Button resgistar;
    Appobjecto ap;
    Skyrunner ask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_carro);

        ask=new Skyrunner(this);

        nome_marca=findViewById(R.id.Nome_marca);
        nome_modelo=findViewById(R.id.Nome_modelo);
        portas=findViewById(R.id.Nrportas);
        combustivel=findViewById(R.id.Combustivel);
        consumo=findViewById(R.id.Consumo);
        potencia=findViewById(R.id.Potencia);
        motor=findViewById(R.id.Motor);
        matricula23=findViewById(R.id.Matricula);

        resgistar=findViewById(R.id.resgistar_carro);

        //ap=(Appobjecto)getApplication();


       final Modelos modelo=((Appobjecto) getApplicationContext()).getMod();

        nome_marca.setText(modelo.getNome_marca());
        nome_modelo.setText(modelo.getNome_modelo());
        portas.setText(modelo.getNr_portas());
        combustivel.setText(modelo.getCombustivel());
        consumo.setText(modelo.getConsumo());
        potencia.setText(modelo.getPotencia());
        motor.setText(modelo.getMotor());
        matricula23.setText(modelo.getMatricula());

        resgistar.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              String imagem_modelo="",nome_marca="", nome_modelo="",id="",username="",nr_portas="",combustivel="",consumo="",potencia="",matricula1="",motor="";

              User u=((Appobjecto) getApplicationContext()).getUu();

              imagem_modelo=modelo.getImagem_modelo();
              nome_marca=modelo.getNome_marca();
              nome_modelo=modelo.getNome_modelo();
             // id=modelo.getId();

              username=u.getUsername();
              nr_portas=modelo.getNr_portas();
              combustivel=modelo.getCombustivel();
              consumo=modelo.getConsumo();
              potencia=modelo.getPotencia();
              matricula1=matricula23.getText().toString();
              motor=modelo.getMotor().toString();


              RequestR2D2 req = new RequestR2D2(Webservice.addmodelos,null,RequestR2D2.POST);

              req.addParValue("imagem_modelo",imagem_modelo);
              req.addParValue("nome_marca",nome_marca);
              req.addParValue("nome_modelo",nome_modelo);
              req.addParValue("username",username);
              req.addParValue("nr_portas",nr_portas);
              req.addParValue("combustivel",combustivel);
              req.addParValue("consumo",consumo);
              req.addParValue("potencia",potencia);
              req.addParValue("motor",motor);
              req.addParValue("matricula",matricula1);


              Log.i("ola",id);
           //   Log.i("MERDAAA",username);


              ask.sendRequest(req,Skyrunner.RequestTag.KPOSONE);
          }
      });

      System.out.println(modelo.getId());



    }


    @Override
    public void receivedResponse(ResponseR2D2 response) {
        switch (response.getId()) {
           case Skyrunner.RequestTag.KPOSONE: {
                JSONObject job = response.getJSONObj();

                String imagem_modelo = "", nome_marca = "", nome_modelo = "", id = "", username = "", nr_portas = "", combustivel = "", consumo = "", potencia = "", matricula1 = "", motor = "";

                try {

                        imagem_modelo = job.getString("imagem_modelo");
                        nome_marca = job.getString("nome_marca");
                        nome_modelo = job.getString("nome_modelo");
                        id = job.getString("id");
                        username = job.getString("username");
                        nr_portas = job.getString("nr_portas");
                        combustivel = job.getString("combustivel");
                        consumo = job.getString("consumo");
                        potencia = job.getString("potencia");
                        matricula1 = job.getString("matricula");
                        motor = job.getString("motor");

                        //  marca.add(new Marcas(id, logo, nome));

                        // System.out.println("ola"+ marca.get(0).getNome());


                   /* if(id.contains(id)){
                        Toast.makeText(this,"Registo Completo",Toast.LENGTH_LONG).show();
                        Intent activity_utilizador = new Intent(getApplicationContext(),Utilizador.class);
                        startActivity(activity_utilizador);
                    }*/
                    if(id=="null"){
                        Toast.makeText(this,"Matricula ja existe",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Registo Completo",Toast.LENGTH_LONG).show();
                        Intent activity_utilizador = new Intent(getApplicationContext(),Utilizador.class);
                        startActivity(activity_utilizador);
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
