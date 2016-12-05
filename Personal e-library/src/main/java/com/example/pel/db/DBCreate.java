package com.example.pel.db;

import java.sql.SQLException;

import com.example.pel.dao.DaoListBook;
import com.example.pel.entity.ListBook;

public class DBCreate {
	public static void createDB(String url, String user, String password, String nameDB)
			throws SQLException, ClassNotFoundException {
		DB db = new DB(url, user, password);
		db.update("CREATE DATABASE " + nameDB + " DEFAULT CHARACTER SET utf8;");
		db.update("USE " + nameDB + ";");
		db.update("CREATE TABLE ListBook (" + "id INT AUTO_INCREMENT, " + "name VARCHAR(100) NOT NULL, "
				+ "year INT(4) NOT NULL, " + "author VARCHAR(30) NOT NULL, " + "pages INT(4) NOT NULL, "
				+ "isbn VARCHAR(20) NOT NULL, " + "description TEXT, " + "url TEXT, " + "status TEXT NOT NULL, "
				+ "PRIMARY KEY (id));");
		DaoListBook daoListBook = new DaoListBook(db);
		daoListBook.addBook(new ListBook("Java 8. Полное руководство", 2015, "Геберт Шилдт", 1376, "978-5-8459-1918-2",
				"Книга «Java 8. Полное руководство» является исчерпывающим руководством"
						+ " по программированию на языке Java. В этом справочном пособии,"
						+ " полностью обновленном с учетом последней версии Java SE 8, поясняется, "
						+ "как разрабатывать, компилировать, отлаживать и выполнять программы на языке "
						+ "программирования Java. Книга написана Гербертом Шилдтом, автором популярных во"
						+ " всем мире книг по языкам программирования, таким образом, чтобы охватить "
						+ "все языковые средства Java, включая синтаксис, ключевые слова, основные "
						+ "принципы объектно- ориентированного программирования, значительную часть "
						+ "прикладного программного интерфейса Java API, библиотеки классов, аплеты "
						+ "и сервлеты, компоненты JavaBeans, библиотеки AWT и Swing, а также "
						+ "продемонстрировать их применение на простых и наглядных примерах. "
						+ "В книге «Java 8. Полное руководство» не обойдены вниманием и новые "
						+ "средства, появившиеся в версии Java SE 8, в том числе лямбда-выражения, "
						+ "стандартные интерфейсные методы, библиотека потоков ввода-вывода, а также технология JavaFX.",
				"D:\\Denis\\Видео уроки, книги\\Java\\Java 8. Полное руководство. 9-е издание.pdf", "Do not read"));
		daoListBook.addBook(new ListBook("Android. Программирование для профессионалов", 2016, "Билл Филлипс", 640,
				"978-5-496-02051-0",
				"Изучение Android - все равно что жизнь в другой стране: даже если "
						+ "вы говорите на местном языке, на первых порах вы все равно не чувствуете "
						+ "себя как дома. Такое впечатление, что все окружающие знают что-то такое, "
						+ "чего вы еще не понимаете. И даже то, что уже известно, в новом контексте оказывается попросту неправильным.\n"
						+ "Второе издание познакомит вас с интегрированной средой разработки Android Studio,"
						+ " которая поможет с легкостью создавать приложения для Android. "
						+ "Вы не только изучите основы программирования, но и узнаете о возможностях Lollipop,"
						+ " новых инструментах вспомогательных библиотек, а также некоторых ключевых инструментах "
						+ "стандартной библиотеки, включая SoundPool, анимацию и ресурсы. "
						+ "Все учебные приложения были спроектированы таким образом, чтобы продемонстрировать важные"
						+ " концепции и приемы программирования под Android и дать опыт их практического применения.",
				"http://oz.by/books/more10492926.html?sbtoken=3847b554f47d3ba544a0d5dbf9c593f4", "Read"));
	}

	public static void deleteDB(String url, String user, String password, String nameDB)
			throws SQLException, ClassNotFoundException {
		DB db = new DB(url, user, password);
		db.update("DROP DATABASE " + nameDB + ";");
		db.close();
	}
}