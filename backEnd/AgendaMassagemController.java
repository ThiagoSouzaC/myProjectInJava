/**
 * Intranet - UNIDADE DE TESOURARIA GLOBAL
 *
 * @author T1091599
 *
 * @create: 14 de fev de 2023
 *
 */
package br.com.bb.intranet.tesouraria.ecoa.agendaMassagem.controler;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.bb.intranet.tesouraria.ecoa.agendaMassagem.dao.AgendaDAO;
import br.com.bb.intranet.tesouraria.ecoa.agendaMassagem.model.Agenda;
import br.com.bb.intranet.tesouraria.ecoa.agendaMassagem.model.AgendamentoShiatsu;
import br.com.bb.intranet.tesouraria.gepro.infor.admin.dao.ConfiguracaoDAO;
import br.com.bb.intranet.tesouraria.gepro.infor.admin.dao.LogAcessosDAO;
import br.com.bb.intranet.tesouraria.publico.usuario.controller.UsuarioLogadoController;
import br.com.bb.intranet.tesouraria.util.FuncoesUteis;
import br.com.bb.intranet.tesouraria.util.VerificaPermissao;

/**
 * @author T1091599
 *
 */
@Controller
public class AgendaMassagemController {
	
	@Autowired 
	protected ServletContext servletContext;
	
	
	@Autowired
	private UsuarioLogadoController usuarioLogado;
	
	@Autowired
	private LogAcessosDAO logAcessosDAO;
	
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));		
	}
	
	
	//################################################################################################
	// CHAMA UM FORMULARIO PARA PREENCHIMENTO DE UM NOVO AGENDAMENTO
	//################################################################################################
	
	@RequestMapping(value = "/publico/ecoa/agendaMassagem", method = RequestMethod.GET)
	public String abreAgenda (HttpServletRequest request, 
				 					HttpServletResponse response, 
				 					Model model,
				 					@RequestParam(value="idAgenda",required=false) int idAgenda) {
		
		

		
		 ConfiguracaoDAO configuracao = new ConfiguracaoDAO(); 
		 if (configuracao.buscarPorCodigo(1).getTituloIntranetAdministrativo().
		 equalsIgnoreCase("PRODUÇÃO")){
		   if  (!VerificaPermissao.verificaFunciTesou(usuarioLogado.pegaUsuario())){ 
			   
			   return "redirect:/semPerfil?stringPerfil=Somente funcionarios Tesou."; }
			  
		  }
		 
		 Agenda agenda = AgendaDAO.buscarAgenda(idAgenda);
		 model.addAttribute("agenda", agenda);	
		 model.addAttribute("usuario", usuarioLogado.pegaUsuario());
		
		 //Verifica os agendamentos do usuário logado.
//		 AgendaDAO.buscaAgendamentosUsuario(idAgenda, usuarioLogado.pegaUsuario());
		
		return "publico/ecoa/agendaMassagem";
		
	}
	
	
	//################################################################################################
	// CHAMA UM FORMULARIO PARA ABERTUDA DE NOVA AGENDA PELA ECOA
	//################################################################################################

	@RequestMapping(value = "/publico/ecoa/agendaData", method = RequestMethod.GET)
	public String agenda (HttpServletRequest request, HttpServletResponse response,Model model) {
		
		

		
		ConfiguracaoDAO configuracao = new ConfiguracaoDAO(); 
		 
		if (configuracao.buscarPorCodigo(1).getTituloIntranetAdministrativo().equalsIgnoreCase("PRODUÇÃO")){
		  
			 if
			  (!VerificaPermissao.verificaFunciTesou(usuarioLogado.pegaUsuario())){ 
//TODO - CONTROLE DE ACESSO SOMENTE ECOA - SE FOR NECESSÁRIO, SENÃO PODE SER TESOU			   
			   return "redirect:/semPerfil?stringPerfil=Somente funcionarios Tesou."; }
			  
		 }
		 
		 
		Agenda agenda = new Agenda();
		
		model.addAttribute("agenda", agenda);
		 

		
		return "publico/ecoa/formAgendaData";
		
	}

	
	//################################################################################################
	// CHAMA UM FORMULARIO PARA ABERTURA DE NOVA AGENDA PELA ECOA
	//################################################################################################

//	@RequestMapping(value = "/publico/ecoa/novoAgendamento", method = RequestMethod.GET)
//	public String agendarHorario (HttpServletRequest request, HttpServletResponse response,Model model,
//			@RequestParam("ramal") String ramal,
//			@RequestParam("idAgenda") int idAgenda,
//			@RequestParam("horario") String horario) {
//		
//
//		
//		ConfiguracaoDAO configuracao = new ConfiguracaoDAO(); 
//		 
//		if (configuracao.buscarPorCodigo(1).getTituloIntranetAdministrativo().equalsIgnoreCase("PRODUÇÃO")){
//		  
//			 if
//			  (!VerificaPermissao.verificaFunciTesou(usuarioLogado.pegaUsuario())){ 
////TODO - CONTROLE DE ACESSO SOMENTE ECOA - SE FOR NECESSÁRIO, SENÃO PODE SER TESOU			   
//			   return "redirect:/semPerfil?stringPerfil=Somente funcionarios Tesou."; }
//			  
//		 }
//		
//		
//		
//		 Agenda agenda = AgendaDAO.buscarAgendamento(agenda, usuarioLogado);
//		 model.addAttribute("agenda", agenda);	
//		 model.addAttribute("usuario", usuarioLogado.pegaUsuario());
//		 
//
//		
//		return "publico/ecoa/agendaMassagem";
//		
//	}	
//	
	
	
	
	
	
	//CHAMA LISTA DE DATAS DISPONÍVEIS NA AGENDA.
	@RequestMapping(value = "/publico/ecoa/agendaDataUsu", method = RequestMethod.GET)
	public String agendaUsu (HttpServletRequest request, HttpServletResponse response,Model model) {
		
		

		
		 ConfiguracaoDAO configuracao = new ConfiguracaoDAO(); if
		 (configuracao.buscarPorCodigo(1).getTituloIntranetAdministrativo().
		 equalsIgnoreCase("PRODUÇÃO")){
		   if
			  (!VerificaPermissao.verificaFunciTesou(usuarioLogado.pegaUsuario())){ 
			   
			   return "redirect:/semPerfil?stringPerfil=Somente funcionarios Tesou."; }
			  
		  }
		 
		 

		 List<Agenda> agendaLista =  new ArrayList<Agenda>(); 
		 
		 
		 agendaLista = AgendaDAO.buscarListaAgenda();
		 
		 
		 if (agendaLista != null && agendaLista.size() > 0) {
				
		      PagedListHolder<Agenda> pagedListHolder = new PagedListHolder<Agenda>(agendaLista);
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				pagedListHolder.setPage(page);
				int pageSize = 20;
				pagedListHolder.setPageSize(pageSize);
				
				model.addAttribute("pagedListHolder", pagedListHolder);				
				
			} else {
				model.addAttribute("pagedListHolder",null);
			}
		 
		 
		 
		 model.addAttribute("agendaLista", agendaLista);
		 
		return "publico/ecoa/agendaDataUsu";
		
	}
	
    //#########################################################################
	// GRAVA UMA NOVA AGENDA NO BANCO DE DADOS APOS O SUBMIT DO FORMULARIO - ECOA
	//#########################################################################
	@RequestMapping(value = "/publico/ecoa/agendaData", method = RequestMethod.POST)
	public String novaAgendaFormPost (
			@ModelAttribute("agenda") Agenda agenda,
			BindingResult result,HttpServletRequest request, HttpServletResponse response,
			 Model model) {

	
		
		
		if (agenda.getData()==null) {
			model.addAttribute("msgErro", "ok");
			result.rejectValue("Data", "error.data", "Informe a data!");
		}
		
		if (FuncoesUteis.isNullOrBlank(agenda.getNomeProf())) {
			result.rejectValue("nomeProf", "error.nomeProf", "Você deve informar o nome do profissional para prosseguir!");
			model.addAttribute("msgErro", "ok");
		}
		

		
		
		// FECHA VALIDACAO NO SERVIDOR
		// A VALIDACAO ACIMA - TRABALHA COM REQUISAO NA VIEW VIA AJAX MANTENDO 
		// OS VALORES NO FORM CASO TENHA DEVOLUCAO POR CONTA DE ERROS
		// E AINDA MANTEM LINDAMENTE OS SELECTS COM OS BINDS PREENCHIDOS
		
		if (result.hasErrors()) {
			model.addAttribute("msgSucesso", "ok");
			model.addAttribute("agenda", agenda);
			
			return "redirect:/publico/ecoa/agendaData";
			
		}else {
			
			
			if(AgendaDAO.inserirAgenda(agenda) != 0){
				
				
				model.addAttribute("msgSucesso", "ok");
				
			}else {
				
				model.addAttribute("msgErro", "ok");
			}
				
				
		}
			
			

	
		return "redirect:/publico/ecoa/agendaDataUsu";
		
	}
	
	
	
/*	
	@RequestMapping(value = "/publico/ecoa/agendaMassagem", method = RequestMethod.POST)
	public String adicionaAgendamento (HttpServletRequest request, 
				 					HttpServletResponse response, 
				 					Model model,
				 					@RequestParam(value="idAgenda",required=false) int idAgenda) {
		
		

		
		 ConfiguracaoDAO configuracao = new ConfiguracaoDAO(); if
		 (configuracao.buscarPorCodigo(1).getTituloIntranetAdministrativo().
		 equalsIgnoreCase("PRODUÇÃO")){
		   if
			  (!VerificaPermissao.verificaFunciTesou(usuarioLogado.pegaUsuario())){ 
			   
			   return "redirect:/semPerfil?stringPerfil=Somente funcionarios Tesou."; }
			  
		  }
		 
		 model.addAttribute("usuario", usuarioLogado.pegaUsuario());
		
		return "publico/ecoa/agendaMassagem";
		
	}*/
	
	
	@RequestMapping(value = "/publico/ecoa/modalAgendamentoForm", method = RequestMethod.GET)
	public String novoAgendamentoForm (
			@ModelAttribute("idAgenda") int idAgenda,
			@ModelAttribute("idHorario") int idHorario,
			BindingResult result,HttpServletRequest request, HttpServletResponse response,
			 Model model, RedirectAttributes redirectAttributes) throws SQLException {
		
		String chaveUsuarioLogado = usuarioLogado.pegaUsuario().getChave();
		int numeroSessoesPermitidas = 2; //regra de negócio: limite de 2 sessões por mês
		int numeroSessoesUsuario = AgendaDAO.buscaUltimoAgendamentoUsuario(idAgenda, chaveUsuarioLogado);
		
		
		if(numeroSessoesUsuario < numeroSessoesPermitidas){
			AgendamentoShiatsu agendamento = new AgendamentoShiatsu();
			agendamento = AgendaDAO.buscarAgendamento(idAgenda,idHorario);
			agendamento.setFuncionario(usuarioLogado.pegaUsuario().getNome());
			agendamento.setChaveFuncionario(usuarioLogado.pegaUsuario().getChave());
			
			model.addAttribute("agendamento", agendamento);
			
			
		}else {
			
			//return "redirect:/publico/ecoa/telaErro";
			//redirectAttributes.addFlashAttribute("msgErro", "ok");
			//redirectAttributes.addFlashAttribute("msgDescricao", "Somente 2 agendamentos por mês!");
			//model.addAttribute("msgErro", "ok");
			//result.rejectValue("idAgenda", "Somente 2 agendamentos por mês!");
			return "/publico/ecoa/telaErro";
			//return "redirect:/publico/errorPage";
		}
		
		
		
		
		
		return "/publico/ecoa/formAgendamento";
		
	}
	
	@RequestMapping(value = "/publico/ecoa/telaErro", method = RequestMethod.GET)
	public String telaErro (
			BindingResult result,HttpServletRequest request, HttpServletResponse response,
			 Model model, RedirectAttributes redirectAttributes) {
		
		return "/publico/ecoa/telaErro";
		
		
	}
	

	
    //#########################################################################
	// GRAVA UM NOVO AGENDAMENTO NO BANCO DE DADOS APOS O SUBMIT DO FORMULARIO
	//#########################################################################
	@RequestMapping(value = "/publico/ecoa/agendamentoFormPost", method = RequestMethod.POST)
	public String agendamentoPost (
			@ModelAttribute("agendamento") AgendamentoShiatsu agendamento,
			BindingResult result,HttpServletRequest request, HttpServletResponse response,
			 Model model)  {
		
		
		agendamento.setGerencia(usuarioLogado.pegaUsuario().getUOR());
		
		int gravou = 0;
		
		gravou = AgendaDAO.updateAgendamento(agendamento, usuarioLogado.pegaUsuario().getChave());
		
		if(gravou > 0) {
			
			model.addAttribute("msgSucesso", "ok");
			
		}else {
			
			model.addAttribute("msgErro", "ok");
		}
	
		
		return "redirect:/publico/ecoa/agendaMassagem?idAgenda=" + agendamento.getIdAgenda();
		
	}
	
	//################################################################################################
	// EXCLUÍ AGENDAMENTO
	//################################################################################################

	@RequestMapping(value = "/publico/ecoa/excluirAgendamento", method = RequestMethod.GET)
	public String excluirAgendamento (HttpServletRequest request, HttpServletResponse response,Model model,
			@RequestParam("idAgenda") int idAgenda,
			@RequestParam("idHorario") int idHorario) {
		

		
		ConfiguracaoDAO configuracao = new ConfiguracaoDAO(); 
		 
		if (configuracao.buscarPorCodigo(1).getTituloIntranetAdministrativo().equalsIgnoreCase("PRODUÇÃO")){
		  
			 if
			  (!VerificaPermissao.verificaFunciTesou(usuarioLogado.pegaUsuario())){ 
//TODO - CONTROLE DE ACESSO SOMENTE ECOA - SE FOR NECESSÁRIO, SENÃO PODE SER TESOU			   
			   return "redirect:/semPerfil?stringPerfil=Somente funcionarios Tesou."; }
			  
		 }
		 
		
		AgendaDAO.excluirAgendamento(idAgenda, idHorario);
		 

		
		return "redirect:/publico/ecoa/agendaMassagem?idAgenda=" + idAgenda;
		
	}	
	

	
}
