package com.puebla.ayto.ti.multas.adapter;

import java.util.ArrayList;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import android.widget.TextView;


import com.puebla.ayto.ti.multas.objects.TiposDeMulta;
import com.puebla.ayto.ti.multas.R;

public class AdapterElementos extends BaseAdapter {

	private ViewHolder holder;	
	
	private Activity mActivity;
	private ArrayList<TiposDeMulta> listaTipos;
	
	public AdapterElementos (Activity mActivity, ArrayList<TiposDeMulta> listaTipos){
		this.mActivity = mActivity;
		this.listaTipos = listaTipos; 
	}
	
	
	public View getView(int position, View convertView,ViewGroup parent){
		
		holder = null;
		View view = convertView;
		final TiposDeMulta tipoDeMulta = listaTipos.get(position);
		
		if(view == null) {
			view = LayoutInflater.from(mActivity).inflate(R.layout.tipo_de_multa_item, null);
			TextView id = (TextView) view.findViewById(R.id.text_id);
			TextView tipo = (TextView) view.findViewById(R.id.text_tipo_multa);
			view.setTag(new ViewHolder(id, tipo));
		}
		
		if (holder == null && view != null) {
			Object tag = view.getTag();
			if (tag instanceof ViewHolder) {
				holder = (ViewHolder) tag;
			}
		}

		if (holder != null) {
			if (holder.Id_tipo != null){
				holder.Id_tipo.setText(tipoDeMulta.getTip_multa());
				//holder.Id_tipo.setTextSize(20);
			}
			
			if (holder.tipo != null){
				holder.tipo.setText(tipoDeMulta.getDescripcion());
				//holder.Id_tipo.setTextSize(20);
			}	
		}
		return view;
	}
	
	
	public int getCount(){
		return listaTipos.size();
	}
	
	public Object getItem(int arg0){
		return	listaTipos.get(arg0);
	}
	
	public long getItemId(int position){
		return position;
	}

	
	
	public static class ViewHolder {		
		public final TextView Id_tipo;
		public final TextView tipo;		
		public ViewHolder(TextView Id_tipo, TextView tipo) {
			this.Id_tipo = Id_tipo;
			this.tipo = tipo;
		}
	}
	
	
}
