package com.e.testappgre;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;

public class LearnActivity extends AppCompatActivity {

    TextView showWord, theWord, theMeaning, labm, labl;
    Button yes, no;
    ProgressBar master, learn;

    int currentIndex, prevM, prevL;

    LinkedHashSet<String> masterWords;
    LinkedHashSet<String> learningWords;

    EasyFlipView easyFlipView;
    WordData wordData = new WordData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        showWord = findViewById(R.id.word_show);
        theWord = findViewById(R.id.word);
        theMeaning = findViewById(R.id.meaning);
        labm = findViewById(R.id.lab_mastered);
        labl = findViewById(R.id.lab_learning);

        master = findViewById(R.id.mastered);
        learn = findViewById(R.id.learning);

        yes = findViewById(R.id.positive);
        no = findViewById(R.id.negative);

        easyFlipView = findViewById(R.id.easyflipview);

        masterWords = new LinkedHashSet<>();
        learningWords = new LinkedHashSet<>();

        //init
        getCurrentWord();


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyFlipView.flipTheView();
                //changeWord();
                ifWordStatePositive();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyFlipView.flipTheView();
                //changeWord();
                ifWordStateNegative();
            }
        });
    }

    private void ifWordStateNegative() {
        String check = wordData.easyWord1[currentIndex];
        if (masterWords.contains(check)) {
            learningWords.add(check);
            masterWords.remove(check);
            changeWord();
            refreshProgressBar();
        } else {
            learningWords.add(check);
            changeWord();
            refreshProgressBar();
        }
    }

    private void ifWordStatePositive() {
        String check = wordData.easyWord1[currentIndex];
        if (learningWords.contains(check)) {
            masterWords.add(check);
            learningWords.remove(check);
            changeWord();
            refreshProgressBar();
        } else {
            masterWords.add(check);
            learningWords.remove(check);
            changeWord();
            refreshProgressBar();
        }
    }

    private void getCurrentWord() {
        Random random = new Random();
        currentIndex = random.nextInt(wordData.easyWord1.length);
        showWord.setText(wordData.easyWord1[currentIndex]);
        theWord.setText(wordData.easyWord1[currentIndex]);
        theMeaning.setText(wordData.easyMeaning1[currentIndex]);
        refreshProgressBar();
    }

    public void changeWord() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getCurrentWord();
                refreshProgressBar();
            }
        }, 150);
    }

    @SuppressLint("SetTextI18n")
    private void refreshProgressBar() {
        int masterListSize = masterWords.size();
        int totalSize = wordData.easyWord1.length;
        labm.setText("Mastered WordList "+masterListSize+"/"+totalSize);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            master.setProgress(masterListSize*(4),true);
        }


        int learningListSize = learningWords.size();
        int totalSize1 = wordData.easyWord1.length;
        labl.setText("Learning WordList "+learningListSize+"/"+totalSize1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            learn.setProgress(learningListSize*(4),true);
        }
    }
}
