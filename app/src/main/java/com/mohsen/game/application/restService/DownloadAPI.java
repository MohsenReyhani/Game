package com.mohsen.game.application.restService;

import com.android.volley.ServerError;
import com.mohsen.game.R;
import com.mohsen.game.application.MainApplication;
import com.mohsen.game.application.helper.AppHelper;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DownloadAPI {

    public static final String URL_CDN_ADDRESSS = "";//CloudSettings.getBackendCDNFilesAddress();

    public static StandardResponse<byte[]> downloadFile(String fileName) throws Exception {
        if(AppHelper.isNetworkAvailable(MainApplication.getAppContext()) == false)
            throw new Exception(MainApplication.getAppContext().getString(R.string.network_unavailable), null);

        RestService restService = RestService.getInstance();


        try {
            StandardResponse<byte[]> apiCallResult =
                    restService.getMethodInputStreamResult(URL_CDN_ADDRESSS + "/" + fileName, new HashMap<String, String>())
                            .get(RestService.LONG_TIME_OUT_SECONDS, TimeUnit.SECONDS);


            return apiCallResult;

        } catch (TimeoutException e) {
            String resultMsg = "خطا در ارتباط با سرور.";
            throw new Exception(resultMsg + " : " + ((e.getMessage() != null) ? e.getMessage() : ""), e);
        } catch (ExecutionException e) {

            String resultMsg = e.getMessage();
            if (ServerError.class == e.getCause().getClass() && ((ServerError) e.getCause()) != null && ((ServerError) e.getCause()).networkResponse != null) {
                JSONObject result = new JSONObject(new String(((ServerError) e.getCause()).networkResponse.data));
                resultMsg = result.getString("message");
            }

            if (resultMsg.equals("Incorrect email or password!"))
                resultMsg = "نام کاربری یا کلمه عبور صحیح نمی باشد.";

            throw new Exception(resultMsg, e);
        } catch (InterruptedException e) {
            throw new Exception("execution interrupted : " + ((e.getMessage() != null) ? e.getMessage() : ""), e);
        } catch (Exception e) {
            throw new Exception("execution : " + ((e.getMessage() != null) ? e.getMessage() : ""), e);
        }
    }
}
