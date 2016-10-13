package database;

/**
 * Created by David on 8/30/16.
 */

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Implementation of the ORM lite database for Android SQLite.
 */
@Singleton
public class OrmLiteSqliteDatabase implements OrmLiteDatabase {

    private OrmLiteSqliteOpenHelperExt mHelper;
    private static final int DATABASE_VERSION = 1;
    private static final String LOG_TAG = "OrmLiteSqliteDatabase";

    @Inject
    void setApplication(Application application) {
        mHelper = new OrmLiteSqliteOpenHelperExt(application, "database", null, DATABASE_VERSION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate() {
        //Log.d(LOG_TAG, "onCreate");
        // No table pre-creations. The DAOs are responsible to create the
        // tables.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onUpgrade(int oldVersion, int newVersion) {
        // Log.d(LOG_TAG, "onUpgrade");
        // UPGRADE DATABASE HERE
        // We want to stack the if blocks in a way that each upgrade will be handled in order of database version
        if (oldVersion < 2) {
        // do nothing yet
        }
        if (oldVersion < 3) {
        // do nothing yet
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createTableIfNotExists(Class<?> clazz) {
        try {
            TableUtils.createTableIfNotExists(mHelper.getConnectionSource(), clazz);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Unable to create table" + clazz, e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Dao<T, Integer> getDao(Class<T> clazz) {
        try {
            return mHelper.getDao(clazz);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Unable to create dao", e);
        }
    }

    /**
     * This subclass is the interface with the Android SQLite database; it has
     * callbacks for creation and database upgrade.
     */
    private class OrmLiteSqliteOpenHelperExt extends OrmLiteSqliteOpenHelper {
        OrmLiteSqliteOpenHelperExt(Context context, String databaseName, CursorFactory factory, int databaseVersion) {
            super(context, databaseName, factory, databaseVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
            OrmLiteSqliteDatabase.this.onCreate();
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
            OrmLiteSqliteDatabase.this.onUpgrade(oldVersion, newVersion);
        }
    }
}