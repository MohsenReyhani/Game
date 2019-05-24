package com.mohsen.game.application.restService;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.mohsen.game.application.MainApplication;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class RestService {

    public static final int TIME_OUT_SECONDS = 15;
    public static final int LONG_TIME_OUT_SECONDS = 180;
    public static final int INITIAL_TIMEOUT_MS = 180 * 1000;

    private static RestService mInstance;
    private RequestQueue mRequestQueue;

    private static Context mCtx;

    private RestService(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();


    }

    public static synchronized RestService getInstance() {


        if (mInstance == null) {
            Context context = MainApplication.getAppContext();
            mInstance = new RestService(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public RequestFuture<JSONObject> postMethodJsonResult(String url, JSONObject body) throws TimeoutException, InterruptedException, ExecutionException {

        return postMethodJsonResult(url, body, null);
    }

    public RequestFuture<JSONObject> postMethodJsonResult(String url, JSONObject body, final Map<String, String> header) throws TimeoutException, InterruptedException, ExecutionException {

        RequestFuture<JSONObject> future = RequestFuture.newFuture();


        JsonObjectRequest request =
                new JsonObjectRequest(Request.Method.POST, url, body, future, future) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {

                        return header == null ? super.getHeaders() : header;
                    }
                };

        request.setRetryPolicy(getRetryPolicy());
        addToRequestQueue(request);

        return future;
    }


    public RequestFuture<StandardResponse<byte[]>> getMethodInputStreamResult(String url,  final Map<String, String> header) throws TimeoutException, InterruptedException, ExecutionException {

        RequestFuture<StandardResponse<byte[]>> future = RequestFuture.newFuture();
        
        CustomInputStreamRequest request =
                new CustomInputStreamRequest(Request.Method.GET, url, new HashMap<String, String>(), header, future, future);

        request.setRetryPolicy(getRetryPolicy());
        addToRequestQueue(request);

        return future;
    }

    public RequestFuture<String> postMethodStringResult(String url, final Map<String, String> body, final Map<String, String> header) throws TimeoutException, InterruptedException, ExecutionException {

        RequestFuture<String> future = RequestFuture.newFuture();


        CustomStringRequest request =
                new CustomStringRequest(Request.Method.POST, url, body, new HashMap<String, String>(), future, future);

        request.setRetryPolicy(getRetryPolicy());
        addToRequestQueue(request);

        return future;
    }

    public RequestFuture<String> getMethodStringResult(String url, final Map<String, String> header) throws TimeoutException, InterruptedException, ExecutionException {

        RequestFuture<String> future = RequestFuture.newFuture();


        CustomStringRequest request =
                new CustomStringRequest(Request.Method.GET, url, new HashMap<String, String>(), header, future, future);

        request.setRetryPolicy(getRetryPolicy());
        addToRequestQueue(request);

        return future;
    }

    /*public RequestFuture<JSONArray> postMethodJsonArrayResult(String url, JSONObject body) throws TimeoutException, InterruptedException, ExecutionException {


        return postMethodJsonArrayResult(url, body, null);
    }

    public RequestFuture<JSONArray> postMethodJsonArrayResult(String url, JSONObject body, final Map<String, String> header) throws TimeoutException, InterruptedException, ExecutionException {

        RequestFuture<JSONArray> future = RequestFuture.newFuture();


        JsonArrayRequest request =
                new JsonArrayRequest(Request.Method.POST, url, body, future, future) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {

                        return header == null ? super.getHeaders() : header;
                    }
                };


        request.setRetryPolicy(getRetryPolicy());
        addToRequestQueue(request);

        return future;
    }

    public RequestFuture<JSONArray> getMethodJsonArrayResult(String url) throws TimeoutException, InterruptedException, ExecutionException {


        return getMethodJsonArrayResult(url, null);
    }

    public RequestFuture<JSONArray> getMethodJsonArrayResult(String url, final Map<String, String> header) throws TimeoutException, InterruptedException, ExecutionException {

        RequestFuture<JSONArray> future = RequestFuture.newFuture();


        JsonArrayRequest request =
                new JsonArrayRequest(Request.Method.GET, url, "", future, future) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {

                        return header == null ? super.getHeaders() : header;
                    }
                };


        request.setRetryPolicy(getRetryPolicy());
        addToRequestQueue(request);

        return future;
    }*/

    public RequestFuture<JSONObject> getMethodJsonResult(String url) throws TimeoutException, InterruptedException, ExecutionException {


        return getMethodJsonResult(url, null);
    }

    public RequestFuture<JSONObject> getMethodJsonResult(String url, final Map<String, String> header) throws TimeoutException, InterruptedException, ExecutionException {

        RequestFuture<JSONObject> future = RequestFuture.newFuture();


        JsonObjectRequest request =
                new JsonObjectRequest(Request.Method.GET, url, new JSONObject(), future, future) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {

                        return header == null ? super.getHeaders() : header;
                    }
                };


        request.setRetryPolicy(getRetryPolicy());
        addToRequestQueue(request);

        return future;
    }


    public DefaultRetryPolicy getRetryPolicy() {
        return new DefaultRetryPolicy(
                INITIAL_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }
}
