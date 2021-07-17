import java.util.Map;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int countTextOfTitle;
        String textOfTitles;
        int textOfTitle;
        int counterPoint = 0;
        String[] words = new String[5];
        Map<String,Integer> wordsInWeb;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a site where you want to play\n" +
                "For Mako click 1\n" +
                "For Ynet pressure 2\n" +
                "For Walla click 3");
        int yourChoice = scanner.nextInt();
        switch (yourChoice){
            case 1:
                System.out.println("Please wait a few seconds for the data to load");
                MakoRobot makoRobot = new MakoRobot("https://www.mako.co.il/");
                System.out.println("The longest title on the Mako website is: \n" + makoRobot.getLongestArticleTitle());
                System.out.println("Choose the 5 words that you think appear most on the site");
                for (int i = 0; i < 5; i++){
                    words[i] = scanner.next();
                }
                System.out.println("Please wait a few seconds for the data to load");
                wordsInWeb = makoRobot.getWordsStatistics();
                for (int i = 0; i < 5; i++){
                    if (wordsInWeb.containsKey(words[i])){
                        counterPoint += wordsInWeb.get(words[i]);
                    }
                }
                System.out.println("Select text of up to 20 characters that appears in headings on the home page of the site");
                scanner.nextLine();
                textOfTitles = scanner.nextLine();
                System.out.println("How many times does the text you selected appear in the headings on the home page of the site with an accuracy of up to 2 + - you will win 250 points");
                textOfTitle = scanner.nextInt();
                countTextOfTitle = makoRobot.countInArticlesTitles(textOfTitles);
                if (countTextOfTitle == textOfTitle + 2 || countTextOfTitle == textOfTitle + 1 || countTextOfTitle == textOfTitle || countTextOfTitle - 2 == textOfTitle || countTextOfTitle - 1 == textOfTitle)
                    counterPoint += 250;
                System.out.println("You won " + counterPoint + " points;");
                System.out.println(countTextOfTitle);
                break;
            case 2:
                System.out.println("Please wait a few seconds for the data to load");
                WallaRobot wallaRobot = new WallaRobot("https://www.walla.co.il/");
                System.out.println("The longest title on the Mako website is: \n" + wallaRobot.getLongestArticleTitle());
                System.out.println("Choose the 5 words that you think appear most on the site");
                for (int i = 0; i < 5; i++){
                    words[i] = scanner.next();
                }
                System.out.println("Please wait a few seconds for the data to load");
                wordsInWeb = wallaRobot.getWordsStatistics();
                for (int i = 0; i < 5; i++){
                    if (wordsInWeb.containsKey(words[i])){
                        counterPoint += wordsInWeb.get(words[i]);
                    }
                }
                System.out.println("Select text of up to 20 characters that appears in headings on the home page of the site");
                scanner.nextLine();
                textOfTitles = scanner.nextLine();
                System.out.println("How many times does the text you selected appear in the headings on the home page of the site with an accuracy of up to 2 + - you will win 250 points");
                textOfTitle = scanner.nextInt();
                countTextOfTitle = wallaRobot.countInArticlesTitles(textOfTitles);
                if (countTextOfTitle == textOfTitle + 2 || countTextOfTitle == textOfTitle + 1 || countTextOfTitle == textOfTitle || countTextOfTitle - 2 == textOfTitle || countTextOfTitle - 1 == textOfTitle)
                    counterPoint += 250;
                System.out.println("You won " + counterPoint + " points;");
                break;
            case 3:
                System.out.println("Please wait a few seconds for the data to load");
                YnetRobot ynetRobot = new YnetRobot("https://www.ynet.co.il/home/0,7340,L-8,00.html");
                System.out.println("The longest title on the Mako website is: \n" + ynetRobot.getLongestArticleTitle());
                System.out.println("Choose the 5 words that you think appear most on the site");
                for (int i = 0; i < 5; i++){
                    words[i] = scanner.next();
                }
                System.out.println("Please wait a few seconds for the data to load");
                wordsInWeb = ynetRobot.getWordsStatistics();
                for (int i = 0; i < 5; i++){
                    if (wordsInWeb.containsKey(words[i])){
                        counterPoint += wordsInWeb.get(words[i]);
                    }
                }
                System.out.println("Select text of up to 20 characters that appears in headings on the home page of the site");
                scanner.nextLine();
                textOfTitles = scanner.nextLine();
                System.out.println("How many times does the text you selected appear in the headings on the home page of the site with an accuracy of up to 2 + - you will win 250 points");
                textOfTitle = scanner.nextInt();
                countTextOfTitle = ynetRobot.countInArticlesTitles(textOfTitles);
                if (countTextOfTitle == textOfTitle + 2 || countTextOfTitle == textOfTitle + 1 || countTextOfTitle == textOfTitle || countTextOfTitle - 2 == textOfTitle || countTextOfTitle - 1 == textOfTitle)
                    counterPoint += 250;
                System.out.println("You won " + counterPoint + " points;");
                break;
            default:
                break;
        }
        System.out.println("BY;");
    }
}
