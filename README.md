# 📄 LINK PARA RELATÓRIO: [Acessar Documento no Google Docs](https://docs.google.com/document/d/1arGqex_Mhc-SbWVHiJKaWqHJmO9eN-SoyDhenreW-1s/edit?usp=sharing)
> **Relatório do Trabalho:** https://docs.google.com/document/d/1arGqex_Mhc-SbWVHiJKaWqHJmO9eN-SoyDhenreW-1s/edit?usp=sharing

---

# tpa2026 - Sistema de Gestão de Contatos

Repositório das atividades da matéria de **Técnicas de Programação Avançada (2026/02)**.  
Implementação de estrutura de dados (`ListaEncadeada`) com suporte a busca e remoção por nome e telefone, além de ordenação customizada via `Comparator`.

---

## 📁 Estrutura do Projeto

```text
├── app/
│   ├── dados/          # Arquivos de teste (1.000 a 400.000 contatos)
│   └── src/            # Código-fonte Java
│       ├── Main.java
│       ├── datastructures/
│       ├── domain/
│       ├── repository/
│       ├── services/
│       └── view/
└── dados/              # Cópias dos arquivos de dados na raiz
```

---

## 🚀 Como Executar

### Opção 1: Pela IDE (IntelliJ IDEA, VS Code, Eclipse)
1. Abra a pasta do projeto na sua IDE.
2. Certifique-se de configurar o **SDK Java 21 ou superior**.
3. Navegue até `app/src/Main.java` e execute (`Run`).

---

### Opção 2: Pelo Terminal

#### No Windows (PowerShell):
```powershell
# 1. Compilar os fontes para a pasta bin
javac -d bin (Get-ChildItem -Recurse -Filter *.java app/src | ForEach-Object { $_.FullName })

# 2. Executar
java -cp bin Main
```

#### No Linux / Mac (Bash):
```bash
# 1. Compilar
javac -d bin $(find app/src -name "*.java")

# 2. Executar
java -cp bin Main
```

---

## 📊 Arquivos de Entrada e Testes de Carga

No menu de carregamento de dados (Opção 1), você pode carregar as bases pré-geradas na pasta `dados/`:
- `dados/arquivo1000.txt` (1.000 contatos - **padrão ao apertar Enter**)
- `dados/arquivo100000.txt` (100.000 contatos)
- `dados/arquivo200000.txt` (200.000 contatos)
- `dados/arquivo400000.txt` (400.000 contatos)

O sistema mede e exibe o tempo decorrido em milissegundos (`ms`) para operações de inserção, busca e remoção (inclusive quando o registro não é encontrado), facilitando a coleta de métricas para o relatório.

