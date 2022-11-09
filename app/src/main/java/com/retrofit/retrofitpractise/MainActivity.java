package com.retrofit.retrofitpractise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //global variable declare
    Button button;
   LinearLayout linearLayout;
   EditText editTextemail, editTextPas;
AlertDialog.Builder alertdialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Retrofit");
//variable Initialilze
        button = (Button) findViewById(R.id.login_btn);
        linearLayout=(LinearLayout) findViewById(R.id.linearlayout_reg);
        editTextemail=findViewById(R.id.editTextTextEmailAddress);
        editTextPas=findViewById(R.id.editTextTextPassword);

//verify previous login
        VerifyPreviousLogin();


//set onclicklistener

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "button clicked", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                processLogin(editTextemail.getText().toString(),editTextPas.getText().toString());

            }
        });
                

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));

            }
        });

    }

    private void VerifyPreviousLogin() {

        SharedPreferences sp=getSharedPreferences("credentials",MODE_PRIVATE);
        if (sp.contains("username")){
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));

        }
    }

    private void processLogin(String email, String password) {

      Call<LoginresponseModel> call=ApiController.getInstance()
              .getApi()
              .getlogin(email,password);
      call.enqueue(new Callback<LoginresponseModel>() {
          @Override
          public void onResponse(Call<LoginresponseModel> call, Response<LoginresponseModel> response) {
              LoginresponseModel obj=response.body();
              String result=obj.getStatus();

              if (result.equals("user_found")){
                  SharedPreferences sp=getSharedPreferences("credentials",MODE_PRIVATE);
                  SharedPreferences.Editor editor=sp.edit();
                  editor.putString("username",email);
                  editor.putString("password",password);
                  editor.commit();
                  editor.apply();
                  startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                  finish();

              }else if (result.equals("user_not_found")){
                  //Toast.makeText(MainActivity.this, "Invalid username and Password", Toast.LENGTH_SHORT).show();

                  //show an alertdialog when given wrong username or password
                  alertdialogBuilder = new AlertDialog.Builder(MainActivity.this);
                  alertdialogBuilder.setTitle("Invalid!");
                  alertdialogBuilder.setMessage("Invalid username or Password!");
                  alertdialogBuilder.setIcon(R.drawable.ic_warning);
                  alertdialogBuilder.setCancelable(false);
                  alertdialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialogInterface, int i) {
                          //back to login page
                          finish();
                      }
                  });


                  AlertDialog alertDialog = alertdialogBuilder.create();
                  alertDialog.show();

              }
              
          }

          @Override
          public void onFailure(Call<LoginresponseModel> call, Throwable t) {
             // Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
              //show an alertdialog if something went wrong
              alertdialogBuilder = new AlertDialog.Builder(MainActivity.this);
              alertdialogBuilder.setTitle("Something went wrong!");
              alertdialogBuilder.setMessage("Something went wrong, please check your internet connection and try again!");
              alertdialogBuilder.setIcon(R.drawable.ic_warning);
              alertdialogBuilder.setCancelable(false);
              alertdialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      //back to login page
                      finish();
                  }
              });


              AlertDialog alertDialog = alertdialogBuilder.create();
              alertDialog.show();

          }
      });
    }


}