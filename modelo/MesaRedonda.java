package modelo;

/**
 * Atividade de mesa redonda com tema e moderador
 */
public class MesaRedonda extends Atividade {
    private String tema;
    private String moderador;

    public MesaRedonda(String codigo, String titulo, int cargaHoraria,
            int capacidadeMaxima, Palestrante palestrante, String tema, String moderador) {
        super(codigo, titulo, cargaHoraria, capacidadeMaxima, palestrante);
        setTema(tema);
        setModerador(moderador);
    }

    @Override
    public double calcularCusto() {
        return 25.0;
    }

    @Override
    public String obterTipoAtividade() {
        return "Mesa Redonda";
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        if (tema == null || tema.trim().isEmpty()) {
            throw new IllegalArgumentException("Tema nao pode ser vazio");
        }
        this.tema = tema.trim();
    }

    public String getModerador() {
        return moderador;
    }

    public void setModerador(String moderador) {
        if (moderador == null || moderador.trim().isEmpty()) {
            throw new IllegalArgumentException("Moderador nao pode ser vazio");
        }
        this.moderador = moderador.trim();
    }
}
