package atec.pt.mycar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    Button mainlogin;
    Button mainregisto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainlogin = findViewById(R.id.main_login);
        mainregisto = findViewById(R.id.main_registar);

        mainlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activity_login = new Intent(getApplicationContext(),Login.class);
                startActivity(activity_login);
            }
        });

        mainregisto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activity_registo = new Intent(getApplicationContext(),Registo.class);
                startActivity(activity_registo);
            }
        });


    }

}

