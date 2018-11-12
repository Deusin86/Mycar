package atec.pt.mycar;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
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

import java.util.ArrayList;

import atec.pt.mycar.model.Marcas;
import atec.pt.mycar.model.Revicoes;
import atec.pt.mycar.model.User;
import atec.pt.mycar.util.Webservice;

public class Login extends AppCompatActivity implements RestObserver{

    EditText loginuser;
    EditText loginpassoword;

    Button login;

    //ArrayList<User> aruser=new ArrayList<>();

    Skyrunner Ask;
    Appobjecto ap;


    private final String CHANNEL_ID="revisoes";
    private final int NOTIFICATION_ID=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginuser= findViewById(R.id.login_username);
        loginpassoword= findViewById(R.id.login_password);

        login= findViewById(R.id.butao_login);

        Ask=new Skyrunner(this);

        ap=(Appobjecto)getApplication();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // entrar();


                String username1="";
                String username2="";
                String password1="";

                username1=loginuser.getText().toString();
                password1=loginpassoword.getText().toString();

                username2=loginuser.getText().toString();

              //  Toast.makeText(getApplication(),"ola botao "+ username1,Toast.LENGTH_SHORT).show();

                RequestR2D2 req=new RequestR2D2(Webservice.login,null,RequestR2D2.GET);

                req.addParValue("username",username1);
                req.addParValue("password",password1);


                Ask.sendRequest(req,Skyrunner.RequestTag.KPOSONE);





                RequestR2D2 reqq=new RequestR2D2(Webservice.notificaoes,null,RequestR2D2.GET);

                reqq.addParValue("username",username2);

              //  req.addParValue("password",password1);

                Ask.sendRequest(reqq,Skyrunner.RequestTag.KPOSTWO);



            }
        });


    }

    @Override
    public void receivedResponse(ResponseR2D2 response) {
        switch (response.getId()) {
            case Skyrunner.RequestTag.KPOSONE: {
                JSONArray job = response.getJSONArray();

                String  id= "", nome="", idade="", contacto="", email="", username = "", password = "";

                try {
                    for (int i = 0; i < job.length(); i++) {
                        JSONObject c = job.getJSONObject(i);
                        id = c.getString("id");
                        nome=c.getString("nome");
                        idade=c.getString("idade");
                        contacto=c.getString("contacto");
                        email=c.getString("email");
                        username = c.getString("username");
                        password=c.getString("password");

                        //  marca.add(new Marcas(id, logo, nome));
                        ap.setUu(new User(id, nome, idade, contacto,email, username, password));
                        // System.out.println("ola"+ marca.get(0).getNome());
                    }
                    if(id.isEmpty()){
                        Toast.makeText(this,"Username ou Password errado" + password,Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Bem-Vindo " + nome,Toast.LENGTH_LONG).show();
                        Intent activity_utilizador = new Intent(getApplicationContext(),Utilizador.class);
                        startActivity(activity_utilizador);
                    }


                } catch (JSONException je) {
                    Log.e("passing", je.getMessage());

                }
            }


           case Skyrunner.RequestTag.KPOSTWO: {
                JSONArray job = response.getJSONArray();

                String id="", matricula = "", marca = "", modelo = "", username = "", custos = "", km = "", agua = "", oleo = "", combustivel = "", ar = "", ac = "",luzes="", pneus = "", travoes = "",
                        alinhamento = "", eletrica = "", descricao="", ano="",mes="",dia="",imagem="";

                try {
                    for (int i = 0; i < job.length(); i++) {
                        JSONObject c = job.getJSONObject(i);
                        matricula = c.getString("matricula");
                        marca = c.getString("nome_marca");
                        modelo = c.getString("nome_modelo");
                        id = c.getString("id");
                        username = c.getString("username");
                        custos = c.getString("custos");
                        km = c.getString("km");
                        agua = c.getString("agua");
                        combustivel = c.getString("filtro_combustivel");
                        ar = c.getString("filtro_ar");
                        ac = c.getString("filtro_arcondicionado");
                        luzes = c.getString("luzes");
                        pneus = c.getString("pneus");
                        oleo = c.getString("oleo");
                        travoes = c.getString("travoes");
                        alinhamento = c.getString("alenhamento");
                        eletrica = c.getString("eletrica");
                        descricao = c.getString("descricao_prob");
                        ano = c.getString("ano");
                        mes = c.getString("mes");
                        dia = c.getString("dia");

                        imagem = c.getString("imagem");

                        ap.arrev.add(new Revicoes(id, matricula, marca, modelo, username, custos, km, agua, oleo, combustivel, ar, ac, luzes, pneus, travoes, alinhamento, eletrica, descricao, ano, mes, dia, imagem));

                      //  ap.setRv(new Revicoes(id, matricula, marca, modelo, username, custos, km, agua, oleo, combustivel, ar, ac, luzes, pneus, travoes, alinhamento, eletrica, descricao, ano, mes, dia, imagem));


                    }

                    if (id.isEmpty()) {
                        //Toast.makeText(this, "Username ou Password errado" + password, Toast.LENGTH_LONG).show();
                       // Toast.makeText(this, "if " ,Toast.LENGTH_LONG).show();
                    } else {
                       // Toast.makeText(this, "else " ,Toast.LENGTH_LONG).show();
                        notif();



                    }




                } catch (JSONException je) {
                    Log.e("passing", je.getMessage());

                }


            }//acaba qui





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

   /*public void funcnotif(){

          RequestR2D2 reqq=new RequestR2D2(Webservice.notificaoes,null,RequestR2D2.GET);

                req.addParValue("username",username2);

                req.addParValue("password",password1);

                Ask.sendRequest(req,Skyrunner.RequestTag.KPOSTWO);

   }*/

    public void notif(){
        Intent not=new Intent(this,Notificacao.class);

        not.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        PendingIntent pend = PendingIntent.getActivity(this,0,not,PendingIntent.FLAG_ONE_SHOT);


        NotificationCompat.Builder note=new NotificationCompat.Builder(this,CHANNEL_ID)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.information)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.bmw_serie1))
                .setContentTitle("Revicao pendente")
                .setContentText("Falta 1 mês para a revição !")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(pend)
                .addAction(R.drawable.bmw,"ver",pend);

        NotificationManagerCompat mm =  NotificationManagerCompat.from(this);

        mm.notify(NOTIFICATION_ID,note.build());

    }

}
