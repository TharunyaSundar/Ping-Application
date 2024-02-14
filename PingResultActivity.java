package com.example.ping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class PingResultActivity extends AppCompatActivity {

    public static final String CHECKRES = "";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate((savedInstanceState));
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        TextView Result = findViewById(R.id.textViewResult);
        String msg = intent.getStringExtra(CHECKRES);
        // Getting the domain from intent extras
        //String pingResult = getIntent().getStringExtra("pingResult");
        EnterDomainActivity.PingResult pingResult = (EnterDomainActivity.PingResult) getIntent().getSerializableExtra("pingResult");
        if(pingResult != null){
            String resultText = getResultText(pingResult);
            Result.setText(resultText);
        }else {
            Result.setText("No ping result available.");
        }
        Result.setText(msg);


        // Button to go back to EnterDomainActivity
        Button Back = findViewById(R.id.button6);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PingResultActivity.this, EnterDomainActivity.class));
            }
        });

        // Button to go back to MainActivity
        Button MainMenu = findViewById(R.id.button5);
        MainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PingResultActivity.this, MainActivity.class));
            }
        });
    }
    private String getResultText(EnterDomainActivity.PingResult pingResult) {
        StringBuilder resultText = new StringBuilder();
        if (pingResult.isSuccess()) {
            resultText.append("Ping successful\n");
            resultText.append("Host: ").append(pingResult.getHost()).append("\n");
            resultText.append("Address: ").append(pingResult.getAddress()).append("\n");
            resultText.append("Round-trip time: ").append(pingResult.getRoundTripTime()).append(" ms\n");
        } else {
            resultText.append("Ping failed\n");
        }
        return resultText.toString();
    }
}


