<h1 align="center" style="font-size: 10px;">📊 Gerenciamento de Ordens de Manutenção</h1>

<a href="https://pandas.pydata.org/" target="_blank">
    <img src="https://img.shields.io/badge/pandas-150458?style=for-the-badge&logo=pandas&logoColor=white" target="_blank">
</a>
<a href="https://numpy.org/" target="_blank">
    <img src="https://img.shields.io/badge/numpy-013243?style=for-the-badge&logo=numpy&logoColor=white" target="_blank">
</a>
<a href="https://scikit-learn.org/" target="_blank">
    <img src="https://img.shields.io/badge/scikit--learn-F7931E?style=for-the-badge&logo=scikit-learn&logoColor=white" target="_blank">
</a>
<a href="https://seaborn.pydata.org/" target="_blank">
    <img src="https://img.shields.io/badge/seaborn-3776AB?style=for-the-badge&logo=python&logoColor=white" target="_blank">
</a>
<a href="https://matplotlib.org/" target="_blank">
    <img src="https://img.shields.io/badge/matplotlib-013243?style=for-the-badge&logo=python&logoColor=white" target="_blank">
</a>
<a href="https://spring.io/projects/spring-boot" target="_blank">
    <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white">
</a>
<a href="https://maven.apache.org/" target="_blank">
    <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white">
</a>
<a href="https://www.postman.com/" target="_blank">
    <img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white">
</a>
<a href="https://www.postgresql.org/" target="_blank">
    <img src="https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white">
</a>
<a href="https://www.canva.com/design/DAGXQv4643E/OkeoeNYG-SbreaitFX2xHA/edit?utm_content=DAGXQv4643E&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton" target="_blank">
    <img src="https://img.shields.io/badge/Apresentação-00C4CC?style=for-the-badge&logo=canva&logoColor=white">
</a>

> Este projeto foca no gerenciamento de ordens de manutenção em um ambiente industrial. A aplicação permite que líderes de manutenção e técnicos registrem informações sobre equipamentos, usuários e ordens de manutenção. O objetivo é otimizar a gestão das tarefas no chão de fábrica, priorizando atividades e facilitando o acompanhamento do status de cada ordem de trabalho.

---

<div align="center">
  <img src="src\Images\fluxo.gif" alt="Logo">
</div>


## Executando

Para executar o projeto:

1. Certifique-se de ter o Java 17 ou superior instalado.
2. Configure o banco de dados PostgreSQL com as credenciais definidas no `application.properties`:
   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/ordem_manager
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```
3. Rode o comando:
   ```
   ./mvnw spring-boot:run
   ```

## Tecnologias Utilizadas

* **Spring Boot** - Framework para construção da aplicação.
* **Spring Data JPA** - Integração com banco de dados relacional.
* **PostgreSQL** - Banco de dados utilizado.
* **Lombok** - Redução de boilerplate no código.
* **HikariCP** - Pool de conexões de alta performance.
* **Docker** - Contêinerização para facilitar a execução.

## Recursos

1. **Equipamentos**
2. **Usuários**
3. **Ordens de Manutenção**

> Nota: Este projeto fornece uma API RESTful com endpoints para gerenciar os recursos mencionados.

## Regras de Negócio

* Cada ordem de manutenção deve ser associada a um **equipamento** e a um **técnico responsável**.
* O status de uma ordem de manutenção pode ser `Aberta` ou `Finalizada`.
* As prioridades disponíveis são: `Alta`, `Média`, e `Baixa`.
* Somente usuários previamente cadastrados podem ser responsáveis por ordens de manutenção.

## Rotas

### Ordens de Manutenção

#### `GET /ordens`
Lista todas as ordens de manutenção.

Resposta:
```json
[
  {
    "id": 1,
    "descricao": "Troca de correia",
    "prioridade": "Alta",
    "status": "Aberta",
    "equipamentoDescricao": "Esteira transportadora",
    "usuarioNome": "João Técnico"
  }
]
```

#### `GET /ordens/{id}`
Busca uma ordem de manutenção pelo ID.

Resposta:
```json
{
  "id": 1,
  "descricao": "Troca de correia",
  "prioridade": "Alta",
  "status": "Aberta",
  "equipamentoDescricao": "Esteira transportadora",
  "usuarioNome": "João Técnico"
}
```

Respostas de erro:
* `404` - Ordem de manutenção não encontrada.

#### `POST /ordens`

Cadastra uma nova ordem de manutenção.

Corpo da Requisição:
```json
{
  "descricao": "Troca de motor",
  "prioridade": "Média",
  "status": "Aberta",
  "equipamento": {
    "id": 1
  },
  "usuario": {
    "id": 2
  }
}
```

Corpo da Resposta:
```json
{
  "id": 2,
  "descricao": "Troca de motor",
  "prioridade": "Média",
  "status": "Aberta",
  "equipamentoDescricao": "Gerador industrial",
  "usuarioNome": "Maria Técnica"
}
```

Respostas de erro:
* `400` - Equipamento ou usuário inválido.
* `404` - Equipamento ou usuário não encontrado.

#### `PUT /ordens/{id}`
Atualiza as informações de uma ordem de manutenção.

Corpo da Requisição:
```json
{
  "descricao": "Troca de correia ajustada",
  "status": "Finalizada"
}
```

Resposta:
```json
{
  "id": 1,
  "descricao": "Troca de correia ajustada",
  "prioridade": "Alta",
  "status": "Finalizada",
  "equipamentoDescricao": "Esteira transportadora",
  "usuarioNome": "João Técnico"
}
```

Respostas de erro:
* `400` - Status ou prioridade inválidos.
* `404` - Ordem de manutenção não encontrada.

#### `DELETE /ordens/{id}`
Remove uma ordem de manutenção pelo ID.

Resposta:
```json
{
  "message": "Ordem de manutenção excluída com sucesso."
}
```

Respostas de erro:
* `404` - Ordem de manutenção não encontrada.

---

### Equipamentos e Usuários

Endpoints semelhantes são disponibilizados para gerenciar **equipamentos** e **usuários**, permitindo operações de **CRUD**.

---

## Autor

**Renato Ribas**  
