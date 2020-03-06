package com.ssk.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@XStreamAlias("xml")
public class NewsMessage extends BaseMessage {
    @XStreamAlias("ArticleCount")
    private String articleCount;
    @XStreamAlias("Articles")
    private List<Articles> articles = new ArrayList<Articles>();

    public NewsMessage(Map<String, String> requsetMap,List<Articles> articles) {
        super(requsetMap);
        setMsgType("news");
        this.articleCount=articles.size()+"";
        this.articleCount = articleCount;
        this.articles = articles;
    }

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }
}
