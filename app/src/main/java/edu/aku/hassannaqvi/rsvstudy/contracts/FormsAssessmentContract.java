package edu.aku.hassannaqvi.rsvstudy.contracts;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class FormsAssessmentContract {

    public static final String DATE_FORMAT = "yyyy-mm-dd";
    public static final String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.rsvstudy";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_FORMS = "forms";
    private final String projectName = "ANISA RSV Study-2";
    //private final String surveyType = "SN";
    private String _ID = "";
    private String _UID = "";
    private String formType = "";
    private String formDate = ""; // Date
    private String user = ""; // Interviewer
    private String istatus = ""; // Interview Status
    private String sB = "";
    private String istatus88x = ""; // Interview Status
    private String DSSID = "";
    private String sA = "";
    private String NextVisit = "";
    private String endingdatetime = "";
    private String gpsLat = "";
    private String gpsLng = "";
    private String gpsDT = "";
    private String gpsAcc = "";
    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion = "";
    private String status = "";


    public FormsAssessmentContract() {
    }

    public static String getDbDateString(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    public static Date getDateFromDb(String dateText) {
        SimpleDateFormat dbDateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            return dbDateFormat.parse(dateText);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getsB() {
        return sB;
    }

    public void setsB(String sB) {
        this.sB = sB;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public FormsAssessmentContract Sync(JSONObject jsonObject) throws JSONException {
        this._ID = jsonObject.getString(FormsAssessmentTable.COLUMN_ID);
        this._UID = jsonObject.getString(FormsAssessmentTable.COLUMN_UID);
        this.formDate = jsonObject.getString(FormsAssessmentTable.COLUMN_FORMDATE);
        this.user = jsonObject.getString(FormsAssessmentTable.COLUMN_USER);
        this.istatus = jsonObject.getString(FormsAssessmentTable.COLUMN_ISTATUS);
        this.istatus88x = jsonObject.getString(FormsAssessmentTable.COLUMN_ISTATUS88x);
        this.DSSID = jsonObject.getString(FormsAssessmentTable.COLUMN_DSSID);
        this.endingdatetime = jsonObject.getString(FormsAssessmentTable.COLUMN_ENDINGDATETIME);
        this.sA = jsonObject.getString(FormsAssessmentTable.COLUMN_SA);
        this.sB = jsonObject.getString(FormsAssessmentTable.COLUMN_SB);
        this.gpsLat = jsonObject.getString(FormsAssessmentTable.COLUMN_GPSLAT);
        this.gpsLng = jsonObject.getString(FormsAssessmentTable.COLUMN_GPSLNG);
        this.gpsDT = jsonObject.getString(FormsAssessmentTable.COLUMN_GPSDATE);
        this.gpsAcc = jsonObject.getString(FormsAssessmentTable.COLUMN_GPSACC);
        this.deviceID = jsonObject.getString(FormsAssessmentTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(FormsAssessmentTable.COLUMN_DEVICETAGID);
        this.synced = jsonObject.getString(FormsAssessmentTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(FormsAssessmentTable.COLUMN_SYNCED_DATE);
        this.appversion = jsonObject.getString(FormsAssessmentTable.COLUMN_SYNCED_DATE);
        this.formType = jsonObject.getString(FormsAssessmentTable.COLUMN_FORMTYPE);

        return this;

    }

    public FormsAssessmentContract Hydrate(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(FormsAssessmentTable.COLUMN_ID));
        this._UID = cursor.getString(cursor.getColumnIndex(FormsAssessmentTable.COLUMN_UID));
        this.formDate = cursor.getString(cursor.getColumnIndex(FormsAssessmentTable.COLUMN_FORMDATE));
        this.user = cursor.getString(cursor.getColumnIndex(FormsAssessmentTable.COLUMN_USER));
        this.istatus = cursor.getString(cursor.getColumnIndex(FormsAssessmentTable.COLUMN_ISTATUS));
        this.istatus88x = cursor.getString(cursor.getColumnIndex(FormsAssessmentTable.COLUMN_ISTATUS88x));
        this.DSSID = cursor.getString(cursor.getColumnIndex(FormsAssessmentTable.COLUMN_DSSID));
        this.endingdatetime = cursor.getString(cursor.getColumnIndex(FormsAssessmentTable.COLUMN_ENDINGDATETIME));
        this.sA = cursor.getString(cursor.getColumnIndex(FormsAssessmentTable.COLUMN_SA));
        this.sB = cursor.getString(cursor.getColumnIndex(FormsAssessmentTable.COLUMN_SB));
        this.gpsLat = cursor.getString(cursor.getColumnIndex(FormsAssessmentTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndex(FormsAssessmentTable.COLUMN_GPSLNG));
        this.gpsDT = cursor.getString(cursor.getColumnIndex(FormsAssessmentTable.COLUMN_GPSDATE));
        this.gpsAcc = cursor.getString(cursor.getColumnIndex(FormsAssessmentTable.COLUMN_GPSACC));
        this.deviceID = cursor.getString(cursor.getColumnIndex(FormsAssessmentTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(FormsAssessmentTable.COLUMN_DEVICETAGID));
        this.appversion = cursor.getString(cursor.getColumnIndex(FormsAssessmentTable.COLUMN_APPVERSION));
        this.status = cursor.getString(cursor.getColumnIndex(FormsAssessmentTable.COLUMN_STATUS));
        this.formType = cursor.getString(cursor.getColumnIndex(FormsAssessmentTable.COLUMN_FORMTYPE));

        return this;
    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(FormsAssessmentTable.COLUMN_ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(FormsAssessmentTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(FormsAssessmentTable.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(FormsAssessmentTable.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        json.put(FormsAssessmentTable.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
        json.put(FormsAssessmentTable.COLUMN_ISTATUS88x, this.istatus88x == null ? JSONObject.NULL : this.istatus88x);
        json.put(FormsAssessmentTable.COLUMN_DSSID, this.DSSID == null ? JSONObject.NULL : this.DSSID);
        json.put(FormsAssessmentTable.COLUMN_ENDINGDATETIME, this.endingdatetime == null ? JSONObject.NULL : this.endingdatetime);

        if (!this.sA.equals("")) {
            json.put(FormsAssessmentTable.COLUMN_SA, this.sA.equals("") ? JSONObject.NULL : new JSONObject(this.sA));
        }
        if (!this.sB.equals("")) {
            json.put(FormsAssessmentTable.COLUMN_SB, this.sB.equals("") ? JSONObject.NULL : new JSONObject(this.sB));
        }
        json.put(FormsAssessmentTable.COLUMN_GPSLAT, this.gpsLat == null ? JSONObject.NULL : this.gpsLat);
        json.put(FormsAssessmentTable.COLUMN_GPSLNG, this.gpsLng == null ? JSONObject.NULL : this.gpsLng);
        json.put(FormsAssessmentTable.COLUMN_GPSDATE, this.gpsDT == null ? JSONObject.NULL : this.gpsDT);
        json.put(FormsAssessmentTable.COLUMN_GPSACC, this.gpsAcc == null ? JSONObject.NULL : this.gpsAcc);
        json.put(FormsAssessmentTable.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
        json.put(FormsAssessmentTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
        json.put(FormsAssessmentTable.COLUMN_APPVERSION, this.appversion == null ? JSONObject.NULL : this.appversion);
        json.put(FormsAssessmentTable.COLUMN_STATUS, this.status == null ? JSONObject.NULL : this.status);
        json.put(FormsAssessmentTable.COLUMN_FORMTYPE, this.formType == null ? JSONObject.NULL : this.formType);

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

    public String getNextVisit() {
        return NextVisit;
    }

    public void setNextVisit(String NextVisit) {
        this.NextVisit = NextVisit;
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

    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }

    public String getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(String gpsLng) {
        this.gpsLng = gpsLng;
    }

    public String getGpsDT() {
        return gpsDT;
    }

    public void setGpsDT(String gpsDT) {
        this.gpsDT = gpsDT;
    }

    public String getGpsAcc() {
        return gpsAcc;
    }

    public void setGpsAcc(String gpsAcc) {
        this.gpsAcc = gpsAcc;
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


    public static abstract class FormsAssessmentTable implements BaseColumns {

        public static final String TABLE_NAME = "forms_ca";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECT_NAME = "projectname";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_FORMTYPE = "formtype";
        public static final String COLUMN_USER = "username";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_ISTATUS88x = "istatus88x";
        public static final String COLUMN_DSSID = "DSSID";
        public static final String COLUMN_ENDINGDATETIME = "endingdatetime";
        public static final String COLUMN_SA = "sA";
        public static final String COLUMN_SB = "sB";
        public static final String COLUMN_GPSLAT = "gpslat";
        public static final String COLUMN_GPSLNG = "gpslng";
        public static final String COLUMN_GPSDATE = "gpsdate";
        public static final String COLUMN_GPSACC = "gpsacc";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "tagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
        public static final String COLUMN_APPVERSION = "appversion";
        public static final String COLUMN_STATUS = "status";

        public static String _URL = "sync.php";

    }
}
