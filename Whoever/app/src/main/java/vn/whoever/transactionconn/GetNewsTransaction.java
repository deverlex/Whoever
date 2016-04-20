package vn.whoever.transactionconn;

import android.app.Activity;
import android.location.Location;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import vn.whoever.models.supports.Position;
import vn.whoever.transactionconn.utils.GPSLocation;

/**
 * Created by spider man on 1/7/2016.
 */
public class GetNewsTransaction {

    private Integer httpStatusCode = null;
    private Activity activity;

    private GetNewsTransaction() {}

    public GetNewsTransaction(Activity activity) {
        this.activity = activity;
    }

    public void getNewsFeed(String ssoId, String order, int offset) {
        Map<String, Object> mapGetStatus = new LinkedHashMap<>();
        mapGetStatus.put("ssoId", ssoId);
        mapGetStatus.put("order", order);
        mapGetStatus.put("offset", offset);

        JSONObject jsonReqStatus = new JSONObject(mapGetStatus);

        Map<String, Double> loc = new LinkedHashMap<>();
        try {
            Position position = (new GPSLocation(activity)).getLocation();
            if(position != null) {
                loc.put("xLoc", Math.round(position.getX()*1000000)/1000000.0);
                loc.put("yLoc", Math.round(position.getY()*1000000)/1000000.0);
            }
        } catch (SecurityException e) {}

        final JSONObject reqLoc = new JSONObject(loc);
        try {
            jsonReqStatus.put("location", reqLoc);
        } catch (JSONException e) {}

        JsonObjectRequest newsRequest = new JsonObjectRequest(Request.Method.POST, AddressConnection.url_news, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("responseStatus", response.toString());
                // TODO: insert DB status
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                exTractError(error);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", System.getProperty("http.agent"));
                return headers;
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(newsRequest);
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    private void exTractError(VolleyError error) {
        NetworkResponse networkResponse = error.networkResponse;
        if(networkResponse != null) {
            httpStatusCode = networkResponse.statusCode;
        }

        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            httpStatusCode = HttpStatus.SC_BAD_REQUEST;
        } else if (error instanceof AuthFailureError) {
            //TODO
        } else if (error instanceof ServerError) {
            httpStatusCode = HttpStatus.SC_SERVICE_UNAVAIABLE;
        } else if (error instanceof NetworkError) {
            httpStatusCode = HttpStatus.SC_BAD_REQUEST;
        } else if (error instanceof ParseError) {
            //TODO
        }
    }
}
