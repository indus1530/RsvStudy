package edu.aku.hassannaqvi.rsvstudy.get;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import edu.aku.hassannaqvi.rsvstudy.adapter.SyncListAdapter;
import edu.aku.hassannaqvi.rsvstudy.contracts.ChildList;
import edu.aku.hassannaqvi.rsvstudy.contracts.UsersContract;
import edu.aku.hassannaqvi.rsvstudy.contracts.VersionAppContract;
import edu.aku.hassannaqvi.rsvstudy.core.DatabaseHelper;
import edu.aku.hassannaqvi.rsvstudy.core.MainApp;
import edu.aku.hassannaqvi.rsvstudy.otherClasses.SyncModel;

/**
 * Created by ali.azaz on 7/14/2017.
 */

public class GetAllData extends AsyncTask<String, String, String> {

    HttpURLConnection urlConnection;
    SyncListAdapter adapter;
    List<SyncModel> list;
    int position;
    private String TAG = "";
    private final Context mContext;
    private ProgressDialog pd;
    private final String syncClass;


    public GetAllData(Context context, String syncClass) {
        mContext = context;
        this.syncClass = syncClass;
        TAG = "Get" + syncClass;
    }

    public GetAllData(Context context, String syncClass, SyncListAdapter adapter, List<SyncModel> list) {
        mContext = context;
        this.syncClass = syncClass;
        this.adapter = adapter;
        this.list = list;
        TAG = "Get" + syncClass;
        switch (syncClass) {
            case "VersionApp":
                position = 0;
                break;
            case "Users":
                position = 1;
                break;/*
            case "UCs":
                position = 2;
                break;
            case "LHW":
                position = 3;
                break;
            case "Taluka":
                position = 4;
                break;
            case "Villages":
                position = 5;
                break;
            case "Children":
                position = 6;
                break;*/
            case "Childlist":
                position = 2;
                break;
        }
        list.get(position).settableName(syncClass);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(mContext);
        pd.setTitle("Syncing " + syncClass);
        pd.setMessage("Getting connected to server...");
//        pd.show();
        list.get(position).setstatus("Getting connected to server...");
        list.get(position).setstatusID(2);
        list.get(position).setmessage("");
        adapter.updatesyncList(list);

    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        switch (values[0]) {
            case "VersionApp":
                position = 0;
                break;
            case "Users":
                position = 1;
                break;/*
            case "UCs":
                position = 2;
                break;
            case "LHW":
                position = 3;
                break;
            case "Taluka":
                position = 4;
                break;
            case "Villages":
                position = 5;
                break;
            case "Children":
                position = 6;
                break;*/
            case "Childlist":
                position = 2;
                break;
        }
        list.get(position).setstatus("Syncing");
        list.get(position).setstatusID(2);
        list.get(position).setmessage("");
        adapter.updatesyncList(list);
    }

    @Override
    protected String doInBackground(String... args) {

        StringBuilder result = new StringBuilder();
        Log.d(TAG, "doInBackground: Class: " + syncClass);
        URL url = null;
        try {
            switch (syncClass) {
                case "VersionApp":
                    url = new URL(MainApp._UPDATE_URL + VersionAppContract.VersionAppTable._URI);
                    position = 0;
                    break;
                case "Users":
                    url = new URL(MainApp._HOST_URL + UsersContract.singleUser._URI);
                    position = 1;
                    break;
                /*case "UCs":
                    url = new URL(MainApp._HOST_URL + UCsContract.singleUCs._URI);
                    position = 2;
                    break;
                case "LHW":
                    url = new URL(MainApp._HOST_URL + LHWContract.lhwEntry._URI);
                    position = 3;
                    break;
                case "Taluka":
                    url = new URL(MainApp._HOST_URL + TalukasContract.singleTalukas._URI);
                    position = 4;
                    break;
                case "Villages":
                    url = new URL(MainApp._HOST_URL + VillagesContract.singleVillage._URI);
                    position = 5;
                    break;
                case "Children":
                    url = new URL(MainApp._HOST_URL + ChildrenContract.singleChild._URI);
                    position = 6;
                    break;*/
                case "Childlist":
                    url = new URL(MainApp._HOST_URL + ChildList.singleChildList._URI);
                    position = 2;
                    break;

            }
            Log.d(TAG, "doInBackground: URL: " + url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(100000 /* milliseconds */);
            urlConnection.setConnectTimeout(150000 /* milliseconds */);

            switch (syncClass) {
                case "Childlist":
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoOutput(true);
                    urlConnection.setDoInput(true);
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    urlConnection.setRequestProperty("charset", "utf-8");
                    urlConnection.setUseCaches(false);
                    // Starts the query
                    urlConnection.connect();
                    DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
                    JSONObject json = new JSONObject();
                    try {
                        json.put("f_type", "f1");
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    Log.d(TAG, "downloadUrl: " + json.toString());
                    wr.writeBytes(json.toString());
                    wr.flush();
                    wr.close();
                    break;
            }

            Log.d(TAG, "doInBackground: " + urlConnection.getResponseCode());
            publishProgress(syncClass);
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                publishProgress("In Progress");

                String line;
                while ((line = reader.readLine()) != null) {
                    Log.i(TAG, syncClass + " In: " + line);
                    result.append(line);
                }
            }
        } catch (java.net.SocketTimeoutException e) {
            Log.d(TAG, "doInBackground: " + e.getMessage());
            return null;
        } catch (java.io.IOException e) {
            Log.d(TAG, "doInBackground: " + e.getMessage());
            return null;
        } finally {
//            urlConnection.disconnect();
        }


        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {

        //Do something with the JSON string
        if (result != null) {
            String json = result;
            if (json.length() > 0) {
                DatabaseHelper db = new DatabaseHelper(mContext);
                String message;
                try {
                    JSONArray jsonArray = new JSONArray();
                    int insertCount = 0;

                    switch (syncClass) {

                        case "VersionApp":
                            insertCount = db.syncVersionApp(new JSONObject(result));
                            if (insertCount == 1) jsonArray.put("1");
                            position = 0;
                            break;
                        case "Users":
                            jsonArray = new JSONArray(result);
                            insertCount = db.syncUser(jsonArray);
                            position = 1;
                            break;/*
                        case "UCs":
                            db.syncUCs(jsonArray);
                            position = 2;
                            break;
                        case "LHW":
                            db.syncLHWs(jsonArray);
                            position = 3;
                            break;
                        case "Taluka":
                            db.syncTalukas(jsonArray);
                            position = 4;
                            break;
                        case "Villages":
                            db.syncVillages(jsonArray);
                            position = 5;
                            break;
                        case "Children":
                            db.syncChildren(jsonArray);
                            position = 6;
                            break;*/
                        case "Childlist":
                            jsonArray = new JSONArray(result);
                            insertCount = db.syncChildlist(jsonArray);
                            position = 2;
                            break;
                    }

                    pd.setMessage("Received: " + jsonArray.length());
                    list.get(position).setmessage("Received: " + jsonArray.length() + ", Saved: " + insertCount);
                    list.get(position).setstatus(insertCount == 0 ? "Unsuccessful" : "Successful");
                    list.get(position).setstatusID(insertCount == 0 ? 2 : 3);
                    adapter.updatesyncList(list);
//                    pd.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                pd.setMessage("Received: " + json.length() + "");
                list.get(position).setmessage("Received: " + json.length() + "");
                list.get(position).setstatus("Successfull");
                list.get(position).setstatusID(3);
                adapter.updatesyncList(list);
//                pd.show();
            }
        } else {
            pd.setTitle("Connection Error");
            pd.setMessage("Server not found!");
            list.get(position).setstatus("Failed");
            list.get(position).setstatusID(1);
            list.get(position).setmessage("Server not found!");
            adapter.updatesyncList(list);
//            pd.show();
        }
    }

}
