package atec.pt.mycar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.eunainter.r2std2oid.RequestR2D2;
import net.eunainter.r2std2oid.ResponseR2D2;
import net.eunainter.r2std2oid.RestObserver;
import net.eunainter.r2std2oid.Skyrunner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import atec.pt.mycar.model.Modelos;
import atec.pt.mycar.model.Revicoes;
import atec.pt.mycar.util.Webservice;

public class Notificacao extends AppCompatActivity {

    RecyclerView notificacao;
    AAModelos_notific viewadapter;
    Skyrunner ask;
    Appobjecto ap;

    /*  ArrayList<Revicoes> modelos_notific = new ArrayList<>();
      List<String> matriculaa;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacao);


        // ArrayList<Revicoes> armm = ((Appobjecto) getApplication()).getArrev();

        ap = (Appobjecto) getApplication();
        notificacao = findViewById(R.id.Rvver_notific);


        notificacao.setLayoutManager(new LinearLayoutManager(this));


        viewadapter = new AAModelos_notific(ap.getArrev()); //adiciona cada equipa ao arraylist
        notificacao.setAdapter(viewadapter);


    }

    public class AAModelos_notific extends RecyclerView.Adapter<AAModelos_notific.VHnotificao> {

        ArrayList<Revicoes> rev;

        public AAModelos_notific(ArrayList<Revicoes> rev) {
            this.rev = rev;
        }

        @Override
        public AAModelos_notific.VHnotificao onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_vercarro_notific, parent, false);
            //   System.out.println("ola"+ marca.get(0).getNome());
            return new VHnotificao(v);
        }

        @Override
        public void onBindViewHolder(AAModelos_notific.VHnotificao holder, int position) {

            Revicoes r = rev.get(position);
            // Log.i("1",marca.get(0).getNome());
            //  Toast.makeText(getActivity().getApplicationContext(),"Carreguei no "+marca.get(position).getNome(),Toast.LENGTH_LONG).show();
            //  System.out.println("Carreguei no "+marca.get(position).getNome());
            //holder.getLogo().setImageResource(Integer.parseInt(m.getLogo()));//pus o parse

            // Picasso.get().load("http://192.168.0.10:8080/"+marca.get(position).getLogo()).into(holder.getLogo());
            //  holder.getNome().setText(m.getNome());
            holder.getNome_marca96().setText(r.getNome_marca());
            holder.getNome_modelo96().setText(r.getNome_modelo());
            holder.getMatricula96().setText(r.getMatricula());
            holder.getKilometros96().setText(r.getKm());
            holder.getAgua96().setText(r.getAgua());
            holder.getOleo96().setText(r.getOleo());
            holder.getCombustivel96().setText(r.getFiltro_combustivel());
            holder.getAr96().setText(r.getFiltro_ar());
            holder.getAc96().setText(r.getFiltro_ac());
            holder.getLuzes96().setText(r.getTravoes());
            holder.getPneus96().setText(r.getPneus());
            holder.getTravoes96().setText(r.getTravoes());
            holder.getAlinhamento96().setText(r.getAlinhamento());
            holder.getEletrica96().setText(r.getEletrica());
            holder.getAno96().setText(r.getAno());
            holder.getMes96().setText(r.getMes());
            holder.getDia96().setText(r.getDia());
            holder.getCusto96().setText(r.getCustos());
            holder.getProblema96().setText(r.getDescricao_prob());

            Picasso.get().load("http://192.168.0.6:8080/" + rev.get(position).getImagem()).into(holder.getCarro());
        }

        @Override
        public int getItemCount() {
            return rev.size();
        }

        public class VHnotificao extends RecyclerView.ViewHolder {
            ImageView carro;

            EditText nome_marca96, nome_modelo96, matricula96, kilometros96, agua96, oleo96, combustivel96, ar96, ac96, luzes96, pneus96, travoes96, alinhamento96, eletrica96, ano96, mes96, dia96, custo96, problema96;


            public VHnotificao(View itemView) {
                super(itemView);

                nome_marca96 = itemView.findViewById(R.id.marca_ver2);
                nome_modelo96 = itemView.findViewById(R.id.modelo_ver2);
                matricula96 = itemView.findViewById(R.id.matricula_ver2);
                kilometros96 = itemView.findViewById(R.id.km_ver2);
                agua96 = itemView.findViewById(R.id.agua_ver2);
                oleo96 = itemView.findViewById(R.id.oleo_ver2);
                combustivel96 = itemView.findViewById(R.id.combustivel_ver2);
                ar96 = itemView.findViewById(R.id.ver_ar2);
                ac96 = itemView.findViewById(R.id.ver_ac2);
                luzes96 = itemView.findViewById(R.id.luzes_ver2);
                pneus96 = itemView.findViewById(R.id.pneus_ver2);
                travoes96 = itemView.findViewById(R.id.travoes_ver2);
                alinhamento96 = itemView.findViewById(R.id.alinhamento_ver2);
                eletrica96 = itemView.findViewById(R.id.eletrica_ver2);
                ano96 = itemView.findViewById(R.id.ano_ver2);
                mes96 = itemView.findViewById(R.id.mes_ver2);
                dia96 = itemView.findViewById(R.id.dia_ver2);
                custo96 = itemView.findViewById(R.id.custo_ver2);
                problema96 = itemView.findViewById(R.id.problema_ver2);

                carro = itemView.findViewById(R.id.carro_ver2);


            }

            public ImageView getCarro() {
                return carro;
            }

            public void setCarro(ImageView carro) {
                this.carro = carro;
            }

            public EditText getNome_marca96() {
                return nome_marca96;
            }

            public void setNome_marca96(EditText nome_marca96) {
                this.nome_marca96 = nome_marca96;
            }

            public EditText getNome_modelo96() {
                return nome_modelo96;
            }

            public void setNome_modelo96(EditText nome_modelo96) {
                this.nome_modelo96 = nome_modelo96;
            }

            public EditText getMatricula96() {
                return matricula96;
            }

            public void setMatricula96(EditText matricula96) {
                this.matricula96 = matricula96;
            }

            public EditText getKilometros96() {
                return kilometros96;
            }

            public void setKilometros96(EditText kilometros96) {
                this.kilometros96 = kilometros96;
            }

            public EditText getAgua96() {
                return agua96;
            }

            public void setAgua96(EditText agua96) {
                this.agua96 = agua96;
            }

            public EditText getOleo96() {
                return oleo96;
            }

            public void setOleo96(EditText oleo96) {
                this.oleo96 = oleo96;
            }

            public EditText getCombustivel96() {
                return combustivel96;
            }

            public void setCombustivel96(EditText combustivel96) {
                this.combustivel96 = combustivel96;
            }

            public EditText getAr96() {
                return ar96;
            }

            public void setAr96(EditText ar96) {
                this.ar96 = ar96;
            }

            public EditText getAc96() {
                return ac96;
            }

            public void setAc96(EditText ac96) {
                this.ac96 = ac96;
            }

            public EditText getLuzes96() {
                return luzes96;
            }

            public void setLuzes96(EditText luzes96) {
                this.luzes96 = luzes96;
            }

            public EditText getPneus96() {
                return pneus96;
            }

            public void setPneus96(EditText pneus96) {
                this.pneus96 = pneus96;
            }

            public EditText getTravoes96() {
                return travoes96;
            }

            public void setTravoes96(EditText travoes96) {
                this.travoes96 = travoes96;
            }

            public EditText getAlinhamento96() {
                return alinhamento96;
            }

            public void setAlinhamento96(EditText alinhamento96) {
                this.alinhamento96 = alinhamento96;
            }

            public EditText getEletrica96() {
                return eletrica96;
            }

            public void setEletrica96(EditText eletrica96) {
                this.eletrica96 = eletrica96;
            }

            public EditText getAno96() {
                return ano96;
            }

            public void setAno96(EditText ano96) {
                this.ano96 = ano96;
            }

            public EditText getMes96() {
                return mes96;
            }

            public void setMes96(EditText mes96) {
                this.mes96 = mes96;
            }

            public EditText getDia96() {
                return dia96;
            }

            public void setDia96(EditText dia96) {
                this.dia96 = dia96;
            }

            public EditText getCusto96() {
                return custo96;
            }

            public void setCusto96(EditText custo96) {
                this.custo96 = custo96;
            }

            public EditText getProblema96() {
                return problema96;
            }

            public void setProblema96(EditText problema96) {
                this.problema96 = problema96;
            }
        }


    }
}

