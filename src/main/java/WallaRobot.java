import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WallaRobot extends BaseRobot {
    private String rootWebsiteUrl;

    public WallaRobot(String rootWebsiteUrl) {
        this.rootWebsiteUrl = rootWebsiteUrl;
    }

    public String getRootWebsiteUrl() {
        return rootWebsiteUrl;
    }

    public void setRootWebsiteUrl(String rootWebsiteUrl) {
        this.rootWebsiteUrl = rootWebsiteUrl;
    }

    public  int countInArticlesTitles(String text){
        return 1;
    }

    public  String getLongestArticleTitle(){
        return "g";
    }


    public Map<String, Integer> getWordsStatistics() {
        Map<String, Integer> wordInWalla = new HashMap<>();
        try {
            Document website = Jsoup.connect(this.rootWebsiteUrl).get();
            Elements elements = website.getElementsByClass("with-roof");
            Element mainArticle = elements.get(0);
            Element mainTitle = mainArticle.getElementsByTag("h2").get(0);
            System.out.println(mainTitle.text());
            Element linkElement = mainArticle.getElementsByTag("a").get(0);
            String link = linkElement.attr("href");
            System.out.println("The link is " + link);
            Document mainArticlePage = Jsoup.connect(link).get();
            Elements titles = mainArticlePage.getElementsByTag("p");
            String longTitle;
            int c = 0;
            for (int i = 0; i < titles.size(); i++) {
                longTitle = titles.get(i).text();
                String[] s = longTitle.split(" ");
                for (int j = 0; j < s.length; j++) {
                    System.out.println(s[j]);
                    c++;
                    //wordInMako.get(s[j]);
                    if (wordInWalla.containsKey(s[j])) {
                        wordInWalla.put(s[j], wordInWalla.get(s[j]) + 1);
                    } else
                        wordInWalla.put(s[j], 1);
                }
            }
            int x = wordInWalla.size();
            System.out.println(x + " = x ... c = " + c);
            System.out.println(wordInWalla.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordInWalla;
    }
}

