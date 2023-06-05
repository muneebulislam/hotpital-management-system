/**
 * Models a person
 */
public class Person {
    private String name; //Name of the person
    private int healthNumber; //health number of the person

    /**
     *
     * @param name Name of the person
     * @param healthNumber Health number of the person
     */
    public Person(String name, int healthNumber) {
        this.healthNumber = healthNumber;
        this.name = name;
    }

    public int getHealthNumber() {
        return this.healthNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", healthNumber=" + healthNumber +
                '}';
    }

    public static void main(String[] args){
        Person newPerson = new Person("john", 123);
        newPerson.setName("kohl");
        System.out.println(newPerson);
    }
}
