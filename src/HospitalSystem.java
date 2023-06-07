import javax.print.Doc;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Models the Hospital System
 */
public class HospitalSystem {
    private Ward ward; /** ward attribute */
    private HashMap<Integer,Patient>patients; /** key value map of the Patients */
    private HashMap<String, Doctor> doctors; /** key value map of the doctors */
    private Scanner scanner;

    public HospitalSystem(String wardName, int firstBedLabel, int LastBedLabel) {
       ward = new Ward(wardName,firstBedLabel,firstBedLabel);
       patients = new HashMap<>();
       doctors = new HashMap<>();
       scanner = new Scanner(System.in);
   }
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
           System.out.println("Please enter a number: ");
            healthNum = Integer.parseInt(scanner.nextLine());
       }

       Patient p = new Patient(name, healthNum);
       patients.put(healthNum,p);
   }

    public void addDoctor(){
        System.out.println("Enter Doctor's name: ");
        String name = scanner.nextLine();
        System.out.println("Enter patient's health number: ");

        Doctor d = new Doctor(name);
        doctors.put(name,d);
    }
   public void run(){
       String message = "Please Select an option (1-8)" +
               "1. quit\n" +
               "2. add a new patient to the system\n" +
               "3. add a new doctor to the system\n" +
               "4. assign a doctor to a patient\n" +
               "5. display the empty beds of the ward\n" +
               "6. assign a patient a bed\n" +
               "7. release a patient\n" +
               "8. drop doctor-patient association\n";
       int option = -1;
       while (option!=1){
           System.out.println(message);
           System.out.println("\n\nPlease Enter only an integer number (1-8)");
           try{
               option = Integer.parseInt(scanner.nextLine());
           } catch (Exception e) {
               System.out.println("Error: Only integer value allowed!.");
           }
           switch (option){
               case 1:
                   scanner.close();
                   break;
               case 2: addPatient();
                        break;
               case 3: addDoctor();
                   break;
               default:
                   System.out.println("Please enter a valid number 1-8");

           }
       }
   }

   public static void main(String[] args){
       HospitalSystem hospital = new HospitalSystem("Ward1",10,15);
       hospital.run();
       System.out.println(hospital.patients);
       System.out.println(hospital.doctors);
//       System.out.println(hospital.scanner);

   }
}
