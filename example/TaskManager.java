package example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import com.jcraft.jsch.JSchException;

import shell.Command;
import shell.Param;
import shell.Shell;
import shell.ShellDependent;
import shell.ShellFactory;

public class TaskManager implements ShellDependent {

	private static JDBC database;
	private Shell shell;

	@Override
	public void cliSetShell(Shell theShell) {
		this.shell = theShell;

	}

	@Command(description = "Varargs example")
	public String active() {
		String stmt = "SELECT task_id, task_label, create_date, due_date FROM task WHERE task_status = 'active'";
		String sqlReturn = database.runQuery(stmt);
		return sqlReturn;
	}

	@Command(description = "Varargs example")
	public String add(@Param(name = "Label of task", description = "task to be added to list") String... labels) {
		String result = "";
		for (String i : labels) {
			result += i + " ";
		}
		String stmt = "INSERT INTO task(task_label, create_date) VALUES ('" + result + "', CURDATE())";
		database.runQueryNoReturn(stmt);
		String stmt2 = "SELECT task_id FROM task WHERE task_label='" + result + "'";
		String sqlReturn = database.runQuery(stmt2);
		return sqlReturn;
	}

	@Command(description = "Varargs example")
	public void tag(@Param(name = "task_id", description = "assign tags to tasks") int task_id,
			@Param(name = "tags", description = "assign multiple tags") String... tags) {
		String stmt = "";
		String tag_IdStmt = "";
		for (String i : tags) {
			stmt = "SELECT tag_id FROM tag WHERE tag_label= '" + i + "'";
			tag_IdStmt = database.runQuery(stmt);
			String statement = "INSERT INTO assign (task_id, tag_id) VALUES (" + task_id + "," + tag_IdStmt + ")";
			database.runQueryNoReturn(statement);
		}
	}

	@Command(description = "Varargs example")
	public void due(@Param(name = "task_id", description = "assign due dates to tasks") int task_id,
			@Param(name = "date", description = "assign due dates to tasks") String date) {
		String stmt = "UPDATE task SET due_date = '" + date + "' WHERE task_id=" + task_id;
		database.runQueryNoReturn(stmt);
	}

	@Command(description = "Varargs example")
	public void finish(@Param(name = "task_id", description = "assign task to completed") int task_id) {
		String stmt = "UPDATE task SET task_status = 'complete' WHERE task_id=" + task_id;
		database.runQueryNoReturn(stmt);
	}

	@Command(description = "Varargs example")
	public void cancel(@Param(name = "task_id", description = "cancel task") int task_id) {
		String stmt = "DELETE FROM task WHERE task.task_id=" + task_id;
		String stmt2 = "DELETE FROM assign WHERE assign.task_id=" + task_id;
		database.runQueryNoReturn(stmt);
		database.runQueryNoReturn(stmt2);
	}

	// Should we return ALL details such as tags?
	@Command(description = "print details of tasks")
	public String show(@Param(name = "task_id", description = "show tasks") int task_id) {
		String stmt = "SELECT * FROM task WHERE task_id =" + task_id;
		String sqlReturn = database.runQuery(stmt);
		return sqlReturn;
	}

	// Check database for actual data!
	@Command(description = "Varargs example")
	public String active(@Param(name = "tag_id", description = "show active tasks") String label) {
		String stmt = "SELECT tag_id FROM tag WHERE tag_label='" + label + "'";
		String sqlReturn = database.runQuery(stmt);
		String stmt2 = "SELECT task.task_id FROM assign, task WHERE tag_id='" + sqlReturn
				+ "' AND assign.task_id = task.task_id AND task_status = 'active'";
		String sqlReturn2 = database.runQuery(stmt2);
		String sqlReturn3 = "";
		String stmt3 = "";
		for (String i : sqlReturn2.split("\n")) {
			stmt3 = "SELECT * FROM task WHERE task_id= '" + i + "'";
			sqlReturn3 += database.runQuery(stmt3);
		}
		return sqlReturn3;
	}

	@Command(description = "Varargs example")
	public void rename(@Param(name = "task_id", description = "rename task") int task_id,
			@Param(name = "label", description = "rename") String... labels) {
		String result = "";
		for (String i : labels) {
			result += i + " ";
		}
		String stmt = "UPDATE task SET task_label='" + result + "' WHERE task_id=" + task_id;
		database.runQueryNoReturn(stmt);
	}

	@Command(description = "Varargs example")
	public String due(@Param(name = "string", description = "what is due today/soon") String string) {
		if (string.equals("today")) {
			String stmt = "SELECT * FROM task WHERE task_status= 'active' AND due_date = CURDATE()";
			String sqlReturn = database.runQuery(stmt);
			return sqlReturn;
		} else if (string.equals("soon")) {
			String stmt = "SELECT * FROM task WHERE task_status= 'active' AND due_date = CURDATE()+3";
			String sqlReturn = database.runQuery(stmt);
			return sqlReturn;
		}
		return "Nothing found";
	}

	@Command(description = "Varargs example")
	public String overdue() {
		String stmt = "SELECT * FROM task WHERE task_status= 'active' AND due_date < CURDATE()";
		String sqlReturn = database.runQuery(stmt);
		return sqlReturn;
	}

	@Command(description = "Varargs example")
	public String completed(@Param(name = "tag_id", description = "show completed tasks") String label) {
		String stmt = "SELECT tag_id FROM tag WHERE tag_label='" + label + "'";
		String sqlReturn = database.runQuery(stmt);
		String stmt2 = "SELECT task.task_id FROM assign, task WHERE tag_id='" + sqlReturn
				+ "' AND assign.task_id = task.task_id AND task_status = 'completed'";
		String sqlReturn2 = database.runQuery(stmt2);
		String sqlReturn3 = "";
		String stmt3 = "";
		for (String i : sqlReturn2.split("\n")) {
			stmt3 = "SELECT * FROM task WHERE task_id= '" + i + "'";
			sqlReturn3 += database.runQuery(stmt3);
			if (sqlReturn3.equals("")) {
				return "Nothing found";
			}
		}
		return sqlReturn3;
	}

	@Command(description = "Varargs example")
	public String search(@Param(name = "keyword", description = "search projects") String keyword) {
		String stmt = "SELECT * FROM task WHERE task_label LIKE '%" + keyword + "%'";
		String sqlReturn = database.runQuery(stmt);
		return sqlReturn;
	}

	public static void main(String[] args) throws IOException, JSchException {

		if (args.length != 5) {
			System.out.println("Inputs are incorrect");
			System.out.println("<BroncoUserid> <BroncoPassword> <sandboxUSerID> <sandbox password> <yourportnumber>");
		} else {
			
			User dbUser = new User(args[0], args[1], args[2], args[3],  Integer.parseInt(args[4]));
			
			try {
				try {
					database = new JDBC();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SQLException e) {
				System.out.println("Database did not connect");
			}

			ShellFactory.createConsoleShell("GetItDone",
					"EVERYTHING IS DUE NOTHING IS DONE\n" + "Enter ?l to list available commands.", new TaskManager())
					.commandLoop();

		}
	}
}
