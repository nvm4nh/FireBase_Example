package vn.edu.ueh.thanhdnh.firebase_example;

import com.google.firebase.firestore.Exclude;

public class Article {
  private String id;
  private String title;
  private String content;

  public Article() {}

  public Article(String title, String content) {
    this.title = title;
    this.content = content;
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
}
