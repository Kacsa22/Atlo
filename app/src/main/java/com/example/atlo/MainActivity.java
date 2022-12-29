package com.example.atlo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText aText;
    EditText bText;
    EditText cText;
    EditText alfaText;
    EditText betaText;
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
                    imm.hideSoftInputFromWindow(bText.getWindowToken(), 0);
                }
            }
        });
    }

    void initGUI(){
        aText      = findViewById(R.id.aText);
        bText      = findViewById(R.id.bText);
        cText      = findViewById(R.id.cText);
        alfaText   = findViewById(R.id.alfaText);
        betaText   = findViewById(R.id.betaText);
        main       = findViewById(R.id.main);
    }

    public void click(View v){
        Eredmeny eredmeny = new Eredmeny(aText.getText().toString(), bText.getText().toString(), cText.getText().toString(), alfaText.getText().toString(), betaText.getText().toString());
    }

    public void clearAll(View v){
        aText.setText("");
        bText.setText("");
        cText.setText("");
        alfaText.setText("");
        betaText.setText("");
    }

    public void clearA(View v){
        aText.setText("");
    }
    public void clearB(View v){
        bText.setText("");
    }
    public void clearC(View v){
        cText.setText("");
    }
    public void clearAlfa(View v){
        alfaText.setText("");
    }
    public void clearBeta(View v){
        betaText.setText("");
    }

    class Eredmeny{

        Double a,b,c,alfa,beta;
        Boolean hiba = false;

        public Eredmeny(String a, String b, String c, String alfa, String beta){
            this.a = konvertal(a);
            this.b = konvertal(b);
            this.c = konvertal(c);
            this.alfa = konvertal(alfa);
            this.beta = konvertal(beta);
            if (!hiba){
                szamol();
            }
            if (!hiba){
                kiir();
            }
        }

        public void szamol(){
            int i = 0;
            if (this.a != 0.0){
                i++;
            }
            if (this.b != 0.0){
                i++;
            }
            if (this.c != 0.0){
                i++;
            }
            if (this.alfa != 0.0){
                i++;
            }
            if (this.beta != 0.0){
                i++;
            }
            if (i < 2){
                hiba = true;
                Toast.makeText(MainActivity.this, "Túl kevés adatot adott meg", Toast.LENGTH_SHORT).show();
            }
            else if (i == 2){
                if (this.a != 0.0 && this.b != 0.0){
                    if(this.a <= 0.0){
                        hiba = true;
                        Toast.makeText(MainActivity.this, "Befogónak nagyobbnak kell lennie 0-nál", Toast.LENGTH_SHORT).show();
                    }
                    if(this.b <= 0.0){
                        hiba = true;
                        Toast.makeText(MainActivity.this, "Befogónak nagyobbnak kell lennie 0-nál", Toast.LENGTH_SHORT).show();
                    }
                    if (!hiba) {
                        this.c = atfogoSzamol(this.a, this.b);
                        this.alfa = szogSzamolBefogobol(this.a, this.b);
                        this.beta = szogSzamolBefogobol(this.b, this.a);
                    }
                }
                else if (this.a != 0.0 && this.c != 0.0){
                    if(this.a <= 0.0){
                        hiba = true;
                        Toast.makeText(MainActivity.this, "Befogónak nagyobbnak kell lennie 0-nál", Toast.LENGTH_SHORT).show();
                    }
                    if(this.c <= 0.0){
                        hiba = true;
                        Toast.makeText(MainActivity.this, "Átfogónak nagyobbnak kell lennie 0-nál", Toast.LENGTH_SHORT).show();
                    }
                    if(this.c <= this.a) {
                        hiba = true;
                        Toast.makeText(MainActivity.this, "Átfogónak nagyobbnak kell lennie a befogónál", Toast.LENGTH_SHORT).show();
                    }
                    if (!hiba) {
                        this.b = befogoSzamolAtfogobol(this.a, this.c);
                        this.alfa = szogSzamolBefogobol(this.a, this.b);
                        this.beta = szogSzamolBefogobol(this.b, this.a);
                    }
                }
                else if (this.b != 0.0 && this.c != 0.0){
                    if(this.b <= 0.0){
                        hiba = true;
                        Toast.makeText(MainActivity.this, "Befogónak nagyobbnak kell lennie 0-nál", Toast.LENGTH_SHORT).show();
                    }
                    if(this.c <= 0.0){
                        hiba = true;
                        Toast.makeText(MainActivity.this, "Átfogónak nagyobbnak kell lennie 0-nál", Toast.LENGTH_SHORT).show();
                    }
                    if(this.c <= this.b) {
                        hiba = true;
                        Toast.makeText(MainActivity.this, "Átfogónak nagyobbnak kell lennie a befogónál", Toast.LENGTH_SHORT).show();
                    }
                    if (!hiba) {
                        this.a = befogoSzamolAtfogobol(this.b, this.c);
                        this.alfa = szogSzamolBefogobol(this.a, this.b);
                        this.beta = szogSzamolBefogobol(this.b, this.a);
                    }
                }
                else if (this.a != 0.0 && this.alfa != 0.0){
                    if(this.a <= 0.0){
                        hiba = true;
                        Toast.makeText(MainActivity.this, "Befogónak nagyobbnak kell lennie 0-nál", Toast.LENGTH_SHORT).show();
                    }
                    if(this.alfa <= 0.0 || this.alfa >= 90){
                        hiba = true;
                        Toast.makeText(MainActivity.this, "Szögnek nagyobbnak kell lennie 0-nál, és kisebbnek 90-nél ", Toast.LENGTH_SHORT).show();
                    }
                    if (!hiba) {
                        this.b = befogoSzamolSzogbol(a, alfa);
                        this.c = atfogoSzamol(this.a, this.b);
                        this.beta = 90 - this.alfa;
                    }
                }
                else if (this.b != 0.0 && this.alfa != 0.0){
                    if(this.b <= 0.0){
                        hiba = true;
                        Toast.makeText(MainActivity.this, "Befogónak nagyobbnak kell lennie 0-nál", Toast.LENGTH_SHORT).show();
                    }
                    if(this.alfa <= 0.0 || this.alfa >= 90){
                        hiba = true;
                        Toast.makeText(MainActivity.this, "Szögnek nagyobbnak kell lennie 0-nál, és kisebbnek 90-nél ", Toast.LENGTH_SHORT).show();
                    }
                    if (!hiba) {
                        this.beta = 90 - this.alfa;
                        this.a = befogoSzamolSzogbol(b, beta);
                        this.c = atfogoSzamol(this.a, this.b);
                    }
                }
                else if (this.a != 0.0 && this.beta != 0.0){
                    if(this.a <= 0.0){
                        hiba = true;
                        Toast.makeText(MainActivity.this, "Befogónak nagyobbnak kell lennie 0-nál", Toast.LENGTH_SHORT).show();
                    }
                    if(this.beta <= 0.0 || this.beta >= 90){
                        hiba = true;
                        Toast.makeText(MainActivity.this, "Szögnek nagyobbnak kell lennie 0-nál, és kisebbnek 90-nél ", Toast.LENGTH_SHORT).show();
                    }
                    if (!hiba) {
                        this.alfa = 90 - this.beta;
                        this.b = befogoSzamolSzogbol(a, alfa);
                        this.c = atfogoSzamol(this.a, this.b);
                    }
                }
                else if (this.b != 0.0 && this.beta != 0.0){
                    if(this.b <= 0.0){
                        hiba = true;
                        Toast.makeText(MainActivity.this, "Befogónak nagyobbnak kell lennie 0-nál", Toast.LENGTH_SHORT).show();
                    }
                    if(this.beta <= 0.0 || this.beta >= 90){
                        hiba = true;
                        Toast.makeText(MainActivity.this, "Szögnek nagyobbnak kell lennie 0-nál, és kisebbnek 90-nél ", Toast.LENGTH_SHORT).show();
                    }
                    if (!hiba) {
                        this.a = befogoSzamolSzogbol(b, beta);
                        this.c = atfogoSzamol(this.a, this.b);
                        this.alfa = 90 - this.beta;
                    }
                }
                else if (this.c != 0.0 && this.alfa != 0.0){
                    if(this.c <= 0.0){
                        hiba = true;
                        Toast.makeText(MainActivity.this, "Átfogónak nagyobbnak kell lennie 0-nál", Toast.LENGTH_SHORT).show();
                    }
                    if(this.alfa <= 0.0 || this.alfa >= 90){
                        hiba = true;
                        Toast.makeText(MainActivity.this, "Szögnek nagyobbnak kell lennie 0-nál, és kisebbnek 90-nél ", Toast.LENGTH_SHORT).show();
                    }
                    if (!hiba) {
                        this.a = szogSzamolAtfogobol2(this.c, this.alfa);
                        this.beta = 90 - this.alfa;
                        this.b = befogoSzamolSzogbol(this.a, this.alfa);
                    }
                }
                else if (this.c != 0.0 && this.beta != 0.0){
                    if(this.c <= 0.0){
                        hiba = true;
                        Toast.makeText(MainActivity.this, "Átfogónak nagyobbnak kell lennie 0-nál", Toast.LENGTH_SHORT).show();
                    }
                    if(this.beta <= 0.0 || this.beta >= 90){
                        hiba = true;
                        Toast.makeText(MainActivity.this, "Szögnek nagyobbnak kell lennie 0-nál, és kisebbnek 90-nél ", Toast.LENGTH_SHORT).show();
                    }
                    if (!hiba) {
                        this.alfa = 90 - this.beta;
                        this.a = szogSzamolAtfogobol2(this.c, this.alfa);
                        this.b = befogoSzamolSzogbol(this.a, this.alfa);
                    }
                }
                else if(this.alfa != 0.0 && this.beta != 0.0){
                    hiba = true;
                    Toast.makeText(MainActivity.this, "Két szögből nem lehet egyértelmű háromszöget készíteni", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                hiba = true;
                Toast.makeText(MainActivity.this, "Túl sok adatot adott meg", Toast.LENGTH_SHORT).show();
            }

        }

        public void kiir(){
            aText.setText(kerekit(this.a,3));
            bText.setText(kerekit(this.b,3));
            cText.setText(kerekit(this.c,3));
            alfaText.setText(kerekit(this.alfa,3));
            betaText.setText(kerekit(this.beta,3));
        }

        Double atfogoSzamol(Double befogo1, Double befogo2){
            return Math.sqrt((befogo1 * befogo1) + (befogo2*befogo2));
        }

        Double befogoSzamolAtfogobol(Double befogo1, Double atfogo){
            return Math.sqrt((atfogo * atfogo) - (befogo1 * befogo1));
        }

        Double befogoSzamolSzogbol(Double befogo, Double szog){
            return befogo / (Math.tan(Math.toRadians(szog)));
        }

        Double szogSzamolBefogobol(Double szemkoztiOldal, Double melletiOldal){
            return Math.toDegrees(Math.atan(szemkoztiOldal/melletiOldal));
        }

        Double szogSzamolAtfogobol2(Double atfogo, Double szog){
           return (Math.sin(Math.toRadians(szog))) * atfogo;
        }

        String kerekit(Double szam, Integer jegy){
            szam = szam * Math.pow(10,jegy);
            szam = (double) Math.round(szam);
            szam = szam / Math.pow(10,jegy);
            return szam.toString();
        }

        Double konvertal(String szam){
            Double kimenet;
            if (szam.isEmpty()){
                szam = "0.0";
            }
            try {
                kimenet = Double.parseDouble(szam);
                return kimenet;
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Szám formátum hiba", Toast.LENGTH_SHORT).show();
                hiba = true;
                return 0.0;
            }
        }
    }
}