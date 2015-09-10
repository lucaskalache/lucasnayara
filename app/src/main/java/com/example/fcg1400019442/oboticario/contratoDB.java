package com.example.fcg1400019442.oboticario;

import android.provider.BaseColumns;

/**
 * Created by xubuntu-developer on 9/10/15.
 */
public class contratoDB {

    public static final class Produto implements BaseColumns {

        public static final String NOME_TABELA = "produto ";

        public static final String COLUNA_DATA = "validade";
        public static final String COLUNA_NOME= "nome";
        public static final String COLUNA_DESCRICAO = "descricao";
    }
}

