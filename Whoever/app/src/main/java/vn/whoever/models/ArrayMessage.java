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

        Message message4 = new Message();
        message4.setMe(false);
        message4.setMessage("Thế mình là người của nhau em nha :))");
        message4.setDate(new Date());

        Message message5 = new Message();
        message5.setMe(true);
        message5.setMessage("Ừ, mai anh giới thiệu em với gia đình, rồi mình đến với nhau nha, yhaha!!!");
        message5.setDate(new Date());

        Message message6 = new Message();
        message6.setMe(true);
        message6.setMessage("Em đang làm gì đấy, nhớ em quá nha!");
        message6.setDate(new Date());


        Message message7 = new Message();
        message7.setMe(false);
        message7.setMessage("Em đang xem ảnh của anh, ahihi, nhớ a quá a ạ, phải bỏ ảnh anh ra ngắm cho đỡ nhớ vậy. Hức!!!@@@");
        message7.setDate(new Date());

        messages.add(message0);
       // messages.add(message0);
        /*

        messages.add(message0);
        messages.add(message0);
        messages.add(message0);
        messages.add(message0);
        messages.add(message0);
        messages.add(message0);
        messages.add(message0);
        messages.add(message0);
        messages.add(message0);
        messages.add(message0);
        messages.add(message0);
        messages.add(message0);
        messages.add(message0);
        messages.add(message0);
        messages.add(message1);
        */
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);
        messages.add(message4);
        messages.add(message5);
        messages.add(message6);
        messages.add(message7);

        return messages;
    }
}
