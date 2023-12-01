package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity1 extends AppCompatActivity {

    private Button[] buttons = new Button[9];
    private boolean isXTurn = true;
    private int roundCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < buttons.length; i++) {
            String buttonID = "button" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resID);
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonClick((Button) v);
                }
            });
        }
    }

    private void onButtonClick(Button clickedButton) {
        if (clickedButton.getText().length() > 0) {
            return;
        }

        roundCount++;
        if (isXTurn) {
            clickedButton.setText("X");
        } else {
            clickedButton.setText("O");
        }

        isXTurn = !isXTurn;

        if (roundCount >= 5) {
            checkWinCondition();
        }
    }

    private void checkWinCondition() {
        String[][] winConditions = {{"button0", "button1", "button2"},
                {"button3", "button4", "button5"},
                {"button6", "button7", "button8"},
                {"button0", "button3", "button6"},
                {"button1", "button4", "button7"},
                {"button2", "button5", "button8"},
                {"button0", "button4", "button8"},
                {"button2", "button4", "button6"}};

        for (String[] winCondition : winConditions) {
            String button0Text = buttons[Integer.parseInt(winCondition[0].substring(6))].getText().toString();
            String button1Text = buttons[Integer.parseInt(winCondition[1].substring(6))].getText().toString();
            String button2Text = buttons[Integer.parseInt(winCondition[2].substring(6))].getText().toString();

            if (button0Text.equals(button1Text) && button0Text.equals(button2Text) && !button0Text.equals("")) {
                displayWinMessage(button0Text + " wins!");
                return;
            }
        }

        if (roundCount == 9) {
            displayWinMessage("It's a draw!");
        }
    }

    private void displayWinMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Over")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startNewGame();
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void startNewGame() {
        for (Button button : buttons) {
            button.setText("");
        }

        isXTurn = true;
        roundCount = 0;
    }
}