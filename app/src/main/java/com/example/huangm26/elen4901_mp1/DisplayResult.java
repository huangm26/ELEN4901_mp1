package com.example.huangm26.elen4901_mp1;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayResult extends AppCompatActivity implements View.OnClickListener {


    public static final int WIN  = 1;
    public static final int LOSE = 2;
    public static final int TIE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);
        int result = getIntent().getExtras().getInt("Result");
        TextView resultText = (TextView) this.findViewById(R.id.resultText);
        ImageView resultImage = (ImageView) this.findViewById(R.id.resultImage);
        Button returnButton = (Button) this.findViewById(R.id.Return);
        returnButton.setOnClickListener(this);
        switch (result)
        {
            case WIN:
                resultText.setText("Congratulations, you win!");
                resultImage.setImageResource(0);
                resultImage.setImageResource(R.drawable.win2);
                break;
            case LOSE:
                resultText.setText("Sorry, you lose...");
                resultImage.setImageResource(0);
                resultImage.setImageResource(R.drawable.lose);
                break;
            case TIE:
                resultText.setText("This is a tie");
                resultImage.setImageResource(0);
                resultImage.setImageResource(R.drawable.tie);
                break;
            default:
                break;
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.Return)
        {
            Log.d("DisplayResult", "Press Return");
            finish();
        }
    }
}
