Feature: Testes de API - DummyJSON Fake REST API

  ### ---------------- Products ---------------- ###

  @list-products @products
  Scenario: Listar todos os produtos
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido a resposta com a lista completa de produtos
    Examples:
      | url              		  | endpoint   |
      | https://dummyjson.com | /products  |

  @product-details @products
  Scenario: Obter detalhes de um produto existente
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>" e id válido
    Then eu valido os dados do produto específico
    Examples:
      | url                | endpoint   |
      | https://dummyjson.com | /products/ |

  @nonexistent-product @products
  Scenario: Obter detalhes de um produto inexistente
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido que o erro retornado tem o status code "<status>"
    Examples:
      | url                | endpoint       | status |
      | https://dummyjson.com | /products/9999 | 404    |
### ---------------- Posts ---------------- ###
	
	@list-posts @posts
	Scenario: Listar todas as postagens
	  Given que acesso a API "<url>"
	  When realizo uma request GET para "<endpoint>"
	  Then eu valido a resposta com a lista completa de postagens
	  Examples:
	    | url                | endpoint |
	    | https://dummyjson.com | /posts    |
	
	@post-details @posts
	Scenario: Obter detalhes de uma postagem existente
	  Given que acesso a API "<url>"
	  When realizo uma request GET para "<endpoint>" e id válido
	  Then eu valido os dados da postagem específica
	  Examples:
	    | url                | endpoint |
	    | https://dummyjson.com | /posts/   |
	
	### ---------------- Comments ---------------- ###
	
	@list-comments @comments
	Scenario: Listar todos os comentários
	  Given que acesso a API "<url>"
	  When realizo uma request GET para "<endpoint>"
	  Then eu valido a resposta com a lista completa de comentários
	  Examples:
	    | url               	 | endpoint  |
	    | https://dummyjson.com | /comments |
	
	@comment-details @comments
	Scenario: Obter detalhes de um comentário existente
	  Given que acesso a API "<url>"
	  When realizo uma request GET para "<endpoint>" e id válido
	  Then eu valido os dados do comentário específico
	  Examples:
	    | url                | endpoint  |
	    | https://dummyjson.com | /comments/|
	
	### ---------------- Todos ---------------- ###
	
	@list-todos @todos
	Scenario: Listar todas as tarefas
	  Given que acesso a API "<url>"
	  When realizo uma request GET para "<endpoint>"
	  Then eu valido a resposta com a lista completa de tarefas
	  Examples:
	    | url                | endpoint |
	    | https://dummyjson.com | /todos    |
	
	@todo-details @todos
	Scenario: Obter detalhes de uma tarefa existente
	  Given que acesso a API "<url>"
	  When realizo uma request GET para "<endpoint>" e id válido
	  Then eu valido os dados da tarefa específica
	  Examples:
	    | url                | endpoint |
	    | https://dummyjson.com | /todos/   |
