package lk.ehand.healthservice.domain;

public class DoctorScheduleRequest {

    private int noOfDays;

    public DoctorScheduleRequest(){}

    public DoctorScheduleRequest(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }
}
