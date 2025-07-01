package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import service.DummyService;

public class DummyStep {
	
	DummyService service = new DummyService();
	
	@Given("que acesso a API {string}")
	public void que_acesso_a_api(String url) {
	    service.accessApi(url);
	}

	@When("realizo uma request GET para {string}")
	public void realizo_uma_request_get_para(String endpoint) {
	    service.sendRequestGETMethod(endpoint);
	}

	@Then("eu valido a resposta com a lista completa de produtos")
	public void eu_valido_a_resposta_com_a_lista_completa_de_produtos() {
	    service.validateCompleteProductsList();
	}

	@When("realizo uma request GET para {string} e id válido")
	public void realizo_uma_request_get_para_e_id_válido(String endpoint) {
		service.sendRequestGETMethodWithIdUser(endpoint);
	}

	@Then("eu valido os dados do produto específico")
	public void eu_valido_os_dados_do_produto_específico() {
	    service.validateResponseSpecificProduct();
	}

	@Then("eu valido que o erro retornado tem o status code {string}")
	public void eu_valido_que_o_erro_retornado_tem_o_status_code(String statusCode) {
	    service.validateResponseWithError(statusCode);
	}

	@Then("eu valido a resposta com a lista completa de postagens")
	public void eu_valido_a_resposta_com_a_lista_completa_de_postagens() {
	    service.validateResponseWithCompleteLists();
	}

	@Then("eu valido os dados da postagem específica")
	public void eu_valido_os_dados_da_postagem_específica() {
	    service.validateResponseWithSpecificPost();
	}

	@Then("eu valido a resposta com a lista completa de comentários")
	public void eu_valido_a_resposta_com_a_lista_completa_de_comentários() {
	    service.validateCompleteListComments();
	}

	@Then("eu valido os dados do comentário específico")
	public void eu_valido_os_dados_do_comentário_específico() {
	    service.validateResponseSpecificComment();
	}

	@Then("eu valido a resposta com a lista completa de tarefas")
	public void eu_valido_a_resposta_com_a_lista_completa_de_tarefas() {
	    service.validateResponseWithCompleteTasksList();
	}

	@Then("eu valido os dados da tarefa específica")
	public void eu_valido_os_dados_da_tarefa_específica() {
	    service.validateResponseSpecificTask();
	}
}
