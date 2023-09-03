/**
 * Intranet - UNIDADE DE TESOURARIA GLOBAL
 *
 * @author F0724093
 *
 * @create: 27 de abr de 2023
 *
 */
package br.com.bb.intranet.tesouraria.ecoa.agendaMassagem.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import br.com.bb.intranet.tesouraria.ecoa.agendaMassagem.model.Agenda;
import br.com.bb.intranet.tesouraria.ecoa.agendaMassagem.model.AgendamentoShiatsu;
import br.com.bb.intranet.tesouraria.publico.usuario.controller.UsuarioLogadoController;
import br.com.bb.intranet.tesouraria.util.ConnectionFactory;
import br.com.bb.intranet.tesouraria.util.FuncoesUteis;
import br.com.bb.sso.api.bean.Usuario;

/**
 * @author F0724093
 *
 */
@Repository
public class AgendaDAO {
   
  
	public static int inserirAgenda(Agenda agenda) {

		Connection conn = ConnectionFactory.open();

		PreparedStatement pstm = null;
		
		int idAgenda = 0;
		
	

		
		String sql = "INSERT INTO AdmRj.INTRANET_ECOA_AGENDA_MASSAGEM(idAgenda,"
		      + "nomeProf," + "data,"
		      + "dataHoraAudit," + "ipComp," + "chaveUsu)"
		      + "VALUES (?,?,?,?,?,?)";

		try {
			pstm = conn.prepareStatement(sql);

			idAgenda = findMaxId();
			pstm.setInt(1, idAgenda);
			pstm.setString(2, agenda.getNomeProf());
			pstm.setDate(3, new java.sql.Date(agenda.getData().getTime()));
			pstm.setTimestamp(4, FuncoesUteis.pegaDataHoraLocal());
			pstm.setString(5, FuncoesUteis.pegaIPComputador());
			pstm.setString(6, FuncoesUteis.pegaUsuComputador());

			pstm.executeUpdate();

		} catch (Exception e) {
			System.out.println("Erro ao adicionar agenda: " + e.getMessage());
		} finally {
			ConnectionFactory.close(conn, pstm, null);

		}
		
		
		//PREENCHER LISTA DE AGENDAMENTOS EM BRANCO
		
		List<AgendamentoShiatsu> listaAgendamento = buscarListaHorarios(idAgenda);
		
		for(AgendamentoShiatsu agendamento: listaAgendamento) {
			
			inserirAgendamentoShiatsu(agendamento);
			
		}
		

		return idAgenda;
	}
	
	
	
	
	
	public static Agenda buscarAgenda(int idAgenda) {
		
		Agenda agenda = null;
		ResultSet rs = null;
		Connection conn = ConnectionFactory.open();

		PreparedStatement pstm = null;
		

		String sql = "SELECT * FROM AdmRj.INTRANET_ECOA_AGENDA_MASSAGEM"
				+ " WHERE idAgenda = " + idAgenda;

		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				
				agenda = new Agenda();
				agenda.setIdAgenda(rs.getInt("idAgenda"));
				agenda.setNomeProf(rs.getString("nomeProf"));
				agenda.setData(rs.getDate("data"));
				
				List<AgendamentoShiatsu> listaAgendamento = buscarListaAgendamento(idAgenda);
				
				if(listaAgendamento.isEmpty()) {
					
					//Quando a lista estiver vazia preenchemos com os horários em branco.
					listaAgendamento = buscarListaHorarios(agenda.getIdAgenda());
					agenda.setListaAgendamento(listaAgendamento);
					
				} else agenda.setListaAgendamento(listaAgendamento);
				
				
			}

		} catch (Exception e) {
			System.out.println("Erro ao adicionar agenda: " + e.getMessage());
		} finally {
			ConnectionFactory.close(conn, pstm, null);
		}
		
		return agenda;
	}
	
	
	public static List<Agenda> buscarListaAgenda() {
		
		List<Agenda> listAgenda = new ArrayList<Agenda>();
		ResultSet rs = null;
		Connection conn = ConnectionFactory.open();

		PreparedStatement pstm = null;


		String sql = "SELECT TOP 25* FROM AdmRj.INTRANET_ECOA_AGENDA_MASSAGEM";


		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				
				Agenda agenda = new Agenda();
				agenda.setIdAgenda(rs.getInt("idAgenda"));
				agenda.setNomeProf(rs.getString("nomeProf"));
				agenda.setData(rs.getDate("data"));
				listAgenda.add(agenda);
			
				System.out.println("listaAgendamento" + agenda.getIdAgenda() );	
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao adicionar agenda: " + e.getMessage());
		} finally {
			ConnectionFactory.close(conn, pstm, null);
		}
		
		return listAgenda;
	}
	
	
	/*.......................................................................Metodo inserir e buscar da tabela Agentamento Massagem.......................................................................*/
	public static int inserirAgendamentoShiatsu(AgendamentoShiatsu agendamento) {

		Connection conn = ConnectionFactory.open();

		PreparedStatement pstm = null;
		
		int saida = 0;

		try {
			String sql = "INSERT INTO AdmRj.INTRANET_ECOA_AGENDAMENTO("
				  + "idAgenda,"
				  + "idHorario, "
			      + "funcionario, "
				  + "chaveFuncionario, "
				  + "ramal,"
			      + "horario,"
				  + "presenca,"
			      + "dataHoraAudit," 
				  + "ipComp," 
			      + "chaveUsu)"
			      + "VALUES ("
			      + agendamento.getIdAgenda() + ", "
			      + agendamento.getIdHorario() + ", '"
			      + agendamento.getFuncionario() + "','"
               + agendamento.getChaveFuncionario() + "','"
			      + agendamento.getRamal() + "', '"
			      + agendamento.getHorario() + "', '-1', '"
	      		+ FuncoesUteis.pegaDataHoraLocal() + "', '"
	      		+ FuncoesUteis.pegaIPComputador() + "', '"
	      		+ FuncoesUteis.pegaUsuComputador() + "')";

		
			pstm = conn.prepareStatement(sql);


			pstm.executeUpdate();
			saida = 1;

		} catch (Exception e) {
			System.out.println("Erro ao adicionar agendamento: " + e.getMessage());
		} finally {
			ConnectionFactory.close(conn, pstm, null);
		}

		
		
		return saida;
	}
	
	public static int updateAgendamento(AgendamentoShiatsu agendamento, String usuarioLogado_Chave) {

		Connection conn = ConnectionFactory.open();

		PreparedStatement pstm = null;
		
		int saida = 0;

		try {
			String sql = "UPDATE AdmRj.INTRANET_ECOA_AGENDAMENTO SET "
               + "funcionario = '" + agendamento.getFuncionario() + "', "
               + "ramal = '" + agendamento.getRamal() + "', "
               + "presenca = -1, " 
               + "dataHoraAudit = '" + FuncoesUteis.pegaDataHoraLocal() + "', " 
               + "ipComp = '" + FuncoesUteis.pegaIPComputador() + "', "
               + "chaveFuncionario = '" + usuarioLogado_Chave + "', "
               + "chaveUsu = '" + FuncoesUteis.pegaUsuComputador() + "'"
               + " WHERE "
               + "idAgenda = " + agendamento.getIdAgenda()
               + " AND idHorario = " + agendamento.getIdHorario(); 
		
			pstm = conn.prepareStatement(sql);


			pstm.executeUpdate();
			saida = 1;

		} catch (Exception e) {
			System.out.println("Erro ao adicionar agendamento: " + e.getMessage());
		} finally {
			ConnectionFactory.close(conn, pstm, null);
		}

		
		
		return saida;
	}
	
	public static AgendamentoShiatsu buscarAgendamento(int idAgenda) {

		AgendamentoShiatsu agendamento = null;
		ResultSet rs = null;
		Connection conn = ConnectionFactory.open();

		PreparedStatement pstm = null;

		String sql = "SELECT * FROM AdmRj.INTRANET_ECOA_AGENDAMENTO(" + "WHERE idAgenda = " + idAgenda;

		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {

				agendamento = new AgendamentoShiatsu();
				agendamento.setIdAgenda(rs.getInt("idAgenda"));
				agendamento.setFuncionario(rs.getString("funcionario"));
				agendamento.setChaveFuncionario(rs.getString("chaveFuncionario"));
				agendamento.setRamal(rs.getString("ramal"));
				agendamento.setHorario(rs.getString("horario"));
				agendamento.setPresenca(rs.getString("presenca"));
			}

		} catch (Exception e) {
			System.out.println("Erro ao adicionar o agendamento: " + e.getMessage());
		} finally {
			ConnectionFactory.close(conn, pstm, null);
		}

		return agendamento;
	}
	
	
	
	
	 public static List<AgendamentoShiatsu> buscarListaAgendamento(int idAgenda) {

		List<AgendamentoShiatsu> listAgendamento = new ArrayList<AgendamentoShiatsu>();
		ResultSet rs = null;
		Connection conn = ConnectionFactory.open();

		PreparedStatement pstm = null;

		String sql = "SELECT * FROM AdmRj.INTRANET_ECOA_AGENDAMENTO where idAgenda = " + idAgenda;

		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {

				AgendamentoShiatsu agendamento = new AgendamentoShiatsu();
				agendamento.setIdAgenda(rs.getInt("idAgenda"));
				agendamento.setFuncionario(rs.getString("funcionario"));
				agendamento.setChaveFuncionario(rs.getString("chaveFuncionario"));
				agendamento.setRamal(rs.getString("ramal"));
				agendamento.setHorario(rs.getString("horario"));
				agendamento.setIdHorario(rs.getInt("idHorario"));
				agendamento.setPresenca(rs.getString("presenca"));
				listAgendamento.add(agendamento);

			}

		} catch (Exception e) {
			System.out.println("Erro ao agendar sua massagem: " + e.getMessage());
		} finally {
			ConnectionFactory.close(conn, pstm, null);
		}

		return listAgendamento;
	}
	 

	 
	 	 
	 public static void excluiAgendamento(int idAgenda, int horario) {

			Connection conn = ConnectionFactory.open();
			PreparedStatement pstm = null;
			String sql = "DELETE FROM AdmRj.INTRANET_USUARIOS_FAVORITOS WHERE idAgenda = " + idAgenda + " and horario = " + horario;
					
			try {
				pstm = conn.prepareStatement(sql);
				
				pstm.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ConnectionFactory.close(conn, pstm, null);
			}
		}

	/**
	 * @param valueOf
	 * @param string
	 * @param funcionario
	 */

	 
	 
	 
	 
	 
	 public static int findMaxId() {
			Connection conn = ConnectionFactory.open();
			PreparedStatement pstm = null;
			ResultSet rs = null;

			int id = 0;
			int idSaida = 0;
			int i=0;

			String sql = "Select coalesce (max(idAgenda),0) as idAgenda "
					+ "FROM "
					+ "AdmRj.INTRANET_ECOA_AGENDA_MASSAGEM ";


			try {
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();

				if (rs.next()) {
					id = rs.getInt(1);
					
				}

				if (id != 0) {
					idSaida = id + 1;
				} else {

						idSaida = 1;
					
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnectionFactory.close(conn, pstm, rs);
			}
			 System.out.println(idSaida);
			 
			return idSaida;
		}
	 
	 //====================================================================================
	 //
	 //BUSCA LISTA DOS HORÁRIOS DE MASSAGENS
	 //
	 //====================================================================================
	 
	 public static List<AgendamentoShiatsu> buscarListaHorarios(int idAgenda) {

			List<AgendamentoShiatsu> listHorarios = new ArrayList<AgendamentoShiatsu>();
			ResultSet rs = null;
			Connection conn = ConnectionFactory.open();

			PreparedStatement pstm = null;

			String sql = "SELECT * FROM AdmRj.INTRANET_ECOA_AGENDAMENTO_HORARIOS ";

			try {
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();

				while (rs.next()) {

					AgendamentoShiatsu agendamento = new AgendamentoShiatsu();	
					agendamento.setIdAgenda(idAgenda);
					agendamento.setIdHorario(rs.getInt("idHorario"));
					agendamento.setHorario(rs.getString("horario"));
					agendamento.setFuncionario("");
					agendamento.setChaveFuncionario("");
					agendamento.setGerencia("");
					agendamento.setRamal("");  
					agendamento.setPresenca("");
					listHorarios.add(agendamento);

				}

			} catch (Exception e) {
				System.out.println("Erro ao buscar horários: " + e.getMessage());
			} finally {
				ConnectionFactory.close(conn, pstm, null);
			}

			return listHorarios;
		}





	/**
	 * @param idAgenda
	 * @param idHorario
	 * @return
	 */
	public static AgendamentoShiatsu buscarAgendamento(int idAgenda,
	      int idHorario) {
		
		
		AgendamentoShiatsu agendamento = null;
		ResultSet rs = null;
		Connection conn = ConnectionFactory.open();

		PreparedStatement pstm = null;

		String sql = "SELECT * FROM AdmRj.INTRANET_ECOA_AGENDAMENTO WHERE idAgenda = " + idAgenda 
				 + " AND idHorario = " + idHorario;

		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {

				agendamento = new AgendamentoShiatsu();
				agendamento.setIdAgenda(rs.getInt("idAgenda"));
				agendamento.setFuncionario(rs.getString("funcionario"));
				agendamento.setChaveFuncionario(rs.getString("chaveFuncionario"));
				agendamento.setRamal(rs.getString("ramal"));
				agendamento.setHorario(rs.getString("horario"));
				agendamento.setIdHorario(rs.getInt("idHorario"));
				agendamento.setPresenca(rs.getString("presenca"));
			}

		} catch (Exception e) {
			System.out.println("Erro ao adicionar o agendamento: " + e.getMessage());
		} finally {
			ConnectionFactory.close(conn, pstm, null);
		}

		return agendamento;


		
		
	}
	
	 public static int excluirAgendamento(int idAgenda, int idHorario) {
		 
	Connection conn = ConnectionFactory.open();

	PreparedStatement pstm = null;
	
	int saida = 0;

	try {
		String sql = "UPDATE AdmRj.INTRANET_ECOA_AGENDAMENTO SET "
           + "funcionario = '', "
           + "ramal = '', "
           + "presenca = -1, " 
           + "dataHoraAudit = '" + FuncoesUteis.pegaDataHoraLocal() + "', " 
           + "ipComp = '" + FuncoesUteis.pegaIPComputador() + "', "
           + "chaveFuncionario = '', "
           + "chaveUsu = '" + FuncoesUteis.pegaUsuComputador() + "'"
           + " WHERE "
           + "idAgenda = " + idAgenda
           + " AND idHorario = " + idHorario; 
	
		pstm = conn.prepareStatement(sql);


		pstm.executeUpdate();
		saida = 1;

	} catch (Exception e) {
		System.out.println("Erro ao adicionar agendamento: " + e.getMessage());
	} finally {
		ConnectionFactory.close(conn, pstm, null);
	}

	
	
	return saida;
}




    public LocalDate dataPrimeraQuinzena(LocalDate data) {
		
		int primeiroDia = 0;
    	int dataFinal = 0;
    	
		if (data.getDayOfMonth() < 16) {
			primeiroDia = 1; 
		} else  {
			primeiroDia = 16;
		}
		
//		String sql = "SELECT * FROM AdmRj.INTRANET_ECOA_AGENDAMENTO [idAgenda]" + 
//					"  FROM [INFORRJCONTROLE].[AdmRJ].[INTRANET_ECOA_AGENDAMENTO]" + 
//					"  where (idagenda in (SELECT idagenda " + 
//					"  FROM [INFORRJCONTROLE].[AdmRJ].[INTRANET_ECOA_AGENDA_MASSAGEM]" + 
//					"  WHERE Data >= primeiroDiaQuinzena and Data <= ultimoDiaQuinzena " + 
//					"  )" + 
//					"  )" + 
//					"  AND (chaveFuncionario = "+ usua +)";

		
		return null;
		
    }

	/**
	 * @param idAgenda
	 * @param pegaUsuario
	 * @throws SQLException 
	 */
    
    //Verifica se o usuário possui um agendamento nos ultimos 15 dias.
//	public static void buscaUltimoAgendamentoUsuario(int idAgenda, Usuario usuarioLogado) {
//		
//		AgendamentoShiatsu agendamento = null;
//		ResultSet rs = null;
//		Connection conn = ConnectionFactory.open();
//
//		PreparedStatement pstm = null;
//		
//		String sql = "SELECT * FROM AdmRj.INTRANET_ECOA_AGENDAMENTO" + 
//					"  FROM [INFORRJCONTROLE].[AdmRJ].[INTRANET_ECOA_AGENDAMENTO]" + 
//					"  where (idagenda in (SELECT idagenda " + 
//					"  FROM [INFORRJCONTROLE].[AdmRJ].[INTRANET_ECOA_AGENDA_MASSAGEM]" + 
//					"  WHERE Data >= primeiroDiaQuinzena and Data <= ultimoDiaQuinzena " + 
//					"  )" + 
//					"  )" + 
//					"  AND (chaveFuncionario = "+ usuarioLogado.getChave() + ")";
//		
//		
//
//		
//		
//	}
    
public static int buscaUltimoAgendamentoUsuario(int idAgenda, String chaveUsuarioLogado) throws SQLException {
		
		int sessoesAgendadasDoUsuario = 0; 
		Date date = new Date(); //cria data atual
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int mesAtual = localDate.getMonthValue(); //pega o mês da data atual
		int anoAtual = localDate.getYear(); //pega o ano da data atual		
		
		
		ResultSet rs = null;
		Connection conn = ConnectionFactory.open();

		PreparedStatement pstm = null;
		
		String sql = "SELECT COUNT(p.chavefuncionario) as totalSessoes from (Select a.chaveFuncionario, b.data from [INFORRJCONTROLE].[AdmRJ].[INTRANET_ECOA_AGENDAMENTO] a" + 
					"  inner join [INFORRJCONTROLE].[AdmRJ].[INTRANET_ECOA_AGENDA_MASSAGEM] b" + 
					"  on a.idAgenda = b.idAgenda" + 
					"  where a.chaveFuncionario = '" + chaveUsuarioLogado  + "'" +
					"  and MONTH(b.data) = " + mesAtual +
					"  and YEAR(b.data) = " + anoAtual + ") as p";
		
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				
				//seta o número de sessoes do usuário
				sessoesAgendadasDoUsuario =(rs.getInt("totalSessoes"));
														
				
			}
			
		
		} catch (Exception e) {
			System.out.println("Erro ao buscar agendamentos do funci: " + e.getMessage());
		} finally {
			ConnectionFactory.close(conn, pstm, null);
		}

		return sessoesAgendadasDoUsuario;

		
		
	}

	
}
