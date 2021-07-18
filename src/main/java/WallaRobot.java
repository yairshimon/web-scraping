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
        super(rootWebsiteUrl);
    }

    public String getRootWebsiteUrl() {
        return rootWebsiteUrl;
    }

    public void setRootWebsiteUrl(String rootWebsiteUrl) {
        this.rootWebsiteUrl = rootWebsiteUrl;
    }

    public int countInArticlesTitles(String text) {
        int tempLongest = 0;
        String titleArticleLongest = null;
        int count = 0;
        String subTitle;
        try {
            Document website = Jsoup.connect("https://www.walla.co.il/").get();
            Elements element = website.getElementsByClass("main-taste");
            Elements linkElement = element.get(0).getElementsByTag("a");
            for (int i = 0; i < linkElement.size(); i++) {
                String link = linkElement.get(i).attr("href");
                Document web = Jsoup.connect(link).get();
                Elements e = web.getElementsByClass("item-main-content");
                Element titles = e.get(0).getElementsByTag("p").get(0);
                subTitle = titles.text();
                if (subTitle.contains(text))
                    count++;
                titles = e.get(0).getElementsByTag("h1").get(0);
                subTitle = titles.text();
                if (subTitle.contains(text))
                    count++;
            }

            Elements elements = website.getElementsByClass("with-roof");
            Element mainArticle = elements.get(0);
            Element mainTitle = mainArticle.getElementsByTag("h2").get(0);
            Element linkElement1 = mainArticle.getElementsByTag("a").get(0);
            String link = linkElement1.attr("href");
            Document mainArticlePage = Jsoup.connect(link).get();
            Elements titles = mainArticlePage.getElementsByClass("subtitle");
            subTitle = titles.text();
            if (subTitle.contains(text))
                count++;
            Element title = mainArticlePage.getElementsByClass("cover-story-title").get(0);
            subTitle = title.text();
            if (subTitle.contains(text))
                count++;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    public String getLongestArticleTitle() {
        String longTitle1 = null;
        int tempLongest = 0;
        String titleArticleLongest = null;
        int count = 0;
        try {
            Document website = Jsoup.connect("https://www.walla.co.il/").get();
            Elements element = website.getElementsByClass("main-taste");
            Elements linkElement = element.get(0).getElementsByTag("a");
            for (int i = 0; i < linkElement.size(); i++) {
                String link = linkElement.get(i).attr("href");
                Document web = Jsoup.connect(link).get();
                Elements e = web.getElementsByClass("item-main-content");
                String[] s = e.text().split(" ");
                count = s.length;
                if (count > tempLongest) {
                    tempLongest = count;
                    count = 0;
                    titleArticleLongest = linkElement.get(i).text();
                }

            }
            Elements elements = website.getElementsByClass("with-roof");
            Element mainArticle = elements.get(0);
            Element mainTitle = mainArticle.getElementsByTag("h2").get(0);
            Element linkElement1 = mainArticle.getElementsByTag("a").get(0);
            String link = linkElement1.attr("href");
            Document mainArticlePage = Jsoup.connect(link).get();
            Elements titles = mainArticlePage.getElementsByTag("p");
            String longTitle;
            for (int i = -1; i < titles.size(); i++) {// I started from minus 1 so that for is in minus 1 added the main title of the main article to the map
                if (i == -1) {
                    Element title = mainArticlePage.getElementsByClass("cover-story-title").get(0);
                    longTitle = title.text();

                } else
                    longTitle = titles.get(i).text();
                String[] s = longTitle.split(" ");
                count =+ s.length;
                if (count > tempLongest) {
                    tempLongest = count;
                    titleArticleLongest = longTitle;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return titleArticleLongest;
    }


    public Map<String, Integer> getWordsStatistics() {
        Map<String, Integer> wordInWalla = new HashMap<>();
        try {
            Document website = Jsoup.connect("https://www.walla.co.il/").get();
            Elements element = website.getElementsByClass("main-taste");
            Elements linkElement = element.get(0).getElementsByTag("a");
            for (int i = 0; i < linkElement.size(); i++) {
                String link = linkElement.get(i).attr("href");
                Document web = Jsoup.connect(link).get();
                Elements e = web.getElementsByClass("item-main-content");
                String[] s = e.text().split(" ");
                for (String value : s) {
                    if (wordInWalla.containsKey(value)) {
                        int y = wordInWalla.get(value) + 1;
                        wordInWalla.put(value, y);
                    } else {
                        wordInWalla.put(value, 1);
                    }
                }
            }
            Elements elements = website.getElementsByClass("with-roof");
            Element mainArticle = elements.get(0);
            Element mainTitle = mainArticle.getElementsByTag("h2").get(0);
            Element linkElement1 = mainArticle.getElementsByTag("a").get(0);
            String link = linkElement1.attr("href");
            Document mainArticlePage = Jsoup.connect(link).get();
            Elements titles = mainArticlePage.getElementsByTag("p");
            String longTitle;
            for (int i = -1; i < titles.size(); i++) {// I started from minus 1 so that for is in minus 1 added the main title of the main article to the map
                if (i == -1) {
                    Element title = mainArticlePage.getElementsByClass("cover-story-title").get(0);
                    longTitle = title.text();
                } else
                    longTitle = titles.get(i).text();
                String[] s = longTitle.split(" ");
                for (int j = 0; j < s.length; j++) {
                    if (wordInWalla.containsKey(s[j])) {
                        wordInWalla.put(s[j], wordInWalla.get(s[j]) + 1);
                    } else
                        wordInWalla.put(s[j], 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordInWalla;
    }
}

