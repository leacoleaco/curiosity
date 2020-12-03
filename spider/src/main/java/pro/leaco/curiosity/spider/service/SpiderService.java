package pro.leaco.curiosity.spider.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.leaco.curiosity.spider.analysiser.PageAnalysisFactory;
import pro.leaco.curiosity.spider.analysiser.SearchConfig;
import pro.leaco.curiosity.spider.magic.downloader.PuppeteerDownloader;
import pro.leaco.curiosity.spider.magic.processor.CuriosityPageProcessor;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class SpiderService {

    private static Logger logger = LoggerFactory.getLogger(SpiderService.class);

    @Resource
    private DataService dataService;

    /**
     * 开始抓取网页
     */
    public void grap(String searchWord) {
        try {
            Spider.create(new CuriosityPageProcessor(new PageAnalysisFactory(new SearchConfig(searchWord))))
                    .setDownloader(new PuppeteerDownloader())
                    //设置爬取的开始网页
                    .addUrl("https://www.baidu.com/s?wd=" + searchWord)
                    //设置爬取数据后进入的pipeline
                    .addPipeline(dataService)
                    //设置线程
                    .thread(1)
                    .run();
        } catch (InterruptedException | ExecutionException | IOException e) {
            logger.error(e.getMessage(), e);
        }

    }

}

