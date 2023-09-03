/**
 * Intranet - UNIDADE DE TESOURARIA GLOBAL
 *
 * @author T1091599
 *
 * @create: 14 de fev de 2023
 *
 */
package br.com.bb.intranet.tesouraria.ecoa.agendaMassagem.model;

/**
 * @author T1091599
 *
 */
public class AgendamentoShiatsu {

	private int idAgenda;
	private int idHorario;
	private String funcionario;
	
	private String chaveFuncionario;
	private String gerencia;
	private String ramal;
	private String presenca;
	private String horario;

	
	
	
	
	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public int getIdAgenda() {
		return idAgenda;
	}			

	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	
	

	public int getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}
	
	
	
	public String getChaveFuncionario() {
		return chaveFuncionario;
	}

	public void setChaveFuncionario(String chaveFuncionario) {
		this.chaveFuncionario = chaveFuncionario;
	}

	public String getGerencia() {
		return gerencia;
	}

	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public String getPresenca() {
		return presenca;
	}

	public void setPresenca(String presenca) {
		this.presenca = presenca;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
