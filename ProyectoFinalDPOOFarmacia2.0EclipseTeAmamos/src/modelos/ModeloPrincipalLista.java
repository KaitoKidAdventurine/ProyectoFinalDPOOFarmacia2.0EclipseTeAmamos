package modelos;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class ModeloPrincipalLista extends AbstractListModel<String> 
{
    private List<String> items = new ArrayList<String>();
  
    public int getSize() 
    {
        return items.size();
    }

    public String getElementAt(int index) 
    {
        return items.get(index);
    }

}