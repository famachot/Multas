package com.puebla.ayto.ti.multas;




import fragments.LosMasComunes;
import baseAdapterListView.NavigationAdapter;

import br.liveo.utils.Constant;
import br.liveo.utils.Menus;
import navigationList.NavigationList;



import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
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





public class MainActivity extends ActionBarActivity {

	private int counterItemDownloads;
    private int lastPosition = 2;
	
	private DrawerLayout mDrawerLayout;
	private LinearLayout mLayoutMenu;
	private ListView mListViewMenu;
	private RelativeLayout mRelativeLayoutUser;
	private ActionBarDrawerToggleCompat mDrawerToggle;
	
	private NavigationAdapter navigationAdapter;
	
	
	
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
			
			if (lastPosition < 5){
				navigationAdapter.resetarCheck();			
				navigationAdapter.setChecked(lastPosition, true);
			}    	
			
	    }else{
	    	setLastPosition(lastPosition); 
	    	setFragmentList(lastPosition);	    	
	    }
		
		
		
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
		
		FragmentManager fragmentManager = getSupportFragmentManager();							
		
		switch (position) {
		case 0:			
			fragmentManager.beginTransaction().replace(R.id.content_frame, new LosMasComunes()).commit();
			break;					
		case 1:			
			fragmentManager.beginTransaction().replace(R.id.content_frame, new LosMasComunes()).commit();
			break;			
		case 2:			
			fragmentManager.beginTransaction().replace(R.id.content_frame, new LosMasComunes()).commit();						
			break;				
			
		default:
			Toast.makeText(getApplicationContext(), "implement other fragments here", Toast.LENGTH_SHORT).show();
			break;	
		}			
	
		if (position < 5){
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
		//	navigationAdapter.notifyDataSetChanged();  Cambio el estado del adaptador 			
			supportInvalidateOptionsMenu();			
		}		
	}
	
}
