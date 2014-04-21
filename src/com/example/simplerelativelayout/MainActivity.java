package com.example.simplerelativelayout;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
    	
    	private Spinner month;
    	private Spinner date;
    	private final static int[] LIMITS = new int[] { 31, 28, 31, 30, 31, 30,
			31, 31, 30, 31, 30, 31 };

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            
            month = (Spinner) rootView.findViewById(R.id.month);
            date = (Spinner) rootView.findViewById(R.id.date);
            
            String[] list_month = getResources().getStringArray(R.array.monthEng);
            ArrayAdapter<String> adapter_month = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list_month);
            
            
            final int[] limits = getResources().getIntArray(R.array.dateMax);
            month.setAdapter(adapter_month);
            month.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
					setDateSpinner(limits[position]);
					//setDateSpinner(LIMITS[position]);
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});           
            
            return rootView;
        }

		private void setDateSpinner(int limit) {
            String[] list_date = new String[limit];
            for (int i = 0; i < limit; i++) {
            	list_date[i] = String.valueOf(i + 1);
            }
            
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list_date);
            date.setAdapter(adapter2);
		}
    }

}
