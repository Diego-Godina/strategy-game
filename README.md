# Jogo de Estratégia Militar

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![POO](https://img.shields.io/badge/Programação%20Orientada%20a%20Objetos-blue?style=for-the-badge)
![Instituto Politécnico de Portalegre](https://img.shields.io/badge/Instituto%20Politécnico%20de%20Portalegre-lightgrey?style=for-the-badge)

Projeto desenvolvido para a disciplina de **Programação Orientada a Objetos em Java**, no **Instituto Politécnico de Portalegre**, Portugal.

## 📚 Descrição

Este projeto consiste na criação de um **jogo de estratégia militar**, no qual o jogador pode criar diferentes unidades de combate, agrupá-las em exércitos e simular batalhas entre eles, utilizando os princípios de **Programação Orientada a Objetos (POO)**.

A simulação ocorre num tabuleiro de dimensão definida pelo utilizador e permite diversas interações e decisões estratégicas, como movimentação, alcance e combate entre unidades.

## ⚙️ Funcionalidades

- **Criação de Unidades**
    - Guerreiros com blindagem
    - Arqueiros com escolha de arma (arco ou besta)
    - Feiticeiros com limite de feitiços por simulação

- **Gestão de Exércitos**
    - Criação, listagem e organização de unidades

- **Configuração do Tabuleiro**
    - Definição do tamanho (linhas e colunas)
    - Posicionamento manual ou aleatório dos exércitos

- **Simulação de Combate**
    - Movimentação automática das unidades
    - Combate com base no alcance, força e tipo de unidade
    - Exibição do estado após cada passo da simulação

- **Validação de Dados**
    - Entrada de dados robusta com tratamento de exceções
    - Validação de posições e colisões

- **Finalização Segura**
    - Opção para terminar o jogo de forma segura pelo utilizador

## 🛠️ Tecnologias Utilizadas

- **Java** (JDK 17 ou superior)
- **Programação Orientada a Objetos**
- **Conceitos de herança, polimorfismo e encapsulamento**

## 🏗️ Estrutura Principal do Projeto

## 🗂️ Instruções de Execução

1. Certifique-se de ter o **Java JDK 17+** instalado no sistema.
2. Compile os ficheiros:

```bash
javac -d bin src/*.java
