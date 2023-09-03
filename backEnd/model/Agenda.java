/**
 * Intranet - UNIDADE DE TESOURARIA GLOBAL
 *
 * @author T1091599
 *
 * @create: 14 de fev de 2023
 *
 */
package br.com.bb.intranet.tesouraria.ecoa.agendaMassagem.model;

import java.util.Date;
import java.util.List;

import br.com.bb.intranet.tesouraria.ecoa.agendaMassagem.dao.AgendaDAO;

/**
 * @author T1091599
 *
 */
public class Agenda {

	private int idAgenda;
	private Date data;
	private String nomeProf;
	private List<AgendamentoShiatsu> listaAgendamento;
	
	
	public Agenda() {
		
		
	}
	
	public Agenda(List<AgendamentoShiatsu> listaAgendamento){
		
		this.listaAgendamento = listaAgendamento;
	}
	


	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNomeProf() {
		return nomeProf;
	}

	public void setNomeProf(String nomeProf) {
		this.nomeProf = nomeProf;
	}

	public int getIdAgenda() {
		return idAgenda;
	}

	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	
	public List<AgendamentoShiatsu> getListaAgendamento() {
		return listaAgendamento;
	}

	public void setListaAgendamento(List<AgendamentoShiatsu> listaAgendamento) {
		this.listaAgendamento = listaAgendamento;
	}

	
}

