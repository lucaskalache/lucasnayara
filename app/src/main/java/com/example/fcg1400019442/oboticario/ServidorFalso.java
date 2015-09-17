package com.example.fcg1400019442.oboticario;

/**
 * Created by xubuntu-developer on 9/3/15.
 */
public class ServidorFalso {
    public String[][] pegaDados() {
        String[][] dados1 = {
                {"1474070400", "7  Tentações Egeo", "Lançamento"},
                {"1474070400", "Atraia", "Novidade"},
                {"1474070400","Provoque","Não fique sem!"}
        };

        String[][] dados2 = {
                {"1474070400","Seduza","Não deixa de ter"},
                {"1474070400","Agarre","Mais um lançamento"},
                {"1474070400", "Devore","Surpreende que você ama"},
                {"1474070400","Desfrute","Uma otima escolha de presente"}
        };

        // Para não pegar sempre o mesmo...
         long time = System.currentTimeMillis() / 1000;
        if (time % 2 == 0) {
            return dados1;
        } else {
            return dados2;
        }
    }
}


