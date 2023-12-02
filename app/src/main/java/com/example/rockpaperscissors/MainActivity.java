package com.example.rockpaperscissors;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView humanImageView;
    private ImageView computerImageView;
    private TextView computerScore;
    private TextView humanScore;
    private Button rockButton;
    private Button scissorButton;
    private Button paperButton;
    private int humanChoice;
    private int humanResult;
    private int computerResult;
    private AlertDialog.Builder gameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        paperButton.setOnClickListener(view -> {
            humanImageView.setImageResource(R.drawable.paper);
            humanChoice = 2;
            computerRandom();
            humanScore.setText(String.valueOf(humanResult));
            computerScore.setText(String.valueOf(computerResult));
            checkGameOver();
        });
        rockButton.setOnClickListener(view -> {
            humanImageView.setImageResource(R.drawable.rock);
            humanChoice = 1;
            computerRandom();
            humanScore.setText(String.valueOf(humanResult));
            computerScore.setText(String.valueOf(computerResult));
            checkGameOver();
        });
        scissorButton.setOnClickListener(view -> {
            humanImageView.setImageResource(R.drawable.scissors);
            humanChoice = 3;
            computerRandom();
            humanScore.setText(String.valueOf(humanResult));
            computerScore.setText(String.valueOf(computerResult));
            checkGameOver();
        });
    }

    public void checkGameOver() {
        if (humanResult == 3) {
            gameOver.setTitle("Győzelem")
                    .create()
                    .show();

        } else if (computerResult == 3) {
            gameOver.setTitle("Vereség")
                    .create()
                    .show();
        }
    }

    public void newGame() {
        humanResult = 0;
        computerResult = 0;
        humanScore.setText(String.valueOf(humanResult));
        computerScore.setText(String.valueOf(computerResult));
        humanImageView.setImageResource(R.drawable.rock);
        computerImageView.setImageResource(R.drawable.rock);
    }

    public void init() {
        humanImageView = findViewById(R.id.humanImageView);
        computerImageView = findViewById(R.id.computerImageView);
        computerScore = findViewById(R.id.computerScore);
        humanScore = findViewById(R.id.humanScore);
        rockButton = findViewById(R.id.rockButton);
        paperButton = findViewById(R.id.paperButton);
        scissorButton = findViewById(R.id.scissorButton);
        humanChoice = 0;
        humanResult = 0;
        computerResult = 0;
        gameOver = new AlertDialog.Builder(MainActivity.this);
        gameOver.setTitle("Győzelem")
                .setCancelable(false)
                .setMessage("Szeretnél újat játszani?")
                .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        newGame();
                    }
                })
                .create();
    }

    public void computerRandom() {
        Random random = new Random();
        int computerRandom = random.nextInt(3) + 1;

        switch (computerRandom) {
            case 1:
                computerImageView.setImageResource(R.drawable.rock);
                result(computerRandom);
                break;
            case 2:
                computerImageView.setImageResource(R.drawable.paper);
                result(computerRandom);
                break;
            case 3:
                computerImageView.setImageResource(R.drawable.scissors);
                result(computerRandom);
                break;
        }
    }

    public void result(int computerRandom) {
        if (humanChoice == computerRandom) {
            Toast.makeText(MainActivity.this, "Döntetlen", Toast.LENGTH_SHORT).show();

        } else if (humanChoice == 1 && computerRandom == 2) {
            Toast.makeText(MainActivity.this, "A gép nyert", Toast.LENGTH_SHORT).show();
            computerResult++;
        } else if (humanChoice == 1 && computerRandom == 3) {
            Toast.makeText(MainActivity.this, "A játékos nyert", Toast.LENGTH_SHORT).show();
            humanResult++;
        } else if (humanChoice == 2 && computerRandom == 1) {
            Toast.makeText(MainActivity.this, "A játékos nyert", Toast.LENGTH_SHORT).show();
            humanResult++;
        } else if (humanChoice == 2 && computerRandom == 3) {
            Toast.makeText(MainActivity.this, "A gép nyert", Toast.LENGTH_SHORT).show();
            computerResult++;
        } else if (humanChoice == 3 && computerRandom == 1) {
            Toast.makeText(MainActivity.this, "A gép nyert", Toast.LENGTH_SHORT).show();
            computerResult++;
        } else if (humanChoice == 3 && computerRandom == 2) {
            Toast.makeText(MainActivity.this, "A játékos nyert", Toast.LENGTH_SHORT).show();
            humanResult++;
        }
    }
}
