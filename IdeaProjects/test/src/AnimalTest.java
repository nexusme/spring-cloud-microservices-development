class Animal {
    void eat() {
        System.out.println("animal: eat");
    }
}

class Dog extends Animal {
    void eat() {
        System.out.println("Dog: eat");
    }

    void eatTest() {
        System.out.println("Test: This. 调用自己");
        this.eat();
        System.out.println("Test: Super. 继承父类");
        super.eat();
    }
}

public class AnimalTest {
    public static void main(String[] args) {
        Animal a = new Animal();
        a.eat();
        Dog d = new Dog();
        d.eatTest();

    }
}