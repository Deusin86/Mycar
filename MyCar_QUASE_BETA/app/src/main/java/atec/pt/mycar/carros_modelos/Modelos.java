package atec.pt.mycar.carros_modelos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import net.eunainter.r2std2oid.RequestR2D2;
import net.eunainter.r2std2oid.ResponseR2D2;
import net.eunainter.r2std2oid.RestObserver;
import net.eunainter.r2std2oid.Skyrunner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import atec.pt.mycar.AddCarro;
import atec.pt.mycar.Appobjecto;
import atec.pt.mycar.R;
import atec.pt.mycar.model.Marcas;
import atec.pt.mycar.model.User;
import atec.pt.mycar.util.Webservice;

public class Modelos extends AppCompatActivity implements RestObserver{

    RecyclerView modelos;
    AAModelos viewadapter;
    Skyrunner aSk;
    Appobjecto ap;
    ArrayList<atec.pt.mycar.model.Modelos> modelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modelos);

        modelos=findViewById(R.id.Rvmodelos_bmw);

        modelo= new ArrayList<>();

        aSk=new Skyrunner(this);
        modelos.setLayoutManager(new LinearLayoutManager(this));

        ap=(Appobjecto)getApplication();

        Marcas mar = ((Appobjecto)getApplicationContext()).getMm();


        /* MOKUP */
     //   modelo.add(new Modelos(R.drawable.bmw1,"Modelos","Serie 1","1","nada","5","disel","5.1 L/100km","200cv","nada"));

        String nome="";
        nome=mar.getNome().toString();

        RequestR2D2 req=new RequestR2D2(Webservice.listamodelos,null,RequestR2D2.GET);

        req.addParValue("nome_marca",nome);

        aSk.sendRequest(req,Skyrunner.RequestTag.KPOSONE);



        viewadapter = new AAModelos(modelo); //adiciona cada equipa ao arraylist

        modelos.setAdapter(viewadapter); //organiza o recyler view com os cardviews("cartões")

    }



    public class AAModelos extends RecyclerView.Adapter<AAModelos.VHModelo>{

        ArrayList<atec.pt.mycar.model.Modelos> modelo;
        User aqui;


        public AAModelos(ArrayList<atec.pt.mycar.model.Modelos> modelo){
            this.modelo=modelo;
        }

        @Override
        public VHModelo onCreateViewHolder(ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_modelos,parent,false);
            return new VHModelo(v);
        }

        @Override
        public void onBindViewHolder(VHModelo holder, final int position) {
         /*   Modelos m= modelo.get(position);

            holder.imagem_modelo.setImageResource(m.getImagem_modelo());
            holder.nome_modelo.setText(m.getNome_modelo());*/

            atec.pt.mycar.model.Modelos m= modelo.get(position);

            //  Toast.makeText(getActivity().getApplicationContext(),"Carreguei no "+marca.get(position).getNome(),Toast.LENGTH_LONG).show();
            System.out.println("Carreguei no "+modelo.get(position).getNome_modelo());

            Picasso.get().load("http://192.168.0.6:8080/"+modelo.get(position).getImagem_modelo()).into(holder.getImagem_modelo());
            holder.getNome_modelo().setText(m.getNome_modelo());

            holder.getCv().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                 //   if(position==0){


                     //   aqui=((Appobjecto) getApplicationContext()).getUu();
                  //      Toast.makeText(getApplicationContext(),"Carreguei no "+ aqui.getUsername(),Toast.LENGTH_LONG).show();
                       // Toast.makeText(getApplicationContext(),"Carreguei no ",Toast.LENGTH_LONG).show();

                        String imagem_modelo="",nome_marca="", nome_modelo="",username="",nr_portas="",combustivel="",consumo="",potencia="",matricula="",motor="";

                        imagem_modelo=modelo.get(position).getImagem_modelo().toString();
                       nome_marca=modelo.get(position).getNome_marca().toString();
                        nome_modelo=modelo.get(position).getNome_modelo().toString();
                    //    username=aqui.getUsername();
                       nr_portas=modelo.get(position).getNr_portas().toString();
                         combustivel=modelo.get(position).getCombustivel().toString();
                       consumo=modelo.get(position).getCombustivel().toString();
                       potencia=modelo.get(position).getPotencia().toString();
                       matricula=modelo.get(position).getMatricula().toString();
                       motor=modelo.get(position).getMotor().toString();
                    ap.setMod(new atec.pt.mycar.model.Modelos(imagem_modelo,nome_marca,nome_modelo,null,null,nr_portas,combustivel,consumo,potencia,matricula,motor));

                     /*   RequestR2D2 req=new RequestR2D2(Webservice.addmodelos,null,RequestR2D2.POST);



                        req.addParValue("imagem_modelo",imagem_modelo);
                        req.addParValue("nome_marca",nome_marca);
                        req.addParValue("nome_modelo",nome_modelo);
                        req.addParValue("username",username);
                        req.addParValue("nr_portas",nr_portas);
                        req.addParValue("combustivel",combustivel);
                        req.addParValue("consumo",consumo);
                        req.addParValue("potencia",potencia);
                        req.addParValue("matricula",matricula);
                        req.addParValue("motor",motor);



                        aSk.sendRequest(req,Skyrunner.RequestTag.KPOSTWO);*/


                       // ap.setMod(new atec.pt.mycar.model.Modelos(imagem_modelo,nome_marca,nome_modelo,null,username,nr_portas,combustivel,consumo,potencia,matricula,motor));

                        Intent activity_addcarro = new Intent(getApplicationContext(),AddCarro.class);
                        startActivity(activity_addcarro);

                    }


               // }
            });



        }


        @Override
        public int getItemCount() {
            return modelo.size();

        }

        public class VHModelo extends RecyclerView.ViewHolder{

            ImageView imagem_modelo;
            TextView nome_modelo;
            CardView cv;

            public VHModelo(View itemView){
                super(itemView);

                imagem_modelo=itemView.findViewById(R.id.cv_modelo);
                nome_modelo=itemView.findViewById(R.id.cv_nome_modelo);
                cv=itemView.findViewById(R.id.cv_bt_bmw);
            }

            public ImageView getImagem_modelo() {
                return imagem_modelo;
            }

            public void setImagem_modelo(ImageView imagem_modelo) {
                this.imagem_modelo = imagem_modelo;
            }

            public TextView getNome_modelo() {
                return nome_modelo;
            }

            public void setNome_modelo(TextView nome_modelo) {
                this.nome_modelo = nome_modelo;
            }

            public CardView getCv() {
                return cv;
            }

            public void setCv(CardView cv) {
                this.cv = cv;
            }
        }
    }

    @Override
    public void receivedResponse(ResponseR2D2 response) {
        switch (response.getId()){
            case Skyrunner.RequestTag.KPOSONE:{
                JSONArray job=response.getJSONArray();

                String imagem_modelo="", id="",nome_marca="", nome_modelo="",username="",nr_portas="",combustivel="",consumo="",potencia="",matricula="",motor="";

                try {
                    for(int i=0; i < job.length(); i++){
                        JSONObject c = job.getJSONObject(i);
                        imagem_modelo=c.getString("imagem_modelo");
                        id=c.getString("id");
                        nome_marca=c.getString("nome_marca");
                        nome_modelo=c.getString("nome_modelo");
                        nr_portas=c.getString("nr_portas");
                        combustivel=c.getString("combustivel");
                        consumo=c.getString("consumo");
                        potencia=c.getString("potencia");
                        matricula=c.getString("matricula");
                        motor=c.getString("motor");
                        username=c.getString("username");


                        //  marca.add(new Marcas(id, logo, nome));
                        modelo.add(new atec.pt.mycar.model.Modelos(imagem_modelo,nome_marca,nome_modelo,id,username,nr_portas,combustivel,consumo,potencia,matricula,motor));
                        // System.out.println("ola"+ marca.get(0).getNome());
                      //  ap.setMod(new atec.pt.mycar.model.Modelos(imagem_modelo,nome_marca,nome_modelo,id,username,nr_portas,combustivel,consumo,potencia,matricula,motor));

                    }

                    viewadapter.notifyDataSetChanged();

                }catch (JSONException je){
                    Log.e("passing",je.getMessage());

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
