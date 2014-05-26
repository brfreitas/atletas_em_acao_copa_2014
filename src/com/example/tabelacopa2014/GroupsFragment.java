package com.example.tabelacopa2014;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;

import com.example.tabelacopa2014.Contants.Flags;
import com.example.tabelacopa2014.adapter.ClassificationAdapter;

public class GroupsFragment extends Fragment{
	
	private static final String DATA_HORA = "data_hora_jogo";
	private static final String LOCAL_JOGO = "local_jogo";
	private static final String LEFT_TEAM_NAME = "left_team_name";
	private static final String LEFT_TEAM_FLAG = "left_team_flag";
	private static final String LEFT_TEAM_GOL = "left_team_gol";
	private static final String RIGHT_TEAM_GOL = "right_team_gol";
	private static final String RIGHT_TEAM_FLAG = "right_team_flag";
	private static final String RIGHT_TEAM_NAME = "right_team_name";
	
	
	private static final String CLASSIFICATION_POSITION = "classification_position";
	private static final String CLASSIFICATION_SELECTION_NAME = "classification_selection_name";
	private static final String CLASSIFICATION_DIVIDER = "classification_divider";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		// Retrieving the currently selected item number
		int position = getArguments().getInt("position");
		int action = getArguments().getInt("radioClicked");
		
		// Creating view correspoding to the fragment
		View v = inflater.inflate(R.layout.fragment_layout, container, false);
		
		ListView mMatchesList = (ListView) v.findViewById(R.id.matches_list);
		RadioButton radioAtual = (RadioButton) v.findViewById(action); 
		radioAtual.setChecked(true);
		
		
		if(action == R.id.radioMatch){
			// List of rivers
			String[] groups = getResources().getStringArray(R.array.groups);	
			
			ArrayList<HashMap<String,String>> mList = new ArrayList<HashMap<String,String>>();
			for(int i=0;i<10;i++){
	            HashMap<String, String> hm = new HashMap<String,String>();
	            hm.put(DATA_HORA, "Qui 12/06 - 17h00");
	            hm.put(LOCAL_JOGO, "Itaquerão");    
	            hm.put(LEFT_TEAM_NAME, "BRA");    
	            hm.put(LEFT_TEAM_FLAG, Integer.toString(Flags.BRAZIL.getResourceId()));    
	            hm.put(LEFT_TEAM_GOL, "2");    
	            hm.put(RIGHT_TEAM_GOL, "1");    
	            hm.put(RIGHT_TEAM_FLAG, Integer.toString(Flags.BRAZIL.getResourceId()));
	            hm.put(RIGHT_TEAM_NAME, "CRO");            
	            mList.add(hm);
	        }
			String[] from = { DATA_HORA, LOCAL_JOGO, LEFT_TEAM_NAME,LEFT_TEAM_FLAG, LEFT_TEAM_GOL, RIGHT_TEAM_GOL, RIGHT_TEAM_FLAG, RIGHT_TEAM_NAME};
			int[] to = { R.id.data_hora_jogo, R.id.local_jogo, R.id.left_team_name, R.id.left_team_flag, R.id.left_team_gol, R.id.right_team_gol, R.id.right_team_flag, R.id.right_team_name};
			
			SimpleAdapter mAdapter = new SimpleAdapter(v.getContext(), mList, R.layout.match_information, from, to);
			
			mMatchesList.setAdapter(mAdapter);
		}
		else{
			

			ArrayList<HashMap<String,String>> mList = new ArrayList<HashMap<String,String>>();
			for(int i=0;i<4;i++){
	            HashMap<String, String> hm = new HashMap<String,String>();
	            hm.put(CLASSIFICATION_POSITION, Integer.toString(i+1));
	            hm.put(CLASSIFICATION_SELECTION_NAME, "Brasil");    
	                        
	            mList.add(hm);
	        }
			String[] from = { CLASSIFICATION_POSITION, CLASSIFICATION_SELECTION_NAME};
			int[] to = { R.id.classification_position, R.id.classification_selection_name};
			
			ClassificationAdapter mAdapter = new ClassificationAdapter(v.getContext(), mList, R.layout.classification, from, to);			
			mMatchesList.setAdapter(mAdapter);
			
		}
		// Getting reference to the TextView of the Fragment
		//TextView tv = (TextView) v.findViewById(R.id.tv_content);
		
		// Setting currently selected river name in the TextView
		//tv.setText(countries[position]);
		
		return v;
	}
}