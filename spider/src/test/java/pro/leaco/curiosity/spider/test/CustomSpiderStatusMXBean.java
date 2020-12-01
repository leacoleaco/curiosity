package pro.leaco.curiosity.spider.test;

import us.codecraft.webmagic.monitor.SpiderStatusMXBean;

/**
 * @author code4crafer@gmail.com
 */
public interface CustomSpiderStatusMXBean extends SpiderStatusMXBean {

    public String getSchedulerName();

}
