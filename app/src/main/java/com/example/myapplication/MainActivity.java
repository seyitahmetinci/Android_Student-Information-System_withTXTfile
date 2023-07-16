package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et_stunumber, et_name, et_surname;

    RadioGroup programRadioGroup;

    Button bt_save, bt_list;

    String fileName = "StudentInfos.txt";

    private ArrayList<Student> studentList;

    private String selectedProgram = "";

//    ListView lv_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentList = new ArrayList<Student>();
        et_stunumber = findViewById(R.id.editTextNumber);
        et_name = findViewById(R.id.editTextTextPersonName);
        et_surname = findViewById(R.id.editTextTextPersonName2);

        programRadioGroup = findViewById(R.id.GroupButton);

        bt_save = findViewById(R.id.button);
        bt_list = findViewById(R.id.button2);


        programRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int depID) {

                try {
                    switch (depID) {
                        case R.id.radioButton4:
                            selectedProgram = "CMPE";
                            break;

                        case R.id.radioButton5:
                            selectedProgram = "CMSE";
                            break;

                        case R.id.radioButton6:
                            selectedProgram = "BLGM";
                            break;
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Choose Department", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!selectedProgram.equals("")) {
//                    try {
//                        int id = Integer.parseInt(et_stunumber.getText().toString());
//                        String name = et_name.getText().toString();
//                        String surname = et_surname.getText().toString();
//
//                        Student student= new Student(id,name,surname,selectedProgram);
//                        studentList.add(student);
//
//                        et_name.setText("");
//                        et_stunumber.setText("");
//                        et_surname.setText("");
//                        programRadioGroup.clearCheck();
//
//                        Toast.makeText(MainActivity.this, "Student Added", Toast.LENGTH_SHORT).show();
//                    } catch (NumberFormatException e) {
//                        Toast.makeText(MainActivity.this, "Fields not filled.", Toast.LENGTH_SHORT).show();
//                    }

                    try {
                        int id = Integer.parseInt(et_stunumber.getText().toString());
                        String name = et_name.getText().toString();
                        String surname = et_surname.getText().toString();

                        Student student = new Student(id, name, surname, selectedProgram);

                        studentList.add(student);
//                        txt side
                        FileOutputStream fos = openFileOutput(fileName, Context.MODE_APPEND);
                        OutputStreamWriter osw = new OutputStreamWriter(fos);

                        String input = id +" "+ name + " " + surname + " " + selectedProgram+ "\n";
//                    fos.write(input.toString().getBytes());

                        osw.write(input);

                        osw.flush();
                        osw.close();



                        et_name.setText("");
                        et_stunumber.setText("");
                        et_surname.setText("");
                        programRadioGroup.clearCheck();
                    fos.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Select a program", Toast.LENGTH_SHORT).show();
                }

            }
        });

        bt_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Bundle bundle = new Bundle();
                bundle.putSerializable("Students", studentList);
                Intent intent = new Intent(MainActivity.this, MainActivity1.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }
}