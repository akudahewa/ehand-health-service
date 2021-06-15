package lk.ehand.healthservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Setter @Getter
@Table(name = "doctor_schedule_grid")
public class DoctorScheduleGrid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn (name = "doctor_dispensary_id",referencedColumnName = "doctor_dispensary_id")
    private DoctorDispensary doctorDispensary;

    @JsonIgnore
    @OneToMany(mappedBy = "doctorScheduleGrid",cascade = CascadeType.ALL)
    private List<DoctorScheduleGridAlteration> doctorScheduleGridAlterationList;

    @JsonIgnore
    @OneToMany(mappedBy = "doctorScheduleGrid",cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    private String dayOfWeek;
    private String sessionStart;
    private String sessionEnd;
    private String status;
    private int maxCount;

    public DoctorScheduleGrid(){}

    public DoctorScheduleGrid(DoctorDispensary doctorDispensary, List<DoctorScheduleGridAlteration> doctorScheduleGridAlterationList, String dayOfWeek,
                              String sessionStart, String sessionEnd, String status,int maxCount) {
        this.doctorDispensary = doctorDispensary;
        this.doctorScheduleGridAlterationList = doctorScheduleGridAlterationList;
        this.dayOfWeek = dayOfWeek;
        this.sessionStart = sessionStart;
        this.sessionEnd = sessionEnd;
        this.status = status;
        this.maxCount = maxCount;

    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DoctorDispensary getDoctorDispensary() {
        return doctorDispensary;
    }

    public void setDoctorDispensary(DoctorDispensary doctorDispensary) {
        this.doctorDispensary = doctorDispensary;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getSessionStart() {
        return sessionStart;
    }

    public void setSessionStart(String sessionStart) {
        this.sessionStart = sessionStart;
    }

    public String getSessionEnd() {
        return sessionEnd;
    }

    public void setSessionEnd(String sessionEnd) {
        this.sessionEnd = sessionEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DoctorScheduleGridAlteration> getDoctorScheduleGridAlterationList() {
        return doctorScheduleGridAlterationList;
    }

    public void setDoctorScheduleGridAlterationList(List<DoctorScheduleGridAlteration> doctorScheduleGridAlterationList) {
        this.doctorScheduleGridAlterationList = doctorScheduleGridAlterationList;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setDoctorAppoinments(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
