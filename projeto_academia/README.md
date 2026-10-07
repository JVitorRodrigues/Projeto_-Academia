# Sistema de Gestão de Academia — View, Function e Procedure

## Identificação
- **Aluno:** João Vitor Rodrigues Santos
- **Disciplina:** Banco de Dados
- **Professor:** Anderson Costa

## Sobre o projeto
Aplicação desktop (Java Swing) para gerenciar uma academia: alunos, instrutores, planos, matrículas e pagamentos.
Esta versão evolui o CRUD anterior movendo regras de negócio e consultas para o **banco de dados**
(View, Function e Procedure), em vez de deixá-las espalhadas no código Java.

## Tecnologias
Java 17+ · Java Swing · JDBC (postgresql-42.7.10) · PostgreSQL 15+

## Banco de dados
**SGBD:** PostgreSQL. **Tabelas:** `usuarios`, `alunos`, `instrutores`, `planos`, `matriculas`, `pagamentos` (nova).

| Recurso | Nome | Finalidade | Onde é usado na aplicação |
|---|---|---|---|
| View | `vw_matriculas_detalhadas` | Junta matrículas, alunos, planos, instrutores e pagamentos; calcula situação (Em dia/Vencida/Cancelada) e total pago | Tela **Matrículas** (listagem e filtro) |
| View | `vw_resumo_por_plano` | Dashboard: nº de matrículas (ativas/canceladas) e receita por plano | Tela **Relatório Financeiro** |
| Function | `fn_valor_final_plano(id_plano, id_aluno)` | Calcula o valor do plano com 10% de desconto de fidelidade (aluno que já teve matrícula) | Tela **Matrículas** (valor exibido antes de matricular) e dentro da procedure |
| Function | `fn_situacao_aluno(id_aluno)` | Retorna Ativo / Vencido / Inativo / Sem matrícula | Tela **Alunos** (coluna Situação) |
| Procedure | `sp_matricular_aluno(aluno, plano, instrutor, forma_pgto)` | Em uma transação: valida dados, bloqueia matrícula duplicada, encerra matrículas vencidas, calcula valor (function), cria a matrícula e registra o pagamento | Tela **Matrículas** (botão Matricular) |
| Procedure | `sp_cancelar_matricula(id_matricula)` | Valida e cancela uma matrícula | Tela **Matrículas** (botão Cancelar matrícula) |

Fluxo integrado: `Tela → JDBC (CALL / SELECT) → View/Function/Procedure → resultado na tela`.

## Estrutura do repositório
```
/src                 código-fonte Java
/lib                 driver JDBC do PostgreSQL
/database
  /tables            criação das tabelas
  /functions         functions
  /views             views
  /procedures        procedures
  /inserts           dados de exemplo
  run_all.sh         executa todos os scripts na ordem correta
/docs                diagrama (DER)
```

## Como executar
1. Criar o banco: `CREATE DATABASE academia;`
2. Rodar os scripts **nesta ordem** (tables → functions → views → procedures → inserts):
   - Linux/macOS: `cd database && ./run_all.sh postgres academia`
   - Windows (psql), para cada pasta na ordem acima:
     `psql -U postgres -d academia -f database/tables/01_criar_tabelas.sql` (e assim por diante)
3. Ajustar usuário/senha em `src/ConexaoBD.java` (padrão: `postgres` / `1234`).
4. Compilar e executar:
   ```bash
   # Windows
   cd src
   javac -encoding UTF-8 -cp ".;../lib/postgresql-42.7.10.jar" *.java
   java -cp ".;../lib/postgresql-42.7.10.jar" Login
   # Linux/macOS: trocar ";" por ":"
   ```
5. Login: `admin` / `admin123`.
