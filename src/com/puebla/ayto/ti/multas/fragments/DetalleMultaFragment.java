package com.puebla.ayto.ti.multas.fragments;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.puebla.ayto.ti.multas.R;
import com.puebla.ayto.ti.multas.objects.Multa;

public class DetalleMultaFragment extends Fragment {
	
	
	MuestraDetalleFragment mCallBack;
	public static final String TAG = "DetalleFragment";
	private Multa mMulta;
	private static final double salario = 63.77;
	
	TextView text_Infraccion;
	TextView text_Fundamento;
	TextView text_DiasMulta;
	TextView text_CostoSalario;
	TextView text_PrecioRango;
	ImageView mFoto;
	View mLineaDiv;
	Button btn_donde_pagar;
	
	public DetalleMultaFragment() {
		// TODO Auto-generated constructor stub
	}
	
	 public static DetalleMultaFragment newInstance(Bundle arguments){
		 DetalleMultaFragment f = new DetalleMultaFragment();
	        if(arguments != null){
	            f.setArguments(arguments);
	        }
	        return f;
	    }
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.detalle_multa, container, false);
		
		 text_Infraccion = (TextView) rootView.findViewById(R.id.infraccion);
		 text_Fundamento = (TextView) rootView.findViewById(R.id.fundamento_legal);
		 text_DiasMulta = (TextView) rootView.findViewById(R.id.dias_multa);
		 text_CostoSalario = (TextView) rootView.findViewById(R.id.costo_salario);		
		 text_PrecioRango = (TextView) rootView.findViewById(R.id.precio_rango_costos);
		 mFoto = (ImageView) rootView.findViewById(R.id.imageView1);
		 mLineaDiv = (View) rootView.findViewById(R.id.linea_divisora);
		 btn_donde_pagar = (Button) rootView.findViewById(R.id.btn_donde_pagar);
		
		return rootView;
	}
	
	 @Override
	    public void onViewCreated(View view, Bundle savedInstanceState) {
	        super.onViewCreated(view, savedInstanceState);
	        if (getArguments() != null) {
	        	
	        	DecimalFormat df = new DecimalFormat("0.00");
	        	String costo_inicial = df.format(getArguments().getInt("ran_ini") * salario);
	        	String costo_final = df.format(getArguments().getInt("ran_fin") * salario);
	        	
	        	text_Infraccion.setText(getArguments().getString("infraccion"));
		        text_Fundamento.setText(getArguments().getString("fundamento"));
		        text_DiasMulta.setText("De " + Integer.toString(getArguments().getInt("ran_ini"))   + " a " + Integer.toString(getArguments().getInt("ran_fin"))  + " días");
		        text_PrecioRango.setText("Minimo $" + costo_inicial + " Máximo $" + costo_final);
		        text_CostoSalario.setText(Double.toString(salario));
		        if (getArguments().getBoolean("frecuente")) {
		        	mFoto.setImageResource(id_icono_frecuente(getArguments().getInt("num_multa")));
		        	mFoto.setVisibility(View.VISIBLE);
		        	mLineaDiv.setVisibility(View.VISIBLE);
		        }
	        }
	        
	        btn_donde_pagar.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	                 // Perform action on click
	            	 mCallBack.dondePagar();
	             }
	         });
	        
	    }
	 
	 
	    //El Fragment ha sido quitado de su Activity y ya no está disponible
	 @Override
	 public void onDetach() {
	    // mCallback = null;
	     super.onDetach();
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
	 
	

	 
	
	 public interface MuestraDetalleFragment {
		 public void dondePagar();
		 
	 }
	 
	 
	 @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        
	        // Nos aseguramos de que la actividad contenedora haya implementado la
	        // interfaz de retrollamada. Si no, lanzamos una excepción
	        try {
	        	mCallBack = (MuestraDetalleFragment) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " debe implementar OnMultasSelectedListener");
	        }
	    }
	 
}
