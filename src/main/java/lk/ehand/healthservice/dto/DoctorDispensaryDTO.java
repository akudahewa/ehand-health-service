package lk.ehand.healthservice.dto;

import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class DoctorDispensaryDTO implements Serializable {

    private String doctorId;
    private String dispensaryId;
    private String status;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDispensaryId() {
        return dispensaryId;
    }

    public void setDispensaryId(String dispensaryId) {
        this.dispensaryId = dispensaryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DoctorDispensaryDTO(String doctorId, String dispensaryId, String status) {
        this.doctorId = doctorId;
        this.dispensaryId = dispensaryId;
        this.status = status;
    }
}
