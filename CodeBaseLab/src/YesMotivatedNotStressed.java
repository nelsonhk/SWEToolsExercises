public class YesMotivatedNotStressed extends IState
{
    private Student student = Student.getInstance();

    @Override
    public void doSchoolwork()
    {
        student.setStressed(true);
        student.addNewIntelligenceLevel();
        System.out.println("You are now stressed but wiser");
        student.setState(new YesMotivatedYesStressed());
    }

    //Added code to show that student passes a test automatically here
    @Override
    public void takeTest()
    {
        student.setStressed(true);
        student.subtractMotivationLevel();
        student.setPassedATest(true);
        student.increaseCompletedTests();
        student.setIntelligenceLevel(0);
        student.setState(new YesMotivatedYesStressed());

        //check to see if the student has passed all necessary tests to graduate; if so, exit.
        if (student.getCompletedTests() == student.getSemesterCount()) {
            System.out.println("Congratulations! You graduated!");
            System.exit(0);
        }

    }
}
