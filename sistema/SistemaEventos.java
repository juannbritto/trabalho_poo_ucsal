package sistema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Atividade;
import modelo.Palestrante;
import modelo.Participante;

/**
 * Classe central que cadastra, busca, inscreve e gera relatorios.
 */
public class SistemaEventos {
    private String nomeEvento;
    private Map<String, Participante> participantesPorMatricula;
    private Map<String, Palestrante> palestrantesPorCpf;
    private List<Atividade> atividades;
    private String[] diasFuncionamento;

    public SistemaEventos(String nomeEvento) {
        setNomeEvento(nomeEvento);
        this.participantesPorMatricula = new HashMap<>();
        this.palestrantesPorCpf = new HashMap<>();
        this.atividades = new ArrayList<>();
        this.diasFuncionamento = new String[7];
        inicializarDias();
    }

    private void inicializarDias() {
        diasFuncionamento[0] = "Segunda-feira";
        diasFuncionamento[1] = "Terca-feira";
        diasFuncionamento[2] = "Quarta-feira";
        diasFuncionamento[3] = "Quinta-feira";
        diasFuncionamento[4] = "Sexta-feira";
        diasFuncionamento[5] = "Sabado";
        diasFuncionamento[6] = "Domingo";
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        if (nomeEvento == null || nomeEvento.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do evento nao pode ser vazio");
        }
        this.nomeEvento = nomeEvento.trim();
    }

    public List<Atividade> getAtividades() {
        return new ArrayList<>(atividades);
    }

    public Map<String, Participante> getParticipantesPorMatricula() {
        return new HashMap<>(participantesPorMatricula);
    }

    public Map<String, Palestrante> getPalestrantesPorCpf() {
        return new HashMap<>(palestrantesPorCpf);
    }

    public String[] getDiasFuncionamento() {
        String[] copia = new String[diasFuncionamento.length];
        for (int i = 0; i < diasFuncionamento.length; i++) {
            copia[i] = diasFuncionamento[i];
        }
        return copia;
    }

    public boolean cadastrarParticipante(Participante participante) {
        if (participante == null) {
            throw new IllegalArgumentException("Participante nao pode ser nulo");
        }
        if (participantesPorMatricula.containsKey(participante.getMatricula())) {
            return false;
        }
        participantesPorMatricula.put(participante.getMatricula(), participante);
        return true;
    }

    public boolean cadastrarPalestrante(Palestrante palestrante) {
        if (palestrante == null) {
            throw new IllegalArgumentException("Palestrante nao pode ser nulo");
        }
        if (palestrantesPorCpf.containsKey(palestrante.getCpf())) {
            return false;
        }
        palestrantesPorCpf.put(palestrante.getCpf(), palestrante);
        return true;
    }

    public boolean cadastrarAtividade(Atividade atividade) {
        if (atividade == null) {
            throw new IllegalArgumentException("Atividade nao pode ser nula");
        }
        if (buscarAtividadePorCodigo(atividade.getCodigo()) != null) {
            return false;
        }
        atividades.add(atividade);
        return true;
    }

    public Participante buscarParticipantePorMatricula(String matricula) {
        if (matricula == null) {
            return null;
        }
        return participantesPorMatricula.get(matricula.trim());
    }

    public Palestrante buscarPalestrantePorCpf(String cpf) {
        if (cpf == null) {
            return null;
        }
        return palestrantesPorCpf.get(cpf.trim());
    }

    public Atividade buscarAtividadePorCodigo(String codigo) {
        if (codigo == null) {
            return null;
        }
        String codigoBuscado = codigo.trim();
        for (Atividade atividade : atividades) {
            if (atividade.getCodigo().equals(codigoBuscado)) {
                return atividade;
            }
        }
        return null;
    }

    public boolean inscreverParticipanteEmAtividade(String matricula, String codigoAtividade) {
        Participante participante = buscarParticipantePorMatricula(matricula);
        Atividade atividade = buscarAtividadePorCodigo(codigoAtividade);

        if (participante == null || atividade == null) {
            return false;
        }
        if (atividade.inscreverParticipante(participante)) {
            participante.adicionarAtividadeFrequentada(atividade);
            return true;
        }
        return false;
    }

    public double calcularFaturamentoTotal() {
        double total = 0.0;
        for (Atividade atividade : atividades) {
            total += atividade.calcularCusto() * atividade.getNumeroParticipantesInscritos();
        }
        return total;
    }

    public double calcularTaxaMediaOcupacao() {
        if (atividades.isEmpty()) {
            return 0.0;
        }
        double soma = 0.0;
        for (Atividade atividade : atividades) {
            soma += atividade.obterTaxaOcupacao();
        }
        return soma / atividades.size();
    }

    public Atividade identificarAtividadeComMaiorOcupacao() {
        if (atividades.isEmpty()) {
            return null;
        }
        Atividade maior = atividades.get(0);
        for (Atividade atividade : atividades) {
            if (atividade.obterTaxaOcupacao() > maior.obterTaxaOcupacao()) {
                maior = atividade;
            }
        }
        return maior;
    }

    public Atividade identificarAtividadeComMenorOcupacao() {
        if (atividades.isEmpty()) {
            return null;
        }
        Atividade menor = atividades.get(0);
        for (Atividade atividade : atividades) {
            if (atividade.obterTaxaOcupacao() < menor.obterTaxaOcupacao()) {
                menor = atividade;
            }
        }
        return menor;
    }

    public void listarAtividadesComOcupacao() {
        System.out.println("Atividades cadastradas:");
        for (Atividade atividade : atividades) {
            System.out.printf("%s | %s | %d/%d inscritos | %.2f%% ocupacao | R$ %.2f%n",
                    atividade.getCodigo(),
                    atividade.getTitulo(),
                    atividade.getNumeroParticipantesInscritos(),
                    atividade.getCapacidadeMaxima(),
                    atividade.obterTaxaOcupacao(),
                    atividade.calcularCusto());
        }
    }

    public void listarParticipantes() {
        System.out.println("Participantes cadastrados:");
        for (Participante participante : participantesPorMatricula.values()) {
            System.out.printf("%s | Matricula: %s | Horas: %d | Custo: R$ %.2f%n",
                    participante,
                    participante.getMatricula(),
                    participante.calcularTotalHoras(),
                    participante.calcularCustoTotal());
        }
    }

    public void listarPalestrantes() {
        System.out.println("Palestrantes cadastrados:");
        for (Palestrante palestrante : palestrantesPorCpf.values()) {
            System.out.printf("%s | %s%n", palestrante, palestrante.descreverEspecialidade());
        }
    }

    public void gerarRelatorioGeral() {
        System.out.println("=== RELATORIO GERAL - " + nomeEvento + " ===");
        listarAtividadesComOcupacao();
        System.out.printf("Faturamento total: R$ %.2f%n", calcularFaturamentoTotal());
        System.out.printf("Taxa media de ocupacao: %.2f%%%n", calcularTaxaMediaOcupacao());

        Atividade maior = identificarAtividadeComMaiorOcupacao();
        Atividade menor = identificarAtividadeComMenorOcupacao();
        if (maior != null) {
            System.out.println("Maior ocupacao: " + maior);
        }
        if (menor != null) {
            System.out.println("Menor ocupacao: " + menor);
        }
    }
}
