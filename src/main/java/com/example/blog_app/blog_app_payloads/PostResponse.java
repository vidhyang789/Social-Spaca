package com.example.blog_app.blog_app_payloads;

import java.util.List;

public class PostResponse {
    List<PostDto> content;
    private int pagenumber;
    private int pagesize;
    private long totalelements;
    private int totalpage;
    private boolean lastpage;

    public PostResponse(List<PostDto> content, int pagenumber, int pagesize, int totalelements, int totalpage, boolean lastpage) {
        this.content = content;
        this.pagenumber = pagenumber;
        this.pagesize = pagesize;
        this.totalelements = totalelements;
        this.totalpage = totalpage;
        this.lastpage = lastpage;
    }

    public PostResponse() {
    }

    public List<PostDto> getContent() {
        return content;
    }

    public void setContent(List<PostDto> content) {
        this.content = content;
    }

    public int getPagenumber() {
        return pagenumber;
    }

    public void setPagenumber(int pagenumber) {
        this.pagenumber = pagenumber;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public long getTotalelements() {
        return totalelements;
    }

    public void setTotalelements(long totalelements) {
        this.totalelements = totalelements;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public boolean isLastpage() {
        return lastpage;
    }

    public void setLastpage(boolean lastpage) {
        this.lastpage = lastpage;
    }
}
