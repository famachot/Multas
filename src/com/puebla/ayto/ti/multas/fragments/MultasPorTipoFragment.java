package com.puebla.ayto.ti.multas.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;

import com.puebla.ayto.ti.multas.R;

import com.puebla.ayto.ti.multas.adapter.MultasPorTipoAdapter;
import com.puebla.ayto.ti.multas.objects.Multa;

import dataBase.AlertasDbAdapter;

public class MultasPorTipoFragment extends Fragment{
	private AlertasDbAdapter DB;
	private MultasPorTipoAdapter mAdaptador;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		DB = new AlertasDbAdapter(getActivity());
		
		DB.open();
		ArrayList<Multa> mListaMulta = DB.RetornaMultasPorTipo(2);

		DB.close();

			
			
			

		mAdaptador = new MultasPorTipoAdapter(getActivity(), mListaMulta);
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.contenedor_de_elementos, container, false);
		
		ListView mListView = (ListView) rootView.findViewById(R.id.list_tiposDeMulta);
		
		mListView.setAdapter(mAdaptador);

		
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT ));		
		return rootView;		
	}
}
