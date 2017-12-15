package mi.protectactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProtetByService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protet_by_service);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("This Activity is Unexpoted so it should open by the exported service and protect by the service"+"\n");
    }
}
