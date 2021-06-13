package Beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Adriana
 */
public class Contrato {
    private int fornecedor;
    private int mes_inicio;
    private int mes_fim;
    private double valor_Contrato;

    public Contrato(int fornecedor, int mes_inicio, int mes_fim, double valor_Contrato) {
        this.fornecedor = fornecedor;
        this.mes_inicio = mes_inicio;
        this.mes_fim = mes_fim;
        this.valor_Contrato = valor_Contrato;
    }

    public int getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(int fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getMes_inicio() {
        return mes_inicio;
    }

    public void setMes_inicio(int mes_inicio) {
        this.mes_inicio = mes_inicio;
    }

    public int getMes_fim() {
        return mes_fim;
    }

    public void setMes_fim(int mes_fim) {
        this.mes_fim = mes_fim;
    }

    public double getValor_Contrato() {
        return valor_Contrato;
    }

    public void setValor_Contrato(double valor_Contrato) {
        this.valor_Contrato = valor_Contrato;
    }
}
