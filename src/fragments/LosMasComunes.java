package fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.puebla.ayto.ti.multas.R;

public class LosMasComunes extends Fragment{

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.multas_fragment, container, false);
		
		return rootView;
	}
}
