package vn.edu.ueh.thanhdnh.firebase_example;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleViewHolder extends RecyclerView.ViewHolder {
  private TextView txtTitle;
  private TextView txtContent;
  private ImageView imgArticle;
  private Button btnDelete;
  private ArticleViewAdapter adapter;

  public ArticleViewHolder(@NonNull View itemView, ArticleViewAdapter adapter) {
    super(itemView);
    this.adapter = adapter;
    txtTitle = itemView.findViewById(R.id.txt_title);
    txtContent = itemView.findViewById(R.id.txt_content);
    imgArticle = itemView.findViewById(R.id.img_article);
    btnDelete = itemView.findViewById(R.id.btn_delete);
  }

  public TextView getTxtTitle() { return txtTitle; }
  public TextView getTxtContent() { return txtContent; }
  public ImageView getImgArticle() { return imgArticle; }
  public Button getBtnDelete() { return btnDelete; }
}