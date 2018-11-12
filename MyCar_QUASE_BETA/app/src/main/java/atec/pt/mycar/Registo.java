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

import atec.pt.mycar.util.Webservice;

public class Registo extends AppCompatActivity implements RestObserver{

     Button registado;
     Skyrunner aSK;
     EditText ednome,edidade,edcontacto,edemail,edusername,edpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);


        ednome=findViewById(R.id.RNome);
        edidade=findViewById(R.id.RIdade);
        edcontacto=findViewById(R.id.RContacto);
        edemail=findViewById(R.id.REmail);
        edusername=findViewById(R.id.RUsername);
        edpassword=findViewById(R.id.RPassword);

        registado= findViewById(R.id.RResgistar);
        aSK=new Skyrunner(this);


        registado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent activity_main = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(activity_main);*/


                String nome1="";
                String idade1="";
                String contacto1="";
                String email1="";
                String user1="";
                String password1="";


              nome1=ednome.getText().toString();
              idade1=edidade.getText().toString();
              contacto1=edcontacto.getText().toString();
              email1=edemail.getText().toString();
              user1=edusername.getText().toString();
              password1=edpassword.getText().toString();


                RequestR2D2 req=new RequestR2D2(Webservice.registo,null,RequestR2D2.POST);

                req.addParValue("nome",nome1);
                req.addParValue("idade",idade1);
                req.addParValue("contacto",contacto1);
                req.addParValue("email",email1);
                req.addParValue("username",user1);
                req.addParValue("password",password1);


                aSK.sendRequest(req,Skyrunner.RequestTag.KPOSONE);

            }
        });
    }

    @Override
    public void receivedResponse(ResponseR2D2 response) {
        switch (response.getId()){
            case Skyrunner.RequestTag.KPOSONE:{
                String job=response.getMessage();

               // String id="", nome="",idade="",contacto="", email="", username="", password="";

             //   try {
                /*    id=job.getString("id");
                    nome=job.getString("nome");
                    idade=job.getString("idade");
                    contacto=job.getString("contacto");
                    email=job.getString("email");
                    username=job.getString("username");
                    password=job.getString("password");*/
                    if(job.compareTo(edemail.getText().toString())==0 && job.compareTo(edusername.getText().toString())==0){
                        Toast.makeText(this,"Ja existe este username e email",Toast.LENGTH_LONG).show();
                    }

                    else if( job.compareTo(edemail.getText().toString())==0){
                        Toast.makeText(this,"Ja existe este email",Toast.LENGTH_LONG).show();
                    }
                    else if(job.compareTo(edusername.getText().toString())==0){
                        Toast.makeText(this,"Ja existe este user",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Inserido com sucesso",Toast.LENGTH_LONG).show();
                        Intent activity_main = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(activity_main);
                    }


                  /*  if(id.contains("null")){
                        Toast.makeText(this,"Ja existe este email: " + email + " ou este username: " + username ,Toast.LENGTH_LONG).show();

                    }
                    else{
                        Toast.makeText(this,"Inserido" + nome,Toast.LENGTH_SHORT).show();

                    }*/

                    //Toast.makeText(this,"Inserido",Toast.LENGTH_SHORT).show();


            /*    }catch (JSONException je){
                    Log.e("passing",je.getMessage());*/

                }

            }

        }
    //}

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
