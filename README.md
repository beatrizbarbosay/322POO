# Simulador de Robôs - MC322

## Sobre o Projeto

Este repositório contém a implementação de um simulador de robôs desenvolvido ao longo dos laboratórios da disciplina MC322 - Programação Orientada a Objetos (Unicamp). O projeto evoluiu de um sistema simples de movimentação para uma simulação complexa com múltiplos tipos de robôs, habilidades, missões autônomas e uma arquitetura de software modular.

O simulador permite a modelagem de robôs terrestres e aéreos operando em um ambiente tridimensional, com simulação de movimentação, sensores, interações e execução de tarefas de forma autônoma, com todas as atividades sendo registradas em um arquivo de log.

## Ambiente de Desenvolvimento

- **IDE Sugerida**: Visual Studio Code, IntelliJ IDEA, Eclipse
- **Versão do Java**: 11 ou superior

## Conteúdo do Repositório

O projeto é dividido em laboratórios acumulativos, onde cada etapa adiciona novas funcionalidades ao sistema:

### Lab 01 a 03 (Fundação e Sensores)
- Implementação básica das classes `Robo`, `Ambiente`, e subclasses como `RoboTerrestre` e `RoboAereo`.
- Introdução de um ambiente tridimensional, obstáculos e detecção de colisões.
- Implementação de `Sensor` e suas especializações (`SensorProximidade`, `SensorQuenteFrio`).
- Criação de um menu interativo para controle manual dos robôs.

### Lab 04 (Interfaces, Polimorfismo e Exceções)
- **Generalização com a Interface `Entidade`**: Todas as classes que existem no ambiente (`Robo`, `Obstaculo`) passaram a implementar a interface `Entidade`, permitindo tratamento polimórfico.
- **Sistema de Habilidades com Interfaces**: Foram criadas múltiplas interfaces para adicionar comportamentos específicos aos robôs, como `Comunicavel`, `Sensoreavel`, `Atacante` e `Curavel`.
- **Exceções Personalizadas**: Implementação de um sistema robusto de tratamento de erros com exceções customizadas para controlar o fluxo do programa (`ColisaoException`, `RoboDesligadoException`, etc.).
- **Mapa 3D Explícito**: O `Ambiente` passou a usar uma matriz `TipoEntidade[][][]` para ter um mapa de ocupação explícito do espaço.

### Lab 05 (Arquitetura, Autonomia e Logs)
- **Arquitetura Modular em Pacotes**: O projeto foi totalmente refatorado em pacotes lógicos (`ambiente`, `robo`, `missao`, etc.) para melhorar a organização e o encapsulamento.
- **Composição de Subsistemas**: A classe `Robo` foi redesenhada para ser composta por subsistemas internos (`ControleMovimento`, `GerenciadorSensores`, `ModuloComunicacao`), delegando responsabilidades e tornando o código mais coeso.
- **Agentes Inteligentes e Missões Autônomas**: Um sistema de autonomia foi criado para que robôs executem tarefas sem intervenção do usuário, utilizando a classe abstrata `AgenteInteligente` e a interface `Missao`.
- **Registro de Logs em Arquivo**: Toda a execução de uma missão é registrada em um arquivo de texto (`log_das_missoes.txt`), salvando posições, ativações de sensores e outros eventos importantes.

## Como Compilar e Executar

**Pré-requisitos:**
- Java Development Kit (JDK) 11 ou superior instalado.

**Instruções:**
1.  Navegue pelo terminal até a pasta raiz do projeto (ex: `lab05`).
2.  Compile todo o projeto a partir da pasta raiz com o comando abaixo:
    ```bash
    javac */*.java
    ```
4.  Execute o programa principal:
    ```bash
    java main.Main
    ```

## Diagrama de Classes
*O diagrama UML atualizado para o Lab 5 deve ser inserido aqui como uma imagem.*

![Diagrama de Classes do Lab 5](DIAGRAMA_LAB![diagrama_lab05](https://github.com/user-attachments/assets/91a2ecf5-9d38-476e-a8ab-0bd4b4a098d8)
05.png)

## Descrição da Arquitetura

- **Herança**:
  - `Robo` é a superclasse abstrata de todos os robôs.
  - `AgenteInteligente` estende `Robo` para adicionar comportamento autônomo.
  - As classes concretas (`RoboTerrestre`, `RoboAereo`, `RoboExplorador`) herdam de `Robo` ou `AgenteInteligente`.

- **Composição**:
  - `Robo` **é composto por** `ControleMovimento`, `GerenciadorSensores` e `ModuloComunicacao`. Esta é a principal característica da arquitetura do Lab 5, promovendo alta coesão e baixo acoplamento.
  - `Ambiente` contém uma coleção de `Entidade`.

- **Interfaces**:
  - `Entidade`: Define o contrato para qualquer objeto que pode existir no ambiente.
  - `Missao`: Define o contrato para tarefas autônomas executáveis por um `AgenteInteligente`.
  - `Sensoreavel`, `Comunicavel`, `Atacante`, `Curavel`: Definem "habilidades" que podem ser adicionadas a qualquer robô.

- **Pacotes**:
  - A separação em pacotes organiza o código por responsabilidade, tornando o sistema mais fácil de manter e expandir.

### Classes e Interfaces Principais:
- `Main`: Ponto de entrada do programa e menu interativo.
- `Ambiente`: Gerencia o mapa, as entidades e as regras do mundo.
- `Robo`: Classe base abstrata para todos os robôs, coordenando seus subsistemas.
- `AgenteInteligente`: Especialização de `Robo` para executar missões.
- `Missao`: Interface para definir tarefas autônomas.
- `Logger`: Classe utilitária para registrar eventos em um arquivo de texto.
- `ControleMovimento`, `GerenciadorSensores`, `ModuloComunicacao`: Subsistemas que encapsulam a lógica principal do robô.
