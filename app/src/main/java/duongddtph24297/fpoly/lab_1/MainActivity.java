package duongddtph24297.fpoly.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
     Button btnLoad;
     ImageView img;
     TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLoad = findViewById(R.id.btn);
        img = findViewById(R.id.img1);
        tv = findViewById(R.id.tvMessage);
        btnLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = loadImg("https://img3.thuthuatphanmem.vn/uploads/2019/10/10/anh-doremon-png-dep_033146690.png");
                img.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("Img download");
                        img.setImageBitmap(bitmap);
                    }
                });
            }
        });
        thread.start();
    }
    private Bitmap loadImg(String link){
        URL url;
        Bitmap bmp = null;
        try {
            url= new URL(link);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  bmp;
    }


}