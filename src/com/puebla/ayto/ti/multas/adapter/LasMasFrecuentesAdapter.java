package com.puebla.ayto.ti.multas.adapter;

import java.util.ArrayList;











import com.puebla.ayto.ti.multas.R;
import com.puebla.ayto.ti.multas.objects.Multa;










import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LasMasFrecuentesAdapter extends BaseAdapter {
	
	private ViewHolder holder;	

	private Activity mActivity;
	private ArrayList<Multa> listaMulta;
	public boolean id_26 = false;
	public boolean id_6 = false;
	
	
	public LasMasFrecuentesAdapter(Activity mActivity, ArrayList<Multa> listaMulta){
		this.mActivity = mActivity;
		this.listaMulta = listaMulta; 
	}

	
public View getView(int position, View convertView,ViewGroup parent){
		
		holder = null;
		View view = convertView;
		final Multa mMulta = listaMulta.get(position);
		
		if(view == null) {
			view = LayoutInflater.from(mActivity).inflate(R.layout.item_multa_frecuente, null);
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
				
				holder.icono_frecuente.setImageResource(id_icono_frecuente(mMulta.getMulta_id()));
				//holder.Id_tipo.setTextSize(20);
			}
			
			if (holder.infraccion != null){
				if(mMulta.getMulta_id() == 26)
					holder.infraccion.setText(R.string.item_26);
					else 
						if (mMulta.getMulta_id() == 6) {
							holder.infraccion.setText(R.string.item_6);
							
						}
							
				
						else
							if (infraccion_text(mMulta.getMulta())==true) {
								holder.infraccion.setText(mMulta.getMulta().substring(0, 140));
								//holder.ver_mas.setVisibility(View.VISIBLE);
								holder.ver_mas.setText("ver más...");
								
							}else {
								
								holder.infraccion.setText(mMulta.getMulta());
								holder.ver_mas.setText("ver detalle");
							}
							
				//holder.Id_tipo.setTextSize(20);
				//holder.infraccion.setText("Hola");
			}
			
			if (holder.txt_frecuente != null){
				holder.txt_frecuente.setText("La más frecuente");
				//holder.Id_tipo.setTextSize(20);
			}
			
			if (holder.num_frecuencia != null){
				holder.num_frecuencia.setText(Integer.toString(mMulta.getFrecuencia()));
				
				//holder.num_frecuencia.setText("Hola 2");
				//holder.Id_tipo.setTextSize(20);
			}
			
		}
		return view;
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

	private boolean infraccion_text(String txt) {
		return (txt.length()> 140) ? true : false;
	}
	
	private int id_icono_frecuente(int id) {
		int icon_pk;
		switch (id) {
		case 58:
			icon_pk = R.drawable.frecuente_1;
			break;
		case 26:
			icon_pk = R.drawable.frecuente_2;
			break;
		case 67:
			icon_pk = R.drawable.frecuente_3;
			break;
		case 6:
			icon_pk = R.drawable.frecuente_17;
			break;
		case 7:
			icon_pk = R.drawable.frecuente_5;
			break;
		case 11:
			icon_pk = R.drawable.frecuente_17;
			break;
		case 61:
			icon_pk = R.drawable.frecuente_7;
			break;
		case 55:
			icon_pk = R.drawable.frecuente_8;
			break;
		case 36:
			icon_pk = R.drawable.frecuente_9;
			break;
		case 124:
			icon_pk = R.drawable.frecuente_10;
			break;
		case 52:
			icon_pk = R.drawable.frecuente_11;
			break;
		case 32:
			icon_pk = R.drawable.frecuente_12;
			break;
		case 33:
			icon_pk = R.drawable.frecuente_12;
			break;
		case 45:
			icon_pk = R.drawable.frecuente_9;
			break;
		case 46:
			icon_pk = R.drawable.frecuente_14;
			break;
		case 65:
			icon_pk = R.drawable.frecuente_15;
			break;
		case 70:
			icon_pk = R.drawable.frecuente_16;
			break;
		case 92:
			icon_pk = R.drawable.frecuente_17;
			break;
		case 49:
			icon_pk = R.drawable.frecuente_18;
			break;
		default:
			icon_pk = R.drawable.frecuente_17;
			break;	
		}
		return icon_pk;
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
