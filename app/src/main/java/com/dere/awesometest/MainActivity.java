package com.dere.awesometest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Objects;

import Utilities.QRCodeGenerator;

public class MainActivity extends AppCompatActivity{

    protected EditText dec1,dec2,dec3,dec4,dec5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize the edit text input controls
        //we'll gather the individual numbers from the inputs and we'll concatenate them to make a code
        dec1 = findViewById(R.id.inputCode1);
        dec2 = findViewById(R.id.inputCode2);
        dec3 = findViewById(R.id.inputCode3);
        dec4 = findViewById(R.id.inputCode4);
        dec5 = findViewById(R.id.inputCode5);

        dec1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length()>0){
                    dec2.requestFocus();
                }
            }
        });
        dec2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length()>0){
                    dec3.requestFocus();
                }
            }
        });
        dec3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length()>0){
                    dec4.requestFocus();
                }
            }
        });
        dec4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length()>0){
                    dec5.requestFocus();
                }
            }
        });

        //Generate QR code on Alert Dialogue after the Export button is clicked

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AppCompatButton exportButton = findViewById(R.id.btnExport);
        exportButton.setOnClickListener(view -> {
            LayoutInflater inflater = this.getLayoutInflater();
            View alertView = inflater.inflate(R.layout.exportalert,null);
            builder.setView(alertView);
            builder.show();
            AppCompatButton generateButton = alertView.findViewById(R.id.generate);
            AppCompatEditText transferQuantity = alertView.findViewById(R.id.amountPrompt);
            AppCompatImageView qrcode = alertView.findViewById(R.id.qrcode);
            QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();
            generateButton.setOnClickListener(view1 -> {
                qrcode.setVisibility(View.VISIBLE);
                qrcode.setImageBitmap(qrCodeGenerator.generateQRCode(Objects.requireNonNull(transferQuantity.getText()).toString()));
            });
        });


        //Read QR code and add the points to the new user

        AppCompatImageButton scanner = findViewById(R.id.scan);
        scanner.setOnClickListener(view -> {
            IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
            intentIntegrator.setPrompt("Scanning for points");
            //intentIntegrator.setOrientationLocked(true);
            intentIntegrator.setOrientationLocked(false);
            intentIntegrator.initiateScan();
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getBaseContext(), "Scanning Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, intentResult.getContents(), Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}