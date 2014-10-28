package com.puebla.ayto.ti.multas.adapter;
import java.util.ArrayList;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.puebla.ayto.ti.multas.R;

import com.puebla.ayto.ti.multas.objects.Multa;

public class MultasPorGrupoAdapter extends ArrayAdapter<Multa> {
	
	private ViewHolder holder;
	private static final String AdapterPorGrupo = "AdapterPorGrupo"; 

		public MultasPorGrupoAdapter(Activity mContext, ArrayList<Multa> mMulta) {
			super(mContext, R.layout.item_multa_frecuente , mMulta);
			
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			holder = null;
			View view = convertView;
			
			final Multa mMulta = getItem(position);
			Log.d(AdapterPorGrupo, "se acaba de otener un item ->" + mMulta.getMulta());

			if(view == null) {
				
				view = LayoutInflater.from(getContext()).inflate(R.layout.item_multa_frecuente, null);
		        
				TextView infraccion = (TextView) view.findViewById(R.id.text_infraccion_frecuente);
				TextView frecuente = (TextView) view.findViewById(R.id.text_incidencia);
				TextView frecuente_id = (TextView) view.findViewById(R.id.text_num_incidencia);
				TextView ver_mas = (TextView) view.findViewById(R.id.ver_mas);
				ImageView icon_image = (ImageView) view.findViewById(R.id.img_item_frecuente);
				
				view.setTag(new ViewHolder(icon_image, infraccion,frecuente,frecuente_id,ver_mas));
				
			}
			
			if (holder == null && view != null) {
				Object tag = view.getTag();
				if (tag instanceof ViewHolder) {
					holder = (ViewHolder) tag;
				}
			}
			
			if (holder != null) {
				
				if (holder.icono_frecuente != null){
					holder.icono_frecuente.setImageResource(LasMasFrecuentesAdapter.id_icono_frecuente(mMulta.getMulta_id()));
				}
				
				if (holder.infraccion != null) {
					holder.infraccion.setText(mMulta.getMulta());
				}
				
				//holder.txt_frecuente.setVisibility(View.GONE);
				holder.num_frecuencia.setVisibility(View.GONE);
				holder.ver_mas.setVisibility(View.GONE);
			}
			
			return view;
			
		}
	
	
		
		
		public static class ViewHolder {	
			public final ImageView icono_frecuente;
			public final TextView infraccion;
			public final TextView txt_frecuente;
			public final TextView num_frecuencia;
			public final TextView ver_mas;
			public ViewHolder(ImageView icono_frecuente,TextView infraccion, TextView txt_frecuente,TextView num_frecuencia, TextView ver_mas) {
				this.icono_frecuente = icono_frecuente;
				this.infraccion = infraccion;
				this.txt_frecuente = txt_frecuente;
				this.num_frecuencia = num_frecuencia;
				this.ver_mas = ver_mas;
				
			}
		}
}
