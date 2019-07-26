package sg.edu.rp.c346.reservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Reservation> awoo = new ArrayList<Reservation>();
    CustomAdapter adapter;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.listView);
        tv = findViewById(R.id.textView6);
        adapter = new CustomAdapter(MainActivity.this, R.layout.row, awoo);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Reservation res = awoo.get(position);
                Intent intent = new Intent(MainActivity.this, ReservationDetails.class);
                intent.putExtra("name", res.getName());
                intent.putExtra("tele", res.getTele());
                intent.putExtra("smoke", res.getSmoke());
                intent.putExtra("size", res.getSize());
                intent.putExtra("date", res.getDate());
                intent.putExtra("time", res.getTime());
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menui, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, CreateReservationActivity.class);
        startActivityForResult(intent, 1, null);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                awoo.add(new Reservation(data.getStringExtra("name"), data.getIntExtra("tele", 0),
                        data.getIntExtra("size", 0), data.getStringExtra("smoke"),
                        data.getStringExtra("date"), data.getStringExtra("time")));
                tv.setText("");
                adapter.notifyDataSetChanged();
            }
        }
    }
}