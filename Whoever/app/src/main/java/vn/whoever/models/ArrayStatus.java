package vn.whoever.models;

import java.util.ArrayList;
import java.util.Date;

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

        LocalAccount user = new LocalAccount();


        status.setSenderStatus(user);

        status.setDateWriterStatus(new Date());
        status.setCountLike(30);
        status.setCountDislike(10);
        status.setCountComment(50);


        for(int i = 0; i < 1; ++i) {
            arrStatus.add(status);
        }
        return arrStatus;
    }
}
