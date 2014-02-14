package com.example.sockettest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Socket socket;
	public static final String SERVER_IP = "";
	public static final int SERVERPORT = 6000;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        new Thread(new ClientThread()).start();
    }
    
    public void onClick(View view){
    	try{
    		EditText et = (EditText) findViewById(R.id.EditText01);
    		String str = et.getText().toString();
    		PrintWriter out = new PrintWriter(new BufferedWriter(
    				new OutputStreamWriter(socket.getOutputStream())), 
    				true);
    		out.println(str);
    	} catch (Exception e){
    		e.printStackTrace();
    	}
    }
    
    class ClientThread implements Runnable{
    	@Override
    	public void run(){
    		try{
    			InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
    			socket = new Socket(serverAddr, SERVERPORT);
    		} catch (Exception e){
    			e.printStackTrace();
    		}
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
