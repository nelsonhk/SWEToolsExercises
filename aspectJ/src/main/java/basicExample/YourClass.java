package basicExample;

public class YourClass {

    public static void main(String[] args) {
        YourClass yourClass = new YourClass();
        yourClass.yourMethodAround();
    }

    @YourAnnotation
    public void yourMethodAround(){
        System.out.println("Executing TestTarget.yourMethodAround()");
    }
}
