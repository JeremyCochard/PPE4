package fr.cours.ppe4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private String result=null;
    private SQLiteDatabase db;
    private Cursor cursorLogin, cursorPassword, cursorAuth, cursorSetUtilisateur;
    private ContentValues contentValue;
    private String getLogin;
    private String getPassword;
    private String getAdmin;
    private String reqAuth;
    private String req;
    private Integer resultNumUtilisateur;

    private Context dbContext;

    private static String DB_NAME = "zonestockage.db";

    private static final int DB_VERSION = 1;

    public MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.dbContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public String getLoginDb(String login){
        getLogin="SELECT login From UTILISATEUR where login="+"\""+login+"\"";

        db = this.getReadableDatabase();

            cursorLogin = this.getReadableDatabase().rawQuery(getLogin, null);
            cursorLogin.moveToFirst();

            result = cursorLogin.getString(0);

            db.close();
            cursorLogin.close();

        return result ;
    }

    public String getPassworDb(String password, String login){
        getPassword="SELECT password From UTILISATEUR where login ="+"\""+login+"\""+"AND password="+"\""+password+"\"";

        db = this.getReadableDatabase();

            cursorPassword = this.getReadableDatabase().rawQuery(getPassword, null);

            cursorPassword.moveToFirst();
            result = cursorPassword.getString(0);

            cursorPassword.close();
            db.close();

        return result ;
    }

    public boolean getAuthAdministrateur(String login){
        boolean testAuth = false;

        getAdmin="SELECT type From UTILISATEUR where login="+"\""+login+"\""+"AND type = 'administrateur'";


        db = this.getReadableDatabase();

        cursorPassword = this.getReadableDatabase().rawQuery(getAdmin, null);

        cursorPassword.moveToFirst();
        result = cursorPassword.getString(0);

        Log.v("admin",result);
        if (result=="administrateur"){
            testAuth=true;
        }
        cursorPassword.close();
        db.close();
        return testAuth;
    }

}
