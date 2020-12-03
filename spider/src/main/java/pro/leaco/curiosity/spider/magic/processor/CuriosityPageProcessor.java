package pro.leaco.curiosity.spider.magic.processor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.leaco.curiosity.db.g.service.GDataDto;
import pro.leaco.curiosity.spider.analysiser.PageAnalysisFactory;
import pro.leaco.curiosity.spider.analysiser.PageAnalysiser;
import pro.leaco.curiosity.spider.magic.CuriosityRequest;
import pro.leaco.curiosity.spider.vo.Data;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;

import java.util.List;
import java.util.stream.Collectors;

public class CuriosityPageProcessor implements us.codecraft.webmagic.processor.PageProcessor {

    private static Logger logger = LoggerFactory.getLogger(CuriosityPageProcessor.class);

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(5000);

    private final PageAnalysisFactory pageAnalysisFactory;

    public CuriosityPageProcessor(PageAnalysisFactory pageAnalysisFactory) {
        this.pageAnalysisFactory = pageAnalysisFactory;
    }

    @Override
    public void process(Page page) {

        if (page == null) {
            return;
        }

        PageAnalysiser pageAnalysiser = pageAnalysisFactory.findBestAnalysiser(page);
        if (pageAnalysiser == null) {
            logger.debug("page:{}找不到合适的页面分析器", page);
            return;
        }


        // 部分二：定义如何抽取页面信息，并保存下来
        List<Data> collect = pageAnalysiser.analysisInterestData(page);
        page.putField("result", collect);


        // 部分三：从页面发现后续的url地址来抓取
        boolean hasNext = false;
        List<String> nextUrls = pageAnalysiser.analysisNextUrl(page);
        if (nextUrls != null) {
            addTargetRequest(page, nextUrls, 99999);
            hasNext = true;
        }
        if (collect != null) {
            List<GDataDto> toUrls = collect.stream().filter(x -> StringUtils.isNotEmpty(x.getToUrl())).collect(Collectors.toList());
            if (!toUrls.isEmpty()) {
                for (GDataDto toUrl : toUrls) {
                    addTargetRequest(page, toUrl.getToUrl(), toUrl.getPriority());
                }
                hasNext = true;
            }
        }
        page.setSkip(!hasNext);

    }

    private static void addTargetRequest(Page page, List<String> urls, long priority) {
        if (urls == null) {
            return;
        }
        for (String url : urls) {
            addTargetRequest(page, url, priority);
        }
    }

    private static void addTargetRequest(Page page, String url, long priority) {
        if (!StringUtils.isBlank(url) && !url.equals("#") && !url.startsWith("javascript:")) {
            CuriosityRequest request = new CuriosityRequest(url);
            request.setPriority(priority);
            //增加访问深度
            request.setDeep(CuriosityRequest.getDeepValue(page.getRequest()) + 1);
            page.addTargetRequest(request);
        }
    }


    @Override
    public Site getSite() {
        return site;
    }
}
