# CP 04 - Java

- Augusto Barcelos Barros – RM: 98078
- Gabriel Souza de Queiroz – RM: 98570
- Lucas Pinheiro de Melo – RM: 97707

## Estrutura do Projeto

- **TaskController**: Gerencia as operações relacionadas a tarefas.
- **StatusController**: Gerencia as operações relacionadas ao status.
- **AutenticacaoController**: Gerencia as operações de autenticação de usuários.

### TaskController

Responsável por:

- Listar tarefas
- Buscar uma tarefa específica por ID
- Registrar uma nova tarefa
- Atualizar uma tarefa existente
- Deletar uma tarefa

**Endpoints:**

- `GET /tasks` - Lista todas as tarefas
- `GET /tasks/{id}` - Busca uma tarefa pelo ID
- `POST /tasks` - Registra uma nova tarefa
- `PUT /tasks/{id}` - Atualiza uma tarefa existente
- `DELETE /tasks/{id}` - Deleta uma tarefa pelo ID

### StatusController

Responsável por:

- Listar status
- Buscar um status específico por ID
- Registrar um novo status
- Atualizar um status existente
- Deletar um status

**Endpoints:**

- `GET /public/status` - Lista todos os status
- `GET /public/status/{id}` - Busca um status pelo ID
- `POST /public/status` - Registra um novo status
- `PUT /public/status/{id}` - Atualiza um status existente
- `DELETE /public/status/{id}` - Deleta um status pelo ID

### AutenticacaoController

Responsável por:

- Autenticação de usuários (login)
- Registro de novos usuários

**Endpoints:**

- `POST /auth/login` - Realiza login de um usuário
- `POST /auth/register` - Registra um novo usuário
