package com.puebla.ayto.ti.multas.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.puebla.ayto.ti.multas.R;
import com.puebla.ayto.ti.multas.adapter.LasMasFrecuentesAdapter;
import com.puebla.ayto.ti.multas.objects.Multa;

import dataBase.AlertasDbAdapter;

public class MasFrecuentesFragment extends Fragment {


	private LasMasFrecuentesAdapter mAdaptadorMulta; 
	private AlertasDbAdapter DB;
	private Multa mMulta;
	private OnMultasSelectedListener mCallback;
	
	public MasFrecuentesFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		DB = new AlertasDbAdapter(getActivity());
		
		DB.open();
		ArrayList<Multa> mListaMulta = DB.buscaMultasFrecuentes(true);

		DB.close();

		mAdaptadorMulta = new LasMasFrecuentesAdapter(getActivity(), mListaMulta);
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.contenedor_de_elementos, container, false);
		
		ListView mListView = (ListView) rootView.findViewById(R.id.list_tiposDeMulta);
		
		mListView.setAdapter(mAdaptadorMulta);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
            	    int position, long id) {
            		
            			  
            	   Toast.makeText(getActivity(),"Click ListItem Number " + position, Toast.LENGTH_LONG).show();
            	  }

		});
		
		//txtFragmentDownload = (TextView) rootView.findViewById(R.id.textView2);
		
//		String info = (verificaDatosDB()) ? "Si funciono" : "jeje No funciono";
		
		
		//txtFragmentDownload.setText("");
		
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT ));		
		return rootView;		
	}
	
	
	
	 private class ListViewClickListener implements ListView.OnItemClickListener {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {          	        	
		    	    	
		    	Toast.makeText(getActivity(), "El elemento seleccionado es",Toast.LENGTH_LONG).show();	    	
		    		    	
	        }
	    }
	 
	 
	// Interface de los metodos a implementar en la Activity
	    public interface OnMultasSelectedListener {
	        public void onMultaSelected(int position);
	    }
	    
	    
	    @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        
	        // Nos aseguramos de que la actividad contenedora haya implementado la
	        // interfaz de retrollamada. Si no, lanzamos una excepción
	        try {
	            mCallback = (OnMultasSelectedListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " debe implementar OnMultasSelectedListener");
	        }
	    }
	

}
