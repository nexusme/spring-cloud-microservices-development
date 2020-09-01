public class AnimalInheritance {
    private String name;
    private int id;

    public AnimalInheritance(String myName, int myid) {
        name = myName;
        id = myid;

    }

    public void eat() {
        System.out.println(name + "is eating.");
    }

    public void sleep() {
        System.out.println(name + " is sleeping.");
    }

    public void introduction() {
        System.out.println("Hi! I am NO." + id + ", " + name + ".");

    }

    public static void main(String[] args) {
        Penguin p = new Penguin("cool man", 1);

        p.introduction();

    }


}


class Penguin extends AnimalInheritance {
    Penguin(String myName, int myid) {
        super(myName, myid);

    }
}

class Mouse extends AnimalInheritance {
    public Mouse(String myName, int myid) {
        super(myName, myid);
    }
}

