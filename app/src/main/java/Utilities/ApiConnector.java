package Utilities;

import android.os.AsyncTask;

public class ApiConnector extends AsyncTask<String,String,String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // display a progress dialog to show the user what is happening
    }

    @Override
    protected String doInBackground(String... params) {
        String url = "http://localhost:8080/codes/user";
        // Fetch data from the API in the background.
        return null;
    }

    @Override
    protected void onPostExecute(String s) {

    }
}
