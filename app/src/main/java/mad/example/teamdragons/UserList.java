package mad.example.teamdragons;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class UserList extends AppCompatActivity {

    ListView listView;
    ArrayList<User> list;
    UserListAdapter adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list_activity);

        listView = (ListView) findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter = new UserListAdapter(this, R.layout.user_profiles, list);
        listView.setAdapter(adapter);

        // get data from sqlite
        Cursor cursor = createUserProfile.dbHandlerUserPro.getData("SELECT * FROM USER");
        list.clear();

        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String contact = cursor.getString(3);
            String email = cursor.getString(4);
            String nic = cursor.getString(5);
            String address = cursor.getString(6);
            byte[] image = cursor.getBlob(7);

       list.add(new User(id, name, age, contact, email, nic, address, image));

        }
        adapter.notifyDataSetChanged();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(UserList.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(i==0){

                            //update
                            Cursor c = createUserProfile.dbHandlerUserPro.getData("SELECT id FROM USER");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }

                            // show dialog update at here
                            showDialogUpdate(UserList.this, arrID.get(position));


                        }else{

                            //delete
                            Cursor c = createUserProfile.dbHandlerUserPro.getData("SELECT id FROM USER");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }

                            showDialogDelete(arrID.get(position));

                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }

    ImageView imageViewUser;
    private void showDialogUpdate(Activity activity, int position){

        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_user_activity);
        dialog.setTitle("Update Your Profile");

        imageViewUser = (ImageView) dialog.findViewById(R.id.imageViewUpdate);
        final EditText edtname = (EditText) dialog.findViewById(R.id.txt_nameUP);
        final EditText edtage = (EditText) dialog.findViewById(R.id.txt_ageUP);
        final EditText edtcontact = (EditText) dialog.findViewById(R.id.txt_phoneUP);
        final EditText edtemail = (EditText) dialog.findViewById(R.id.txt_emailUP);
        final EditText edtnic = (EditText) dialog.findViewById(R.id.txt_nicUP);
        final EditText edtaddress = (EditText) dialog.findViewById(R.id.txt_addressUP);

        Button btnUpdate = (Button) dialog.findViewById(R.id.btn_updateUP);

        // set width to dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);

        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.90);

        dialog.getWindow().setLayout(width, height);
        dialog.show();

        imageViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // request photo library
                ActivityCompat.requestPermissions(
                        UserList.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{

                    createUserProfile.dbHandlerUserPro.updateData(

                            edtname.getText().toString().trim(),
                            edtage.getText().toString().trim(),
                            edtcontact.getText().toString().trim(),
                            edtemail.getText().toString().trim(),
                            edtnic.getText().toString().trim(),
                            edtaddress.getText().toString().trim(),
                            createUserProfile.imageViewToByte(imageViewUser),
                            position

                    );
                    dialog.dismiss();

                    Toast.makeText(getApplicationContext(), "Your profile successfully updated !", Toast.LENGTH_SHORT).show();

                }catch (Exception error){
                    Log.e("Update error", error.getMessage());
                }
                updateUserList();
            }
        });

    }

    private void showDialogDelete(final int idUser){

        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(UserList.this);

        dialogDelete.setTitle("Warning !!");
        dialogDelete.setMessage("Are you sure want to delete ?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                try{
                    createUserProfile.dbHandlerUserPro.deleteData(idUser);
                    Toast.makeText(getApplicationContext(), "Delete Successfully !!", Toast.LENGTH_SHORT).show();

                }catch (Exception e){

                    Log.e("error", e.getMessage());

                }
                updateUserList();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });

        dialogDelete.show();


    }




    private void updateUserList(){

        // get data from sqlite
        Cursor cursor = createUserProfile.dbHandlerUserPro.getData("SELECT * FROM USER");
        list.clear();

        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String contact = cursor.getString(3);
            String email = cursor.getString(4);
            String nic = cursor.getString(5);
            String address = cursor.getString(6);
            byte[] image = cursor.getBlob(7);

            list.add(new User(id, name, age, contact, email, nic, address, image));

        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 888){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,888);
            }
            else{
                Toast.makeText(getApplicationContext(), "You don't have permission to access !!!", Toast.LENGTH_SHORT).show();
            }
            return;

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 888 && resultCode == RESULT_OK && data != null){

            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewUser.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
