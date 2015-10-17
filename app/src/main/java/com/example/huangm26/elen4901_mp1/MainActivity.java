package com.example.huangm26.elen4901_mp1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
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
    public static final int WIN  = 1;
    public static final int LOSE = 2;
    public static final int TIE = 3;
    private ImageView displayView;
    private TextView resultText;
    private int win;
    private int lose;
    private int tie;


    //for music service
    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
            Find all the components in the view
         */
        displayView = (ImageView) this.findViewById(R.id.imageView);
        resultText = (TextView) this.findViewById(R.id.result);
        Button mouse_button = (Button) this.findViewById(R.id.mouse);
        Button cat_button = (Button) this.findViewById(R.id.cat);
        Button elephant_button = (Button) this.findViewById(R.id.elephant);
        Button exit_button = (Button) this.findViewById(R.id.exit);
        /*
            Set on click listener for buttons
         */
        mouse_button.setOnClickListener(this);
        cat_button.setOnClickListener(this);
        elephant_button.setOnClickListener(this);
        exit_button.setOnClickListener(this);

        /*
            Init the computer output image to be question mark, and init win counter to 0
         */
        displayView.setImageResource(R.drawable.question);
        win = 0;
        lose = 0;
        tie = 0;

        //for the music service binder?
        doBindService();
        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        startService(music);

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
            case R.id.exit:
                mServ.stopMusic();
                finish();
            default:
//                Toast.makeText(this,"You didn't choose any animal", Toast.LENGTH_SHORT).show();
                break;
        }
        computerGenerate(animal_chosen,v);
    }

    /*
        This method is used to randomly generate an animal and compete it with the user's choice
        And it will display thte output
     */
    private void computerGenerate(int userChoice, View viewChosen)
    {
        Random rand = new Random();
        int ComputerChoose = rand.nextInt(3) + 1;
        Log.d("MainActivity", "Number " + ComputerChoose);
        switch(ComputerChoose){
            case MOUSE:
                Log.d("MainActivity", "Mouse case");
                displayView.setImageResource(0);
                displayView.setImageResource(R.drawable.mouse);
                if(userChoice == MOUSE)
//                    resultText.setText("This is a tie");
                    displayResult(TIE, viewChosen);
                if(userChoice == CAT)
//                    resultText.setText("Congratulations, you win!");
                    displayResult(WIN,viewChosen);
                if(userChoice == ELEPHANT)
//                    resultText.setText("Sorry, you lose...");
                    displayResult(LOSE,viewChosen);
                break;
            case CAT:
//                displayView.setImageDrawable(getResources().getDrawable(R.drawable.cat));
                Log.d("MainActivity", "Cat case");
                displayView.setImageResource(0);
                displayView.setImageResource(R.drawable.cat);
                if(userChoice == MOUSE)
//                    resultText.setText("Sorry, you lose...");
                    displayResult(LOSE, viewChosen);
                if(userChoice == CAT)
//                    resultText.setText("This is a tie");
                    displayResult(TIE,viewChosen);
                if(userChoice == ELEPHANT)
//                    resultText.setText("Congratulations, you win!");
                    displayResult(WIN,viewChosen);
                break;
            case ELEPHANT:
//                displayView.setImageDrawable(getResources().getDrawable(R.drawable.elephant));
                Log.d("MainActivity", "Elephant case");
                displayView.setImageResource(0);
                displayView.setImageResource(R.drawable.elephant);
                if(userChoice == MOUSE)
//                    resultText.setText("Congratulations, you win!");
                    displayResult(WIN, viewChosen);
                if(userChoice == CAT)
//                    resultText.setText("Sorry, you lose...");
                    displayResult(LOSE,viewChosen);
                if(userChoice == ELEPHANT)
//                    resultText.setText("This is a tie");
                    displayResult(TIE,viewChosen);
                break;
            default:
                break;
        }
    }

    /*
        This method builds an intent to start the DisplayResult activity.
        It also updates the current status in the textView below
     */
    private void displayResult(int result, View viewChosen)
    {
        Intent startResult = new Intent(this, DisplayResult.class);
        switch (result)
        {
            case WIN:
                win ++;
                startResult.putExtra("Result", WIN);
                break;
            case LOSE:
                lose ++;
                startResult.putExtra("Result", LOSE);
                break;
            case TIE:
                tie ++;
                startResult.putExtra("Result", TIE);
                break;
            default:
                break;
        }
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        startActivity(startResult);
        resultText.setText(String.format("The current result is: Win %d, Lose %d, Tie %d", win, lose, tie));
    }



    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }

}
