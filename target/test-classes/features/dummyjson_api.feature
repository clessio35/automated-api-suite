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

  @create-valid-product @products
  Scenario: Criar produto com dados válidos
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>" com dados válidos
    Then eu valido que a criação foi bem-sucedida com status "<status>"
    Examples:
      | url                | endpoint       | status |
      | https://dummyjson.com | /products/add | 200    |

  @update-product @products
  Scenario: Atualizar um produto existente
    Given que acesso a API "<url>"
    When realizo uma request PUT para "<endpoint>" com novos dados
    Then eu valido que os dados foram atualizados corretamente com status "<status>"
    Examples:
      | url                | endpoint    | status |
      | https://dummyjson.com | /products/1 | 200    |

  @delete-product @products
  Scenario: Excluir produto existente
    Given que acesso a API "<url>"
    When realizo uma request DELETE para "<endpoint>"
    Then eu valido o status de resposta "<status>"
    Examples:
      | url                | endpoint    | status |
      | https://dummyjson.com | /products/1 | 200    |

  ### ---------------- Users ---------------- ###

  @list-users @users
  Scenario: Listar todos os usuários
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido a resposta com a lista completa de usuários
    Examples:
      | url                | endpoint |
      | https://dummyjson.com | /users    |

  @user-details @users
  Scenario: Obter detalhes de um usuário existente
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>" e id válido
    Then eu valido os dados do usuário específico
    Examples:
      | url                | endpoint |
      | https://dummyjson.com | /users/   |

  @create-valid-user @users
  Scenario: Criar usuário com dados válidos
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>" com dados válidos
    Then eu valido que a criação foi bem-sucedida com status "<status>"
    Examples:
      | url                | endpoint   | status |
      | https://dummyjson.com | /users/add | 200    |

  @update-user @users
  Scenario: Atualizar dados de um usuário existente
    Given que acesso a API "<url>"
    When realizo uma request PUT para "<endpoint>" com novos dados
    Then eu valido que os dados foram atualizados corretamente com status "<status>"
    Examples:
      | url                | endpoint  | status |
      | https://dummyjson.com | /users/1  | 200    |

  @delete-user @users
  Scenario: Excluir usuário existente
    Given que acesso a API "<url>"
    When realizo uma request DELETE para "<endpoint>"
    Then eu valido o status de resposta "<status>"
    Examples:
      | url                | endpoint  | status |
      | https://dummyjson.com | /users/1  | 200    |

  ### ---------------- Carts ---------------- ###

  @list-carts @carts
  Scenario: Listar todos os carrinhos
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido a resposta com a lista completa de carrinhos
    Examples:
      | url                | endpoint |
      | https://dummyjson.com | /carts    |

  @cart-details @carts
  Scenario: Obter detalhes de um carrinho existente
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>" e id válido
    Then eu valido os dados do carrinho específico
    Examples:
      | url                | endpoint |
      | https://dummyjson.com | /carts/   |

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
      | url                | endpoint  |
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
