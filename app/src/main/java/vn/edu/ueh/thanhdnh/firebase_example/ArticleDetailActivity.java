package vn.edu.ueh.thanhdnh.firebase_example;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

public class ArticleDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        TextView txtTitle = findViewById(R.id.txtDetailTitle);
        TextView txtContent = findViewById(R.id.txtDetailContent);
        ImageView imgDetail = findViewById(R.id.imgDetail);

        String initialTitle = getIntent().getStringExtra("title");
        String initialContent = getIntent().getStringExtra("content");
        String initialImageUrl = getIntent().getStringExtra("imageUrl");

        if (initialTitle != null) txtTitle.setText(initialTitle);
        if (initialContent != null) txtContent.setText(initialContent);
        if (initialImageUrl != null && !initialImageUrl.isEmpty()) {
            imgDetail.setVisibility(View.VISIBLE);
            Glide.with(this).load(initialImageUrl).into(imgDetail);
        }

        String id = getIntent().getStringExtra("id");
        if (id == null || id.isEmpty()) return;

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("articles").document(id).addSnapshotListener(this, (snapshot, e) -> {
            if (e != null) return;
            if (snapshot != null && snapshot.exists()) {
                String updatedTitle = snapshot.getString("title");
                String updatedContent = snapshot.getString("content");
                String updatedImageUrl = snapshot.getString("imageUrl");

                if (updatedTitle != null) txtTitle.setText(updatedTitle);
                if (updatedContent != null) txtContent.setText(updatedContent);
                if (updatedImageUrl != null && !updatedImageUrl.isEmpty()) {
                    imgDetail.setVisibility(View.VISIBLE);
                    Glide.with(this).load(updatedImageUrl).into(imgDetail);
                }
            } else {
                Toast.makeText(this, "Bài viết đã bị xóa!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}