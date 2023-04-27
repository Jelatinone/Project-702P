/**
 * Hicca Animal Class
 */
public class Hicca extends Animal
{
    private final Double H_FurWorth;

    public Hicca()
    {
        super();
        H_FurWorth = 0.0;
    }

    public Hicca(String Name, String Word, Double Worth)
    {
        super(Name,Word);
        this.H_FurWorth  = Worth;
    }

    public Double getWorth()
    {
        return H_FurWorth;
    }

    public String toString()
    {
        return new StringBuilder("Hicca's name is:  ").append(super.getName()).
                append("\nIts fur is worth: $").append(H_FurWorth).toString();
    }
}