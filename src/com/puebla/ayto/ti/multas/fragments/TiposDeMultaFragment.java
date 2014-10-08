package com.puebla.ayto.ti.multas.fragments;





import java.lang.reflect.Type;
import java.util.ArrayList;

import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;





import dataBase.AlertasDbAdapter;







import com.puebla.ayto.ti.multas.R;
import com.puebla.ayto.ti.multas.adapter.*;
import com.puebla.ayto.ti.multas.fragments.DetalleMultaFragment.MuestraDetalleFragment;
import com.puebla.ayto.ti.multas.objects.*;

public class TiposDeMultaFragment extends Fragment {
	
	
	
	private static final String TAG_ASYN_CONECTOR ="TAG_ASYNC_MULTAS";
	private static final String TAG_DEBUG ="TAG_DEBUG";
	private static final String TAG_RECUPERAR_DATOS ="TAG_RECUPERAR_DATOS";
	
	MultasPorTipoInter mCallBack;
	private AdapterElementos mAdaptadorTipo; 
	private AlertasDbAdapter DB;
	
	ArrayList<TiposDeMulta> mListaTipos;
	
	
	//private TextView txtFragmentDownload;
	private boolean searchCheck;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		DB = new AlertasDbAdapter(getActivity());
		
		DB.open();
		 mListaTipos = DB.buscaTiposDeMultasObjects();
		DB.close();
		
		mAdaptadorTipo = new AdapterElementos(getActivity(), mListaTipos);
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.contenedor_de_elementos, container, false);
		
		ListView mListView = (ListView) rootView.findViewById(R.id.list_tiposDeMulta);
		
		mListView.setAdapter(mAdaptadorTipo);
		mListView.setOnItemClickListener(new ListViewClickListener());
		//txtFragmentDownload = (TextView) rootView.findViewById(R.id.textView2);
		
//		String info = (verificaDatosDB()) ? "Si funciono" : "jeje No funciono";
		
		
		//txtFragmentDownload.setText("");
		
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT ));		
		return rootView;		
	}
	
	
	
	 private class ListViewClickListener implements ListView.OnItemClickListener {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int posision, long id) {          	        	
		    	    	
		    	Toast.makeText(getActivity(), "El elemento seleccionado es",Toast.LENGTH_LONG).show();	
		    	TiposDeMulta mTipos = mListaTipos.get(posision);
		    	mCallBack.TipoSeleccionado(mTipos.getId());
		    		    	
	        }
	    }

	/**
	 * Sección para la Interfaz 
	 * 
	 */
    
	public interface MultasPorTipoInter {
		 public void TipoSeleccionado(int id);
		 
	 }

	 @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        
	        // Nos aseguramos de que la actividad contenedora haya implementado la
	        // interfaz de retrollamada. Si no, lanzamos una excepción
	        try {
	        	mCallBack = (MultasPorTipoInter) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " debe implementar OnMultasSelectedListener");
	        }
	    }
    

}



