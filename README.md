
# Sistema de Locadora de Filmes

Este é um sistema de locadora de filmes implementado em Java. Ele permite o gerenciamento de filmes, locações e usuários, com funcionalidades diferenciadas para administradores e clientes. O sistema oferece opções de login, registro de usuários, aluguel e devolução de filmes, além de permitir o gerenciamento do catálogo de filmes e clientes.

## Funcionalidades

### Para Clientes:
- **Registro e Login**: Os clientes podem criar contas e fazer login no sistema.
- **Aluguel de Filmes**: Clientes podem visualizar filmes disponíveis e alugá-los por um período de 7 dias.
- **Histórico de Locações**: Os clientes podem visualizar o histórico de filmes que alugaram.
- **Devolução de Filmes**: Após o aluguel, os clientes podem devolver os filmes e realizar o pagamento pelo aluguel.
- **Recomendações**: Baseado nos filmes já alugados, o sistema sugere novos filmes aos clientes.

### Para Administradores:
- **Gerenciamento de Filmes**: Os administradores podem adicionar, remover e listar filmes no catálogo da locadora.
- **Gerenciamento de Clientes**: Administradores podem remover clientes do sistema.
- **Visualização do Histórico de Locações**: Administradores podem verificar o histórico de locações de cada cliente.

## Requisitos
- **Java 8 ou superior**

## Como Usar

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/sistema-locadora-de-filmes.git
cd sistema-locadora-de-filmes
```

2. Compile o código:

```bash
javac Main/ProgramaPrincipal.java
```

3. Execute o programa:

```bash
java Main.ProgramaPrincipal
```

4. Siga as instruções na tela para registrar usuários, fazer login, e interagir com o sistema como cliente ou administrador.

## Estrutura do Projeto

O projeto é dividido em diferentes pacotes, com a seguinte estrutura:

- **Pessoas**: Contém as classes relacionadas aos usuários (Clientes e Administradores).
- **Filmes**: Contém as classes relacionadas ao gerenciamento de filmes e locações.
- **Main**: Contém a classe principal (`ProgramaPrincipal`) que gerencia a interação com o usuário e a execução do sistema.
- **Locadora**: Responsável pelo gerenciamento de filmes e clientes.

## Licença

Este projeto está licenciado sob a MIT License - veja o arquivo [LICENSE](LICENSE) para mais detalhes.

