package vn.edu.ueh.thanhdnh.firebase_example;

import android.os.Bundle;
import android.widget.TextView;
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

        // Hiển thị tạm thời dữ liệu cũ khi vừa mở trang
        String initialTitle = getIntent().getStringExtra("title");
        String initialContent = getIntent().getStringExtra("content");
        if (initialTitle != null) txtTitle.setText(initialTitle);
        if (initialContent != null) txtContent.setText(initialContent);

        // Lấy ID để lắng nghe thay đổi Realtime
        String id = getIntent().getStringExtra("id");
        if (id != null && !id.isEmpty()) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("articles").document(id).addSnapshotListener((snapshot, e) -> {
                if (e != null) {
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    String updatedTitle = snapshot.getString("title");
                    String updatedContent = snapshot.getString("content");
                    if (updatedTitle != null) txtTitle.setText(updatedTitle);
                    if (updatedContent != null) txtContent.setText(updatedContent);
                } else {
                    // Nếu document bị xóa trên firebase, đóng màn hình này lại
                    finish();
                }
            });
        }
    }
}
