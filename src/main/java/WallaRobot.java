import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WallaRobot extends BaseRobot {
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
        int count = 0;
        String subTitle;
        try {
            Document website = Jsoup.connect(this.rootWebsiteUrl).get();
            Elements element = website.getElementsByClass("main-taste");
            Elements linkElement = element.get(0).getElementsByTag("a");
            for (Element value : linkElement) {
                String link = value.attr("href");
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
            Element linkElement1 = mainArticle.getElementsByTag("a").get(0);
            String link = linkElement1.attr("href");
            Document mainArticlePage = Jsoup.connect(link).get();
            Elements titles = mainArticlePage.getElementsByClass("subtitle");
            subTitle = titles.text();
            try {
                if (subTitle.contains(text))
                    count++;
                Element title = mainArticlePage.getElementsByClass("cover-story-title").get(0);
                subTitle = title.text();
                if (subTitle.contains(text))
                    count++;
            }catch (IndexOutOfBoundsException ignored){}
    } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    public String getLongestArticleTitle() {
        int tempLongest = 0;
        String titleArticleLongest = null;
        int count;
        try {
            Document website = Jsoup.connect(this.rootWebsiteUrl).get();
            Elements element = website.getElementsByClass("main-taste");
            Elements linkElement = element.get(0).getElementsByTag("a");
            for (Element value : linkElement) {
                String link = value.attr("href");
                Document web = Jsoup.connect(link).get();
                Elements e = web.getElementsByClass("item-main-content");
                String[] s = e.text().split(" ");
                count = s.length;
                if (count > tempLongest) {
                    tempLongest = count;
                    titleArticleLongest = value.text();
                }

            }
            Elements elements = website.getElementsByClass("with-roof");
            Element mainArticle = elements.get(0);
            Element linkElement1 = mainArticle.getElementsByTag("a").get(0);
            String link = linkElement1.attr("href");
            Document mainArticlePage = Jsoup.connect(link).get();
            Elements titles = mainArticlePage.getElementsByTag("p");

            try {
                String longTitle =null;

                for (int i = -1; i < titles.size(); i++) {// I started from minus 1 so that for is in minus 1 added the main title of the main article to the map
                    if (i == -1) {
                        Element title = mainArticlePage.getElementsByClass("cover-story-title").get(0);
                        longTitle = title.text();

                    } else
                        longTitle = titles.get(i).text();
                }


                String[] s = longTitle.split(" ");
                count = +s.length;
                if (count > tempLongest) {
                    titleArticleLongest = longTitle;
                }

            }catch (IndexOutOfBoundsException ignored){}
        } catch (IOException ignored) {
        }

        return titleArticleLongest;
    }


    public Map<String, Integer> getWordsStatistics() {
        Map<String, Integer> wordInWalla = new HashMap<>();
        try {
            Document website = Jsoup.connect(this.rootWebsiteUrl).get();
            Elements element = website.getElementsByClass("main-taste");
            Elements linkElement = element.get(0).getElementsByTag("a");
            for (Element item : linkElement) {
                String link = item.attr("href");
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
            Element linkElement1 = mainArticle.getElementsByTag("a").get(0);
            String link = linkElement1.attr("href");
            Document mainArticlePage = Jsoup.connect(link).get();
            Elements titles = mainArticlePage.getElementsByTag("p");
            try{
            String longTitle;
            for (int i = -1; i < titles.size(); i++) {// I started from minus 1 so that for is in minus 1 added the main title of the main article to the map
                if (i == -1) {
                    Element title = mainArticlePage.getElementsByClass("cover-story-title").get(0);
                    longTitle = title.text();
                } else
                    longTitle = titles.get(i).text();
                String[] s = longTitle.split(" ");
                for (String value : s) {
                    if (wordInWalla.containsKey(value)) {
                        wordInWalla.put(value, wordInWalla.get(value) + 1);
                    } else
                        wordInWalla.put(value, 1);
                }
            }
            }catch (IndexOutOfBoundsException ignored){}
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordInWalla;
    }
}

