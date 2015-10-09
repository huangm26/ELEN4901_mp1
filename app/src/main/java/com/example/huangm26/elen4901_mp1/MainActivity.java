package com.example.huangm26.elen4901_mp1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int MOUSE = 1;
    public static final int CAT = 2;
    public static final int ELEPHANT = 3;
    private ImageView displayView;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        displayView = (ImageView) this.findViewById(R.id.imageView);
        resultText = (TextView) this.findViewById(R.id.result);
        Button mouse_button = (Button) this.findViewById(R.id.mouse);
        Button cat_button = (Button) this.findViewById(R.id.cat);
        Button elephant_button = (Button) this.findViewById(R.id.elephant);
        mouse_button.setOnClickListener(this);
        cat_button.setOnClickListener(this);
        elephant_button.setOnClickListener(this);
        displayView.setImageResource(R.drawable.question);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int animal_chosen = 0;
        switch (v.getId()){
            case R.id.mouse:
                animal_chosen = MOUSE;
                Toast.makeText(this,"You chose Mouse", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cat:
                animal_chosen = CAT;
                Toast.makeText(this,"You chose Cat", Toast.LENGTH_SHORT).show();
                break;
            case R.id.elephant:
                animal_chosen = ELEPHANT;
                Toast.makeText(this,"You chose Elephant", Toast.LENGTH_SHORT).show();
                break;
            default:
//                Toast.makeText(this,"You didn't choose any animal", Toast.LENGTH_SHORT).show();
                break;
        }
        Random rand = new Random();
        int ComputerChoose = rand.nextInt(3) + 1;
        Log.d("MainActivity", "Number " + ComputerChoose);
        switch(ComputerChoose){
            case MOUSE:
//                displayView.setImageDrawable(getResources().getDrawable(R.drawable.mouse));
                Log.d("MainActivity", "Mouse case");
                displayView.setImageResource(0);
                displayView.setImageResource(R.drawable.mouse);
                if(animal_chosen == MOUSE)
                    resultText.setText("This is a tie");
                if(animal_chosen == CAT)
                    resultText.setText("Congratulations, you win!");
                if(animal_chosen == ELEPHANT)
                    resultText.setText("Sorry, you lose...");
                break;
            case CAT:
//                displayView.setImageDrawable(getResources().getDrawable(R.drawable.cat));
                Log.d("MainActivity", "Cat case");
                displayView.setImageResource(0);
                displayView.setImageResource(R.drawable.cat);
                if(animal_chosen == MOUSE)
                    resultText.setText("Sorry, you lose...");
                if(animal_chosen == CAT)
                    resultText.setText("This is a tie");
                if(animal_chosen == ELEPHANT)
                    resultText.setText("Congratulations, you win!");
                break;
            case ELEPHANT:
//                displayView.setImageDrawable(getResources().getDrawable(R.drawable.elephant));
                Log.d("MainActivity", "Elephant case");
                displayView.setImageResource(0);
                displayView.setImageResource(R.drawable.elephant);
                if(animal_chosen == MOUSE)
                    resultText.setText("Congratulations, you win!");
                if(animal_chosen == CAT)
                    resultText.setText("Sorry, you lose...");
                if(animal_chosen == ELEPHANT)
                    resultText.setText("This is a tie");
                break;
            default:
                break;
        }
    }
}
