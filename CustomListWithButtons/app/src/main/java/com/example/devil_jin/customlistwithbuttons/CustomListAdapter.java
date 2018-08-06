package com.example.devil_jin.customlistwithbuttons;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter {

    private Context context;
    public CustomListAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getViewTypeCount(){
        return getCount();
    }

    @Override
    public int getItemViewType(int i){
        return i;
    }

    @Override
    public int getCount() {
        return MainActivity.modelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return MainActivity.modelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listviewitem, null, true);

            holder.departmentTextView = (TextView) view.findViewById(R.id.departmentTextView);

            holder.viewButton = (Button) view.findViewById(R.id.viewDeptButton);
            holder.editButton = (Button) view.findViewById(R.id.editDeptButton);
            holder.deleteButton = (Button) view.findViewById(R.id.deleteDeptButton);

            view.setTag(holder);
        } else holder = (ViewHolder) view.getTag();

        holder.departmentTextView.setText(MainActivity.modelArrayList.get(i).getDepartmentName());

        //View Department here.
        holder.viewButton.setTag(R.integer.button_viewButton_view, view);
        holder.viewButton.setTag(R.integer.button_viewButton_position, i);

        holder.viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View tempView = (View) holder.viewButton.getTag(R.integer.button_viewButton_view);
                Integer pos = (Integer) holder.viewButton.getTag(R.integer.button_viewButton_position);

                Intent intent = new Intent(context.getApplicationContext(), ViewDepartmentActivity.class);
                intent.putExtra("id", Integer.toString(MainActivity.modelArrayList.get(pos).getDepartmentId()));
                context.startActivity(intent);
            }
        });

        //Edit Department Here.
        holder.editButton.setTag(R.integer.button_editButton_view, view);
        holder.editButton.setTag(R.integer.button_editButton_position, i);

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View tempView = (View) holder.editButton.getTag(R.integer.button_editButton_view);
                Integer pos = (Integer) holder.editButton.getTag(R.integer.button_editButton_position);

                Intent intent = new Intent(context.getApplicationContext(), EditDepartmentActivity.class);
                intent.putExtra("id", Integer.toString(MainActivity.modelArrayList.get(pos).getDepartmentId()));
                intent.putExtra("title", MainActivity.modelArrayList.get(pos).getDepartmentTitle());
                intent.putExtra("body", MainActivity.modelArrayList.get(pos).getDepartmentName());
                context.startActivity(intent);
            }
        });

        //Delete Department Here
        holder.deleteButton.setTag(R.integer.button_deleteButton_view, view);
        holder.deleteButton.setTag(R.integer.button_deleteButton_position, i);


        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                builder.setTitle("Delete Department").setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                View tempView = (View) holder.deleteButton.getTag(R.integer.button_deleteButton_view);
                                Integer pos = (Integer) holder.deleteButton.getTag(R.integer.button_deleteButton_position);

                                DeleteDepartmentActivity obj = new DeleteDepartmentActivity();
                                obj.deleteDepartment(MainActivity.modelArrayList.get(pos).getDepartmentId());
                                }
                        }).create().show();
                }
        });

        return view;
    }

    private class ViewHolder{
        protected Button viewButton, editButton, deleteButton;
        private TextView departmentTextView;
    }
}
