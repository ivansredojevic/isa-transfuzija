package com.ivan.isaback.repository.specification;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FilterSpecification<T> implements Specification<T> {

	private final T criteria;

	public FilterSpecification(T criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
		final List<Predicate> predicates = new ArrayList<Predicate>();
		String name;
		Object value;
		for (Field field : this.criteria.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				value = field.get(this.criteria);
				name = field.getName();
				if (name.equals("asString")) {
					continue;
				}
				if (value != null && value.toString().length() > 0 && name != "serialVersionUID") {
					if (value instanceof String && ((String) value).length() > 0) {
						predicates.add(cb.like(cb.lower(root.<String> get(name)), "%" + value.toString().toLowerCase() + "%"));
					} else if ((value instanceof Byte || value instanceof Long) && !value.toString().equals("0")  ) {
						predicates.add(cb.equal(root.<String> get(name), value));
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		if (predicates.size() == 0) {
			return null;
		} else {
			log.trace(cb.and(predicates.toArray(new Predicate[predicates.size()])).toString());
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		}
	}

}
