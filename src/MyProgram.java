//Libraries

import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 702p Hiccas Project
 * @author Cody Washington
 */
public class MyProgram
{

    public static void main(String [] args) throws Exception
    {

        /*
         * <------------------------------->
         *      Initialization Section
         * <------------------------------->
         */

        long InitTime = System.currentTimeMillis();
        int HiccaCount = 0, WallieCount = 0, BeeperCount = 0, Beeper_TotalWordLength = 0;
        double Hicca_AverageWorth, Hicca_TotalWorth = 0.0, Wallie_AverageSteps, Wallie_TotalSteps = 0.0, Beeper_AverageWordLength;
        Scanner FileReader = new Scanner(new File("src\\lang701g.txt")); // <---- TODO: Change this value when switching between CodeHS and IDE
        DecimalFormat Formatter = new DecimalFormat("###.##"); Formatter.setRoundingMode(RoundingMode.UP);
        ArrayList<Animal> Animals = new ArrayList<>();
        ArrayList<Character> Beeper_TotalCharacters = new ArrayList<>();

        //Working Common Letter Code; Outputs 'i'

        /*
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
        */

        //Testing Common Letter Code; Output List Containing: 'a, e'

        Callable<String> Beeper_CommonLetter = () -> {StringBuilder ResultBuilder = new StringBuilder(); List<Character> Results = Beeper_TotalCharacters.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getValue, Collectors
                        .mapping(Map.Entry::getKey, Collectors.toList()))).entrySet().stream().max(Map.Entry.comparingByKey()).map(Map.Entry::getValue).orElse(Collections.emptyList());
            for(int i = 0; i < Results.size(); i++){ResultBuilder.append(Results.get(i)).append((i == Results.size()-1)? (""): (", "));} return ResultBuilder.toString();};

        //Working File Estimation Lambda Code; Outputs # of Animal Objects

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


        /*
         * <------------------------------->
         *      File Parsing Section
         * <------------------------------->
         */

        while(FileReader.hasNextLine())
        {
            TimeUnit.MILLISECONDS.sleep(50);
            System.out.println("\033[H\033[2J");
            System.out.println(Beepers.class.getConstructors()[1].getParameterCount());
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
                    for(String WordPart: ((Beepers)(Animals.get(Animals.size()-1))).getExtraneousWord().split(""))
                        Beeper_TotalCharacters.add(WordPart.charAt(0));
                    Beeper_TotalWordLength += ((Beepers)Animals.get(Animals.size()-1)).getExtraneousWord().length(); BeeperCount++; break;

                //End of File Reached
                case 99:
                    break;
            }

            //Print Animals
            for(Animal animal: Animals)
                System.out.println(new StringBuilder(animal.toString()).append("\n"));
        }

        /*
         * <------------------------------->
         *        Calculation Section
         * <------------------------------->
         */

        Hicca_AverageWorth =  (Hicca_TotalWorth / HiccaCount);
        Wallie_AverageSteps = (Wallie_TotalSteps / WallieCount);
        Beeper_AverageWordLength = ((double) Beeper_TotalWordLength / (double) BeeperCount);

        /*
         * <------------------------------->
         *          Output Section
         * <------------------------------->
         */

        System.out.println(new StringBuilder("Analysis Results\n----------------------\n")
                .append("\nThe average value of the Hicca fur is: $").append(Formatter.format(Hicca_AverageWorth))
                .append("\nThe average number of steps taken by the Wallies is: ").append(Wallie_AverageSteps)
                .append("\nThe average size of the Beepers words is: ").append(Beeper_AverageWordLength)
                .append("\nThe most common letter(s) in all the Beepers' words is: ").append(Beeper_CommonLetter.call())
                .append("\n\nTime Elapsed: ").append(System.currentTimeMillis() - InitTime).append("ms"));
    }
}