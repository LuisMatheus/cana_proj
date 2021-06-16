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
public abstract class Contract_Manager {

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

    public abstract void printClear();

    public abstract void printAdd(String s);

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

    //PROJETO 3 ---------------------------------------------------------------------------------
    // <editor-fold defaultstate="collapsed" desc="Projeto 3 - Algoritimos">
    public void escolhaGulosa() {
        ArrayList<Contrato> aux = new ArrayList<Contrato>();
        double h = Integer.MAX_VALUE;
        Contrato aux1 = null;
        for (int i = 0; i < fornecedores; i++) {
            for (int j = 0; j < meses; j++) {

                if ((taxa + contratos_matriz[i][0][j]) / (j + 1) <= h) {
                    aux1 = new Contrato(i + 1, 1, j + 1, contratos_matriz[i][0][j]);

                    h = (taxa + contratos_matriz[i][0][j]) / (j + 1);

                }
            }
        }

        aux.add(aux1);
        int mes = aux1.getMes_fim();

        while (mes != meses) {
            h = Integer.MAX_VALUE;
            for (int i = 0; i < fornecedores; i++) {
                for (int j = mes; j < meses; j++) {
                    if ((taxa + contratos_matriz[i][mes][j]) / (j - mes + 1) <= h) {
                        aux1 = new Contrato(i + 1, mes + 1, j + 1, contratos_matriz[i][mes][j]);
                        h = (taxa + contratos_matriz[i][mes][j]) / (j - mes + 1);
                    }
                }
            }
            aux.add(aux1);
            mes = aux1.getMes_fim();
        }

        double valor = 0;

        for (int i = 0; i < aux.size(); i++) {

            printAdd("Contrato Fornecedor: " + aux.get(i).getFornecedor() + "\n Mes inicio: " + aux.get(i).getMes_inicio() + "\n Mes Fim:  " + aux.get(i).getMes_fim() + "\n Valor Unit:  " + aux.get(i).getValor_Contrato());
            printAdd("-------------------------");
            valor = valor + aux.get(i).getValor_Contrato();
            if (i != aux.size() - 1) {
                valor = valor + taxa;
            }
        }

        printAdd("VALOR TOTAL:" + valor);
    }

    public void chamarRecursivo() {
        double[][] menoresValor = new double[meses][meses];
        int[][] fornecedores_matriz = new int[meses][meses];
        double h = Integer.MAX_VALUE;
        for (int i = 0; i < meses; i++) {
            for (int j = i; j < meses; j++) {
                h = Integer.MAX_VALUE;
                for (int k = 0; k < fornecedores; k++) {
                    if (contratos_matriz[k][i][j] < h) {
                        menoresValor[i][j] = contratos_matriz[k][i][j];
                        fornecedores_matriz[i][j] = k + 1;
                        h = contratos_matriz[k][i][j];
                    }
                }
            }
        }
        double total = 0;
        total = total + recursivo(menoresValor, fornecedores_matriz, 0, meses - 1);
        printAdd("TOTAL: " + total);
    }

    public double recursivo(double[][] menoresValor, int[][] fornecedores_matriz, int inicio, int fim) {

        double menorValor = menoresValor[inicio][fim];
        int sup = -1;
        for (int j = inicio; j < fim; j++) {
            if (menoresValor[inicio][j] + menoresValor[j + 1][fim] + taxa < menorValor) {
                menorValor = menoresValor[inicio][j] + menoresValor[j + 1][fim] + taxa;
                sup = j;
            }
        }

        if (sup != -1) {

            return recursivo(menoresValor, fornecedores_matriz, inicio, sup) + recursivo(menoresValor, fornecedores_matriz, sup + 1, fim) + taxa;

        } else {
            int aux = inicio + 1;
            int aux1 = fim + 1;

            printAdd("Contrato Fornecedor: " + fornecedores_matriz[inicio][fim] + "\n Mes inicio: " + aux + "\n Mes Fim:  " + aux1 + "\n Valor Unit:  " + menoresValor[inicio][fim]);
            printAdd("-------------------------");
            return menoresValor[inicio][fim];
        }

    }

    public void dinamico() {
        double[][] menoresValor = new double[meses][meses];
        int[][] fornecedores_matriz = new int[meses][meses];
        double h = Integer.MAX_VALUE;
        for (int i = 0; i < meses; i++) {
            for (int j = i; j < meses; j++) {
                h = Integer.MAX_VALUE;
                for (int k = 0; k < fornecedores; k++) {
                    if (contratos_matriz[k][i][j] < h) {
                        menoresValor[i][j] = contratos_matriz[k][i][j];
                        fornecedores_matriz[i][j] = k + 1;
                        h = contratos_matriz[k][i][j];
                    }
                }
            }
        }

        double[][] m = new double[meses][meses];
        int[][] s = new int[meses][meses];

        for (int[] item : s) {
            for (int col = 0; col < item.length; col++) {
                item[col] = -1;
            }
        }

        for (int i = 0; i < meses; i++) {
            m[i][i] = menoresValor[i][i];
        }

        for (int l = 1; l < meses; l++) {
            for (int i = 0; i < meses - l; i++) {
                int j = i + l;
                m[i][j] = menoresValor[i][j];
                for (int k = i; k < j; k++) {
                    double q = m[i][k] + m[k + 1][j] + taxa;
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }

        printDinamico(0, meses - 1, menoresValor, fornecedores_matriz, s);

    }

    //Ω (1) e O(m^2)
    public void printDinamico(int i, int j, double[][] menoresValor, int[][] fornecedores_matriz, int[][] s) {

        if (s[i][j] == -1) {

            printAdd("Contrato Fornecedor: " + fornecedores_matriz[i][j] + "\n Mes inicio: " + (i + 1) + "\n Mes Fim:  " + (j + 1) + "\n Valor Unit:  " + menoresValor[i][j]);
            printAdd("-------------------------");

        } else {
            printDinamico(i, s[i][j], menoresValor, fornecedores_matriz, s);
            printDinamico(s[i][j] + 1, j, menoresValor, fornecedores_matriz, s);

        }
    }    // </editor-fold>
}
