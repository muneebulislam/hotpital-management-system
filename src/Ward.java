import java.util.Arrays;

/**
 * Models a ward in the hospital
 */
public class Ward {
    private String wardName; /** Name of the ward */
    private int firstBedLabel; /**Bed label of the first bed in the ward */
    private Person[] beds; /** an array of Person */

    public Ward(String wardName) {
        this.wardName = wardName;
    }

    /**
     * Creates an instance of the ward creating the wards array of the size that depends upon the parameters.
     * @param wardName string Name of the ward
     * @param firstBedLabel int label of the first bed in the ward
     * @param lastBedLabel int label of the last bed in the ward
     */
    public Ward(String wardName, int firstBedLabel, int lastBedLabel) {
        this.wardName = wardName;
        this.firstBedLabel = firstBedLabel;
        int wardSize = lastBedLabel - firstBedLabel + 1;
        this.beds = new Person[wardSize];

    }

    public String getWardName() {
        return wardName;
    }

    public int getFirstBedLabel() {
        return firstBedLabel;
    }

    public Person[] getBeds() {
        return beds;
    }

    /**
     * This method accepts the bed label of the wards as a parameter and returns the
     * index of the beds array
     * @param bedLabel int Label of the bed in the ward
     * @return int corresponding index of the beds array OR -1 if the bed label does not exist in the ward
     */
    public int bedLabelToArrayIndex(int bedLabel) {
      int idx;
      try {
          if (bedLabel >= this.beds.length + this.firstBedLabel || bedLabel < this.firstBedLabel) {
              throw new ArrayIndexOutOfBoundsException("bed label: "+bedLabel+" not found in this ward");
          } else {
              idx = bedLabel - this.firstBedLabel;
              return idx;
          }
      } catch (Exception e){
          System.out.println(e.getMessage());
          return -1;
      }

    }

    /**
     * Returns the bed label in the ward corresponding to given index of the beds array
     * @param arrayIndex int index of the beds array
     * @return int: bed label corresponding to the given index
     */
    public int indexToBedLabel(int arrayIndex){
        int bedLabel;
        try{
            if (arrayIndex < 0 || arrayIndex >=this.beds.length){
                throw new ArrayIndexOutOfBoundsException("Given index: "+arrayIndex+" is out of the bounds");
            }
            else {
                bedLabel = this.firstBedLabel + arrayIndex;
                return bedLabel;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            return -1;

        }
    }

    public boolean isOccupied(int bedLabel){
        int idx;
        try {
             idx = this.bedLabelToArrayIndex(bedLabel);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        if (idx == -1) return false;
        return this.beds[idx]!= null;
    }
    public Person getPerson(int bedLabel){
        Person person;
        try{
           if (isOccupied(bedLabel)){
               person = this.beds[this.bedLabelToArrayIndex(bedLabel)];
               return person;
           }
           else person = null;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return person;
    }
    public void assignPersonToBed(Person p, int bedLabel){
        int idx=-1;
        try {
            idx = bedLabelToArrayIndex(bedLabel);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if(idx !=- 1){
            this.beds[idx] = p;
        }
    }

    @Override
    public String toString() {
        String res= "Ward{" + "wardName='" + wardName ;
        for(int i=this.firstBedLabel; i<this.firstBedLabel+this.beds.length; i++){
            if (isOccupied(i)){
                res += "\n, { bed label: "+ i+ "  , Occupant Name: "+ getPerson(i).getName()+ " } ";

            }
            else {
                res += "\n, { bed label: "+ i+ "  Occupant Name: Not Occupied } ";
            }
        }

      return res+"}";
    }

    /**
     * Main method to test the class and its methods
     * @param args
     */
    public static void main(String[] args){
        Ward w = new Ward("w1", 20, 30);
        System.out.println(w);
        System.out.println(w.bedLabelToArrayIndex(11));
        System.out.println(w.bedLabelToArrayIndex(19));
        System.out.println(w.bedLabelToArrayIndex(31));
        System.out.println("The array index of the bed label 21: "+w.bedLabelToArrayIndex(21));
        System.out.println(w.indexToBedLabel(11));
        System.out.println(w.indexToBedLabel(12));
        System.out.println("The  bed label for array index 2 : "+w.indexToBedLabel(2));
        System.out.println("The  bed label for array index 10 : "+w.indexToBedLabel(10));
        System.out.println("Ward name is: "+w.getWardName());
        System.out.println("bed label 10 is occupied? : "+w.isOccupied(13));
        Person p = new Person("john", 32);
        System.out.println("person p : "+p);
        w.assignPersonToBed(p,22);
        System.out.println("Ward after assigning bed 22 : "+w);
        w.assignPersonToBed(p,32);

    }
}
