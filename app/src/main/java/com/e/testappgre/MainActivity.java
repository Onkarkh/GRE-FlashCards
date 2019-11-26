package com.e.testappgre;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private ArrayList<String> wordList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);

        wordList = new ArrayList<>();
        wordList.add("Easy Words - I");
        wordList.add("Easy Words - II");
//        wordList.add("Easy Words - III");
//        wordList.add("Easy Words - IV");
//        wordList.add("Easy Words - V");
//        wordList.add("Medium Words - I");
//        wordList.add("Medium Words - II");
//        wordList.add("Medium Words - III");
//        wordList.add("Medium Words - IV");
//        wordList.add("Medium Words - V");
//        wordList.add("Hard Words - I");
//        wordList.add("Hard Words - II");
//        wordList.add("Hard Words - III");
//        wordList.add("Hard Words - IV");
//        wordList.add("Hard Words - V");

        listView.setAdapter(new SetData());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedList = wordList.get(position);
                Intent intent = new Intent(MainActivity.this,LearnActivity.class);
                intent.putExtra("keylist",selectedList);
                startActivity(intent);
            }
        });
    }

    class SetData extends BaseAdapter{

        @Override
        public int getCount() {
            return wordList.size();
        }

        @Override
        public Object getItem(int position) {
            return wordList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.layout_listype, null);

            TextView textView = view.findViewById(R.id.wordlist);
            textView.setText(wordList.get(position));

            return view;
        }
    }
}