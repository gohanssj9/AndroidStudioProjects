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

public class CustomStudentListAdapter extends BaseAdapter {
    private Context context;
    public CustomStudentListAdapter(Context context){
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
        return ViewDepartmentActivity.modelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return ViewDepartmentActivity.modelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final CustomStudentListAdapter.ViewStudentHolder holder;
        if(view == null){
            holder = new CustomStudentListAdapter.ViewStudentHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.studentlistviewitem, null, true);

            holder.studentTextView = (TextView) view.findViewById(R.id.studentTextView);

            holder.editStudentButton = (Button) view.findViewById(R.id.editStudentButton);
            holder.deleteStudentButton = (Button) view.findViewById(R.id.deleteStudentButton);

            view.setTag(holder);
        } else holder = (CustomStudentListAdapter.ViewStudentHolder) view.getTag();

        holder.studentTextView.setText(ViewDepartmentActivity.modelArrayList.get(i).getStudentName());


        holder.editStudentButton.setTag(R.integer.button_editStudentButton_view, view);
        holder.editStudentButton.setTag(R.integer.button_editStudentButton_position, i);

        holder.editStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View tempView = (View) holder.editStudentButton.getTag(R.integer.button_editStudentButton_view);
                Integer pos = (Integer) holder.editStudentButton.getTag(R.integer.button_editStudentButton_position);

                Intent intent = new Intent(context.getApplicationContext(), EditStudentActivity.class);
                intent.putExtra("id", Integer.toString(ViewDepartmentActivity.modelArrayList.get(pos).getStudentId()));
                intent.putExtra("name", ViewDepartmentActivity.modelArrayList.get(pos).getStudentName());
                intent.putExtra("age", Integer.toString(ViewDepartmentActivity.modelArrayList.get(pos).getStudentAge()));
                context.startActivity(intent);
            }
        });

        //Delete Department Here
        holder.deleteStudentButton.setTag(R.integer.button_deleteStudentButton_view, view);
        holder.deleteStudentButton.setTag(R.integer.button_deleteStudentButton_position, i);


        holder.deleteStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                builder.setTitle("Delete Student").setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                View tempView = (View) holder.deleteStudentButton.getTag(R.integer.button_deleteStudentButton_view);
                                Integer pos = (Integer) holder.deleteStudentButton.getTag(R.integer.button_deleteStudentButton_position);

                                DeleteStudentActivity obj = new DeleteStudentActivity();
                                obj.deleteStudent(ViewDepartmentActivity.modelArrayList.get(pos).getStudentId());
                            }
                        }).create().show();
            }
        });

        return view;
    }

    private class ViewStudentHolder{
        protected Button editStudentButton, deleteStudentButton;
        private TextView studentTextView;
    }
}
