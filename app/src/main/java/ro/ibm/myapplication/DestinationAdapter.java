package ro.ibm.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;


public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.DestinationViewHolder> {

    private final List<Destination> data = new ArrayList<>();

    public DestinationAdapter (List<Destination> destinationList){
        data.addAll(destinationList);
    }


    @NonNull
    @Override
    public DestinationViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.destination_item,parent);
        return new DestinationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  DestinationAdapter.DestinationViewHolder holder, int position) {
        holder.bind(data.get(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class DestinationViewHolder extends RecyclerView.ViewHolder{

        public DestinationViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Destination destination){
            TextView name = itemView.findViewById(R.id.tile_name);
            TextView message = itemView.findViewById(R.id.tile_message);

            name.setText(destination.getName());
            message.setText(destination.getDescriptoin());

        }
    }

}
