package pro.leaco.curiosity.spider.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.leaco.curiosity.spider.downloader.PuppeteerDownloader;
import pro.leaco.curiosity.spider.processor.BaiduPageProcessor;
import pro.leaco.curiosity.spider.processor.GithubRepoPageProcessor;
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
            Spider.create(new BaiduPageProcessor(null))
                    .setDownloader(new PuppeteerDownloader())
                    //设置爬取的开始网页
                    .addUrl("https://www.baidu.com/s?wd=" + searchWord)
                    //设置爬取数据进入的pipeline
                    .addPipeline(dataService)
                    //设置线程
                    .thread(5)
                    .run();
        } catch (InterruptedException | ExecutionException | IOException e) {
            logger.error(e.getMessage(), e);
        }

    }

}

