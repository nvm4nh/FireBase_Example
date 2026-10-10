package vn.edu.ueh.thanhdnh.firebase_example;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ArticleDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        TextView txtTitle = findViewById(R.id.txtDetailTitle);
        TextView txtContent = findViewById(R.id.txtDetailContent);

        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");

        if (title != null) txtTitle.setText(title);
        if (content != null) txtContent.setText(content);
    }
}
