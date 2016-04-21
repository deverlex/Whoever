package vn.whoever.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by spider man on 2/20/2016.
 */
public class ArrayReply {

    private ArrayList<Reply> replies;

    public ArrayList<Reply> getReplies() {
        replies = new ArrayList<>();

        LocalAccount user = new LocalAccount();

        Reply reply = new Reply();
        reply.setIdReply(12345343);
        reply.setContent("con cá vàng là con cá vàng, xòe đôi cánh, xòe đôi cánh");
        reply.setTimeUpload(new Date());
        reply.setUserReply(user);
        reply.setCountDislike(10);
        reply.setCountLike(24);

        for(int i = 0 ; i < 1; ++i) {
            replies.add(reply);
        }
        return replies;
    }

}
