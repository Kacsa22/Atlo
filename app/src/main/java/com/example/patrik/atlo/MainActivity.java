package com.example.patrik.atlo;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText oldalAEdit;
    EditText oldalBEdit;
    EditText oldalCEdit;
    ConstraintLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGUI();
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(oldalBEdit.getWindowToken(), 0);
                }
            }
        });
    }


    void initGUI(){
        oldalAEdit = findViewById(R.id.oldalAEdit);
        oldalBEdit = findViewById(R.id.oldalBEdit);
        oldalCEdit = findViewById(R.id.oldalCEdit);
        main       = findViewById(R.id.main);
    }


    public void Click(View V){
        eredmeny Eredmeny = new eredmeny(oldalAEdit.getText().toString(), oldalBEdit.getText().toString(), oldalCEdit.getText().toString());
    }


    class eredmeny{

        String aOldal;
        String bOldal;
        String cOldal;

        public eredmeny(String aOldal, String bOldal, String cOldal) {
            this.aOldal = aOldal;
            this.bOldal = bOldal;
            this.cOldal = cOldal;
            ellenoriz();
        }


        void ellenoriz(){

            Boolean felt1 = aOldal.equals("");
            Boolean felt2 = bOldal.equals("");
            Boolean felt3 = cOldal.equals("");

            if (felt1 && !felt2 && !felt3){
                befogoszamol(bOldal, cOldal, oldalAEdit);
            }

            else if (!felt1 && felt2 && !felt3){
                befogoszamol(aOldal,cOldal, oldalBEdit);
            }

            else if (!felt1 && !felt2 && felt3){
                atfogoszamol(aOldal, bOldal);

            }
            else{
                Toast.makeText(MainActivity.this,"Egy mezőt üresen kell hagyni!!!",Toast.LENGTH_LONG).show();
            }
        }


        void befogoszamol(String befogo, String atfogo, EditText edit){

            Double dBefogo, dAtfogo, eredmeny;
            eredmeny = 0.0;

            try{
                dBefogo = Double.parseDouble(befogo);
                dAtfogo = Double.parseDouble(atfogo);

                dBefogo = dBefogo * dBefogo;
                dAtfogo = dAtfogo * dAtfogo;
                eredmeny = Math.sqrt(dAtfogo - dBefogo);
            }
            catch (Exception e){
                Toast.makeText(MainActivity.this, "Karakterhiba!!!",Toast.LENGTH_LONG).show();
            }

            eredmeny = kerekit(eredmeny,3);

            if (!(eredmeny == 0)) {
                edit.setText(eredmeny.toString());
            }
            else edit.setText("");
        }


        void atfogoszamol(String befogo1, String befogo2){

            Double dBefogo1, dBefogo2, eredmeny;
            eredmeny = 0.0;

            try{
                dBefogo1 = Double.parseDouble(befogo1);
                dBefogo2 = Double.parseDouble(befogo2);

                dBefogo1 = dBefogo1 * dBefogo1;
                dBefogo2 = dBefogo2 * dBefogo2;
                eredmeny = Math.sqrt(dBefogo1 + dBefogo2);
            }
            catch (Exception e){
                Toast.makeText(MainActivity.this, "Karakterhiba!!!",Toast.LENGTH_LONG).show();
            }

            eredmeny = kerekit(eredmeny,3);

            if (!(eredmeny == 0)) {
                oldalCEdit.setText(eredmeny.toString());
            }
            else oldalCEdit.setText("");
        }


        double kerekit(double szam, int jegy){
            szam = szam * Math.pow(10, jegy);
            szam = Math.round(szam);
            szam = szam / Math.pow(10, jegy);
            return szam;
        }
    }
}
