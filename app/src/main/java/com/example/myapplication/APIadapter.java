package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class APIadapter extends RecyclerView.Adapter<APIadapter.APIViewHolder>{

    List<SRMLight> apiList;
    Context context;
    SRMLight srmLight;

    public APIadapter(Context context, List<SRMLight> list){
        this.context = context;
        apiList = list;
    }
    @NonNull
    @Override
    public APIViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_idvaluelist, viewGroup, false);
        return new APIViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull APIViewHolder apiViewHolder, int i) {

        srmLight = apiList.get(i);
        apiViewHolder.id.setText("ID:  " + srmLight.getId());
        apiViewHolder.value.setText("Value:  " + srmLight.getValue());

    }

    @Override
    public int getItemCount() {
        return apiList.size();
    }

    public class APIViewHolder extends RecyclerView.ViewHolder{
        TextView id, value;

        public APIViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id_list);
            value = itemView.findViewById(R.id.value_list);
        }
    }
}
