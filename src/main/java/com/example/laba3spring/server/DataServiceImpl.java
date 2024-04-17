package com.example.laba3spring.server;

import javax.jws.WebService;
import java.sql.*;

@WebService(
		serviceName = "Hello",
		portName = "HelloPort",
		targetNamespace = "http://service.ws.sample/",
		endpointInterface = "com.example.demows.server.DataService")
public class DataServiceImpl implements DataService {

	private static final String DB_URL = "jdbc:postgresql://localhost:5432/library2";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "admin2233";

	@Override
	public String getBookInfo(int bookId) {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			 PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE book_id = ?")) {
			statement.setInt(1, bookId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt("book_id");
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				String genre = resultSet.getString("genre");
				int publicationYear = resultSet.getInt("publication_year");
				return "Book ID: " + id + ", Title: " + title + ", Author: " + author +
						", Genre: " + genre + ", Publication Year: " + publicationYear;
			} else {
				return "Book with ID " + bookId + " not found";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error fetching book info: " + e.getMessage();
		}
	}

	@Override
	public String getAllBooks() {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			 Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
			StringBuilder result = new StringBuilder();
			while (resultSet.next()) {
				int id = resultSet.getInt("book_id");
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				String genre = resultSet.getString("genre");
				int publicationYear = resultSet.getInt("publication_year");
				result.append("Book ID: ").append(id).append(", Title: ").append(title)
						.append(", Author: ").append(author).append(", Genre: ").append(genre)
						.append(", Publication Year: ").append(publicationYear).append("\n");
			}
			return result.toString();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error fetching books: " + e.getMessage();
		}
	}
}