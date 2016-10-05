package utils;



public interface Callback<T> {
    /**
     * @param data      - the data supplied - if any, may be null
     * @param exception - the exception raised - if any, may be null
     */
    void onCallback(T data, Exception exception);
}
