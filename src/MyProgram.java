import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MyProgram
{

    public static void main(String [] args) throws Exception
    {
        ArrayList<Animal> Animals = new ArrayList<>();
        ArrayList<Character> Beeper_TotalCharacters = new ArrayList<>();
        int HiccaCount = 0;
        int WallieCount = 0;
        int BeeperCount = 0;
        int Beeper_TotalWordLength = 0;
        double Hicca_AverageWorth;
        double Hicca_TotalWorth = 0.0;
        double Wallie_AverageSteps;
        double Wallie_TotalSteps = 0.0;
        double Beeper_AverageWordLength;
        DecimalFormat Formatter = new DecimalFormat("###.##"); Formatter.setRoundingMode(RoundingMode.UP);
        Callable<Character> Beeper_CommonLetter = () ->
        {
            int MostCommonCount = 0; Character MostCommon = Beeper_TotalCharacters.get(0);
            for(int i = 0; i < Beeper_TotalCharacters.size(); i++)
            {
                Character CurrentCharacter = Beeper_TotalCharacters.get(i);
                if(Beeper_TotalCharacters.size() - i > MostCommonCount)
                {
                    int CurrentCharacterCount = 1; for(int CharacterInduce = (i + 1); CharacterInduce < Beeper_TotalCharacters.size(); CharacterInduce++)
                {if(Beeper_TotalCharacters.get(CharacterInduce).equals(CurrentCharacter)) {CurrentCharacterCount++;}}
                    if(CurrentCharacterCount > MostCommonCount) {MostCommonCount = CurrentCharacterCount; MostCommon = CurrentCharacter;}
                }
            }
            return MostCommon;
        };

        Callable <Integer> ProjectedAnimalCount = () -> {
            Scanner PreFileReader = new Scanner(new File("src\\lang701g.txt"));
            int NumObjects = 0;
            while(PreFileReader.hasNextLine())
            {
                try
                {
                    Integer.parseInt(PreFileReader.nextLine());
                    PreFileReader.nextLine(); PreFileReader.nextLine(); PreFileReader.nextLine();
                    NumObjects++;
                }
                catch(Exception ignored){}
            }
            return NumObjects;
        };
        Scanner FileReader = new Scanner(new File("src\\lang701g.txt"));

        /*
         * <------------------------------->
         *      File Parsing Section
         * <------------------------------->
         */
        while(FileReader.hasNextLine())
        {
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println("\033[2J");
            System.out.println(new StringBuilder("Parsing File: ").append(((Formatter.format(((double)(HiccaCount + WallieCount + BeeperCount) / ProjectedAnimalCount.call()) * 100)))).append("%\n"));
            switch(Integer.parseInt(FileReader.nextLine()))
            {
                //Hicca
                case 1:
                    Animals.add(new Hicca(FileReader.nextLine(),FileReader.nextLine(),Double.parseDouble(FileReader.nextLine())));
                    Hicca_TotalWorth += ((Hicca)Animals.get(Animals.size()-1)).getWorth(); HiccaCount++; break;


                //Wallie
                case 2:
                    Animals.add(new Wallie(FileReader.nextLine(),FileReader.nextLine(),Double.parseDouble(FileReader.nextLine())));
                    Wallie_TotalSteps += ((Wallie)Animals.get(Animals.size()-1)).getSteps(); WallieCount++; break;

                //Beeper
                case 3:
                    Animals.add(new Beepers(FileReader.nextLine(),FileReader.nextLine(),FileReader.nextLine()));
                    for(String WordPart: Animals.get(Animals.size()-1).getWord().split(""))
                        Beeper_TotalCharacters.add(WordPart.charAt(0));
                    Beeper_TotalWordLength += (Animals.get(Animals.size()-1).getWord()).length(); BeeperCount++; break;

                //End of File Reached
                case 99:
                    break;
            }
        }

        /*
         * <------------------------------->
         *        Calculation Section
         * <------------------------------->
         */

        Hicca_AverageWorth =  (Hicca_TotalWorth / HiccaCount);
        Wallie_AverageSteps = (Wallie_TotalSteps / WallieCount);
        Beeper_AverageWordLength = (double) (Beeper_TotalWordLength / BeeperCount);

        /*
         * <------------------------------->
         *          Output Section
         * <------------------------------->
         */

        //Parsing Results
        for(Animal animal: Animals)
        {
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(new StringBuilder(animal.toString()).append("\n"));
        }

        //Calculation Results
        System.out.println("Analysis Results\n----------------------\n");
        System.out.println(new StringBuilder("The average value of the Hicca fur is: $").append(Formatter.format(Hicca_AverageWorth)));
        System.out.println(new StringBuilder("The average number of steps taken by the Wallies is: ").append(Wallie_AverageSteps));
        System.out.println(new StringBuilder("The average size of the Beepers words is: ").append(Beeper_AverageWordLength));
        System.out.println(new StringBuilder("The most common letter(s) in all the Beepers' words is: ").append(Beeper_CommonLetter.call()));
    }
}