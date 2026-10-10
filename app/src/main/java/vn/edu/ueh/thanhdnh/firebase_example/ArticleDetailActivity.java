package vn.edu.ueh.thanhdnh.firebase_example;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;

public class ArticleDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        TextView txtTitle = findViewById(R.id.txtDetailTitle);
        TextView txtContent = findViewById(R.id.txtDetailContent);

        String initialTitle = getIntent().getStringExtra("title");
        String initialContent = getIntent().getStringExtra("content");
        if (initialTitle != null) txtTitle.setText(initialTitle);
        if (initialContent != null) txtContent.setText(initialContent);

        String id = getIntent().getStringExtra("id");
        
        if (id == null || id.isEmpty()) {
            Toast.makeText(this, "Lỗi: ID bị null, không thể Realtime!", Toast.LENGTH_LONG).show();
            return;
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("articles").document(id).addSnapshotListener(this, (snapshot, e) -> {
            if (e != null) {
                Toast.makeText(this, "Lỗi Realtime: " + e.getMessage(), Toast.LENGTH_LONG).show();
                return;
            }
            if (snapshot != null && snapshot.exists()) {
                String updatedTitle = snapshot.getString("title");
                String updatedContent = snapshot.getString("content");
                if (updatedTitle != null) txtTitle.setText(updatedTitle);
                if (updatedContent != null) txtContent.setText(updatedContent);
            } else {
                Toast.makeText(this, "Bài viết đã bị xóa!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
