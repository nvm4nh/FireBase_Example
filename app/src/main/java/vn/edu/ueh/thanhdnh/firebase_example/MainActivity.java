package vn.edu.ueh.thanhdnh.firebase_example;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  FirebaseFirestore db;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    db = FirebaseFirestore.getInstance();

    EditText edTitle = findViewById(R.id.ed_title);
    EditText edContent = findViewById(R.id.ed_content);
    Button btnSave = findViewById(R.id.btn_save);
    Button btnShow = findViewById(R.id.btn_show);
    Button btnSeed = findViewById(R.id.btn_seed);

    btnSave.setOnClickListener(view -> {
      String title = edTitle.getText().toString();
      String content = edContent.getText().toString();
      Article article = new Article(title, content, "");

      db.collection("articles")
        .add(article)
        .addOnSuccessListener(documentReference -> Toast.makeText(getApplicationContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show())
        .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    });

    btnShow.setOnClickListener(view -> {
      Intent intent = new Intent(MainActivity.this, ShowDataActivity.class);
      startActivity(intent);
    });

    btnSeed.setOnClickListener(v -> {
        seedData();
    });
  }

  private void seedData() {
      List<Article> singers = Arrays.asList(
          new Article("Taylor Swift", "Taylor Alison Swift là nữ ca sĩ, nhạc sĩ người Mỹ. Cô nổi tiếng với các bài hát về cuộc sống cá nhân, nhận được sự tán dương rộng rãi.", "https://upload.wikimedia.org/wikipedia/commons/b/b1/Taylor_Swift_at_the_2023_MTV_Video_Music_Awards_%283%29.png"),
          new Article("Michael Jackson", "Michael Joseph Jackson là một ca sĩ, nhạc sĩ, vũ công người Mỹ. Được mệnh danh là 'Ông hoàng nhạc Pop'.", "https://upload.wikimedia.org/wikipedia/commons/3/33/Michael_Jackson_in_1988.jpg"),
          new Article("Ed Sheeran", "Edward Christopher Sheeran là nam ca sĩ, nhạc sĩ người Anh. Nổi tiếng với Shape of You, Perfect.", "https://upload.wikimedia.org/wikipedia/commons/c/c1/Ed_Sheeran-6886_%28cropped%29.jpg"),
          new Article("Adele", "Adele Laurie Blue Adkins là ca sĩ, nhạc sĩ người Anh. Nổi tiếng với giọng hát truyền cảm.", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Adele_2016.jpg/800px-Adele_2016.jpg"),
          new Article("Bruno Mars", "Peter Gene Hernandez, được biết đến với nghệ danh Bruno Mars, là một ca sĩ-nhạc sĩ người Mỹ.", "https://upload.wikimedia.org/wikipedia/commons/9/9b/Bruno_Mars_in_2024.jpg")
      );

      for (Article s : singers) {
          db.collection("articles").add(s);
      }
      Toast.makeText(this, "Đã thêm 5 ca sĩ mẫu!", Toast.LENGTH_SHORT).show();
  }
}