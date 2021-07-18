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
        int count = 0;
        String text = "בנט";
        int c = 0;
        int q = 0;
        Map<String, Integer> wordInMako1 = new HashMap<>();
        try {
            Document website = Jsoup.connect("https://www.ynet.co.il/home/0,7340,L-8,00.html").get();
            Elements titles = website.getElementsByClass("slotTitle");//slotSubTitle
            Document web;
            for (int o = 0; o < titles.size(); o++) {
                try {
                    Element g = titles.get(o).getElementsByTag("a").get(0);
                    String link = g.attr("href");
                    web = Jsoup.connect(link).get();
                    Elements titles2 = web.getElementsByClass("text_editor_paragraph");
                    for (Element element : titles2) {
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
                    System.out.println(o +":" +titles2.text());
                    Element titles1 = web.getElementsByClass("subTitle").get(0);
                    String[] s = titles1.text().split(" ");
                    for (String value : s) {
                        c++;
                        if (wordInMako1.containsKey(value)) {
                            int y = wordInMako1.get(value) + 1;
                            wordInMako1.put(value, y);
                        } else {
                            wordInMako1.put(value, 1);
                        }
                    }
                    titles1 = web.getElementsByClass("mainTitleWrapper").get(0);
                    String[]  S = titles1.text().split(" ");
                    for (String value : S) {
                        c++;
                        if (wordInMako1.containsKey(value)) {
                            int y = wordInMako1.get(value) + 1;
                            wordInMako1.put(value, y);
                        } else {
                            wordInMako1.put(value, 1);
                        }
                    }
                } catch (Exception ignored) {

                }
            }

        } catch (Exception ignored) {
        }
        return wordInMako1;
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
        int tempLongest = 0;
        String titleArticleLongest = null;
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
                    Elements titles2 = web.getElementsByClass("text_editor_paragraph");
                    for (Element element : titles2) {
                        String[] s = element.text().split(" ");
                        for (String value : s) {
                            count++;
                        }
                    }
                    System.out.println(o + ":" + titles2.text());
                    Element titles1 = web.getElementsByClass("subTitle").get(0);
                    String[] s = titles1.text().split(" ");
                    for (String value : s) {
                        count++;
                    }
                    titles1 = web.getElementsByClass("mainTitleWrapper").get(0);
                    String[] S = titles1.text().split(" ");
                    for (String value : S) {
                        count++;
                    }
                    if (count > tempLongest) {
                        tempLongest = count;
                        titleArticleLongest = titles1.text();
                        count = 0;
                    }
                } catch (Exception ignored) {

                }
            }

        } catch (Exception ignored) {
        }
        return titleArticleLongest;
    }
}


