package vn.edu.ueh.thanhdnh.firebase_example;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleViewHolder extends RecyclerView.ViewHolder {
  private TextView txtTitle, txtContent;
  private Button btnDelete;
  private ArticleViewAdapter adapter;

  public ArticleViewHolder(@NonNull View itemView, ArticleViewAdapter adapter) {
    super(itemView);
    txtTitle = itemView.findViewById(R.id.txt_title);
    txtContent = itemView.findViewById(R.id.txt_content);
    btnDelete = itemView.findViewById(R.id.btn_delete);
    this.adapter = adapter;
  }

  public TextView getTxtTitle() {
    return txtTitle;
  }

  public void setTxtTitle(TextView txtTitle) {
    this.txtTitle = txtTitle;
  }

  public TextView getTxtContent() {
    return txtContent;
  }

  public void setTxtContent(TextView txtContent) {
    this.txtContent = txtContent;
  }

  public Button getBtnDelete() {
    return btnDelete;
  }
}
