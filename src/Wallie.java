public class Wallie extends Animal
{
    private final Double StepsTaken;

    public Wallie()
    {
        super();
        StepsTaken = 0.0;
    }

    public Wallie(String Name, String Word, Double Steps)
    {
        super(Name,Word);
        this.StepsTaken = Steps;
    }

    public Double getSteps()
    {
        return StepsTaken;
    }

    public String toString()
    {
        return new StringBuilder("Wallie's name is:  ").append(super.getName()).
                append("\nWallie has taken : ").append(StepsTaken).toString();
    }
}
