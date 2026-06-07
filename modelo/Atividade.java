package modelo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Classe abstrata base para atividades do evento
 */
public abstract class Atividade {
    private String codigo;
    private String titulo;
    private int cargaHoraria;
    private int capacidadeMaxima;
    private Palestrante palestrante;
    private Set<Participante> participantesInscritos;

    public Atividade(String codigo, String titulo, int cargaHoraria,
            int capacidadeMaxima, Palestrante palestrante) {
        setCodigo(codigo);
        setTitulo(titulo);
        setCargaHoraria(cargaHoraria);
        setCapacidadeMaxima(capacidadeMaxima);
        setPalestrante(palestrante);
        this.participantesInscritos = new HashSet<>();
    }

    public abstract double calcularCusto();

    public abstract String obterTipoAtividade();

    public boolean inscreverParticipante(Participante participante) {
        if (participante == null) {
            throw new IllegalArgumentException("Participante nao pode ser nulo");
        }
        if (participantesInscritos.size() >= capacidadeMaxima) {
            return false;
        }
        return participantesInscritos.add(participante);
    }

    public int obterVagasDisponiveis() {
        return capacidadeMaxima - participantesInscritos.size();
    }

    public double obterTaxaOcupacao() {
        return (participantesInscritos.size() * 100.0) / capacidadeMaxima;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Codigo nao pode ser vazio");
        }
        this.codigo = codigo.trim();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Titulo nao pode ser vazio");
        }
        this.titulo = titulo.trim();
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("Carga horaria deve ser maior que zero");
        }
        this.cargaHoraria = cargaHoraria;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        if (capacidadeMaxima <= 0) {
            throw new IllegalArgumentException("Capacidade maxima deve ser maior que zero");
        }
        if (participantesInscritos != null && capacidadeMaxima < participantesInscritos.size()) {
            throw new IllegalArgumentException("Capacidade nao pode ser menor que inscritos");
        }
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public Palestrante getPalestrante() {
        return palestrante;
    }

    public void setPalestrante(Palestrante palestrante) {
        if (palestrante == null) {
            throw new IllegalArgumentException("Palestrante nao pode ser nulo");
        }
        this.palestrante = palestrante;
    }

    public int getNumeroParticipantesInscritos() {
        return participantesInscritos.size();
    }

    public Set<Participante> getParticipantesInscritos() {
        return new HashSet<>(participantesInscritos);
    }

    @Override
    public String toString() {
        return codigo + " - " + titulo + " (" + obterTipoAtividade() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Atividade)) {
            return false;
        }
        Atividade outra = (Atividade) obj;
        return codigo.equals(outra.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
