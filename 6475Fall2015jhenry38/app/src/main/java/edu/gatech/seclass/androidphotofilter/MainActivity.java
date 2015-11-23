package edu.gatech.seclass.androidphotofilter;

//import edu.gatech.seclass.androidphotofilter.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import org.opencv.android.OpenCVLoader;

public class MainActivity extends AppCompatActivity {
    Button button;
    ImageView image;
    private Spinner spinner1, spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();
        if (!OpenCVLoader.initDebug()) {}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addListenerOnButton() {

        image = (ImageView) findViewById(R.id.imageView);

        button = (Button) findViewById(R.id.btnChangeImage);
        spinner1 = (Spinner) findViewById(R.id.imageSpinner);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (String.valueOf(spinner1.getSelectedItem()).equals("city")) {
                    image.setImageResource(R.drawable.city);
                }
                if (String.valueOf(spinner1.getSelectedItem()).equals("dog")) {
                    image.setImageResource(R.drawable.dog);
                }
                if (String.valueOf(spinner1.getSelectedItem()).equals("sloth")) {
                    image.setImageResource(R.drawable.sloth);
                }

            }

        });

    }
}
