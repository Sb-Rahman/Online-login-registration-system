package com.retrofit.retrofitpractise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText name, email, password;
    Button signup;
    TextView showStatus;
    LinearLayout linearLayoutfor;
    AlertDialog.Builder alertdialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Signup BipulApp");
        //init all
        initializeall();

        //action
        linearLayoutfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRegistera(name.getText().toString(), email.getText().toString(), password.getText().toString());
            }
        });


    }

    private void userRegistera(String name, String email, String password) {
        String mobile = "not applicable";
        String address = "not applicable";


        Call<ApiModel> call = ApiController.getInstance()
                .getApi()
                .getregister(name, email, mobile, address, password);

        call.enqueue(new Callback<ApiModel>() {
            @Override
            public void onResponse(Call<ApiModel> call, Response<ApiModel> response) {
                ApiModel obj = response.body();
                String resuilt = obj.getStatus().trim();
                if (resuilt.equals("success")) {
                    //showStatus.setText("User Registered successfully!");
//success alert
                    alertdialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                    alertdialogBuilder.setTitle("Success!!");
                    alertdialogBuilder.setMessage("User Registered successfully!.Try to login. ");
                    alertdialogBuilder.setIcon(R.drawable.ic_success);
                    alertdialogBuilder.setCancelable(false);
                    alertdialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //back to login  page
                            finish();
                        }
                    });


                    AlertDialog alertDialog = alertdialogBuilder.create();
                    alertDialog.show();


                  //  showStatus.setTextColor(Color.GREEN);

                } else if (resuilt.equals("exits")) {
//                    showStatus.setText("User Already Registered, Try to login!");
//                    showStatus.setTextColor(Color.RED);

                    //show exits dialog
                    alertdialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                    alertdialogBuilder.setTitle("Exists!!");
                    alertdialogBuilder.setMessage("User Already Registered, Try to login! ");
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



                } else if (resuilt.equals("failed")) {
                    //show an alertdialog when it's failed
                    alertdialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                    alertdialogBuilder.setTitle("Ooops!");
                    alertdialogBuilder.setMessage("Registration Failed!");
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
            public void onFailure(Call<ApiModel> call, Throwable t) {
                //show an alertdialog when it's failed
                alertdialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                alertdialogBuilder.setTitle("Ooops!");
                alertdialogBuilder.setMessage("Somewent wrong, please try again later.");
                alertdialogBuilder.setIcon(R.drawable.ic_warning);
                alertdialogBuilder.setCancelable(false);
                alertdialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //back to loging page
                        finish();
                    }
                });


                AlertDialog alertDialog = alertdialogBuilder.create();
                alertDialog.show();
//                showStatus.setText("Something went wrong try again later.");
//                showStatus.setTextColor(Color.RED);
            }
        });
    }

    //init medhod
    private void initializeall() {

        name = findViewById(R.id.signup_name);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        signup = findViewById(R.id.btn_signup);
        showStatus = findViewById(R.id.show_status);
        linearLayoutfor = findViewById(R.id.backtologin);


    }
}