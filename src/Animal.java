public class Animal
{
    private final String Name;
    private final String Word;

    public Animal()
    {
        Name = "";
        Word = "";
    }

    public Animal(String n, String w)  //order matters here because of arguments
    {
        Name = n;
        Word = w;
    }

    public String getWord()
    {
        return Word;
    }

    public String getName()
    {
        return Name;
    }
}
