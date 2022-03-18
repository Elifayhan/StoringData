package com.elifayhan.storingdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //küçük verileri kaydetmek için illa veri tabanı kullanmamıza gerek yok.
        //bunun için bir obje oluşturmuşlar. aşağıda obje oluşturacağız ama bu obje diğerleri gibi
        //new veya değer atamalarını direkt olarak yapabileceğimiz tarzda değil.
        //oluşturacağımız objeyi global oluşturuyoruz ki başka methodların içerisinde de kullanabilelim.

        sharedPreferences = this.getSharedPreferences("com.elifayhan.storingdata", Context.MODE_PRIVATE);
        editText =findViewById(R.id.editTextNumber);
        textView = findViewById(R.id.textView);
        //edit().putInt ile kaydettiğimiz veriyi uygulama kapatılıp açıldığı zaman geri almak istiyoruz.
        //bunu da şöyle yaparız:
        int storedAge= sharedPreferences.getInt("storedAge",0); //burada getInt ile kaydettiğimiz şeyi geri alıyoruz.
        //bizden anahtar kelime ve default değerlerini istiyor.
        //anahtar kelime edit.putInt te verdiğimiz şeyin birebir aynısı olmak zorunda.
        //default ise kayıtlı bir şey yoksa oraya hangi değeri gireceğimizi soruyor.
        //Tabiki bunu bir değişkene eşitledik ki o değişkeni kullanabilelim.
        if(storedAge==0){
            textView.setText("Your age: ");
        }
        else{
            textView.setText("Your age: " + storedAge);
        }

    }
    public void save(View view){
        if(!editText.getText().toString().matches("")){ //kullanıcı herhangi bir değer girdiyse
            int userAge= Integer.parseInt(editText.getText().toString());

            textView.setText("Your Age " + userAge); //textViewda userAge değeri gözükecek.
            sharedPreferences.edit().putInt("storedAge", userAge).apply(); //burada bir anahtar değer ve değer istiyor.
            //anahtar değere storedAge dedik, değerimiz de zaten userAge.
        }


    }
    public void delete(View view){
        //getInt ile kaydedilmiş veriyi tamamen silmek istersek delete tuşuna bastığımızda storedAge!=0 ise
        //silelim.
        int sharedData= sharedPreferences.getInt("storedAge",0);
        if(sharedData!=0){
            sharedPreferences.edit().remove("storedAge").apply();
            textView.setText("Your age: ");
        }

    }
}