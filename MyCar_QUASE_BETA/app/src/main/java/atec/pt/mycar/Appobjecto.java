package atec.pt.mycar;

import android.app.Application;
import android.view.Display;

import java.util.ArrayList;

import atec.pt.mycar.model.Marcas;
import atec.pt.mycar.model.Modelos;
import atec.pt.mycar.model.Revicoes;
import atec.pt.mycar.model.User;

public class Appobjecto extends Application{

    User uu;
    Marcas mm;
    Modelos mod;
    Revicoes rv;

    ArrayList<Revicoes> arrev;
    @Override
    public void onCreate() {
        super.onCreate();
        arrev=new ArrayList<>();
    }

    public User getUu() {
        return uu;
    }

    public void setUu(User uu) {
        this.uu = uu;
    }

    public Marcas getMm() {
        return mm;
    }

    public void setMm(Marcas mm) {
        this.mm = mm;
    }

    public Modelos getMod() {
        return mod;
    }

    public void setMod(Modelos mod) {
        this.mod = mod;
    }

    public Revicoes getRv() {
        return rv;
    }

    public void setRv(Revicoes rv) {
        this.rv = rv;
    }

    public ArrayList<Revicoes> getArrev() {
        return arrev;
    }

    public void setArrev(ArrayList<Revicoes> arrev) {
        this.arrev = arrev;
    }
}
