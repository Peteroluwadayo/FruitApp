package com.example.cart_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout imageAppleLayout;
    private LinearLayout imageBananaLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();
    }
    public void initViews() {
        imageAppleLayout = findViewById(R.id.layout1);
        imageBananaLayout = findViewById(R.id.layout2);

    }
    public void initListeners() {
        imageAppleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this,ApplePage.class);
                startActivity(myIntent);

            }
        });
        imageBananaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this,ApplePage.class);
                startActivity(myIntent);

            }
        });
    }
}