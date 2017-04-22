package com.yiwen.guet.service;

import android.util.Log;

import com.yiwen.guet.db.LinkNode;

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
            return find.get(0).getLink();
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
        Elements elements = doc.select("li");
        for (Element element : elements) {
            result.append(element.html() + "\n" + "*" + element.attr("a href") + "\n");


            if (element.attr("a href") != null & element.attr("a href").length() > 2) {
                linkNode = new LinkNode();
                linkNode.setTitle(element.text());
                linkNode.setLink(element.attr("a href"));
                Log.d("parseMenu", "parseMenu: 0a" + element.attr("a href"));
                save(linkNode);
            }


        }
        Log.d("parseMenu", "parseMenu: 1" + result.toString());
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
