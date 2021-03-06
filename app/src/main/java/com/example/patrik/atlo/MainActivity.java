package com.example.patrik.atlo;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText oldalAEdit;
    EditText oldalBEdit;
    EditText oldalCEdit;
    TextView alfaText;
    TextView betaText;
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
        alfaText   = findViewById(R.id.alfaText);
        betaText   = findViewById(R.id.betaText);
    }


    public void Click(View V){
        oldalEredmeny oldaleredmeny = new oldalEredmeny(oldalAEdit.getText().toString(), oldalBEdit.getText().toString(), oldalCEdit.getText().toString());
        szogEredmeny szogEredmeny = new szogEredmeny(oldalAEdit.getText().toString(), oldalBEdit.getText().toString(), oldalCEdit.getText().toString());
        alfaText.setText(szogEredmeny.getAlfa()+"°");
        betaText.setText(szogEredmeny.getBeta()+"°");

    }


    class oldalEredmeny{

        String aOldal;
        String bOldal;
        String cOldal;

        public oldalEredmeny(String aOldal, String bOldal, String cOldal) {
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

                if ((dAtfogo != 0) && (dBefogo != 0)) {
                    dBefogo = dBefogo * dBefogo;
                    dAtfogo = dAtfogo * dAtfogo;
                    eredmeny = Math.sqrt(dAtfogo - dBefogo);
                }
                else{
                    Toast.makeText(MainActivity.this, "A bemenet nem lehet 0!!!", Toast.LENGTH_SHORT).show();
                    return;
                }

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

                if ((dBefogo1 != 0) && (dBefogo2 != 0)) {
                    dBefogo1 = dBefogo1 * dBefogo1;
                    dBefogo2 = dBefogo2 * dBefogo2;
                    eredmeny = Math.sqrt(dBefogo1 + dBefogo2);
                }
                else{
                    Toast.makeText(MainActivity.this, "A bemenet nem lehet 0!!!", Toast.LENGTH_SHORT).show();
                    return;
                }

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


    }

    class szogEredmeny{
        String aOldal;
        String bOldal;
        String cOldal;
        Double alfa;
        Double beta;

        public szogEredmeny(String aOldal, String bOldal, String cOldal){
            this.aOldal = aOldal;
            this.bOldal = bOldal;
            this.cOldal = cOldal;
            if((aOldal != "") && (bOldal !="") && (cOldal != "")){
                alfa = szogSzamol(this.aOldal, this.bOldal);
                beta = szogSzamol(this.bOldal, this.aOldal);
            }

        }

        Double szogSzamol(String oldal1, String oldal2){
            Double dOldal1, dOldal2;
            try {
                dOldal1 = Double.parseDouble(oldal1);
                dOldal2 = Double.parseDouble(oldal2);
                return Math.toDegrees(Math.atan(dOldal1 / dOldal2));
            }
            catch (Exception e){
                Toast.makeText(MainActivity.this,"Hiba",Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        public String getAlfa() {
            alfa = kerekit(alfa,3);
            return String.valueOf(alfa);
        }

        public String getBeta() {
            beta = kerekit(beta,3);
            return String.valueOf(beta);
        }
    }

    double kerekit(double szam, int jegy){
        szam = szam * Math.pow(10, jegy);
        szam = Math.round(szam);
        szam = szam / Math.pow(10, jegy);
        return szam;
    }

    public void clrA(View v){
        oldalAEdit.setText("");
    }

    public void clrB(View v){
        oldalBEdit.setText("");
    }

    public void clrC(View v){
        oldalCEdit.setText("");
    }

}
