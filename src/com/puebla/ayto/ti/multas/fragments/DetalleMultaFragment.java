package com.puebla.ayto.ti.multas.fragments;

import com.puebla.ayto.ti.multas.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetalleMultaFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.detalle_multa, container, false);
		
		TextView text_Infraccion = (TextView) rootView.findViewById(R.id.infraccion);
		TextView text_Fundamento = (TextView) rootView.findViewById(R.id.fundamento_legal);
		TextView text_DiasMulta = (TextView) rootView.findViewById(R.id.dias_multa);
		TextView text_CostoSalario = (TextView) rootView.findViewById(R.id.costo_salario);		
		TextView text_PrecioRango = (TextView) rootView.findViewById(R.id.precio_rango_costos);
		
		return rootView;
	}
}
