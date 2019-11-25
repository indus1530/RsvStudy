package edu.aku.hassannaqvi.rsvstudy.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class ChildList {

    private String dssid;
    private String mother_name;
    private String father_name;
    private String hhhead;
    private String study_id;

    private String dob;

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }


    public ChildList() {
    }


    public String getDssid() {
        return dssid;
    }

    public String getMother_name() {
        return mother_name;
    }

    public String getFather_name() {
        return father_name;
    }

    public String getHhhead() {
        return hhhead;
    }

    public String getStudy_id() {
        return study_id;
    }

    public ChildList sync(JSONObject jsonObject) throws JSONException {
        this.dssid = jsonObject.getString(singleChildList.COLUMN_DSSID);
        this.mother_name = jsonObject.getString(singleChildList.COLUMN_MOTHER_NAME);
        this.father_name = jsonObject.getString(singleChildList.COLUMN_FATHER_NAME);
        this.hhhead = jsonObject.getString(singleChildList.COLUMN_HHHEAD);
        this.study_id = jsonObject.getString(singleChildList.COLUMN_STUDY_ID);
        this.dob = jsonObject.getString(singleChildList.COLUMN_DOB);

        return this;
    }

    public ChildList hydrate(Cursor cursor) {
        this.dssid = cursor.getString(cursor.getColumnIndex(singleChildList.COLUMN_DSSID));
        this.mother_name = cursor.getString(cursor.getColumnIndex(singleChildList.COLUMN_MOTHER_NAME));
        this.father_name = cursor.getString(cursor.getColumnIndex(singleChildList.COLUMN_FATHER_NAME));
        this.hhhead = cursor.getString(cursor.getColumnIndex(singleChildList.COLUMN_HHHEAD));
        this.study_id = cursor.getString(cursor.getColumnIndex(singleChildList.COLUMN_STUDY_ID));
        this.dob = cursor.getString(cursor.getColumnIndex(singleChildList.COLUMN_DOB));

        return this;
    }


    /*public ChildList hydrateForm(Cursor cursor) {

        //this.dssid = cursor.getString(cursor.getColumnIndex(FormsContract.FormsTable.COLUMN_UID));
        this.mother_name = cursor.getString(cursor.getColumnIndex(FormsContract.FormsTable.COLUMN_FORMTYPE));
        this.study_id = cursor.getString(cursor.getColumnIndex(FormsContract.FormsTable.COLUMN_ISTATUS));
        this.lhw_code = cursor.getString(cursor.getColumnIndex(FormsContract.FormsTable.COLUMN_CODE_LHW));
        this.caseid = cursor.getString(cursor.getColumnIndex(FormsContract.FormsTable.COLUMN_REF_ID));
        this.ref_date = cursor.getString(cursor.getColumnIndex(FormsContract.FormsTable.COLUMN_FORMDATE));

        CrfChild formSa = new Gson().fromJson(cursor.getString(cursor.getColumnIndex(FormsContract.FormsTable.COLUMN_SA)), CrfChild.class);
        CrfChild formSb = new Gson().fromJson(cursor.getString(cursor.getColumnIndex(FormsContract.FormsTable.COLUMN_SB)), CrfChild.class);

        this.child_name = formSa.getPocfa09();
        this.f_name = formSb.getPocfb02();
        //this.rep_date = formSa.getKapr09();

        return this;
    }*/


    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(singleChildList.COLUMN_DSSID, this.dssid == null ? JSONObject.NULL : this.dssid);
        json.put(singleChildList.COLUMN_MOTHER_NAME, this.mother_name == null ? JSONObject.NULL : this.mother_name);
        json.put(singleChildList.COLUMN_FATHER_NAME, this.father_name == null ? JSONObject.NULL : this.father_name);
        json.put(singleChildList.COLUMN_HHHEAD, this.hhhead == null ? JSONObject.NULL : this.hhhead);
        json.put(singleChildList.COLUMN_STUDY_ID, this.study_id == null ? JSONObject.NULL : this.study_id);
        json.put(singleChildList.COLUMN_DOB, this.dob == null ? JSONObject.NULL : this.dob);

        return json;
    }

    public static abstract class singleChildList implements BaseColumns {

        public static final String TABLE_NAME = "childlist";
        public static final String _ID = "_ID";
        public static final String COLUMN_DSSID = "dssid";
        public static final String COLUMN_MOTHER_NAME = "mother_name";
        public static final String COLUMN_FATHER_NAME = "father_name";
        public static final String COLUMN_HHHEAD = "hhhead";
        public static final String COLUMN_DOB = "dob";
        public static final String COLUMN_STUDY_ID = "study_id";

        public static final String _URI = "childlist.php";
    }

}
