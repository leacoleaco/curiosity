package pro.leaco.curiosity.spider.processor;

import org.apache.commons.lang3.StringUtils;
import pro.leaco.curiosity.db.g.service.GDataDto;
import pro.leaco.curiosity.spider.analysiser.PageAnalysiser;
import pro.leaco.curiosity.util.ListUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

import java.util.List;
import java.util.stream.Collectors;

public class CuriosityPageProcessor implements us.codecraft.webmagic.processor.PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(5000);

    private final PageAnalysiser pageAnalysiser;

    public CuriosityPageProcessor(PageAnalysiser pageAnalysiser) {
        this.pageAnalysiser = pageAnalysiser;
    }

    @Override
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        List<GDataDto> collect = pageAnalysiser.analysisPageDatas(page);
        page.putField("result", collect);


        // 部分三：从页面发现后续的url地址来抓取
        boolean hasNext = false;
        List<String> nextUrls = pageAnalysiser.analysisNextUrl(page);
        if (nextUrls != null) {
            page.addTargetRequests(nextUrls, 99999);
            hasNext = true;
        }
        if (collect != null) {
            List<GDataDto> toUrls = collect.stream().filter(x -> StringUtils.isNotEmpty(x.getToUrl())).collect(Collectors.toList());
            if (!toUrls.isEmpty()) {
                for (GDataDto toUrl : toUrls) {
                    page.addTargetRequests(ListUtil.asList(toUrl.getToUrl()), toUrl.getPriority());
                }
                hasNext = true;
            }
        }
        page.setSkip(!hasNext);

    }


    @Override
    public Site getSite() {
        return site;
    }
}
