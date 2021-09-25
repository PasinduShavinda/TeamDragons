package mad.example.teamdragons;

import mad.example.teamdragons.Database.DBHandlerProduct;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewProducts extends AppCompatActivity {

    ListView mListView;
    ArrayList<ProductModel> mList;
    ProductAdapter mAdapter = null;

    DBHandlerProduct db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);

        mListView = findViewById(R.id.productlist);
        mList = new ArrayList<>();
        mAdapter = new ProductAdapter(this, R.layout.row, mList);
        mListView.setAdapter(mAdapter);

        db = new DBHandlerProduct(getApplicationContext());
        Cursor cursor = db.getData("SELECT * FROM Product");
        mList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String des = cursor.getString(2);
            String price = cursor.getString(3);
            byte[] image = cursor.getBlob(4);
            mList.add(new ProductModel(id, name, des, price, image));
        }
        mAdapter.notifyDataSetChanged();
        if (mList.size() == 0) {
            //if there is no reord in table of database
            Toast.makeText(this, "No record found...", Toast.LENGTH_SHORT).show();


        }


        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l){
                //alert dialog to display options of update and delete
                CharSequence[] items = {"Update","Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(ViewProducts.this);
                dialog.setTitle("Choose an action");
                dialog.setItems(items,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface,int i) {
                        if(i == 0){
                            //update
                            Cursor c = db.getData("SELECT _id FROM Product");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while(c.moveToNext()){

                                arrID.add(c.getInt(0));
                            }
                            //show update dialog
                            showDialogUpdate(ViewProducts.this,arrID.get(position));



                        }
                        if(i == 1){

                            //delete
                            Cursor c = db.getData("SELECT _id FROM Product");
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



    private void showDialogDelete(final int idRecord) {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(ViewProducts.this);
        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure to delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try{
                    db.deleteById((idRecord));
                    Toast.makeText(ViewProducts.this,"Delete successfully",Toast.LENGTH_SHORT).show();


                }catch(Exception e)
                {

                    Log.e("error",e.getMessage());


                }
                updateRecordList();


            }
        });
        dialogDelete.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void showDialogUpdate(final Activity activity, final int position)
    {

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.activity_update_product);
        dialog.setTitle("Update");

        // imageViewicon = dialog.findViewById(R.id.imageView);
        final EditText edtName = dialog.findViewById(R.id.editName);
        final EditText edtDes = dialog.findViewById(R.id.editDes);
        final EditText edtPrice = dialog.findViewById(R.id.editPrice);
        Button btnUpdate = dialog.findViewById(R.id.btnUpdate);

        Cursor cursor = db.getData("SELECT * FROM Product WHERE  _id="+position);
        mList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name  = cursor.getString(1);
            edtName.setText(name);
            String des = cursor.getString(2);
            edtDes.setText(des);
            String price = cursor.getString(3);
            edtPrice.setText(price);
            byte[] image = cursor.getBlob(4);

            mList.add(new ProductModel(id,name,des,price, image));
        }
        //get data of row clicked from sqlite


        //set width of dialog
        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        //set height o dialog
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width,height);
        dialog.show();

        //in update dialog click image view to update image
       /* imageViewIcon.setOnClickListner(new mListView.hasOnClickListeners() {
            @Override
            public void onClick(View view)
            {
             //check external storage permissions
            }
                                        }
        );
*/
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    db.updateByName(position ,edtName.getText().toString(),edtDes.getText().toString(), edtPrice.getText().toString());

                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_LONG).show();

                } catch (Exception error) {
                    Log.e("Upadte error", error.getMessage());
                }

                updateRecordList();

            }
        });



    }

    private void updateRecordList() {
        //get all data from sqlite
        Cursor cursor = db.getData("SELECT * FROM Product");
        mList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name  = cursor.getString(1);
            String des = cursor.getString(2);
            String price = cursor.getString(3);
            byte[] image = cursor.getBlob(4);

            mList.add(new ProductModel(id,name,des,price, image));


        }
        mAdapter.notifyDataSetChanged();

    }

}


























//        productList = findViewById(R.id.productlist);
//
//        db = new DBHandlerProduct(getApplicationContext());
//        dataList = db.readInfo();
//
//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList);
//
//        productList.setAdapter(adapter);
//
//        Cursor cursor = DBHandlerProduct.getData("SELECT * FROM employees");
//        mList.clear();
//        while (cursor.moveToNext()){
//            int id = cursor.getInt(0);
//            String name  = cursor.getString(1);
//            int age = cursor.getInt(2);
//            int phone = cursor.getInt(3);
//            String email = cursor.getString(4);
//            double salary = cursor.getDouble(5);
//            //byte[] image = cursor.getBlob(6);
//            mList.add(new Model(id,name,age,phone,email,salary));
//        }
//        mAdapter.notifyDataSetChanged();
//        if(mList.size()==0){
//            //if there is no reord in table of database
//            Toast.makeText(this,"No record found...",Toast.LENGTH_SHORT).show();
//
//
//        }
//
//        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String text = productList.getItemAtPosition(i).toString();
//                Toast.makeText(ViewProducts.this, "User: "+text, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//

