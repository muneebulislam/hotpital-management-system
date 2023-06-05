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
    @Override
    public String toString(){
        String res = "Patient{ Bed label: "+ this.bedLabel +"\nDoctors attending this patient: ";
        if (this.docs.size() == 0){
            res+="\nNo doctor";
        }
        else {
            for (Doctor doc : docs) {
                res += "\n" + doc.getName();
            }
        }

        res += " }";
        return res;
    }

    /**
     * Main method to test the patient class and all its methods
     * @param args
     */
    public static void main(String[] args){
        Patient p = new Patient("john", 123);
        System.out.println(p);
    }

}
