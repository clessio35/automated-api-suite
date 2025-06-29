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

	@When("realizo uma request POST para {string} com dados válidos")
	public void realizo_uma_request_post_para_com_dados_válidos(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido que a criação foi bem-sucedida com status {string}")
	public void eu_valido_que_a_criação_foi_bem_sucedida_com_status(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("realizo uma request PUT para {string} com novos dados")
	public void realizo_uma_request_put_para_com_novos_dados(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido que os dados foram atualizados corretamente com status {string}")
	public void eu_valido_que_os_dados_foram_atualizados_corretamente_com_status(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("realizo uma request DELETE para {string}")
	public void realizo_uma_request_delete_para(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido o status de resposta {string}")
	public void eu_valido_o_status_de_resposta(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido a resposta com a lista completa de usuários")
	public void eu_valido_a_resposta_com_a_lista_completa_de_usuários() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido os dados do usuário específico")
	public void eu_valido_os_dados_do_usuário_específico() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido a resposta com a lista completa de carrinhos")
	public void eu_valido_a_resposta_com_a_lista_completa_de_carrinhos() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido os dados do carrinho específico")
	public void eu_valido_os_dados_do_carrinho_específico() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido a resposta com a lista completa de postagens")
	public void eu_valido_a_resposta_com_a_lista_completa_de_postagens() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido os dados da postagem específica")
	public void eu_valido_os_dados_da_postagem_específica() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido a resposta com a lista completa de comentários")
	public void eu_valido_a_resposta_com_a_lista_completa_de_comentários() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido os dados do comentário específico")
	public void eu_valido_os_dados_do_comentário_específico() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido a resposta com a lista completa de tarefas")
	public void eu_valido_a_resposta_com_a_lista_completa_de_tarefas() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido os dados da tarefa específica")
	public void eu_valido_os_dados_da_tarefa_específica() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}
