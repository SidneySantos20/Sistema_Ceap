import java.sql.*;
public class SistemaDAO {
	public PainelFinal Final;
	public BD bd;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String men, sql;
	public static final byte INCLUSAO = 1;
	public static final byte ALTERACAO = 2;
	public static final byte EXCLUSAO = 3;
	
	public SistemaDAO() {
		bd = new BD();
		Final = new PainelFinal();
	}
	
	public boolean localizar() {
		sql = "select * from SistemaCeap where professor =?";
		try {
			statement = bd.c.prepareStatement(sql);
			statement.setString(1, Final.getProf());
			resultSet = statement.executeQuery();
			resultSet.next();
			Final.setData(resultSet.getString(1));
			Final.setHoras(resultSet.getString(2));
			Final.setProf(resultSet.getString(3));
			Final.setSala(resultSet.getString(4));
			Final.setMaterial(resultSet.getString(5));
			return true;
		}catch (SQLException erro) {
			return false;
		}
	}
	public String atualizarTabela() {
		String atuTab = "select * from sistemaceap;";
		return atuTab;
	}
	
	public String atualizar(int operacao) {
		men = "Operação realizada com sucesso ";
		try {
			if(operacao == INCLUSAO) {
				sql = "insert into SistemaCeap values (?,?,?,?,?)";
				statement = bd.c.prepareStatement(sql);
				statement.setString(1,	Final.getData());
				statement.setString(2, Final.getHoras());
				statement.setString(3, Final.getProf());
				statement.setString(4, Final.getSala());
				statement.setString(5,	Final.getMaterial());
			} else if(operacao == ALTERACAO) {
				sql = "update SistemaCeap set data = ?, horas = ?, sala = ?, material = ? where professor =?";
				statement = bd.c.prepareStatement(sql);
				statement.setString(1,	Final.getData());
				statement.setString(2, Final.getHoras());
				statement.setString(5, Final.getProf());
				statement.setString(3, Final.getSala());
				statement.setString(4,	Final.getMaterial());
			} else if(operacao == EXCLUSAO) {
				sql = "delete from SistemaCeap where professor =?";
				statement = bd.c.prepareStatement(sql);
				statement.setString(1,Final.getProf());
			}
			if (statement.executeUpdate() == 0) {
				men = "Falha na operação";
			}
			
		} catch(SQLException erro) {
			men = "Falha na operação " + erro.toString();
		}
		return men;
	}
}