package com.architecture.mvvm.UI.DetailsModule;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.architecture.mvvm.R;
import com.architecture.mvvm.Storage.Response.SampleUserDetailsPojo;

import java.util.List;

/**
 * Created by akhil on 11/9/18.
 */

public class DetailsActivityAdapterDataBinding extends RecyclerView.Adapter<DetailsActivityAdapterDataBinding.ViewHolder> {

    private List<SampleUserDetailsPojo> sampleUsersList;


    DetailsActivityAdapterDataBinding(List<SampleUserDetailsPojo> sampleUsersList) {
        this.sampleUsersList = sampleUsersList;
    }

    void refreshData(List<SampleUserDetailsPojo> sampleUsersList) {
        this.sampleUsersList = sampleUsersList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public DetailsActivityAdapterDataBinding.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_details_item, parent, false);
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.user_details_item_data_binding, parent, false);
        return new DetailsActivityAdapterDataBinding.ViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsActivityAdapterDataBinding.ViewHolder holder, int position) {
        holder.updateUi(position);
    }

    @Override
    public int getItemCount() {
        return sampleUsersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding mViewDataBinding;

        public ViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            mViewDataBinding = viewDataBinding;
            mViewDataBinding.executePendingBindings();
        }

        public void updateUi(int position) {
            mViewDataBinding.setVariable(BR.sampleUsersModel, sampleUsersList.get(position));
        }
    }
}
