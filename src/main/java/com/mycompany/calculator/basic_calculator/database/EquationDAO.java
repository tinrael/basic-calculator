package com.mycompany.calculator.basic_calculator.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mycompany.calculator.basic_calculator.math.Equation;

public class EquationDAO {
	public static int add(Equation equation, double solution) {
		String sql = "INSERT INTO equations(lhs, rhs, solution) VALUES(?, ?, ?)";
		
		try (Connection connection = Database.connect();
				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setArray(1, connection.createArrayOf("text", equation.getLhs().getPostfix()));
			preparedStatement.setArray(2, connection.createArrayOf("text", equation.getRhs().getPostfix()));
			preparedStatement.setDouble(3, solution);
			
			int rowCount = preparedStatement.executeUpdate();
			if (rowCount > 0) {
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				if (generatedKeys.next()) {
					return generatedKeys.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
}
