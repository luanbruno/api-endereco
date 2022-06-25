package br.edu.infnet.apiendereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.apiendereco.model.domain.Endereco;
import br.edu.infnet.apiendereco.model.service.EnderecoService;
@Component
public class EnderecoLoader implements ApplicationRunner{
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception{
		
		String cep = "1597895565";
		
		Endereco endereco = new Endereco();
		try {
			
			endereco.setBairro("luz");
			endereco.setCep(cep);
			endereco.setComplemento("casa");
			endereco.setLocalidade("rio de janeiro");
			endereco.setLogradouro("nova cidade");
			endereco.setUf("rj");
			enderecoService.incluir(endereco);
			
			//inclusao
			System.out.println("Processo deu certo try1");			
		} catch (Exception e) {
			System.out.println("[ERRO] não gravou 1");
			
			Endereco enderecoBusca = enderecoService.obterPorCep(cep);
			System.out.println("Endereco recuperado atraves do cep ["+cep+"]");
			System.out.printf("%s -  %s, %s  %s  -  %s \n",
			endereco.getLogradouro(),
			endereco.getComplemento(),
			endereco.getBairro(),
			endereco.getLocalidade(),
			endereco.getUf()
			);
			try {
				
			endereco.setCep("265456554");
			enderecoService.incluir(endereco);
			System.out.println("Processo deu certo try2");
			
			}catch (Exception e2) {
				System.out.println("[ERRO] não gravou  2");
				
			}
			for(Endereco end : enderecoService.obterLista()) {
				System.out.printf("[%s] %s -  %s, %s  %s  -  %s \n",
						end.getCep(),
						end.getLogradouro(),
						end.getComplemento(),
						end.getBairro(),
						end.getLocalidade(),
						end.getUf()
						);
			}
			System.out.println("Endereco excluir atraves do cep ["+enderecoBusca.getCep()+"]");
			enderecoService.excluir(enderecoBusca.getId());
			
		}
	}

}
