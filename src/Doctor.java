import java.util.LinkedList;

/**
 * Models a doctor
 */
public class Doctor extends BasicDoctor{
    LinkedList<Patient> patients; /** A liked list to store the patients */
    public Doctor(String name){
        super(name);
        patients = new LinkedList<>();
    }

    /**
     * Adds a patient in the doctors record.
     * @param name String: name of the patient
     * @param healthNumber int: health number of the patient
     */
    public void addPatient(String name, int healthNumber){
        Patient p = new Patient(name, healthNumber);
        this.patients.add(p);
    }

    /**
     * Removes a patient from the doctor's record
     * @param healthNumber int: health number of the patient
     */
    public void removePatient(int healthNumber){
        for (int i = 0; i<patients.size(); i ++){
            if (patients.get(i).getHealthNumber() == healthNumber){
                patients.remove(i);
            }
        }
    }
    @Override
    public String toString(){
        String res = "Dotor{ Doctor name: "+ this.getName()+ "\nPatients list:";
        int count = 1;
        if(patients.size() ==0){
            res+= "\nNo patients.";
        } else {
            for (Patient p : patients) {
                res += "\n" + count+ "- "+p.getName();
                count++;
            }
        }
        res += "\n}";
        return res;
    }

    /**
     * Main method to test the Doctor class and its methods
     * @param args
     */
    public static void main(String[] args){
        Doctor d = new Doctor("mike");
        d.addPatient("a",1);
        d.addPatient("b",2);
        d.addPatient("c", 3);
        System.out.println(d);
        d.removePatient(2);
        System.out.println("after removing patient with health number: 2 we get");
        System.out.println(d);


    }
}
