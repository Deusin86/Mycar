package atec.pt.mycar.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
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

import atec.pt.mycar.Appobjecto;
import atec.pt.mycar.R;
import atec.pt.mycar.carros_modelos.Modelos;
import atec.pt.mycar.model.Marcas;
import atec.pt.mycar.model.User;
import atec.pt.mycar.util.Webservice;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Marcas_frag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Marcas_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Marcas_frag extends Fragment implements RestObserver{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    // ArrayList<Marcas> marca;

    private OnFragmentInteractionListener mListener;
    View v;
    RecyclerView marcas;
    AAMarcas viewadapter;
    Skyrunner aSk;
    ImageView imagem;
    Appobjecto ap;
    ArrayList<Marcas> marca=new ArrayList<>();



    public Marcas_frag() {
        // Required empty public constructor
       // Log.i("1",marca.get(0).getNome());

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Marcas_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static Marcas_frag newInstance(String param1, String param2) {
        Marcas_frag fragment = new Marcas_frag();
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



        v=inflater.inflate(R.layout.fragment_marcas_frag, container, false);

        imagem= v.findViewById(R.id.cv_marca);

        aSk=new Skyrunner(this);

        marcas = v.findViewById(R.id.Rvmarcas);

       marcas.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

       ap=(Appobjecto)getActivity().getApplicationContext();


      /*   final ArrayList<Marcas> marca = new ArrayList<>();
        marca.add(new Marcas(R.drawable.bmw,"BMW"));
        marca.add(new Marcas(R.drawable.mercedes,"Mercedes"));
        marca.add(new Marcas(R.drawable.vw,"Volkswagen"));
        marca.add(new Marcas(R.drawable.ford,"Ford"));
        marca.add(new Marcas(R.drawable.renaut,"Renaut"));*/

        RequestR2D2 req=new RequestR2D2(Webservice.listamarca,null,RequestR2D2.GET);
        aSk.sendRequest(req,Skyrunner.RequestTag.KPOSONE);

       // Log.i("1",marca.get(0).getNome());

        viewadapter = new AAMarcas(marca); //adiciona cada equipa ao arraylist
        marcas.setAdapter(viewadapter); //organiza o recyler view com os cardviews("cartões")


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

    public class AAMarcas extends RecyclerView.Adapter<AAMarcas.VHMarcas>{

        ArrayList<Marcas> marca;
        User aqui;


        public AAMarcas(ArrayList<Marcas> marca){
            this.marca=marca;
        }

        @Override
        public VHMarcas onCreateViewHolder(ViewGroup parent, int viewtype) {
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_marcas,parent,false);
            System.out.println("ola"+ marca.get(0).getNome());
            return new VHMarcas(v);
        }

        @Override
        public void onBindViewHolder(VHMarcas holder, final int position) {


            Marcas m= marca.get(position);
           // Log.i("1",marca.get(0).getNome());
          //  Toast.makeText(getActivity().getApplicationContext(),"Carreguei no "+marca.get(position).getNome(),Toast.LENGTH_LONG).show();
          //  System.out.println("Carreguei no "+marca.get(position).getNome());
            //holder.getLogo().setImageResource(Integer.parseInt(m.getLogo()));//pus o parse
            Picasso.get().load("http://192.168.0.6:8080/"+marca.get(position).getLogo()).into(holder.getLogo());
            holder.getNome().setText(m.getNome());


            holder.getCv_bt().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


              //     if(position==0){

                       aqui=((Appobjecto) getActivity().getApplicationContext()).getUu();
                    //   Toast.makeText(getActivity().getApplicationContext(),"Carreguei no "+ aqui.getUsername(),Toast.LENGTH_LONG).show();
                       String nome="";
                       String username="";
                       String logo="";

                       nome=marca.get(position).getNome().toString();
                       username=aqui.getUsername();
                       logo=marca.get(position).getLogo().toString();
                       //username="Francisco";

                       RequestR2D2 req=new RequestR2D2(Webservice.addmarca,null,RequestR2D2.POST);


//REQ MANDA PARA BASE DE DADOS

                       req.addParValue("logo",logo);
                       req.addParValue("nome",nome);
                       req.addParValue("username",username);

                       ap.setMm(new Marcas(null,logo,nome,username));

                       aSk.sendRequest(req,Skyrunner.RequestTag.KPOSTWO);



                       Intent activity_bmw = new Intent(getActivity().getApplicationContext(),Modelos.class);
                       startActivity(activity_bmw);



                }
            });

        }

        @Override
        public int getItemCount() {
            return marca.size();
        }

        public class VHMarcas extends RecyclerView.ViewHolder {

            ImageView logo;
            TextView nome;
            CardView cv_bt;

            public VHMarcas(View itemView) {
                super(itemView);

                cv_bt = itemView.findViewById(R.id.cv_bt);

                logo = itemView.findViewById(R.id.cv_marca);
                nome = itemView.findViewById(R.id.cv_nome);



            }

            public CardView getCv_bt() {
                return cv_bt;
            }

            public ImageView getLogo() {
                return logo;
            }

            public void setLogo(ImageView logo) {
                this.logo = logo;
            }

            public TextView getNome() {
                return nome;
            }

            public void setNome(TextView nome) {
                this.nome = nome;
            }
        }

    }


    @Override
    public void receivedResponse(ResponseR2D2 response) {
        switch (response.getId()){
            case Skyrunner.RequestTag.KPOSONE:{
                JSONArray job=response.getJSONArray();

                String id="", logo="",nome="",username="";

                try {
                    for(int i=0; i < job.length(); i++){
                        JSONObject c = job.getJSONObject(i);
                        id=c.getString("id");
                        logo=c.getString("logo");
                        nome=c.getString("nome");
                        username=c.getString("username");

                      //  marca.add(new Marcas(id, logo, nome));
                        marca.add(new Marcas(id,logo,nome,username));
                       // System.out.println("ola"+ marca.get(0).getNome());
                    }

                    viewadapter.notifyDataSetChanged();

                }catch (JSONException je){
                    Log.e("passing",je.getMessage());

                }
            }
            case Skyrunner.RequestTag.KPOSTWO: {
                JSONObject job = response.getJSONObj();

                String id = "", logo = "", nome = "", username = "";

                try {
                    id = job.getString("id");
                    logo = job.getString("logo");
                    nome = job.getString("nome");
                    username = job.getString("username");
                   // Toast.makeText(getActivity().getApplicationContext(), "Inserido " + nome, Toast.LENGTH_LONG).show();


                } catch (JSONException je) {
                    Log.e("passing", je.getMessage());

                }

            }
         /*   case Skyrunner.RequestTag.KPOSTHREE: {
                JSONObject job = response.getJSONObj();

                String id = "", logo = "", nome = "", username = "";

                try {
                    id = job.getString("id");
                    logo = job.getString("logo");
                    nome = job.getString("nome");
                    username = job.getString("username");
                    Toast.makeText(getActivity().getApplicationContext(), "Inserido " + nome, Toast.LENGTH_LONG).show();


                } catch (JSONException je) {
                    Log.e("passing", je.getMessage());

                }

            }
            case Skyrunner.RequestTag.KPOSFOUR: {
                JSONObject job = response.getJSONObj();

                String id = "", logo = "", nome = "", username = "";

                try {
                    id = job.getString("id");
                    logo = job.getString("logo");
                    nome = job.getString("nome");
                    username = job.getString("username");
                    Toast.makeText(getActivity().getApplicationContext(), "Inserido " + nome, Toast.LENGTH_LONG).show();


                } catch (JSONException je) {
                    Log.e("passing", je.getMessage());

                }

            }
            case Skyrunner.RequestTag.KPOSFIVE: {
                JSONObject job = response.getJSONObj();

                String id = "", logo = "", nome = "", username = "";

                try {
                    id = job.getString("id");
                    logo = job.getString("logo");
                    nome = job.getString("nome");
                    username = job.getString("username");
                    Toast.makeText(getActivity().getApplicationContext(), "Inserido " + nome, Toast.LENGTH_LONG).show();


                } catch (JSONException je) {
                    Log.e("passing", je.getMessage());

                }

            }
            case Skyrunner.RequestTag.KPOSSIX: {
                JSONObject job = response.getJSONObj();

                String id = "", logo = "", nome = "", username = "";

                try {
                    id = job.getString("id");
                    logo = job.getString("logo");
                    nome = job.getString("nome");
                    username = job.getString("username");
                    Toast.makeText(getActivity().getApplicationContext(), "Inserido " + nome, Toast.LENGTH_LONG).show();


                } catch (JSONException je) {
                    Log.e("passing", je.getMessage());

                }

            }*/
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


