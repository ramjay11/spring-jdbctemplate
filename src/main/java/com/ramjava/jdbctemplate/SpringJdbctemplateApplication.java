package com.ramjava.jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class SpringJdbctemplateApplication {

	public static void main(String[] args) {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		//datasource.setDriverClassName("com.mysql.jdbc.Driver"); deprecated 
		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver"); 
		datasource.setUrl("jdbc:mysql://localhost:3306/bookstore_jdbctemplate");
		datasource.setUsername("root");
		datasource.setPassword("Saint123");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
		
		//String sqlInsert = "INSERT INTO book(title, author, price) VALUES(?, ?, ?)";
		//String sqlUpdate = "UPDATE book SET title = WHERE book_id = ?";
		//String sqlDelete = "DELETE FROM book WHERE book_id = ?";
		String sqlList = "SELECT * FROM book";
		//String sqlList = "SELECT * FROM book WHERE book_id = 1";
		
		RowMapper<Book> rowMapper = new RowMapper<Book>() {
			public Book mapRow(ResultSet result, int row) throws SQLException {
				Integer id = result.getInt("book_id");
				String title = result.getString("title");
				String author = result.getString("author");
				float price = result.getFloat("price");
				return new Book(id, title, author, price);
			}
		};
		
		//int result = jdbcTemplate.update(sqlDelete, "Java Concurrency", "Phillips", 23.99f);
		//int result = jdbcTemplate.update(sqlDelete, "Java Concurrency", "Phillips", 1);
		
		List<Book> listBooks = jdbcTemplate.query(sqlList, rowMapper);
		System.out.println(listBooks);
		
		//Book aBook = jdbcTemplate.queryForObject(sqlList, rowMapper);
		//System.out.println(aBook);
		
		/*
		if (result > 0) {
			//System.out.println("A new book has been inserted.");
 			//System.out.println("The book has been updated.");
			System.out.println("The book has been deleted.");
		}
		*/
	}

}
