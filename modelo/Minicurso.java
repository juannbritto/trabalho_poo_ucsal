package modelo;

/**
 * Atividade com custo de material e vetor de avaliações
 */
public class Minicurso extends Atividade {
    private double custoMaterial;
    private double[] avaliacoes;
    private int numAvaliacoes;
    private static final int TAMANHO_MAXIMO = 5;

    public Minicurso(String codigo, String titulo, int cargaHoraria,
            int capacidadeMaxima, Palestrante palestrante, double custoMaterial) {
        super(codigo, titulo, cargaHoraria, capacidadeMaxima, palestrante);
        setCustoMaterial(custoMaterial);
        this.avaliacoes = new double[TAMANHO_MAXIMO];
        this.numAvaliacoes = 0;
    }

    @Override
    public double calcularCusto() {
        return custoMaterial;
    }

    @Override
    public String obterTipoAtividade() {
        return "Minicurso";
    }

    public boolean adicionarAvaliacao(double nota) {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("Nota deve estar entre 0 e 10");
        }
        if (numAvaliacoes < TAMANHO_MAXIMO) {
            avaliacoes[numAvaliacoes] = nota;
            numAvaliacoes++;
            return true;
        }
        return false;
    }

    public double calcularMediaAvaliacoes() {
        if (numAvaliacoes == 0) {
            return 0.0;
        }
        double soma = 0.0;
        for (int i = 0; i < numAvaliacoes; i++) {
            soma += avaliacoes[i];
        }
        return soma / numAvaliacoes;
    }

    public double obterMaiorAvaliacao() {
        if (numAvaliacoes == 0) {
            return 0.0;
        }
        double maior = avaliacoes[0];
        for (int i = 1; i < numAvaliacoes; i++) {
            if (avaliacoes[i] > maior) {
                maior = avaliacoes[i];
            }
        }
        return maior;
    }

    public double obterMenorAvaliacao() {
        if (numAvaliacoes == 0) {
            return 0.0;
        }
        double menor = avaliacoes[0];
        for (int i = 1; i < numAvaliacoes; i++) {
            if (avaliacoes[i] < menor) {
                menor = avaliacoes[i];
            }
        }
        return menor;
    }

    public double getCustoMaterial() {
        return custoMaterial;
    }

    public void setCustoMaterial(double custoMaterial) {
        if (custoMaterial < 0) {
            throw new IllegalArgumentException("Custo do material nao pode ser negativo");
        }
        this.custoMaterial = custoMaterial;
    }

    public double[] getAvaliacoes() {
        double[] copia = new double[numAvaliacoes];
        for (int i = 0; i < numAvaliacoes; i++) {
            copia[i] = avaliacoes[i];
        }
        return copia;
    }

    public int getNumAvaliacoes() {
        return numAvaliacoes;
    }
}
