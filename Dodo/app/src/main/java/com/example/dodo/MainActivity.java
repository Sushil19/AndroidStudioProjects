package com.example.dodo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3] ;
    private boolean player1turn = true ;
    private int roundcount ;
    private  int player1points ;
    private int player2points ;

    private TextView textViewplayer1 ;
    private TextView textViewplayer2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewplayer1 = findViewById(R.id.text1);
        textViewplayer2 = findViewById(R.id.text2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonsid = "button_" + i + j;
                int resid = getResources().getIdentifier(buttonsid, "id", getPackageName());
                buttons[i][j] = findViewById(resid);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.resetbtn);
        buttonReset.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        if (player1turn){
            ((Button) view).setText("X");
        }else {
            ((Button) view).setText("O");
        }

        roundcount++;

        if (checkwin()) {
            if (player1turn) {
                player1wins();
            }
            else{
                player2wins();
            }
        }else if (roundcount == 9){
            draw();
        }else {
            player1turn = ! player1turn;
        }
    }

    private boolean checkwin(){
        String[][] field = new  String[3][3] ;

        for (int i = 0; i<3 ; i++){
            for (int j = 0; j<3 ; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i =0 ; i<3 ; i++){
            if (field[i][0].equals(field[i][1])
                && field[i][0].equals(field[i][2])
                && !field[i][0].equals("")){
                return  true ;
            }
        }

        for (int i =0 ; i<3 ; i++){
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")){
                return  true ;
            }
        }

        for (int i =0 ; i<3 ; i++){
            if (field[0][0].equals(field[1][1])
                    && field[0][0].equals(field[2][2])
                    && !field[0][0].equals("")){
                return  true ;
            }
        }

        for (int i =0 ; i<3 ; i++){
            if (field[0][2].equals(field[1][1])
                    && field[0][2].equals(field[2][0])
                    && !field[0][2].equals("")){
                return  true ;
            }
        }

        return false ;
    }

    private void player1wins(){
        player1points++;
        Toast.makeText(this, "Player 1 wins", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2wins(){
        player2points++;
        Toast.makeText(this, "Player 2 wins", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw(){
        Toast.makeText(this, "Draw !!!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText(){
        textViewplayer1.setText("Player 1 : " + player1points);
        textViewplayer2.setText("Player 2 : " + player2points);
    }
    private void resetBoard(){
        for (int i = 0 ; i<3 ; i++){
            for (int j = 0 ; j<3 ; j++){
                buttons[i][j].setText("");
            }
        }
        roundcount = 0;
        player1turn = true ;
    }

    private  void resetGame(){
        player1points = 0;
        player2points = 0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundcount", roundcount);
        outState.putInt("player1points", player1points);
        outState.putInt("player2points", player2points);
        outState.putBoolean("player1turn", player1turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundcount = savedInstanceState.getInt("roundcount");
        player1points = savedInstanceState.getInt("player1points");
        player2points = savedInstanceState.getInt("player2points");
        player1turn = savedInstanceState.getBoolean("player1turn");
    }
}