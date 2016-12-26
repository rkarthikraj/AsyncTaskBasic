package com.example.rkarthikraj.asynctaskthreadbasic;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AsyncTaskBasicThreadActivity extends AppCompatActivity {

    TextView txt;
    EditText time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_basic_thread);
        txt = (TextView) findViewById(R.id.tv);
    }

    public void onClickStart(View view){
        AsyncTaskRunner runner = new AsyncTaskRunner(); // or Directly new AsyncTaskRunner().execute();
        time = (EditText) findViewById(R.id.inputTime);
        String sleepTime = time.getText().toString();
        runner.execute(sleepTime);
    }

    public class AsyncTaskRunner extends AsyncTask<String,String,String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {

            try {
                int time = Integer.parseInt(params[0])*1000;
                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";
            }
            catch (InterruptedException e){
            }
            return resp;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(AsyncTaskBasicThreadActivity.this,"ProgressDialog","Wait for "+time.getText().toString()+ " seconds");
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            txt.setText(result);
        }

        @Override
        protected void onProgressUpdate(String...text) {
            // txt.setText(text[0]);
        }
    }
}



