package com.architecture.mvvm.UI.DetailsModule;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.architecture.mvvm.R;
import com.architecture.mvvm.Storage.Response.SampleUserDetailsPojo;

import java.util.List;

/**
 * Created by akhil on 10/9/18.
 */

public class DetailsActivityAdapter extends RecyclerView.Adapter<DetailsActivityAdapter.ViewHolder> {

    private List<SampleUserDetailsPojo> sampleUsersList;


    DetailsActivityAdapter(List<SampleUserDetailsPojo> sampleUsersList) {
        this.sampleUsersList = sampleUsersList;
    }

    void refreshData(List<SampleUserDetailsPojo> sampleUsersList) {
        this.sampleUsersList = sampleUsersList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public DetailsActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_details_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsActivityAdapter.ViewHolder holder, int position) {
        holder.updateUi(position);
    }

    @Override
    public int getItemCount() {
        return sampleUsersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvUserName, tvEmail, tvWebsite;
        private SampleUserDetailsPojo sampleUserDetailsPojo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvWebsite = itemView.findViewById(R.id.tvWebsite);
        }

        public void updateUi(int position) {
            sampleUserDetailsPojo = sampleUsersList.get(position);
            tvName.setText("Name:- " + sampleUserDetailsPojo.getName());
            tvUserName.setText("UserName:- " + sampleUserDetailsPojo.getUsername());
            tvWebsite.setText("Website:- " + sampleUserDetailsPojo.getWebsite());
            tvEmail.setText("Email:- " + sampleUserDetailsPojo.getEmail());
        }
    }
}
