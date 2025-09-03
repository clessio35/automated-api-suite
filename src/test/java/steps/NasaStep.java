package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import service.NasaService;

public class NasaStep {
	
	NasaService nasa = new NasaService();
	
	@When("realizo uma request GET para {string} com uma chave de API valida")
	public void realizo_uma_request_get_para_com_uma_chave_de_api_valida(String endpoint) {
	    nasa.requestGETMethodWithAPIKey(endpoint);
	}

	@Then("eu valido que a resposta contém os dados da imagem do dia")
	public void eu_valido_que_a_resposta_contém_os_dados_da_imagem_do_dia() {
	    nasa.validateResponseWithImageData();
	}

	@When("realizo uma request GET para {string} com o parametro {string} igual a {string}")
	public void realizo_uma_request_get_para_com_o_parametro_igual_a(String endpoint, String param, String data) {
	    nasa.requestGETMethodWithAPIKeyAndParam(endpoint, param, data);
	}

	@Then("eu valido que a resposta contém as fotos tiradas nessa data")
	public void eu_valido_que_a_resposta_contém_as_fotos_tiradas_nessa_data() {
	    nasa.validateResponseWithPictureData();
	}

	@When("realizo uma request GET para {string} com a data inicial {string} e final {string}")
	public void realizo_uma_request_get_para_com_a_data_inicial_e_final(String endpoint, String start_date, String end_date) {
	    nasa.requestGETMethodWithInicialDateAndFinalDate(endpoint, start_date, end_date);
	}

	@Then("eu valido que a resposta contém dados dos objetos próximos à Terra nesse período")
	public void eu_valido_que_a_resposta_contém_dados_dos_objetos_próximos_à_terra_nesse_período() {
	    nasa.validateResponseWithEarthDate();
	}

}
