package pro.leaco.curiosity.spider.magic;

import us.codecraft.webmagic.Request;

public class CuriosityRequest extends Request {

    /**
     * 请求访问的深度
     */
    long deep;

    public CuriosityRequest() {
    }

    public CuriosityRequest(String url) {
        super(url);
    }

    public static long getDeepValue(Request request) {
        if (request == null) {
            return 0;
        }
        if (request instanceof CuriosityRequest) {
            return ((CuriosityRequest) request).getDeep();
        }
        return 0;
    }

    public long getDeep() {
        return deep;
    }

    public void setDeep(long deep) {
        this.deep = deep;
    }
}
