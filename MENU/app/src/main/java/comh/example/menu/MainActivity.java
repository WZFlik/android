package comh.example.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    //overrid  eonCreateOptionsMenu  function

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    //menu item响应

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.Item_f:
                Toast.makeText(this,"you clicked copy!",Toast.LENGTH_LONG).show();
                break;
            case R.id.Item_p:
                Toast.makeText(this,"you clicked paste!",Toast.LENGTH_LONG).show();
                break;
            case R.id.Item_j:
                Toast.makeText(this,"you clicked cut !",Toast.LENGTH_LONG).show();
                break;
                default:;
        }
        return true;
    }
}
