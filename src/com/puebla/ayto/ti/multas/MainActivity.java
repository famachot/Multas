package com.puebla.ayto.ti.multas;




import java.lang.reflect.Type;
import java.util.ArrayList;

import navigationList.NavigationList;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.SQLException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import baseAdapterListView.NavigationAdapter;
import br.liveo.utils.Constant;
import br.liveo.utils.Menus;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import com.puebla.ayto.ti.multas.HttpRequest.HttpRequestException;
import com.puebla.ayto.ti.multas.fragments.DetalleMultaFragment;
import com.puebla.ayto.ti.multas.fragments.DetalleMultaFragment.MuestraDetalleFragment;
import com.puebla.ayto.ti.multas.fragments.DireccionesFragment;
import com.puebla.ayto.ti.multas.fragments.MasFrecuentesFragment;
import com.puebla.ayto.ti.multas.fragments.MasFrecuentesFragment.OnMultasSelectedListener;
import com.puebla.ayto.ti.multas.fragments.MultasPorTipoFragment;
import com.puebla.ayto.ti.multas.fragments.MultasPorTipoFragment.OnMultasSelectedTipo;
import com.puebla.ayto.ti.multas.fragments.TiposDeMultaFragment;
import com.puebla.ayto.ti.multas.fragments.TiposDeMultaFragment.MultasPorTipoInter;
import com.puebla.ayto.ti.multas.objects.Multa;
import com.puebla.ayto.ti.multas.objects.TiposDeMulta;

import dataBase.AlertasDbAdapter;


public class MainActivity extends ActionBarActivity 
implements OnMultasSelectedListener, MuestraDetalleFragment,
MultasPorTipoInter, OnMultasSelectedTipo{

	private int counterItemDownloads;
    private int lastPosition = 0;
	
	private DrawerLayout mDrawerLayout;
	private LinearLayout mLayoutMenu;
	private ListView mListViewMenu;
	private RelativeLayout mRelativeLayoutUser;
	private ActionBarDrawerToggleCompat mDrawerToggle;
	
	
	
	private NavigationAdapter navigationAdapter;
	private AlertasDbAdapter DB;
	
	private static final String TAG_ASYN_CONECTOR ="TAG_ASYNC_MULTAS";
	

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getSupportActionBar().setIcon(R.drawable.logo_ayuntamiento_original); //Se cambia el icono del Action Bar
		setContentView(R.layout.content_app);
		
	
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout); //Se obtiene una referencia de todo el drawer layout (Todo el contenedor)
		mLayoutMenu = (LinearLayout) findViewById(R.id.layout_menu); // Se obtiene el layout que contiene toda la parte del menu lateral
		mListViewMenu = (ListView) findViewById(R.id.listView_drawer); // Se obtiene una referencia del ListView 	que contendra el menu
		mRelativeLayoutUser = (RelativeLayout) findViewById(R.id.userDrawerMenu); // Se obtinee una referencia del LRelativeLayout que contiene los widget del usuario
		mRelativeLayoutUser.setOnClickListener(userOnClick); // Se ñe asigna un escuchador para cerrar el menu cuando presionen en esa parte
		
		
		if(mListViewMenu != null) {
			navigationAdapter = NavigationList.getNavigationAdapter(this);
		}
		
		mListViewMenu.setAdapter(navigationAdapter);
		mListViewMenu.setOnItemClickListener(new DrawerItemClickListener());
		

		mDrawerToggle = new ActionBarDrawerToggleCompat(this, mDrawerLayout);
		
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	    getSupportActionBar().setHomeButtonEnabled(true);
		
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		if (savedInstanceState != null) { 			
			setLastPosition(savedInstanceState.getInt(Constant.LAST_POSITION)); 				
			
			if (lastPosition < 2){
				navigationAdapter.resetarCheck();			
				navigationAdapter.setChecked(lastPosition, true);
			}    	
			
	    }else{
	    	setLastPosition(lastPosition); 
	    	setFragmentList(lastPosition);	    	
	    }
		
		//RecuperarTiposMulta();
		
		MuestraMasFrecuentes();
		}
	
	
	 private class DrawerItemClickListener implements ListView.OnItemClickListener {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {          	        	
		    	setLastPosition(posicao);        	
		    	setFragmentList(lastPosition);	    	
		    	mDrawerLayout.closeDrawer(mLayoutMenu);	    	
	        }
	    }
	
	private OnClickListener userOnClick = new OnClickListener() {		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mDrawerLayout.closeDrawer(mLayoutMenu);
		}
	};

	
	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub		
		super.onSaveInstanceState(outState);		
		outState.putInt(Constant.LAST_POSITION, lastPosition);					
	}
	
	
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);	     
		mDrawerToggle.syncState();	
	 }
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);		
	}
	
	
	
    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        //boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mLayoutMenu);
        menu.findItem(R.id.about).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		 MenuInflater inflater = getMenuInflater();
	     inflater.inflate(R.menu.main, menu);
	     return true;
	}
	
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {  
		Intent mIntentAcerca;
		//Menu mMenu = (Menu) findViewById(R.menu.main);
		
		//Log.d(TAG_VERIFICANDO_VOLCAN, "Antes de entrar al if ");
		if (item.getItemId() == R.id.about) {
			mIntentAcerca = new Intent(this,AcercaDe.class);
			startActivity(mIntentAcerca);
			
		}
		
        if (mDrawerToggle.onOptionsItemSelected(item)) {
              return true;
        }		
        
		switch (item.getItemId()){	
		case Menus.HOME:
			if (mDrawerLayout.isDrawerOpen(mLayoutMenu)) {
				mDrawerLayout.closeDrawer(mLayoutMenu);
				//hideMenus(mMenu,2);
			} else {
				//hideMenus(mMenu,1);
				mDrawerLayout.openDrawer(mLayoutMenu);
			}
			return true;			
		default:
			return super.onOptionsItemSelected(item);			
		}		             
    }
	
    private void hideMenus(Menu menu, int posicao) {
    	
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mLayoutMenu);    	
    	
        switch (posicao) {
		case 1:        
	        menu.findItem(Menus.ABOUT).setVisible(false);       
			break;
			
		case 2:
	        menu.findItem(Menus.ABOUT).setVisible(true);	        	        	              			
			break;				
			//implement other fragments here			
		}          
    }
	
	public void setLastPosition(int posicao){		
		this.lastPosition = posicao;
	}
	
	public int getCounterItemDownloads() {
		return counterItemDownloads;
	}
	
	
	
	/*** Revisión de todos los fragments ***/
	
private void setFragmentList(int position){
		
	//	FragmentManager fragmentManager = getSupportFragmentManager();		//No se ocupa y consume memoria					
		
		switch (position) {
		case 0:			
			MasFrecuentesFragment principalFragment = new MasFrecuentesFragment();
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.content_frame, principalFragment, "TAG_FRAGMENT");
			 //ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
			//ft.disallowAddToBackStack();// Verificar esta linea

		     //   int num = getSupportFragmentManager().getBackStackEntryCount(); // Si funciona y regresa el númerod e fragmentos en la pila
		     //Toast.makeText(this, "Numero de fragmentos en la pila " + num, Toast.LENGTH_SHORT).show();
		    getSupportFragmentManager().popBackStack(0,FragmentManager.POP_BACK_STACK_INCLUSIVE);
			ft.commit();
	
			break;					
		case 1:			
			TiposDeMultaFragment porTiposFragment = new TiposDeMultaFragment();
			FragmentTransaction ftt = getSupportFragmentManager().beginTransaction();
			ftt.replace(R.id.content_frame, porTiposFragment, "TAG_FRAGMENT");
			ftt.addToBackStack(null);
			// ftt.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
			ftt.commit();
			
			break;			
		
		}			
	
		if (position < 2){ // Cambiar, solo hay dos posibles opciones
			navigationAdapter.resetarCheck();			
			navigationAdapter.setChecked(position, true);
		}
	}
	
	
	
	
	
	
	/*** Espacio para las clases que se utilizan dentro de esta activity  ****/
	
	private class ActionBarDrawerToggleCompat extends ActionBarDrawerToggle {

		public ActionBarDrawerToggleCompat(Activity mActivity, DrawerLayout mDrawerLayout){
			super(
			    mActivity,
			    mDrawerLayout, 
  			    R.drawable.ic_action_navigation_drawer, 
				R.string.drawer_open,
				R.string.drawer_close);
		}
		
		@Override
		public void onDrawerClosed(View view) {			
			supportInvalidateOptionsMenu();				
		}

		@Override
		public void onDrawerOpened(View drawerView) {	
			supportInvalidateOptionsMenu();			
		}		
	}
	
	
	
	
	
	
	
	/*** Sección para verificar si ya hay datos en la BD  ***/
	
	/***
	 * 
	 * En el siguiente metodo debo de mandar a eliminar todos los tipos d multas por si se da el caso 
	 * de que haya tipos de multas pero no se hayan descargado las multas, esto para evitar problemas con 
	 * las claves primarias a la hora de descagar todo, entonces antes de solicitar los datos
	 * debo eliminar todo lo de las tablas 
	 * ***/
	
	public void MuestraMasFrecuentes() { //Debo utilizar este metodo para no hacer varias consultas a la base de datos y aprovechar los recursos al maximo
		DB = new AlertasDbAdapter(this);
		ArrayList<Multa> mListaFrecuentes = new ArrayList<Multa>();
		
		try {
			DB.open();
			mListaFrecuentes = DB.buscaMultasFrecuentes(true);
			DB.close();
			
			if (mListaFrecuentes.size() < 1) {
	
				if (estaConectado()) {
					RecuperarTiposMulta();
				}else {
					showDialogInternet();
				}
			}
		}catch(Exception e) {
			
		}
		
	}
	
	


	
	private boolean agregaMultas(ArrayList<Multa> multas){
		DB = new AlertasDbAdapter(this);
		Long result = null;
		//int contador = multas.size(); //Verificar si puede precindir de esta variable para no consumir recursos 
		boolean bandera = true; 
		 
		 try {
			DB.open();
			for(int i = 0; i < multas.size();i++) {
				
			result = DB.CreateMulta(multas.get(i).getId(), multas.get(i).getMulta_id(), 
						multas.get(i).getMulta(),multas.get(i).getRango_importe_ini(), 
					 multas.get(i).getRango_importe_fin(), multas.get(i).getFundamento(),
					 multas.get(i).getTipo(), multas.get(i).getFrecuencia(),
					  (multas.get(i).getFrecuente()) ? "1":"0");
				
				if (result == -1) {
					bandera = false;
				}
			}
		
			DB.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bandera;
		
	}
	  
	private boolean agregaTiposMultas(ArrayList<TiposDeMulta> tiposMulta){
		DB = new AlertasDbAdapter(this);
		Long result = null;
		//int contador = tiposMulta.size();// verificar si puedo precindir de esta variable
		boolean bandera = true; 
		 
		 try {
			DB.open();
			for(int i = 0; i< tiposMulta.size();i++) {
				
			result = DB.CreateTipoMulta(tiposMulta.get(i).getId(), tiposMulta.get(i).getTip_multa(), 
					tiposMulta.get(i).getDescripcion());
				if (result == -1) {
					bandera = false;
				}
			}

			DB.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bandera;
	}
	
	  public void RecuperarTiposMulta(){
	    	String url = String.format("http://movil-kiosko.pueblacapital.gob.mx/notificaciones/multas/todas/tipos/"); //Cambiar la url
	    	new AsyncSolicitaTiposDeMultas(this).execute(url);
	    }
	  
	  public void RecuperarMulta(){
	    	String url = String.format("http://movil-kiosko.pueblacapital.gob.mx/notificaciones/multas/todas/multas/"); //Cambiar la url
	    	new AsyncSolicitaMultas(this).execute(url);
	    }
	
    public class AsyncSolicitaTiposDeMultas extends AsyncTask<String, Long, String>  {
    	private Context mContext;
       	private ProgressDialog pd;
    	
       	public AsyncSolicitaTiposDeMultas(Context mContext){
    		this.mContext = mContext;
    		pd = new ProgressDialog(mContext);
    	}
    
    	@Override
    	protected void onPreExecute() {
    		pd.setIndeterminate(true);
    		pd.setMessage("Cargando información...");
    		pd.setTitle(R.string.tituloProgresBar);
    		pd.show();
    		super.onPreExecute();
    	}
    	
    	@Override
    	protected String doInBackground(String... urls) {

    		try {
    			
    			return HttpRequest.get(urls[0]).accept("application/json").body();
    			
    		} catch (HttpRequestException exception) {
    			Log.e(TAG_ASYN_CONECTOR, exception.getMessage());
    			return null;
    		}
    	}

    	
    	@Override
    	protected void onPostExecute(String result) {
    		
    		if (result != null){
    			ArrayList<TiposDeMulta> mGetTiposMultas = getTiposDeMultas(result);
    			//mostrarDatos(mGetMultas, tipo);
	    		if(agregaTiposMultas(mGetTiposMultas)) { 
	    			RecuperarMulta();
	    		}
    		
    		}
    		else
    		{
    			Toast.makeText(mContext, "No se obtuvieron datos de internet", Toast.LENGTH_SHORT).show();
    		}
    		
    		
    		
    		pd.dismiss();
    		super.onPostExecute(result);
    	}	
    }
	//Port verificar todo lo siguiwntw 
    public class AsyncSolicitaMultas extends AsyncTask<String, Long, String>  {
    	private Context mContext;
       	private ProgressDialog pd;
    	
       	public AsyncSolicitaMultas(Context mContext){
    		this.mContext = mContext;
    		pd = new ProgressDialog(mContext);
    	}
    
    	@Override
    	protected void onPreExecute() {
    		pd.setIndeterminate(true);
    		pd.setMessage("Cargando información...");
    		pd.setTitle(R.string.tituloProgresBar);
    		pd.show();
    		super.onPreExecute();
    	}
    	
    	@Override
    	protected String doInBackground(String... urls) {

    		try {
    			return HttpRequest.get(urls[0]).accept("application/json").body();
    			
    		} catch (HttpRequestException exception) {
    			return null;
    		}
    	}

    	
    	@Override
    	protected void onPostExecute(String result) {
    		
    		if (result != null){
    		ArrayList<Multa> mGetMultas = getMultas(result);
    		FragmentManager fragmentManager = getSupportFragmentManager();
    		    agregaMultas(mGetMultas);
    		    fragmentManager.beginTransaction().replace(R.id.content_frame, new MasFrecuentesFragment()).commit();
    		}
    		else
    		{
    			//datoRecibido = false;
    			Toast.makeText(mContext, "No se obtuvieron datos de internet", Toast.LENGTH_SHORT).show();
    		}

    		pd.dismiss();
    		super.onPostExecute(result);
    	}	
    }
	
	
	
    private ArrayList<TiposDeMulta> getTiposDeMultas(String json) {
    	final Gson gson = new Gson();
		final Type tipoListMultas = new TypeToken<ArrayList<TiposDeMulta>>(){}.getType();
		ArrayList<TiposDeMulta> listMultas;
		 
		try {
			listMultas = gson.fromJson(json, tipoListMultas);
		} catch (JsonSyntaxException e) { 
			e.printStackTrace();
			listMultas= null;
		}
		 return listMultas;
	}
	
    private ArrayList<Multa> getMultas(String json) {
    	
    	final Gson gson = new Gson();
		final Type tipoListMultas = new TypeToken<ArrayList<Multa>>(){}.getType();
		ArrayList<Multa> listMultas;
		try {
			listMultas = gson.fromJson(json, tipoListMultas);
		} catch (JsonSyntaxException e) { 
			e.printStackTrace();
			listMultas= null;
		}
		 return listMultas;
	}
    
    
    /***
     * 	Sección que se utiliza para las llamadas a los fragments y el paso de parametros entre ellos
     * 
     *  
     */
	
	
	public void onMultaSelected(int id, String infraccion, String fundamento, int ran_ini, int ran_fin, Boolean frecuente, int num_multa) {
		
		Bundle args = new Bundle(); 
		
		args.putInt("id", id);
		args.putString("infraccion", infraccion);
		args.putString("fundamento", fundamento);
		args.putInt("ran_ini", ran_ini);
		args.putInt("ran_fin", ran_fin);
		args.putBoolean("frecuente", frecuente);
		args.putInt("num_multa", (frecuente) ? num_multa : -1);
		
		DetalleMultaFragment mDetalleFragment =  DetalleMultaFragment.newInstance(args);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		
        ft.replace(R.id.content_frame, mDetalleFragment, "TAG_FRAGMENT");
        ft.addToBackStack(null);
       // ft.setTransition(1);
        ft.commit();
		
	}
	
	
	public void onMultaSelectedGrupo(int id) {
		
		DB.open();
		DB.multasPorGrupo(id);
		DB.close();
	}
	
	
public void onMultaSelectedTipo(int id, String infraccion, String fundamento, int ran_ini, int ran_fin, Boolean frecuente, int num_multa) {
		
		Bundle args = new Bundle(); 
		
		args.putInt("id", id);
		args.putString("infraccion", infraccion);
		args.putString("fundamento", fundamento);
		args.putInt("ran_ini", ran_ini);
		args.putInt("ran_fin", ran_fin);
		args.putBoolean("frecuente", frecuente);
		args.putInt("num_multa", (frecuente) ? num_multa : -1);
		
		DetalleMultaFragment mDetalleFragment =  DetalleMultaFragment.newInstance(args);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		
        ft.replace(R.id.content_frame, mDetalleFragment, "TAG_FRAGMENT");
        ft.addToBackStack(null);
        ft.commit();
		
	}
	
	
	
	public void dondePagar() {
		
		DireccionesFragment mFt = new DireccionesFragment();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.content_frame, mFt, "TAG_FRAGMENT");
        ft.addToBackStack(null);
        ft.commit();
		
	}
	
	public void TipoSeleccionado(int id) {
		Bundle args = new Bundle(); 
		args.putInt("id", id);

		MultasPorTipoFragment mDetalleFragment =  MultasPorTipoFragment.newInstance(args);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		
        ft.replace(R.id.content_frame, mDetalleFragment, "TAG_FRAGMENT");
        ft.addToBackStack(null);
        ft.commit();
	}
	
	
	

	
	
	protected Boolean estaConectado(){
		if(conectadoWifi()){
			return true;
			}else{
				if(conectadoRedMovil()){
					return true;
				}else{
					return false;
				}
		}
	}
	
	
	
	protected Boolean conectadoWifi(){
		ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (info != null) {
				if (info.isConnected()) {
					return true;
				}else {
					
				}
			}
		}
		return false;
	}
	
	
	
	protected Boolean conectadoRedMovil(){
		ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (info != null) {
				if (info.isConnected()) {
					return true;
				}
			}
		}
		return false;
	}
	

	
	public void showDialogInternet(){
		AlertDialog.Builder builder =  new AlertDialog.Builder(MainActivity.this)
									.setTitle(R.string.titleDialogNoInternet)
									.setMessage(R.string.mensajeDialogNoInternet)
									.setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
						                    public void onClick(DialogInterface dialog, int whichButton) {
						    
						                    	startActivityForResult(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS), 0);
						                    
						                    }
						                })
						             .setNegativeButton(R.string.alert_dialog_no, new DialogInterface.OnClickListener() {
						                  public void onClick(DialogInterface dialog, int whichButton) {
						                	 
						                      
						                  }
						              });
									
		builder.show();
	}

	
}

