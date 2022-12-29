public class NotMotivatedYesStressed extends IState
{
    private Student student = Student.getInstance();

    @Override
    public void doSchoolwork()
    {
        student.addNewIntelligenceLevel();
        System.out.println("You are still stressed but wiser");
    }

    //added space to the print statement to remove typo
    @Override
    public void doHobby()
    {
        student.setStressed(false);
        System.out.println("You are no longer stressed, but your" +
                " motivation has dropped a little");
        student.setState(new NotMotivatedNotStressed());
    }

    @Override
    public void inspireSelf()
    {
        student.addNewMotivationalLevel();
        System.out.println("You are now a little more motivated");
        student.setState(new YesMotivatedYesStressed());
    }
}
