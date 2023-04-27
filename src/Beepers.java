public class Beepers extends Animal
{
    private final String B_ExtraneousWord;

    public Beepers()
    {
        super();
        B_ExtraneousWord = new String();
    }

    public Beepers(String Name, String Word, String ExtraWord)
    {
        super(Name,Word);
        this.B_ExtraneousWord = ExtraWord;
    }

    public String toString()
    {
        return new StringBuilder("Beeper's name is:  ").append(super.getName()).
                append("\nBeepers word is: ").append(B_ExtraneousWord).toString();
    }
}
