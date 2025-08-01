# 🧪 Automação de Testes de API - automated-api-suite

Automação de testes da API DummyJSON, desenvolvida em Java 17, utilizando RestAssured, Cucumber (BDD), JUnit e Hamcrest. O projeto inclui relatórios detalhados com Extent Reports, geração de evidências em PDF, e execução dos testes tanto localmente (via Jenkins) quanto em ambientes de nuvem, com integração contínua utilizando GitHub Actions, GitLab CI/CD e Azure DevOps.
Agora também conta com análise de qualidade e cobertura de código utilizando o SonarQube.

---

## 🚀 Objetivo

🚀 Objetivo
Automatizar testes de API com foco em:

✅ Testes automatizados para métodos HTTP (GET, POST, PUT, DELETE)

✅ Escrita de cenários com Cucumber (BDD)

✅ Validações com Hamcrest

✅ Geração de evidências em PDF

✅ Integração contínua com GitHub Actions, GitLab CI/CD e Jenkins

✅ Relatórios completos com Extent Reports

✅ Análise de qualidade e cobertura de código com SonarQube

---

## 📊 Relatórios com Extent Reports

O projeto utiliza a biblioteca **Extent Reports** para gerar relatórios visuais e interativos ao final da execução dos testes. Esses relatórios fornecem uma visão clara do status dos testes, incluindo:

- ✔️ Cenários executados
- ✔️ Resultados (pass/fail)
- ✔️ Logs detalhados
- ✔️ Capturas de evidências (quando aplicável)


📈 Análise de Qualidade com SonarQube
Este projeto utiliza o SonarQube para análise contínua da qualidade do código, cobrindo aspectos como:

✅ Cobertura de testes

✅ Identificação de bugs e code smells

✅ Análise de vulnerabilidades e hotspots de segurança

✅ Acompanhamento de métricas de manutenção e legibilidade

### 📸 Exemplo do Sonar

![sonar](./images/sonar.png)

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia           | Finalidade                                        |
|----------------------|--------------------------------------------------|
| Java 17              | Linguagem base                                   |
| Maven                | Gerenciador de dependências                      |
| Cucumber 6           | Testes BDD com escrita de cenários em Gherkin    |
| JUnit 4              | Framework de execução dos testes                 |
| REST-assured 5.2.0   | Testes de APIs REST                              |
| Hamcrest 2.2         | Validações mais expressivas                      |
| Extent Reports 5     | Geração de relatórios interativos de testes     |
| iText 9              | Geração de evidências em PDF                     |
| Java Faker 1.0.2     | Geração de dados dinâmicos para testes           |
| org.json             | Manipulação de objetos JSON                      |

---

## ▶️ Como Executar Localmente

### ✅ Pré-requisitos

- Java 17 instalado  
- Maven 3.8+ instalado  

### ▶️ Comando de execução

```bash
mvn clean test

```

## ⚙️ Integração Contínua

- **GitHub Actions**: Automatização via workflows YAML  
- **Jenkins**: Pipeline definido no Jenkinsfile para execução contínua e agendada

### 📸 Exemplo de Job Agendado no Jenkins

![Jenkins Job](./images/job-jenkins.png)

---

## 📌 Status do Projeto

✅ **CONCLUÍDO**
