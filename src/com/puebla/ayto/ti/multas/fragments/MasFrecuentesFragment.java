package com.puebla.ayto.ti.multas.fragments;

import java.util.ArrayList;
import java.util.Iterator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;

import com.puebla.ayto.ti.multas.R;
import com.puebla.ayto.ti.multas.adapter.LasMasFrecuentesAdapter;
import com.puebla.ayto.ti.multas.objects.Multa;

import dataBase.AlertasDbAdapter;

public class MasFrecuentesFragment extends Fragment {


	private LasMasFrecuentesAdapter mAdaptadorMulta; 
	private AlertasDbAdapter DB;
	private Multa mMulta;
	
	
	public MasFrecuentesFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		DB = new AlertasDbAdapter(getActivity());
		
		DB.open();
		ArrayList<Multa> mListaMulta = DB.buscaMultasFrecuentes(true);
		//ArrayList<Multa> mListaMulta_cp = new ArrayList<Multa>();
		DB.close();
		//boolean elemento_26 = false;
	//	boolean elemento_6 = false;

		

		/*
		for(int x=0; x < mListaMulta.size(); x++) {
			
			if (mListaMulta.get(x).getMulta_id() == 26) {
				if (elemento_26 == false) {
					mListaMulta_cp.add(mListaMulta.get(x));
					elemento_26 = true;
				}
			}else {
				if (mListaMulta.get(x).getMulta_id() == 6) {
					if (elemento_6 == false) {
						mListaMulta_cp.add(mListaMulta.get(x));
						elemento_6 = true;
					}
				}else {
					mListaMulta_cp.add(mListaMulta.get(x));
				}
			}
			
			
			
			
			
			
			
			Log.d("Debug_Fragment", "Los datos almacenados en la BD son (Objetos) ID: " + mListaMulta.get(x).getId() + ", Infraccion -> " + mListaMulta.get(x).getMulta());
		  }*/
		mAdaptadorMulta = new LasMasFrecuentesAdapter(getActivity(), mListaMulta);
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.contenedor_de_elementos, container, false);
		
		ListView mListView = (ListView) rootView.findViewById(R.id.list_tiposDeMulta);
		
		mListView.setAdapter(mAdaptadorMulta);
		//txtFragmentDownload = (TextView) rootView.findViewById(R.id.textView2);
		
//		String info = (verificaDatosDB()) ? "Si funciono" : "jeje No funciono";
		
		
		//txtFragmentDownload.setText("");
		
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT ));		
		return rootView;		
	}
	

}
