package database;

import com.google.inject.Inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by David on 8/30/16.
 */

public abstract class DatabaseAccesor {

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    public @interface CreateOrmLiteTable {
        Class<?>[] value() default {};
    }

    private OrmLiteDatabase mDatabase;

    @Inject
    void setDatabase(OrmLiteDatabase database) {
        mDatabase = database;
        CreateOrmLiteTable annotation = getClass().getAnnotation(CreateOrmLiteTable.class);
        if (annotation != null) {
            for (Class<?> cls : annotation.value()) {
                mDatabase.createTableIfNotExists(cls);
            }
        }
    }

    /**
     * Get the database.
     */
    protected OrmLiteDatabase getDatabase() {
        return mDatabase;
    }

}