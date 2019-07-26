package sg.edu.rp.c346.reservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReservationDetails extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details);

        tv1 = findViewById(R.id.textView4);
        tv2 = findViewById(R.id.textView5);

        Intent intent = getIntent();
        tv1.setText("Name: " + intent.getStringExtra("name"));
        tv2.setText("Telephone: " + intent.getIntExtra("tele", 0)
                + "\nSmoking: " + intent.getStringExtra("smoke")
                + "\nSize: " + intent.getIntExtra("size",0)
                + "\nDate: " + intent.getStringExtra("date")
                + "\nTime: " + intent.getStringExtra("time"));

    }
}
