#  Sistema de Gestão de Academia

Sistema desktop desenvolvido em **Java Swing** para gerenciamento de uma academia, permitindo controlar alunos, instrutores, planos, matrículas e pagamentos.

O projeto utiliza **PostgreSQL** como banco de dados e aplica recursos como **Views, Functions e Procedures** para centralizar consultas e regras de negócio no banco de dados.

---

##  Sobre o Projeto

O **Sistema de Gestão de Academia** foi desenvolvido para facilitar o gerenciamento das principais informações de uma academia.

A aplicação possui uma interface desktop desenvolvida em **Java Swing**, integrada ao PostgreSQL por meio de **JDBC**.

Nesta versão, as regras de negócio e consultas foram aprimoradas utilizando recursos do próprio banco de dados, reduzindo a concentração dessas responsabilidades no código Java.

### Principais recursos

-  Gerenciamento de alunos
-  Gerenciamento de instrutores
-  Gerenciamento de planos
-  Controle de matrículas
-  Registro de pagamentos
-  Relatório financeiro
-  Consulta detalhada de matrículas
-  Sistema de login
-  Regras de negócio implementadas no banco de dados

---

## Tecnologias Utilizadas

| Tecnologia | Utilização |
|---|---|
| **Java 17+** | Desenvolvimento da aplicação |
| **Java Swing** | Interface gráfica |
| **JDBC** | Comunicação entre Java e PostgreSQL |
| **PostgreSQL 15+** | Banco de dados |
| **PostgreSQL JDBC Driver 42.7.10** | Conexão com o banco |

---

##  Estrutura do Banco de Dados

O banco de dados utiliza as seguintes tabelas:

- `usuarios`
- `alunos`
- `instrutores`
- `planos`
- `matriculas`
- `pagamentos`

### Recursos implementados no banco

| Tipo | Nome | Descrição |
|---|---|---|
| **View** | `vw_matriculas_detalhadas` | Apresenta informações detalhadas das matrículas, incluindo aluno, plano, instrutor, pagamentos e situação da matrícula. |
| **View** | `vw_resumo_por_plano` | Apresenta informações para o relatório financeiro, incluindo matrículas e receita por plano. |
| **Function** | `fn_valor_final_plano` | Calcula o valor final do plano considerando desconto de fidelidade. |
| **Function** | `fn_situacao_aluno` | Retorna a situação atual do aluno. |
| **Procedure** | `sp_matricular_aluno` | Realiza o processo de matrícula, valida os dados, calcula o valor e registra o pagamento. |
| **Procedure** | `sp_cancelar_matricula` | Realiza o cancelamento de uma matrícula. |

---

##  Fluxo da Aplicação

```text
┌──────────────┐
│     Tela     │
└──────┬───────┘
       │
       ▼
┌──────────────┐
│     JDBC     │
└──────┬───────┘
       │
       ▼
┌────────────────────────┐
│ PostgreSQL             │
│                        │
│ Views                  │
│ Functions              │
│ Procedures             │
└───────────┬────────────┘
            │
            ▼
┌────────────────────────┐
│ Resultado na aplicação │
└────────────────────────┘
```

---

##  Estrutura do Projeto

```text
/
├── src/
│   └── Código-fonte Java
│
├── lib/
│   └── Driver JDBC do PostgreSQL
│
├── database/
│   ├── tables/
│   │   └── Criação das tabelas
│   │
│   ├── functions/
│   │   └── Functions do banco
│   │
│   ├── views/
│   │   └── Views do banco
│   │
│   ├── procedures/
│   │   └── Procedures do banco
│   │
│   ├── inserts/
│   │   └── Dados de exemplo
│   │
│   └── run_all.sh
│
└── docs/
    └── Diagrama DER
```

---

##  Como Executar

### 1. Criar o banco de dados

No PostgreSQL, execute:

```sql
CREATE DATABASE academia;
```

### 2. Executar os scripts do banco

Os scripts devem ser executados na seguinte ordem:

```text
1. tables
2. functions
3. views
4. procedures
5. inserts
```

#### Windows

Utilizando o `psql`:

```bash
psql -U postgres -d academia -f database/tables/01_criar_tabelas.sql
```

Depois, execute os demais scripts seguindo a ordem das pastas.

---

##  Configuração da Conexão

Antes de executar a aplicação, configure os dados de acesso ao PostgreSQL no arquivo:

```text
src/ConexaoBD.java
```

Configuração padrão utilizada pelo projeto:

```text
Usuário: postgres
Senha: 1234
Banco: academia
```

##  Executando a Aplicação

### Windows

Entre na pasta `src`:

```bash
cd src
```

Compile o projeto:

```bash
javac -encoding UTF-8 -cp ".;../lib/postgresql-42.7.10.jar" *.java
```

Execute a aplicação:

```bash
java -cp ".;../lib/postgresql-42.7.10.jar" Login
```

##  Acesso ao Sistema

Utilize as credenciais padrão:

```text
Usuário: admin
Senha: admin123
```

---

##  Funcionalidades do Banco

O projeto utiliza o banco de dados não apenas para armazenar informações, mas também para executar parte das regras de negócio.

### Views

As Views facilitam a consulta e organização das informações utilizadas pela aplicação, principalmente na tela de matrículas e no relatório financeiro.

### Functions

As Functions são utilizadas para realizar cálculos e determinar informações específicas, como o valor final de um plano e a situação atual de um aluno.

### Procedures

As Procedures concentram operações mais complexas, como realizar uma matrícula ou cancelar uma matrícula, garantindo que as regras definidas sejam executadas diretamente no banco de dados.

---
## Vídeo Demonstrativo

Link: https://drive.google.com/file/d/14fwpOFYc3mA-trRqMYSj_LOqe7VZpFWh/view?usp=sharing 

##  Objetivo Acadêmico

O projeto tem como objetivo demonstrar, na prática, a utilização de recursos avançados de **Banco de Dados**, integrando uma aplicação Java a um banco PostgreSQL e utilizando:

- Views;
- Functions;
- Procedures;
- Transações;
- Consultas SQL;
- JDBC;
- Regras de negócio no banco de dados.



**João Vitor Rodrigues Santos**

Projeto desenvolvido para a disciplina de **Banco de Dados**.
