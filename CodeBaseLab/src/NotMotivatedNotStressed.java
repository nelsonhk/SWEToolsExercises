public class NotMotivatedNotStressed extends IState
{
    private Student student = Student.getInstance();

    //Added code to increase student's intelligence after doing schoolwork, despite motivation levels
    @Override
    public void doSchoolwork()
    {
        student.addNewIntelligenceLevel();
        student.setStressed(true);
        System.out.println("You are now stressed but wiser");
        student.setState(new NotMotivatedYesStressed());
    }

    @Override
    public void inspireSelf()
    {
        student.addNewMotivationalLevel();
        System.out.println("You are now a little more motivated");
        student.setState(new YesMotivatedNotStressed());
    }
}
