package duongddtph24297.fpoly.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bai4Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText ed1;
    private Button btn;
    private TextView tv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai4);
        ed1 = findViewById(R.id.ed1);
        btn = findViewById(R.id.btn4);
        tv4 = findViewById(R.id.tv4);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AsynTaskRunner asynTaskRunner = new AsynTaskRunner(tv4,ed1,this);
        String sleeptime = ed1.getText().toString();
        asynTaskRunner.execute(sleeptime);
    }
}