package com.mohsen.game.application.restService;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.Iterator;
import java.util.Map;

class CustomInputStreamRequest extends Request<StandardResponse<byte[]>> {
    //create a static map for directly accessing headers

    private int mMethod;
    private String mUrl;
    private Map<String, String> mParams;
    private Map<String, String> mHeader;
    private Response.Listener<StandardResponse<byte[]>> mListener;

    public CustomInputStreamRequest(int method, String url, Map<String, String> params, Map<String, String> header,
                                    Response.Listener<StandardResponse<byte[]>> reponseListener, Response.ErrorListener errorListener) {
        // TODO Auto-generated constructor stub

        super(method, url, errorListener);
        // this request would never use cache.
        mUrl = url;
        setShouldCache(false);
        mListener = reponseListener;
        mHeader = header;
        mParams = params;
        mMethod = method;
    }

    @Override
    public String getUrl() {
        String url = mUrl;
        if (mMethod == Method.GET) {
            StringBuilder stringBuilder = new StringBuilder(url);
            Iterator<Map.Entry<String, String>> iterator = mParams.entrySet().iterator();
            int i = 1;
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                if (i == 1) {
                    stringBuilder.append("?" + entry.getKey() + "=" + entry.getValue());
                } else {
                    stringBuilder.append("&" + entry.getKey() + "=" + entry.getValue());
                }
                iterator.remove(); // avoids a ConcurrentModificationException
                i++;
            }
            url = stringBuilder.toString();
        }
        return url;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {

        return mHeader == null ? super.getHeaders() : mHeader;
    }

    @Override
    protected Map<String, String> getParams()
            throws AuthFailureError {
        return mParams;
    }


    @Override
    protected void deliverResponse(StandardResponse<byte[]> response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<StandardResponse<byte[]>> parseNetworkResponse(NetworkResponse response) {

        StandardResponse<byte[]> standardResponse = new StandardResponse<byte[]>(
                response.statusCode,
                response.headers,
                response.data
        );

        //Pass the response data here
        return Response.success(standardResponse, HttpHeaderParser.parseCacheHeaders(response));
    }
}