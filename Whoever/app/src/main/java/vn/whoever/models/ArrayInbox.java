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

        LocalAccount user = new LocalAccount();

        inbox.setUserSend(user);
        inbox.setIsRead(true);
        inbox.setLastMessage("Trời nóng quá :3");

        inbox.setLastTimeChat(new Date());

        for(int i = 0; i < 1; ++i) {
            arrayList.add(inbox);
        }

        return  arrayList;
    }
}
