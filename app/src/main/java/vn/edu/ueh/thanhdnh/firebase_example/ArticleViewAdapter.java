package vn.edu.ueh.thanhdnh.firebase_example;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ArticleViewAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
  private LayoutInflater mInflater;
  private List<Article> articles;
  private Context context;

  public ArticleViewAdapter(Context context, List<Article> articles) {
    this.context = context;
    this.mInflater = LayoutInflater.from(context);
    this.articles = articles;
  }

  public void update(List<Article> articles){
    this.articles = articles;
  }

  @NonNull
  @Override
  public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View customView = mInflater.inflate(R.layout.article_list, parent, false);
    return new ArticleViewHolder(customView, this);
  }

  @Override
  public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
    Article currentArticle = articles.get(position);
    holder.getTxtTitle().setText(currentArticle.getTitle());
    holder.getTxtContent().setText(currentArticle.getContent());

    holder.itemView.setOnClickListener(v -> {
        Intent intent = new Intent(context, ArticleDetailActivity.class);
        intent.putExtra("title", currentArticle.getTitle());
        intent.putExtra("content", currentArticle.getContent());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    });

    holder.getBtnDelete().setOnClickListener(v -> {
      if (currentArticle.getId() != null) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("articles").document(currentArticle.getId())
          .delete()
          .addOnSuccessListener(aVoid -> Toast.makeText(v.getContext(), "Đã xóa bài viết!", Toast.LENGTH_SHORT).show())
          .addOnFailureListener(e -> Toast.makeText(v.getContext(), "Lỗi khi xóa", Toast.LENGTH_SHORT).show());
      }
    });
  }

  @Override
  public int getItemCount() {
    return articles.size();
  }
}
