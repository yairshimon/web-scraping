import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class YnetRobot extends BaseRobot {
    private String rootWebsiteUrl;

    public YnetRobot(String rootWebsiteUrl) {
        this.rootWebsiteUrl = rootWebsiteUrl;
    }

    public String getRootWebsiteUrl() {
        return rootWebsiteUrl;
    }

    public void setRootWebsiteUrl(String rootWebsiteUrl) {
        this.rootWebsiteUrl = rootWebsiteUrl;
    }

    public  Map<String, Integer> getWordsStatistics(){
        Map<String, Integer> wordInYnet = new HashMap<>();
        return wordInYnet;
    }

    public  int countInArticlesTitles(String text){
        int count = 0;

        try {
            Document website = Jsoup.connect("https://www.ynet.co.il/home/0,7340,L-8,00.html").get();
            Elements titles = website.getElementsByClass("slotTitle");//slotSubTitle
            Document web;
            for (int o = 0; o < titles.size(); o++) {
                try {
                    Element g = titles.get(o).getElementsByTag("a").get(0);
                    String link = g.attr("href");
                    web = Jsoup.connect(link).get();
                    Element titles1 = web.getElementsByClass("subTitle").get(0);
                    String subTitle = titles1.text();
                    if (subTitle.contains(text)) {
                        count++;
                    }
                    titles1 = web.getElementsByClass("mainTitleWrapper").get(0);
                    String mainTitle = titles1.text();
                    if (mainTitle.contains(text)) {
                        count++;
                    }
                } catch (Exception ignored) {

                }
            }

        }catch (Exception ignored){
        }


        return count;
    }

    public  String getLongestArticleTitle() {

        return "f";
    }

}
