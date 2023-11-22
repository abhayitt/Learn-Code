// Abstract class representing Digestive System
abstract class DigestiveSystem {
    // Encapsulated private variable
    private String stomachContent;

    // Encapsulation: Setter method for stomachContent
    public void setStomachContent(String food) {
        this.stomachContent = food;
    }
    
    // Encapsulation: Getter method for stomachContent
    public String getStomachContent() {
        return stomachContent;
    }

    

    // Abstract method representing the digestion process
    public abstract void digest();

}

// Subclass representing Esophagus
class Esophagus extends DigestiveSystem {
    public void digest() {
        System.out.println("Food is moving through the esophagus.");
    }
}

// Subclass representing Stomach
class Stomach extends DigestiveSystem {
    private boolean isHungry;

    // Constructor to initialize properties
    public Stomach() {
        super(); // Call the constructor of the base class
        this.isHungry = true;
    }
    
    // Encapsulation: Setter method for isHungry
    public void setHungry(boolean hungry) {
        isHungry = hungry;
    }

    // Encapsulation: Getter method for isHungry
    public boolean isHungry() {
        return isHungry;
    }

    

    // Implementation of the abstract method from the base class
    public void digest() {
        if (isHungry) {
            System.out.println("Stomach is hungry. Cannot digest without food.");
        } else {
            System.out.println("Digesting food in the stomach.");
        }
    }
}

// Subclass representing Small Intestine
class SmallIntestine extends DigestiveSystem {
    public void digest() {
        System.out.println("Digesting nutrients in the small intestine.");
    }
}

// Subclass representing Large Intestine
class LargeIntestine extends DigestiveSystem {

    public void digest() {
        System.out.println("Absorbing water in the large intestine.");
    }
}

public class HumanBody {
    public static void main(String[] args) {

        DigestiveSystem esophagus = new Esophagus();
        DigestiveSystem stomach = new Stomach();
        DigestiveSystem smallIntestine = new SmallIntestine();
        DigestiveSystem largeIntestine = new LargeIntestine();

        esophagus.digest();
        stomach.digest();
        smallIntestine.digest();
        largeIntestine.digest();

        stomach.setStomachContent("Pizza");
        System.out.println("Stomach Content: " + stomach.getStomachContent());
    }
}
