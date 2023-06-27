package duongddtph24297.fpoly.lab_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Bai2Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgLoad;
    private Button btnLoad;
    private ProgressDialog progressDialog;
    private String url="https://img3.thuthuatphanmem.vn/uploads/2019/10/10/anh-doremon-png-dep_033146690.png";
    private Bitmap bitmap = null;
    private TextView tvMessage;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
        imgLoad = findViewById(R.id.img2);
        btnLoad = findViewById(R.id.btn2);
        tvMessage = findViewById(R.id.tvmsg);
        btnLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        progressDialog = ProgressDialog.show(Bai2Activity.this,"","Download...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                bitmap = downloadBitmap(url);
                Message message = msgHandler.obtainMessage();
                Bundle bundle = new Bundle();
                String threadMsg = "Image Download";
                bundle.putString("message",threadMsg);
                message.setData(bundle);
                msgHandler.sendMessage(message);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private Bitmap downloadBitmap(String link) {
        try {
            URL url1 = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap1 = BitmapFactory.decodeStream(inputStream);
            return bitmap1;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    private Handler msgHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String message = bundle.getString("message");
            tvMessage.setText(message);
            imgLoad.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }
    };
}