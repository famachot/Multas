package com.puebla.ayto.ti.multas;




import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	
	private DrawerLayout mDrawerLayout; //Contenedor Pirncipal
	private ListView mDrawerListMenu; // ListView del menu
	private ActionBarDrawerToggle mDrawerToggle;
	private View mDrawerView; // Contenedor para el menu lateral
	
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mItemsMenu;

	//AppSectionsPagerAdapter mAppSectionsPagerAdapter;
	 ViewPager mViewPager;

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_app);
		
		mTitle = mDrawerTitle = getTitle();
		
		// Son los items del menu que se va a utilizar, quizá los cambie por consulta a BD
		mItemsMenu = getResources().getStringArray(R.array.menu_principal);
		
		//DraweLayout que es el contenedor para toda la aplicacion, contendra los Fragment y el menu lateral
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		
		//La siguiente vista es la que contiene toda la parte del menu lateral (Elementos compuestos)
		mDrawerView = (View)findViewById(R.id.layout_menu_completo);
		
		//Se obtiene una referencia al Listview que contendra todo el menu
		mDrawerListMenu = (ListView) findViewById(R.id.left_drawer);
		
		//Aplicamos una sombra al contenedor principal y le damos la posicón inicial
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		
		mDrawerListMenu.setAdapter(new AdapterListViewMenu(this, mItemsMenu));
		
		//Escucha los eventos click del ListView (Menu)
		mDrawerListMenu.setOnItemClickListener(new DrawerItemClickListener());
		

		
		
        
		   // enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_navigation_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
            	getSupportActionBar().setTitle(mTitle);
              //  invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
            	getSupportActionBar().setTitle(mDrawerTitle);
            //    invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
        
        
				
	}

	
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }
	

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	

	
	
    
	/**
	 * Implementamos la interfaz para OnItemClickListener
	 * 
	 **/
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
    	
    	// Sobre escribimos el metodo, se va a lanzar cada que se seleccione un item
    	// del ListView y nos da como parametro la posici�n del elemento
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    
    
    /**
     * En este metodo es donde vamos a lanzar los diferentes Fragments dependiendo del 
     * item seleccionado en el ListView
     * @param position
     */
    private void selectItem(int position) {
    	
    	
       
       }
	
	
	
	/**
	 * 
	 * La clase estatica AdaptadorListView se utiliza como un adaptador
	 * para el listview (Menu), en ella se van a ir agregando los elementos en el listView
	 * 
	 * **/
    public static class AdapterListViewMenu extends BaseAdapter{
    	private final Activity actividad;
    	private final String[] lista;
    	
    	public AdapterListViewMenu(Activity actividad, String[] lista)
    	{
    		super();
    		this.actividad=actividad;
    		this.lista =  lista;
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent)
    	{
    		String dato;
    		dato = lista[position];
    		LayoutInflater inflater = actividad.getLayoutInflater();
    		View view = inflater.inflate(R.layout.item_menu,null,true);
    		TextView textView = (TextView)view.findViewById(R.id.name_menu);
    		textView.setText(dato);
    		ImageView imageView = (ImageView)view.findViewById(R.id.icono_menu);
    		
    		switch (position)
    		{
    			case 0:
    				imageView.setImageResource(android.R.drawable.ic_menu_save);
    				break;
    			case 1:
    				imageView.setImageResource(android.R.drawable.ic_menu_add);
    				break;
    			case 2:
    				imageView.setImageResource(android.R.drawable.ic_dialog_alert);
    				break;
    			case 3:
    				imageView.setImageResource(android.R.drawable.ic_media_play);
    				break;
    			case 4:
    				imageView.setImageResource(android.R.drawable.ic_lock_power_off);
    				break;
    			default:
    				imageView.setImageResource(R.drawable.ic_launcher);
    				break;
    				
    		}	
    		

    		return view;
    	}
    	public int getCount()
    	{
    		return lista.length;
    	}
    	
    	public Object getItem(int position)
    	{
    		return lista[position];
    	}
    	
    	public long getItemId(int position)
    	{
    		return position;
    	}
    }
    
    
    

    
	
	
}
