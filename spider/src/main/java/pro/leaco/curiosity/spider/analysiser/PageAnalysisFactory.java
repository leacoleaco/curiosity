package pro.leaco.curiosity.spider.analysiser;

import pro.leaco.curiosity.spider.analysiser.impl.BaiduPageAnalysiser;
import pro.leaco.curiosity.util.ListUtil;
import us.codecraft.webmagic.Page;

import java.util.List;

public class PageAnalysisFactory {

    List<PageAnalysiser> analysisers = ListUtil.asList(
            new BaiduPageAnalysiser()
    );

    public PageAnalysiser findBestAnalysiser(Page page) {

        for (PageAnalysiser analysiser : analysisers) {
            if (analysiser.support(page)) {
                return analysiser;
            }
        }
        return null;
    }


}
