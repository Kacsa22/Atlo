package com.example.patrik.atlo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button szamolbutton;
    EditText oldal1,oldal2,eredmenytxt;
    boolean karakterhiba,ertekhiba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        szamolbutton = findViewById(R.id.szamol_button);
        oldal1 = findViewById(R.id.oldal1_edit);
        oldal2 = findViewById(R.id.oldal2_edit);
        eredmenytxt = findViewById(R.id.eredmeny_edit);
        karakterhiba = false;
        ertekhiba = false;
    }

    public void Click(View v){
        oldal1.setBackgroundColor(0x00000000);
        oldal2.setBackgroundColor(0x00000000);
        String oldal1str,oldal2str;
        oldal1str = Karaktercsere(oldal1.getText().toString());
        oldal2str = Karaktercsere(oldal2.getText().toString());
        Eredmeny kecske = strtofloat(oldal1str,oldal2str);

        if(!karakterhiba){
            ertekcheck(kecske.getOldal1(),oldal1);
            ertekcheck(kecske.getOldal2(),oldal2);
        }
        if(!karakterhiba && !ertekhiba) {
            Szamol(kecske.getOldal1(), kecske.getOldal2());
        }
        karakterhiba = false;
        ertekhiba = false;
    }

    private void Szamol(double szamoldal1,double szamoldal2 ){
        double eredmeny = 0;
        szamoldal1 = szamoldal1*szamoldal1;
        szamoldal2 = szamoldal2*szamoldal2;
        eredmeny = Math.sqrt(szamoldal1+szamoldal2);
        eredmenytxt.setText(String.valueOf(eredmeny));
    }

    private String Karaktercsere(String oldal){
      return oldal.replace(",",".");
    }

    class Eredmeny{
        private double oldal1;
        private double oldal2;

        public Eredmeny(double oldal1, double oldal2) {
            this.oldal1 = oldal1;
            this.oldal2 = oldal2;
        }

        public double getOldal1() {
            return oldal1;
        }

        public double getOldal2() {
            return oldal2;
        }
    }


    private Eredmeny strtofloat(String oldal1, String oldal2 ){
        try{
            return new Eredmeny(Double.parseDouble(oldal1),Double.parseDouble(oldal2));
        }
        catch (Exception e){
            karakterhiba = true;

            eredmenytxt.setText("");
            Toast.makeText(getApplicationContext(),"Érvénytelen karakter!!!",Toast.LENGTH_LONG).show();
            return null;
        }

    }

    private void ertekcheck(double oldal, EditText text){
        if(oldal <= 0){
            Toast.makeText(getApplicationContext(),"Az oldalnak nagyobbnak kell lennie 0-nál!!!",Toast.LENGTH_LONG).show();
            ertekhiba = true;
            text.setBackgroundColor(0x99FF0000);
            eredmenytxt.setText("");
        }
    }
}
