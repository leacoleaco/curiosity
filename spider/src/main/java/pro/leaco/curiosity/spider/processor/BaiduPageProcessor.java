package pro.leaco.curiosity.spider.processor;

import org.apache.commons.lang3.StringUtils;
import pro.leaco.curiosity.db.g.service.GDataDto;
import pro.leaco.curiosity.spider.analysiser.Analysiser;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.*;
import java.util.stream.Collectors;

public class BaiduPageProcessor implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    private final Analysiser analysiser;

    public BaiduPageProcessor(Analysiser analysiser) {
        this.analysiser = analysiser;
    }

    @Override
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来


        List<Selectable> nodes = page.getHtml().css(".result").nodes();
        if (nodes == null) {
            page.setSkip(true);
            return;
        }

        List<GDataDto> collect = nodes.stream()
                .map(this::collectData)
                .filter(x-> StringUtils.isNotEmpty(x.getTitle()))
                .collect(Collectors.toList());


        page.putField("result", collect);


        // 部分三：从页面发现后续的url地址来抓取

    }

    private GDataDto collectData(Selectable s) {
        String title = s.$(".t a").xpath("/a/text()").get();
        String abs = s.$(".c-abstract").xpath("/div/text()").get();
        String data = s.get();
        String url = s.$(".se_st_footer a").xpath("/a/@href").get();
        Short type = 0;
        Integer ruleId = 0;

        return new GDataDto()
                .withTitle(title)
                .withAbs(abs)
                .withData(data)
                .withType(type)
                .withRuleId(ruleId)
                .withUrl(url);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
