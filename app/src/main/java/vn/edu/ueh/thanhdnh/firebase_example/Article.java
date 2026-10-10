package vn.edu.ueh.thanhdnh.firebase_example;

import com.google.firebase.firestore.Exclude;

public class Article {
  private String id;
  private String title;
  private String content;
  private String imageUrl;

  public Article() {}

  public Article(String title, String content, String imageUrl) {
    this.title = title;
    this.content = content;
    this.imageUrl = imageUrl;
  }

  @Exclude
  public String getId() {
    return id;
  }

  @Exclude
  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}