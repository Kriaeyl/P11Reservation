package sg.edu.rp.c346.reservation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class CreateReservationActivity extends AppCompatActivity {

    EditText etName;
    EditText etTelephone;
    EditText etSize;
    CheckBox checkBox;
    Button btReserve;
    Button btReset;
    EditText etDate;
    EditText etTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reservation);

        this.setTitle("Reservation");

        etName = findViewById(R.id.editTextName);
        etTelephone = findViewById(R.id.editTextTelephone);
        etSize = findViewById(R.id.editTextSize);
        checkBox = findViewById(R.id.checkBox);
        btReserve = findViewById(R.id.buttonReserve);
        btReset = findViewById(R.id.buttonReset);
        etDate = findViewById(R.id.editText2);
        etTime = findViewById(R.id.editText);

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText(null);
                etTelephone.setText(null);
                etSize.setText(null);
                checkBox.setChecked(false);
                etDate.setText(null);
                etTime.setText(null);
            }
        });

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etDate.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                    }
                };

                Calendar curr = Calendar.getInstance();

                DatePickerDialog myDateDialog = new DatePickerDialog
                        (CreateReservationActivity.this, myDateListener,
                                curr.get(Calendar.YEAR), curr.get(Calendar.MONTH), curr.get(Calendar.DAY_OF_MONTH));
                myDateDialog.show();
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String nm;
                        if (minute < 10) {
                            nm = "0" + minute;
                        }
                        else {
                            nm = minute + "";
                        }
                        etTime.setText(hourOfDay + ":" + nm);
                    }
                };

                Calendar curr = Calendar.getInstance();

                TimePickerDialog myTimeDialog = new TimePickerDialog(CreateReservationActivity.this, myTimeListener,
                        curr.get(Calendar.HOUR_OF_DAY), curr.get(Calendar.MINUTE), true);
                myTimeDialog.show();
            }
        });

        btReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                String isSmoke;
                if (checkBox.isChecked()) {
                    isSmoke = "smoking";
                } else {
                    isSmoke = "non-smoking";
                }
                final String smoker = isSmoke;
                boolean error = false;
                String ohno = "";

                if (etName.getText().toString().isEmpty()) {
                    ohno += " Name |";
                    error = true;
                }
                if (etTelephone.getText().toString().length() != 8) {
                    ohno += " Telephone No. |";
                    error = true;
                }
                if (etSize.getText().toString().isEmpty()) {
                    ohno += " Size |";
                    error = true;
                }
                if (etDate.getText().toString().isEmpty()) {
                    ohno += " Date |";
                    error = true;
                }
                if (etTime.getText().toString().isEmpty()) {
                    ohno += " Time |";
                    error = true;
                }
                if (error) {
                    Toast.makeText(CreateReservationActivity.this, "Error in Fields:" + ohno, Toast.LENGTH_LONG).show();
                }
                else {
                    AlertDialog.Builder myBuilder = new AlertDialog.Builder(CreateReservationActivity.this);
                    myBuilder.setTitle("Confirm Your Order");
                    myBuilder.setMessage("New Reservation\nName: " + etName.getText().toString()
                            + "\nTelephone: " + etTelephone.getText().toString()
                            + "\nSmoking: " + smoker
                            + "\nSize: " + etSize.getText().toString()
                            + "\nDate: " + etDate.getText().toString()
                            + "\nTime: " + etTime.getText().toString());

                    myBuilder.setCancelable(true);

                    myBuilder.setPositiveButton("Reserve", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.putExtra("name", etName.getText().toString());
                            intent.putExtra("tele", Integer.parseInt(etTelephone.getText().toString()));
                            intent.putExtra("smoke", smoker);
                            intent.putExtra("size", Integer.parseInt(etSize.getText().toString()));
                            intent.putExtra("date", etDate.getText().toString());
                            intent.putExtra("time", etTime.getText().toString());
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    });
                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();
                }
            }
        });
    }
}