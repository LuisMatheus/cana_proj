package Beans;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Matheus
 */
public class Contract_Manager {

    public int meses;
    public int fornecedores;
    public double taxa;

    public double[][][] contratos_matriz;
    public ArrayList<Contrato> contratos_array;

    public Contract_Manager(int meses, int fornecedores, double taxa) {
        this.meses = meses;
        this.fornecedores = fornecedores;
        this.taxa = taxa;

        contratos_matriz = new double[fornecedores][meses][meses];
        contratos_array = new ArrayList<>();
    }

    // <editor-fold defaultstate="collapsed" desc="Projeto 1 - AUX">
    public void add_contrato(int fornecedor, int mes_inicio, int mes_fim, double valor_contrato) {
        contratos_matriz[fornecedor - 1][mes_inicio - 1][mes_fim - 1] = valor_contrato;
        contratos_array.add(new Contrato(fornecedor, mes_inicio, mes_fim, valor_contrato));
    }

    public Contrato menorContrato() {
        Contrato menor = contratos_array.get(0);
        for (int i = 1; i < contratos_array.size(); i++) {
            if (contratos_array.get(i).getValor_Contrato() < menor.getValor_Contrato()) {
                menor = contratos_array.get(i);
            }
        }

        return menor;
    }

    public Contrato menorValorMatriz() {
        double menor = Double.MAX_VALUE;

        int mes = -1;
        int fornecedor = -1;

        for (int i = 0; i < fornecedores; i++) {
            for (int j = 0; j < meses; j++) {
                if (contratos_matriz[i][j][j] < menor) {
                    menor = contratos_matriz[i][j][j];
                    fornecedor = i + 1;
                    mes = j + 1;

                }
            }
        }

        if (mes > 0) {
            return new Contrato(fornecedor, mes, mes, menor);
        }

        return null;
    }

    public Contrato procurarContratoVetor(double valor) {
        for (int i = 0; i < contratos_array.size(); i++) {
            if (contratos_array.get(i).getValor_Contrato() == valor) {
                return contratos_array.get(i);
            }
        }
        return null;
    }

    public Contrato menorTodosMeses() {
        double menor = Double.MAX_VALUE;
        int fornecedor = -1;

        for (int i = 0; i < fornecedores; i++) {
            if (contratos_matriz[i][0][meses - 1] < menor) {
                menor = contratos_matriz[i][0][meses - 1];
                fornecedor = i + 1;
            }
        }

        if (fornecedor > 0) {
            return new Contrato(fornecedor, 1, meses, menor);
        }

        return null;
    }

    public Contrato procurarMesMatriz(int mes) {
        double menor = Double.MAX_VALUE;
        int fornecedor = -1;
        for (int i = 0; i < fornecedores; i++) {
            if (contratos_matriz[i][mes - 1][mes - 1] < menor) {
                menor = contratos_matriz[i][mes - 1][mes - 1];
                fornecedor = i + 1;
            }
        }

        if (fornecedor > 0) {
            return new Contrato(fornecedor, mes, mes, menor);
        }

        return null;
    }
    // </editor-fold>

    //PROJETO 2 ---------------------------------------------------------------------------------
    
    // <editor-fold defaultstate="collapsed" desc="Projeto 2 - Algoritimos">
    //Algoritimos
    public void InsertionSort(Contrato[] arrayCopia) {
        for (int j = 1; j < arrayCopia.length; j++) {
            Contrato x = arrayCopia[j];
            int i = j - 1;
            while (i >= 0 && arrayCopia[i].getValor_Contrato() > x.getValor_Contrato()) {      //(2+3+4...(nm+nm^2)/2)
                arrayCopia[i + 1] = arrayCopia[i];

                i = i - 1;
            }
            arrayCopia[i + 1] = x;
        }
    }

    public void mergeSort(Contrato[] ordenar, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(ordenar, p, q);
            mergeSort(ordenar, q + 1, r);
            merge(ordenar, p, q, r);
        }
    }

    public void merge(Contrato[] ordenar, int p, int q, int r) {

        int n1 = q - p + 1;
        int n2 = r - q;
        Contrato[] L = new Contrato[n1 + 1];
        Contrato[] R = new Contrato[n2 + 1];
        for (int i = 0; i < n1; i++) {
            L[i] = ordenar[p + i];
        }
        for (int j = 0; j < n2; j++) {

            R[j] = ordenar[q + j + 1];
        }

        L[n1] = new Contrato(Integer.MAX_VALUE, 0, 0, Integer.MAX_VALUE);
        R[n2] = new Contrato(Integer.MAX_VALUE, 0, 0, Integer.MAX_VALUE);

        int i = 0;
        int j = 0;
        for (int k = p; k <= r; k++) {

            if (L[i].getValor_Contrato() <= R[j].getValor_Contrato()) {

                ordenar[k] = L[i++];
            } else {
                ordenar[k] = R[j++];
            }
        }
    }

    public void quickSort(Contrato[] array, int p, int r) {
        if (p < r) {
            int q = partition(array, p, r);
            quickSort(array, p, q - 1);
            quickSort(array, q + 1, r);
        }

    }

    public int partition(Contrato[] array, int p, int r) {
        Contrato x = array[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (array[j].getValor_Contrato() <= x.getValor_Contrato()) {
                i++;
                Contrato aux = array[i];
                array[i] = array[j];
                array[j] = aux;
            }
        }
        Contrato aux = array[i + 1];
        array[i + 1] = array[r];
        array[r] = aux;

        return i + 1;

    }
    // </editor-fold>

}
