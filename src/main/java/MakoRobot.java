import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.HashMap;
import java.util.Map;
public class MakoRobot extends BaseRobot {
private String rootWebsiteUrl;

public MakoRobot(String rootWebsiteUrl) {
    super(rootWebsiteUrl);
        }

public String getRootWebsiteUrl() {
        return rootWebsiteUrl;
        }

public void setRootWebsiteUrl(String rootWebsiteUrl) {
        this.rootWebsiteUrl = rootWebsiteUrl;
        }

public  int countInArticlesTitles(String text) {
    int j = 0;
    int count = 0;
    Document web;
    String mainTitle;
    String subTitle;
    Element link;
    String li;
    try {
        Document website = Jsoup.connect("https://www.mako.co.il/").get();
        Elements titles = website.getElementsByClass("headLine");
        Elements titles1 = titles.parents();
        Elements titles2;
        while (j < 34) { //Goes through the 5 main report on the site
            link = titles1.get(j).getElementsByTag("a").get(0);
            li = link.attr("href");
            if (j == 0) {
                j = 16;
            }
            j = j + 4;
            if (li.charAt(0) == 'h') {
                web = Jsoup.connect(li).get();
            } else {
                web = Jsoup.connect("https://www.mako.co.il/" + li).get();
            }
            titles2 = web.getElementsByTag("h1");
            mainTitle = titles2.text();
            if (mainTitle.contains(text)) {
                count++;
            }
            titles2 = web.getElementsByTag("h2");
            subTitle = titles2.text();
            if (subTitle.contains(text)) {
                count++;
            }
        }
        titles1 = website.getElementsByClass("element");
        for (int i = 9; i < titles1.size(); i++) { //Goes over the rest of the site reports
            link = titles1.get(i).getElementsByTag("a").get(0);
            String linked = link.attr("href");
            if (linked.charAt(0) == 'h')
                web = Jsoup.connect(linked).get();
            else
                web = Jsoup.connect("https://www.mako.co.il/" + linked).get();
            titles2 = web.getElementsByTag("h1");
            mainTitle = titles2.text();
            if (mainTitle.contains(text)) {
                count++;
            }
            titles2 = web.getElementsByTag("h2");
            subTitle = titles2.text();
            if (subTitle.contains(text)) {
                count++;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return count;
}

    public  String getLongestArticleTitle(){
        int tempLongest = -1;
        String titleArticleLongest = null;
        int c = 0;
        int q = 0;
        Map<String, Integer> wordInMako1 = new HashMap<>();
        Document web;
        try {
            Document website = Jsoup.connect(this.rootWebsiteUrl).get();
            Elements titles = website.getElementsByClass("headLine");
            Elements titles6 = titles.parents();
            Elements titles5;
            Element T;
            String li;
            while (q < 34) {
                T = titles6.get(q).getElementsByTag("a").get(0);
                li = T.attr("href");
                if (q == 0) {
                    q = 16;
                }
                q = q + 4;
                if (li.charAt(0) == 'h')
                    web = Jsoup.connect(li).get();
                else
                    web = Jsoup.connect(this.rootWebsiteUrl + li).get();
                titles5 = web.getElementsByTag("p");
                for (Element element : titles5) {
                    String[] s = element.text().split(" ");
                    for (String value : s) {
                        c++;
                        if (wordInMako1.containsKey(value)) {
                            int y = wordInMako1.get(value) + 1;
                            wordInMako1.put(value, y);
                        } else {
                            wordInMako1.put(value, 1);
                        }
                    }
                }
                if(c > tempLongest){
                    tempLongest = c;
                    titleArticleLongest = T.text();
                    c = 0;
                }
            }
            Element link;
            Elements titles1 = website.getElementsByClass("element");
            for (int o = 9; o < titles1.size(); o++) {
                link = titles1.get(o).getElementsByTag("a").get(0);
                String linked = link.attr("href");
                if (linked.charAt(0) == 'h')
                    web = Jsoup.connect(linked).get();
                else
                    web = Jsoup.connect(this.rootWebsiteUrl + linked).get();
                titles5 = web.getElementsByTag("p");
                for (Element element : titles5) {
                    String[] s = element.text().split(" ");
                    for (String value : s) {
                        c++;
                        if (wordInMako1.containsKey(value)) {
                            int y = wordInMako1.get(value) + 1;
                            wordInMako1.put(value, y);
                        } else {
                            wordInMako1.put(value, 1);
                        }
                    }
                }
                if(c > tempLongest){
                    tempLongest = c;
                    titleArticleLongest = link.text();
                    c = 0;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return titleArticleLongest;
    }

    public Map<String, Integer> getWordsStatistics() {
        int q = 0;
        Map<String, Integer> wordInMako1 = new HashMap<>();
        Document web;
        try {
            Document website = Jsoup.connect(this.rootWebsiteUrl).get();
            Elements titles = website.getElementsByClass("headLine");
            Elements titles6 = titles.parents();
            Elements titles5;
            Element T;
            String li;
            while (q < 34) {
                T = titles6.get(q).getElementsByTag("a").get(0);
                li = T.attr("href");
                if (q == 0) {
                    q = 16;
                }
                q = q + 4;
                if (li.charAt(0) == 'h')
                    web = Jsoup.connect(li).get();
                else
                    web = Jsoup.connect(this.rootWebsiteUrl + li).get();
                titles5 = web.getElementsByTag("p");
                for (Element element : titles5) {
                    String[] s = element.text().split(" ");
                    for (String value : s) {
                        if (wordInMako1.containsKey(value)) {
                            int y = wordInMako1.get(value) + 1;
                            wordInMako1.put(value, y);
                        } else {
                            wordInMako1.put(value, 1);
                        }
                    }
                }
            }
            Element link;
            Elements titles1 = website.getElementsByClass("element");
            for (int o = 9; o < titles1.size(); o++) {
                link = titles1.get(o).getElementsByTag("a").get(0);
                String linked = link.attr("href");
                if (linked.charAt(0) == 'h')
                    web = Jsoup.connect(linked).get();
                else
                    web = Jsoup.connect(this.rootWebsiteUrl + linked).get();
                titles5 = web.getElementsByTag("p");
                for (Element element : titles5) {
                    String[] s = element.text().split(" ");
                    for (String value : s) {
                        if (wordInMako1.containsKey(value)) {
                            int y = wordInMako1.get(value) + 1;
                            wordInMako1.put(value, y);
                        } else {
                            wordInMako1.put(value, 1);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return wordInMako1;
    }
}

