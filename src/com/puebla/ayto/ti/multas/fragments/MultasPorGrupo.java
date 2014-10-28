package com.puebla.ayto.ti.multas.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.puebla.ayto.ti.multas.R;
import com.puebla.ayto.ti.multas.adapter.MultasPorGrupoAdapter;
import com.puebla.ayto.ti.multas.objects.Multa;

import dataBase.AlertasDbAdapter;

public class MultasPorGrupo extends Fragment{

	AlertasDbAdapter DB;
	private  MultasPorGrupoAdapter mAdaptadorMulta; 
	private OnMultasGrupoSelected mCallback;
	private static final String MultasporGrupo = "MultasporGrupo";
	
	public MultasPorGrupo() {}
	
	 public static MultasPorGrupo newInstance(Bundle arguments){
		 MultasPorGrupo f = new MultasPorGrupo();
	        if(arguments != null){
	            f.setArguments(arguments);
	        }
	        return f;
	    }
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
		
	
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.contenedor_de_elementos, container, false);
		ListView mListView = (ListView) rootView.findViewById(R.id.list_tiposDeMulta);
		TextView titulo = (TextView) rootView.findViewById(R.id.txt_texto_multa);
		titulo.setVisibility(View.VISIBLE);
		
		if(getArguments() != null)
			if (getArguments().getInt("multa_id") == 26 )
				titulo.setText(R.string.item_26);
			else
				if (getArguments().getInt("multa_id") == 6 )
					titulo.setText(R.string.item_6);
				else
					if (getArguments().getInt("multa_id") == 87 )
						titulo.setText(R.string.item_87);
		
		
		mAdaptadorMulta = new MultasPorGrupoAdapter(getActivity(), new ArrayList());
		
		
		mListView.setAdapter(mAdaptadorMulta);
		mListView.setOnItemClickListener(new ListViewClickListener() );
	
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT ));
		
		if (getArguments() != null ) {
			Log.d(MultasporGrupo, "Antes de actualizar el dataadapter del listview");
			buscarMultas(mAdaptadorMulta, getArguments().getInt("multa_id"));
		}
		return rootView;		
	}
	
	

	
	public void buscarMultas(MultasPorGrupoAdapter mAdaptadorMulta, int id) {
		Log.d(MultasporGrupo, "Entrando a buscarMultas");
		DB = new AlertasDbAdapter(getActivity());
		
			DB.open();
			ArrayList<Multa> multas = DB.multasPorGrupo(id);
			if (multas.size() > 0) {
				Log.d(MultasporGrupo, "Se obtuvieron " + multas.size() + ", datos");
				mAdaptadorMulta.addAll(multas);
				mAdaptadorMulta.notifyDataSetChanged();
			}
				
			
			DB.close();
		
		try {
				
		
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			Log.d(MultasporGrupo, "Error: " + e.getMessage());
		}
		
	}
	
	
	
	
	
	 private class ListViewClickListener implements ListView.OnItemClickListener {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int posision, long id) {          	        	
	        	Multa mMultaDatos = mAdaptadorMulta.getItem(posision);
	        	mCallback.onMultaSelecGrupoItem(mMultaDatos.getId(), mMultaDatos.getMulta(),
		    			mMultaDatos.getFundamento(), mMultaDatos.getRango_importe_ini(),
		    			mMultaDatos.getRango_importe_fin(), mMultaDatos.getFrecuente(), 
		    			mMultaDatos.getMulta_id());
	        	Toast.makeText(getActivity(), "Se presiono para acceer al detalle", Toast.LENGTH_SHORT).show();
	        }
	    }
	 
	 
	 
		// Interface de los metodos a implementar en la Activity
	    public interface OnMultasGrupoSelected {
	        public void onMultaSelecGrupoItem(int id, String infraccion, String fundamento, int ran_ini, int ran_fin, Boolean frecuente, int num_multa);
	    }
	   
	    
	    
	    @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        
	        // Nos aseguramos de que la actividad contenedora haya implementado la
	        // interfaz de retrollamada. Si no, lanzamos una excepción
	        try {
	            mCallback = (OnMultasGrupoSelected) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " debe implementar OnMultasSelectedListener");
	        }
	    }
	
}
