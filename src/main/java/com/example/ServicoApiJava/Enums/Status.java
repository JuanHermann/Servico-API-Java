package com.example.ServicoApiJava.Enums;

public enum Status {
    semStatus( 0),verde(1),amarelo(2),vermelho(3);

    public int valorStatus;
    Status(int valor) {
        valorStatus = valor;
    }
}
