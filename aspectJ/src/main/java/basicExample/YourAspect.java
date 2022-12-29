package basicExample;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class YourAspect {


    /**
    * Defines a pointcut that we can use in the @Before,@After, @AfterThrowing, @AfterReturning,@Around specifications
    * The pointcut will look for the @basicExample.YourAnnotation
    */
    @Pointcut("@annotation(basicExample.YourAnnotation)")
    public void annotationPointCutDefinition(){
    }

    /**
    * Defines a pointcut that we can use in the @Before,@After, @AfterThrowing, @AfterReturning,@Around specifications
    * The pointcut is a catch-all pointcut with the scope of execution
    */
    @Pointcut("execution(* *(..))")
    public void atExecution(){}

    /**
    * Defines a pointcut where the @basicExample.YourAnnotation exists
    * and combines that with a catch-all pointcut with the scope of execution
    */
    @Around("@annotation(basicExample.YourAnnotation) && execution(* *(..))")
    /**
     * @param joinPoint= the reference of the call to the method.
     * The difference between ProceedingJointPoint and JointPoint is that a JointPoint can't be continued (proceeded)
     * A ProceedingJointPoint can be continued (proceeded) and is needed for an Around advice
     */
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        //Default Object that we can use to return to the consumer
        Object returnObject = null;
        try {
            System.out.println("basicExample.YourAspect's aroundAdvice's body is now executed Before yourMethodAround is called.");
            //We choose to continue the call to the method in question
            returnObject = joinPoint.proceed();
            //If no exception is thrown we should land here and we can modify the returnObject, if we want to.
        } catch (Throwable throwable) {
            //Here we can catch and modify any exceptions that are called
            //We could potentially not throw the exception to the caller and instead return "null" or a default object.
            throw throwable;
        }
        finally {
            //If we want to be sure that some of our code is executed even if we get an exception
            System.out.println("basicExample.YourAspect's aroundAdvice's body is now executed After yourMethodAround is called.");
        }
        return returnObject;
    }

    @After("annotationPointCutDefinition() && atExecution()")
    /**
     * @param pointcut = the reference of the call to the method
     *                 Prints new lines after each method thats executed
     */
    public void printNewLine(JoinPoint pointcut){
        System.out.print("\n\r");
    }
}
