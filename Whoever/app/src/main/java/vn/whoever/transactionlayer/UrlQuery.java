package vn.whoever.transactionlayer;

/**
 * Created by spider man on 1/9/2016.
 */
public class UrlQuery {

    private String url;
    private int countParam = 0;

    public UrlQuery() {}

    public UrlQuery(String url) {
        this.url = url + "?";
    }

    public void putParam(String key, String value) {
        if(countParam ==0) {
            url+=(key + "=" + value);
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
