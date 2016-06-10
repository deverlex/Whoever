package vn.whoever.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Nguyen Van Do on 1/20/2016.
 * This class is testing for UI - not is class of application
 */
public class ArrayMessage {

    private ArrayList<Message> messages;

    public ArrayList<Message> getMessages() {
        messages = new ArrayList<>();

        Message message0 = new Message();
        message0.setMe(true);
        message0.setMessage("Em học bài chưa?");
        message0.setDate(new Date());

        Message message1 = new Message();
        message1.setMe(false);
        message1.setMessage("Em học bài rồi ^^");
        message1.setDate(new Date());

        Message message2 = new Message();
        message2.setMe(true);
        message2.setMessage("Thế đi ngủ đi :))");
        message2.setDate(new Date());

        Message message3 = new Message();
        message3.setMe(false);
        message3.setMessage("Vâng, em đi ngủ đây");
        message3.setDate(new Date());

        messages.add(message0);
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);

        return messages;
    }
}
