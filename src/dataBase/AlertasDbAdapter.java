
package dataBase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;



public class AlertasDbAdapter {
	
	
	private final Context mContext;
	private static final String DataBaseName = "MultasDB";
	private static final int DataBaseVersion = 1;
	
	private DataBaseHelper mDataBaseHelper;
	private SQLiteDatabase mSQLiteDatabase;
	
	private static final String TAG_DB = "ClassDataBase";
	
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
	
	
	

	
	private static final String DROP_TABLE_NOTIFICACIONES = "DROP TABLE IF EXISTS " + EsquemaMultas.TABLE_NAME;
	
	private static final String DROP_TABLE_AVISOS = "DROP TABLE IF EXISTS " + EsquemaTipos.TABLE_NAME;
	
	

	

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
	
	public long CreateMulta(int id,int num_multa, String infraccion,int rangoInicial, int rangoFinal, String fundamento, String tipo,String frecuencia,String frecuente){
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
	
	
	
	public long updateNotificacion(int id,int num_multa, String infraccion,int rangoInicial, int rangoFinal, String fundamento, String tipo,String frecuencia,String frecuente){
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
	
	
	//Esta consulta busca las multas mas frecuentes pero puede buscar todas, verificar como voy a solicitar todas
	public boolean buscaMultasFrecuentes(boolean frecuente){
		Cursor mCursor = null;
		String frecuencia = (frecuente) ? "1":"0";
		try {
			mCursor = mSQLiteDatabase.query(true, EsquemaMultas.TABLE_NAME, new String[] {
					EsquemaMultas.COLUMN_NAME_ID,
					EsquemaMultas.COLUMN_NAME_INFRACCION},
					EsquemaMultas.COLUMN_NAME_FRECUENTE + "=" + frecuencia,
											null, null, null, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("DB", "Ocurrio un error al realizar una consulta pasando como parametro el tipo de notificacion " + e.getMessage());
		}
		if (mCursor.moveToFirst()){return true;}else{return false;}
		
	}
	
	public boolean deleteNotificacion(){
		boolean bandera = false;
		
		try {
			bandera = mSQLiteDatabase.delete(EsquemaNotificaciones.TABLE_NAME, null,null) > 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("DB", "Ocurrio un error al borrar una notificacion " + e.getMessage());
		}
		return bandera;
	}
	
	public boolean deleteNotificacion(long notificacion){
		boolean bandera = false;
		
		try {
			bandera = mSQLiteDatabase.delete(EsquemaNotificaciones.TABLE_NAME, EsquemaNotificaciones.COLUMN_NAME_ID + "=" + notificacion,null) > 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("DB", "Ocurrio un error al borrar una notificacion " + e.getMessage());
		}
		return bandera;
	}
	
	
	public boolean deleteAviso(){
		boolean bandera = false;
		
		try {
			bandera = mSQLiteDatabase.delete(EsquemaAvisos.TABLE_NAME, null,null) > 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("DB", "Ocurrio un error al borrar una notificacion " + e.getMessage());
		}
		return bandera;
	}
	
	public boolean deleteAviso(long aviso){
		boolean bandera = false;
		
		try {
			bandera = mSQLiteDatabase.delete(EsquemaAvisos.TABLE_NAME, EsquemaAvisos.COLUMN_NAME_ID + "=" + aviso,null) > 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("DB", "Ocurrio un error al borrar una notificacion " + e.getMessage());
		}
		return bandera;
	}
	
	
	public Cursor fetchAllNotificaciones(String tipNotificacion){
		Cursor mCursor = null;
		
		try {
			mCursor = mSQLiteDatabase.query(true, EsquemaNotificaciones.TABLE_NAME, new String[] {EsquemaNotificaciones.COLUMN_NAME_ID,
											EsquemaNotificaciones.COLUMN_NAME_TITULO, EsquemaNotificaciones.COLUMN_NAME_TEXTO,
											EsquemaNotificaciones.COLUMN_NAME_FECHA, EsquemaNotificaciones.COLUMN_NAME_LONGITUD, 
											EsquemaNotificaciones.COLUMN_NAME_LATITUD, EsquemaNotificaciones.COLUMN_NAME_TIPO},
											EsquemaNotificaciones.COLUMN_NAME_TIPO + "=" + tipNotificacion,
											null, null, null, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("DB", "Ocurrio un error al realizar una consulta pasando como parametro el tipo de notificacion " + e.getMessage());
		}
		
		return mCursor;
	}
	
	
	 
	public Cursor fetchAllAvisos(String notificacion){
		Cursor mCursor = null;
		
		try {
			mCursor = mSQLiteDatabase.query(true, EsquemaAvisos.TABLE_NAME, new String[] {EsquemaAvisos.COLUMN_NAME_ID,
												EsquemaAvisos.COLUMN_NAME_NOTIFICACION,EsquemaAvisos.COLUMN_NAME_AVISO} , 
												EsquemaAvisos.COLUMN_NAME_NOTIFICACION + "=" + notificacion,
												null, null, null, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("DB", "Ocurrio un error al intentar acceder a los avisos de la notificaci�n " + notificacion + "  " + e.getMessage());
		}
		return mCursor;
	}
	
	
	
}
