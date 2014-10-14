package com.puebla.ayto.ti.multas.fragments;

import java.util.ArrayList;
import java.util.Locale;

import android.content.Intent;
import android.net.Uri;
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
import com.puebla.ayto.ti.multas.adapter.DireccionesAdapter;



public class DireccionesFragment extends Fragment {
	
	private ArrayList<String []> mDireccionesList = new ArrayList<String[]>();
	private DireccionesAdapter mAdaptador;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		mDireccionesList.add(new String[] {"Ex - Acuario","Lun a Vier - 8:00 a 15:00 hrs.","11 Pte. Esq. 13 Sur Paseo Bravo", "19.045329", "-98.208853" }); //19.045329, -98.208853
		mDireccionesList.add(new String[] {"Tesorería Municipal","Lun a Vier - 8:00 a 15:00 hrs.","Reforma 126 Centro","19.044964", "-98.199363"});//19.044964, -98.199363
		mDireccionesList.add(new String[] {"Mayorazgo","Lun a Vier - 8:00 a 15:00 hrs.","15 A Sur No. 7738 Mayorazgo","19.008869","-98.234003"});//19.008869, -98.234003
		mDireccionesList.add(new String[] {"San Manuel","Lun a Vier - 8:00 a 15:00 hrs.","Rio Papagayo 5310 San Manuel","19.013201","-98.193002"});//19.013201, -98.193002
		mDireccionesList.add(new String[] {"Amalucán","Lun a Vier - 8:00 a 15:00 hrs.","And. de las Torres No. 18 Amalucán","19.051420","-98.147276"});//19.051420, -98.147276
		mDireccionesList.add(new String[] {"Panteón Municipal","Lun a Vier - 8:00 a 15:00 hrs. ","11 Sur y 35 Pte.","19.034637","-98.214366"});//19.034637, -98.214366
		mDireccionesList.add(new String[] {"Obras Públicas","Lun a Vier - 8:00 a 15:00 hrs.","Prolongación Reforma 3308 Col. Amor","19.054322","-98.217614"});//19.054322, -98.217614
		mDireccionesList.add(new String[] {"C.A.M*","Lun a Vier - 8:00 a 20:00 hrs.","4 Poniente Esq. 11 norte","19.049925","-98.205116"});//19.049925, -98.205116
		mDireccionesList.add(new String[] {"Plaza Molino del Cristo","Lun a Vier - 8:00 a 14:00 hrs. ","Blvd. Vicente Suarez No. 1011-A Esq. Av. 14 Ote. Col. El Cristo(Local 27)","19.043590","-98.164323"});//19.043590, -98.164323
		mDireccionesList.add(new String[] {"Comercio informal ","Lun a Vier - 8:00 a 14:00 hrs.","3 Poniente 116 Colonia Centro","19.044201","-98.199972"});//19.044201, -98.199972
		mDireccionesList.add(new String[] {"Sindicatura","Lun a Vier - 8:00 a 14:00 hrs.","4 Poniente No. 604 Colonia Centro","19.048266","-98.202120"});//19.048266, -98.202120
		
		
		mAdaptador = new DireccionesAdapter(getActivity(), mDireccionesList);
		
		View rootView = inflater.inflate(R.layout.lugares_de_pago_list, container, false);
		
		ListView mListView = (ListView) rootView.findViewById(R.id.list_lugares_pago);
		
		mListView.setAdapter(mAdaptador);
		
		mListView.setOnItemClickListener(new ListViewClickListenerG() );
		
		
		
		
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT ));
		return rootView;
	}
	
	
	

	 private class ListViewClickListenerG implements ListView.OnItemClickListener {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int posision, long id) {          	        	
		    	    	
		    	
		    	String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Double.parseDouble(mDireccionesList.get(posision)[3]), Double.parseDouble(mDireccionesList.get(posision)[4]));
		    	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
		    	getActivity().startActivity(intent);
		    	
		    	Log.d("NNN","URI: " + uri);
		    		    	
	        }
	    }
	 
	
	
	
	

}
