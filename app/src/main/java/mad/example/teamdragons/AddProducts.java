//package mad.example.teamdragons;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.ContentValues;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.BitmapDrawable;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//
////import com.github.dhaval2404.imagepicker.ImagePicker;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.io.ByteArrayOutputStream;
//
//import mad.example.teamdragons.Database.DBHandlerProduct;
//
//public class AddProducts extends AppCompatActivity {
//
//    EditText product, description,price;
//    Button submit, view;
//    ImageView cover;
//    FloatingActionButton fab;
//    Uri pathImage;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_products);
//
//        cover = findViewById(R.id.cover);
//        fab = findViewById(R.id.fab);
//        product = findViewById(R.id.edt_txt_PName);
//        description = findViewById(R.id.edt_txt_Description);
//        price = findViewById(R.id.edt_txt_Price);
//        submit = findViewById(R.id.btn_submit);
//        view = findViewById(R.id.btn_view);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ImagePicker.with(AddProducts.this)
//                        .crop()	    			//Crop image(Optional), Check Customization for more option
//                        .cropSquare()
//                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
//                        .start();
//            }
//        });
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                DBHandlerProduct dbHandlerProduct = new DBHandlerProduct(getApplicationContext());
//                BitmapDrawable drawable = (BitmapDrawable) cover.getDrawable();
//                Bitmap bitmap = drawable.getBitmap();
//                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
//                byte[] bytesImage = byteArrayOutputStream.toByteArray();
//              long newID = dbHandlerProduct.addInfo(bytesImage, product.getText().toString(), description.getText().toString(), price.getText().toString());
//
//                Toast.makeText(AddProducts.this, "Product is added. Product ID" +newID, Toast.LENGTH_SHORT).show();
//              Intent i = new Intent(getApplicationContext(),EditProducts.class);
//              startActivity(i);
//
//                product.setText(null);
//                description.setText(null);
//                price.setText(null);
//                //cover.setImageURI();
//            }
//        });
//
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent newintent = new Intent(getApplicationContext(),ViewProducts.class);
//                startActivity(newintent);
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        Uri uri = data.getData();
//        pathImage = uri;
//        cover.setImageURI(uri);
//    }
//}