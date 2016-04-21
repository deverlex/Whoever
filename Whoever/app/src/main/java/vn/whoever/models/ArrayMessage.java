package vn.whoever.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by spider man on 1/28/2016.
 */
public class ArrayMessage {

    private ArrayList<Message> messages;

    public ArrayList<Message> getMessages() {
        messages = new ArrayList<>();

        Message message0 = new Message();
        message0.setMe(true);
        message0.setMessage("Ngân à, em thích a không?");
        message0.setDate(new Date());

        Message message1 = new Message();
        message1.setMe(false);
        message1.setMessage("Em có ạ. Ahihi");
        message1.setDate(new Date());

        Message message2 = new Message();
        message2.setMe(false);
        message2.setMessage("Thế còn anh thì sao? :))");
        message2.setDate(new Date());

        Message message3 = new Message();
        message3.setMe(false);
        message3.setMessage("Có chứ, thích quá đi chứ, ahihi");
        message3.setDate(new Date());

        messages.add(message0);
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);

        return messages;
    }
}
