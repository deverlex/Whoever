package vn.whoever.TransConnection;

/**
 * Created by spider man on 1/9/2016.
 */
public class UrlQuery {

    private String url;
    private int countParam = 0;

    private UrlQuery() {}

    public UrlQuery(String url) {
        this.url = url;
    }

    public void putPathVariable(String value) {
        url += ("/" + value + "/");
    }

    public void putRequestParam(String key, String value) {
        if(countParam ==0) {
            url+=("?" + key + "=" + value);
            countParam+=1;
        } else {
            url+=("&" + key + "=" + value);
            countParam+=1;
        }
    }

    public String getUrl() {
        return this.url;
    }

    public int getCountParam() {
        return this.countParam;
    }
}
