package com.example.cart_page;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApplePage extends AppCompatActivity {

    private View arrowBack;
    private TextView amountTextView, quantityTextView;
    private TextView sumTextView;
    private Button decrementButton1, incrementButton1, clearButton, decrementButton2, incrementButton2;
    private AppCompatButton payButton;

    private int defaultAmountToCharge = 500;
    private int amountCount = 0;
    private int quantityCount = 0;

    SharedPreferences mySharedPreference;

    String amount, quantity;
    private int sum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appple_page);

        mySharedPreference = getSharedPreferences(getString(R.string.my_preferences), Context.MODE_PRIVATE);


        initViews();
        initListeners();

    }

    public void initViews() {

        //textviews
        amountTextView = findViewById(R.id.tv_amount);
        quantityTextView = findViewById(R.id.quantityCounter1);
        sumTextView = findViewById(R.id.tv_sum_total);

        //Buttons
        decrementButton1 = findViewById(R.id.btn_decrement1);
        incrementButton1 = findViewById(R.id.btn_increment1);
        decrementButton2 = findViewById(R.id.btn_decrement2);
        incrementButton2 = findViewById(R.id.btn_increment2);


        //submit order button
        payButton = findViewById(R.id.orderButton);
        arrowBack = findViewById(R.id.arrow_back1);
        clearButton = findViewById(R.id.clear_button1);

        sum = amountCount + quantityCount;

        amountTextView.setText(amountTextView + "");
        quantityTextView.setText(quantityTextView + "");
        sumTextView.setText("#" + sum);


    }

    public void initListeners() {

        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ApplePage.this, MainActivity.class);
                startActivity(intent);
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amountTextView.setText("");


            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantityTextView.setText("");

            }
        });
        incrementButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amountCount = amountCount + defaultAmountToCharge;
                quantityCount = quantityCount + 1;
                quantityTextView.setText(quantityCount + "");

                amountTextView.setText(getString(R.string.naira_sign) + amountCount);

            }
        });
        decrementButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amountCount != 0) {
                    amountCount = amountCount - defaultAmountToCharge;
                    amountTextView.setText("#" + amountCount);

                    quantityCount = quantityCount - 1;
                    quantityTextView.setText(quantityCount + "");


                }

            }
        });
        incrementButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amountCount = amountCount + defaultAmountToCharge;
                quantityCount = quantityCount + 1;
                quantityTextView.setText(quantityCount + "");

                amountTextView.setText(getString(R.string.naira_sign) + amountCount);

            }
        });
        decrementButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amountCount != 0) {
                    amountCount = amountCount - defaultAmountToCharge;
                    amountTextView.setText("#" + amountCount);

                    quantityCount = quantityCount - 1;
                    quantityTextView.setText(quantityCount + "");


                }

            }
        });
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaysum();
                displayAConfirmPaymentDialog();
                getPrefLabelValues();
                onPause();


            }
        });
    }
    private void getPrefLabelValues() {
        amountCount = mySharedPreference.getInt(AppConstant.amount,0);
        quantityCount = mySharedPreference.getInt(AppConstant.quantity,0);
        sum = mySharedPreference.getInt(AppConstant.sum,0);

        amount= mySharedPreference.getString(AppConstant.amount,"");
        quantity = mySharedPreference.getString(AppConstant.quantity,"");
    }
    protected void onPause() {
        super.onPause();
        SharedPreferences mySharedPreference = getSharedPreferences(getString(R.string.my_preferences),Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = mySharedPreference.edit();
        myEditor.putInt(AppConstant.sum,amountCount+quantityCount);
        myEditor.apply();
    }
    public void displaysum() {
        String acceptableValues = "^([a-zA-Z@#?$+._\\d]*)$";
    }


    public void displayAConfirmPaymentDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.activity_appple_page, null, false);

        Button payButton = view.findViewById(R.id.orderButton);
        TextView messageTextview = view.findViewById(R.id.message_tv);
        messageTextview.setText( "you are about to pay" + amountCount);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAConfirmPaymentDialog2();
            }
        });
        alertDialog.setView(view);

        alertDialog.show();
    }
    private void displayAConfirmPaymentDialog2(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Do you want to make payment?");
        alertDialog.setTitle("Payment");
        alertDialog.setPositiveButton("Pay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ApplePage.this, " your payment successful for amount" + amountCount, Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ApplePage.this, "Cancel Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();

    }


}






