package com.puebla.ayto.ti.multas.fragments;





import java.lang.reflect.Type;
import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;





import com.puebla.ayto.ti.multas.R;


public class LasMasFrecuentesFragment extends Fragment {
	
	
	
	private static final String TAG_ASYN_CONECTOR ="TAG_ASYNC_MULTAS";
	private static final String TAG_DEBUG ="TAG_DEBUG";
	private static final String TAG_RECUPERAR_DATOS ="TAG_RECUPERAR_DATOS";
	
	
	private TextView txtFragmentDownload;
	private boolean searchCheck;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.multas_fragment, container, false);
		
		txtFragmentDownload = (TextView) rootView.findViewById(R.id.textView2);
		
//		String info = (verificaDatosDB()) ? "Si funciono" : "jeje No funciono";
		
		
		txtFragmentDownload.setText("");
		
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT ));		
		return rootView;		
	}
	
	

	
	

	
	
	

	

	
	
	


	
	
	
	
	

    
	
	
	

    
    

    
    

}



