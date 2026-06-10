import java.time.LocalDate;

import modelo.MesaRedonda;
import modelo.Minicurso;
import modelo.Palestra;
import modelo.Palestrante;
import modelo.Participante;
import modelo.Participante.TipoParticipante;
import sistema.SistemaEventos;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTAO DE EVENTOS ===");

        SistemaEventos sistema = new SistemaEventos("SGEAC - Semana Academica");

        System.out.println("Cadastrando participantes...");
        Participante participante1 = new Participante("111.111.111-11", "Ana Souza",
                "ana.souza@email.com", LocalDate.of(2002, 4, 12),
                "2026001", TipoParticipante.ESTUDANTE);
        Participante participante2 = new Participante("222.222.222-22", "Bruno Lima",
                "bruno.lima@email.com", LocalDate.of(1998, 8, 20),
                "2026002", TipoParticipante.PROFISSIONAL);
        Participante participante3 = new Participante("333.333.333-33", "Carla Rocha",
                "carla.rocha@email.com", LocalDate.of(1989, 1, 5),
                "2026003", TipoParticipante.DOCENTE);

        sistema.cadastrarParticipante(participante1);
        sistema.cadastrarParticipante(participante2);
        sistema.cadastrarParticipante(participante3);

        System.out.println("Cadastrando palestrantes...");
        Palestrante palestrante1 = new Palestrante("999.999.999-99", "Dra. Marina Costa",
                "marina.costa@email.com", LocalDate.of(1980, 7, 14),
                "Doutora", "Engenharia de Software");
        Palestrante palestrante2 = new Palestrante("888.888.888-88", "Me. Rafael Alves",
                "rafael.alves@email.com", LocalDate.of(1985, 11, 3),
                "Mestre", "Inteligencia Artificial");

        sistema.cadastrarPalestrante(palestrante1);
        sistema.cadastrarPalestrante(palestrante2);

        System.out.println("Cadastrando atividades...");
        Palestra palestra = new Palestra("PAL001", "POO aplicada a sistemas reais",
                2, 100, palestrante1);
        Minicurso minicurso = new Minicurso("MIN001", "Collections em Java",
                4, 30, palestrante2, 35.0);
        MesaRedonda mesa = new MesaRedonda("MES001", "Pesquisa cientifica na graduacao",
                3, 50, palestrante1, "Iniciacao cientifica", "Prof. Eduardo Santos");

        sistema.cadastrarAtividade(palestra);
        sistema.cadastrarAtividade(minicurso);
        sistema.cadastrarAtividade(mesa);

        System.out.println("Inscrevendo participantes...");
        sistema.inscreverParticipanteEmAtividade("2026001", "PAL001");
        sistema.inscreverParticipanteEmAtividade("2026001", "MIN001");
        sistema.inscreverParticipanteEmAtividade("2026002", "MIN001");
        sistema.inscreverParticipanteEmAtividade("2026003", "MES001");

        System.out.println("Registrando avaliacoes do minicurso...");
        minicurso.adicionarAvaliacao(9.0);
        minicurso.adicionarAvaliacao(8.5);
        minicurso.adicionarAvaliacao(10.0);

        System.out.println("Gerando relatorios...");
        sistema.listarParticipantes();
        System.out.println();
        sistema.listarPalestrantes();
        System.out.println();
        sistema.gerarRelatorioGeral();

        System.out.println();
        System.out.printf("Media das avaliacoes do minicurso: %.2f%n",
                minicurso.calcularMediaAvaliacoes());
        System.out.printf("Maior avaliacao: %.2f%n", minicurso.obterMaiorAvaliacao());
        System.out.printf("Menor avaliacao: %.2f%n", minicurso.obterMenorAvaliacao());
    }
}
