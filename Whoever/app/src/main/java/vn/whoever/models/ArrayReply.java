package vn.whoever.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by spider man on 2/20/2016.
 */
public class ArrayReply {

    private ArrayList<Comment> replies;

    public ArrayList<Comment> getReplies() {
        replies = new ArrayList<>();

        LocalAccount user = new LocalAccount();

        Comment comment = new Comment();

        for(int i = 0 ; i < 1; ++i) {
            replies.add(comment);
        }
        return replies;
    }

}
