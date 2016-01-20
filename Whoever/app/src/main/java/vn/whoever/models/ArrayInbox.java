package vn.whoever.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by spider man on 1/20/2016.
 */
public class ArrayInbox {

    public ArrayList<Inbox> arrayList;

    public ArrayList<Inbox> getArrayList() {
        arrayList = new ArrayList<>();

        Inbox inbox = new Inbox();

        User user = new User();
        user.setNickName("Ngan Yeu");

        inbox.setUserSend(user);
        inbox.setIsRead(true);
        inbox.setLastMessage("Em yeu anh :)))) hihihi");

        inbox.setLastTimeChat(new Date());

        for(int i = 0; i < 10; ++i) {
            arrayList.add(inbox);
        }

        return  arrayList;
    }
}
