/**
 * 
 */
package com.disys.analyzer.model.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import com.disys.analyzer.model.User;

/**
 * @author Sajid
 * 
 */
@FacesConverter(forClass = User.class, value = "userEntityConverter")
public class UserEntityConverter implements Converter {

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		try {
			if (value == null || value.equals("")) {
				return "";
			} else {
				return String.valueOf(((User) value).getUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		return getObjectFromUIPickListComponent(component, value);
	}

	@SuppressWarnings("unchecked")
	private User getObjectFromUIPickListComponent(UIComponent component,
			String value) {
		final DualListModel<User> dualList;
		try {
			dualList = (DualListModel<User>) ((PickList) component).getValue();
			User user = getObjectFromList(dualList.getSource(), value);
			if (user == null) {
				user = getObjectFromList(dualList.getTarget(), value);
			}

			return user;
		} catch (ClassCastException cce) {
			throw new ConverterException();
		} catch (NumberFormatException nfe) {
			throw new ConverterException();
		}
	}

	private User getObjectFromList(final List<?> list, final String identifier) {
		for (final Object object : list) {
			final User user = (User) object;
			if (user.getUserId().equals(identifier)) {
				return user;
			}
		}
		return null;
	}

}