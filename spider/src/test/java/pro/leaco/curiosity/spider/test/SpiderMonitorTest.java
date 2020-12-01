package pro.leaco.curiosity.spider.test;

import org.junit.Test;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.monitor.SpiderStatusMXBean;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;
import us.codecraft.webmagic.processor.example.ZhihuPageProcessor;

/**
 * @author code4crafer@gmail.com
 * @since 0.5.0
 */
public class SpiderMonitorTest {

    @Test
    public void testInherit() throws Exception {
        SpiderMonitor spiderMonitor = new SpiderMonitor(){
            @Override
            protected SpiderStatusMXBean getSpiderStatusMBean(Spider spider, MonitorSpiderListener monitorSpiderListener) {
                return new CustomSpiderStatus(spider, monitorSpiderListener);
            }
        };

        Spider zhihuSpider = Spider.create(new ZhihuPageProcessor())
                .addUrl("http://my.oschina.net/flashsword/blog").thread(2);
        Spider githubSpider = Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://github.com/code4craft");

        spiderMonitor.register(zhihuSpider, githubSpider);

        zhihuSpider.start();
        githubSpider.start();

        Thread.sleep(1000*60);

    }

    @Test
    public void testGrap() throws Exception {

        Spider zhihuSpider = Spider.create(new ZhihuPageProcessor())
                .addUrl("http://my.oschina.net/flashsword/blog").thread(2);
        zhihuSpider.start();
        Thread.sleep(1000*60);

    }
}
