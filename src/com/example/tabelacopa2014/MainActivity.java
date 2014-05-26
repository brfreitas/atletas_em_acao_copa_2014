package com.example.tabelacopa2014;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	int mPosition = -1;	
	String mTitle = "";
	
	// Array of strings storing country names
    String[] mGroups ;  
   

    
	
	private DrawerLayout mDrawerLayout;	
	private ListView mDrawerList;	
	private ActionBarDrawerToggle mDrawerToggle;	
	private LinearLayout mDrawer ;	
	private List<HashMap<String,String>> mList ;	
	private SimpleAdapter mAdapter;	
	final private String DRAWER_ITEM_LABEL = "drawer_item_label";	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Getting an array of country names
		mGroups = getResources().getStringArray(R.array.groups);
		
		
		// Title of the activity
		mTitle = (String)getTitle();
		
		// Getting a reference to the drawer listview
		mDrawerList = (ListView) findViewById(R.id.drawer_list);
		
		// Getting a reference to the sidebar drawer ( Title + ListView )
		mDrawer = ( LinearLayout) findViewById(R.id.drawer);
		
		// Each row in the list stores country name, count and flag
        mList = new ArrayList<HashMap<String,String>>();

        
        for(int i=0;i<mGroups.length;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put(DRAWER_ITEM_LABEL, mGroups[i]);
            
            mList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { DRAWER_ITEM_LABEL };

        // Ids of views in listview_layout
        int[] to = { R.id.drawer_item_label };

        // Instantiating an adapter to store each items
        // R.layout.drawer_layout defines the layout of each item
        mAdapter = new SimpleAdapter(this, mList, R.layout.drawer_layout, from, to);
        
        // Getting reference to DrawerLayout
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);        
        
        // Creating a ToggleButton for NavigationDrawer with drawer event listener
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer , R.string.drawer_open,R.string.drawer_close){
        	
        	 /** Called when drawer is closed */
            public void onDrawerClosed(View view) {               
            	highlightSelectedCountry();            		
                supportInvalidateOptionsMenu();       
            }

            /** Called when a drawer is opened */
            public void onDrawerOpened(View drawerView) {            	
                getSupportActionBar().setTitle("Select a Country");            	
            	supportInvalidateOptionsMenu();                
            }
        };
        
        // Setting event listener for the drawer
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        
        // ItemClick event handler for the drawer items
        mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				// Increment hit count of the drawer list item
				incrementHitCount(position);			
				 
				if(position < 5) { // Show fragment for countries : 0 to 4				
					showFragment(position, R.id.radioMatch);
				}else{ // Show message box for countries : 5 to 9				
					Toast.makeText(getApplicationContext(), mGroups[position], Toast.LENGTH_LONG).show();
				}
				
				// Closing the drawer
				mDrawerLayout.closeDrawer(mDrawer);		
			}
		});
        
        
        // Enabling Up navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);     
        
        getSupportActionBar().setDisplayShowHomeEnabled(true);        

        // Setting the adapter to the listView
        mDrawerList.setAdapter(mAdapter);   
        
	}
	
	public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.radioClassification:
	            if (checked)
	            	showFragment(0, R.id.radioClassification);
	            break;
	        case R.id.radioMatch:
	            if (checked)
	            	showFragment(0, R.id.radioMatch);
	            break;
	    }
	}
	
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
	          return true;
	    }
		return super.onOptionsItemSelected(item);
	}	
			
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void incrementHitCount(int position){
		HashMap<String, String> item = mList.get(position);
//		String count = item.get(COUNT);
//		item.remove(COUNT);
//		if(count.equals("")){
//			count = "  1  ";
//		}else{
//			int cnt = Integer.parseInt(count.trim());
//			cnt ++;
//			count = "  " + cnt + "  ";
//		}				
//		item.put(COUNT, count);				
		mAdapter.notifyDataSetChanged();
	}
	
	public void showFragment(int position, int action){
		
		//Currently selected country
        mTitle = mGroups[position];	

        // Creating a fragment object
        GroupsFragment cFragment = new GroupsFragment();

        // Creating a Bundle object
        Bundle data = new Bundle();

        // Setting the index of the currently selected item of mDrawerList
        data.putInt("position", position);
        data.putInt("radioClicked", action);

        // Setting the position to the fragment
        cFragment.setArguments(data);

        // Getting reference to the FragmentManager
        FragmentManager fragmentManager  = getSupportFragmentManager();

        // Creating a fragment transaction
        FragmentTransaction ft = fragmentManager.beginTransaction();

        // Adding a fragment to the fragment transaction
        ft.replace(R.id.content_frame, cFragment);

        // Committing the transaction
        ft.commit();
	}
	
	// Highlight the selected country : 0 to 4
	public void highlightSelectedCountry(){
		int selectedItem = mDrawerList.getCheckedItemPosition();
    	
    	if(selectedItem > 4)
    		mDrawerList.setItemChecked(mPosition, true);
    	else
    		mPosition = selectedItem;
    	
    	if(mPosition!=-1)
    		getSupportActionBar().setTitle(mGroups[mPosition]);
	}	
}