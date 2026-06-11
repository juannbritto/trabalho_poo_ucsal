# trabalho_poo_ucsal

SGEAC - Sistema de Gestao de Eventos Academicos e Cientificos.

Projeto pratico em Java desenvolvido para a disciplina de Programacao Orientada a Objetos.

## Estrutura do projeto

```text
modelo/
  Pessoa.java
  Participante.java
  Palestrante.java
  Atividade.java
  Palestra.java
  Minicurso.java
  MesaRedonda.java
sistema/
  SistemaEventos.java
Main.java
README.md
```

## Requisitos

- JDK 11 ou superior.
- Terminal ou IntelliJ IDEA.

## Compilar pelo terminal

Na pasta raiz do projeto, execute:

```bash
javac modelo/*.java sistema/*.java Main.java
```

## Executar pelo terminal

Depois da compilacao, execute:

```bash
java Main
```

## Compilar em uma pasta separada

Caso queira deixar os arquivos `.class` fora da raiz do projeto:

```bash
mkdir out
javac -d out modelo/*.java sistema/*.java Main.java
java -cp out Main
```

No PowerShell, se a pasta `out` ja existir:

```powershell
New-Item -ItemType Directory -Force out
javac -d out modelo/*.java sistema/*.java Main.java
java -cp out Main
```

## Executar no IntelliJ IDEA

1. Abra a pasta do projeto no IntelliJ IDEA.
2. Confirme que o JDK esta configurado no projeto.
3. Abra o arquivo `Main.java`.
4. Clique no botao Run da classe `Main`.

## Funcionalidades

- Cadastro de participantes com matricula unica.
- Cadastro de palestrantes com CPF unico.
- Cadastro de atividades com codigo unico.
- Inscricao de participantes em atividades.
- Validacao de capacidade maxima e inscricoes duplicadas.
- Busca de participante por matricula.
- Busca de palestrante por CPF.
- Busca de atividade por codigo.
- Relatorios de participantes, palestrantes, atividades, ocupacao e faturamento.
- Avaliacao de minicursos com media, maior nota e menor nota.

## Conceitos aplicados

- Encapsulamento com atributos privados, getters, setters e validacoes.
- Heranca com as classes abstratas `Pessoa` e `Atividade`.
- Polimorfismo nos metodos `calcularCusto()` e `obterTipoAtividade()`.
- Uso de `List`, `Set`, `Map` e vetores.
