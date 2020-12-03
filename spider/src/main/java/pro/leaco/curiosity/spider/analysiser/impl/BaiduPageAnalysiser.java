package pro.leaco.curiosity.spider.analysiser.impl;

import org.apache.commons.lang3.StringUtils;
import pro.leaco.curiosity.db.g.service.GDataDto;
import pro.leaco.curiosity.spider.analysiser.PageAnalysiser;
import pro.leaco.curiosity.spider.vo.Data;
import pro.leaco.curiosity.util.ListUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.stream.Collectors;

public class BaiduPageAnalysiser implements PageAnalysiser {

    @Override
    public boolean support(Page page) {
        String url = page.getRequest().getUrl();
        if (url == null) {
            return false;
        }
        return url.startsWith("https://www.baidu.com") || url.startsWith("http://www.baidu.com");
    }

    @Override
    public List<Data> analysisInterestData(Page page) {
        List<Selectable> nodes = page.getHtml().css(".result").nodes();
        if (nodes == null) {
            return null;
        }
        return nodes.stream()
                .map(s -> this.collectData(s, page.getRequest()))
                .filter(x -> StringUtils.isNotEmpty(x.getTitle()))
                .collect(Collectors.toList());
    }

    private Data collectData(Selectable s, Request request) {
        String title = s.$(".t a").xpath("/a/text()").get();
        String abs = s.$(".c-abstract").xpath("/div/text()").get();
        String data = s.get();
        String fromUrl = request.getUrl();
        String toUrl = s.$(".se_st_footer a").xpath("/a/@href").get();

        Data result = new Data(title, abs, data, GDataDto.Type.REF, GDataDto.ContentType.TEXT, fromUrl, this);
        result.setToUrl(toUrl);
        result.setPriority(0);
        return result;
    }

    @Override
    public List<String> analysisNextUrl(Page page) {
        String url = page.getHtml().$("#page a:contains(>)", "href").get();
        return ListUtil.asList(url);
    }
}
