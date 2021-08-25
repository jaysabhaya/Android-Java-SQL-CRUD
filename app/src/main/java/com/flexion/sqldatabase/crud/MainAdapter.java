package com.flexion.sqldatabase.crud;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyClass> {

    MainActivity mainActivity;
    ArrayList<User> arrayList;
    MyDatabase myDatabase;

    public MainAdapter(MainActivity mainActivity, ArrayList<User> arrayList, MyDatabase myDatabase) {
        this.mainActivity = mainActivity;
        this.arrayList = arrayList;
        this.myDatabase = myDatabase;
    }

    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mainActivity).inflate(R.layout.item_users, parent, false);
        MyClass myClass = new MyClass(view);

        return myClass;
    }

    @Override
    public void onBindViewHolder(@NonNull MyClass holder, int position) {

        User user = arrayList.get(position);

        int userid = user.getUserid();
        String name = user.getName();
        String contact = user.getContact();

        holder.tname.setText(name);
        holder.initial.setText(name);
        holder.tconatct.setText(contact);

        holder.popmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(mainActivity, holder.popmenu);

                mainActivity.getMenuInflater().inflate(R.menu.updatedelete, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        if (item.getItemId() == R.id.update) {

                            Dialog dialog = new Dialog(mainActivity);

                            dialog.setContentView(R.layout.update_dialog);

                            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, -1);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

//                            dialog.setCanceledOnTouchOutside(false);
//                            dialog.setCancelable(false);

                            EditText ename = dialog.findViewById(R.id.ename);
                            EditText econtact = dialog.findViewById(R.id.econtact);
                            Button submit = dialog.findViewById(R.id.submit);

                            ename.setText(name);
                            econtact.setText(contact);

                            submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    dialog.dismiss();

                                    String newname = ename.getText().toString();
                                    String newcontact = econtact.getText().toString();

                                    myDatabase.updateData(userid, newname, newcontact);

                                    arrayList.remove(position);

                                    User user1 = new User(userid, newname,newcontact);

                                    arrayList.add(position, user1);

                                    notifyDataSetChanged();

                                }
                            });

                            dialog.show();

                        } else if (item.getItemId() == R.id.delete) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                            builder.setMessage("Are you sure ?");
                            builder.setTitle("Delete");

                            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    myDatabase.deleteData(userid);

                                    arrayList.remove(position);

                                    notifyDataSetChanged();
                                }
                            });
                            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            builder.show();
                        }

                        return false;
                    }
                });

                popupMenu.show();
            }
        });
        holder.initial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mainActivity, DetailsActivity.class);

//                intent.putExtra("image",user.getUserid());
                intent.putExtra("name",user.getName());
                intent.putExtra("number",user.getContact());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mainActivity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyClass extends RecyclerView.ViewHolder {
        TextView tname;
        TextView initial;
        TextView tconatct;

        ImageView popmenu;

        public MyClass(@NonNull View itemView) {
            super(itemView);

            initial = itemView.findViewById(R.id.initial);
            tname = itemView.findViewById(R.id.tname);
            tconatct = itemView.findViewById(R.id.tconatct);
            popmenu = itemView.findViewById(R.id.popmenu);
        }
    }

}