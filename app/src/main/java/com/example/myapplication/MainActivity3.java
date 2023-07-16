package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity3 extends AppCompatActivity {

    String fileName = "StudentInfos.txt";

    Student searchingStudent;
    int searchStudentIndex=0;

    ArrayList<Student> studentList = new ArrayList<Student>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EditText et_no = (EditText) findViewById(R.id.et_stdno);
        EditText et_name = (EditText) findViewById(R.id.stdname);
        EditText et_surname = (EditText) findViewById(R.id.et_stdsurname);
        RadioGroup radioGroup = findViewById(R.id.radiogroup22);
        RadioButton cmpeButton2 = (RadioButton) findViewById(R.id.radioButton4cmpe);
        RadioButton cmseButton2 = (RadioButton) findViewById(R.id.radioButton5cmse);
        RadioButton blgmButton2 = (RadioButton) findViewById(R.id.radioButton6blgm);


        Button searchButton = (Button) findViewById(R.id.button9search);
        Button update_button = (Button) findViewById(R.id.button10update);

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
                studentList.add(student);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = Integer.parseInt(et_no.getText().toString());

                for (int i = 0;i<studentList.size();i++){
                    Student student = studentList.get(i);
                    if (student.getId() == id){

                        searchingStudent = student;
                        searchStudentIndex = i;

                        et_name.setText(student.getName());
                        et_surname.setText(student.getSurname());

                        if (student.getProgram().equals("CMPE")) {
                            cmpeButton2.setChecked(true);
                        } else if (student.getProgram().equals("CMSE")) {
                            cmseButton2.setChecked(true);
                        } else if (student.getProgram().equals("BLGM")) {
                            blgmButton2.setChecked(true);
                        }



                    }
                }

            }
        });

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                searchingStudent.setName(et_name.getText().toString());
                searchingStudent.setSurname(et_surname.getText().toString());
                if (cmpeButton2.isChecked()) {
                    searchingStudent.setProgram("CMPE");
                } else if (cmseButton2.isChecked()) {
                    searchingStudent.setProgram("CMSE");
                } else if (blgmButton2.isChecked()) {
                    searchingStudent.setProgram("BLGM");
                }

                studentList.set(searchStudentIndex,searchingStudent);

                try {
                    FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    for (int i = 0; i < studentList.size(); i++) {
                        String text = studentList.get(i).toString() + "\n";
                        osw.write(text);
                    }
                    osw.flush();
                    osw.close();
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Toast.makeText(MainActivity3.this, "Student info updated", Toast.LENGTH_SHORT).show();



            }
        });

        Button list_button = (Button) findViewById(R.id.button11list);
        list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity1.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Students", studentList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



    }
}