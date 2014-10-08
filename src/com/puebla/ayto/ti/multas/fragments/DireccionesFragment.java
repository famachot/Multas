package com.puebla.ayto.ti.multas.fragments;

import java.util.ArrayList;
import java.util.Vector;

import com.puebla.ayto.ti.multas.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;

import com.puebla.ayto.ti.multas.adapter.DireccionesAdapter;;

public class DireccionesFragment extends Fragment {
	
	private ArrayList<String []> mDireccionesList = new ArrayList<String[]>();
	private DireccionesAdapter mAdaptador;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		mDireccionesList.add(new String[] {"Ex - Acuario","Lun a Vier - 8:00 a 15:00 hrs.","11 Pte. Esq. 13 Sur Paseo Bravo"});
		mDireccionesList.add(new String[] {"Tesorería Municipal","Lun a Vier - 8:00 a 15:00 hrs.","Reforma 126 Centro"});
		mDireccionesList.add(new String[] {"Mayorazgo","Lun a Vier - 8:00 a 15:00 hrs.","15 A Sur No. 7738 Mayorazgo"});
		mDireccionesList.add(new String[] {"San Manuel","Lun a Vier - 8:00 a 15:00 hrs.","Rio Papagayo 5310 San Manuel"});
		mDireccionesList.add(new String[] {"Amalucán","Lun a Vier - 8:00 a 15:00 hrs.","And. de las Torres No. 18 Amalucán"});
		mDireccionesList.add(new String[] {"Panteón Municipal","Lun a Vier - 8:00 a 15:00 hrs. ","11 Sur y 35 Pte."});
		mDireccionesList.add(new String[] {"Obras Públicas","Lun a Vier - 8:00 a 15:00 hrs.","Prolongación Reforma 3308 Col. Amor"});
		mDireccionesList.add(new String[] {"C.A.M*","Lun a Vier - 8:00 a 20:00 hrs.","4 Poniente Esq. 11 norte"});
		mDireccionesList.add(new String[] {"Plaza Molino del Cristo","Lun a Vier - 8:00 a 14:00 hrs. ","Blvd. Vicente Suarez No. 1011-A Esq. Av. 14 Ote. Col. El Cristo(Local 27)"});
		mDireccionesList.add(new String[] {"Comercio informal ","Lun a Vier - 8:00 a 14:00 hrs.","3 Poniente 116 Colonia Centro"});
		mDireccionesList.add(new String[] {"Sindicatura","Lun a Vier - 8:00 a 14:00 hrs.","4 Poniente No. 604 Colonia Centro"});
		
		
		mAdaptador = new DireccionesAdapter(getActivity(), mDireccionesList);
		
		View rootView = inflater.inflate(R.layout.lugares_de_pago_list, container, false);
		
		ListView mListView = (ListView) rootView.findViewById(R.id.list_lugares_pago);
		
		mListView.setAdapter(mAdaptador);
		
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT ));
		return rootView;
	}
	

}
