package utils;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


public class Task<T> extends AsyncTask<Object, Void, T> {

    private final Callback<T> mCallback;
    private final RunOnBackground<T> mRunInBackground;
    protected Exception exception;

    /**
     * @param runInBackground
     * @param callback
     */
    public Task(@NonNull RunOnBackground<T> runInBackground, @Nullable Callback<T> callback) {
        this.mCallback = callback;
        this.mRunInBackground = runInBackground;
    }

    @Override
    protected T doInBackground(Object... params) {
        try {
            return mRunInBackground.runInBackground();
        } catch (Exception e) {
            e.printStackTrace();
            this.exception = e;
            return null;
        }
    }

    @Override
    protected void onPostExecute(T data) {
        super.onPostExecute(data);
        if (mCallback != null)
            mCallback.onCallback(data, exception);
    }

    public interface RunOnBackground<T> {
        T runInBackground() throws Exception;
    }

    public void execute() {
        super.execute();
    }

}
