package br.com.titcs.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
public class AuditRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public List<?> pesquisarHistorico(final Class<?> outClass, Map<String,Object>criteria) {
		AuditReader reader = AuditReaderFactory.get(entityManager);
		AuditQuery query = reader.createQuery().forRevisionsOfEntity(outClass, false,true);
		if(!CollectionUtils.isEmpty(criteria)) {
			criteria.keySet().forEach(key->{
				query.add(AuditEntity.property(key).eq(criteria.get(key)));
			});
		}		
		return query.getResultList();	
		
	}
}
