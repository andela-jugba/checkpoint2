package checkpoint.andela.parser.document.models;

import java.util.Date;
import java.util.HashMap;

public class Reactant extends HashMap<String, String>{
	private static final long serialVersionUID = 1L;
	private Date date;
	public Reactant() {
		super();
		this.date = new Date();
	}

	public Date getDate() {
		return date;
	}
}
