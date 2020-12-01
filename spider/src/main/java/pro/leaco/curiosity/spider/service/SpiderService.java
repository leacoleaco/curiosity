package pro.leaco.curiosity.spider.service;

import org.springframework.stereotype.Service;
import pro.leaco.curiosity.spider.processor.GithubRepoPageProcessor;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

@Service
public class SpiderService {


    /**
     * 开始抓取网页
     */
    public void grap() {

        Spider.create(new GithubRepoPageProcessor())
                //设置爬取的开始网页
                .addUrl("https://github.com/code4craft")
                //设置爬取数据进入的pipeline
                .addPipeline(new JsonFilePipeline("/Users/like/Documents/workspace/curiosity/logs/spiders/"))
                //设置线程
                .thread(5)
                .run();

    }

}

