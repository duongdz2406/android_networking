package duongddtph24297.fpoly.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Bai3Activity extends AppCompatActivity implements View.OnClickListener,Listener {
    private TextView tvMessage;
    private Button btnLoad;
    private ImageView imgLoad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);
        imgLoad = findViewById(R.id.img3);
        btnLoad = findViewById(R.id.btn3);
        tvMessage = findViewById(R.id.tvmsg3);
        btnLoad.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        new LoadImageTask(this,this).execute("https://img3.thuthuatphanmem.vn/uploads/2019/10/10/anh-doremon-png-dep_033146690.png");

    }

    @Override
    public void onImageLoaded(Bitmap bitmap) {
        imgLoad.setImageBitmap(bitmap);
        tvMessage.setText("Image download");
    }

    @Override
    public void onError() {
tvMessage.setText("error");
    }
}