package duongddtph24297.fpoly.lab_1;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

public class AsynTaskRunner extends AsyncTask<String,String,String> {
    private  String resp;
    ProgressDialog dialog;
    TextView tv;
    EditText time;
    Context context;

    public AsynTaskRunner(TextView tv, EditText time, Context context) {
        this.tv = tv;
        this.time = time;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(context,"ProgressDialog","Wait for "+time.getText().toString()+"seconds");

    }

    @Override
    protected String doInBackground(String... strings) {
        publishProgress("Sleeping...");

        try {
            int time = Integer.parseInt(strings[0])*1000;
            resp="slept for "+ strings[0]+ " seconds";
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
            resp = e.getMessage();
        }
        return resp;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (dialog.isShowing()){
            dialog.dismiss();
        }
        tv.setText(s);
    }
}
