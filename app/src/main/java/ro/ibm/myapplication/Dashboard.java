package ro.ibm.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        RecyclerView recyclerView = findViewById(R.id.recyler_view);
        ArrayList<Destination> destinations = new ArrayList<>();
        destinations.add(new Destination("Maldive","Sunny days","URL"));
        destinations.add(new Destination("Nepal","Be prepared for the snow","URL"));
        destinations.add(new Destination("UK","Bring your coat","URL"));

        DestinationAdapter destinationAdapter = new DestinationAdapter(destinations);
        recyclerView.setAdapter(destinationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}