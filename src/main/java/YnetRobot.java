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
        return 1;
    }

    public  String getLongestArticleTitle() {

        return "f";
    }

}
