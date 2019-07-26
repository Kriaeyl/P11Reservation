package sg.edu.rp.c346.reservation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Reservation> versionList;

    public CustomAdapter(Context context, int resource, ArrayList<Reservation> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        versionList = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tv1 = rowView.findViewById(R.id.textView);
        TextView tv2 = rowView.findViewById(R.id.textView2);
        TextView tv3 = rowView.findViewById(R.id.textView3);

        // Obtain the Android Version information based on the position
        Reservation currentVersion = versionList.get(position);

        // Set values to the TextView to display the corresponding information
        tv1.setText(currentVersion.getName());
        tv2.setText("Date: " + currentVersion.getDate());
        tv3.setText("Time: " + currentVersion.getTime());

        return rowView;
    }

}
