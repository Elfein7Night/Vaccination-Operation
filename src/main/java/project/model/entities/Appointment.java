package project.model.entities;

import project.model.util.UtilMethods;
import project.model.exceptions.InvalidInputException;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
public class Appointment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "appointment_id")
    private int appointmentId;
    @Basic
    @Column(name = "clinic_id")
    private int clinicId;
    @Basic
    @Column(name = "citizen_id")
    private Integer citizenId;
    @Basic
    @Column(name = "worker_id")
    private Integer workerId;
    @Basic
    @Column(name = "date")
    private Timestamp date;
    @ManyToOne
    @JoinColumn(name = "clinic_id", referencedColumnName = "clinic_id", updatable = false, insertable = false, nullable = false)
    private Clinic clinicByClinicId;
    @ManyToOne
    @JoinColumn(name = "citizen_id", referencedColumnName = "citizen_id", updatable = false, insertable = false)
    private Citizen citizenByCitizenId;
    @ManyToOne
    @JoinColumn(name = "worker_id", referencedColumnName = "worker_id", updatable = false, insertable = false)
    private Worker workerByWorkerId;

    public static final int MIN_DAYS_RANGE_FOR_APPOINT = 1;
    public static final int MAX_DAYS_RANGE_FOR_APPOINT = 30;

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getClinicId() {
        return clinicId;
    }

    public void setClinicId(int clinicId) {
        this.clinicId = clinicId;
    }

    public Integer getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Integer citizenId) {
        this.citizenId = citizenId;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public static Timestamp createAppointmentDate(int nDaysFromNow) throws InvalidInputException {
        if (nDaysFromNow < MIN_DAYS_RANGE_FOR_APPOINT || nDaysFromNow > MAX_DAYS_RANGE_FOR_APPOINT) {
            throw new InvalidInputException(
                    "Appointment date must be between " +
                            MIN_DAYS_RANGE_FOR_APPOINT + " and " +
                            MAX_DAYS_RANGE_FOR_APPOINT + " days from now (not " + nDaysFromNow + ")"
            );
        }
        return UtilMethods.nDaysFromNow(nDaysFromNow);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appointment that = (Appointment) o;

        if (appointmentId != that.appointmentId) return false;
        if (clinicId != that.clinicId) return false;
        if (!Objects.equals(citizenId, that.citizenId)) return false;
        if (!Objects.equals(workerId, that.workerId)) return false;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        int result = appointmentId;
        result = 31 * result + clinicId;
        result = 31 * result + (citizenId != null ? citizenId.hashCode() : 0);
        result = 31 * result + (workerId != null ? workerId.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", clinicId=" + clinicId +
                ", citizenId=" + citizenId +
                ", workerId=" + workerId +
                ", date=" + date +
                '}';
    }

    public Clinic getClinicByClinicId() {
        return clinicByClinicId;
    }

    public void setClinicByClinicId(Clinic clinicByClinicId) {
        this.clinicByClinicId = clinicByClinicId;
    }

    public Citizen getCitizenByCitizenId() {
        return citizenByCitizenId;
    }

    public void setCitizenByCitizenId(Citizen citizenByCitizenId) {
        this.citizenByCitizenId = citizenByCitizenId;
    }

    public Worker getWorkerByWorkerId() {
        return workerByWorkerId;
    }

    public void setWorkerByWorkerId(Worker workerByWorkerId) {
        this.workerByWorkerId = workerByWorkerId;
    }

    public static final class AppointmentBuilder {
        private int appointmentId;
        private int clinicId;
        private Integer citizenId;
        private Integer workerId;
        private Timestamp date;

        private AppointmentBuilder() {
        }

        public static AppointmentBuilder anAppointment() {
            return new AppointmentBuilder();
        }

        public AppointmentBuilder withAppointmentId(int appointmentId) {
            this.appointmentId = appointmentId;
            return this;
        }

        public AppointmentBuilder withClinicId(int clinicId) {
            this.clinicId = clinicId;
            return this;
        }

        public AppointmentBuilder withCitizenId(Integer citizenId) {
            this.citizenId = citizenId;
            return this;
        }

        public AppointmentBuilder withWorkerId(Integer workerId) {
            this.workerId = workerId;
            return this;
        }

        public AppointmentBuilder withDate(Timestamp date) {
            this.date = date;
            return this;
        }

        public Appointment build() {
            Appointment appointment = new Appointment();
            appointment.setAppointmentId(appointmentId);
            appointment.setClinicId(clinicId);
            appointment.setCitizenId(citizenId);
            appointment.setWorkerId(workerId);
            appointment.setDate(date);
            return appointment;
        }
    }
}
