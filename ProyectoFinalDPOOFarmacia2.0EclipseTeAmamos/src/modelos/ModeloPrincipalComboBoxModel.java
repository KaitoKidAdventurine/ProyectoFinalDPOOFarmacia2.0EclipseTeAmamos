package modelos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

public abstract class ModeloPrincipalComboBoxModel extends DefaultComboBoxModel<String>{

	private static final long serialVersionUID = 1L;
	
	protected abstract void inicializar();
	protected ModeloPrincipalComboBoxModel(List<String> list) {
		inicializar();
		if(list!=null)
			for(String s : list)
				this.addElement(s);
	}

}
