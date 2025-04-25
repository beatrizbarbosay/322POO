# Simulador de Robôs - MC322

## Sobre o Projeto

Este repositório contém a implementação de um simulador de robôs desenvolvido ao longo de três laboratórios da disciplina MC322 - Programação Orientada a Objetos (Unicamp). O sistema é capaz de testar e analisar o comportamento de robôs em diferentes cenários sem a necessidade de hardware físico. O simulador permite a modelagem de robôs terrestres e aéreos operando em um ambiente tridimensional, com simulação de movimentação, obstáculos e sensores.

## Ambiente de Desenvolvimento

- **IDE**: Visual Studio Code
- **Versão do Java**: 21.0.5

## Conteúdo do Repositório

O projeto é dividido em laboratórios acumulativos, onde cada etapa adiciona novas funcionalidades ao sistema:

### Lab 01
- Implementação básica da classe `Robo`, com atributos como nome e posição (X, Y).
- Criação da classe `Ambiente`, que define os limites do espaço onde os robôs podem se movimentar.
- Toda a lógica foi implementada na classe `Main`, incluindo criação e movimentação de robôs.

### Lab 02
- Refatoração da classe `Robo` como classe base.
- Implementação das subclasses:
  - `RoboTerrestre`, com atributo `velocidadeMaxima`.
  - `RoboAereo`, com atributos `altitude` e `altitudeMaxima`.
- Implementação de duas subclasses específicas para cada tipo de robô:
  - `RoboMinerador`, `RoboBombardeiro`, `RoboAereoFada`, `RoboAereoFantasma`.
- Modificações na classe `Ambiente`:
  - Agora é tridimensional (adição do eixo Z)
  - Agora possui lista de robôs ativos (`ArrayList<Robo>`).
  - Método `adicionarRobo(Robo r)` e atualização de `dentroDosLimites()` para robôs aéreos.
- Testes com movimentação, limites de velocidade e altitude máxima.

### Lab 03
- Refatoração e aprimoramento da classe `Ambiente`:
  - Lista de `Obstaculo`.
  - Detecção de colisões.
- Implementação da classe `Obstaculo`:
  - Utilização do `enum TipoObstaculo` com valores como `PAREDE`, `ARVORE`, `FOGO`, etc.
- Implementação da classe `Sensor` e subclasses:
  - `SensorProximidade`, `SensorQuenteFrio`
- Criação de menu interativo para:
  - Adicionar os robôs no ambiente.
  - Visualizar status dos robôs.
  - Controlar movimentações e ações dos robôs.
  - Acionar sensores.

## Como Executar

### Pré-requisitos
- Java 21 instalado
- VS Code (ou outra IDE) com extensão para Java configurada

### Executando no terminal

1. Clone o repositório:
    ```bash
    git clone https://github.com/beatrizbarbosay/322POO.git
    cd 322POO
    cd "nome do lab (lab01, lab02 ou lab 03)"
2. Compile os arquivos e execute o projeto:
    javac *.java
    java Main


### Diagrama de Classes
![Diagrama de Classes](diagrama_classes.png)

### Descrição

- **Herança**: 
  - `Robo` é a classe base de onde herdam `RoboTerrestre` e `RoboAereo`.
  - `RoboTerrestre` é estendido por `RoboMinerador` e `RoboBombardeiro`.
  - `RoboAereo` é estendido por `RoboAereoFada` e `RoboAereoFantasma`.
  - `Sensor` é uma superclasse abstrata, estendida por `SensorProximidade` e `SensorQuenteFrio`.

- **Composição**:
  - `Ambiente` contém múltiplos `Robo` e `Obstaculo`.
  - `Robo` possui uma lista de sensores (`List<Sensor>`).
  - `Obstaculo` possui um atributo do tipo `TipoObstaculo` (enum).

- **Enum**:
  - `TipoObstaculo` define as características físicas e térmicas dos obstáculos, como altura, se bloqueiam passagem, se são quentes ou frios.

- **Agregação**:
  - Os sensores são agregados aos robôs e podem ser reutilizados entre instâncias.

### Classes Principais:
- `Ambiente`: gerencia os robôs, minerais e obstáculos.
- `Robo`: classe abstrata com movimentação, sensores e detecção.
- `Sensor`: interface para sensores acoplados a robôs.
- `Obstaculo`: elemento físico com tipo definido pelo enum `TipoObstaculo`.

### Sensores:
- `SensorProximidade`: detecta robôs (exceto fantasmas) e obstáculos próximos.
- `SensorQuenteFrio`: detecta obstáculos com propriedades térmicas.

### Robôs:
- `RoboTerrestre` → `RoboMinerador`, `RoboBombardeiro`
- `RoboAereo` → `RoboAereoFada`, `RoboAereoFantasma`