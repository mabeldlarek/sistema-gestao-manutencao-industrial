# 🛠️ Sistema de Gestão de Manutenção Industrial (CMMS)

Este projeto é um **sistema de gestão de manutenção industrial baseado em microsserviços**, desenvolvido com **Spring Boot 3 e Java 17**, seguindo princípios de **arquitetura orientada a domínio (DDD)** e **separação de responsabilidades**.

O sistema cobre todo o ciclo de manutenção de ativos industriais, integrando equipamentos, sensores, criticidade, ordens de manutenção, execução, estoque de peças e controle de acesso.

---

## 🧩 Microsserviços

### 🔹 Ativos
Responsável pela gestão de:
- **Equipamentos** (dados técnicos, localização, status, hierarquia)
- **Medidores** (valores atuais, mínimos e máximos)
- **Criticidade** dos equipamentos, calculada a partir de uma matriz de impacto:
  - Produção
  - Segurança
  - Meio ambiente
  - Frequência de falha
  - Custo de reparo

Os dados são persistidos em **MongoDB**.

---

### 🔹 Procedimentos
Gerencia os **procedimentos de manutenção**, incluindo:
- Checklist de passos
- Ferramentas e EPIs necessários
- Peças utilizadas
- Riscos associados
- Tempo estimado
- Versionamento e histórico de revisão

---

### 🔹 Ordem de Manutenção
Responsável pelo fluxo completo de manutenção:
- Planos de manutenção (preventiva/preditiva)
- Criação manual ou automática de ordens
- Priorização baseada em criticidade
- Custos estimados e reais
- Registro de modo de falha e causa raiz

Inclui também a **execução da ordem**, com:
- Períodos de trabalho
- Checklists
- Fotos antes/depois
- Observações do executor
- Status e assinatura digital

---

### 🔹 Material e Estoque
Controle de:
- **Peças** em estoque
- Quantidade mínima e localização
- **Consumo de peças** vinculado às ordens de manutenção
- Cálculo de custo total por consumo

---

### 🔹 Identidade e Acesso
- Gestão de **usuários e funcionários**
- Autenticação baseada em **email e senha**
- Segurança com **Spring Security e OAuth2 Resource Server**

---

## ⚙️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Data MongoDB
- Spring Security / OAuth2
- Lombok
- MapStruct
- ModelMapper
- Jakarta Validation
- Maven
- Docker / Docker Compose
- Git Hub Actions

## ⚙️ Diagrama microsserviços
<img width="1522" height="756" alt="image" src="https://github.com/user-attachments/assets/a24a0f57-e4d2-4261-b4d9-c03ec395121b" />

## ⚙️ Fluxo operacional principal
<img width="1749" height="851" alt="image" src="https://github.com/user-attachments/assets/c3562141-5e49-4c1e-9339-3994d7fe5c9a" />

🚀 Projeto suscetível a evolução.
