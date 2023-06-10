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
       System.out.println("Getting information about the patient.....");
       boolean patientNotFound = true;
       String patientName = "";
       boolean patientHealthNumpNotFound = true;
       int patientHealthNumber = -1;
       //Get patient's name
       do {
           System.out.println("Please enter the name of the patient  : ");
           try {
               patientName = scanner.nextLine();
               if (patientName.equals("")){
                   throw new RuntimeException("Your entered an empty string! Please try again.");
               }
               else {
                   patientNotFound = false;
               }
           }
             catch (RuntimeException e){
               System.out.println(e.getMessage());
           }
       } while (patientNotFound);

   //Get patient's health number

       do {
           System.out.println("Please enter the health number of the patient: ");
           try {
               patientHealthNumber = Integer.parseInt(scanner.nextLine());
               if(patientHealthNumber!=-1){
                   patientHealthNumpNotFound = false;
               }
           } catch (NumberFormatException e) {
               System.out.println("Please enter a valid health number: ");
           }
       } while (patientHealthNumpNotFound);

       if(patientHealthNumber!= -1 && !patientName.equals("")){
       Patient p = new Patient(patientName, patientHealthNumber);
       patients.put(patientHealthNumber,p);
       }
   }

    /**
     * Adds a doctor to the hospital system.
     * @precond: User has to enter the
     */
    public void addDoctor(){
        System.out.println("Getting information about the doctor to add in the system.... ");

        boolean doctorNotFound = true;
        String doctorName = "";
        do {
            System.out.println("Please enter the name of the doctor  : ");
            try {
                doctorName = scanner.nextLine();
                if (doctorName.equals("")){
                    throw new RuntimeException("Your entered an empty string! Please try again.");
                }
                else {
                    doctorNotFound = false;
                }
            }
            catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        } while (doctorNotFound);


        Doctor d = new Doctor(doctorName);
        doctors.put(doctorName,d);

    }
    public int getValidPatientHealthNumber(){
        int patientHealthNumber = -1;
        boolean patientNotFound = true;
        do {
            System.out.println("Please enter the health number of the patient: ");
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

    public String getValidDoctorName(){
        boolean doctorNotFound = true;
        String docName = "";
        do {
            System.out.println("Please enter the name of the doctor  : ");
            try {
                 docName = scanner.nextLine();
                if (docName.equals("")){
                    throw new RuntimeException("Your entered an empty string! Please try again.");
                }
                else if (doctors.containsKey(docName)){
                    doctorNotFound = false;
                }
                else {
                    throw new NoSuchElementException("No doctor with the given name found in the system, Please try again!");
                }
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        } while (doctorNotFound);

        return docName;
    }

    public int getValidBedLabel(){
        System.out.println("Getting information about the bed label .... ");
        boolean notFound = true;
        int bedLabel = -1;
        do {
            try {
                System.out.println("Please enter a valid bed label for the patient: ");
                bedLabel = Integer.parseInt(scanner.nextLine());
                if(ward.bedLabelToArrayIndex(bedLabel)!=-1){
                    notFound = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter only an int bed label : ");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while(notFound);

            return bedLabel;

    }

    /**
     * Assigns a doctor to a patient.
     * @precond: user has to enter all the information about the doctor as well as the
     * patient. If the patient and the doctor are already in the hospital system, only
     * then the doctor can be assigned to the patient.
     */
    public void assignDoctor(){
        int healthNumber = getValidPatientHealthNumber();
        String docName = getValidDoctorName();
        if(healthNumber!=-1 ){
            // Add the doctor in patients record.
            patients.get(healthNumber).addDoctor(doctors.get(docName).getName());
            doctors.get(docName).addPatient(patients.get(healthNumber).getName(), patients.get(healthNumber).getHealthNumber());
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
        int healthNum = getValidPatientHealthNumber();
        Patient p = patients.get(healthNum);
        int bedLabel = getValidBedLabel();
        if(healthNum != -1 && bedLabel != -1){
            p.setBedLabel(bedLabel);
            ward.assignPatientToBed(p,bedLabel);
        }

    }

    /**
     * Release a patient from a bed in the ward
     */
    public void releasePatient(){
        int healthNum = getValidPatientHealthNumber();
        int bedLabel = getValidBedLabel();
        Patient p = patients.get(healthNum);
        if (bedLabel== p.getBedLabel()){
            ward.removePatient(bedLabel);
            p.removeBedLabel();
        }

    }

    /**
     * Removes the patient from doctors list of patients and removes the doctor from patients
     * list of attending doctors.
     */
    public void dropPatientDoctorAssociation(){
        int healthNumber = getValidPatientHealthNumber();
        String docName = getValidDoctorName();
        if (healthNumber!= -1 && !docName.equals("")){
            doctors.get(docName).removePatient(patients.get(healthNumber).getHealthNumber());
            patients.get(healthNumber).removeDoctor(doctors.get(docName).getName());
        }

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
               case 6: assignBed();
                   break;
               case 7: releasePatient();
                   break;
               case 8: dropPatientDoctorAssociation();
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
       System.out.println(hospital.ward);

   }
}
