package com.yiwen.guet.service;

import android.util.Log;

import com.yiwen.guet.db.LinkNode;
import com.yiwen.guet.utils.HttpUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * LinNode表的业务逻辑处理
 *
 * @author lizhangqu
 * @date 2015-2-1
 */
public class LinkService {
    private static volatile LinkService linkService;

    private LinkService() {
    }

    public static LinkService getLinkService() {
        if (linkService == null) {
            synchronized (LinkService.class) {
                if (linkService == null)
                    linkService = new LinkService();
            }
        }

        return linkService;
    }

    public static String getLinkByName(String name) {
        List<LinkNode> find = DataSupport.where("title=?", name).limit(1).find(LinkNode.class);
        if (find.size() != 0) {
            String linkName = find.get(0).getLink();
            if (linkName.indexOf("..") > -1) {
                linkName = HttpUtil.URL_BASE + linkName.substring(linkName.indexOf("/"));
                Log.d("linkName", "getLinkByName: " + linkName);
            } else {
                linkName = "http://bkjw.guet.edu.cn/student/public/" + linkName;
                Log.d("linkName1", "getLinkByName: " + linkName);
            }

            return linkName;
        } else {
            return null;
        }
    }

    public boolean save(LinkNode linknode) {
        return linknode.save();
    }

    /**
     * 查询所有链接
     *
     * @return
     */
    public List<LinkNode> findAll() {
        return DataSupport.findAll(LinkNode.class);
    }

    public String parseMenu(String content) {
        LinkNode linkNode = null;
        StringBuilder result = new StringBuilder();
        Document doc = Jsoup.parse(content);
        Elements elements = doc.select("a");
        for (Element element : elements) {
            result.append(element.text().trim() + "\n" + "*" + element.attr("href").trim() + "\n");
            if ("办事指南".equals(element.text().trim())) {
                continue;
            }
            linkNode = new LinkNode();
            linkNode.setTitle(element.text().trim());
            linkNode.setLink(element.attr("href").trim());
            Log.d("parseMenu", element.text() + element.attr("href"));
            save(linkNode);
        }
        Log.d("parseMenu", "last" + result.toString());
        return result.toString();

    }

    public String isLogin(String content) {
        Document doc = Jsoup.parse(content, "UTF-8");
        Elements elements = doc.select("noframes");
        try {
            Element element = elements.get(0);
            return element.text();
        } catch (IndexOutOfBoundsException e) {
            //e.printStackTrace();
        }
        return null;
    }
}
