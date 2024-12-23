package application;

import db.DB;

import java.sql.*;

public class Program {

    public static void main(String[] args) {

        Connection conn = null; // Inicializa a conexão como nula
        PreparedStatement st = null; // Inicializa o PreparedStatement como nulo

        try {
            // Obtém a conexão com o banco de dados
            conn = DB.getConnection();

            // Prepara a instrução SQL para atualizar o salário base dos vendedores
            st = conn.prepareStatement(
                    "UPDATE seller "
                            + "SET BaseSalary = BaseSalary + ? "
                            + "WHERE "
                            + "(DepartmentId = ?)");

            // Define os valores a serem usados na atualização
            st.setDouble(1, 200.0); // Aumento de 200.0 no salário
            st.setInt(2, 2); // ID do departamento que será afetado

            // Executa a atualização e obtém a quantidade de linhas afetadas
            int rowsAffected = st.executeUpdate();

            // Exibe o resultado da operação
            System.out.println("Done! Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro
        } finally {
            // Fecha o PreparedStatement e a conexão
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}