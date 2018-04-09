package com.vysocki.yuri.gameconnect3;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 - android player
    // 1 - apple player
    int currentPlayer = 0;

    // 2 - empty position
    int[] tokenPositions = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void setDefaultGameStatus(View view) {
        currentPlayer = 0;
        for (int i = 0; i < tokenPositions.length; i++) {
            tokenPositions[i] = 2;
        }
        // clearing ImageView's
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i= 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

        gridLayout.setVisibility(View.VISIBLE);

        Button playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setText(R.string.playAgainButton_start_over);

        TextView winnerTextView = findViewById(R.id.winnerTextView);
        winnerTextView.setVisibility(View.INVISIBLE);
    }

    public void setWinScreen(View view, int winPosition) {

        //checking which player is win and printing the winner
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        winnerTextView.setText(R.string.winnerTextView_apple);
        if (tokenPositions[winPosition] == 0) {
            winnerTextView.setText(R.string.winnerTextView_android);
        }
        winnerTextView.setVisibility(View.VISIBLE);

        //setting layout invisible
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setVisibility(View.INVISIBLE);

        //changing PlayAgain button text
        Button playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setText(R.string.playAgainButton);

    }

    public void setDrawScreen(View view) {

        //printing about draw
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        winnerTextView.setText(R.string.winnerTextView_draw);
        winnerTextView.setVisibility(View.VISIBLE);

        //setting layout invisible
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setVisibility(View.INVISIBLE);

        //changing PlayAgain button text
        Button playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setText(R.string.playAgainButton);

    }

    public void tokenPlacement(View view) {
        ImageView token = (ImageView) view;
        int tappedToken = Integer.parseInt(token.getTag().toString());

        //checking if position for token is empty
        if (tokenPositions[tappedToken] == 2) {
            tokenPositions[tappedToken] = currentPlayer;

            //checking which token to place and placing it
            if (currentPlayer == 0){
                token.setImageResource(R.drawable.bucket);
                currentPlayer = 1;
            } else {
                token.setImageResource(R.drawable.apple);
                currentPlayer = 0;
            }

        }

        for (int[] winPosition : winPositions) {

            //checking for win condition
            if (tokenPositions[winPosition[0]] == tokenPositions[winPosition[1]]
                && tokenPositions[winPosition[1]] == tokenPositions[winPosition[2]]
                && tokenPositions[winPosition[0]] != 2) {

                setWinScreen(view, winPosition[0]);

            } else {
                //checking for draw

                boolean gameIsOver = true;

                for (int tokenPosition : tokenPositions) {
                    if (tokenPosition == 2) { gameIsOver = false; }
                }

                if (gameIsOver) {
                    setDrawScreen(view);
                }
            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
