package com.example.tabelacopa2014.adapter;

import java.util.List;
import java.util.Map;

import com.example.tabelacopa2014.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

public class ClassificationAdapter extends SimpleAdapter{

	public ClassificationAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		View divider = (View) view.findViewById(R.id.classification_divider);
		if(position<2)
		divider.setBackgroundColor(view.getResources().getColor(R.color.classification_divider_classified));		

		return view;
	}

}
