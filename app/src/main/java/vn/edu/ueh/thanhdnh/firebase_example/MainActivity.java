package vn.edu.ueh.thanhdnh.firebase_example;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
  FirebaseFirestore db;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    db = FirebaseFirestore.getInstance();

    EditText edTitle = findViewById(R.id.ed_title);
    EditText edContent = findViewById(R.id.ed_content);
    EditText edImageUrl = findViewById(R.id.ed_image_url);
    Button btnSave = findViewById(R.id.btn_save);
    Button btnShow = findViewById(R.id.btn_show);

    btnSave.setOnClickListener(view -> {
      String title = edTitle.getText().toString();
      String content = edContent.getText().toString();
      String imageUrl = edImageUrl.getText().toString();
      
      Article article = new Article(title, content, imageUrl);

      db.collection("articles")
        .add(article)
        .addOnSuccessListener(documentReference -> {
            Toast.makeText(getApplicationContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
            edTitle.setText("");
            edContent.setText("");
            edImageUrl.setText("");
        })
        .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    });

    btnShow.setOnClickListener(view -> {
      Intent intent = new Intent(MainActivity.this, ShowDataActivity.class);
      startActivity(intent);
    });
  }
}