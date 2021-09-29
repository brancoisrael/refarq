package br.com.titcs.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.collection.spi.PersistentCollection;
import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;

public class MapperClass {

	private MapperClass() {
	}

	public static <D, T> D converter(final T entity, Class<D> outClass) {
		if (entity == null)
			return null;

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
				.setPropertyCondition(context -> !(context.getSource() instanceof PersistentCollection));
		try {
			return mapper.map(entity, outClass);
		} catch (Exception e) {
			Logger.getLogger(outClass).error(e.getMessage());
			return null;
		}
	}

	public static <D, T> List<D> converter(final List<T> entity, Class<D> outClass) {
		if (entity == null)
			return null;

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
				.setPropertyCondition(context -> (!(context.getSource() instanceof PersistentCollection)));

		List<D> l = new ArrayList<>(entity.size());
		entity.forEach((en) -> {
			try {
				l.add(mapper.map(en, outClass));
			} catch (Exception e) {
				Logger.getLogger(outClass).error(e.getMessage());
			}
		});

		return l;
	}

	public static <D, T> Set<D> converter(final Set<T> entity, Class<D> outClass) {
		if (entity == null)
			return null;

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
				.setPropertyCondition(context -> !(context.getSource() instanceof PersistentCollection));

		Set<D> l = new HashSet<>(entity.size());
		entity.forEach((en) -> {
			try {
				l.add(mapper.map(en, outClass));
			} catch (Exception e) {
				Logger.getLogger(outClass).error(e.getMessage());
			}
		});
		return l;
	}
}
