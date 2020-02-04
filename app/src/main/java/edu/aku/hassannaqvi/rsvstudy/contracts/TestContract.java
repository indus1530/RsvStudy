package edu.aku.hassannaqvi.rsvstudy.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class TestContract {

    private final String projectName = "ANISA RSV Study-2";
    //private final String surveyType = "SN";
    private String _ID = "";
    private String _UID = "";
    private String _UUID = "";
    private String formType = "";
    private String formDate = ""; // Date
    private String user = ""; // Interviewer
    private String istatus = ""; // Interview Status
    private String istatus88x = ""; // Interview Status
    private String DSSID = "";
    private String sA = "";
    private String testStatus = "";
    private String endingdatetime = "";
    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion = "";

    public TestContract() {
    }

    public String get_UUID() {
        return _UUID;
    }

    public void set_UUID(String _UUID) {
        this._UUID = _UUID;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus;
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public TestContract Sync(JSONObject jsonObject) throws JSONException {
        this._ID = jsonObject.getString(TestTable.COLUMN_ID);
        this._UID = jsonObject.getString(TestTable.COLUMN_UID);
        this.formDate = jsonObject.getString(TestTable.COLUMN_FORMDATE);
        this.user = jsonObject.getString(TestTable.COLUMN_USER);
        this.istatus = jsonObject.getString(TestTable.COLUMN_ISTATUS);
        this.istatus88x = jsonObject.getString(TestTable.COLUMN_ISTATUS88x);
        this.DSSID = jsonObject.getString(TestTable.COLUMN_DSSID);
        this.endingdatetime = jsonObject.getString(TestTable.COLUMN_ENDINGDATETIME);
        this.sA = jsonObject.getString(TestTable.COLUMN_SA);
        this.deviceID = jsonObject.getString(TestTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(TestTable.COLUMN_DEVICETAGID);
        this.synced = jsonObject.getString(TestTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(TestTable.COLUMN_SYNCED_DATE);
        this.appversion = jsonObject.getString(TestTable.COLUMN_SYNCED_DATE);
        this.formType = jsonObject.getString(TestTable.COLUMN_FORMTYPE);
        this._UUID = jsonObject.getString(TestTable.COLUMN_UUID);
        this.testStatus = jsonObject.getString(TestTable.COLUMN_TEST_STATUS);

        return this;

    }

    public TestContract Hydrate(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(TestTable.COLUMN_ID));
        this._UID = cursor.getString(cursor.getColumnIndex(TestTable.COLUMN_UID));
        this.formDate = cursor.getString(cursor.getColumnIndex(TestTable.COLUMN_FORMDATE));
        this.user = cursor.getString(cursor.getColumnIndex(TestTable.COLUMN_USER));
        this.DSSID = cursor.getString(cursor.getColumnIndex(TestTable.COLUMN_DSSID));
        this.sA = cursor.getString(cursor.getColumnIndex(TestTable.COLUMN_SA));
        this.deviceID = cursor.getString(cursor.getColumnIndex(TestTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(TestTable.COLUMN_DEVICETAGID));
        this.appversion = cursor.getString(cursor.getColumnIndex(TestTable.COLUMN_APPVERSION));
        this.formType = cursor.getString(cursor.getColumnIndex(TestTable.COLUMN_FORMTYPE));
        this._UUID = cursor.getString(cursor.getColumnIndex(TestTable.COLUMN_UUID));
        this.testStatus = cursor.getString(cursor.getColumnIndex(TestTable.COLUMN_TEST_STATUS));

        return this;
    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(TestTable.COLUMN_ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(TestTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(TestTable.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(TestTable.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        json.put(TestTable.COLUMN_DSSID, this.DSSID == null ? JSONObject.NULL : this.DSSID);

        json.put(TestTable.COLUMN_ENDINGDATETIME, this.endingdatetime == null ? JSONObject.NULL : this.endingdatetime);

        if (!this.sA.equals("")) {
            json.put(TestTable.COLUMN_SA, this.sA.equals("") ? JSONObject.NULL : new JSONObject(this.sA));
        }

        json.put(TestTable.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
        json.put(TestTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
        json.put(TestTable.COLUMN_APPVERSION, this.appversion == null ? JSONObject.NULL : this.appversion);
        json.put(TestTable.COLUMN_FORMTYPE, this.formType == null ? JSONObject.NULL : this.formType);
        json.put(TestTable.COLUMN_UUID, this._UUID == null ? JSONObject.NULL : this._UUID);
        json.put(TestTable.COLUMN_TEST_STATUS, this.testStatus == null ? JSONObject.NULL : this.testStatus);

        return json;
    }

    public String getProjectName() {
        return projectName;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_UID() {
        return _UID;
    }

    public void set_UID(String _UID) {
        this._UID = _UID;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }

    public String getIstatus88x() {
        return istatus88x;
    }

    public void setIstatus88x(String istatus88x) {
        this.istatus88x = istatus88x;
    }

    public String getDSSID() {
        return DSSID;
    }

    public void setDSSID(String Study_Id) {
        this.DSSID = Study_Id;
    }


    public String getsA() {
        return sA;
    }

    public void setsA(String sA) {
        this.sA = sA;
    }


    public String getEndingdatetime() {
        return endingdatetime;
    }

    public void setEndingdatetime(String endingdatetime) {
        this.endingdatetime = endingdatetime;
    }


    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSynced_date() {
        return synced_date;
    }

    public void setSynced_date(String synced_date) {
        this.synced_date = synced_date;

    }


    public static abstract class TestTable implements BaseColumns {

        public static final String TABLE_NAME = "tests";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECT_NAME = "projectname";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_UUID = "_uuid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_FORMTYPE = "formtype";
        public static final String COLUMN_USER = "username";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_ISTATUS88x = "istatus88x";
        public static final String COLUMN_DSSID = "DSSID";
        public static final String COLUMN_ENDINGDATETIME = "endingdatetime";
        public static final String COLUMN_SA = "sA";
        public static final String COLUMN_TEST_STATUS = "tstatus";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "tagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
        public static final String COLUMN_APPVERSION = "appversion";

        public static String _URL = "sync.php";

    }
}
