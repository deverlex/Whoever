package vn.whoever.TransConnection;

import android.app.Activity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vn.whoever.models.SearchContact;

/**
 * Created by spider man on 4/23/2016.
 */
public class ContactTransaction extends  AbstractTransaction{

    private List<SearchContact> searchContactList;

    public ContactTransaction(Activity activity) {
        super(activity);
    }

    public List<SearchContact> getSearchContactList() {
        return this.searchContactList;
    }

    public void queryContact(String query) {
        searchContactList = new ArrayList<SearchContact>();
        String url_query = "http://192.168.1.112:8080/mainserver/mobile/friends/search";
        UrlQuery urlQuery = new UrlQuery(url_query);
        urlQuery.putPathVariable(query);

        JsonArrayRequest queryContactRequest = new JsonArrayRequest(Request.Method.GET, urlQuery.getUrl(),
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray resp) {
                try {
                    for(int i = 0; i < resp.length(); ++i) {
                        JSONObject obj = resp.getJSONObject(i);
                        SearchContact contact = new SearchContact();
                        contact.setNickName(obj.getString("nickName"));
                        contact.setAvatar("null");
                        contact.setSsoId(obj.getString("ssoId"));
                        searchContactList.add(contact);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return onCreateHeaders();
            }

            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
    }

    public void getContactOnline() {

    }
}
