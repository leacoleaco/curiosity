package pro.leaco.curiosity.spider.service;

import org.springframework.stereotype.Service;
import pro.leaco.curiosity.spider.processor.BaiduPageProcessor;
import pro.leaco.curiosity.spider.processor.GithubRepoPageProcessor;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;

@Service
public class SpiderService {

    @Resource
    private DataService dataService;

    /**
     * 开始抓取网页
     */
    public void grap(String searchWord) {

        Spider.create(new BaiduPageProcessor(null))
                //设置爬取的开始网页
                .addUrl("https://www.baidu.com/s?wd=" + searchWord)
                //设置爬取数据进入的pipeline
                .addPipeline(dataService)
                //设置线程
                .thread(5)
                .run();

    }

}

