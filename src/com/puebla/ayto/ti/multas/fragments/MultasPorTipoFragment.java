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
import android.widget.Toast;

import com.puebla.ayto.ti.multas.R;
import com.puebla.ayto.ti.multas.adapter.MultasPorTipoAdapter;
import com.puebla.ayto.ti.multas.objects.Multa;

import dataBase.AlertasDbAdapter;

public class MultasPorTipoFragment extends Fragment{
	private AlertasDbAdapter DB;
	private MultasPorTipoAdapter mAdaptador;
	ArrayList<Multa> mListaMulta;
	
	OnMultasSelectedTipo mCallback;
	
	final String TAG_FRECUENTES = "TAG_FRECUENTES";
	
	 public MultasPorTipoFragment() {
		// TODO Auto-generated constructor stub
	}
	
	 public static MultasPorTipoFragment newInstance(Bundle arguments){
		 MultasPorTipoFragment f = new MultasPorTipoFragment();
	        if(arguments != null){
	            f.setArguments(arguments);
	        }
	        return f;
	    }
	 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		DB = new AlertasDbAdapter(getActivity());
		if (getArguments() != null) {
		DB.open();
		 mListaMulta = DB.RetornaMultasPorTipo(getArguments().getInt("id"));

		DB.close();	
		}else {mListaMulta = null;}
		
		
		
		for (int x = 0; x < mListaMulta.size(); x++) {
			
			Log.d(TAG_FRECUENTES, "Multa: " + mListaMulta.get(x).getMulta().substring(0, 20) + ", Frecuente: " +  mListaMulta.get(x).getFrecuente() + 
					",  Multa_id: " + mListaMulta.get(x).getMulta_id());
		}
		

			
			
			

		mAdaptador = new MultasPorTipoAdapter(getActivity(), mListaMulta);
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.contenedor_de_elementos, container, false);
		
		ListView mListView = (ListView) rootView.findViewById(R.id.list_tiposDeMulta);
		
		mListView.setAdapter(mAdaptador);
		mListView.setOnItemClickListener(new ListViewClickListener());

		
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT ));		
		return rootView;		
	}
	
	
	 private class ListViewClickListener implements ListView.OnItemClickListener {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int posision, long id) {          	        	
		    	    	
		    	Toast.makeText(getActivity(), "El elemento seleccionado es",Toast.LENGTH_LONG).show();	
		    	Multa mMultaDatos = mListaMulta.get(posision);
		    	mCallback.onMultaSelectedTipo(mMultaDatos.getId() , mMultaDatos.getMulta(),
		    			mMultaDatos.getFundamento(), mMultaDatos.getRango_importe_ini(),
		    			mMultaDatos.getRango_importe_fin(), mMultaDatos.getFrecuente(), 
		    			mMultaDatos.getMulta_id());
		    		    	
	        }
	    }
	
	
	// Interface de los metodos a implementar en la Activity
    public interface OnMultasSelectedTipo {
    	
        public void onMultaSelectedTipo(int id, String infraccion, String fundamento, int ran_ini, int ran_fin, Boolean frecuente, int num_multa);
    }
    
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        // Nos aseguramos de que la actividad contenedora haya implementado la
        // interfaz de retrollamada. Si no, lanzamos una excepción
        try {
            mCallback = (OnMultasSelectedTipo) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " debe implementar OnMultasSelectedListener");
        }
    }
	
}
