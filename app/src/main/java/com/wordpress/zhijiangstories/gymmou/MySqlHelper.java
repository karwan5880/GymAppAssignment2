package com.example.gymappassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MySqlHelper {	

	public static final String MYDATABASE_NAME = "GYMAPP";
	public static final String MYDATABASE_TABLE1 = "EXERCISE";
	public static final int MYDATABASE_VERSION = 1;
	public static final String KEY_CONTENT1 = "Pushup";
	public static final String KEY_CONTENT2 = "Situp";
	private static final String SCRIPT_CREATE_DATABASE = "create table "
			+ MYDATABASE_TABLE1 + " (" + KEY_CONTENT1 + " integer,"
					+ KEY_CONTENT2 + "integer"
					+ ""
					+ ");";
	private SQLiteHelper sqLiteHelper;
	private SQLiteDatabase sqLiteDatabase;
	private Context context;
	
	public MySqlHelper(Context c) {
		context = c;
	}
	
	public MySqlHelper openToRead() throws
	android.database.SQLException {
		sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,
				MYDATABASE_VERSION);
		sqLiteDatabase = sqLiteHelper.getReadableDatabase();
		return this;
	}
	
	public MySqlHelper openToWrite() throws
	android.database.SQLException {
		sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,
				MYDATABASE_VERSION);
		sqLiteDatabase = sqLiteHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		sqLiteHelper.close();
	}

	public long insert(int content) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(KEY_CONTENT1, content);
		return sqLiteDatabase.insert(MYDATABASE_TABLE1, null,
				contentValues);
	}
	
	public int deleteAll() {
		return sqLiteDatabase.delete(MYDATABASE_TABLE1, null, null);
	}
	
	public String queueAll() {
		String[] columns = new String[] { KEY_CONTENT1 };
		Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE1, columns,
				null, null, null, null, null);
		String result = "";
		int index_CONTENT = cursor.getColumnIndex(KEY_CONTENT1);
		for (cursor.moveToFirst(); !(cursor.isAfterLast());
				cursor.moveToNext()) {
			result = result + cursor.getString(index_CONTENT) + "\n";
		}
		return result;
	}

	public class SQLiteHelper extends SQLiteOpenHelper {
		public SQLiteHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(SCRIPT_CREATE_DATABASE);
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int
				newVersion) {
		}
	}
	

}
