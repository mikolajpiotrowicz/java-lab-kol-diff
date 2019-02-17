/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomoc;

import java.util.List;
import javax.faces.model.SelectItem;
/**
 *
 * @author mpiotrowicz
 */
public class JSFPomoc {
	public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne){
		int size = selectOne ? entities.size() + 1 : entities.size();
		SelectItem[] items = new SelectItem[size];
		int i = 0;
		if (selectOne){
			items[0] = new SelectItem("", "---");
			i++;
		}
		for (Object x : entities){
			items[i++] = new SelectItem(x, x.toString());
		}
		return items;
	}
}
