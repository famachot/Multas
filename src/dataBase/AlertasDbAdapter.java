
package dataBase;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.puebla.ayto.ti.multas.objects.*;



public class AlertasDbAdapter {
	
	
	private final Context mContext;
	private static final String DataBaseName = "DataBaseName";
	private static final int DataBaseVersion = 1;
	
	private DataBaseHelper mDataBaseHelper;
	private SQLiteDatabase mSQLiteDatabase;
	
	
	
	private static final String verificandoDb = "verificandoDb";
	
	
	public static abstract class EsquemaMultas implements BaseColumns{
		public static final String TABLE_NAME = "MULTAS";
		public static final String COLUMN_NAME_ID = "ID";
		public static final String COLUMN_NAME_NUMERO_MULTA = "NUMERO_MULTA";
		public static final String COLUMN_NAME_INFRACCION = "INFRACCION";
		public static final String COLUMN_NAME_RANGO_INICIAL = "RANGO_INICIAL";
		public static final String COLUMN_NAME_RANGO_FINAL = "RANGO_FINAL";
		public static final String COLUMN_NAME_FUNDAMENTO = "FUNDAMENTO";
		public static final String COLUMN_NAME_TIPO = "TIPO";
		public static final String COLUMN_NAME_FRECUENCIA = "FRECUENCIA";
		public static final String COLUMN_NAME_FRECUENTE = "FRECUENTE";
	}
	
	public static abstract class EsquemaTipos implements BaseColumns{
		public static final String TABLE_NAME = "TIPO_MULTAS";
		public static final String COLUMN_NAME_ID = "ID";
		public static final String COLUMN_NAME_TIPO = "TIPO";
		public static final String COLUMN_NAME_DESCRIPCION = "DESCRIPCION";
	}
	

	private static final String TYPE_CREATE_TABLE = "CREATE TABLE ";

	
	private static final String CREATE_TABLE_TIPOS_MULTA = TYPE_CREATE_TABLE + EsquemaTipos.TABLE_NAME + " (" + 
			EsquemaTipos.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
			EsquemaTipos.COLUMN_NAME_TIPO + " CHAR(5) NOT NULL," +
			EsquemaTipos.COLUMN_NAME_DESCRIPCION + " CHAR(150) NOT NULL);";
	
	
	
	private static final String CREATE_TABLE_MULTAS = TYPE_CREATE_TABLE + EsquemaMultas.TABLE_NAME + " (" +
													EsquemaMultas.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
													EsquemaMultas.COLUMN_NAME_NUMERO_MULTA + " INTEGER NULL," +
													EsquemaMultas.COLUMN_NAME_INFRACCION + " TEXT NOT NULL,"+
													EsquemaMultas.COLUMN_NAME_RANGO_INICIAL + " INTEGER NOT NULL," +
													EsquemaMultas.COLUMN_NAME_RANGO_FINAL + " INTEGER NULL," +
													EsquemaMultas.COLUMN_NAME_FUNDAMENTO + " CHAR(80) NOT NULL," +
													EsquemaMultas.COLUMN_NAME_TIPO + " INTEGER NOT NULL," +
													EsquemaMultas.COLUMN_NAME_FRECUENCIA + " INTEGER NULL," +
													EsquemaMultas.COLUMN_NAME_FRECUENTE + " CHAR(2) NOT NULL," + 
													"FOREIGN KEY (" + EsquemaMultas.COLUMN_NAME_TIPO + ") REFERENCES " + 
													EsquemaTipos.TABLE_NAME + " (" + EsquemaTipos.COLUMN_NAME_ID + "));";
	
	
	

	
	
	

	

	private static class DataBaseHelper extends SQLiteOpenHelper{
		

		
		
		DataBaseHelper(Context context){
			super(context, DataBaseName, null, DataBaseVersion);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db){		
			try {
				db.execSQL(CREATE_TABLE_TIPOS_MULTA);
				db.execSQL(CREATE_TABLE_MULTAS);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("DB", e.getMessage());
			}	
		}
		
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
			Log.w("BD", "Modificacion de la BD de la version " + oldVersion + "a la versión" + newVersion);
			
				try {
					onCreate(db);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	
	public AlertasDbAdapter(Context context){
	this.mContext = context;	
	}
	
	public AlertasDbAdapter open() throws SQLException{
		mDataBaseHelper = new DataBaseHelper(mContext);
		mSQLiteDatabase = mDataBaseHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		mDataBaseHelper.close();
	}
	
	public long CreateMulta(int id,int num_multa, String infraccion,int rangoInicial, int rangoFinal, String fundamento, int tipo,int frecuencia,String frecuente){
		long result = -1;
		ContentValues mContentValues = new ContentValues();
		mContentValues.put(EsquemaMultas.COLUMN_NAME_ID, id);
		mContentValues.put(EsquemaMultas.COLUMN_NAME_NUMERO_MULTA, num_multa);
		mContentValues.put(EsquemaMultas.COLUMN_NAME_INFRACCION, infraccion);
		mContentValues.put(EsquemaMultas.COLUMN_NAME_RANGO_INICIAL, rangoInicial);
		mContentValues.put(EsquemaMultas.COLUMN_NAME_RANGO_FINAL ,rangoFinal);
		mContentValues.put(EsquemaMultas.COLUMN_NAME_FUNDAMENTO ,fundamento);
		mContentValues.put(EsquemaMultas.COLUMN_NAME_TIPO , tipo);
		mContentValues.put(EsquemaMultas.COLUMN_NAME_FRECUENCIA , frecuencia);
		mContentValues.put(EsquemaMultas.COLUMN_NAME_FRECUENTE , frecuente);
		
		try {
			result =  mSQLiteDatabase.insert(EsquemaMultas.TABLE_NAME, null, mContentValues);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d(verificandoDb, "Ocurrio un error al intentar agregar una notificacion");
		}
		return result;
	}
	
	
	
	public long updateMulta(int id,int num_multa, String infraccion,int rangoInicial, int rangoFinal, String fundamento, String tipo,String frecuencia,String frecuente){
		long result = -1;
		ContentValues mContentValues = new ContentValues();
		//mContentValues.put(EsquemaMultas.COLUMN_NAME_ID, id);
		mContentValues.put(EsquemaMultas.COLUMN_NAME_NUMERO_MULTA, num_multa);
		mContentValues.put(EsquemaMultas.COLUMN_NAME_INFRACCION, infraccion);
		mContentValues.put(EsquemaMultas.COLUMN_NAME_RANGO_INICIAL, rangoInicial);
		mContentValues.put(EsquemaMultas.COLUMN_NAME_RANGO_FINAL ,rangoFinal);
		mContentValues.put(EsquemaMultas.COLUMN_NAME_FUNDAMENTO ,fundamento);
		mContentValues.put(EsquemaMultas.COLUMN_NAME_TIPO , tipo);
		mContentValues.put(EsquemaMultas.COLUMN_NAME_FRECUENCIA , frecuencia);
		mContentValues.put(EsquemaMultas.COLUMN_NAME_FRECUENTE , frecuente);
		
		
		
		try {
			Log.d(verificandoDb, "Antes de actualizar los datos de la notificacion");
			result =  mSQLiteDatabase.update(EsquemaMultas.TABLE_NAME, mContentValues,EsquemaMultas.COLUMN_NAME_ID + "=" + id, null);
			if (result>0){
			Log.d(verificandoDb, "Inmediatamente despues de modificar los campos de la tabla " + 
					EsquemaMultas.TABLE_NAME + " Columna: " + 
					EsquemaMultas.COLUMN_NAME_ID + "=" + Integer.toString(id) + "el texto a actualizado es: " + infraccion);	
			}else{
				Log.d(verificandoDb, "Inmediatamente despues de intentar modificar los campos de la tabla " + 
						EsquemaMultas.TABLE_NAME + " Columna: " + 
						EsquemaMultas.COLUMN_NAME_ID + "=" + Integer.toString(id));	
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d(verificandoDb, "Ocurrio un error al intentar actualizar un campo de notificacion con id=" + Integer.toString(id) + ";  " + e.getMessage());
		}
		return result;
	}
	
	
	
	
	
	public long CreateTipoMulta( int id_notificacion, String tipo, String descripcion){
		long result = -1;
		ContentValues mContentValues = new ContentValues();
		mContentValues.put(EsquemaTipos.COLUMN_NAME_ID, id_notificacion);
		mContentValues.put(EsquemaTipos.COLUMN_NAME_TIPO,tipo);
		mContentValues.put(EsquemaTipos.COLUMN_NAME_DESCRIPCION,descripcion);
		
		try {
			result = mSQLiteDatabase.insert(EsquemaTipos.TABLE_NAME, null, mContentValues);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("DB"," Ocurrio un error al intentar agregar un Aviso " + e.getMessage());
			
		}
		return result;
	}
	

	
	public boolean deleteMultas(){
		boolean bandera = false;
		
		try {
			bandera = mSQLiteDatabase.delete(EsquemaMultas.TABLE_NAME, null,null) > 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("DB", "Ocurrio un error al borrar una notificacion " + e.getMessage());
		}
		return bandera;
	}
	
	public boolean deleteUnaMulta(long pk){
		boolean bandera = false;
		
		try {
			bandera = mSQLiteDatabase.delete(EsquemaMultas.TABLE_NAME, EsquemaMultas.COLUMN_NAME_ID + "=" + pk,null) > 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("DB", "Ocurrio un error al borrar una notificacion " + e.getMessage());
		}
		return bandera;
	}
	
	
	public boolean deleteTipoMultas(){
		boolean bandera = false;
		
		try {
			bandera = mSQLiteDatabase.delete(EsquemaTipos.TABLE_NAME, null,null) > 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("DB", "Ocurrio un error al borrar una notificacion " + e.getMessage());
		}
		return bandera;
	}
	
	public boolean deleteUnTipoMulta(long aviso){
		boolean bandera = false;
		
		try {
			bandera = mSQLiteDatabase.delete(EsquemaTipos.TABLE_NAME, EsquemaTipos.COLUMN_NAME_ID + "=" + aviso,null) > 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("DB", "Ocurrio un error al borrar una notificacion " + e.getMessage());
		}
		return bandera;
	}
	
	
	
	
	/*** Sección destinada a la creación de todas las consultas  ***/
	
	/*** 
	 * 
	 *   											EsquemaMultas.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
													EsquemaMultas.COLUMN_NAME_NUMERO_MULTA + " INTEGER NULL," +
													EsquemaMultas.COLUMN_NAME_INFRACCION + " TEXT NOT NULL,"+
													EsquemaMultas.COLUMN_NAME_RANGO_INICIAL + " INTEGER NOT NULL," +
													EsquemaMultas.COLUMN_NAME_RANGO_FINAL + " INTEGER NULL," +
													EsquemaMultas.COLUMN_NAME_FUNDAMENTO + " CHAR(80) NOT NULL," +
													EsquemaMultas.COLUMN_NAME_TIPO + " INTEGER NOT NULL," +
													EsquemaMultas.COLUMN_NAME_FRECUENCIA + " INTEGER NULL," +
													EsquemaMultas.COLUMN_NAME_FRECUENTE + " CHAR(2) NOT NULL," + 
	 *   ***/
	
	//Esta consulta busca las multas mas frecuentes pero puede buscar todas, verificar como voy a solicitar todas
	public ArrayList<Multa> buscaMultasFrecuentes(boolean frecuente){
		ArrayList<Multa> mListMultas = new ArrayList<Multa>();
		Cursor mCursor = null;
		String frecuencia = (frecuente) ? "1":"0";
		boolean elemento_26 = false;
		boolean elemento_6 = false;
		boolean elemento_87 = false;
		
		try {
			mCursor = mSQLiteDatabase.query(true, EsquemaMultas.TABLE_NAME, null,
					EsquemaMultas.COLUMN_NAME_FRECUENTE + "=" + frecuencia,
											null, null, null,  EsquemaMultas.COLUMN_NAME_FRECUENCIA,null);
			
			if(mCursor != null) {
			if(	mCursor.moveToFirst()) {
					do {
						Log.d("DB", "Entro en el ciclo do while ");
						Log.d("DB", "Despues de recuperar la multa " + mCursor.getInt(2));
						Log.d("DB", "Despues de recuperar el numero de frecuencia " + mCursor.getInt(7));
						
						Multa mMulta = new Multa();
						mMulta.setId(mCursor.getInt(0));
						mMulta.setMulta_id(mCursor.getInt(1));
						mMulta.setMulta(mCursor.getString(2));
						mMulta.setRango_importe_ini(mCursor.getInt(3));
						mMulta.setRango_importe_fin(mCursor.getInt(4));
						mMulta.setFundamento(mCursor.getString(5));
						mMulta.setTipo(mCursor.getInt(6));
						mMulta.setFrecuecia(mCursor.getInt(7));
						mMulta.setFrecuente(frecuente);		
						
						if (mCursor.getInt(1) == 26) {
							if (elemento_26 == false) {
								mListMultas.add(mMulta);
								elemento_26 = true;
							}
						}else {
							if (mCursor.getInt(1) == 6) {
								if (elemento_6 == false) {
									mListMultas.add(mMulta);
									elemento_6 = true;
								}
							}else {
								if (mCursor.getInt(1) == 87) {
									if (elemento_87 == false) {
										mListMultas.add(mMulta);
										elemento_87 = true;
									}
								}else {
									mListMultas.add(mMulta);
								}
								
								
							}
						}
						
						
						
					}while(mCursor.moveToNext());
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("DB", "Ocurrio un error al realizar una consulta pasando como parametro el tipo de notificacion " + e.getMessage());
		}
		return mListMultas;
		
	}
	
	
	public ArrayList<Multa> RetornaMultasPorTipo(int tipoMulta){
		Cursor mCursor = null;
		ArrayList<Multa> mListMulta = new ArrayList<Multa>();
		
		boolean elemento_26 = false;
		boolean elemento_6 = false;
		boolean elemento_87 = false;
		
		try {
			mCursor = mSQLiteDatabase.query(true, EsquemaMultas.TABLE_NAME, null,
					EsquemaMultas.COLUMN_NAME_TIPO + "=" + tipoMulta,
					null, null, null, EsquemaMultas.COLUMN_NAME_NUMERO_MULTA, null);
			
			if(mCursor.moveToFirst()) {
				do {
				
					

					Multa mMulta = new Multa();
					mMulta.setId(mCursor.getInt(0));
					mMulta.setMulta_id(mCursor.getInt(1));
					mMulta.setMulta(mCursor.getString(2));
					mMulta.setRango_importe_ini(mCursor.getInt(3));
					mMulta.setRango_importe_fin(mCursor.getInt(4));
					mMulta.setFundamento(mCursor.getString(5));
					mMulta.setTipo(mCursor.getInt(6));
					mMulta.setFrecuecia(mCursor.getInt(7));
					Log.d(DataBaseName, "Multa: " + mCursor.getString(2).substring(0, 15) + ", Es frecuente " + mCursor.getString(8) + ", Num multa: " + mCursor.getInt(1));
					mMulta.setFrecuente((mCursor.getString(8).equals("1")) ? true : false);
					
					
					
					
					if (mCursor.getInt(1) == 26) {
						if (elemento_26 == false) {
							mListMulta.add(mMulta);
							elemento_26 = true;
						}
					}else {
						if (mCursor.getInt(1) == 6) {
							if (elemento_6 == false) {
								mListMulta.add(mMulta);
								elemento_6 = true;
							}
						}else {
							if (mCursor.getInt(1) == 87) {
								if (elemento_87 == false) {
									mListMulta.add(mMulta);
								    elemento_87 = true;
								}
							}else {
								mListMulta.add(mMulta);
							}
							
							
						}
					}
					
					
				//	mListMulta.add(mMulta);
				
				
				
				}while(mCursor.moveToNext());
			}
		} catch (SQLiteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("DB", "Ocurrio un error al realizar una consulta pen el metodo RetornaMultasPorTipo " + e.getMessage());
		}
		
		return mListMulta;
	}
	
	
	 
	public Cursor buscaTiposDeMultas(String notificacion){
		Cursor mCursor = null;
		
		try {
			//mCursor = mSQLiteDatabase.query(true, EsquemaTipos.TABLE_NAME, null , null,null, null, null,null,null);
			mCursor = mSQLiteDatabase.rawQuery("select * from TIPO_MULTAS", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("DB", "Ocurrio un error al intentar obtener los datos de la tabla Tipos de Multas -> " + e.getMessage());
		}
		return mCursor;
	}
	
	
	//Tiposd e multas modificado
	public ArrayList<TiposDeMulta> buscaTiposDeMultasObjects(){
		Cursor mCursor = null;
		ArrayList<TiposDeMulta> mListTiposMulta = new ArrayList<TiposDeMulta>();
		try {
			mCursor = mSQLiteDatabase.query(true, EsquemaTipos.TABLE_NAME, null , null,null, null, null,EsquemaTipos.COLUMN_NAME_TIPO,null);
			
			if(mCursor != null) {
				if(mCursor.moveToFirst()) {
					do {
						TiposDeMulta mTipoMultas = new TiposDeMulta();
						mTipoMultas.setId(mCursor.getInt(0));
						mTipoMultas.setTipo(mCursor.getString(1));
						mTipoMultas.setDescripcion(mCursor.getString(2));
						mListTiposMulta.add(mTipoMultas);
					}while(mCursor.moveToNext());
				}
			}
			
			//mCursor = mSQLiteDatabase.rawQuery("select * from TIPO_MULTAS", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("DB", "Ocurrio un error al intentar obtener los datos de la tabla Tipos de Multas -> " + e.getMessage());
		}
		return mListTiposMulta;
	}
	
	
	
	
	
}
