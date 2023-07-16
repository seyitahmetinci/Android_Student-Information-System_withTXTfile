package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity1 extends AppCompatActivity {

    String fileName = "StudentInfos.txt";

    ArrayList<Student> studentsList = new ArrayList<Student>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        Button alllist = (Button) findViewById(R.id.alllist);
        Button cmpelist = (Button) findViewById(R.id.cmpelist);
        Button cmselist = (Button) findViewById(R.id.cmselist);
        Button blgmlist = (Button) findViewById(R.id.blgmlist);

        try {
            InputStreamReader isr = new InputStreamReader(openFileInput(fileName));
            BufferedReader reader = new BufferedReader(isr);
            String textFromLine;
            while ((textFromLine = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(textFromLine, " ");

                int id = Integer.parseInt(tokenizer.nextToken());
                String name = tokenizer.nextToken();
                String surname = tokenizer.nextToken();
                String department = tokenizer.nextToken();

                Student student = new Student(id, name, surname, department);
                studentsList.add(student);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Student> cmpeList = new ArrayList<>();
        ArrayList<Student> cmseList = new ArrayList<>();
        ArrayList<Student> blgmList = new ArrayList<>();

        for (Student student : studentsList) {
            if (student.getProgram().equals("CMPE")) {
                cmpeList.add(student);
            } else if (student.getProgram().equals("CMSE")) {
                cmseList.add(student);
            } else if (student.getProgram().equals("BLGM")) {
                blgmList.add(student);
            }
        }

        alllist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("Students", studentsList);
                Intent intent = new Intent(MainActivity1.this, MainActivity2.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        cmpelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("Students", cmpeList);
                Intent intent = new Intent(MainActivity1.this, MainActivity2.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        cmselist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("Students", cmseList);
                Intent intent = new Intent(MainActivity1.this, MainActivity2.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        blgmlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("Students", blgmList);
                Intent intent = new Intent(MainActivity1.this, MainActivity2.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}