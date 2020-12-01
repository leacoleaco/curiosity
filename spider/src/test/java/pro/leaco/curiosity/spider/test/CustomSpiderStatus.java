package pro.leaco.curiosity.spider.test;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.monitor.SpiderStatus;

/**
 * @author code4crafer@gmail.com
 */
public class CustomSpiderStatus extends SpiderStatus implements CustomSpiderStatusMXBean {

    public CustomSpiderStatus(Spider spider, SpiderMonitor.MonitorSpiderListener monitorSpiderListener) {
        super(spider, monitorSpiderListener);
    }


    @Override
    public String getSchedulerName() {
        return spider.getScheduler().getClass().getName();
    }
}
