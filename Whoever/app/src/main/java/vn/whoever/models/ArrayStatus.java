package vn.whoever.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vn.whoever.models.Status;
import vn.whoever.models.User;

/**
 * Created by spider man on 1/17/2016.
 */
public class ArrayStatus {

    public ArrayList<Status> arrStatus;

    public ArrayList<Status> getArrStatus() {
        arrStatus = new ArrayList<>();

        Status status = new Status();
        status.setIdStatus(100);
        status.setContentStatus("Tất cả vì mục tiêu, ra trường đúng hạn !!!");

        User user = new User();
        user.setNickName("Nguyễn Đô");


        status.setSenderStatus(user);

        status.setDateWriterStatus(new Date());
        status.setCountLike(30);
        status.setCountDislike(10);
        status.setCountComment(50);


        for(int i = 0; i < 10; ++i) {
            arrStatus.add(status);
        }
        return arrStatus;
    }
}
