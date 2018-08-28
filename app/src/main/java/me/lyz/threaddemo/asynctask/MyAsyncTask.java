package me.lyz.threaddemo.asynctask;

import android.os.AsyncTask;
import android.util.Log;

/**
 * @author LYZ 2018.08.21
 */
public class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {
    private static final String TAG = "MyAsyncTask";

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        Log.d(TAG, "onPreExecute: " + THREAD_POOL_EXECUTOR);
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
