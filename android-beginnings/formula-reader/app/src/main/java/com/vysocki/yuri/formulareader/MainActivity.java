package com.vysocki.yuri.formulareader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    char plus = '+';
    char minus = '-';
    int startingPosition = 0;
    int endPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        String finalResult = calculateFormula("2d10+8-1d4+100d8-7");
        textView.setText(finalResult);

    }

    public int calculateDiceValues(String diceString, int dCharPosition) {
        Random r = new Random();
        int finalDiceValue = 0;
        int beforeDValue;
        int afterDValue;
        int minValueToRandomize = 1;
        int maxValueToRandomize;
        startingPosition = 0;
        endPosition = diceString.length();

        // read 'dice substrings' of the formula
        // separate values before and after 'd' symbol
        // convert separated values to integers and calculate them correctly

        beforeDValue = Integer.parseInt(diceString.substring(startingPosition, dCharPosition));
        afterDValue = Integer.parseInt(diceString.substring(dCharPosition+1, endPosition));
        maxValueToRandomize = afterDValue;

        for (int i = 1; i <= beforeDValue; i++) {
            int rollResult;
            rollResult = r.nextInt((maxValueToRandomize - minValueToRandomize) + 1) + minValueToRandomize;
            finalDiceValue = finalDiceValue + rollResult;
        }

        return finalDiceValue;
    }

    public ArrayList<String> formulaSeparator(String formula) {
        ArrayList<String> formulaStringParts = new ArrayList<>();

        //separate formula into the individual string parts
        //and put them into string array

        for (int i = 0; i < formula.length(); i++) {
            if ((formula.charAt(i) == plus) || (formula.charAt(i) == minus)) {
                endPosition = i;
                formulaStringParts.add(formula.substring(startingPosition, endPosition));
                startingPosition = i+1;
            }
        }

        endPosition = formula.length();
        formulaStringParts.add(formula.substring(startingPosition, endPosition));

        return formulaStringParts;
    }

    public ArrayList<String> formulaSignsKeeper(String formula) {
        ArrayList<String> formulaSigns = new ArrayList<>();

        // read all '+" and '-' signs into the separate array

        for (int i = 0; i < formula.length(); i++) {
            if ((formula.charAt(i) == plus) || (formula.charAt(i) == minus)) {
                formulaSigns.add(Character.toString(formula.charAt(i)));
            }
        }

        return formulaSigns;
    }

    public ArrayList<Integer> formulaPartsConverter(ArrayList<String> formulaStringParts) {
        ArrayList<Integer> formulaIntParts = new ArrayList<>();
        char diceChar = 'd';

        //convert numerical parts into integer
        // and call methods to generate numbers on 'dice' parts
        // and convert them to integers as well

        for (int i = 0; i < formulaStringParts.size(); i++) {
            String currentStringPart = formulaStringParts.get(i);
            int currentIntPart;
            boolean isDice = false;
            int dCharPosition = 0;

            for (int y = 0; y < currentStringPart.length(); y++) {
                if (currentStringPart.charAt(y) == diceChar) {
                    isDice = true;
                    dCharPosition = y;
                }
            }

            if (!isDice) {
                currentIntPart = Integer.parseInt(currentStringPart);
            } else {
                currentIntPart = calculateDiceValues(currentStringPart, dCharPosition);
            }
            formulaIntParts.add(currentIntPart);
            isDice = false;
        }

        return formulaIntParts;
    }

    public int formulaPartsCombiner(ArrayList<Integer> formulaIntParts, ArrayList<String> formulaSigns) {
        int finalIntResult = formulaIntParts.get(0);

        //calculate the final result value depends on '+' and '-' signs

        for (int i = 1; i <= formulaSigns.size(); i++) {
            if (formulaSigns.get(i-1).charAt(0) == plus) {
                finalIntResult = finalIntResult + formulaIntParts.get(i);
            } else {
                finalIntResult = finalIntResult - formulaIntParts.get(i);
            }
        }

        return finalIntResult;
    }

    public String calculateFormula(String formula) {

        // call all methods that perform actions with formula and return result to display

        ArrayList<String> formulaStringParts = new ArrayList<>();
        ArrayList<Integer> formulaIntParts = new ArrayList<>();
        ArrayList<String> formulaSigns = new ArrayList<>();

        formulaStringParts = formulaSeparator(formula);
        formulaSigns = formulaSignsKeeper(formula);
        formulaIntParts = formulaPartsConverter(formulaStringParts);
        String finalStringResult = Integer.toString(formulaPartsCombiner(formulaIntParts, formulaSigns));
        return finalStringResult;
    }
}

