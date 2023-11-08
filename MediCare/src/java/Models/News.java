/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;


/**
 *
 * @author hoang
 */
public class News {
    private String id, title, subtitle, content, author, createdAt, lastModified, viewCount, coverImage, slug, type;
    private NewsCategory category;
    

    public News() {
    }
    public News(String id, String title, String content, String author, NewsCategory newsCategory, String createdAt, String lastModified, String viewCount, String coverImage, String subtitle, String slug, String type) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = newsCategory;
        this.createdAt = createdAt;
        this.lastModified = lastModified;
        this.viewCount = viewCount;
        this.coverImage = coverImage;
        this.subtitle = subtitle;
        this.slug = slug;
        this.type = type;
    }
     public News(String id, String title, String content, String author, NewsCategory newsCategory, String createdAt, String lastModified, String viewCount, String coverImage, String subtitle, String slug) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = newsCategory;
        this.createdAt = createdAt;
        this.lastModified = lastModified;
        this.viewCount = viewCount;
        this.coverImage = coverImage;
        this.subtitle = subtitle;
        this.slug = slug;

    }
      public News(String id, String title, String content, String author, NewsCategory newsCategory, String createdAt, String lastModified, String viewCount, String coverImage, String subtitle) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = newsCategory;
        this.createdAt = createdAt;
        this.lastModified = lastModified;
        this.viewCount = viewCount;
        this.coverImage = coverImage;
        this.subtitle = subtitle;
    }


    public News(String id, String title, String subtitle, String content, String author, String createdAt, String lastModified, String viewCount, String coverImage, String slug, String type) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.lastModified = lastModified;
        this.viewCount = viewCount;
        this.coverImage = coverImage;
        this.slug = slug;
        this.type = type;
    }
    
    public News(String id, String title, String subtitle, String content, String author, String createdAt, String lastModified, String viewCount, String coverImage, String slug, String type, NewsCategory category) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.lastModified = lastModified;
        this.viewCount = viewCount;
        this.coverImage = coverImage;
        this.slug = slug;
        this.type = type;
        this.category = category;
    }

    

    public News(String id, String title, String type) {
        this.id = id;
        this.title = title;
        this.type = type;
    }

    public News(String id, String title, String content, String email, NewsCategory newsCategory, String string, String string0, int i, String coverImage, String subtitle, String slug, Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public String toString() {
        return "{" + this.id + ", " + this.title + ", " + content + ", " + author + ", " + "}\n";
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NewsCategory getCategory() {
        return category;
    }

    public void setCategory(NewsCategory category) {
        this.category = category;
    }
}
