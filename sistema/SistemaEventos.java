package sistema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Atividade;
import modelo.Minicurso;
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
}
