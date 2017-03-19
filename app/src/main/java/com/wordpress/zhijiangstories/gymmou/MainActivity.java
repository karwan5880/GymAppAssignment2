package com.example.gymappassignment;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        LinearLayout ll1 = new LinearLayout(this);
        ll1.setOrientation(LinearLayout.VERTICAL);

        TextView tv1 = new TextView(this);
        tv1.setText("hello from github, and kw. ");
        TextView tv2 = new TextView(this);
        tv2.setText("let say i add one line of code here ");
        //ok let say i add a comment here

        ll1.addView(tv1);
        ll1.addView(tv2);
        
        
        final EditText et1 = new EditText(this);
        
        ll1.addView(et1);      

        MySqlHelper mysqlhelper;
        
        Button bt1 = new Button(this);
        bt1.setText("Submit");   
        bt1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if(et1.getText().toString().matches("")){
					Toast.makeText(MainActivity.this, "text", Toast.LENGTH_SHORT).show();
				}
				else{
					int value = Integer.valueOf(et1.getText().toString());
					
					MySqlHelper mysqlhelper;
					
			        mysqlhelper = new MySqlHelper(MainActivity.this);
			        mysqlhelper.openToWrite();
			        mysqlhelper.insert(value);
			        mysqlhelper.close();
				}
				
				
			}
        });
        ll1.addView(bt1);     
        
        

        
        mysqlhelper = new MySqlHelper(this);
        mysqlhelper.openToRead();
        String mycontent = mysqlhelper.queueAll();
        mysqlhelper.close();
        
        
        
        
        
        
        setContentView(ll1);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
