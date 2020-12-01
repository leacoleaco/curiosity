package pro.leaco.curiosity.spider.downloader;

import com.ruiyun.jvppeteer.core.Puppeteer;
import com.ruiyun.jvppeteer.core.browser.Browser;
import com.ruiyun.jvppeteer.core.browser.BrowserFetcher;
import com.ruiyun.jvppeteer.core.page.Response;
import com.ruiyun.jvppeteer.options.LaunchOptions;
import com.ruiyun.jvppeteer.options.LaunchOptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PuppeteerDownloader implements Downloader {


    private static Logger logger = LoggerFactory.getLogger(PuppeteerDownloader.class);

    Browser browser;

    public PuppeteerDownloader() throws InterruptedException, ExecutionException, IOException {
        //设置基本的启动配置,这里选择了‘有头’模式启动
        ArrayList<String> argList = new ArrayList<>();
        //自动下载，第一次下载后不会再下载
        BrowserFetcher.downloadIfNotExist(null);
        LaunchOptions options = new LaunchOptionsBuilder().withArgs(argList).withHeadless(false).build();
        argList.add("--no-sandbox");
        argList.add("--disable-setuid-sandbox");
        this.browser = Puppeteer.launch(options);
    }

    @Override
    public Page download(Request request, Task task) {

        com.ruiyun.jvppeteer.core.page.Page page = browser.newPage();

        try {
            Response response = page.goTo(request.getUrl());

            //TODO:
            return new Page();
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void setThread(int i) {

    }
}
