# Gallywix Exchange — Tarefa do Curso

Este repositório contém uma pequena aplicação Java usada em uma tarefa de curso para buscar cotações de moedas e converter valores usando a API ExchangeRate (v6).

## Objetivo

- Mostrar como ler entrada do usuário (valor e seleção de moedas).
- Montar uma URL para consumir uma API externa de câmbio.
- Fazer uma requisição HTTP, analisar o JSON de resposta e exibir o resultado da conversão.

## Recursos e bibliotecas usados

- Java (recomenda-se Java 11 ou superior) — para executar a aplicação e usar a API `java.net.http.HttpClient` moderna.
- Biblioteca Gson (com.google.gson) — para manipulação de JSON (criar/ler `JsonObject` e `JsonElement`).
- API ExchangeRate (https://www.exchangerate-api.com) — provê a cotação entre pares de moedas via endpoint `/v6/{API_KEY}/pair/{FROM}/{TO}`.
- `Scanner` (java.util.Scanner) — para leitura de entrada do usuário (valor numérico e seleção de moeda por número).
- Variável de ambiente `API_KEY` — a chave da API é lida do ambiente (não está preservada no código).

## Como o código está organizado

- `src/Main.java` — arquivo principal que:
  - Lista as moedas suportadas (array em memória);
  - Pede ao usuário que escolha a moeda de origem e destino por número;
  - Pede o valor a ser convertido;
  - Monta a URL de requisição e envia a chamada HTTP;
  - Lê o JSON de resposta e extrai `conversion_rate` para calcular o valor convertido.

## Observações importantes

- A aplicação espera que a variável de ambiente `API_KEY` esteja definida antes da execução. Se não estiver, o programa lança uma exceção informando que a chave não foi configurada.
- A aplicação usa a biblioteca Gson — ao compilar/rodar localmente você precisa adicionar o JAR do Gson ao classpath ou gerenciar dependências via um sistema de build (Maven/Gradle).
- Entradas do usuário são validadas: a seleção de moeda é feita por número e a entrada deve estar dentro do intervalo disponível.
- O código faz um simples tratamento de exceções na chamada HTTP e na leitura da resposta.

## Pontos de extensão (próximos passos sugeridos)

- Adicionar suporte a mais moedas ou carregar a lista dinamicamente.
- Tratar corretamente formatos de número com vírgula/ponteiro decimal de acordo com o locale do usuário.
- Adicionar testes unitários para as funções de parsing e montagem de URL.
- Migrar o projeto para Maven/Gradle para facilitar o gerenciamento de dependências (Gson) e a execução.

---

Se quiser, eu posso:
- adicionar um pequeno `pom.xml` ou `build.gradle` para gerenciar o Gson automaticamente;
- incluir instruções de compilação/execução passo a passo com exemplos de comandos; ou
- extrair a lógica de cálculo para uma função/teste separada.

Diga qual desses extras prefere que eu adicione.
