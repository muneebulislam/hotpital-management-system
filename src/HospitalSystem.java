import javax.print.Doc;
import java.util.*;

/**
 * Models the Hospital System
 */
public class HospitalSystem {
    private Ward ward; /** ward attribute */
    private HashMap<Integer,Patient>patients; /** key value map of the Patients */
    private HashMap<String, Doctor> doctors; /** key value map of the doctors */
    private Scanner scanner;

    /**
     * Constructor for HospitalSystem class
     * @param wardName String: name of the ward
     * @param firstBedLabel int: label of the first bed in the ward
     * @param LastBedLabel int: label of the last bed in the ward
     */
    public HospitalSystem(String wardName, int firstBedLabel, int LastBedLabel) {
       ward = new Ward(wardName,firstBedLabel,LastBedLabel);
       patients = new HashMap<>();
       doctors = new HashMap<>();
       scanner = new Scanner(System.in);
   }

    /**
     * adds a patient to the hospital system.
     * @precond user has to enter the name of the patient as well as his health number
     */
   public void addPatient(){
        int healthNum;
       System.out.println("Enter patient's name: ");
       String name = scanner.nextLine();
       System.out.println("Enter patient's health number: ");
       try{
            healthNum = Integer.parseInt(scanner.nextLine());
       }
       catch (Exception e){
           System.out.println(e.getMessage());
           System.out.println("Please enter a valid health number: ");
            healthNum = Integer.parseInt(scanner.nextLine());
       }

       Patient p = new Patient(name, healthNum);
       patients.put(healthNum,p);
   }

    /**
     * Adds a doctor to the hospital system.
     * @precond: User has to enter the
     */
    public void addDoctor(){
        System.out.println("Enter Doctor's name: ");
        String name = scanner.nextLine();
        Doctor d = new Doctor(name);
        doctors.put(name,d);
    }
    public int getValidPatientHealthNumber(){
        int patientHealthNumber = -1;
        boolean patientNotFound = true;
        do {
            System.out.println("Please enter the health number of the patient you want to assign a doctor: ");
            try {
                 patientHealthNumber = Integer.parseInt(scanner.nextLine());
                if(patients.containsKey(patientHealthNumber)){
                    patientNotFound = false;
                }
                else {
                    throw new NoSuchElementException("No patient with given health number found!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid health number: ");
            } catch (NoSuchElementException e){
                System.out.println(e.getMessage());
            }
        } while (patientNotFound);
        return patientHealthNumber;
    }

    /**
     * Assigns a doctor to a patient.
     * @precond: user has to enter all the information about the doctor as well as the
     * patient. If the patient and the doctor are already in the hospital system, only
     * then the doctor can be assigned to the patient.
     */
    public void assignDoctor(){
        Patient p = null;
        Doctor d= null;
        boolean patientNotFound = true;
        boolean doctorNotFound = true;
        int healthNumber = getValidPatientHealthNumber();
        do {
                System.out.println("Please enter the name of the doctor you want to assign to this patient: ");
                try {
                    String docName = scanner.nextLine();
                    if (docName == ""){
                        throw new RuntimeException("Your entered an empty string! Please try again.");
                    }
                    else if (doctors.containsKey(docName)){
                        d = doctors.get(docName);
                        doctorNotFound = false;
                    }
                    else {
                        throw new NoSuchElementException("No doctor with the given name found in the system, Please try again!");
                    }
                } catch (NoSuchElementException e){
                    System.out.println(e.getMessage());
                } catch (RuntimeException e){
                    System.out.println(e.getMessage());
                }
            } while (doctorNotFound);

        if (healthNumber!=-1){
            p = patients.get(healthNumber);
        }
        if(p!=null && d!= null){
            // Add the doctor in patients record.
            p.addDoctor(d.getName());
            d.addPatient(p.getName(),p.getHealthNumber());
        }

    }

    /**
     * Displays empty beds in the ward
     * @return
     */
    public void displayEmptyBeds(){
        ArrayList<Integer>emptyBeds =  ward.getEmptyBeds();
        System.out.println("List of Empty beds: "+emptyBeds);
    }

    /**
     * Assigns a bed to a patient
     * @precond: User has to enter the health number of the patient.
     */
    public void assignBed(){

    }

    /**
     * A method to run the hospital system.
     */
   public void run(){
       String message = "Please Select an option (1-8)\n" +
               "1. quit\n" +
               "2. add a new patient to the system\n" +
               "3. add a new doctor to the system\n" +
               "4. assign a doctor to a patient\n" +
               "5. display the empty beds of the ward\n" +
               "6. assign a patient a bed\n" +
               "7. release a patient\n" +
               "8. drop doctor-patient association\n";
       int option = -1;
       boolean dontEnd = true;
       while (dontEnd){
           System.out.println(message);
           System.out.println("\n");
           try{
               option = Integer.parseInt(scanner.nextLine());
           } catch (Exception e) {
               System.out.println("Error: Only integer value allowed!.");
           }
           switch (option){
               case 1:
                   scanner.close();
                   dontEnd = false;
                   break;
               case 2: addPatient();
                        break;
               case 3: addDoctor();
                   break;
               case 4: assignDoctor();
                   break;
               case 5: displayEmptyBeds();
                   break;
               default:
                   System.out.println("Please enter a valid number 1-8");

           }
       }
   }

    /**
     * Main method to test the working of the hospial system.
     * @param args
     */
   public static void main(String[] args){
       HospitalSystem hospital = new HospitalSystem("Ward1",10,15);
       hospital.run();
       System.out.println(hospital.patients);
       System.out.println(hospital.doctors);
//       System.out.println(hospital.scanner);

   }
}
