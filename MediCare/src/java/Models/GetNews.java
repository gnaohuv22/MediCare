/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author DELL
 */
public class GetNews {
    private News news;
    private NewsCategory newsCategory;

    public GetNews() {
    }

    public GetNews(News news, NewsCategory newsCategory) {
        this.news = news;
        this.newsCategory = newsCategory;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public NewsCategory getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(NewsCategory newsCategory) {
        this.newsCategory = newsCategory;
    }
    
}
