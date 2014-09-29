package com.puebla.ayto.ti.multas.adapter;

import java.util.ArrayList;

import com.puebla.ayto.ti.multas.R;
import com.puebla.ayto.ti.multas.adapter.LasMasFrecuentesAdapter.ViewHolder;
import com.puebla.ayto.ti.multas.objects.Multa;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MultasPorTipoAdapter extends BaseAdapter {

	private ViewHolder holder;	

	private Activity mActivity;
	private ArrayList<Multa> listaMulta;
	
	public MultasPorTipoAdapter (Activity mActivity, ArrayList<Multa> listaMulta){
		this.mActivity = mActivity;
		this.listaMulta = listaMulta; 
	}
	
	
	
	
public View getView(int position, View convertView,ViewGroup parent){
		
		holder = null;
		View view = convertView;
		final Multa mMulta = listaMulta.get(position);
		
		if(view == null) {
			view = LayoutInflater.from(mActivity).inflate(R.layout.item_multa_tipo, null);
			TextView infraccion = (TextView) view.findViewById(R.id.text_infraccion_frecuente);
			TextView frecuente = (TextView) view.findViewById(R.id.text_incidencia);

			view.setTag(new ViewHolder(infraccion,frecuente));
		}
		
		if (holder == null && view != null) {
			Object tag = view.getTag();
			if (tag instanceof ViewHolder) {
				holder = (ViewHolder) tag;
			}
		}

		if (holder != null) {
		
			
			if (holder.infraccion != null){
				if(mMulta.getMulta_id() == 26)
					holder.infraccion.setText(R.string.item_26);
					else 
						if (mMulta.getMulta_id() == 6)
							holder.infraccion.setText(R.string.item_6);
						else
							if (mMulta.getMulta_id() == 87)
								holder.infraccion.setText(R.string.item_87);
							else
								holder.infraccion.setText(infraccion_text(mMulta.getMulta()));
			}
			
			if(verificaFrecuente(mMulta.getMulta_id())){
				if (holder.txt_frecuente != null){
					holder.txt_frecuente.setText( "Frecuente: " + Integer.toString(mMulta.getFrecuencia()));
					holder.txt_frecuente.setVisibility(View.VISIBLE);
					
				}
			}	
		}
		return view;
	}
	
	

	private boolean verificaFrecuente(int id) {
		switch(id) {
		case 58:
			return true;
		case 26:
			return true;
		case 67:
			return true;
		case 6:
			return true;
		case 7:
			return true;
		case 11:
			return true;
		case 61:
			return true;
		case 55:
			return true;
		case 36:
			return true;
		case 124:
			return true;
		case 52:
			return true;
		case 32:
			return true;
		case 33:
			return true;
		case 45:
			return true;
		case 46:
			return true;
		case 65:
			return true;
		case 70:
			return true;
		case 92:
			return true;
		case 49:
			return true;
		default: 
		 	return false;
		}
		
	}
	

	private String infraccion_text(String txt) {
		return (txt.length()> 250) ? txt.substring(0, 247) + "..." : txt;
	}

	public int getCount(){
		return listaMulta.size();
	}
	
	public Object getItem(int arg0){
		return	listaMulta.get(arg0);
	}
	
	public long getItemId(int position){
		return position;
	}
	
	public static class ViewHolder {	

		public final TextView infraccion;
		public final TextView txt_frecuente;
		
		public ViewHolder(TextView infraccion, TextView txt_frecuente) {
			this.infraccion = infraccion;
			this.txt_frecuente = txt_frecuente;
		}
	}
	
}
