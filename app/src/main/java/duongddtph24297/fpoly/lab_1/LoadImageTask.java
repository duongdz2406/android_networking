package duongddtph24297.fpoly.lab_1;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadImageTask extends AsyncTask<String,Void, Bitmap> {
    private Listener mlistener;
    private ProgressDialog progressDialog;
    public LoadImageTask(Listener listener, Context context){
        this.mlistener=listener;
        this.progressDialog = new ProgressDialog(context);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Downgload...");
        progressDialog.show();
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        URL url;
        Bitmap bmp = null;
        try {
            url=  new URL(strings[0]);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  bmp;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        if (bitmap!=null){
            mlistener.onImageLoaded(bitmap);

        }else{
            mlistener.onError();
        }

    }
}
