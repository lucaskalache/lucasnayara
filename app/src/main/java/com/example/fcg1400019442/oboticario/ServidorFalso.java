package com.example.fcg1400019442.oboticario;

/**
 * Created by xubuntu-developer on 9/3/15.
 */
public class ServidorFalso {
    public String[] pegaDados() {
        String[] dados1 = {
                "Novidades",
                "Dois",
                "Três indiozinhos"
        };

        String[] dados2 = {
                "Quatro",
                "Cinco",
                "Seis indiozinhos"
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


