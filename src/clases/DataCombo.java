package clases;

public class DataCombo {
	private String id;
	private String name;
	
	public DataCombo(){
	
	}
	
	public DataCombo(String id, String name){        
	    this.id = id;
	    this.name = name;
	}
	
	public String getId() {
	    return id;
	}
	
	public void setId(String id) {
	    this.id = id;
	}
	
	//Este metodo retorna el valor a mostrar del JComboBox
	@Override
	public String toString(){
	   return name;
	}
}
