package com.amostrone.akash.lorsicalculator;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.amostrone.akash.lorsicalculator.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    int count=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_lorentz, R.id.navigation_spi)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public void spiclick(View view) {
        ConstraintLayout lorentz_home = findViewById(R.id.container);
        lorentz_home.setBackgroundColor(getResources().getColor(R.color.white));
        //Vibration
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(80);

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.navigation_spi);
    }

    public void spical(View view) {
        ConstraintLayout lorentz_home = findViewById(R.id.container);
        lorentz_home.setBackgroundColor(getResources().getColor(R.color.white));
        //Vibration
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(80);

        TextView cur_time = findViewById(R.id.cur_time);
        TextView spi_ans = findViewById(R.id.spi_ans);

        SimpleDateFormat dateFormat= new SimpleDateFormat("hh:mm:ss a");
        String dateString = dateFormat.format(new Date());
        //Toast.makeText(this, dateString, Toast.LENGTH_SHORT).show();
        cur_time.setText("Current Time is " + dateString);

        float h,m,s,ans=0;
        h=Float.parseFloat(dateString.substring(0,2));
        m=Float.parseFloat(dateString.substring(3,5));
        s=Float.parseFloat(dateString.substring(6,8));

        ans=1/(m*m*m + s);
        for(int i=2;i<=h;i++)ans*=i;

        spi_ans.setText("SPI Factor is "+ans);

        updateTextView();
    }

    public void lorclick(View view) {
        ConstraintLayout lorentz_home = findViewById(R.id.container);
        lorentz_home.setBackgroundColor(getResources().getColor(R.color.white));
        count=3;
        //Vibration
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(80);

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.navigation_lorentz);
    }

    public void lorcal(View view) {
        ConstraintLayout lorentz_home = findViewById(R.id.container);
        lorentz_home.setBackgroundColor(getResources().getColor(R.color.white));
        //Vibration
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(80);

        TextView ans=findViewById(R.id.lor_ans);
        EditText v=findViewById(R.id.lor_input);
        if(TextUtils.isEmpty(v.getText().toString())) {
            v.setError("Enter Velocity Value");
            return;
        }
        double x=(Double.parseDouble(v.getText().toString()))/3e8;
        double an= 1/(Math.sqrt(1-x*x));
        ans.setText("Answer is "+an);
    }

    public void lorprac(View view){

        ConstraintLayout lorentz_home = findViewById(R.id.container);

        //Vibration
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(80);

        EditText v=findViewById(R.id.lor_prac_vel);
        EditText s=findViewById(R.id.lor_prac_spi);
        if(TextUtils.isEmpty(s.getText().toString())) {
            s.setError("Enter Spi Value");
            return;
        }
        if(TextUtils.isEmpty(v.getText().toString())) {
            v.setError("Enter Velocity Value");
            return;
        }
        double vel=(Double.parseDouble(v.getText().toString()))/3e8;
        double ans= 1/(Math.sqrt(1-vel*vel));
        double spi=Double.parseDouble(s.getText().toString());
        if((Math.abs(ans-spi))<1e-6){
            count=3;
            lorentz_home.setBackgroundColor(getResources().getColor(R.color.right));
            Toast.makeText(this, "Correct Ans", Toast.LENGTH_SHORT).show();
        }
        else {
            count--;
            lorentz_home.setBackgroundColor(getResources().getColor(R.color.wrong));
            if(count>0)
            Toast.makeText(this, "Wrong Ans "+count+ " Chances Remaining", Toast.LENGTH_SHORT).show();
            else{
                count=3;
                Toast.makeText(this, "Sorry you answers were wrong. Correct Answer is "+ans, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void updateTextView() {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView cur_time = findViewById(R.id.cur_time);
                TextView spi_ans = findViewById(R.id.spi_ans);

                SimpleDateFormat dateFormat= new SimpleDateFormat("hh:mm:ss a");
                String dateString = dateFormat.format(new Date());
                //Toast.makeText(this, dateString, Toast.LENGTH_SHORT).show();
                cur_time.setText("Current Time is " + dateString);

                float h,m,s,ans=0;
                h=Float.parseFloat(dateString.substring(0,2));
                m=Float.parseFloat(dateString.substring(3,5));
                s=Float.parseFloat(dateString.substring(6,8));

                ans=1/(m*m*m + s);
                for(int i=2;i<=h;i++)ans*=i;

                spi_ans.setText("SPI Factor is "+ans);
            }
        });

    }
}