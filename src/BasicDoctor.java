/**
 * Models the doctor
 */
public class BasicDoctor {
    private String name;

    public BasicDoctor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "BasicDoctor{" +
                "name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args){
        BasicDoctor doc = new BasicDoctor("Rachel");
        System.out.println(doc);

    }
}
