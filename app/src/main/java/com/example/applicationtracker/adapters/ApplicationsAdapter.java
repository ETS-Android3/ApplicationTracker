package com.example.applicationtracker.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationtracker.R;
import com.example.applicationtracker.models.Application;

import java.util.List;

public class ApplicationsAdapter extends RecyclerView.Adapter<ApplicationsAdapter.ViewHolder> {

    public static final String TAG = "ApplicationsAdapter";
    List<Application> applications;
    Context context;

    public ApplicationsAdapter(List<Application> applications, Context context) {
        Log.d(TAG, "ApplicationsAdapter: Constructor called");
        this.applications = applications;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: Made view holder");
        View view = LayoutInflater.from(context).inflate(R.layout.activity_application, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        Application application = applications.get(position);
        holder.bind(application);
    }

    @Override
    public int getItemCount() {
        return applications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCompanyName;
        TextView tvJobTitle;
        TextView tvDateApplied;
        TextView tvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCompanyName = itemView.findViewById(R.id.companyName);
            tvJobTitle = itemView.findViewById(R.id.jobTitle);
            tvDateApplied = itemView.findViewById(R.id.dateApplied);
            tvStatus = itemView.findViewById(R.id.status);
        }

        public void bind(Application application) {
            Log.d(TAG, "bind: ");
            tvCompanyName.setText(application.getCompName());
            tvJobTitle.setText(application.getJobTitle());
            tvDateApplied.setText(application.getDateApplied().toString());
            // 1 - Accepted
            // 2 - Interviewing
            // 3 - Rejected
            // 4 - No response
            if (application.getStatus() == 1) {
                tvStatus.setBackgroundColor(Color.GREEN);
            } else if (application.getStatus() == 2) {
                tvStatus.setBackgroundColor(Color.YELLOW);
            } else if (application.getStatus() == 3) {
                tvStatus.setBackgroundColor(Color.RED);
            } else if (application.getStatus() == 4) {
                tvStatus.setBackgroundColor(Color.GRAY);
            }

        }
    }
}
