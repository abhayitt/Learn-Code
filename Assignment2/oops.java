// Abstraction: Define a Human abstract class with 10 properties
abstract class Human {
    private String name;
    private int age;
    private String gender;
    private String skinColor;
    private String eyeColor;
    private double height;
    private double weight;
    private String hairColor;
    private String bloodType;
    private String nationality;

    // Constructor
    public Human(String name, int age, String gender, String skinColor, String eyeColor,
                 double height, double weight, String hairColor, String bloodType, String nationality) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.skinColor = skinColor;
        this.eyeColor = eyeColor;
        this.height = height;
        this.weight = weight;
        this.hairColor = hairColor;
        this.bloodType = bloodType;
        this.nationality = nationality;
    }

    // Encapsulation: Use getters and setters to access and modify properties
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getNationality() {
        return nationality;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
    }

    // Abstract methods representing actions of the human
    public abstract void walk();

    public abstract void talk();
}

// Inheritance: Create a subclass of Human (e.g., Doctor)
class Doctor extends Human {
    private String specialization;

    public Doctor(String name, int age, String gender, String skinColor, String eyeColor,
                  double height, double weight, String hairColor, String bloodType, String nationality,
                  String specialization) {
        super(name, age, gender, skinColor, eyeColor, height, weight, hairColor, bloodType, nationality);
        this.specialization = specialization;
    }

    @Override
    public void walk() {
        System.out.println("The doctor is walking.");
    }

    @Override
    public void talk() {
        System.out.println("The doctor is talking.");
    }

    public void diagnose() {
        System.out.println("The doctor is diagnosing patients.");
    }

    // Getter for specialization
    public String getSpecialization() {
        return specialization;
    }
}

public class Main {
    public static void main(String[] args) {
        Doctor doctor = new Doctor("Dr. Smith", 40, "Male", "Fair", "Blue", 6.0, 175, "Brown", "A+", "American", "Cardiologist");
        System.out.println("Doctor's Name: " + doctor.getName());
        System.out.println("Specialization: " + doctor.getSpecialization());
        doctor.walk();
        doctor.talk();
        doctor.diagnose();
    }
}
