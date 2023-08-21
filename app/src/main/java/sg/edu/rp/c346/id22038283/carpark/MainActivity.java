package sg.edu.rp.c346.id22038283.carpark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ListView lvCarpark;
    AsyncHttpClient client;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvCarpark = findViewById(R.id.listview);
        client = new AsyncHttpClient();
        ArrayList alCarpark = new ArrayList<>();
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, alCarpark); // Initialize the adapter
        lvCarpark.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<CarPark> alCarpark = new ArrayList<CarPark>();
        client.get("https://api.data.gov.sg/v1/transport/carpark-availability", new JsonHttpResponseHandler() {
            String carpark_no;
            String datetime;
            int total_lots;
            String lot_type;
            int lots_avail;

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray jsonArrItems = response.getJSONArray("items");
                    JSONObject firstObj = jsonArrItems.getJSONObject(0);
                    JSONArray jsonArrCarparkdata = firstObj.getJSONArray("carpark_data");

                    for (int i = 0; i < jsonArrCarparkdata.length(); i++) {
                        JSONObject jsonObjCarparkdata = jsonArrCarparkdata.getJSONObject(i);
                        JSONArray jsonArrCarparkinfo = jsonObjCarparkdata.getJSONArray("carpark_info");

                        JSONObject firstObjInfo = jsonArrCarparkinfo.getJSONObject(0);

                        carpark_no = jsonObjCarparkdata.getString("carpark_number");
                        datetime = jsonObjCarparkdata.getString("update_datetime");
                        total_lots = firstObjInfo.getInt("total_lots");
                        lot_type = firstObjInfo.getString("lot_type");
                        lots_avail = firstObjInfo.getInt("lots_available");

                        CarPark carpark = new CarPark(carpark_no, datetime, total_lots, lot_type, lots_avail);
                        alCarpark.add(carpark);
                    }

                    // Update the adapter with new data
                    adapter.clear();
                    adapter.addAll(alCarpark); // Add new data
                    adapter.notifyDataSetChanged(); // Notify the adapter of data change
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }

        });
    }
}