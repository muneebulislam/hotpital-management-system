/**
 * Models a Surgeon
 */
public class Surgeon extends Doctor{
    /**
     * Constructor for Surgeon class
     * @param name name of the surgeon
     */
    public Surgeon(String name){
        super(name);
    }
    @Override
    public String toString(){
        String res = "Surgeon{ Surgeon\nDoctor name: "+ this.getName()+ "\nPatients list:";
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
     * Tests the Surgeon class and all its methods
     * @param args
     */
    public static void main(String[] args){
        Surgeon s = new Surgeon("keen");
        s.addPatient("john", 1);
        s.addPatient("mike", 2);
        s.addPatient("kane", 3);
        System.out.println(s);
        s.removePatient(2);
        System.out.println(s);

    }
}

