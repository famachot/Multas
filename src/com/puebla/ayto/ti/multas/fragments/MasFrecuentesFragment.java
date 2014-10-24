package com.puebla.ayto.ti.multas.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ListView;

import com.puebla.ayto.ti.multas.R;
import com.puebla.ayto.ti.multas.adapter.LasMasFrecuentesAdapter;
import com.puebla.ayto.ti.multas.objects.Multa;

import dataBase.AlertasDbAdapter;

public class MasFrecuentesFragment extends Fragment {


	private LasMasFrecuentesAdapter mAdaptadorMulta; 
	private AlertasDbAdapter DB;

	private OnMultasSelectedListener mCallback;
	ArrayList<Multa> mListaMulta;
	
	
	
	public MasFrecuentesFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
		
		DB = new AlertasDbAdapter(getActivity());
		
		DB.open();
		 mListaMulta = DB.buscaMultasFrecuentes(true);

		DB.close();
	

		mAdaptadorMulta = new LasMasFrecuentesAdapter(getActivity(), mListaMulta);
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.contenedor_de_elementos, container, false);
		
		ListView mListView = (ListView) rootView.findViewById(R.id.list_tiposDeMulta);
		mListView.setAdapter(mAdaptadorMulta);
		mListView.setOnItemClickListener(new ListViewClickListener() );
	
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT ));		
		return rootView;		
	}
	
	
	
	 private class ListViewClickListener implements ListView.OnItemClickListener {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int posision, long id) {          	        	

		    	Multa mMultaDatos = mListaMulta.get(posision);
		    	mCallback.onMultaSelected(mMultaDatos.getId() , mMultaDatos.getMulta(),
		    			mMultaDatos.getFundamento(), mMultaDatos.getRango_importe_ini(),
		    			mMultaDatos.getRango_importe_fin(), mMultaDatos.getFrecuente(), 
		    			mMultaDatos.getMulta_id());
		    	
		    	
		    		    	
	        }
	    }
	 
	 
	// Interface de los metodos a implementar en la Activity
	    public interface OnMultasSelectedListener {
	    	
	        public void onMultaSelected(int id, String infraccion, String fundamento, int ran_ini, int ran_fin, Boolean frecuente, int num_multa);
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
