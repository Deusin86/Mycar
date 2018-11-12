package atec.pt.mycar;

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

import org.json.JSONException;
import org.json.JSONObject;

import atec.pt.mycar.model.Modelos;
import atec.pt.mycar.model.User;
import atec.pt.mycar.util.Webservice;

public class EditarCarro extends AppCompatActivity implements RestObserver {

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
        setContentView(R.layout.activity_editar_carro);

        ask=new Skyrunner(this);

        nome_marca=findViewById(R.id.Nome_marca_editar);
        nome_modelo=findViewById(R.id.Nome_modelo_editar);
        portas=findViewById(R.id.Nrportas_editar);
        combustivel=findViewById(R.id.Combustivel_editar);
        consumo=findViewById(R.id.Consumo_editar);
        potencia=findViewById(R.id.Potencia_editar);
        motor=findViewById(R.id.Motor_editar);
        matricula23=findViewById(R.id.Matricula_editar);

        resgistar=findViewById(R.id.resgistar_carro_editar);

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

                String imagem_modelo5="",nome_marca5="", nome_modelo5="",id5="",username5="",nr_portas5="",combustivel5="",consumo5="",potencia5="",matricula5="",motor5="";

            //    User u=((Appobjecto) getApplicationContext()).getUu();
                Modelos m=((Appobjecto) getApplicationContext()).getMod();

                imagem_modelo5=modelo.getImagem_modelo();
                nome_marca5=modelo.getNome_marca();
                nome_modelo5=modelo.getNome_modelo();
               // id5=m.getId();
                id5=m.getId();
                username5=modelo.getUsername();
                nr_portas5=modelo.getNr_portas();
                combustivel5=combustivel.getText().toString();
                consumo5=consumo.getText().toString();
                potencia5=potencia.getText().toString();
                matricula5=matricula23.getText().toString();
                motor5=motor.getText().toString();


                RequestR2D2 req = new RequestR2D2(Webservice.update,null,RequestR2D2.POST);

                req.addParValue("imagem_modelo",imagem_modelo5);
                req.addParValue("nome_marca",nome_marca5);
                req.addParValue("nome_modelo",nome_modelo5);
                req.addParValue("username",username5);
                req.addParValue("nr_portas",nr_portas5);
                req.addParValue("combustivel",combustivel5);
                req.addParValue("consumo",consumo5);
                req.addParValue("potencia",potencia5);
                req.addParValue("motor",motor5);
                req.addParValue("matricula",matricula5);
                req.addParValue("id",id5);


                Log.i("ola",id5);
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


                    if(id.contains(id)){
                        Log.e("CCC",consumo);
                        Toast.makeText(this,"Carro Editado !",Toast.LENGTH_LONG).show();
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
