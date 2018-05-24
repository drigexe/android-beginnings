package com.vysocki.yuri.formulareader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        String myFinalResult = calculateFormula("2d10+8-1d4+100d8-7");
        textView.setText(myFinalResult);
    }

    public ArrayList<String> formulaSeparator(String formula) {
        ArrayList<String> formulaStringParts = new ArrayList<>();

        //separate formula into the individual string parts
        //and put them into string array

        return formulaStringParts;
    }

    public ArrayList<String> formulaSignsKeeper(String formula) {

        // read all '+" and '-' signs into the separate array

        ArrayList<String> formulaSigns = new ArrayList<>();
        return formulaSigns;
    }

    public ArrayList<Integer> formulaPartsConverter(ArrayList<String> formulaStringParts) {
        ArrayList<Integer> formulaIntParts = new ArrayList<>();

        //convert numerical parts into integer
        // and call methods to generate numbers on 'dice' parts
        // and convert them to strings as well

        return formulaIntParts;
    }

    public int formulaPartsCombiner(ArrayList<Integer> formulaIntParts, ArrayList<String> formulaSigns) {

        //calculate the final result value depends on '+' and '-' signs

        int iResult = 1;
        return iResult;
    }

    public String calculateFormula(String formula) {

        // call all methods that perform actions with formula and return result to display

        ArrayList<String> formulaStringParts = new ArrayList<>();
        ArrayList<Integer> formulaIntParts = new ArrayList<>();
        ArrayList<String> formulaSigns = new ArrayList<>();

        formulaStringParts = formulaSeparator(formula);
        formulaSigns = formulaSignsKeeper(formula);
        formulaIntParts = formulaPartsConverter(formulaStringParts);
        String sResult = Integer.toString(formulaPartsCombiner(formulaIntParts, formulaSigns));
        return sResult;
    }
}
