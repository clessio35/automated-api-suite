Feature: Testes de API - NASA Open API

  @apod @all
  Scenario: Obter a imagem astronomica do dia (APOD)
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>" com uma chave de API valida
    Then eu valido que a resposta contém os dados da imagem do dia
    Examples:
      | url                | endpoint         |
      | https://api.nasa.gov | /planetary/apod |

  @mars-photos @all
  Scenario: Obter fotos tiradas pelo rover Curiosity em uma data especifica
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>" com o parametro "<param>" igual a "<data>"
    Then eu valido que a resposta contém as fotos tiradas nessa data
    Examples:
      | url                | endpoint                                      |param        | data       |
      | https://api.nasa.gov | /mars-photos/api/v1/rovers/curiosity/photos |  earth_date | 2023-08-01 |

  @neo-feed @all
  Scenario: Listar asteroides próximos da Terra em uma data específica
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>" com a data inicial "<start_date>" e final "<end_date>"
    Then eu valido que a resposta contém dados dos objetos próximos à Terra nesse período
    Examples:
      | url                | endpoint         | start_date | end_date   |
      | https://api.nasa.gov | /neo/rest/v1/feed | 2023-08-01 | 2023-08-03 |
