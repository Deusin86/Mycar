package atec.pt.mycar.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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

import atec.pt.mycar.Appobjecto;
import atec.pt.mycar.EditarCarro;
import atec.pt.mycar.R;
import atec.pt.mycar.model.Modelos;
import atec.pt.mycar.model.User;
import atec.pt.mycar.util.Webservice;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Especificacoes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Especificacoes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Especificacoes extends Fragment implements RestObserver {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    View v;
    RecyclerView modelos;
    AAModelos_specs viewadapter;
    Skyrunner ask;
    Appobjecto ap;
    ArrayList<Modelos> modelos_specs;



    public Especificacoes() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Especificacoes.
     */
    // TODO: Rename and change types and number of parameters
    public static Especificacoes newInstance(String param1, String param2) {
        Especificacoes fragment = new Especificacoes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        modelos_specs=new ArrayList<>();
        ap=((Appobjecto) getActivity().getApplicationContext());
        User u=((Appobjecto) getActivity().getApplicationContext()).getUu();
        String user="";
        user=u.getUsername();

        v=inflater.inflate(R.layout.fragment_especificacoes, container, false);




        ask=new Skyrunner(this);

        RequestR2D2 req=new RequestR2D2(Webservice.listaspecs,null,RequestR2D2.GET);
        ask.sendRequest(req,Skyrunner.RequestTag.KPOSONE);

        req.addParValue("username",user);


        modelos = v.findViewById(R.id.Rvspecs);

        modelos.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));



        viewadapter = new AAModelos_specs(modelos_specs); //adiciona cada equipa ao arraylist
        modelos.setAdapter(viewadapter); //organiza o recyler view com os cardviews("cartões")


        return v;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class AAModelos_specs extends RecyclerView.Adapter<AAModelos_specs.VHModelos_specs> {

        ArrayList<Modelos> modelos_specs;
        //   User aqui;


        public AAModelos_specs(ArrayList<Modelos> modelos_specs) {
            this.modelos_specs = modelos_specs;
        }

        @Override
        public VHModelos_specs onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_especificacoes, parent, false);
            // System.out.println("ola"+ marca.get(0).getNome());
            return new VHModelos_specs(v);


        }

        @Override
        public void onBindViewHolder(AAModelos_specs.VHModelos_specs holder, final int position) {

            Modelos m = modelos_specs.get(position);

            Picasso.get().load("http://192.168.0.6:8080/" + modelos_specs.get(position).getImagem_modelo()).into(holder.getModelo_carro());
            holder.getMarca().setText(m.getNome_marca());
            holder.getModelo().setText(m.getNome_modelo());
            holder.getPortas().setText(m.getNr_portas());
            holder.getCombustivel().setText(m.getCombustivel());
            holder.getConsumo().setText(m.getConsumo());
            holder.getPotencia().setText(m.getPotencia());
            holder.getMotor().setText(m.getMotor());
            holder.getMatricula().setText(m.getMatricula());


            holder.getDelete().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                 //   Toast.makeText(getActivity().getApplicationContext(),"Carreguei no Delete "+ modelos_specs.get(position).getMatricula(),Toast.LENGTH_LONG).show();

                    String matricula22="";
                    matricula22=modelos_specs.get(position).getMatricula();

                    RequestR2D2 reqdel=new RequestR2D2(Webservice.delmodel,null,RequestR2D2.GET);

                    reqdel.addParValue("matricula",matricula22);
                    ask.sendRequest(reqdel,Skyrunner.RequestTag.KPOSTWO);

                    RequestR2D2 reqdelrev=new RequestR2D2(Webservice.delrevi,null,RequestR2D2.GET);

                    reqdelrev.addParValue("matricula",matricula22);
                    ask.sendRequest(reqdelrev,Skyrunner.RequestTag.KPOSTHREE);


                    modelos_specs.remove(position);


                }
            });

            holder.getEditar().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity().getApplicationContext(),"Carreguei no Editar "+ modelos_specs.get(position).getMatricula(),Toast.LENGTH_LONG).show();


                    String id2="",imagem_modelo2="",nome_marca2="", nome_modelo2="",username2="",nr_portas2="",combustivel2="",consumo2="",potencia2="",matricula2="",motor2="";

                    imagem_modelo2=modelos_specs.get(position).getImagem_modelo().toString();
                    nome_marca2=modelos_specs.get(position).getNome_marca().toString();
                    nome_modelo2=modelos_specs.get(position).getNome_modelo().toString();
                    username2=modelos_specs.get(position).getUsername().toString();
                    nr_portas2=modelos_specs.get(position).getNr_portas().toString();
                    combustivel2=modelos_specs.get(position).getCombustivel().toString();
                    consumo2=modelos_specs.get(position).getCombustivel().toString();
                    potencia2=modelos_specs.get(position).getPotencia().toString();
                    matricula2=modelos_specs.get(position).getMatricula().toString();
                    motor2=modelos_specs.get(position).getMotor().toString();
                    id2=modelos_specs.get(position).getId().toString();


                    ap.setMod(new Modelos(imagem_modelo2,nome_marca2,nome_modelo2,id2,username2,nr_portas2,combustivel2,consumo2,potencia2,matricula2,motor2));



                    Intent fdx2 = new Intent(getActivity().getApplicationContext(),EditarCarro.class);
                    startActivity(fdx2);
                }
            });

        }

        @Override
        public int getItemCount() {
            return modelos_specs.size();
        }


        public class VHModelos_specs extends RecyclerView.ViewHolder {

            ImageView modelo_carro,delete,editar;
            EditText marca,modelo,
                    portas, combustivel,
                    consumo, potencia,
                    motor, matricula;


            public VHModelos_specs(View itemView) {
                super(itemView);

                modelo_carro = itemView.findViewById(R.id.carro_specs);
                marca = itemView.findViewById(R.id.marca_specs);
                modelo = itemView.findViewById(R.id.modelo_specs);
                portas = itemView.findViewById(R.id.Nrportas_specs);
                combustivel = itemView.findViewById(R.id.Combustivel_specs);
                consumo = itemView.findViewById(R.id.Consumo_specs);
                potencia = itemView.findViewById(R.id.Potencia_specs);
                motor = itemView.findViewById(R.id.Motor_specs);
                matricula = itemView.findViewById(R.id.Matricula_specs);
                delete=itemView.findViewById(R.id.delete_carro);
                editar=itemView.findViewById(R.id.editar_carro);

            }

            public ImageView getModelo_carro() {
                return modelo_carro;
            }

            public void setModelo_carro(ImageView modelo_carro) {
                this.modelo_carro = modelo_carro;
            }

            public ImageView getDelete() {
                return delete;
            }

            public void setDelete(ImageView delete) {
                this.delete = delete;
            }

            public ImageView getEditar() {
                return editar;
            }

            public void setEditar(ImageView editar) {
                this.editar = editar;
            }

            public EditText getMarca() {
                return marca;
            }

            public void setMarca(EditText marca) {
                this.marca = marca;
            }

            public EditText getModelo() {
                return modelo;
            }

            public void setModelo(EditText modelo) {
                this.modelo = modelo;
            }

            public EditText getPortas() {
                return portas;
            }

            public void setPortas(EditText portas) {
                this.portas = portas;
            }

            public EditText getCombustivel() {
                return combustivel;
            }

            public void setCombustivel(EditText combustivel) {
                this.combustivel = combustivel;
            }

            public EditText getConsumo() {
                return consumo;
            }

            public void setConsumo(EditText consumo) {
                this.consumo = consumo;
            }

            public EditText getPotencia() {
                return potencia;
            }

            public void setPotencia(EditText potencia) {
                this.potencia = potencia;
            }

            public EditText getMotor() {
                return motor;
            }

            public void setMotor(EditText motor) {
                this.motor = motor;
            }

            public EditText getMatricula() {
                return matricula;
            }

            public void setMatricula(EditText matricula) {
                this.matricula = matricula;
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
                        modelos_specs.add(new Modelos(imagem_modelo,nome_marca,nome_modelo,id,username,nr_portas,combustivel,consumo,potencia,matricula,motor));


                    }

                    viewadapter.notifyDataSetChanged();

                }catch (JSONException je){
                    Log.e("passing",je.getMessage());

                }
            }

            case Skyrunner.RequestTag.KPOSTWO:{
               viewadapter.notifyDataSetChanged();
            }


            case Skyrunner.RequestTag.KPOSTHREE:{
                String job=response.getMessage();


                if(job.compareTo("eliminado")==0){
                    //Toast.makeText(getActivity().getApplicationContext(),"Revisao Eliminado",Toast.LENGTH_LONG).show();
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