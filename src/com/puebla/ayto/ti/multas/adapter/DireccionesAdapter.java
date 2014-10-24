package com.puebla.ayto.ti.multas.adapter;

import java.util.ArrayList;


import com.puebla.ayto.ti.multas.R;






import android.app.Activity;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;




public class DireccionesAdapter extends BaseAdapter {
	private ArrayList<String []> listDireccion; 
	private Activity mActivity;
	private ViewHolder holder;	

	
	
	
	public DireccionesAdapter (Activity mActivity,ArrayList<String []> listDireccion){
		this.mActivity = mActivity;
		this.listDireccion = listDireccion; 
	}
	
	
public View getView(int position, View convertView,ViewGroup parent){
		
		holder = null;
		View view = convertView;

	 final String [] mDatos =  listDireccion.get(position);
		
		if(view == null) {
			view = LayoutInflater.from(mActivity).inflate(R.layout.direcciones_pago, null);
			TextView txt_ubicacion = (TextView) view.findViewById(R.id.ubicacion);
			TextView txt_horario = (TextView) view.findViewById(R.id.horario);
			TextView txt_direccion = (TextView) view.findViewById(R.id.direccion);

			
			
			
			//ImageView icon_image = (ImageView) view.findViewById(R.id.img_item_frecuente);
			view.setTag(new ViewHolder(txt_ubicacion, txt_horario,txt_direccion));
		}
		
		
		
		if (holder == null && view != null) {
			Object tag = view.getTag();
			if (tag instanceof ViewHolder) {
				holder = (ViewHolder) tag;
				
			}
		}

		
		
		if (holder != null) {

			if (holder.txt_ubicacion != null){
				
				holder.txt_ubicacion.setText(mDatos[0]);
				//holder.Id_tipo.setTextSize(20);
			}
			
			if (holder.txt_horario != null){
				
				holder.txt_horario.setText(mDatos[1]);
				//holder.Id_tipo.setTextSize(20);
				//holder.infraccion.setText("Hola");
			}
			
			if (holder.txt_direccion != null){
				holder.txt_direccion.setText(mDatos[2]);
				
				//holder.Id_tipo.setTextSize(20);
			}
			

			
		
			
		}
		return view;
	}
	
	public int getCount(){
		return listDireccion.size();
	}
	
	public Object getItem(int arg0){
		return	listDireccion.get(arg0);
	}
	
	public long getItemId(int position){
		return position;
	}
	
	
	public static class ViewHolder {	

		public final TextView txt_ubicacion;
		public final TextView txt_horario;
		public final TextView txt_direccion;
		
		public ViewHolder(TextView txt_ubicacion, TextView txt_horario,TextView txt_direccion) {
			this.txt_ubicacion = txt_ubicacion;
			this.txt_horario = txt_horario;
			this.txt_direccion = txt_direccion;
			
			
		}
	}
}
