package com.puebla.ayto.ti.multas;

import baseAdapterListView.NavigationAdapter;

import br.liveo.utils.Constant;
import navigationList.NavigationList;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;





public class MainActivity extends ActionBarActivity {

	private int counterItemDownloads;
    private int lastPosition = 0;
	
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
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		
		
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
		
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		
		if (savedInstanceState != null) { 			
			setLastPosition(savedInstanceState.getInt(Constant.LAST_POSITION)); 				
			
			if (lastPosition < 5){
				navigationAdapter.resetarCheck();			
				navigationAdapter.setChecked(lastPosition, true);
			}    	
			
	    }else{
	    	setLastPosition(lastPosition); 
	    	//setFragmentList(lastPosition);	    	
	    }
		
		
		
		}
	
	
	 private class DrawerItemClickListener implements ListView.OnItemClickListener {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {          	        	
		    	//setLastPosition(posicao);        	
		    	//setFragmentList(lastPosition);	    	
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

	
	
	public void setLastPosition(int posicao){		
		this.lastPosition = posicao;
	}
	
	public int getCounterItemDownloads() {
		return counterItemDownloads;
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
