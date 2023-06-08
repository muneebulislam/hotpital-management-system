import javax.print.Doc;
import java.util.LinkedList;

/**
 * Models a patient
 */
public class Patient extends Person{
    private int bedLabel ; /** Bed label for the patient */
    private LinkedList<Doctor> docs; /** A liked list to store the doctors attending the patient */
    /**
     * Constructor for Patient class.
     * @param name String: name of the patient
     * @param healthNumber int: health number of the patient
     */
    public Patient(String name, int healthNumber){
        super(name, healthNumber);
        docs = new LinkedList<>();
        bedLabel = -1;
    }
    /**
     * Adds a patient in the doctors record.
     * @param name String: name of the doctor
     */
    public void addDoctor(String name){
        Doctor d = new Doctor(name);
        this.docs.add(d);
    }

    /**
     * Removes a doctor from the patient's record
     * @param name: name of the attending doctor
     */
    public void removeDoctor(String name){
        for (int i = 0; i<docs.size(); i ++){
            if (docs.get(i).getName() == name){
                docs.remove(i);
            }
        }
    }
    @Override
    public String toString(){
        String res = "Patient{ Bed label: "+ this.bedLabel + "\nPatient's Name: "+this.getName()
                +"\nDoctors attending this patient: ";
        int count = 1;
        if (this.docs.size() == 0){
            res+="\nNo doctor";
        }
        else {
            for (Doctor doc : docs) {
                res += "\n" +count+"- "+ doc.getName();
                count ++;
            }
        }

        res += "\n}";
        return res;
    }

    /**
     * Main method to test the patient class and all its methods
     * @param args
     */
    public static void main(String[] args){
        Patient p = new Patient("john", 123);
        System.out.println(p);
        p.addDoctor("Ryan");
        p.addDoctor("John");
        p.addDoctor("mike");
        System.out.println(p);
        p.removeDoctor("John");
        System.out.println(p);

    }

}
