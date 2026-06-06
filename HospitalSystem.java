import java.util.*;
public class HospitalSystem {
    public static void main(String[] args) {
        Hospital<String, String, String> hospital = new Hospital<>();
                Hospital <String, String, String> apps = new Hospital<>();
                apps.addPatient("Buse");
                apps.addDoctor("Buse", "Dr. Fulya Okudan");
        apps.setAppointment("Buse", "Dr. Fulya Okudan", "2026-05-12 14:00");

                System.out.println(apps);
                System.out.println(apps.getAppointment("Alice", "Dr. Strange"));

                try {
                    apps.getAppointment("Nur", "Dr. Nihal Kahveci");
                } catch (Hospital.PatientNotFoundException e) {
                    System.out.println(e);
                }

                try {
                    apps.getDoctors("Alice");
                } catch (Hospital.DoctorNotFoundException e) {
                    System.out.println(e);
                }
            }
        }

class Hospital<P, D, A> {  // P=Patient, D=Doctor, A=Appointment
    static class PatientNotFoundException extends RuntimeException {
        private final Object patient;

        public PatientNotFoundException(Object patient) {
            this.patient = patient;
        }

        @Override
        public String toString() {
            return "Patient" + patient + "not found at our hospital.";
        }
    }

    static class DoctorNotFoundException extends RuntimeException {
        private final Object doctor;
        private final Object patient;

        public DoctorNotFoundException(Object doctor, Object patient) {
            this.doctor = doctor;
            this.patient = patient;
        }

        @Override
        public String toString() {

            return "Doctor" + doctor + " not found for" + patient;
        }
    }

    private final Map<P, Map<D, A>> data = new LinkedHashMap<>();


    public void addPatient(P patient) {
        data.putIfAbsent(patient, new LinkedHashMap<>());
    }

    public void addDoctor(P patient, D doctor) {
        addPatient(patient);
        data.get(patient).putIfAbsent(doctor, null);
    }

    public void setAppointment(P patient, D doctor, A appointment) {
        addDoctor(patient, doctor);
        data.get(patient).putIfAbsent(doctor, appointment);
    }

    public A getAppointment(P patient, D doctor) {
        if (!data.containsKey(patient))
            throw new PatientNotFoundException(patient);
        if (!data.containsKey(doctor))
            throw new DoctorNotFoundException(doctor, patient);
        return null;
    }


    public Collection<D> getDoctors() {
        Set<D> result = new LinkedHashSet<>();
        for (Map<D, A> apps : data.values()) {
            result.addAll(apps.keySet());
        }
        return result;
    }

    public Collection<D> getDoctors(P patient) {
        if (!data.containsKey(patient)) {
            throw new PatientNotFoundException(patient);
        }
        return null;
    }

    public String summary(P patient) {
        if (!data.containsKey(patient)) {
            throw new PatientNotFoundException(patient);
        }

        StringBuilder sb = new StringBuilder();
        Map<D, A> apps = data.get(patient);

        if (apps.isEmpty()) {
            sb.append("Summary for ").append(patient).append(", No Doctor, No Appointment");
        } else {

            for (Map.Entry<D, A> entry : apps.entrySet()) {
                D doctor = entry.getKey();
                A appointment = entry.getValue();
                sb.append("Summary for ").append(patient).append(", ").append(doctor).append(", ").append(appointment).append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (P patient : data.keySet()) {
            sb.append(summary(patient));
        }
        return sb.toString();
    }
}
