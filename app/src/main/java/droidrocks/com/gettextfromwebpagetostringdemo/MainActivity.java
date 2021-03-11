package droidrocks.com.gettextfromwebpagetostringdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private Button fetch;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.result);
        fetch = (Button) findViewById(R.id.fetch);
        editText = findViewById(R.id.editText);


        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBodyText();
            }
        });
    }

    private void getBodyText() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                String Url = editText.getText().toString();
                if (Url.equals("")){
                    Toast.makeText(MainActivity.this, "Enter URl", Toast.LENGTH_SHORT).show();
                    return;
                }


                try {
                    String url=Url;//your website url
                    Document doc = Jsoup.connect(url).get();

                    Element body = doc.body();
                    builder.append(body.text());

                } catch (Exception e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        result.setText(builder.toString());
                    }
                });
            }
        }).start();
    }




}