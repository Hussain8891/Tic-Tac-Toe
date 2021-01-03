package hussainshaikh.com.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;

    private long pressback;
    private Toast backToast;

    int activePlayer = 0;
    int[] gameState = {2,2,2, 2,2,2, 2,2,2};

    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
                            {0,3,6}, {1,4,7}, {2,5,8},
                            {0,4,8}, {2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playerTap(View view) {
        Button buttonClick = (Button) view;
        int tappedButton = Integer.parseInt(buttonClick.getTag().toString());
        //for gameReset
        if (!gameActive){
            gameReset();
        }

        if (gameState[tappedButton] == 2){
            gameState[tappedButton] = activePlayer;
            buttonClick.setTranslationY(-1000f);
            if (activePlayer ==0){
                buttonClick.setText("x");
                activePlayer = 1;

                TextView status = findViewById(R.id.status_bar);
                status.setText("O's Turn - Tap to play");
            }else {
                buttonClick.setText("O");
                activePlayer = 0;

                TextView status = findViewById(R.id.status_bar);
                status.setText("x's Turn - Tap to play");
            }
            buttonClick.animate().translationYBy(1000f).setDuration(300);

        }
        //Check any player has won
        for (int[]winPosition: winPositions){
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
            gameState[winPosition[1]] == gameState[winPosition[2]] &&
            gameState[winPosition[0]] != 2){

                //Somebody has won
                String winner;
                gameActive = false;

                if (gameState[winPosition[0]] == 0){
                    winner = "X has winner";
                }else {
                    winner = "O has winner";
                }
                //Winner announcement update
                TextView updatestatus = findViewById(R.id.status_bar);
                updatestatus.setText(winner);

            }


        }

    }

    private void gameReset() {
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        ((Button) findViewById(R.id.btn_0)).setText(" ");
        ((Button) findViewById(R.id.btn_1)).setText(" ");
        ((Button) findViewById(R.id.btn_2)).setText(" ");
        ((Button) findViewById(R.id.btn_3)).setText(" ");
        ((Button) findViewById(R.id.btn_4)).setText(" ");
        ((Button) findViewById(R.id.btn_5)).setText(" ");
        ((Button) findViewById(R.id.btn_6)).setText(" ");
        ((Button) findViewById(R.id.btn_7)).setText(" ");
        ((Button) findViewById(R.id.btn_8)).setText(" ");

        TextView status = findViewById(R.id.status_bar);
        status.setText("x's Turn - Tap to play");

    }


    public void Restartgame(View view) {
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i <gameState.length; i++){
            gameState[i] = 2;

        }
        ((Button) findViewById(R.id.btn_0)).setText(" ");
        ((Button) findViewById(R.id.btn_1)).setText(" ");
        ((Button) findViewById(R.id.btn_2)).setText(" ");
        ((Button) findViewById(R.id.btn_3)).setText(" ");
        ((Button) findViewById(R.id.btn_4)).setText(" ");
        ((Button) findViewById(R.id.btn_5)).setText(" ");
        ((Button) findViewById(R.id.btn_6)).setText(" ");
        ((Button) findViewById(R.id.btn_7)).setText(" ");
        ((Button) findViewById(R.id.btn_8)).setText(" ");

        TextView Restrtgame = findViewById(R.id.status_bar);
        Restrtgame.setText("x's Turn - Tap to play");

    }

    //Exit Toast

    @Override
    public void onBackPressed() {
        if (pressback + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else {
            backToast = Toast.makeText(this,"Press back again to exit",Toast.LENGTH_SHORT);
            backToast.show();
        }
        pressback = System.currentTimeMillis();
    }
}