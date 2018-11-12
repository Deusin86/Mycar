package atec.pt.mycar.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import atec.pt.mycar.Appobjecto;
import atec.pt.mycar.Listar_Carro_Revicoes;
import atec.pt.mycar.R;
import atec.pt.mycar.VerRevicoes;
import atec.pt.mycar.model.Modelos;
import atec.pt.mycar.util.Webservice;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Listar_Revicoes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Listar_Revicoes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Listar_Revicoes extends Fragment implements RestObserver {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    Button enviar;
    EditText matricula1;
    View v;
    Appobjecto ap;
    Skyrunner ask;

    public Listar_Revicoes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Listar_Revicoes.
     */
    // TODO: Rename and change types and number of parameters
    public static Listar_Revicoes newInstance(String param1, String param2) {
        Listar_Revicoes fragment = new Listar_Revicoes();
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

        // Inflate the layout for this fragment
       v= inflater.inflate(R.layout.fragment_listar__revicoes, container, false);
        ap=((Appobjecto)getActivity().getApplicationContext());
       ask= new Skyrunner(this);

       matricula1=v.findViewById(R.id.pesquisa_revisoes);
       enviar=v.findViewById(R.id.ver_revisoes);

       enviar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String matricula11="";

               matricula11=matricula1.getText().toString(); //mandar a pergunta á base de dados

               RequestR2D2 req=new RequestR2D2(Webservice.busca,null,RequestR2D2.GET);

               req.addParValue("matricula",matricula11); //adicionar com parametro matricula

               ask.sendRequest(req,Skyrunner.RequestTag.KPOSONE);



           }
       });


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

    @Override
    public void receivedResponse(ResponseR2D2 response) {
        switch (response.getId()) {
            case Skyrunner.RequestTag.KPOSONE: {
                JSONArray job = response.getJSONArray();

                String imagem_modelo = "", nome_marca = "", nome_modelo = "", id = "", username = "", nr_portas = "", combustivel = "", consumo = "", potencia = "", matricula1 = "", motor = "";

                try {
                    for(int i=0; i < job.length(); i++) {
                        JSONObject c = job.getJSONObject(i);
                        imagem_modelo = c.getString("imagem_modelo");
                        nome_marca = c.getString("nome_marca");
                        nome_modelo = c.getString("nome_modelo");
                        id = c.getString("id");
                        username = c.getString("username");
                        nr_portas = c.getString("nr_portas");
                        combustivel = c.getString("combustivel");
                        consumo = c.getString("consumo");
                        potencia = c.getString("potencia");
                        matricula1 = c.getString("matricula");
                        motor = c.getString("motor");

                        //  marca.add(new Marcas(id, logo, nome));
                        ap.setMod(new Modelos(imagem_modelo,nome_marca,nome_modelo,null,username,nr_portas,combustivel,consumo,potencia,matricula1,motor)); //receber as coisas da base de dados para depois meter
                        // System.out.println("ola"+ marca.get(0).getNome());
                        if(id=="null"){
                            Toast.makeText(getActivity().getApplicationContext(),"Não existe",Toast.LENGTH_LONG).show();
                        }else{
                            Intent activity_revisoes = new Intent(getActivity().getApplicationContext(),Listar_Carro_Revicoes.class);
                            startActivity(activity_revisoes);
                        }
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
