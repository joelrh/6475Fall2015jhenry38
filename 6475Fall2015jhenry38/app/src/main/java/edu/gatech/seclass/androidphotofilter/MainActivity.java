package edu.gatech.seclass.androidphotofilter;

//import edu.gatech.seclass.androidphotofilter.R;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import android.widget.TextView;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity {
    Button button;
    Button applyFilter;
    ImageView image;
    SeekBar seekBar;
    int drawableInt;
    private Spinner imageSpinner, filterSpinner;
    TextView spinnerValueText;

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

        applyFilter = (Button) findViewById(R.id.btnApplyFilter);

        imageSpinner = (Spinner) findViewById(R.id.imageSpinner);

        filterSpinner = (Spinner) findViewById(R.id.filterSpinner);

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        spinnerValueText = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (String.valueOf(imageSpinner.getSelectedItem()).equals("city")) {
                    image.setImageResource(R.drawable.city);
                    drawableInt = R.drawable.city;
                }
                if (String.valueOf(imageSpinner.getSelectedItem()).equals("dog")) {
                    image.setImageResource(R.drawable.dog);
                    drawableInt = R.drawable.dog;
                }
                if (String.valueOf(imageSpinner.getSelectedItem()).equals("sloth")) {
                    image.setImageResource(R.drawable.sloth);
                    drawableInt = R.drawable.sloth;
                }

            }

        });

        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

//            @Override
//            public void onClick(View arg0) {
//                int seekValue = seekBar.getProgress();
//                spinnerValueText.setText(String.valueOf(seekValue));
//                applyFilter.performClick();
//            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                int seekValue = seekBar.getProgress();
                spinnerValueText.setText(String.valueOf(seekValue));
                applyFilter.performClick();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                int seekValue = seekBar.getProgress();
                spinnerValueText.setText(String.valueOf(seekValue));
                applyFilter.performClick();

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                int seekValue = seekBar.getProgress();
                spinnerValueText.setText(String.valueOf(seekValue));
                applyFilter.performClick();

            }

        });

        applyFilter.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                int seekValue = seekBar.getProgress();
                spinnerValueText.setText(String.valueOf(seekValue));


                if (String.valueOf(filterSpinner.getSelectedItem()).equals("greyscale")) {
                    try {
                        Mat m = Utils.loadResource(MainActivity.this, drawableInt, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
                        Bitmap bm = Bitmap.createBitmap(m.cols(), m.rows(), Bitmap.Config.ARGB_8888);
                        Utils.matToBitmap(m, bm);
                        image.setImageBitmap(bm);
                    } catch (Exception e) {
                        System.out.println("test");
                    }
                }

                if (String.valueOf(filterSpinner.getSelectedItem()).equals("black and white")) {
                    try {


                        Mat m = Utils.loadResource(MainActivity.this, drawableInt, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
                        Imgproc.threshold(m, m, seekValue, 255, Imgproc.THRESH_BINARY);
                        Bitmap bm = Bitmap.createBitmap(m.cols(), m.rows(), Bitmap.Config.ARGB_8888);
                        Utils.matToBitmap(m, bm);
                        image.setImageBitmap(bm);
                    } catch (Exception e) {
                        System.out.println("test");
                    }
                }

                if (String.valueOf(filterSpinner.getSelectedItem()).equals("GaussianBlur")) {
                    try {
                        //int seekValue = seekBar.getProgress();
                        // double scaledSeekValue = seekValue/255;


                        Mat m = Utils.loadResource(MainActivity.this, drawableInt, Imgcodecs.CV_LOAD_IMAGE_COLOR);

                        //scaledSeekValue = scaledSeekValue*Math.min(m.width(),m.height());
                        //seekValue = (int) scaledSeekValue ;
                        Imgproc.GaussianBlur(m, m, new Size(seekValue, seekValue), 0);
                        Imgproc.cvtColor(m, m, Imgproc.COLOR_BGR2RGB);
                        //Imgproc.threshold(m, m, seekValue, 255, Imgproc.THRESH_BINARY);
                        //Imgproc.cvtColor(m, m, Imgproc.COLOR_GRAY2RGBA, 4);
//                        Imgproc.cvtColor(m, m, Imgproc.COLOR_BGR2RGB);
//                        for (int column=0;column<m.cols();column++){
//                            for (int row=0;row<m.rows();row++) {
//                                m.put(column,row,1);
//                            }
//                        }

                        Bitmap bm = Bitmap.createBitmap(m.cols(), m.rows(), Bitmap.Config.ARGB_8888);
                        Utils.matToBitmap(m, bm);
                        image.setImageBitmap(bm);
                    } catch (Exception e) {
                        System.out.println("test");
                    }
                }

                if (String.valueOf(filterSpinner.getSelectedItem()).equals("Edge")) {
                    try {
                        //int seekValue = seekBar.getProgress();
                        // double scaledSeekValue = seekValue/255;


                        Mat m = Utils.loadResource(MainActivity.this, drawableInt, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

                        //scaledSeekValue = scaledSeekValue*Math.min(m.width(),m.height());
                        //seekValue = (int) scaledSeekValue ;

                        Imgproc.Canny(m, m, 10, seekValue, 3, false);


                        //Imgproc.cvtColor(m, m, Imgproc.COLOR_BGR2RGB);
                        //Imgproc.threshold(m, m, seekValue, 255, Imgproc.THRESH_BINARY);
                        //Imgproc.cvtColor(m, m, Imgproc.COLOR_GRAY2RGBA, 4);
//                        Imgproc.cvtColor(m, m, Imgproc.COLOR_BGR2RGB);
//                        for (int column=0;column<m.cols();column++){
//                            for (int row=0;row<m.rows();row++) {
//                                m.put(column,row,1);
//                            }
//                        }

                        Bitmap bm = Bitmap.createBitmap(m.cols(), m.rows(), Bitmap.Config.ARGB_8888);
                        Utils.matToBitmap(m, bm);
                        image.setImageBitmap(bm);
                    } catch (Exception e) {
                        System.out.println("test");
                    }
                }

            }

        });

        boolean GO = false;
        if (GO) {
            try {
                Mat m = Utils.loadResource(MainActivity.this, R.drawable.dog, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
                Bitmap bm = Bitmap.createBitmap(m.cols(), m.rows(), Bitmap.Config.ARGB_8888);
                Utils.matToBitmap(m, bm);

                image.setImageBitmap(bm);
            } catch (Exception e) {
                System.out.println("test");
            }
        }

    }
}
