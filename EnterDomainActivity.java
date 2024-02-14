package com.example.ping;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ping.MainActivity;
import com.example.ping.PingResultActivity;
import com.example.ping.R;


import android.content.Context;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;

public class EnterDomainActivity extends AppCompatActivity {

    public static class PingResult {
        private boolean success;
        private String address;
        private long roundTripTime; // Changed to long for storing milliseconds
        public String host;
        public String net;


        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        // Getters and setters
        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getRoundTripTime() {
            return roundTripTime;
        }

        public void setRoundTripTime(long roundTripTime) {
            this.roundTripTime = roundTripTime;
        }

        public static String convertToString(PingResult p){
            String newString = "address : " + p.address + "\n roundtrip : " + p.roundTripTime + "\n success:" + p.isSuccess();
            return newString;
        }

    }
    public PingResult ping(String domain) {
        PingResult result = new PingResult();
        try {
            InetAddress address = InetAddress.getByName(domain);
            long startTime = System.currentTimeMillis();
            boolean reachable = address.isReachable(5000); // Timeout set to 5 seconds
            long endTime = System.currentTimeMillis();

            if (reachable) {
                result.setSuccess(true);
                result.setAddress(address.getHostAddress());
                String roundTripTimeStr = String.valueOf(endTime - startTime);
                result.setRoundTripTime(Long.parseLong(roundTripTimeStr));
            } else {
                result.setSuccess(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setAddress("");
            result.setRoundTripTime(-1);
        }
        return result;
    }


    public void funcPass(String checkRes){
        Intent intent = new Intent(this, PingResultActivity.class);
        intent.putExtra(PingResultActivity.CHECKRES, checkRes);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        final EditText editTextText = findViewById(R.id.editTextText);
        Button pingButton = findViewById(R.id.button3);

        EnterDomainActivity object = this;

        pingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                String domain = editTextText.getText().toString();
                PingResult pingResult = ping(domain);
                String checkRes = "DEFAULT";
                if(pingResult.isSuccess()) {

                    String newString = PingResult.convertToString(pingResult);
                    checkRes = newString;
                    String address = pingResult.getAddress();
                    long roundTripTime = pingResult.getRoundTripTime();
                }
                else{
                    checkRes = "False";
                }

                funcPass(checkRes);

            }
            });
        ;

        Button Back = findViewById(R.id.button4);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EnterDomainActivity.this, MainActivity.class));
            }
        });
    }
}
