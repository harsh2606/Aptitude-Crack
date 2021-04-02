package com.example.apttitude_crack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.util.Log;
import java.io.IOException;

public class TestAdapter1 {
    protected static final String TAG = "DataAdapter";
    private  Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public TestAdapter1(Context context) {
        this.mContext = context;
    }

    public TestAdapter1 createDatabase() throws SQLException {
        mDbHelper = new DataBaseHelper(this.mContext);
        try {
            this.mDbHelper.createDataBase();
            return this;
        } catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
    }

    public TestAdapter1 open() throws SQLException {
        try {
            this.mDbHelper.openDataBase();
            this.mDbHelper.close();
            this.mDb = this.mDbHelper.getReadableDatabase();
            return this;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "open >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public void close() {
        this.mDbHelper.close();
    }

    public Cursor getTestData(int row) {
        try {
            Cursor mCur = this.mDb.rawQuery("SELECT * FROm IQTestSaga where RowID =" + row, null);
            if (mCur != null) {
                mCur.moveToNext();
            }
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public boolean SaveEmployee(String name, String email) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("FILENAME", name);
            cv.put("ANSWER", email);
            this.mDb.insert("Employees", null, cv);
            Log.d("SaveEmployee", "informationsaved");
            return true;
        } catch (Exception ex) {
            Log.d("SaveEmployee", ex.toString());
            return false;
        }
    }

    public Cursor getLevelData() {
        try {
            Cursor mCur = this.mDb.rawQuery("SELECT * FROM levelinfo", null);

            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getLevelData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }


    public boolean SaveLevelScore(String level, String score, Integer isCompleted) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("score", score);
            cv.put("iscompleted", isCompleted.toString());
            String where = "id = ?";

            String[] whereArgs = { level };

            this.mDb.update("levelinfo", cv, where, whereArgs);
//            this.mDb.insert("Employees", null, cv);
            Log.d("Save Level Score", "Score Saved.");
            return true;
        } catch (Exception ex) {
            Log.d("Save Level Score", ex.toString());
            return false;
        }
    }

    public boolean UnlockNextLevel(String level) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("iscompleted", "1");
            String where = "id = ?";

            String[] whereArgs = { level };

            this.mDb.update("levelinfo", cv, where, whereArgs);
            Log.d("Unlock Next Level", "Done");
            return true;
        } catch (Exception ex) {
            Log.d("Unlock Next Level", ex.toString());
            return false;
        }
    }

    public boolean UpdateAnswer(String Question) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("answer  ", "1");
            String where = "RowID = ?";

            String[] whereArgs = { Question };

            this.mDb.update("IQTestSaga", cv, where, whereArgs);
            Log.d("Answer Updated", "Done");
            return true;
        } catch (Exception ex) {
            Log.d("Answer Updated", ex.toString());
            return false;
        }
    }

    public Cursor getDailyRewardDay() {
        try {
            Cursor mCur = this.mDb.rawQuery("SELECT * FROM daily_reward", null);

            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getDailyRewardDay >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public boolean SaveDailyReward(String date, String collected, String day) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("date", date);
            cv.put("iscollected", collected);
            cv.put("day", day);
            this.mDb.insert("daily_reward", null, cv);
            Log.d("SaveDaily Reward", "informationsaved");
            return true;
        } catch (Exception ex) {
            Log.d("SaveDaily Reward", ex.toString());
            return false;
        }
    }

    public boolean CheckIsDataAlreadyInDBorNot(String query) {
        Cursor cursor = this.mDb.rawQuery(query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public String getPreviousScore(String level_id) {
        try {
        Cursor mCur = this.mDb.rawQuery("SELECT * FROM levelinfo where id =" + level_id, null);
            if (mCur.moveToFirst()) {
                return mCur.getString(2);
            }
            else
            {
                return "0";
            }
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getLevelData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public boolean isTableExists() {
        Cursor cursor = this.mDb.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = 'levelinfo'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
//            CreateLevelsTable(this.mDb);
        }


//        cursor = this.mDb.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = 'daily_reward'", null);
//        if(cursor!=null) {
//            if(cursor.getCount()>0) {
//                cursor.close();
//                return true;
//            }
//            cursor.close();
//            CreateDailyRewardTable(this.mDb);
//        }

        return false;
    }

//    public void CreateLevelsTable(SQLiteDatabase db) {
//        db.execSQL(DataBaseHelper.CREATE_LEVEL_TABLE);
//        InsertLevelData(db);
//    }
//
//    public void CreateDailyRewardTable(SQLiteDatabase db) {
//        db.execSQL(DataBaseHelper.CREATE_DAILY_REWARD);
//    }

    public void InsertLevelData(SQLiteDatabase db) {
        try {
            for (int i = 1; i<= 30; i++) {
                ContentValues values = new ContentValues();
                values.put("level", i);
                values.put("score", 0);
                values.put("iscompleted", i == 1 ? 1 : 0);
                db.insert(DataBaseHelper.DB_NAME, null, values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public TestAdapter1 updateDatabase() throws SQLException {
        mDbHelper = new DataBaseHelper(this.mContext);
        try {
            this.mDbHelper.UpdateDataBase();
            return this;
        } catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
    }

    public boolean SaveTitleData(String title_id, String title_name, String title_image) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("title_id", title_id);
            cv.put("titlename", title_name);
            cv.put("Isactivate", 1);
            cv.put("titleimg", title_image);
            this.mDb.insert("tbltitle", null, cv);
            Log.d("my ==========>>> ", "Saved Title Data SuccessFully :::: " + title_id);
            return true;
        } catch (Exception ex) {
            Log.d("my ==========>>>", "EXCEPTION :::: "+ex.toString());
            return false;
        }
    }

    public boolean SaveSubTitleData(String sub_title_id, String sub_title_name, String sub_title_image, String title_id) {
        try {
            ContentValues cv = new ContentValues();
           cv.put("sub_title_id", sub_title_id);
            cv.put("sub_title_name", sub_title_name);
            cv.put("title_id", title_id);
            cv.put("subtitleimg", sub_title_image);

            String where = "sub_title_id = ?";

            String[] whereArgs = { sub_title_id };

            this.mDb.insert("tblsubtitle" ,null, cv );


//            this.mDb.insert("tblsubtitle", null, cv);
            Log.d("my ==========>>> ", "Saved sub Title Data SuccessFully :::: " + sub_title_id);
            return true;
        } catch (Exception ex) {
            Log.d("my ==========>>>", "EXCEPTION :::: "+ex.toString());
            return false;
        }
    }

    public boolean SaveQuestionData(String question_id, String question, String sub_title_id, String discussion_id) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("question_id", question_id);
            cv.put("question", question);
            cv.put("sub_title_id", sub_title_id);
            cv.put("discussion_id", discussion_id);
            this.mDb.insert("tblquestion", null, cv);
            Log.d("my ==========>>> ", "Saved Question Data SuccessFully :::: " + question_id);
            return true;
        } catch (Exception ex) {
            Log.d("my ==========>>>", "EXCEPTION :::: "+ex.toString());
            return false;
        }
    }

    public boolean SaveOptionData(String objid, String options, String question_id) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("objid", objid);
            cv.put("options", options);
            cv.put("question_id", question_id);
            this.mDb.insert("tblobjective", null, cv);
            Log.d("my ==========>>> ", "Saved Option Data SuccessFully :::: " + objid);
            return true;
        } catch (Exception ex) {
            Log.d("my ==========>>>", "EXCEPTION :::: "+ex.toString());
            return false;
        }
    }

    public boolean SaveDiscussionData(String discussion_id, String title_id, String sub_title_id, String discussion_title, String exam_valid) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("discussion_id", discussion_id);
            cv.put("title_id", title_id);
            cv.put("sub_title_id", sub_title_id);
            cv.put("discussion_title", discussion_title);
            cv.put("exam_valid", exam_valid);
            this.mDb.insert("tbldiscussion", null, cv);
            Log.d("my ==========>>> ", "Saved Discussion Data SuccessFully :::: " + discussion_id);
            return true;
        } catch (Exception ex) {
            Log.d("my ==========>>>", "EXCEPTION :::: "+ex.toString());
            return false;
        }
    }

    public boolean SaveDescriptionData(String description_id, String description_detail, String discussion_id) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("description_id", description_id);
            cv.put("description_detail", description_detail);
            cv.put("discussion_id", discussion_id);
            this.mDb.insert("tbldescription", null, cv);
            Log.d("my ==========>>> ", "Saved Description Data SuccessFully :::: " + description_id);
            return true;
        } catch (Exception ex) {
            Log.d("my ==========>>>", "EXCEPTION :::: "+ex.toString());
            return false;
        }
    }

    public boolean SaveAnswerData(String answerid, String objid, String answer_description, String question_id) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("answerid", answerid);
            cv.put("objid", objid);
            cv.put("answer_description", answer_description);
            cv.put("question_id", question_id);
            this.mDb.insert("tblanswerr", null, cv);
            Log.d("my ==========>>> ", "Saved Answer Data SuccessFully :::: " + answerid);
            return true;
        } catch (Exception ex) {
            Log.d("my ==========>>>", "EXCEPTION :::: "+ex.toString());
            return false;
        }
    }

    public Cursor getAllSubTitleIds() {
        try {
            Cursor mCur = this.mDb.rawQuery("SELECT sub_title_id FROM tblsubtitle", null);
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getLevelData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public Cursor fetchTitles()
    {
        try{
            String Qry = "Select * From tbltitle";
            Log.e(TAG,"fetchTitles"+ Qry);
            Cursor mCur = this.mDb.rawQuery(Qry,null);
            return mCur;
        }
        catch (SQLException mSQLException){
            Log.e(TAG,"getQuestionByID"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public Cursor fetchSubTitles()
    {
        try{
            String Qry = "Select * From tblSubtitle";
            Log.e(TAG,"fetchTitles"+ Qry);
            Cursor mCur = this.mDb.rawQuery(Qry,null);
            return mCur;
        }
        catch (SQLException mSQLException){
            Log.e(TAG,"getQuestionByID"+ mSQLException.toString());
            throw mSQLException;
        }
    }


    public Cursor fetchSubTitles(String title_id)
    {
        try {
            String Qry = "SELECT ts.*, tq.tquestion, ifnull(tv.tvisited,0) as visited, tt.titlename FROM tblsubtitle ts " +
                    " LEFT JOIN (SELECT count(*) tquestion, sub_title_id FROM tblquestion GROUP BY sub_title_id) as tq ON ts.sub_title_id = tq.sub_title_id " +
                    " LEFT JOIN (SELECT count(*) tvisited, sub_title_id FROM tblquestion WHERE is_visited = 1 GROUP BY sub_title_id) tv ON ts.sub_title_id = tv.sub_title_id " +
                    " LEFT JOIN tbltitle tt ON ts.title_id = tt.title_id " +
                    " WHERE ts.title_id ="+title_id;
            Log.e(TAG, "fetchSubTitles >>" + Qry);
            Cursor mCur = this.mDb.rawQuery(Qry, null);
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getQuestionByID >>" + mSQLException.toString());
            throw mSQLException;

        }
    }

    public Cursor getNextQuestion(int sub_title_id)
    {
        try {
            String Qry = "select DISTINCT tq.* , tst.sub_title_name , ta.answerid ,ta.objid, ta.answer_description ,td.discussion_title, tdesc.description_detail, tdesc.description_id, tcount.qrow " +
                    " from  tblquestion as tq " +
                    " LEFT JOIN (SELECT question_id, sub_title_id, (SELECT COUNT(*) FROM tblquestion b WHERE  sub_title_id = "+sub_title_id+" AND a.question_id >= b.question_id ) as qrow FROM tblquestion a WHERE sub_title_id = "+sub_title_id+") tcount ON tq.question_id = tcount.question_id " +
                    " LEFT JOIN tblsubtitle as tst ON tq.sub_title_id = tst.sub_title_id " +
                    " LEFT JOIN tblanswerr as ta ON tq.question_id = ta.question_id  " +
                    " LEFT JOIN tbldiscussion as td ON tq.discussion_id = td.discussion_id " +
                    " LEFT JOIN tbldescription as tdesc ON td.discussion_id = tdesc.discussion_id " +
                    " WHERE tq.question_id = (SELECT min(question_id)as question_id FROM tblquestion where sub_title_id = "+sub_title_id+") AND tq.sub_title_id = " + sub_title_id;
            Log.d("My :: ", "QUERY :::::::::::::::::::::::"+Qry);
            Cursor mCur = this.mDb.rawQuery(Qry, null);
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getNextQuestion >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public Cursor getOptionsofQuestion(int question_id)
    {
        try {
            String Qry = "select * From  tblobjective where question_id = " + question_id;
            Cursor mCur = this.mDb.rawQuery(Qry, null);
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getNextQuestion >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public Cursor getNextQuestionByID(String sub_title_id, String question_id)
    {
        try {
            String Qry = "select tq.* , tst.sub_title_name , ta.answerid ,ta.objid, ta.answer_description ,td.discussion_title, tdesc.description_detail, tdesc.description_id , tcount.qrow " +
                    "from  tblquestion as tq " +
                    " LEFT JOIN (SELECT question_id, sub_title_id, (SELECT COUNT(*) FROM tblquestion b WHERE  sub_title_id = "+sub_title_id+" AND a.question_id >= b.question_id ) as qrow FROM tblquestion a WHERE sub_title_id = "+sub_title_id+") tcount ON tq.question_id = tcount.question_id " +
                    " LEFT JOIN tblsubtitle as tst ON tq.sub_title_id = tst.sub_title_id " +
                    " LEFT JOIN tblanswerr as ta ON tq.question_id = ta.question_id " +
                    " LEFT JOIN tbldiscussion as td ON tq.discussion_id = td.discussion_id " +
                    " LEFT JOIN tbldescription as tdesc ON td.discussion_id = tdesc.discussion_id " +
                    " WHERE tq.question_id = (SELECT MIN(question_id) from tblquestion where question_id > " + question_id + " AND sub_title_id = "+sub_title_id+") AND tq.sub_title_id = " + sub_title_id;
            Cursor mCur = this.mDb.rawQuery(Qry, null);
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getQuestionByID >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public Cursor getPreviousQuestionByID(String sub_title_id, String question_id)
    {
        try {
            String Qry = "select tq.* , tst.sub_title_name , ta.answerid ,ta.objid, ta.answer_description ,td.discussion_title, tdesc.description_detail, tdesc.description_id , tcount.qrow " +
                    " from  tblquestion as tq " +
                    " LEFT JOIN (SELECT question_id, sub_title_id, (SELECT COUNT(*) FROM tblquestion b WHERE  sub_title_id = "+sub_title_id+" AND a.question_id >= b.question_id ) as qrow FROM tblquestion a WHERE sub_title_id = "+sub_title_id+") tcount ON tq.question_id = tcount.question_id " +
                    " LEFT JOIN tblsubtitle as tst ON tq.sub_title_id = tst.sub_title_id " +
                    " LEFT JOIN tblanswerr as ta ON tq.question_id = ta.question_id " +
                    " LEFT JOIN tbldiscussion as td ON tq.discussion_id = td.discussion_id " +
                    " LEFT JOIN tbldescription as tdesc ON td.discussion_id = tdesc.discussion_id " +
                    " WHERE tq.question_id = (SELECT MAX(question_id) from tblquestion where question_id < " + question_id + " AND sub_title_id = "+sub_title_id+") AND tq.sub_title_id = " + sub_title_id;
            Cursor mCur = this.mDb.rawQuery(Qry, null);
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getQuestionByID >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public Cursor getQuestionByID(String sub_title_id, String question_id)
    {
        try {
            String Qry = "select tq.* , tst.sub_title_name , ta.answerid ,ta.objid, ta.answer_description ,td.discussion_title, tdesc.description_detail, tdesc.description_id, tcount.qrow " +
                    "from  tblquestion as tq " +
                    " LEFT JOIN (SELECT question_id, sub_title_id, (SELECT COUNT(*) FROM tblquestion b WHERE  sub_title_id = "+sub_title_id+" AND a.question_id >= b.question_id ) as qrow FROM tblquestion a WHERE sub_title_id = "+sub_title_id+") tcount ON tq.question_id = tcount.question_id " +
                    "LEFT JOIN tblsubtitle as tst ON tq.sub_title_id = tst.sub_title_id " +
                    "LEFT JOIN tblanswerr as ta ON tq.question_id = ta.question_id " +
                    "LEFT JOIN tbldiscussion as td ON tq.discussion_id = td.discussion_id " +
                    "LEFT JOIN tbldescription as tdesc ON td.discussion_id = tdesc.discussion_id " +
                    "WHERE tq.question_id =" + question_id + " AND tq.sub_title_id = " + sub_title_id;
            Cursor mCur = this.mDb.rawQuery(Qry, null);
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getQuestionByID >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public boolean CheckDoWeNeedSync(String sub_title_id) {
        Cursor cursor = this.mDb.rawQuery("SELECT * FROM tblquestion WHERE sub_title_id = "+sub_title_id+" and is_visited = 0", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return false;
            }
            cursor.close();
        }
        cursor.close();
        return true;
    }
    public boolean CheckDoWeNeedSync(String sub_title_id, String question_id) {
        String qry = "SELECT * FROM tblquestion WHERE sub_title_id = "+sub_title_id+" and question_id = (SELECT MIN(question_id) from tblquestion where question_id > " + question_id + " AND sub_title_id = "+sub_title_id+")";
        Log.d("my :::: ", "Cursor Count ::: " +qry );
        Cursor cursor = this.mDb.rawQuery(qry, null);
        if(cursor!=null) {

            if(cursor.getCount()>0) {
                cursor.close();
                return false;
            }
            cursor.close();
        }
        cursor.close();
        return true;
    }

    public int GetLastQuestionIdBySubTitle(String sub_title_id) {
        try {
            Cursor mCur = this.mDb.rawQuery("SELECT MAX(question_id) as last_record FROM tblquestion WHERE sub_title_id = "+sub_title_id+"", null);
            if (mCur.moveToFirst()) {
                return mCur.getInt(0);
            }
            else
            {
                return -1;
            }


        } catch (Exception ex) {
            Log.d("my ==========>>>", "EXCEPTION :::: "+ex.toString());
            return -1;
        }
    }

    public boolean SetQuestionIsVisitedTrue(String question_id) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("is_visited", 1);
            String where = "question_id = ?";

            String[] whereArgs = { question_id };

            this.mDb.update("tblquestion", cv, where, whereArgs);
            Log.d("Updated ::: tblquestion", "Is Visited True");
            return true;
        } catch (Exception ex) {
            Log.d("Save Level Score", ex.toString());
            return false;
        }
    }

    public boolean checkAnyPreviousQuestion(String sub_title_id, String question_id)
    {
        try {
            String Qry = "select tq.* , tst.sub_title_name , ta.answerid ,ta.objid, ta.answer_description ,td.discussion_title, tdesc.description_detail, tdesc.description_id " +
                    " from  tblquestion as tq " +
                    " LEFT JOIN tblsubtitle as tst ON tq.sub_title_id = tst.sub_title_id " +
                    " LEFT JOIN tblanswerr as ta ON tq.question_id = ta.question_id " +
                    " LEFT JOIN tbldiscussion as td ON tq.discussion_id = td.discussion_id " +
                    " LEFT JOIN tbldescription as tdesc ON td.discussion_id = tdesc.discussion_id " +
                    " WHERE tq.question_id = (SELECT MAX(question_id) from tblquestion where question_id < " + question_id + " AND sub_title_id = "+sub_title_id+") AND tq.sub_title_id = " + sub_title_id;
            Log.e(TAG, "checkAnyPreviousQuestion >>" + Qry);
            Cursor mCur = this.mDb.rawQuery(Qry, null);

            if(mCur!=null) {
                if(mCur.getCount()>0) {
                    mCur.close();
                    return true;
                }
                mCur.close();
            }
            mCur.close();
            return false;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getQuestionByID >>" + mSQLException.toString());
            return false;
//            throw mSQLException;

        }
    }

    public int InsertExamData(String sub_title_id, String user_id, long examtime, long time_given)
    {
        try {
            ContentValues cv = new ContentValues();
            cv.put("sub_title_id", sub_title_id);
            cv.put("user_id", user_id);
            cv.put("exam_time", examtime);
            cv.put("time_given", time_given);
            cv.put("exam_state", "START");
            this.mDb.insert("tblexam", null, cv);
            Cursor mCur;
            Log.d("my ==========>>> ", "Exam  Data SuccessFully :::: ");
            mCur = this.mDb.rawQuery("SELECT id FROM tblexam ORDER BY id DESC LIMIT 1", null);
            int exam_id = -1;
            if (mCur.moveToFirst()) {
                exam_id = mCur.getInt(0);
            }
            mCur.close();
            Log.d("my ==========>>> ", "Exam  Data ID  :::: " + exam_id);
            if (exam_id != -1)
            {
                String Qry = "SELECT q.question_id, a.objid FROM tblquestion q " +
                        "   LEFT JOIN tblanswerr a ON q.question_id = a.question_id  " +
                        "   WHERE q.sub_title_id = "+sub_title_id +" ORDER BY RANDOM() LIMIT 15" ;
                mCur = this.mDb.rawQuery(Qry, null);
                Log.d("my ==========my>>> ", "QUERY  :::: " + Qry);
                if (mCur.moveToFirst()) {
                    do {

                        try {
                            Log.d("my ==========>>> ", "question_id  :::: " + mCur.getInt(0));
                            Log.d("my ==========>>> ", "ans_id  :::: " + mCur.getInt(1));
                            ContentValues cv1 = new ContentValues();
                            cv1.put("exam_id", exam_id);
                            cv1.put("question_id", mCur.getInt(0));
                            cv1.put("ans_id",  mCur.getInt(1));
                            this.mDb.insert("tblexamdetail", null, cv1);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    } while (mCur.moveToNext());

                }
            }
            return  exam_id;
        } catch (Exception ex) {
            Log.d("my ==========>>>", "EXCEPTION :::: "+ex.toString());
            return  -1;

        }
    }


    public Cursor FetchQuestionOfExam(int exam_id) {

            try {
                String Qry = "SELECT question_id FROM tblexamdetail WHERE exam_id = "+exam_id;
                Log.e(TAG, "fetch Exam Questions  >>" + Qry);
                Cursor mCur = this.mDb.rawQuery(Qry, null);
                return mCur;
            } catch (SQLException mSQLException) {
                Log.e(TAG, "getQuestionByID >>" + mSQLException.toString());
                throw mSQLException;

            }
        }


    public boolean UpdateUserAns(int exam_id, String question_id, String objid, int isReview) {
        try {
            ContentValues cv = new ContentValues();
            if (objid != null)
            {
                cv.put("obj_selected_id", objid);
            }
            cv.put("isreview", isReview);
            String where = "question_id = ? AND exam_id = ?";

            String[] whereArgs = { question_id, String.valueOf(exam_id) };

            this.mDb.update("tblexamdetail", cv, where, whereArgs);
            Log.d("Updated tblexamdetail", "Is Visited True");
            return true;
        } catch (Exception ex) {
            Log.d("Save Level Score", ex.toString());
            return false;
        }
    }

    public Cursor getTestQuestionByID(int exam_id, String sub_title_id, String question_id)
    {
        try {
            String Qry = "select tq.* , tst.sub_title_name , ta.answerid ,ta.objid, ta.answer_description ,td.discussion_title, tdesc.description_detail, tdesc.description_id, te.isreview, te.obj_selected_id " +
                    " from  tblquestion as tq " +
                    " LEFT JOIN tblsubtitle as tst ON tq.sub_title_id = tst.sub_title_id " +
                    " LEFT JOIN tblanswerr as ta ON tq.question_id = ta.question_id " +
                    " LEFT JOIN tbldiscussion as td ON tq.discussion_id = td.discussion_id " +
                    " LEFT JOIN tbldescription as tdesc ON td.discussion_id = tdesc.discussion_id " +
                    " LEFT JOIN (SELECT * FROM tblexamdetail WHERE exam_id = "+exam_id+") as te ON tq.question_id = te.question_id " +
                    " WHERE tq.question_id =" + question_id + " AND tq.sub_title_id = " + sub_title_id;
            Cursor mCur = this.mDb.rawQuery(Qry, null);
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getQuestionByID >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public Cursor GetResult(int exam_id)
    {
        try {
            String Qry = "SELECT te.*, tq.total_question, ta.total_attemp, tna.total_notattamp, tw.wrong, tr.right_q  FROM tblexam te " +
                    " LEFT JOIN (SELECT COUNT(*) as total_question, exam_id FROM tblexamdetail WHERE exam_id = "+exam_id+") tq ON te.id = tq.exam_id " +
                    " LEFT JOIN (SELECT COUNT(*) as total_attemp, exam_id FROM tblexamdetail WHERE exam_id = "+exam_id+" AND obj_selected_id is not null) ta ON te.id = ta.exam_id " +
                    " LEFT JOIN (SELECT COUNT(*) as total_notattamp, exam_id FROM tblexamdetail WHERE exam_id = "+exam_id+" AND obj_selected_id is null) tna ON te.id = tna.exam_id " +
                    " LEFT JOIN (SELECT COUNT(*) as wrong, exam_id FROM (SELECT *, CASE  WHEN obj_selected_id == ans_id THEN 1 ELSE 0 END result_data FROM tblexamdetail WHERE exam_id = "+exam_id+" ) WHERE result_data = 0) tw ON te.id = tw.exam_id " +
                    " LEFT JOIN (SELECT COUNT(*) as right_q, exam_id FROM (SELECT  *, CASE   WHEN obj_selected_id == ans_id       THEN 1    ELSE 0 END result_data FROM tblexamdetail WHERE exam_id = "+exam_id+" )WHERE result_data = 1) tr ON te.id = tr.exam_id WHERE te.id = "+ exam_id;
            Cursor mCur = this.mDb.rawQuery(Qry, null);
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "GetResult >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public Cursor getRecordMarkedAsReview(int exam_id)
    {
        try {
            String Qry = "SELECT id, exam_id, question_id, IFNULL(isreview, 0) as isreview FROM tblexamdetail WHERE exam_id = "+ exam_id;
            Cursor mCur = this.mDb.rawQuery(Qry, null);
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getRecordMarkedAsReview >>" + mSQLException.toString());
            throw mSQLException;
        }
    }


    public Cursor getExamHistory(String sub_title_id)
    {
        try {
            String Qry = "SELECT id FROM tblexam WHERE sub_title_id = "+ sub_title_id + " ORDER BY id DESC";
            Cursor mCur = this.mDb.rawQuery(Qry, null);
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getRecordMarkedAsReview >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public Cursor getSubTitle(String sub_title_id) {
        try {
            String Qry = "SELECT * FROM tblsubtitle WHERE sub_title_id = "+ sub_title_id ;
            Cursor mCur = this.mDb.rawQuery(Qry, null);
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getRecordMarkedAsReview >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public boolean Exam_Finished(int exam_id, long time_taken) {
        try {
            ContentValues cv = new ContentValues();

            cv.put("time_taken", time_taken);
            String where = "id = ?";

            String[] whereArgs = { String.valueOf(exam_id) };

            this.mDb.update("tblexam", cv, where, whereArgs);
            Log.d("Updated Exam_Finished", "Is Visited True");
            return true;
        } catch (Exception ex) {
            Log.d("Save Level Score", ex.toString());
            return false;
        }
}

    public boolean SetGameState(int exam_id, String exam_state) {
        try {
            ContentValues cv = new ContentValues();

            cv.put("exam_state", exam_state);
            String where = "id = ?";

            String[] whereArgs = { String.valueOf(exam_id) };

            this.mDb.update("tblexam", cv, where, whereArgs);
            Log.d("Updated SetGameState", "Is Visited True");
            return true;
        } catch (Exception ex) {
            Log.d("Save Level Score", ex.toString());
            return false;
        }
    }

    public boolean SetExamLastQuestion(int exam_id, int question_id) {
        try {
            ContentValues cv = new ContentValues();

            cv.put("last_question_id", question_id);
            String where = "id = ?";

            String[] whereArgs = { String.valueOf(exam_id) };

            this.mDb.update("tblexam", cv, where, whereArgs);
            Log.d("Updated", " SetExamLastQuestion Is Visited True");
            return true;
        } catch (Exception ex) {
            Log.d("Save Level Score", ex.toString());
            return false;
        }
    }

    public Cursor CheckAnyPauseExam(String sub_title_id) {
        try {
            String Qry = "SELECT * FROM tblexam WHERE sub_title_id = "+ sub_title_id + " AND exam_state = 'PAUSE' ORDER BY id DESC LIMIT 1" ;
            Cursor mCur = this.mDb.rawQuery(Qry, null);
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "CheckAnyPauseExam >>" + mSQLException.toString());
            throw mSQLException;
        }

    }

    public Cursor getExamDetails(int exam_id) {
        try {
            String Qry = "SELECT * FROM tblexam WHERE id = "+ exam_id ;
            Cursor mCur = this.mDb.rawQuery(Qry, null);
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getExamDetails >>" + mSQLException.toString());
            throw mSQLException;
        }

    }

}
