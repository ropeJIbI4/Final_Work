package Classes;

public class Dog extends Pet{
    boolean trained = true;
    public Dog(String name, String birthday) {
        super(name, birthday);
    }
    @Override
    public String toString() {
        return "Dog{" +
                "name='" + super.getName() + '\'' +
                ", birthday='" + super.getBirthday() + '\'' + '}';
    }

    @Override
    public void voice() {
        System.out.println("Собака " + getName() + " тяфкает");
    }

    @Override
    public void eat() {
        System.out.println("Собака " + getName() + " ест");
    }

    @Override
    public void sleep() {
        System.out.println("Собака " + getName() + " спит");
    }

    @Override
    public void training() {
        this.trained = false;
        System.out.println("Собака " + getName() + " Прошел обучение");
    }

    public void specialMethod() {
        if(trained){
            System.out.println("Собака " + getName() + " надо обучать");
        }
        else
            System.out.println("Собака " + getName() + " подает лапу");
    }
}