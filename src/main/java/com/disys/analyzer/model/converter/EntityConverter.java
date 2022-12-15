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

import com.disys.analyzer.model.Resource;

/**
 * @author Sajid
 * 
 */
@FacesConverter(forClass = Resource.class, value = "entityConverter")
public class EntityConverter implements Converter {

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		try {
			if (value == null || value.equals("")) {
				return "";
			} else {
				return String.valueOf(((Resource) value).getResId());
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
	private Resource getObjectFromUIPickListComponent(UIComponent component,
			String value) {
		final DualListModel<Resource> dualList;
		try {
			dualList = (DualListModel<Resource>) ((PickList) component)
					.getValue();
			Resource resource = getObjectFromList(dualList.getSource(),
					Integer.valueOf(value));
			if (resource == null) {
				resource = getObjectFromList(dualList.getTarget(),
						Integer.valueOf(value));
			}

			return resource;
		} catch (ClassCastException cce) {
			throw new ConverterException();
		} catch (NumberFormatException nfe) {
			throw new ConverterException();
		}
	}

	private Resource getObjectFromList(final List<?> list, final Integer identifier) {
		for (final Object object : list) {
			final Resource resource = (Resource) object;
			if (resource.getResId().equals(identifier)) {
				return resource;
			}
		}
		return null;
	}

}
