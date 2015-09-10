package com.example.fcg1400019442.oboticario;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rodrigo on 9/9/15.
 */
public class produtosDBHELPER extends SQLiteOpenHelper {
    private static final int VERSAO = 1;

    public static final String NOME_BANCO = "boticario.db";

    public produtosDBHELPER(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE = "CREATE TABLE " + contratoDB.Produto.NOME_TABELA + " ("+
        contratoDB.Produto._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
        contratoDB.Produto.COLUNA_DATA + " INTEGER NOT NULL, "+
        contratoDB.Produto.COLUNA_NOME + " TEXT NOT NULL, "+
        contratoDB.Produto.COLUNA_DESCRICAO + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + contratoDB.Produto.NOME_TABELA);
        onCreate(sqLiteDatabase);
    }
}



