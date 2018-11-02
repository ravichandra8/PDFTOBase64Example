package com.jd.pdfexample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button SignInbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SignInbutton = (Button) findViewById(R.id.click);
        SignInbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent3 = new Intent();
                intent3.setType("application/pdf");
                intent3.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent3, "Choose File to Upload.."), 1);


            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

            Uri content_describer = data.getData();

            if(content_describer.getPath().contains(".pdf")) {
                String filepath = null;

                int pos=content_describer.getPath().indexOf("/storage");

                filepath=content_describer.getPath().substring(pos);

                File file = new File(filepath);
                //get the path
                String s = encodeFileToBase64Binary(file);
                Log.d("base 64", s);

            }
            else{
                Toast.makeText(this, "Invaid file format", Toast.LENGTH_SHORT).show();
            }


        }
    }


    private String encodeFileToBase64Binary(File file){
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = Base64.encodeToString(bytes,Base64.DEFAULT).toString();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encodedfile;
    }

}
